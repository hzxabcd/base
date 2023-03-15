package com.xuan.base.controller.uniqlo;

import com.xuan.base.bean.PageBean;
import com.xuan.base.entity.admin.clothes.Brand;
import com.xuan.base.entity.admin.clothes.ClothClassify;
import com.xuan.base.entity.admin.clothes.Clothes;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.BrandService;
import com.xuan.base.service.admin.ClothclassService;
import com.xuan.base.service.admin.ClothesService;
import com.xuan.base.util.front.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class HomeController {

    @Resource
    private ClothesService clothesService;

    @Resource
    private BrandService brandService;

    @Resource
    private ClothclassService clothclassService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping("/uniqlo/home")
    public String index(Model model, Clothes clothes, PageBean<Clothes> pageBean) {
        model.addAttribute("pageBean", clothesService.findList(clothes, pageBean));
        FrontUser frontUser=hostHolder.getUser();

        model.addAttribute("name",clothes.getName());
        model.addAttribute("frontUser",frontUser);
        model.addAttribute("goodsCategorys", brandService.findAll());
        model.addAttribute("classCategorys", clothclassService.findAll());
        return "/home/index/index";
    }

    @RequestMapping("/home/goods/list")
    public String list(@RequestParam(name="cid",required=true)Long cid,
                       Clothes clothes,
                       PageBean<Clothes> pageBean,
                       Model model){
        Brand brand = brandService.find(cid);
        if(brand == null){


            model.addAttribute("msg", "物品分类不存在！");
            return "error/runtime_error";
        }
        FrontUser frontUser=hostHolder.getUser();
        model.addAttribute("frontUser",frontUser);
        model.addAttribute("pageBean", clothesService.findListByBrand(brand,clothes, pageBean));
        model.addAttribute("gc", brand);
        model.addAttribute("goodsCategorys", brandService.findAll());
        model.addAttribute("classCategorys", clothclassService.findAll());
        return "home/goods/list";
    }

    @RequestMapping("/home/goods/class")
    public String classList(@RequestParam(name="cid",required=true)Long cid,
                            Clothes clothes,
                            PageBean<Clothes> pageBean,
                            Model model) {
        ClothClassify clothClassify = clothclassService.find(cid);
        if(clothClassify == null){
            model.addAttribute("msg", "物品分类不存在！");
            return "error/runtime_error";
        }
        model.addAttribute("gc", clothClassify);
        model.addAttribute("pageBean", clothesService.findListByClass(clothClassify, clothes, pageBean));
        model.addAttribute("goodsCategorys", brandService.findAll());
        model.addAttribute("classCategorys", clothclassService.findAll());
        return "home/goods/list";
    }

    @RequestMapping("/test/br")
    public String testBrand() {
        System.out.println(brandService.findAll());
        return "This is a test";
    }



}
