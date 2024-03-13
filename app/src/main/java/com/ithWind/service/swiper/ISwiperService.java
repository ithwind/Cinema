package com.ithWind.service.swiper;

import com.ruoyi.system.domain.Swiper;

import java.util.List;

/**
 * 轮播图服务
 */
public interface ISwiperService {
    /**
     * 查询所有轮播图
     */
    List<Swiper> selectAllSwipers();
}
