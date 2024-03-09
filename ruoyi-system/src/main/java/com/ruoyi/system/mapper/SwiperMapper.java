package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Swiper;

import java.util.List;

public interface SwiperMapper {
    /**
     * 获取全部轮播图
     */
    public List<Swiper> selectAllSwiper();
    /**
     * 通过id查询swiper
     */
    public Swiper selectSwiperById(int swiperId);
    /**
     * 修改轮播图
     */
    public int updateSwiper(Swiper swiper);
    /**
     * 通过id删除Swiper
     */
    public int deleteSwiperById(int swiperId);
    /**
     * 新增轮播图
     */
    public int addSwiper(Swiper swiper);
    /**
     * 通过filmName查询电影海报
     */
    public List<Swiper> selectSwipersByFilmName(String filmName);
}
