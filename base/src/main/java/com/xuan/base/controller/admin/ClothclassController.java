package com.xuan.base.controller.admin;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.clothes.ClothClassify;
import com.xuan.base.service.admin.ClothclassService;
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

@RequestMapping("/admin/class")
@Controller
public class ClothclassController {

    @Autowired
    private ClothclassService clothclassService;

    @Autowired
    private OperaterLogService operaterLogService;

    /**
     * 分页搜索列表
     * @param model
     * @param clothClassify
     * @param pageBean
     * @return
     */
    @RequestMapping(value="/list")
    public String list(Model model, ClothClassify clothClassify, PageBean<ClothClassify> pageBean){
        System.out.println("class---"+clothClassify);
        model.addAttribute("title", "品牌列表");
        model.addAttribute("name", clothClassify.getName());
        model.addAttribute("pageBean", clothclassService.findList(clothClassify, pageBean));
        return "admin/class/list";
    }
    /**
     * 新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        return "admin/class/add";
    }
    /**
     * 添加表单提交处理
     * @param clothClassify
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(ClothClassify clothClassify){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(clothClassify);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库新增
        if(clothclassService.save(clothClassify) == null){
            return Result.error(CodeMsg.ADMIN_CLASS_ADD_ERROR);
        }
        operaterLogService.add("添加分类：" + clothClassify.getName());
        return Result.success(true);
    }

    /**
     * 编辑页面
     * @param model
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Model model,@RequestParam(name="id",required=true)Long id){
        model.addAttribute("class", clothclassService.find(id));
        return "admin/class/edit";
    }

    /**
     * 编辑信息表单提交处理
     * @param clothClassify
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(ClothClassify clothClassify){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(clothClassify);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        //到这说明一切符合条件，进行数据库保存
        ClothClassify findById = clothclassService.find(clothClassify.getId());
        //讲提交的用户信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
        BeanUtils.copyProperties(clothClassify, findById, "id","createTime","updateTime");
        if(clothclassService.save(findById) == null){
            return Result.error(CodeMsg.ADMIN_CLASS_EDIT_ERROR);
        }
        operaterLogService.add("编辑分类：" + clothClassify.getName());
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
            clothclassService.delete(id);
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_CLASS_DELETE_ERROR);
        }
        operaterLogService.add("删除分类ID：" + id);
        return Result.success(true);
    }
}
