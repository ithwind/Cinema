package com.ruoyi.system.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.Seat;
import com.ruoyi.system.mapper.SeatMapper;

import java.util.List;

/**
 * 订单查询DTO
 */
public class OrderDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 订单类
     */
    private Orders order;
    /**
     * 座位类
     */
    private List<Seat> seat;

    public OrderDto() {
    }

    public OrderDto(Orders order, List<Seat> seat) {
        this.order = order;
        this.seat = seat;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<Seat> getSeat() {
        return seat;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }
}
