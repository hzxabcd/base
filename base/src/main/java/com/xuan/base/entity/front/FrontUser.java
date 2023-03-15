package com.xuan.base.entity.front;

import com.xuan.base.annotion.ValidateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: 梦致A远
 * @Date: 2023/1/1 19:51
 * @Description:
 */
@Entity
@Table(name="uniqlo_frontuser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontUser {

    private static final int USER_SEX_UNKONW = 2;//性别未知
    private static final int USER_DEFAULT_TYPE = 0;//普通用户
    private static final int USER_DEFAULT_STATUS = 0;//正常

    @Id
    @ValidateEntity(required=false)
    @Column(name="id",length=20)
    private int id;

    @ValidateEntity(required=false)
    @Column(name="create_time",nullable=false,length=128)
    private Date createTime;//用户头像

    @ValidateEntity(required=false)
    @Column(name="update_time",nullable=false,length=128)
    private Date updateTime;//用户头像

    @ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=18,errorRequiredMsg="用户名不能为空!",errorMinLengthMsg="用户名长度需大于4!",errorMaxLengthMsg="用户名长度不能大于18!")
    @Column(name="user_name",nullable=false,length=18,unique=true)
    private String userName;//用户名

    @ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=32,errorRequiredMsg="密码不能为空！",errorMinLengthMsg="密码长度需大于4!",errorMaxLengthMsg="密码长度不能大于32!")
    @Column(name="password",nullable=false,length=32)
    private String password;//登录密码

    @ValidateEntity(required=false)
    @Column(name="status",length=1)
    private int status = USER_DEFAULT_STATUS;//用户状态,默认可用

    @ValidateEntity(required=false)
    @Column(name="head_pic",length=128)
    private String headPic;//用户头像

    @ValidateEntity(required=false)
    @Column(name="sex",length=1)
    private int sex = USER_SEX_UNKONW;//用户性别

    @ValidateEntity(required=false)
    @Column(name="mobile",length=12)
    private String mobile;//用户手机号d

    @ValidateEntity(required=false)
    @Column(name="email",length=32)
    private String email;//用户邮箱

    @ValidateEntity(required=false)
    @Column(name="type",length=1)
    private int type = USER_DEFAULT_TYPE;//用户状态


    @ValidateEntity(required=false)
    @Column(name="expire_time",length=128)
    private Date expireTime;//会员到期时间

    @ValidateEntity(required=false)
    @Column(name="address",length=128)
    private String address;//用户地址

    public static int getUserSexUnkonw() {
        return USER_SEX_UNKONW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "FrontUser{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", headPic='" + headPic + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", expireTime=" + expireTime +
                ", address='" + address + '\'' +
                '}';
    }
}
