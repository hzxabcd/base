package com.xuan.base.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.xuan.base.config.uniqlo.AlipayConfig;
import com.xuan.base.constant.SessionConstant;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 支付宝支付
 * @author Administrator
 *
 */
public class Alipay {
	/**
	 * 支付宝pc支付
	 * @param sn
	 * @param money
	 * @param title
	 * @param content
	 * @return
	 */
	public static String generatePcPayHtml(String sn,BigDecimal money,String title,String content) throws UnsupportedEncodingException {

		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		FrontUser frontUser = (FrontUser) SessionUtil.get(SessionConstant.SESSION_FRONTUSER_LOGIN_KEY);

		String  username = URLEncoder.encode(frontUser.getUserName(),"UTF-8");

		Map<String, String> param = new HashMap<String, String>();
		//商户订单号，商户网站订单系统中唯一订单号，必填
		param.put("out_trade_no", sn);
		//付款金额，必填
		param.put("total_amount", money.toString());
		//订单名称，必填
		param.put("subject", title);
		//商品描述，可空
		param.put("body", content);
		param.put("passback_params",username);
		param.put("product_code", "FAST_INSTANT_TRADE_PAY");
		alipayRequest.setBizContent(JSONObject.toJSONString(param));
		String html = null;
		//请求
		try {
			html = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * 支付宝手机wap支付
	 * @param sn
	 * @param money
	 * @param title
	 * @param content
	 * @return
	 */
	public static String generateWapPayHtml(String sn,BigDecimal money,String title,String content) throws UnsupportedEncodingException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//设置请求参数
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		FrontUser frontUser = (FrontUser) SessionUtil.get(SessionConstant.SESSION_FRONTUSER_LOGIN_KEY);

		String  username = URLEncoder.encode(frontUser.getUserName(),"UTF-8");

		Map<String, String> param = new HashMap<String, String>();
		//商户订单号，商户网站订单系统中唯一订单号，必填
		param.put("out_trade_no", sn);
		//付款金额，必填
		param.put("total_amount", money.toString());
		//订单名称，必填
		param.put("subject", title);
		//商品描述，可空
		param.put("body", content);
		param.put("passback_params",username);
		param.put("product_code", "FAST_INSTANT_TRADE_PAY");
		alipayRequest.setBizContent(JSONObject.toJSONString(param));
		String html = null;
		try {
			//请求
			html = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
	/**
	 * 验证支付宝异步通知请求的签名是否正确
	 * @param request
	 * @return
	 */
	public static boolean isValid(HttpServletRequest request){
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		System.out.println(JSONObject.toJSONString(params));
		try {
			return AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //调用SDK验证签名
		return false;
	}
}
