package com.ruoyi.system.service.film;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.dto.FilmInfoDto;
import com.ruoyi.system.domain.dto.UpdateFilmDto;
import com.ruoyi.system.mapper.FilmInfoMapper;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.service.order.ISysOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SysFilmServiceImpl implements ISysFilmService {
    @Resource
    private FilmInfoMapper filmInfoMapper;
    @Resource
    private ISysOrderService orderService;
    @Resource
    private RedisCache redisCache;
    @Override
    public List<FilmInfoDto> selectFilmList() {
        String key = CacheConstants.FILM;
        if (!redisCache.getCacheList(key).isEmpty()) {
            return redisCache.getCacheList(key);
        }
        List<FilmInfo> filmInfos = filmInfoMapper.selectFilmList();
        List<FilmInfoDto> filmInfoDtos = new ArrayList<>();
        for (FilmInfo filmInfo : filmInfos) {
            int boxOffice = orderService.selectBoxOfficeByFilmId(filmInfo.getFilmId());
            FilmInfoDto filmInfoDto = new FilmInfoDto(filmInfo.getFilmId(), filmInfo.getFilmName(), filmInfo.getFilmEnName(), filmInfo.getFilmUrl(), filmInfo.getFilmLanguage(),
                    filmInfo.getFilmType(), filmInfo.getFilmTime(), filmInfo.getReleaseLoc(), filmInfo.getReleaseTime(), filmInfo.getFilmDes(), filmInfo.getFilmActor(), boxOffice);
            filmInfoDtos.add(filmInfoDto);
        }
        if (!filmInfoDtos.isEmpty()) {
            redisCache.setCacheList(key, filmInfoDtos);
        }
        return filmInfoDtos;
    }

    @Override
    public FilmInfo selectFilmById(int filmId) {
        return filmInfoMapper.selectFilmById(filmId);
    }

    @Override
    public int updateFilmById(UpdateFilmDto param) {
        String key = CacheConstants.FILM;
        String filmKey = CacheConstants.FILM_BY_NAME + param.getFilmName();
        int result = 0;
        if (checkFilmNotEmpty(param.getFilmId())) {
            result = filmInfoMapper.updateFilmById(param);
            redisCache.deleteObject(key);
            redisCache.deleteObject(filmKey);
        }
        return result;
    }

    @Override
    public int insertFilm(FilmInfo filmInfo) {
        String key = CacheConstants.FILM;
        int res = filmInfoMapper.insertFilm(filmInfo);
        if (res != 0) {
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public int deleteFilms(int filmId) {
        String key = CacheConstants.FILM;
        int res = filmInfoMapper.deleteFilms(filmId);
        if (res != 0) {
            redisCache.deleteObject(key);
        }
        return res;
    }

    @Override
    public List<FilmInfo> selectFilmsByFilmName(String filmName) {
        String key = CacheConstants.FILM_BY_NAME + filmName;
        if (redisCache.getCacheObject(key) != null) {
            return redisCache.getCacheList(key);
        }
        List<FilmInfo> filmInfos = filmInfoMapper.selectFilmsByFilmName(filmName);
        if (!filmInfos.isEmpty()) {
            redisCache.setCacheList(key, filmInfos);
        }
        return filmInfos;
    }


    private boolean checkFilmNotEmpty(int filmId) {
        String key = CacheConstants.FILM + filmId;
        if (redisCache.getCacheObject(key) != null) {
            return true;
        }
        FilmInfo filmInfo = selectFilmById(filmId);
        redisCache.setCacheObject(key, filmInfo, 15, TimeUnit.HOURS);
        return filmInfo != null;
    }
}
