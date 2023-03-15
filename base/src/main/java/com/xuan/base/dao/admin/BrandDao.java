package com.xuan.base.dao.admin;

import com.xuan.base.entity.admin.clothes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Long> {
    /**
     * 根据品牌id查询
     * @param id
     * @return
     */
    @Query("select item from Brand item where item.id = :id")
    Brand find(@Param("id") Long id);
}
