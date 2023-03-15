package com.xuan.base.entity.admin.order;

import com.xuan.base.bean.PaymentType;
import com.xuan.base.entity.admin.BaseEntity;
import com.xuan.base.entity.front.FrontUser;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 支付记录实体
 * @author Administrator
 *
 */
@Entity
@Table(name="uniqlo_pay_log")
@EntityListeners(AuditingEntityListener.class)
public class PayLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public final static int status_unpay = 0;//待支付

	public final static int status_paid = 1;//已支付

	@Column(name="sn",nullable=false,unique=true,length=32)
	private String sn;//支付编号，唯一

	@ManyToOne
	@JoinColumn(name=" frontuser_id",nullable=false)
	private FrontUser frontuserid ;//所属前台用户

	@Column(name="status",nullable=false,length=1)
	private Integer status = status_unpay;//支付记录状态

	@Column(name="money",nullable=false,length=12)
	private BigDecimal money;//支付金额

	@Column(name="payment_type",nullable=false,length=5)
	private PaymentType paymentType;//支付方式

	@Column(name="pay_type",nullable=false,length=1)
	private Integer paytype; //支付类型  充值会员（0） / 赔偿（1）

	@Column(name="pay_id",length=11)
	private Long payid; // 订单id 或者 充会员的类型

	@Column(name="mounth",length=2)
	private int mounth; //假如是充会员 那就是存的月份

	public Long getPayid() {
		return payid;
	}

	public void setPayid(Long payid) {
		this.payid = payid;
	}

	public int getMounth() {
		return mounth;
	}

	public void setMounth(int mounth) {
		this.mounth = mounth;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public FrontUser getFrontuserid() {
		return frontuserid;
	}

	public void setFrontuserid(FrontUser frontuserid) {
		this.frontuserid = frontuserid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "PayLog{" +
				"sn='" + sn + '\'' +
				", frontuserid=" + frontuserid +
				", status=" + status +
				", money=" + money +
				", paymentType=" + paymentType +
				", paytype=" + paytype +
				", payid=" + payid +
				", mounth=" + mounth +
				'}';
	}
}
