package com.ruoyi.system.service.swiper;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Swiper;
import com.ruoyi.system.mapper.SwiperMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysSwiperServiceImpl implements ISysSwiperService {
    @Resource
    private SwiperMapper swiperMapper;
    @Resource
    private RedisCache redisCache;
    

    @Override
    public List<Swiper> selectAllSwiper() {
        String key = CacheConstants.SWIPER_ALL;
        if (!redisCache.getCacheList(key).isEmpty()) {
            return redisCache.getCacheList(key);
        }
        List<Swiper> swipers = swiperMapper.selectAllSwiper();
        redisCache.setCacheList(key, swipers);
        return swipers;
    }

    @Override
    public Swiper selectSwiperById(int swiperId) {
        String key = CacheConstants.SWIPER_ID + swiperId;
        if (redisCache.getCacheObject(key) != null) {
            return redisCache.getCacheObject(key);
        }
        Swiper swiper = swiperMapper.selectSwiperById(swiperId);
        redisCache.setCacheObject(key, swiper);
        return swiper;
    }

    @Override
    public int updateSwiper(Swiper swiper) {
        String key = CacheConstants.SWIPER_ID + swiper.getSwiperId();
        if (redisCache.getCacheObject(key) != null) {
            redisCache.deleteObject(key);
        }
        return swiperMapper.updateSwiper(swiper);
    }

    @Override
    public int deleteSwiperById(int swiperId) {
        String key = CacheConstants.SWIPER_ID + swiperId;
        if (redisCache.getCacheObject(key) != null) {
            redisCache.deleteObject(key);
        }
        return swiperMapper.deleteSwiperById(swiperId);
    }

    @Override
    public int addSwiper(Swiper swiper) {
        String key = CacheConstants.SWIPER_ALL;
        int res = swiperMapper.addSwiper(swiper);
        if (res != 0) {
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public List<Swiper> selectSwipersByFilmName(String filmName) {
        String key = CacheConstants.SWIPER_NAME + filmName;
        if (!redisCache.getCacheList(key).isEmpty()) {
            return redisCache.getCacheList(key);
        }
        List<Swiper> swipers = swiperMapper.selectSwipersByFilmName(filmName);
        if (!swipers.isEmpty()) {
            redisCache.setCacheList(key, swipers);
        }
        return swipers;
    }
}
