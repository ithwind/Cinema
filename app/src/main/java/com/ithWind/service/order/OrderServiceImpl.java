package com.ithWind.service.order;

import com.ithWind.common.UserContext;
import com.ithWind.domain.dto.UserInfo;
import com.ithWind.domain.req.OrderDto;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.Seat;
import com.ruoyi.system.mapper.CinemaFilmMapper;
import com.ruoyi.system.mapper.OrderDetailsMapper;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.mapper.SeatMapper;
import com.ruoyi.system.service.cinema.ISysCinemaService;
import com.ruoyi.system.service.film.ISysFilmService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.*;

@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private  RedissonClient redissonClient;
    @Resource
    private SeatMapper seatMapper;
    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Resource
    private ISysFilmService filmService;
    @Resource
    private ISysCinemaService cinemaService;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderDetailsMapper orderDetailsMapper;
    // 座位可用
    private static final int AVAILABLE = 0;


    // 座位与并发锁绑定
    private  transient Map<Seat, RLock> seatRLocks;
    @Override
    @Transient
    public int placeOrder(OrderDto orderDto) {
        InitSeatLocks(orderDto.getSeats(), orderDto.getCinemaId(), orderDto.getFilmId());
        int cinemaId = orderDto.getCinemaId();
        int filmId = orderDto.getFilmId();
        try {
            // 对位置进行预定
            for (Seat seat : orderDto.getSeats()) {
                RLock seatLock = seatRLocks.get(seat);
                seatLock.lock();
                Seat seatByColAndRow = checkAndReserveSeat(seat.getRowNum(), seat.getColumnNum(), cinemaId, filmId); // 检查并预留座位
                if (seatByColAndRow.getStatus() != AVAILABLE) {
                    // 如果座位不可用，则释放已获得的所有锁并抛出异常
                    releaseAllLocks();
                    throw new ServiceException("座位已被占用");
                }
                // 下单逻辑
                String usernameOrThrow = UserContext.getCurrentUser()
                        .map(UserInfo::getUserName)
                        .orElseThrow(() -> new IllegalStateException("No current user"));
                int cinemaFilmId = selectCinemaFilmId(cinemaId, filmId);
                FilmInfo filmInfo = filmService.selectFilmById(filmId);
                Cinema cinema = cinemaService.selectCinemaById(cinemaId);
                Orders order = new Orders();
                order.setUserName(usernameOrThrow);
                order.setCinemaFilmId(cinemaFilmId);
                order.setTicketNum(orderDto.getTicketNum());
                order.setTicketPrice(orderDto.getTicketPrice());
                order.setFilmName(filmInfo.getFilmName());
                order.setFilmId(filmId);
                order.setSeat(seatByColAndRow.getSeatId());
                order.setOrderDate(new Date());
                order.setCinemaAddr(cinema.getCinemaAddr());
                order.setCinemaName(cinema.getCinemaName());
                order.setCinemaId(cinemaId);
                int res = ordersMapper.insertOrder(order);
                if (res < 1) {
                    throw new ServiceException("新增订单失败");
                }
                int orderId = order.getOrderId();
                int seatId = seatByColAndRow.getSeatId();
                res = orderDetailsMapper.insertOrderDetail(orderId, seatId);
                if (res < 1) {
                    throw new ServiceException("新增订单详情页失败");
                }
                // 设置座位状态
                res = seatMapper.updateSeatStatus(seatId);
                if (res < 1) {
                    throw new ServiceException("更新座位状态信息失败");
                }
                return 1;
            }
        } finally {
            releaseAllLocks();
        }
        return 0;
    }
    // 初始化锁
    private void InitSeatLocks(List<Seat> seats, int cinemaId, int filmId) {
        seatRLocks = new HashMap<>();
        for (Seat seat : seats) {
            String lockName = "seat:" + seat.getSeatId() + "-" + cinemaId + "-" + filmId;
            RLock seatLock = redissonClient.getLock(lockName);
            seatRLocks.put(seat, seatLock);
        }
    }
    // 释放锁
    private void releaseAllLocks() {
        if (seatRLocks != null) {
            seatRLocks.values().forEach(RLock::unlock);
            seatRLocks.clear();
        }
    }
    // 检查座位是否可用并尝试预留
    private Seat checkAndReserveSeat(int rowNum, int colNum, int cinemaId, int filmId) {
        return seatMapper.checkSeatIsAvailable(rowNum, colNum, cinemaId, filmId);
    }
    // 获取电影和影院关联id
    private int selectCinemaFilmId(int cinemaId, int filmId){
        return cinemaFilmMapper.selectCinemaFilmId(cinemaId, filmId);
    }
}
