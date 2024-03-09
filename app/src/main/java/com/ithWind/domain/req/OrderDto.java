package com.ithWind.domain.req;

import com.ruoyi.system.domain.Seat;

import java.util.List;

/**
 * 下单Dto
 */
public class OrderDto {
    /**
     * 座位信息
     */
    private List<Seat> seats;
    /**
     * 电影id
     */
    private int filmId;
    /**
     * 影院id
     */
    private int cinemaId;
    /**
     * 票价
     */
    private int ticketPrice;
    /**
     * 票数
     */
    private int ticketNum;

    public OrderDto() {
    }

    public OrderDto(List<Seat> seats, int filmId, int cinemaId, int ticketPrice, int ticketNum) {
        this.seats = seats;
        this.filmId = filmId;
        this.cinemaId = cinemaId;
        this.ticketPrice = ticketPrice;
        this.ticketNum = ticketNum;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
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

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
