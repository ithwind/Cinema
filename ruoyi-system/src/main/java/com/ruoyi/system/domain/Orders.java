package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 订单
 */
public class Orders {
    /**
     * 订单id
     */
    private int orderId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 关联id
     */
    private int cinemaFilmId;
    /**
     * 购票数量
     */
    private int ticketNum;
    /**
     * 影票价格
     */
    private int ticketPrice;
    /**
     * 电影名称
     */
    private String filmName;
    /**
     * 电影id
     */
    private int filmId;
    /**
     * 选座信息
     */
    private int seat;
    /**
     * 下单时间
     */
    private Date orderDate;
    /**
     * 影院地址
     */
    private String cinemaAddr;
    /**
     * 影院名称
     */
    private String cinemaName;
    /**
     * 影院id
     */
    private int cinemaId;

    public Orders(int orderId, String userName, int cinemaFilmId, int ticketNum, int ticketPrice, String filmName, int filmId, int seat, Date orderDate, String cinemaAddr, String cinemaName, int cinemaId) {
        this.orderId = orderId;
        this.userName = userName;
        this.cinemaFilmId = cinemaFilmId;
        this.ticketNum = ticketNum;
        this.ticketPrice = ticketPrice;
        this.filmName = filmName;
        this.filmId = filmId;
        this.seat = seat;
        this.orderDate = orderDate;
        this.cinemaAddr = cinemaAddr;
        this.cinemaName = cinemaName;
        this.cinemaId = cinemaId;
    }

    public Orders() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCinemaFilmId() {
        return cinemaFilmId;
    }

    public void setCinemaFilmId(int cinemaFilmId) {
        this.cinemaFilmId = cinemaFilmId;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCinemaAddr() {
        return cinemaAddr;
    }

    public void setCinemaAddr(String cinemaAddr) {
        this.cinemaAddr = cinemaAddr;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
