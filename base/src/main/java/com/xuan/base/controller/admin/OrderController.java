package com.xuan.base.controller.admin;

import com.xuan.base.bean.PageBean;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.service.admin.PaisongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 订单管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaisongService paisongService;
	/**
	 * 订单列表
	 * @param model
	 * @param order
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Order order, PageBean<Order> pageBean){
		model.addAttribute("sn",order.getSn());
		model.addAttribute("phone",order.getPhone());
		model.addAttribute("pageBean", orderService.findPage(order, pageBean));
		return "admin/order/list";
	}

	/**
	 * 查看订单详情
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/view_detail",method=RequestMethod.POST)
	@ResponseBody
	public Result<Order> viewDetail(@RequestParam(name="orderId",required=true)Long orderId){
		return Result.success(orderService.find(orderId));
	}




}
