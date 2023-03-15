package com.xuan.base.controller.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.entity.admin.User;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.front.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 梦致A远
 * @Date: 2023/1/6 17:20
 * @Description:
 */

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    //查看用户信息/个人信息
    @GetMapping("/frontuser/list")

    public String getAllUser(Model model, User user, PageBean<FrontUser> pageBean){
       // List<FrontUser> frontUserslist=userService.findAllUser();
        List<FrontUser> paisongs=userService.findAllUser();
        pageBean.setTotal(paisongs.size());
        pageBean.setTotalPage((paisongs.size()-1)/10+1);
        pageBean.setPageSize(10);
        pageBean.setShowPageSize(paisongs.size()/10+1);
        List<Integer> integers=new ArrayList<>();

        for (int i = 2; i <=paisongs.size()/10+1 ; i++) {
            integers.add(i);
        }

        model.addAttribute("title", "前台用户列表");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("paisongList",paisongs);
        model.addAttribute("pageBean", pageBean);
        System.out.println(paisongs);
        return "/admin/frontuser/list";
    }

}
