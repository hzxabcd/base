package com.xuan.base.entity.admin;

import com.xuan.base.annotion.ValidateEntity;
import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.front.FrontUser;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/*
* 审核表
* */
@Entity
@Table(name="xuan_check")
@EntityListeners(AuditingEntityListener.class)
public class Check extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private static final int CHECK_TYPE_UNKNOW = 0;   // 服装状态未知
    private static final int CHECK_TYPE_GOOD = 1;   // 服装状态完好
    private static final int CHECK_TYPE_BAD = 2;   // 服装状态损坏

    private static final int CHECK_STATUS_NO = 0;   // 服装未审核
    private static final int CHECK_STATUS_YES = 1;   // 服装已审核

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frontuser_id")
    private FrontUser frontUser;    // 审核人员

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;    // 审核的服装

    @ValidateEntity(required = false)
    @Column(name = "status", length = 1)
    private Integer status = CHECK_STATUS_NO;

    @ValidateEntity(required = false)
    @Column(name = "type", length = 1)
    private Integer type = CHECK_TYPE_UNKNOW;

    public FrontUser getFrontUser() {
        return frontUser;
    }

    public void setFrontUser(FrontUser frontUser) {
        this.frontUser = frontUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Check{" +
                "frontUser=" + frontUser +
                ", order=" + order +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
