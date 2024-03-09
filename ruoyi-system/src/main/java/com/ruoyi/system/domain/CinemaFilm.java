package com.ruoyi.system.domain;

/**
 * 影院和电影的关联类
 */
public class CinemaFilm {
    /**
     * 绑定id
     */
    private int cinemaFilmId;
    /**
     * 电影id
     */
    private int filmId;
    /**
     * 影院id
     */
    private int cinemaId;
    /**
     * 价格
     */
    private int filmPrice;
    /**
     * 折扣价格
     */
    private int discountPrice;
    /**
     * 影厅
     */
    private String cinemaHall;
    /**
     * 播放日期
     */
     private String playDate;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    public CinemaFilm() {
    }

    public CinemaFilm(int cinemaFilmId, int filmId, int cinemaId, int filmPrice, int discountPrice, String cinemaHall, String playDate, String beginTime, String endTime) {
        this.cinemaFilmId = cinemaFilmId;
        this.filmId = filmId;
        this.cinemaId = cinemaId;
        this.filmPrice = filmPrice;
        this.discountPrice = discountPrice;
        this.cinemaHall = cinemaHall;
        this.playDate = playDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public int getCinemaFilmId() {
        return cinemaFilmId;
    }

    public void setCinemaFilmId(int cinemaFilmId) {
        this.cinemaFilmId = cinemaFilmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getFilmPrice() {
        return filmPrice;
    }

    public void setFilmPrice(int filmPrice) {
        this.filmPrice = filmPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
