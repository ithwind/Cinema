package com.ruoyi.system.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;


/**
 * 修改电影信息的参数
 */
public class UpdateFilmDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 电影id
     */
    private int filmId;
    /**
     * 电影名称
     */
    private String filmName;
    /**
     * 电影英文名称
     */
    private String filmEnName;
    /**
     * 电影语言
     */
    private String filmLanguage;

    public UpdateFilmDto(){

    }
    public UpdateFilmDto(int filmId, String filmName, String filmEnName, String filmLanguage) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmEnName = filmEnName;
        this.filmLanguage = filmLanguage;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmEnName() {
        return filmEnName;
    }

    public void setFilmEnName(String filmEnName) {
        this.filmEnName = filmEnName;
    }

    public String getFilmLanguage() {
        return filmLanguage;
    }

    public void setFilmLanguage(String filmLanguage) {
        this.filmLanguage = filmLanguage;
    }

    @Override
    public String toString() {
        return "UpdateFilmDto{" +
                "filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", filmEnName='" + filmEnName + '\'' +
                ", filmLanguage='" + filmLanguage + '\'' +
                '}';
    }
}
