package com.ithWind.service.cinema;

import com.ruoyi.system.domain.Cinema;

import java.util.List;

public interface ICinemaService {
    /**
     * 通过电影id获取所有影院信息
     */
    List<Cinema> getCinemaListByFilmId(int filmId);

    List<Cinema> selectCinemaByCinemaName(String cinemaName);

    List<Cinema> selectAllCinema();
}
