package com.xuan.base.bean;
/**
 * 会员类型枚举
 * @author Administrator
 *
 */
public enum MemberType {

	Regular_Member ("普通会员",299),
	Super_Member ("超级会员",599),
	;

	private String name;

	private Integer money;

	private MemberType(String name, Integer money){
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
}
