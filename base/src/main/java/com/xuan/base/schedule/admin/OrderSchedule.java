package com.xuan.base.schedule.admin;

import com.xuan.base.entity.admin.order.Order;
import com.xuan.base.entity.front.FrontUser;
import com.xuan.base.service.admin.OrderService;
import com.xuan.base.service.front.UserService;
import com.xuan.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * 检查订单是否过期 以及会员是否到期定时器
 * @author Administrator
 *
 */
@Configuration
@EnableScheduling
public class OrderSchedule {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(OrderSchedule.class);


    //@Scheduled(initialDelay=10000,fixedRate=5000)
    @Scheduled(fixedDelay=10000)//每隔5秒检查一次是否超时
    public void checkOrder(){
        log.info("开始执行订单以及会员状态检查任务！");
        //设置超时未归还订单为异常订单   并冻结用户
        List<String> snOrderList = orderService.findTimeOutList(new Date());
        for(String sn : snOrderList){ //
            orderService.updateOrderStatus(sn, Order.status_unback,Order.status_abnormal);
            orderService.updateRemark(sn,"您的订单已超时未寄回，请及时寄回！");
            userService.updateStatus(orderService.find(sn).getFrontuserid().getId(),1);
        }
        //设置用户状态为
        List<FrontUser> snuserList = userService.findAllExpireUsers(new Date());
        for (FrontUser user :snuserList)
        {
            userService.updateType(user.getId(),0);
        }
        log.info("成功将【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】时间内超时的【" + snOrderList.size() + "】个订单设置为异常订单!");
        log.info("成功将【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】时间内超时的【" + snuserList.size() + "】个会员用户改为普通用户!");


    }
}
