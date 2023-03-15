package com.xuan.base.dao.admin;

import com.xuan.base.entity.admin.clothes.Brand;
import com.xuan.base.entity.admin.clothes.ClothClassify;
import com.xuan.base.entity.admin.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesDao extends JpaRepository<Clothes, Long> {
    /**
     * 根据服装id查询
     * @param id
     * @return
     */
    @Query(value="select clo from Clothes clo where clo.id = :id")
    Clothes find(@Param("id") Long id);

    /**
     * 更新服装状态
     * @param id
     * @return
     */
    @Modifying
    @Query("update Clothes set status = :newStatus where id=:id")
    void updateClothStatus(@Param("id") Long id, @Param("newStatus") Integer newStatus);

    /**
     * 根据品牌查询服装
     * @param brand
     * @return
     */
    List<Clothes> findClothesByBrand(Brand brand);

    /**
     * 根据分类查询服装
     * @param clothClassify
     * @return
     */
    List<Clothes> findClothesByClassify(ClothClassify clothClassify);
}
