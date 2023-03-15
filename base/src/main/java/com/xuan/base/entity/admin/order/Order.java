package com.xuan.base.entity.admin.order;


import com.xuan.base.annotion.ValidateEntity;
import com.xuan.base.entity.admin.BaseEntity;
import com.xuan.base.entity.admin.clothes.Clothes;
import com.xuan.base.entity.front.FrontUser;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体
 * @author Administrator
 *
 */
@Entity
@Table(name="uniqlo_order")
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public final static int status_unsend = 0;//待配送
    public final static int status_delivery = 1;//配送中
    public final static int status_unback = 2;//待寄回
    public final static int status_onway = 3;//寄回中
    public final static int status_check = 4;//待审核
    public final static int status_finish = 5;//已完成
    public final static int status_abnormal = -1;//异常订单
    public final static int status_cancel = -2;//已取消

    @ManyToOne
    @JoinColumn(name="frontuser_id",nullable=false)
    private FrontUser frontuserid ;//所属前台用户

    @ManyToOne
    @JoinColumn(name="clothe_id",nullable=false)
    private Clothes clothes;//所属服装

    @Column(name="sn",nullable=false,unique=true,length=32)
    private String sn;//订单编号，唯一

    @Column(name="status",nullable=false,length=1)
    private Integer status = status_unsend;//订单状态

    @Column(name="days",nullable=false,length=2)
    private  Integer days;//服装租赁天数

    @Column(name="address",nullable=false,length=64)
    private String address ;//订单配送地址

    @Column(name="phone",nullable=false,length=13)
    private String phone ;//订单配送手机号

    @Column(name="remark",length=128)
    private String remark ;//订单备注

    @ValidateEntity(required=false)
    @Column(name="compensation",length=19)
    private BigDecimal compensation; //设定的赔偿金额

    @ValidateEntity(required=false)
    @Column(name="expire_time")
    private Date expireTime;  //订单到期时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getCompensation() {
        return compensation;
    }

    public void setCompensation(BigDecimal compensation) {
        this.compensation = compensation;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public FrontUser getFrontuserid() {
        return frontuserid;
    }

    public void setFrontuserid(FrontUser frontuserid) {
        this.frontuserid = frontuserid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStatus() {
        return status;
    }



    public Integer getDays() {
        return days;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "frontuserid=" + frontuserid +
                ", clothes=" + clothes +
                ", sn='" + sn + '\'' +
                ", status=" + status +
                ", days=" + days +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", compensation=" + compensation +
                ", expireTime=" + expireTime +
                '}';
    }
}
