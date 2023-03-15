package com.xuan.base.mapper;

import com.xuan.base.entity.admin.Paisong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisongMapper {

    //1.查询所有派送单信息
    List<Paisong> selectAll();

    //2.新增一条派送单信息
    int save(Paisong paisong);

    //3.修改派送单状态
    int update(int id);

    //4.根据派送单id查找派送单
    Paisong getpaisong(int id);

    //5.逻辑删除
    int delete(int id);

    //6.获取订单状态
    int getStatue(int id);

    //7.根据商品名查找派送单
    List<Paisong> getByname(String withname);

    //8.根据用户id找对应的全部派送单
    List<Paisong> getByUserId(int userid);

    //9.查找所有待审核的派送单
    List<Paisong> getByCheck();

    //10.根据订单id找派送单
    Paisong getByOrderId(int orderid);

    //11.根据订单单id把派送单状态直接更新到已完成

    void updateFinish(int orderid);

}
