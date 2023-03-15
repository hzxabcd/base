package com.xuan.base.controller.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.Check;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.service.admin.CheckService;
import com.xuan.base.service.admin.PaisongService;
import com.xuan.base.service.front.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 审核管理
 */
@RequestMapping("/admin/check")
@Controller
public class CheckController {

    @Autowired
    private CheckService checkService;

    @Autowired
    private UserService userService;
    @Autowired
    private PaisongService paisongService;
    /**
     * 分页搜索审核列表
     * @param model
     * @param check
     * @param pageBean
     * @return
     */
    @RequestMapping(value="/list")
    public String list(Model model, Check check, PageBean<Check> pageBean){
        model.addAttribute("title", "审核列表");
        model.addAttribute("pageBean", checkService.findList(check, pageBean));
        return "/admin/check/list";
    }

    /**
     * 审核服装页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/check_submit", method = RequestMethod.GET)
    public String check(Model model, @RequestParam(name="id", required = true)Long id){
        model.addAttribute("check", checkService.find(id));
        return "admin/check/info";
    }

    /**
     * 提交审核单
     * @param status
     * @param type
     * @return
     */
    @PostMapping("/check_submit")
    @ResponseBody
    public Result<Boolean> checkSubmit(long id, Integer status, Integer type, BigDecimal compensation){
        System.out.println("id="+id+"  status="+status+"  type="+type);
        //try{
            Check findbyid = checkService.find(id);
            findbyid.setType(type);
            findbyid.setStatus(status);
            findbyid.getOrder().setCompensation(compensation);

            Long orderid=findbyid.getOrder().getId();//订单id
            paisongService.updateFinish(orderid.intValue());

            if(type == 1){  // 服装完好，订单完成
                findbyid.getOrder().setStatus(Order.status_finish);
               userService.updateStatus(findbyid.getOrder().getFrontuserid().getId(),0) ;
            }
            else if(type == 2){ // 服装异常，订单损坏
                findbyid.getOrder().setStatus(Order.status_abnormal);
            }
            checkService.save(findbyid);
//        }catch (Exception e){
//            return Result.error(CodeMsg.ADMIN_CHECK_SUBMIT_ERROR);
//        }
        return Result.success(true);
    }


}
