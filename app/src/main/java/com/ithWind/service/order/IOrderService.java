package com.ithWind.service.order;

import com.ithWind.domain.req.OrderDto;

/**
 * 用户订单服务
 */
public interface IOrderService {
    /**
     * 下单服务
     */
    public int placeOrder(OrderDto orderDto);
}
