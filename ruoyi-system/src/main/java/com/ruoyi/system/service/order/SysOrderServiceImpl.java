package com.ruoyi.system.service.order;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.Seat;
import com.ruoyi.system.domain.dto.OrderDto;
import com.ruoyi.system.mapper.OrderDetailsMapper;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysOrderServiceImpl implements ISysOrderService{
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private SeatMapper seatMapper;
    @Resource
    private OrderDetailsMapper orderDetailsMapper;
    @Resource
    private RedisCache redisCache;
    @Override
    public List<OrderDto> selectOrderDtos() {
        String key = CacheConstants.ORDER_ALL;
        if (!redisCache.getCacheList(key).isEmpty()) {
            return redisCache.getCacheList(key);
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        // 查询数据库中的订单信息(不包括座位信息)
        List<Orders> orders = ordersMapper.selectOrders();
        // 通过订单信息查询座位信息
        for (Orders order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrder(order);
            // 查询seatId
            List<Integer> selectSeatIdByOrderId = orderDetailsMapper.selectSeatIdByOrderId(order.getOrderId());
            // 通过seatId查询座位信息
            List<Seat> seat = seatMapper.selectSeatsById(selectSeatIdByOrderId);
            orderDto.setSeat(seat);
            orderDtos.add(orderDto);
        }
        if (!orderDtos.isEmpty()) {
            redisCache.setCacheList(key, orderDtos);
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> selectOrderDtosByUserName(String userName) {
        String key = CacheConstants.ORDER_NAME + userName;
        if (!redisCache.getCacheList(key).isEmpty()) {
            return redisCache.getCacheList(key);
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Orders> orders = ordersMapper.selectOrdersByUserName(userName);
        for (Orders order : orders) {
            List<Integer> seatId = orderDetailsMapper.selectSeatIdByOrderId(order.getOrderId());
            List<Seat> seats = seatMapper.selectSeatsById(seatId);
            OrderDto orderDto = new OrderDto(order, seats);
            orderDtos.add(orderDto);
        }
        if (!orderDtos.isEmpty()) {
            redisCache.setCacheList(key, orderDtos);
        }
        return orderDtos;
    }

    @Override
    public int selectBoxOfficeByFilmId(int filmId) {
        String key = CacheConstants.FILM_BOX_OFFICE_FILM_ID + filmId;
        if (redisCache.getCacheObject(key) != null) {
            return redisCache.getCacheObject(key);
        }
        int boxOffice = ordersMapper.selectBoxOfficeByFilmId(filmId);
        redisCache.setCacheObject(key, boxOffice);
        return boxOffice;
    }
}
