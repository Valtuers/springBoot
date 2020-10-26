package com.lmc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private long orderId;

    private String type;

    public Order() {
    }

    public Order(long orderId, String type) {
        this.orderId = orderId;
        this.type = type;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<Order> getOrderList(){
        return new ArrayList<Order>(){{
            add(new Order(111L,"创建订单"));
            add(new Order(222L,"创建订单"));
            add(new Order(111L,"支付订单"));
            add(new Order(333L,"创建订单"));
            add(new Order(222L,"支付订单"));
            add(new Order(111L,"完成订单"));
            add(new Order(333L,"支付订单"));
            add(new Order(333L,"完成订单"));
            add(new Order(222L,"完成订单"));
        }};
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", type='" + type + '\'' +
                '}';
    }
}
