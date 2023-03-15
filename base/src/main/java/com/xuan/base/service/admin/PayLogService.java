package com.xuan.base.service.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.dao.admin.PayLogDao;
import com.xuan.base.entity.admin.order.PayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 支付记录信息service层
 * @author Administrator
 *
 */
@Service
public class PayLogService {

	@Autowired
	private PayLogDao payLogDao;

	/**
	 * 当payLog的id不为空时进行编辑，当id为空时则进行添加
	 * @param payLog
	 * @return
	 */
	public PayLog save(PayLog payLog){
		return payLogDao.save(payLog);
	}

	/**
	 * 根据编号查找
	 * @param sn
	 * @return
	 */
	public PayLog find(String sn){
		return payLogDao.findBySn(sn);
	}


	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public PayLog findById(Long id){
		return payLogDao.find(id);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		payLogDao.deleteById(id);
	}



	/**
	 * 搜索制定时间内的支付记录
	 * @param createTime
	 * @return
	 */
	public List<PayLog> findAll(Date createTime){
		return payLogDao.findByCreateTimeGreaterThan(createTime);
	}

	/**
	 * 分页查找支付记录
	 * @param payLog
	 * @param pageBean
	 * @return
	 */
	public PageBean<PayLog> findPage(PayLog payLog, PageBean<PayLog> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("sn", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status","mounth");
		Example<PayLog> example = Example.of(payLog, withMatcher);
		Sort sort = Sort.by(Direction.DESC, "updateTime");
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(),sort);
		Page<PayLog> findAll = payLogDao.findAll(example, pageable);
//		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
//		Page<PayLog> findAll = payLogDao.findAll( pageable);

		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}

	/**
	 * 返回总记录数
	 * @return
	 */
	public long count(){
		return payLogDao.count();
	}

	/**
	 * 统计支付成功数
	 * @return
	 */
	public long countPaySuccess(){
		return payLogDao.countByStatus(PayLog.status_paid);
	}




}
