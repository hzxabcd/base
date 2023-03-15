package com.xuan.base.controller.admin;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.Check;
import com.xuan.base.entity.admin.Paisong;
import com.xuan.base.entity.admin.User;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员派送单管理部分
 */
@Controller
@RequestMapping("/admin/paisong")
public class PaisongController {
    @Autowired
    public PaisongService paisongService;

    @Autowired
    public UserService userService;

    @Autowired
    private OperaterLogService operaterLogService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public CheckService checkService;

    /*
     * 查询所有派送单列表
     * */
    @GetMapping("/all")
    @ResponseBody
    public List<Paisong> paisongsList(){
        return paisongService.selectAll();
    }

    /**
     * 1.派送单页面
     * @param model
     * @param user
     * @param pageBean
     * @return
     */
    @GetMapping(value="/list")
    public String list(Model model,User user,PageBean<Paisong> pageBean){
        List<Paisong>paisongs=paisongService.selectAll();
        pageBean.setTotal(paisongs.size());
        pageBean.setTotalPage((paisongs.size()-1)/10+1);
        pageBean.setPageSize(10);
        pageBean.setShowPageSize(paisongs.size()/10+1);
        List<Integer> integers=new ArrayList<>();

        for (int i = 2; i <=paisongs.size()/10+1 ; i++) {
            integers.add(i);
        }

        model.addAttribute("title", "派送单列表");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("paisongList", paisongService.selectAll());
        model.addAttribute("pageBean", pageBean);
        System.out.println(paisongsList());
        return "admin/paisong/list";
    }
    //模糊查询
    @PostMapping(value="/list")
    public String list(Model model,User user,PageBean<User> pageBean,String withname){
        List<Paisong>paisongs=paisongService.getByname(withname);
        pageBean.setTotal(paisongs.size());
        pageBean.setTotalPage((paisongs.size()-1)/10+1);
        pageBean.setPageSize(10);
        pageBean.setShowPageSize(paisongs.size()/10+1);
        List<Integer> integers=new ArrayList<>();

        for (int i = 2; i <=paisongs.size()/10+1 ; i++) {
            integers.add(i);
        }

        model.addAttribute("title", "派送单列表");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("paisongList", paisongService.getByname(withname));
        return "admin/paisong/list";
    }




    /**
     * 2.添加派送单
     * @param orderId
     * @return
     */
    @PostMapping("/add_paisong")
    @ResponseBody
    public Result<Boolean> insertPaisong(int orderId,String wuliu_no){

        Order order=orderService.find(new Long((long)orderId));

        Paisong new_paisong=new Paisong();
        new_paisong.setName(order.getClothes().getName());
        new_paisong.setCreate_time(new Date());
        new_paisong.setUpdate_time(new Date());
        new_paisong.setDingdan_id(orderId);
        new_paisong.setDingdan_no(order.getSn());
        new_paisong.setWuliu_no(wuliu_no);
        new_paisong.setStatue(1);


        try {
            //修改订单状态
            orderService.updateOrderStatus(order.getSn(),0,1);
            paisongService.save(new_paisong);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
        }
        operaterLogService.add("根据订单创建派送单，订单ID：" + orderId);
        return Result.success(true);
    }

    /**
     * 3.更新状态
     * @param id
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Boolean> update(int id){
        if (paisongService.getStatue(id) == 1 ||paisongService.getStatue(id)==2){//这个状态只能用户修改
            return Result.error(CodeMsg.DATA_ERROR);
        }else if(paisongService.getStatue(id) == 4 ){//这个状态只能审核节目修改
            return Result.error(CodeMsg.CPACHA_EMPTY);
        }
        else{
            try {
                if(paisongService.getStatue(id)==3){//当前状态是管理员待收货
                    Paisong paisong=paisongService.getPaisong(id);//当前派送单信息

                    Order order=orderService.find(new Long((long)paisong.getDingdan_id()));//获取订单
                    FrontUser frontUser =order.getFrontuserid();//获取前台用户

                    Check check=new Check();//生成审核记录
                    check.setOrder(order);
                    check.setFrontUser(frontUser);
                    checkService.save(check);

                    orderService.updateOrderStatus(paisong.getDingdan_no(),3,4);//更新订单状态
                }
                paisongService.update(id);

            } catch (Exception e) {
                return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
            }
            operaterLogService.add("更新派送单状态，派送单ID：" + id);
            return Result.success(true);
        }
    }

    /**
     *4.根据派送单id获取派送单
     * @param id
     * @return
     */
    @GetMapping("/getbyid")
    @ResponseBody
    public Paisong getById(int id){
        return paisongService.getPaisong(id);
    }

    /**
     * 5.根据id逻辑删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> deletebyid(int id){
        if (paisongService.getStatue(id) <4){

            return Result.error(CodeMsg.ADMIN_NO_RIGHT);
        }else{
            try {
                paisongService.delete(id);
            } catch (Exception e) {
                return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
            }
            operaterLogService.add("删除派送单，派送单ID：" + id);
            return Result.success(true);
        }
    }

}
