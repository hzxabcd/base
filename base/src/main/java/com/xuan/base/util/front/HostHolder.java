package com.xuan.base.util.front;


import org.springframework.stereotype.Component;

import com.xuan.base.entity.front.FrontUser;

/**
 * @Auther: 梦致A远
 * @Date: 2021/8/26 11:34
 * @Description:
 */

/**
 * 持有用户信息,用于代替session对象.
 */

@Component
public class HostHolder {

    private ThreadLocal<FrontUser> users = new ThreadLocal<>();

    public void setUser(FrontUser user){
        users.set(user);
    }

    public FrontUser getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}

