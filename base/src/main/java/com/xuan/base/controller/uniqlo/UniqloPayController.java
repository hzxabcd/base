package com.xuan.base.controller.uniqlo;
/**
 * 支付统一处理控制器
 */

import com.xuan.base.bean.PaymentType;
import com.xuan.base.constant.RuntimeConstant;
import com.xuan.base.entity.admin.clothes.Clothes;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.admin.order.PayLog;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.pay.Alipay;
import com.xuan.base.service.admin.ClothesService;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.service.admin.PaisongService;
import com.xuan.base.service.admin.PayLogService;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.StringUtil;
import com.xuan.base.util.front.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;


@RequestMapping("/uniqlo/pay")
@Controller
public class UniqloPayController {

	private Logger log = LoggerFactory.getLogger(UniqloPayController.class);

	@Autowired
	private PayLogService payLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private PaisongService paisongService;
	@Autowired
	private HostHolder hostHolder;
	/**
	 * 前台支付统一入口方法
	 * @param sn
	 * @param model
	 * @return
	 */
	@RequestMapping("/pay_money")
	public String toPay(@RequestParam(name="sn",required=true)String sn,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		PayLog payLog = payLogService.find(sn);
		if(payLog == null){
			model.addAttribute("msg", "支付记录不存在！");
			return RuntimeConstant.RUNTIME_ERROR_VIEW;
		}
		if(payLog.getStatus() == PayLog.status_paid){
			model.addAttribute("msg", "已经支付成功，请勿重复发起支付！");
			return RuntimeConstant.RUNTIME_ERROR_VIEW;
		}
		if(payLog.getMoney().compareTo(new BigDecimal(0)) <= 0){
			model.addAttribute("msg", "支付金额错误！");
			return RuntimeConstant.RUNTIME_ERROR_VIEW;
		}

		//根据支付记录来判断支付方式
		if(payLog.getPaymentType()== PaymentType.Alipay){
			//表示是支付宝支付
			String html = null;
			//判断浏览器是手机还是pc
			if(StringUtil.isMobile(request)){
				//表示是手机浏览器
				html = Alipay.generateWapPayHtml(payLog.getSn(), payLog.getMoney(), "用户支付", "用户支付");
			}else{
				html = Alipay.generatePcPayHtml(payLog.getSn(), payLog.getMoney(), "用户支付", "用户支付");
			}
			model.addAttribute("html", html);
			return "home/pay/alipay_pc";
		}
		//其他支付
		model.addAttribute("msg", "未定义的支付方式！");
		return RuntimeConstant.RUNTIME_ERROR_VIEW;
	}

	/**
	 * 支付宝异步通知接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/alipay_notify")
	@ResponseBody
	public String alipayNotify(HttpServletRequest request) throws UnsupportedEncodingException {
		//检查异步通知的签名是否合法
		log.info("进入支付宝异步通知接口！");
		if(!Alipay.isValid(request)){
			log.error("支付宝签名验证失败！");
			return "fail";
		}
		//表示签名验证成功
		//订单号
		String sn = request.getParameter("out_trade_no");
		//支付金额
		String totalAmount = request.getParameter("total_amount");
		//支付状态
		String status = request.getParameter("trade_status");
		String passback_params = new String(request.getParameter("passback_params").getBytes("ISO-8859-1"),"UTF-8");
		String username = URLDecoder.decode(passback_params,"UTF-8");

		if("TRADE_SUCCESS".equals(status)){
			//表示是支付成功
			//查询支付记录
			PayLog payLog = payLogService.find(sn);
			if(payLog == null){
				log.error("支付记录未找到sn=" + sn);
				return "fail";
			}
			//检查支付记录的状态
			if(payLog.getStatus() != PayLog.status_unpay){
				log.error("支付记录状态错误，status=" + payLog.getStatus());
				return "fail";
			}
			//检查支付的金额是否与支付记录中相符
			if(payLog.getMoney().compareTo(new BigDecimal(totalAmount)) != 0){
				log.error("支付金额错误，支付记录金额=" + payLog.getMoney() + "支付宝通知支付金额=" + totalAmount);
				return "fail";
			}
			//所有一切都符合，将支付记录的订单状态调整为已支付
			payLog.setStatus(PayLog.status_paid);
			payLogService.save(payLog);
			//这里增加会员时间或者取消掉对应的赔偿按钮
			if(payLog.getPaytype() == 0)  //修改会员
			{
				//获取登录用户，
//				FrontUser frontUser = (FrontUser) SessionUtil.get(SessionConstant.SESSION_FRONTUSER_LOGIN_KEY);
//
				FrontUser frontUser = userService.findUserByName(username);
				userService.updateType(frontUser.getId(),payLog.getPayid().intValue());
				userService.updateExpireTime(frontUser.getId(),StringUtil.getAfterMonthDate(new Date(),payLog.getMounth()));


			}else if (payLog.getPaytype()==1)
			{  //赔偿订单为已完成修改订单为已完成
				Order order = orderService.find(payLog.getPayid());
				orderService.updateOrderStatus(order.getSn(), Order.status_abnormal,Order.status_finish);

				//衣服设置为已下架
				Clothes clothes =order.getClothes();
				clothes.setType(Clothes.CLOTHES_STATUS_OFFSELL);
				clothesService.save(clothes);
				 //派送单设置为已完成
				paisongService.updateFinish(payLog.getPayid().intValue());
			}
		}
		return "success";
	}
}
