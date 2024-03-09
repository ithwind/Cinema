package com.ruoyi.system.domain;

/**
 * 订单详情
 */
public class OrderDetails {
    /**
     * 订单详情id
     */
    private int orderDetailId;
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 座位id
     */
    private int seatId;

    public OrderDetails(int orderDetailId, int orderId, int seatId) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.seatId = seatId;
    }

    public OrderDetails() {
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
}
