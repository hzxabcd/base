package com.xuan.base.dao.admin;

import com.xuan.base.entity.admin.clothes.ClothClassify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothclassDao extends JpaRepository<ClothClassify, Long> {
    /**
     * 根据分类的id查询
     * @param id
     * @return
     */
    @Query("select item from ClothClassify item where item.id = :id")
    ClothClassify find(@Param("id") Long id);
}
