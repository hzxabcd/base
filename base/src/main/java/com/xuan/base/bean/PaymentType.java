package com.xuan.base.bean;
/**
 * 支付方式类型枚举
 * @author Administrator
 *
 */
public enum PaymentType {

	Alipay(true,"支付宝"),
	WxPay(false,"微信支付");

	private boolean enable;
	private String name;

	private PaymentType(boolean enable,String name){
		this.enable = enable;
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
