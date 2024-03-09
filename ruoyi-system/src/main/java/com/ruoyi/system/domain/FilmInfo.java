package com.ruoyi.system.domain;


import java.util.Date;

/**
 * 电影信息表
 */
public class FilmInfo {
    /**
     * 电影id
     */
    private int filmId;
    /**
     * 电影名
     */
    private String filmName;
    /**
     * 电影英文名
     */
    private String filmEnName;
    /**
     * 电影海报
     */
    private String filmUrl;
    /**
     * 电影语言
     */
    private String filmLanguage;
    /**
     * 电影类型
     */
    private String filmType;
    /**
     * 电影时长
     */
    private String filmTime;
    /**
     * 上映国家
     */
    private String releaseLoc;

    /**
     * 上映日期
     */
    private Date releaseTime;
    /**
     * 电影简介
     */
    private String filmDes;
    /**
     * 电影主演
     */
    private String filmActor;

    public FilmInfo() {
    }

    public FilmInfo(int id, String filmName, String filmEnName, String filmUrl, String filmLanguage, String filmType, String filmTime, String releaseLoc, Date releaseTime, String filmDes, String filmActor) {
        this.filmId = id;
        this.filmName = filmName;
        this.filmEnName = filmEnName;
        this.filmUrl = filmUrl;
        this.filmLanguage = filmLanguage;
        this.filmType = filmType;
        this.filmTime = filmTime;
        this.releaseLoc = releaseLoc;
        this.releaseTime = releaseTime;
        this.filmDes = filmDes;
        this.filmActor = filmActor;
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

    public String getFilmUrl() {
        return filmUrl;
    }

    public void setFilmUrl(String filmUrl) {
        this.filmUrl = filmUrl;
    }

    public String getFilmLanguage() {
        return filmLanguage;
    }

    public void setFilmLanguage(String filmLanguage) {
        this.filmLanguage = filmLanguage;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime;
    }

    public String getReleaseLoc() {
        return releaseLoc;
    }

    public void setReleaseLoc(String releaseLoc) {
        this.releaseLoc = releaseLoc;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getFilmDes() {
        return filmDes;
    }

    public void setFilmDes(String filmDes) {
        this.filmDes = filmDes;
    }

    public String getFilmActor() {
        return filmActor;
    }

    public void setFilmActor(String filmActor) {
        this.filmActor = filmActor;
    }
}
