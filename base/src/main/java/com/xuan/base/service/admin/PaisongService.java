package com.xuan.base.service.admin;

import com.xuan.base.mapper.PaisongMapper;
import com.xuan.base.entity.admin.Paisong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaisongService {
    @Resource
    PaisongMapper paisongMapper;

    /**
     * 1.查询全部派送单
     * @return
     */
    public List<Paisong> selectAll(){

        return paisongMapper.selectAll() ;
    }

    /**
     * 2.添加一条派送单
     * @param paisong
     * @return
     */
    public int save(Paisong paisong){
        return paisongMapper.save(paisong);
    }

    /**
     * 3.修改派送单状态+1
     * @param id
     * @return
     */
    public int update(int id){
        return paisongMapper.update(id);
    }

    /**
     * 4.根据id查找派送单
     * @param id
     * @return
     */
    public Paisong getPaisong(int id){
        return paisongMapper.getpaisong(id);
    }

    /**
     * 5.根据id逻辑删除
     * @param id
     * @return
     */
    public int delete(int id){
        return paisongMapper.delete(id);
    }

    /**
     * 6.获取订单状态
     * @param id
     * @return
     */
    public int getStatue(int id){
        return paisongMapper.getStatue(id);
    }

    /**
     * 7.通过name模糊查询
     * @param withname
     * @return
     */
    public List<Paisong> getByname(String withname){
        return paisongMapper.getByname(withname);
    }

    /**
     * 8.通过用户id查询派送单信息
     * @param userid
     * @return
     */
    public List<Paisong> getByUserId(int userid){

        return paisongMapper.getByUserId(userid);
    }

    /**
     * 9.查找所有待审核订单
     * @return
     */
    public List<Paisong> getByCheck(){
        return paisongMapper.getByCheck();
    }

    /**
     * 10.根据订单id找派送单
     * @param orderid
     * @return
     */
    public Paisong getByOrderId(int orderid){
        return paisongMapper.getByOrderId(orderid);
    }

    /**
     * 11.根据订单id把派送单状态直接更新到已完成
     * @param orderid
     * @return
     */
    public void updateFinish(int orderid){
         paisongMapper.updateFinish(orderid);
    }
}
