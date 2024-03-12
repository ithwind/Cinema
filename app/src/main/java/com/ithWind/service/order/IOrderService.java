package com.ithWind.service.order;

import com.ithWind.domain.OrderVo;
import com.ithWind.domain.req.OrderDto;

import java.util.List;

/**
 * 用户订单服务
 */
public interface IOrderService {
    /**
     * 下单服务
     */
    int placeOrder(OrderDto orderDto);
    /**
     * 查询历史订单
     */
    List<OrderVo> selectAllOrders();
}
