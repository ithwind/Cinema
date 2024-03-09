package com.ruoyi.system.service.order;

import com.ruoyi.system.domain.dto.OrderDto;

import java.util.List;

public interface ISysOrderService {
    /**
     * 返回所有订单列表
     */
    List<OrderDto> selectOrderDtos();
    /**
     * 通过用户名查询订单信息
     */
    List<OrderDto> selectOrderDtosByUserName(String userName);
    /**
     * 查询指定电影id的订单总价
     */
    public int selectBoxOfficeByFilmId(int filmId);
}
