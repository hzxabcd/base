package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.OrderDao;
import com.xuan.base.entity.admin.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


/**
 * 订单信息service层
 * @author Administrator
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	/**
	 * 当order的id不为空时进行编辑，当id为空时则进行添加
	 * @param order
	 * @return
	 */
	public Order save(Order order){
		return orderDao.save(order);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@Transactional
	public Order find(Long id){
		return orderDao.find(id);
	}
	/**
	 * 根据编号查找
	 * @param sn
	 * @return
	 */
	@Transactional
	public Order find(String sn){
		return orderDao.findBySn(sn);
	}
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		orderDao.deleteById(id);
	}
	/**
	 * 获取所有的订单列表
	 * @return
	 */
	public List<Order> findAll(){
		return orderDao.findAll();
	}

	public List<Order> findAllUserOrderbyUsername(String username){
		return orderDao.findAllUserOrderbyUsername(username);
	}
	/**
	 * 根据用户id查询所有订单
	 * @param
	 * @return
	 */
	public List<Order> findAllUserOrderByuserid(Long frontuserId){
		return orderDao.findByfrontuseridOrderByUpdateTimeDesc(frontuserId);
	}
	/**
	 * 获取所有的超时未归还的订单编号
	 * @param
	 * @return
	 */
	public List<String> findTimeOutList(Date nowTime){
		return orderDao.findSnList(nowTime, Order.status_unback);
	}
	/**
	 * 设置订单截止时间
	 * @param orderId
	 * @param status
	 * @return
	 */
    public  boolean SteOrderEntirTime (Long orderId,Integer status)
	{
		orderDao.steOrderEntirTime(orderId,status);
		return true;
	}
	/**
	 * 获取指定账号的不同状态订单
	 * 按照更新时间降序排列
	 * @param
	 * @return
	 */
	public List<Order> findAllOrder(Long frontuserId,Integer status){
		return orderDao.findByfrontuseridAndStatusOrderByUpdateTimeDesc(frontuserId, status);
	}

	/**
	 * 删除订单
	 * @param order
	 * @return
	 */
	@org.springframework.transaction.annotation.Transactional
	public boolean delete(Order order) {
		delete(order.getId());
		return true;
	}

	/**
	 * 修改订单状态
	 * @param sn
	 * @param newStatus
	 * @param oldStatus
	 * @return
	 */
	@org.springframework.transaction.annotation.Transactional
	public boolean updateOrderStatus(String sn,int oldStatus,int newStatus){
		return orderDao.updateOrderStatus(sn, oldStatus,newStatus) > 0;
	}

	@org.springframework.transaction.annotation.Transactional
	public boolean updateRemark(String sn,String remark){
		return orderDao.updateRemark(sn,remark)>0;
	}
	/**
	 * 生成订单
	 * @param order
	 * @return
	 */
	@org.springframework.transaction.annotation.Transactional
	public boolean generateOrder(Order order){
		order = save(order);
		return true;
	}

	/**
	 * 分页获取订单列表
	 * @param order
	 * @param pageBean
	 * @return
	 */
	public PageBean<Order> findPage(Order order, PageBean<Order> pageBean){
		Specification<Order> specification = new Specification<Order>() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Order> root,
										 CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("sn"), "%" + (order.getSn() == null ? "" : order.getSn()) + "%");
				if(order.getPhone()!= null){
					Predicate equal1 = criteriaBuilder.equal(root.get("phone"),order.getPhone());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Order> findAll = orderDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
//	/**
//	 * 分页获取订单列表
//	 * @param order
//	 * @param pageBean
//	 * @return
//	 */
//	public PageBean<Order> findPage(Order order, PageBean<Order> pageBean){
//		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("sn", GenericPropertyMatchers.contains());
//		withMatcher = withMatcher.withIgnorePaths("status");
//		Example<Order> example = Example.of(order, withMatcher);
//		Sort sort = Sort.by(Direction.DESC, "updateTime");
//		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(),sort);
//		Page<Order> findAll = orderDao.findAll(example, pageable);
//		pageBean.setContent(findAll.getContent());
//		pageBean.setTotal(findAll.getTotalElements());
//		pageBean.setTotalPage(findAll.getTotalPages());
//		return pageBean;
//	}
}
