package com.xuan.base.controller.front;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.Result;
import com.xuan.base.dao.front.UserMapper;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.front.HostHolder;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: 梦致A远
 * @Date: 2023/1/3 14:54
 * @Description:
 */

@Api(description = "用户会员管理")
@Controller
@Slf4j
@RequestMapping("/user")
public class frontUserController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    //管理员解锁冻结会员
    @GetMapping("/edit")
    @ResponseBody
    public Result<Boolean> unlock(Model model, int userId){
        FrontUser user = userService.findUserById(userId);
        System.out.println(user);
        boolean flag;
        if(user.getStatus()==1){
            flag = userService.updateStatus(userId,0);
            System.out.println("解锁");
        }else{
            flag = userService.updateStatus(userId,1);
            System.out.println("冻结");
        }

        if(flag){
            return Result.success(true);
        }else {
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }



    //修改密码，model变量用来向页面返回数据
    //@LoginRequired
    @PostMapping( "/changePassword")
    @ResponseBody
    public Result<Boolean> changePassword(String oldPassword, String newPassword, String confirmPassword, Model model) {
        FrontUser user = hostHolder.getUser();
        Map<String, Object> map = userService.changePassword(user,oldPassword, newPassword, confirmPassword);
        if(map == null || map.isEmpty()){
            return Result.success(true);
        }else {
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }


    @GetMapping("/test")
    @ResponseBody
    public String test(){
        userMapper.updatePassword(2,"111");
        return "sucess";
    }


    @GetMapping("/usercenter")
    public String userInfo(Model model){
        FrontUser frontUser = hostHolder.getUser();

        model.addAttribute("frontUser",frontUser);
        return "/home/usercenter/index";
    }

    @PostMapping("/updateFrontUser")
    public Result<Boolean> updateFrontUserInfo(FrontUser user){
        boolean i = userService.updateUser(user);
        if(i){
            return Result.success(true);
        }else{
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }


}
