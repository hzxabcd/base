package com.xuan.base.dao.admin;


import com.xuan.base.entity.admin.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckDao extends JpaRepository<Check, Long> {
    /**
     * 根据审核单id查询
     * @param id
     * @return
     */
    @Query(value="select che from Check che where che.id = :id")
    Check find(@Param("id") Long id);
}
