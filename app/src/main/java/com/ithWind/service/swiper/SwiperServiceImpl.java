package com.ithWind.service.swiper;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Swiper;
import com.ruoyi.system.service.swiper.ISysSwiperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SwiperServiceImpl implements ISwiperService{
    private String allSwiperKey = "allSwipers:";
    @Resource
    private RedisCache redisCache;
    @Resource
    private ISysSwiperService swiperService;
    @Override
    public List<Swiper> selectAllSwipers() {
        List<Swiper> swipers = swiperService.selectAllSwiper();
        if (!swipers.isEmpty()) {
            redisCache.setCacheList(allSwiperKey, swipers);
        }
        return swipers;
    }
}
