package com.xuan.base.entity.admin.clothes;

import com.xuan.base.annotion.ValidateEntity;
import com.xuan.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name="xuan_clothes_brand")
@EntityListeners(AuditingEntityListener.class)
public class Brand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*
    private static final int CLOTHES_BRAND_UNKNOW = 0; // 未知服装品牌
    private static final int CLOTHES_BRAND_CHANEL = 1; // CHANEL香奈儿
    private static final int CLOTHES_BRAND_DIOR = 2;   // Dior迪奥
    private static final int CLOTHES_BRAND_LV = 3;     // LV路易威登
    private static final int CLOTHES_BRAND_GUCCI = 4;  // Gucci古驰
    private static final int CLOTHES_BRAND_ARMANI = 5; // ARMANI阿玛尼
    private static final int CLOTHES_BRAND_VERSACE = 6; // Versace范思哲
    private static final int CLOTHES_BRAND_GIVENCHY = 7; // Givenchy纪梵希
    private static final int CLOTHES_BRAND_PRADA = 8; // Prada普拉达
    private static final int CLOTHES_BRAND_BURBERRY = 9; // Burberry巴宝莉
    * */

    @ValidateEntity(required=false)
    @Column(name="name",length=128)
    private String name;

    @ValidateEntity(required = false)
    @Column(name="information",length=128)
    private String info;    // 品牌描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
