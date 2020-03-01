package com.app.service;

import com.app.bean.Order;
import com.app.bean.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    void add(OrderItem c);

    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List<OrderItem> list();

    void fill(List<Order> os);

    void fill(Order o);

    /*根据产品获取销售量*/
    int getSaleCount(int  pid);

    /*根据用户ID查找该用户的所有订单项*/
    List<OrderItem> listByUser(int uid);
}
