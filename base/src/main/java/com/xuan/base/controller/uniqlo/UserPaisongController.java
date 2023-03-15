package com.xuan.base.controller.uniqlo;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.Paisong;
import com.xuan.base.entity.admin.User;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.OperaterLogService;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.service.admin.PaisongService;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.front.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户派送单部分
 */

@Controller
@RequestMapping("/uniqlo/upaisong")
public class UserPaisongController {
    @Autowired
    public PaisongService paisongService;
    @Autowired
    public OperaterLogService operaterLogService;
    @Autowired
    public UserService userService;
    @Autowired
    public HostHolder hostHolder;

    /**
     * 根据用户id获取所有的派送单信息
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/list")
    public String getUserList(Model model, User user){
        FrontUser frontUser=hostHolder.getUser();

        //System.out.println(frontUser.getId());
        System.out.println(paisongService.getByUserId(frontUser.getId()));
        model.addAttribute("frontUser",frontUser);

        model.addAttribute("plist",paisongService.getByUserId(frontUser.getId()));
        model.addAttribute("username", "hzx");
        //model.addAttribute("pageBean", userService.findList(user, pageBean));
        return "home/paisong/list";
    }

    /**
     * 2.更新收货状态
     * @param id
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Boolean> update(int id){
        try {
            Paisong paisong=paisongService.getPaisong(id);//当前派送单信息
            orderService.updateOrderStatus(paisong.getDingdan_no(),1,2);//用户确认收货，更新订单状态
            paisongService.update(id);
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
        }
        operaterLogService.add("更新派送单状态，派送单ID：" + id);
        return Result.success(true);
    }
    //用户寄出更新新物流信息
    @PostMapping("/update2")
    @ResponseBody
    public Result<Boolean> update2(int id,String wuliu_no){

        try {

            Paisong paisong=paisongService.getPaisong(id);//当前派送单信息
            orderService.updateOrderStatus(paisong.getDingdan_no(),2,3);//用户寄出，更新订单状态

            paisong.setWuliu_no(wuliu_no);
            paisong.setStatue(3);
            System.out.println(paisong);
            paisongService.save(paisong);
            System.out.println("成功");
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
        }
        operaterLogService.add("用户寄出，派送单ID：" + id);
        return Result.success(true);
    }


    /**
     * 测试
     *
     * @return
     */
    @Autowired
    public OrderService orderService;

    @GetMapping("/detail")
    //@ResponseBody
    public String test(Model model){

        Long orderid=new Long((long)1);
        Order order=orderService.find(orderid);
        System.out.println(order);
        model.addAttribute("order",order);
        return "/home/goods/detail";
    }

}
