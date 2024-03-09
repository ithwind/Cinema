package com.ruoyi.system.service.cinema;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.mapper.CinemaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SysCinemaServiceImpl implements ISysCinemaService {
    @Resource
    private RedisCache redisCache;
    @Resource
    private CinemaMapper cinemaMapper;
    @Override
    public List<Cinema> selectCinemaList() {
        String key = CacheConstants.CINEMA_ALL;
        if (!redisCache.getCacheList(key).isEmpty() ) {
            return redisCache.getCacheList(key);
        }
        List<Cinema> cinemas = cinemaMapper.selectCinemaList();
        redisCache.setCacheList(key, cinemas);
        return cinemas;
    }

    @Override
    public Cinema selectCinemaById(int cinemaId) {
        String key = CacheConstants.CINEMA_ID + cinemaId;
        if (redisCache.getCacheObject(key) != null) {
            return redisCache.getCacheObject(key);
        }
        Cinema cinema = cinemaMapper.selectCinemaById(cinemaId);
        if (cinema != null) {
            redisCache.setCacheObject(key, cinema, 15, TimeUnit.HOURS);
        }
        return cinema;
    }

    @Override
    public int updateCinema(Cinema cinema) {
        String key1 = CacheConstants.CINEMA_ID + cinema.getCinemaId();
        String key2 = CacheConstants.CINEMA_ALL;
        int res = cinemaMapper.updateCinema(cinema);
        if (res != 0) {
            redisCache.deleteObject(key1);
            redisCache.deleteObject(key2);
            return res;
        }
        return res;
    }

    @Override
    public int insertCinema(Cinema cinema) {
        int res = cinemaMapper.insertCinema(cinema);
        if (res != 0) {
            String key = CacheConstants.CINEMA_ALL;
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public int deleteCinema(int cinemaId) {
        int res = cinemaMapper.deleteCinema(cinemaId);
        if (res != 0) {
            String key = CacheConstants.CINEMA_ALL;
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public List<Cinema> selectCinemaByCinemaName(String cinemaName) {
        return cinemaMapper.selectCinemaByCinemaName(cinemaName);
    }
}
