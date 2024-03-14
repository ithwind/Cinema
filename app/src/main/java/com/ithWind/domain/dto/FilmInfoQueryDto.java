package com.ithWind.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.CinemaFilm;
import com.ruoyi.system.domain.FilmInfo;

/**
 * 查询电影
 */
public class FilmInfoQueryDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 电影详情
     */
    private FilmInfo filmInfo;
    /**
     * 电影与影院关联
     */
    private CinemaFilm cinemaFilm;

    public FilmInfoQueryDto(FilmInfo filmInfo, CinemaFilm cinemaFilm) {
        this.filmInfo = filmInfo;
        this.cinemaFilm = cinemaFilm;
    }

    public FilmInfoQueryDto() {
    }

    public FilmInfo getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(FilmInfo filmInfo) {
        this.filmInfo = filmInfo;
    }

    public CinemaFilm getCinemaFilm() {
        return cinemaFilm;
    }

    public void setCinemaFilm(CinemaFilm cinemaFilm) {
        this.cinemaFilm = cinemaFilm;
    }
}
