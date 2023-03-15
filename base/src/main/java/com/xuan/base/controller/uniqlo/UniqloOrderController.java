package com.xuan.base.controller.uniqlo;

import com.xuan.base.bean.CodeMsg;
import com.xuan.base.bean.MemberType;
import com.xuan.base.bean.PaymentType;
import com.xuan.base.bean.Result;
import com.xuan.base.entity.admin.User;
import com.xuan.base.entity.admin.clothes.Clothes;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.admin.order.PayLog;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.ClothesService;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.service.admin.PayLogService;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.StringUtil;
import com.xuan.base.util.front.HostHolder;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RequestMapping("/uniqlo/order")
@Controller
public class UniqloOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private HostHolder hostHolder;
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private PayLogService payLogService;
	@Autowired
	private UserService userService;

	private Logger log = LoggerFactory.getLogger(UniqloOrderController.class);

	/**
	 * 生成订单
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> addOrder(
			@RequestParam(name="clothesid",required=true)Long clothesId,
			@RequestParam(name="days",required=true)Integer days,
			@RequestParam(name="phone",required=true)String phone,
			@RequestParam(name="address",required=true)String address,
			@RequestParam(name="remark")String remark
			){
		//获取登录用户，以及服装
		FrontUser frontUser = hostHolder.getUser();
		//FrontUser frontUser = userService.findUserByName("故与纵QAQ");

		if(frontUser.getType()==0) //普通用户 直接返回
		{
			return Result.error(CodeMsg.UNIQLO_USER_INSUFFICIENT);
		}
		Clothes clothes = clothesService.find(clothesId);
		//会员等级与衣服等级的关系
		if(frontUser.getType() < clothes.getType())
		{
			return Result.error(CodeMsg.UNIQLO_USER_UNTHORITY);
		}
		//检查商品是否存在
		if(clothes == null)
		{
			return Result.error(CodeMsg.UNIQLO_CLOTHES_NO_EXIST);
		}
		//检查商品是否已经被租赁出去
		if(clothes.getStatus()!= 1)
		{
			return Result.error(CodeMsg.UNIQLO_CLOTHES_STATUS_UNALLOW);
		}
		//服装与用户资格正常，允许租赁

		Order order=new Order();
		order.setAddress(address);
		order.setClothes(clothes);
		order.setDays(days);
		order.setPhone(phone);
		order.setFrontuserid(frontUser);
		order.setRemark(remark);
		order.setSn(StringUtil.generateSn("UNIQLO",""));

		if(!orderService.generateOrder(order)){
			return Result.error(CodeMsg.UNIQLO_ADD_ORDER_ERROR);
		}
		log.info("订单已经生成");
		//更改服装状态为已租赁
		clothes.setType(2);
		if(clothesService.save(clothes) == null){
			return Result.error(CodeMsg.UNIQLO_CLOTHES_SETSTATUS_ERROR);
		}
		return Result.success(true);
	}

	/**
	 * 删除订单
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="/delete_order",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteOrder(@RequestParam(name="order_sn",required=true)String sn){
		Order order = orderService.find(sn);
		if(order == null){
			return Result.error(CodeMsg.UNIQLO_ORDER_NO_EXIST);
		}
		if(order.getStatus() == Order.status_unback){
			return Result.error(CodeMsg.UNIQLO_ORDER_CLOTHES_UNBACK);
		}
		if(order.getStatus() == Order.status_abnormal){
			return Result.error(CodeMsg.UNIQLO_ORDER_CLOTHES_ABNORMAL);
		}
		orderService.delete(order);
		return Result.success(true);
	}
	/**
	 * 充值会员时生成支付记录
	 * @param
	 * @return
	 */
	@RequestMapping(value="/generate_pay_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> generatePayLog(
			@RequestParam(name="mounth",required=true)Integer mounth,
			@RequestParam(name="type",required=true)Integer type,
			@RequestParam(name="money",required=true)String money
	){
		//这里计算 充值的会员类型月份 与所传递金额是否一致
		int paymoney;
		if(type == 1)
		{
			paymoney= mounth * MemberType.Regular_Member.getMoney();
		}else if(type==2){
			paymoney= mounth * MemberType.Super_Member.getMoney();
		}else{
			return Result.error(CodeMsg.MEMBER_STATUS_NO_EXIST);
		}
		if(paymoney!=Integer.parseInt(money))
		{
			return Result.error(CodeMsg.MEMBER_MONEY_ERROR);
		}
		//获取登录用户
		FrontUser frontUser = hostHolder.getUser();
		//测试用户
//		FrontUser frontUser = userService.findUserByName("故与纵QAQ");
		PayLog payLog = new PayLog();
		payLog.setFrontuserid(frontUser);
		payLog.setSn(StringUtil.generateSn("Member",""));
		payLog.setMoney(NumberUtils.createBigDecimal(money));
		payLog.setPaymentType(PaymentType.Alipay);
		payLog.setPaytype(0);
		payLog.setMounth(mounth);
		payLog.setPayid(type.longValue());
		payLogService.save(payLog);
		return Result.success(payLog.getSn());
	}

	/**
	 * 赔偿时生成支付记录
	 * @param
	 * @return
	 */
	@RequestMapping(value="/damage_pay_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> damagePayLog(
			@RequestParam(name="orderid",required=true)Long orderid,
			@RequestParam(name="money",required=true)String money
	){
		//这里比较赔偿金额与订单中设置的赔偿金额是否一致
		Order order = orderService.find(orderid);
		System.out.println("*************");
		System.out.println(order.getCompensation());
		if(order.getCompensation().compareTo(new BigDecimal(money))!=0 )
		{
			return Result.error(CodeMsg.UNIQLO_ORDER_DAMAGE_MONEY_ERROR);
		}
		//获取登录用户
		FrontUser frontUser = hostHolder.getUser();
		//测试用户
		//FrontUser frontUser = userService.findUserByName("故与纵QAQ");
		PayLog payLog = new PayLog();
		payLog.setFrontuserid(frontUser);
		payLog.setSn(StringUtil.generateSn("Damage",""));
		payLog.setMoney(NumberUtils.createBigDecimal(money));
		payLog.setPaymentType(PaymentType.Alipay);
		payLog.setPaytype(1);
		payLog.setPayid(orderid);
		payLogService.save(payLog);
		return Result.success(payLog.getSn());
	}
	/**
	 * 根据用户名获取所有的订单信息
	 * @param model
	 * @param user
	 * @return
	 */
	@GetMapping("/list")
	public String getUserList(Model model, User user){
		FrontUser frontUser=hostHolder.getUser();
		System.out.println(frontUser);

		model.addAttribute("frontUser",frontUser);
//		FrontUser frontUser = userService.findUserByName("故与纵QAQ");
//		System.out.println("userid = "+frontUser.getId());
		List<Order> orders = orderService.findAllUserOrderbyUsername(frontUser.getUserName());
//		for(int i=0; i<orders.size(); i++){
//			System.out.println(orders.get(i));
//		}
		model.addAttribute("orders", orders);
		//model.addAttribute("username", "故与纵QAQ");
//		model.addAttribute("pageBean", userService.findList(user, pageBean));
		return "home/order/list";
	}
	/**
	 * 将订单状态修改为已取消
	 * @param order_id
	 * @return
	 */
	@PostMapping("/cancel_order")
	@ResponseBody
	public Result<Boolean> delUserOrder(int order_id){
		System.out.println("order id = "+order_id);
		try{
			Order order = orderService.find((long)order_id);//根据订单编号查找订单
			System.out.println(order);
			// 如果订单的状态是待配送，则可以取消订单
			if(order.getStatus() == Order.status_unsend){
				orderService.updateOrderStatus(order.getSn(), order.getStatus(), Order.status_cancel);
				System.out.println(order);
			}else{
				return Result.error(CodeMsg.UNIQLO_ORDER_CANNOT_CANCEL);
			}
		}catch (Exception e){
			return Result.error(CodeMsg.UNIQLO_ORDER_STATUS_ERROR);
		}
		return Result.success(true);
	}
}
