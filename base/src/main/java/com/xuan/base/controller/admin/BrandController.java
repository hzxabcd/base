package com.xuan.base.controller.admin;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.clothes.Brand;
import com.xuan.base.service.admin.OperaterLogService;
import com.xuan.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuan.base.service.admin.BrandService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin/brand")
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private OperaterLogService operaterLogService;

    /**
     * 分页搜索列表
     * @param model
     * @param brand
     * @param pageBean
     * @return
     */
    @RequestMapping(value="/list")
    public String list(Model model, Brand brand, PageBean<Brand> pageBean){
        System.out.println(brand);
        model.addAttribute("title", "品牌列表");
        model.addAttribute("name", brand.getName());
        model.addAttribute("pageBean", brandService.findList(brand, pageBean));
        return "admin/brand/list";
    }
    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        return "admin/brand/add";
    }
    /**
     * 添加表单提交处理
     * @param brand
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(Brand brand){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(brand);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库新增
        if(brandService.save(brand) == null){
            return Result.error(CodeMsg.ADMIN_BRAND_ADD_ERROR);
        }
        operaterLogService.add("添加品牌：" + brand.getName());
        return Result.success(true);
    }

    /**
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Model model,@RequestParam(name="id",required=true)Long id){
        model.addAttribute("brand", brandService.find(id));
        return "admin/brand/edit";
    }

    /**
     * 编辑信息表单提交处理
     * @param brand
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(Brand brand){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(brand);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库保存
        Brand findById = brandService.find(brand.getId());
        //讲提交的用户信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
        BeanUtils.copyProperties(brand, findById, "id","createTime","updateTime");
        if(brandService.save(findById) == null){
            return Result.error(CodeMsg.ADMIN_BRAND_EDIT_ERROR);
        }
        operaterLogService.add("编辑品牌：" + brand.getName());
        return Result.success(true);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
        try {
            System.out.println("id="+id);
            brandService.delete(id);
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_BRAND_DELETE_ERROR);
        }
        operaterLogService.add("删除品牌ID：" + id);
        return Result.success(true);
    }
}
