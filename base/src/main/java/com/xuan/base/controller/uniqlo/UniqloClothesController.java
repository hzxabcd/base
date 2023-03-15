package com.xuan.base.controller.uniqlo;

import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.ClothesService;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.util.front.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
* 服装管理
* */
@RequestMapping("/uniqlo/clothes")
@Controller
public class UniqloClothesController {

    @Autowired
    private ClothesService clothesService;
    
    @Autowired
    private OrderService orderService;
    @Autowired
    private HostHolder hostHolder;

//    @Autowired
//    private FrontUserService frontUserService;

    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public String exam(Model model,@RequestParam(name="clothesId",required=true)Long clothesId)
    {
       // FrontUser frontUser = (FrontUser) SessionUtil.get(SessionConstant.SESSION_FRONTUSER_LOGIN_KEY);
      // FrontUser frontUser = frontUserService.findUserById(1);
        //model.addAttribute("frontuser", frontUser);
        FrontUser frontUser=hostHolder.getUser();

        model.addAttribute("frontUser",frontUser);
        model.addAttribute("clothes",clothesService.find(clothesId));
        return "/home/goods/detail";
    }
}
