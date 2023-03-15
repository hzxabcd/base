package com.xuan.base.dao.front;


import com.xuan.base.entity.front.FrontUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 梦致A远
 * @Date: 2021/8/21 14:54
 * @Description:
 */

@Repository
@Mapper
public interface UserMapper {

    FrontUser selectById(int id);

    FrontUser selectByName(String username);

    FrontUser selectByEmail(String email);

    int insertUser(FrontUser user);

    int updateStatus(int id,int status);

    int updateHeader(int id,String headerUrl);

    int updatePassword(int id,String password);

    int updateType(int id,int type);

    List<FrontUser> selectAllUser();

    int updateExpireTime(int id, Date expireTime);

    List<FrontUser> selectAllExpireUsers(Date date);

    int updateUser(FrontUser user);

}
