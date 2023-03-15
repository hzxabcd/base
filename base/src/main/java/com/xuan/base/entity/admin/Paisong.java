package com.xuan.base.entity.admin;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.util.Date;

/*
 * 派送信息实体类
 * */
@Entity
@Table(name="paisong_info")
public class Paisong {
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //商品名
    @Column(name="name",nullable = false,length = 30)
    private String name;

    //订单id

    @Column(name="dingdan_id",nullable = false)
    private int  dingdan_id;

    //创建时间和更新时间
    @Column(name="create_time",nullable = false,length = 30)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    @Column(name="update_time",length = 30)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;

    //订单编号和物流单号
    @Column(name="dingdan_no",nullable = false,length = 30)
    private String dingdan_no;

    @Column(name="wuliu_no",nullable = false,length = 30)
    private String wuliu_no;

    //派送状态---1：用户待收货   2：待寄回 3.已寄回  4：待审核  5：已完成
    @Column(name="statue",nullable = false)
    private int statue;

    public Paisong(String name, int dingdan_id, Date create_time, Date update_time, String dingdan_no, String wuliu_no, int statue) {
        this.name = name;
        this.dingdan_id = dingdan_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.dingdan_no = dingdan_no;
        this.wuliu_no = wuliu_no;
        this.statue = statue;
    }

    public Paisong(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDingdan_id() {
        return dingdan_id;
    }

    public void setDingdan_id(int dingdan_id) {
        this.dingdan_id = dingdan_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDingdan_no() {
        return dingdan_no;
    }

    public void setDingdan_no(String dingdan_no) {
        this.dingdan_no = dingdan_no;
    }

    public String getWuliu_no() {
        return wuliu_no;
    }

    public void setWuliu_no(String wuliu_no) {
        this.wuliu_no = wuliu_no;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "Paisong{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dingdan_id=" + dingdan_id +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", dingdan_no='" + dingdan_no + '\'' +
                ", wuliu_no='" + wuliu_no + '\'' +
                ", statue=" + statue +
                '}';
    }
}
