package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.ClothclassDao;
import com.xuan.base.entity.admin.clothes.ClothClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothclassService {

    @Autowired
    private ClothclassDao clothclassDao;

    /**
     * 根据分类id查询
     * @param id
     * @return
     */
    public ClothClassify find(Long id){
        return clothclassDao.find(id);
    }

    /**
     * 获取所有的分类列表
     * @return
     */
    public List<ClothClassify> findAll(){
        return clothclassDao.findAll();
    }

    /**
     * 分类添加/编辑操作
     * @param clothClassify
     * @return
     */
    public ClothClassify save(ClothClassify clothClassify){
        return clothclassDao.save(clothClassify);
    }

    /**
     * 按照分类id删除
     * @param id
     */
    public void delete(Long id){
        clothclassDao.deleteById(id);
    }

    /**
     * 分页查询用户列表
     * @param clothClassify
     * @param pageBean
     * @return
     */
//    public PageBean<ClothClassify> findList(ClothClassify clothClassify, PageBean<ClothClassify> pageBean){
//        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
//        Page<ClothClassify> findAll = clothclassDao.findAll(pageable);
//        pageBean.setContent(findAll.getContent());
//        pageBean.setTotal(findAll.getTotalElements());
//        pageBean.setTotalPage(findAll.getTotalPages());
//        return pageBean;
//    }
    public PageBean<ClothClassify> findList(ClothClassify clothClassify, PageBean<ClothClassify> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths( "sex");
        Example<ClothClassify> example = Example.of(clothClassify, withMatcher);
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
        Page<ClothClassify> findAll = clothclassDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }
}
