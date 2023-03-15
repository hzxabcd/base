package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.CheckDao;
import com.xuan.base.entity.admin.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {

    @Autowired
    private CheckDao checkDao;

    /**
     * 根据审核单id查询
     * @param id
     * @return
     */
    public Check find(Long id){
        return checkDao.find(id);
    }

    /**
     * 获取所有的审核单列表
     * @return
     */
    public List<Check> findAll(){
        return checkDao.findAll();
    }

    /**
     * 审核添加/编辑操作
     * @param check
     * @return
     */
    public Check save(Check check){
        return checkDao.save(check);
    }

    /**
     * 按照服装id删除
     * @param id
     */
    public void delete(Long id){
        checkDao.deleteById(id);
    }

    /**
     * 分页查询列表
     * @param check
     * @param pageBean
     * @return
     */
    public PageBean<Check> findList(Check check, PageBean<Check> pageBean){
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
        Page<Check> findAll = checkDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }
}
