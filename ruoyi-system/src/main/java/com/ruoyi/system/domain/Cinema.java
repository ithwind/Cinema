package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 影院信息类
 */
public class Cinema extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * 影院代码
     */
    private int cinemaId;
    /**
     * 影院名称
     */
    private String cinemaName;
    /**
     * 影院地址
     */
    private String cinemaAddr;
    /**
     * 位置经度
     */
    private double longitude;
    /**
     * 位置纬度
     */
    private double altitude;
    /**
     * 影厅
     */
    private String cinemaHall;
    /**
     * 影院标签
     */
    private String cinemaTag;

    public Cinema() {
    }

    public Cinema(int cinemaId, String cinemaName, String cinemaAddr, double longitude, double altitude, String cinemaHall, String cinemaTag) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.cinemaAddr = cinemaAddr;
        this.longitude = longitude;
        this.altitude = altitude;
        this.cinemaHall = cinemaHall;
        this.cinemaTag = cinemaTag;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAddr() {
        return cinemaAddr;
    }

    public void setCinemaAddr(String cinemaAddr) {
        this.cinemaAddr = cinemaAddr;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getCinemaTag() {
        return cinemaTag;
    }

    public void setCinemaTag(String cinemaTag) {
        this.cinemaTag = cinemaTag;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId=" + cinemaId +
                ", cinemaName='" + cinemaName + '\'' +
                ", cinemaAddr='" + cinemaAddr + '\'' +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", cinemaHall='" + cinemaHall + '\'' +
                ", cinemaTag='" + cinemaTag + '\'' +
                '}';
    }
}
