package com.app.service;

import com.app.bean.Order;
import com.app.bean.OrderItem;

import java.util.List;

public interface OrderService {
    /**
     * 订单的状态
     */
    String waitPay="waitPay";
    /*等待支付*/
    String waitDelivery="waitDelivery";
    /*等待确认*/
    String waitConfirm="waitConfirm";
    /*等待评价*/
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);

    void delete(int id);

    void  update(Order order);

    Order get(int id);

    List<Order> list();


    float add(Order c,List<OrderItem> ois);

    List list(int uid, String excludedStatus);
}
