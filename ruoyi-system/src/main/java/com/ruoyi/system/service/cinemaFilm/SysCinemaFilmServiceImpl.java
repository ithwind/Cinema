package com.ruoyi.system.service.cinemaFilm;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.CinemaFilm;
import com.ruoyi.system.domain.dto.CinemaFilmDto;
import com.ruoyi.system.mapper.CinemaFilmMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysCinemaFilmServiceImpl implements ISysCinemaFilmService {
    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Resource
    private RedisCache redisCache;


    int[][] seat = {
            {1, 0, 1, 0, 0, 1, 1, 0, 1, 0},
            {1, 1, 0, 0, 1, 1, 0, 0, 1, 1},
            {0, 0, 1, 1, 0, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
            {0, 1, 1, 0, 1, 1, 0, 1, 0, 1}
    };

    // todo 通过订单数据判定位置占用情况
    @Override
    public List<CinemaFilmDto> selectAllCinemaFilms() {
        List<CinemaFilm> cinemaFilms = cinemaFilmMapper.selectAllCinemaFilms();
        List<CinemaFilmDto> cinemaFilmDtos = new ArrayList<>();
        for (CinemaFilm cinemaFilm : cinemaFilms) {
            CinemaFilmDto cinemaFilmDto = new CinemaFilmDto(cinemaFilm);
            cinemaFilmDto.setSeat(seat);
            cinemaFilmDtos.add(cinemaFilmDto);
        }

        return cinemaFilmDtos;
    }

    @Override
    public CinemaFilm selectCinemaFilmById(int cinemaFilmId) {
        String key = CacheConstants.CINEMA_FILM_BY_ID + cinemaFilmId;
        if (redisCache.getCacheObject(key) != null) {
            return redisCache.getCacheObject(key);
        }
        CinemaFilm cinemaFilm = cinemaFilmMapper.selectCinemaFilmById(cinemaFilmId);
        redisCache.setCacheObject(key, cinemaFilm);
        return cinemaFilm;
    }

    @Override
    public int updateCinemaFilm(CinemaFilm cinemaFilm) {
        String key = CacheConstants.CINEMA_FILM_BY_ID + cinemaFilm.getCinemaFilmId();
        int res = cinemaFilmMapper.updateCinemaFilm(cinemaFilm);
        if (res != 0) {
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public int deleteCinemaFilm(int cinemaFilmId) {
        String keyCinemaFilmById = CacheConstants.CINEMA_FILM_BY_ID + cinemaFilmId;
        if (redisCache.getCacheObject(keyCinemaFilmById) != null) {
            redisCache.deleteObject(keyCinemaFilmById);
        }
        return cinemaFilmMapper.deleteCinemaFilm(cinemaFilmId);
    }

    @Override
    public int insertCinemaFilm(CinemaFilm cinemaFilm) {
        return cinemaFilmMapper.insertCinemaFilm(cinemaFilm);
    }

    @Override
    public List<CinemaFilm> selectCinemaFilmsById(int filmId) {
        return cinemaFilmMapper.selectCinemaFilmsById(filmId);
    }
}
