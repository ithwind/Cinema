package com.ithWind.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.Seat;

/**
 * 用于返回当前电影座位
 */
public class FilmDto extends BaseEntity {
    /**
     * 电影信息
     */
    private FilmInfo filmInfo;
    /**
     * 座位信息
     */
    private Seat seat;
}
