package com.ithWind.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.Seat;

import java.util.List;

public class OrderVo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 订单
     */
    private Orders orders;
    /**
     * 电影名
     */
    private String filmName;
    /**
     * 座位详情
     */
    List<Seat> seats;

    public OrderVo() {
    }

    public OrderVo(Orders orders, String filmName, List<Seat> seats) {
        this.orders = orders;
        this.filmName = filmName;
        this.seats = seats;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
