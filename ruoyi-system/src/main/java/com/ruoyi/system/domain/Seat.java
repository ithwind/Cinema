package com.ruoyi.system.domain;

/**
 * 座位表
 */
public class Seat {
    /**
     * 座位id
     */
    private int seatId;
    /**
     * x坐标
     */
    private int xCoord;
    /**
     * Y坐标
     */
    private int yCoord;
    /**
     * 座位编码
     */
    private String seatCode;
    /**
     * 座位状态
     */
    private int status;
    /**
     * 行号
     */
    private int rowNum;
    /**
     * 列号
     */
    private int columnNum;
    /**
     * 影院id
     */
    private int cinemaId;
    /**
     * 电影id
     */
    private int filmId;

    public Seat(int seatId, int xCoord, int yCoord, String seatCode, int status, int rowNum, int columnNum, int cinemaId, int filmId) {
        this.seatId = seatId;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.seatCode = seatCode;
        this.status = status;
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.cinemaId = cinemaId;
        this.filmId = filmId;
    }

    public Seat() {
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }
}
