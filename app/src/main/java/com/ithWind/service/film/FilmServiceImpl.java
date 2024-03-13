package com.ithWind.service.film;

import com.ithWind.service.cinema.ICinemaService;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.Seat;
import com.ruoyi.system.domain.dto.FilmInfoDto;
import com.ruoyi.system.mapper.CinemaFilmMapper;
import com.ruoyi.system.mapper.FilmInfoMapper;
import com.ruoyi.system.mapper.SeatMapper;
import com.ruoyi.system.service.cinema.ISysCinemaService;
import com.ruoyi.system.service.film.ISysFilmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements IFilmService{
    @Resource
    private ISysFilmService filmService;
    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Resource
    private SeatMapper seatMapper;
    @Override
    public List<FilmInfo> allFilms() {
        List<FilmInfoDto> filmInfoDtos = filmService.selectFilmList();
        List<FilmInfo> filmInfos = new ArrayList<>();
        for (FilmInfoDto filmInfoDto : filmInfoDtos) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmActor(filmInfoDto.getFilmActor());
            filmInfo.setFilmDes(filmInfoDto.getFilmDes());
            filmInfo.setFilmId(filmInfoDto.getFilmId());
            filmInfo.setFilmLanguage(filmInfoDto.getFilmLanguage());
            filmInfo.setFilmName(filmInfoDto.getFilmName());
            filmInfo.setFilmTime(filmInfoDto.getFilmTime());
            filmInfo.setFilmUrl(filmInfoDto.getFilmUrl());
            filmInfo.setFilmEnName(filmInfoDto.getFilmEnName());
            filmInfo.setReleaseLoc(filmInfoDto.getReleaseLoc());
            filmInfo.setReleaseTime(filmInfoDto.getReleaseTime());
            filmInfo.setFilmType(filmInfoDto.getFilmType());
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }

    @Override
    public FilmInfo getFilmById(int filmId) {
        return filmService.selectFilmById(filmId);
    }

    @Override
    public List<FilmInfo> getFilmsByCinemaId(int cinemaId) {
        List<Integer> filmIds = cinemaFilmMapper.selectFilmInfosByCinemaId(cinemaId);
        List<FilmInfo> filmInfos = new ArrayList<>();
        for (int filmId : filmIds) {
            FilmInfo filmInfo = filmService.selectFilmById(filmId);
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }

    @Override
    public List<Seat> getSeats(int cinemaId, int filmId) {
        return seatMapper.getSeats(cinemaId, filmId);
    }

    @Override
    public List<FilmInfo> selectFilmByFilmName(String filmName) {
        return filmService.selectFilmsByFilmName(filmName);
    }
}
