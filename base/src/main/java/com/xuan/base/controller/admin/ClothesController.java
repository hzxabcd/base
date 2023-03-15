package com.xuan.base.controller.admin;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.clothes.Clothes;
import com.xuan.base.service.admin.BrandService;
import com.xuan.base.service.admin.ClothclassService;
import com.xuan.base.service.admin.ClothesService;
import com.xuan.base.service.admin.OperaterLogService;
import com.xuan.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 服装管理
 * */
@RequestMapping("/admin/clothes")
@Controller
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ClothclassService clothclassService;

    @Autowired
    private OperaterLogService operaterLogService;

    /**
     * 分页搜索列表
     * @param model
     * @param clothes
     * @param pageBean
     * @return
     */
    @RequestMapping(value="/list")
    public String list(Model model, Clothes clothes, PageBean<Clothes> pageBean){
        System.out.println("clothes" + clothes);
        model.addAttribute("title", "服装列表");
        model.addAttribute("name", clothes.getName());
        model.addAttribute("pageBean", clothesService.findList(clothes, pageBean));
        return "admin/clothes/list";
    }
    /**
     * 新增服装页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("classes", clothclassService.findAll());
        return "admin/clothes/add";
    }

    /**
     * 服装添加表单提交处理
     * @param clothes
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(Clothes clothes){
        System.out.println("add表单：" + clothes);
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(clothes);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库新增
        if(clothesService.save(clothes) == null){
            return Result.error(CodeMsg.ADMIN_CLOTHES_ADD_ERROR);
        }
        operaterLogService.add("添加服装：" + clothes.getName());
        return Result.success(true);
    }

    /**
     * 服装编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Model model,@RequestParam(name="id",required=true)Long id){
        model.addAttribute("clothes", clothesService.find(id));
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("classes", clothclassService.findAll());
        return "admin/clothes/edit";
    }

    /**
     * 编辑用户信息表单提交处理
     * @param clothes
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(Clothes clothes){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(clothes);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库保存
        Clothes findById = clothesService.find(clothes.getId());
        //讲提交的用户信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
        BeanUtils.copyProperties(clothes, findById, "id","createTime","updateTime");
        if(clothesService.save(findById) == null){
            return Result.error(CodeMsg.ADMIN_CLOTHES_EDIT_ERROR);
        }
        operaterLogService.add("编辑用户，用户名：" + clothes.getName());
        return Result.success(true);
    }

    /**
     * 删除服装
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
        try {
            System.out.println("id="+id);
            clothesService.delete(id);
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_CLOTHES_DELETE_ERROR);
        }
        operaterLogService.add("删除服装ID：" + id);
        return Result.success(true);
    }
}
