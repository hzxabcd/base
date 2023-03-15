package com.xuan.base.entity.admin.clothes;

import com.xuan.base.annotion.ValidateEntity;
import com.xuan.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/*
 * 服装类
 * */
@Entity
@Table(name="xuan_clothes")
@EntityListeners(AuditingEntityListener.class)
public class Clothes extends BaseEntity {
    private static final long serialVersionUID = 1L;

//    private static final int CLOTHES_BRAND_UNKNOW = 0; // 未知服装品牌
//    private static final int CLOTHES_BRAND_CHANEL = 1; // CHANEL香奈儿
//    private static final int CLOTHES_BRAND_DIOR = 2;   // Dior迪奥
//    private static final int CLOTHES_BRAND_LV = 3;     // LV路易威登
//    private static final int CLOTHES_BRAND_GUCCI = 4;  // Gucci古驰
//    private static final int CLOTHES_BRAND_ARMANI = 5; // ARMANI阿玛尼
//    private static final int CLOTHES_BRAND_VERSACE = 6; // Versace范思哲
//    private static final int CLOTHES_BRAND_GIVENCHY = 7; // Givenchy纪梵希
//    private static final int CLOTHES_BRAND_PRADA = 8; // Prada普拉达
//    private static final int CLOTHES_BRAND_BURBERRY = 9; // Burberry巴宝莉

    private static final int CLOTHES_TYPE_UNKNOW= 0; // 不知道是常服还是礼服
    private static final int CLOTHES_TYPE_LOW = 1; // 常服
    private static final int CLOTHES_TYPE_HIGH = 2; // 礼服

//    private static final int CLOTHES_CLASS_UNKNOW= 0; // 不知道是什么类别
//    private static final int CLOTHES_CLASS_HOODIE = 1; // 卫衣
//    private static final int CLOTHES_CLASS_SWEATER = 2; // 毛衣
//    private static final int CLOTHES_CLASS_TSHIRT = 3; // T恤
//    private static final int CLOTHES_CLASS_SHIRT= 4; // 衬衫
//    private static final int CLOTHES_CLASS_DRESS = 5; // 连衣裙
//    private static final int CLOTHES_CLASS_SKIRT = 6; // 半身裙
//    private static final int CLOTHES_CLASS_CHEONGSAM = 7; // 旗袍
//    private static final int CLOTHES_CLASS_YURONGFU = 8; // 羽绒服
//    private static final int CLOTHES_CLASS_MIANFU = 9; //棉服
//    private static final int CLOTHES_CLASS_DAYI= 10; // 毛呢大衣
//    private static final int CLOTHES_CLASS_TROUSERS = 11; // 休闲裤
//    private static final int CLOTHES_CLASS_JEANS = 12; // 牛仔裤
//    private static final int CLOTHES_CLASS_SUIT = 13; // 西装

    public static final int CLOTHES_STATUS_UNKNOW= 0; //
    public static final int CLOTHES_STATUS_ONSELL = 1; // 待租赁
    public static final int CLOTHES_STATUS_RENT= 2;   // 已租赁
    public static final int CLOTHES_STATUS_OFFSELL = 3;   // 已下架
    public static final int CLOTHES_STATUS_WARN= 4;   // 服装异常

    private static final int CLOTHES_RECOMMEND_YES = 1; // 推荐
    private static final int CLOTHES_RECOMMEND_NO = 0; // 不推荐

    @ValidateEntity(required=false)
    @Column(name="picture",length=128)
    private String picture;// 服装图片

    @ValidateEntity(required = false)
    @Column(name="price")
    private Long price; // 服装价格

    @ValidateEntity(required = false)
    @Column(name="name")
    private String name;    // 服装名称

    @ValidateEntity(required = false)
    @Column(name="infomation")
    private String info;    // 服装描述信息，如颜色、尺码等

//    @ValidateEntity(required=false)
//    @Column(name="brand",length=2)
//    private Integer brand = CLOTHES_BRAND_UNKNOW;   // 分类：上装、下装、连衣裙……

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @ValidateEntity(required=false)
    @Column(name="type",length=1)
    private Integer type = CLOTHES_TYPE_UNKNOW;

//    @ValidateEntity(required=false)
//    @Column(name="class",length=2)
//    private Integer classify = CLOTHES_CLASS_UNKNOW;   // 分类：上装、下装、连衣裙……

    @ManyToOne
    @JoinColumn(name = "class")
    private ClothClassify classify;

    @ValidateEntity(required=false)
    @Column(name="status",length=1)
    private Integer status = CLOTHES_STATUS_UNKNOW;    // 服装状态

    @ValidateEntity(required=false)
    @Column(name="view_number")
    private Long view_num;

    @ValidateEntity(required=false)
    @Column(name="recommend",length=1)
    private Integer recommend = CLOTHES_RECOMMEND_NO;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

//    public Integer getClassify() {
//        return classify;
//    }
//
//    public void setClassify(Integer classify) {
//        this.classify = classify;
//    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ClothClassify getClassify() {
        return classify;
    }

    public void setClassify(ClothClassify classify) {
        this.classify = classify;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getView_num() {
        return view_num;
    }

    public void setView_num(Long view_num) {
        this.view_num = view_num;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "picture='" + picture + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", brand=" + brand +
                ", type=" + type +
                ", classify=" + classify +
                ", status=" + status +
                ", view_num=" + view_num +
                ", recommend=" + recommend +
                '}';
    }
//    @Override
//    public String toString() {
//        return "Clothes{" +
//                "picture='" + picture + '\'' +
//                ", price=" + price +
//                ", name='" + name + '\'' +
//                ", info='" + info + '\'' +
//                ", type=" + type +
//                ", classify=" + classify +
//                ", status=" + status +
//                ", view_num=" + view_num +
//                ", recommend=" + recommend +
//                '}';
//    }
}
