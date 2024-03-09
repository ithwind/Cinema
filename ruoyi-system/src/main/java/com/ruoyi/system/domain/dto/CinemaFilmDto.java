package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.CinemaFilm;

public class CinemaFilmDto extends CinemaFilm {
    /**
     * 座位信息 0-已销售 1-未销售
     */
    private int[][] seat;


    public CinemaFilmDto(CinemaFilm cinemaFilm) {
        super(cinemaFilm.getCinemaFilmId(), cinemaFilm.getFilmId(), cinemaFilm.getCinemaId(),
                cinemaFilm.getFilmPrice(), cinemaFilm.getDiscountPrice(), cinemaFilm.getCinemaHall(),
                cinemaFilm.getPlayDate(), cinemaFilm.getBeginTime(), cinemaFilm.getEndTime());
        // 复制其他属性到 CinemaFilmDto
    }

    public int[][] getSeat() {
        return seat;
    }

    public void setSeat(int[][] seat) {
        this.seat = seat;
    }
}
