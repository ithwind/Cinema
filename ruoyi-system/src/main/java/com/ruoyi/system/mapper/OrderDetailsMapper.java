package com.ruoyi.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OrderDetailsMapper {
    /**
     * 通过orderId查询seatId
     */
    List<Integer> selectSeatIdByOrderId(int orderId);
    /**
     * 插入数据
     */
    int insertOrderDetail(@Param("orderId") int orderId, @Param("seatId") int seatId);
}
