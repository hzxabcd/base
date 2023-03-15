package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.ClothesDao;
import com.xuan.base.entity.admin.clothes.Brand;
import com.xuan.base.entity.admin.clothes.ClothClassify;
import com.xuan.base.entity.admin.clothes.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服装管理service
 *
 */
@Service
public class ClothesService {

    @Autowired
    private ClothesDao clothesDao;

    /**
     * 根据服装id查询
     * @param id
     * @return
     */
    public Clothes find(Long id){
        return clothesDao.find(id);
    }

    /**
     * 获取所有的服装列表
     * @return
     */
    public List<Clothes> findAll(){
        return clothesDao.findAll();
    }

    /**
     * 服装添加/编辑操作
     * @param clothes
     * @return
     */
    public Clothes save(Clothes clothes){
        return clothesDao.save(clothes);
    }

    /**
     * 按照服装id删除
     * @param id
     */
    public void delete(Long id){
        clothesDao.deleteById(id);
    }

    /**
     * 分页查询用户列表
     * @param clothes
     * @param pageBean
     * @return
     */
//    public PageBean<Clothes> findList(Clothes clothes, PageBean<Clothes> pageBean){
////        ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
////        withMatcher = withMatcher.withIgnorePaths("status","creatTime");
////        Example<Clothes> example = Example.of(clothes, withMatcher);
//        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
////        Page<Clothes> findAll = clothesDao.findAll(example, pageable);
//        System.out.println("服装：5219465151865198451”;");
//        Page<Clothes> findAll = clothesDao.findAll(pageable);
//        pageBean.setContent(findAll.getContent());
//        pageBean.setTotal(findAll.getTotalElements());
//        pageBean.setTotalPage(findAll.getTotalPages());
//        return pageBean;
//    }
    public PageBean<Clothes> findList(Clothes clothes,PageBean<Clothes> pageBean){
        ExampleMatcher withMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths( "status", "recommend","type");
        Example<Clothes> example = Example.of(clothes, withMatcher);
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<Clothes> findAll = clothesDao.findAll(example, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    public PageBean<Clothes> findListByBrand(Brand brand,
                                             Clothes clothes,
                                             PageBean<Clothes> pageBean){
//        ExampleMatcher withMatcher = ExampleMatcher.matching()
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withIgnorePaths( "status", "recommend","type");
//        Example<Clothes> example = Example.of(clothes, withMatcher);
//        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
//        Page<Clothes> findAll = clothesDao.findAll(example, pageable);
        List<Clothes> findClothes = clothesDao.findClothesByBrand(brand);
        pageBean.setContent(findClothes);
//        pageBean.setTotal(findAll.getTotalElements());
//        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    public PageBean<Clothes> findListByClass(ClothClassify clothClassify,
                                             Clothes clothes,
                                             PageBean<Clothes> pageBean) {
        //        ExampleMatcher withMatcher = ExampleMatcher.matching()
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withIgnorePaths( "status", "recommend","type");
//        Example<Clothes> example = Example.of(clothes, withMatcher);
//        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
//        Page<Clothes> findAll = clothesDao.findAll(example, pageable);
        List<Clothes> findClothes = clothesDao.findClothesByClassify(clothClassify);
        pageBean.setContent(findClothes);
//        pageBean.setTotal(findAll.getTotalElements());
//        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

}
