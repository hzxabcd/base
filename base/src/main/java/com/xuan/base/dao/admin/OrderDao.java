package com.xuan.base.dao.admin;
/**
 * 订单信息管理数据库操作层
 */

import com.xuan.base.entity.admin.order.Order;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;


@Repository
public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

	@Query("select o from Order o where o.id = :id")
	Order find(@Param("id") Long id);

	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	Order findBySn(String sn);
	
	List<Order> findByfrontuserid(Long frontuserId);

	@Query("select o from Order o where o.frontuserid.id = :frontuserid order by o.updateTime desc")
	List<Order> findByfrontuseridOrderByUpdateTimeDesc(@Param("frontuserid") Long frontuserid);

	@Query("select o from Order o where o.frontuserid.userName = :username order by o.updateTime desc")
	List<Order> findAllUserOrderbyUsername(@Param("username") String username);

	List<Order> findByfrontuseridAndStatusOrderByUpdateTimeDesc(Long frontuserId,Integer status);

	//获得超时未归还的订单编号
	@Query("select o.sn from Order o where o.expireTime <= :nowTime and o.status = :status")
	List<String> findSnList(@Param("nowTime") Date nowTime, @Param("status") Integer status);



	@Modifying
	@Query("update Order set status = :newStatus where sn = :sn and status = :oldStatus")
	int updateOrderStatus(@Param("sn") String sn, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

	@Modifying
	@Query("update Order set status = :newStatus where id = :id ")
	int steOrderEntirTime(@Param("id") Long id, @Param("newStatus") Integer newStatus);

	@Modifying
	@Query("update Order set remark = :remark where sn = :sn ")
	int updateRemark(@Param("sn") String sn, @Param("remark") String remark);
}
