package com.ruoyi.system.service.cinemaFilm;

import com.ruoyi.system.domain.CinemaFilm;
import com.ruoyi.system.domain.dto.CinemaFilmDto;

import java.util.List;

public interface ISysCinemaFilmService {
    /**
     * 查询所有的电影和影院的关联数据
     */
    public List<CinemaFilmDto> selectAllCinemaFilms();
    /**
     * 通过id查询关联数据
     */
    public CinemaFilm selectCinemaFilmById(int cinemaFilmId);
    /**
     * 修改关联信息
     */
    public int updateCinemaFilm(CinemaFilm cinemaFilm);
    /**
     * 删除关联信息
     */
    public int deleteCinemaFilm(int cinemaFilmId);
    /**
     * 新增关联信息
     */
    public int insertCinemaFilm(CinemaFilm cinemaFilm);
    /**
     * 根据filmId查询信息
     */
    public List<CinemaFilm> selectCinemaFilmsById(int filmId);
}
