package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.BrandDao;
import com.xuan.base.entity.admin.clothes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandDao brandDao;

    /**
     * 根据品牌id查询
     * @param id
     * @return
     */
    public Brand find(Long id){
        return brandDao.find(id);
    }

    /**
     * 获取所有的品牌列表
     * @return
     */
    public List<Brand> findAll(){
        return brandDao.findAll();
    }

    /**
     * 品牌添加/编辑操作
     * @param brand
     * @return
     */
    public Brand save(Brand brand){
        return brandDao.save(brand);
    }

    /**
     * 按照品牌id删除
     * @param id
     */
    public void delete(Long id){
        brandDao.deleteById(id);
    }

    /**
     * 分页查询品牌列表
     * @param brand
     * @param pageBean
     * @return
     */
//    public PageBean<Brand> findList(Brand brand, PageBean<Brand> pageBean){
//        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
//        Page<Brand> findAll = brandDao.findAll(pageable);
//        pageBean.setContent(findAll.getContent());
//        pageBean.setTotal(findAll.getTotalElements());
//        pageBean.setTotalPage(findAll.getTotalPages());
//        return pageBean;
//    }
    public PageBean<Brand> findList(Brand brand, PageBean<Brand> pageBean) {
        ExampleMatcher withMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
//                .withIgnorePaths( "status", "recommend","type");
        Example<Brand> example = Example.of(brand, withMatcher);
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), sort);
        Page<Brand> findAll = brandDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }
}
