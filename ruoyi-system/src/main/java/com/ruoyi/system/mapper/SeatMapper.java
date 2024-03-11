package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Locale;

@Mapper
public interface SeatMapper {
    /**
     * 通过seatId查询座位信息
     */
    public List<Seat> selectSeatsById(@Param("list") List<Integer> seatIds);

    /**
     * 返回当前位置信息
     * @param rowNum 行号
     * @param colNum 列号
     * @param cinemaId 影院Id
     * @param filmId 电影Id
     */
    Seat checkSeatIsAvailable(@Param("rowNum") int rowNum,
                                 @Param("colNum") int colNum,
                                 @Param("cinemaId") int cinemaId,
                                 @Param("filmId") int filmId);

    /**
     * 更新座位状态信息
     * @param seatId 座位Id
     */
    int updateSeatStatus(int seatId);

    /**
     * 获取座位
     * @param cinemaId 影院Id
     * @param filmId 电影Id
     */
    List<Seat> getSeats(@Param("cinemaId") int cinemaId, @Param("filmId") int filmId);

}
