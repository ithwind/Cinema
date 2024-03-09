package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.Orders;

import java.util.List;

public interface OrdersMapper {
    /**
     * 查询所有order
     */
    public List<Orders> selectOrders();
    /**
     * 通过用户名查询订单
     */
    public List<Orders> selectOrdersByUserName(String userName);
    /**
     * 查询指定电影id的订单总价
     */
    public int selectBoxOfficeByFilmId(int filmId);
    /**
     * 新增订单
     */
    public int insertOrder(Orders order);
}
