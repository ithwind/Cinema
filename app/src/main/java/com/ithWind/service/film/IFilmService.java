package com.ithWind.service.film;

import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.Seat;
import com.ruoyi.system.domain.dto.FilmInfoDto;

import java.util.List;

public interface IFilmService {
    //返回所有电影列表
    List<FilmInfo> allFilms();
    // 通过id查询电影详情
    FilmInfo getFilmById(int filmId);
    /**
     * 通过影院id获取电影
     */
    List<FilmInfo> getFilmsByCinemaId(int cinemaId);

    /**
     * 获取当前影院和电影的座位信息
     * @param cinemaId 影院Id
     * @param filmId 电影Id
     */
    List<Seat> getSeats(int cinemaId, int filmId);
}
