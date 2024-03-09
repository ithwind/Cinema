package com.ruoyi.system.mapper;

import io.lettuce.core.dynamic.annotation.Param;

import java.nio.file.LinkOption;
import java.util.List;

public interface OrderDetailsMapper {
    /**
     * 通过orderId查询seatId
     */
    public List<Integer> selectSeatIdByOrderId(int orderId);
    /**
     * 插入数据
     */
    public int insertOrderDetail(@Param("orderId") int orderId, @Param("SeatId") int seatId);
}
