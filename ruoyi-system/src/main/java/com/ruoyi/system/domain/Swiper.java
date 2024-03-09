package com.ruoyi.system.domain;

/**
 * 轮播图
 */
public class Swiper {
    /**
     * 轮播图id
     */
    private int SwiperId;
    /**
     * 电影海报
     */
    private String filmImage;
    /**
     * 电影名称
     */
    private String filmName;

    /**
     * 电影id
     */
    private int filmId;

    public Swiper() {
    }

    public Swiper(int swiperId, String filmImage, String filmName, int filmId) {
        SwiperId = swiperId;
        this.filmImage = filmImage;
        this.filmName = filmName;
        this.filmId = filmId;
    }

    public int getSwiperId() {
        return SwiperId;
    }

    public void setSwiperId(int swiperId) {
        SwiperId = swiperId;
    }

    public String getFilmImage() {
        return filmImage;
    }

    public void setFilmImage(String filmImage) {
        this.filmImage = filmImage;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }
}
