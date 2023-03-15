package com.xuan.base.entity.admin.clothes;

import com.xuan.base.annotion.ValidateEntity;
import com.xuan.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name="xuan_clothes_class")
@EntityListeners(AuditingEntityListener.class)
public class ClothClassify extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /*
    private static final int CLOTHES_CLASS_UNKNOW= 0; // 不知道是什么类别
    private static final int CLOTHES_CLASS_HOODIE = 1; // 卫衣
    private static final int CLOTHES_CLASS_SWEATER = 2; // 毛衣
    private static final int CLOTHES_CLASS_TSHIRT = 3; // T恤
    private static final int CLOTHES_CLASS_SHIRT= 4; // 衬衫
    private static final int CLOTHES_CLASS_DRESS = 5; // 连衣裙
    private static final int CLOTHES_CLASS_SKIRT = 6; // 半身裙
    private static final int CLOTHES_CLASS_CHEONGSAM = 7; // 旗袍
    private static final int CLOTHES_CLASS_YURONGFU = 8; // 羽绒服
    private static final int CLOTHES_CLASS_MIANFU = 9; //棉服
    private static final int CLOTHES_CLASS_DAYI= 10; // 毛呢大衣
    private static final int CLOTHES_CLASS_TROUSERS = 11; // 休闲裤
    private static final int CLOTHES_CLASS_JEANS = 12; // 牛仔裤
    private static final int CLOTHES_CLASS_SUIT = 13; // 西装
    * */
    @ValidateEntity(required=false)
    @Column(name="name",length=128)
    private String name;

    private static final int CLASS_SEX_MAN = 2;//性别男

    private static final int CLASS_SEX_WOMAN = 1;//性别女

    private static final int CLASS_SEX_UNKONW = 0;//性别未知

    @ValidateEntity(required=false)
    @Column(name="sex")
    private int sex = CLASS_SEX_UNKONW;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
