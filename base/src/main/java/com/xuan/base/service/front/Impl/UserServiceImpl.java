package com.xuan.base.service.front.Impl;


import com.xuan.base.dao.front.UserMapper;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.entity.front.LoginTicket;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.front.CommunityUtil;
import com.xuan.base.util.front.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 梦致A远
 * @Date: 2021/8/21 15:10
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<FrontUser> findAllUser() {
        List<FrontUser> frontUserList = userMapper.selectAllUser();
        System.out.println(frontUserList);
        return frontUserList;
    }

    @Override
    public FrontUser findUserById(int id) {
        FrontUser user = getCache(id);
        if(user==null){
            user = initCache(id);
        }
        return user;
    }

    @Override
    public Map<String, Object> register(FrontUser user) {

        HashMap<String, Object> map = new HashMap<>();

        //空值处理
        if(user==null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if(StringUtils.isBlank(user.getUserName())){
            map.put("usernameMsg","账号不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空");
            return map;
        }

        if(StringUtils.isBlank(user.getMobile())){
            map.put("mobileMsg","手机号不能为空");
            return map;
        }

        //验证账号
        FrontUser u= userMapper.selectByName(user.getUserName());
        if(u!=null){
            map.put("usernameMsg","该账号已经存在!");
            return map;
        }
        //验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if(u!=null){
            map.put("emailMsg","该邮箱已经被注册");
            return map;
        }

        //注册用户
        user.setPassword(user.getPassword());
        user.setType(0);
        user.setStatus(0);
        user.setHeadPic("20200318/555.jpg");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertUser(user);

        return map;
    }


    @Override
    public Map<String, Object> login(String username, String password, int expiredSeconds) {
        Map<String,Object> map = new HashMap<>();

        //空值处理
        if(StringUtils.isBlank(username)){
            map.put("usernameMsg","账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("passwordMsg","密码不能为空");
            return map;
        }

        //验证账号
        FrontUser user = userMapper.selectByName(username);
        if(user==null){
            map.put("usernameMsg","该账号不存在");
            return map;
        }

//        //验证状态
//        if(user.getStatus()==0){
//            map.put("usernameMsg","该账号未激活");
//            return map;
//        }

        //验证密码
        if(!user.getPassword().equals(password)){
            map.put("passwordMsg","密码不正确！");
            return map;
        }

        // 生成登录凭证(有些类似于session)
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
//        loginTicketMapper.insertLoginTicket(loginTicket); TODO（重构！）
        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        redisTemplate.opsForValue().set(redisKey,loginTicket);

        map.put("ticket", loginTicket.getTicket());
        return map;
    }

    @Override
    public void logout(String ticket) {
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey,loginTicket);
        //redisTemplate.opsForValue().getAndDelete(redisKey);

    }

    @Override
    public LoginTicket findLoginTicket(String ticket) {
//        return loginTicketMapper.selectByTicket(ticket); TODO(重构！)
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        return (LoginTicket) redisTemplate.opsForValue().get(redisKey);
    }

    @Override
    public int updateHeader(int userId, String headerUrl) {
        int rows = userMapper.updateHeader(userId, headerUrl);
        clearCache(userId);
        return rows;
    }

    @Override
    public FrontUser findUserByName(String username) {
        return userMapper.selectByName(username);
    }

    //1.优先从缓存中取值
    private FrontUser getCache(int userId){
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (FrontUser) redisTemplate.opsForValue().get(redisKey);
    }

    //2.取不到时初始化缓存数据
    private FrontUser initCache(int userId){
        FrontUser user =userMapper.selectById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey,user,3600, TimeUnit.SECONDS);
        return user;
    }

    //3.数据变更时清除缓存数据
    private void clearCache(int userId){
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }



    public Map<String, Object> changePassword(FrontUser user, String oldPassword, String newPassword, String confirmPassword) {
        Map<String, Object> map = new HashMap<>();
        // 验证密码
        if (!user.getPassword().equals(oldPassword)) {
            map.put("oldPasswordMsg", "密码不正确!");
            return map;
        }
        if (StringUtils.isBlank(newPassword)) {
            map.put("newPasswordMsg", "密码不能为空!");
            return map;
        }
        if(!newPassword.equals(confirmPassword)){
            map.put("confirmPasswordMsg", "两次输入的密码不一致!");
            return map;
        }
        int id=user.getId();
        if(oldPassword.equals(newPassword)){
            map.put("newPasswordMsg", "旧密码与新密码一致!");
            return map;
        }
        userMapper.updatePassword(id,newPassword);
        clearCache(user.getId()); //清除缓存用户信息
        return map;
    }

    public FrontUser findUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public String updateType(int userId, int type) {
        int i = userMapper.updateType(userId,type);
        if(i==0){
            return "开通会员失败";
        }else{
            clearCache(userId); //清除缓存用户信息
            return "成功";
        }
    }

    @Override
    public boolean updateStatus(int userId, int status) {
        int i = userMapper.updateStatus(userId,status);

        if(i==0){
            return false;
        }else{
            clearCache(userId); //清除缓存用户信息
            return true;
        }
    }

    @Override
    public boolean updateExpireTime(int userId, Date expireTime) {
        int i = userMapper.updateExpireTime(userId, expireTime);
        if(i==0){
            return false;
        }
//        clearCache(userId); //清除缓存用户信息
        return true;
    }

    @Override
    public List<FrontUser> findAllExpireUsers(Date date) {
        return userMapper.selectAllExpireUsers(date);
    }

    @Override
    public boolean updateUser(FrontUser user) {
        int i = userMapper.updateUser(user);
        if(i==0){
            return false;
        }
        clearCache(user.getId()); //清除缓存用户信息
        return true;
    }

}
