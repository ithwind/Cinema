package com.ruoyi.system.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 修改影院信息的参数
 */
public class UpdateCinemaDto extends BaseEntity {
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
     * 影院标签
     */
    private String cinemaTag;

    public UpdateCinemaDto() {
    }

    public UpdateCinemaDto(int cinemaId, String cinemaName, String cinemaAddr, String cinemaTag) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.cinemaAddr = cinemaAddr;
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

    public String getCinemaTag() {
        return cinemaTag;
    }

    public void setCinemaTag(String cinemaTag) {
        this.cinemaTag = cinemaTag;
    }

    @Override
    public String toString() {
        return "UpdateCinemaDto{" +
                "cinemaId=" + cinemaId +
                ", cinemaName='" + cinemaName + '\'' +
                ", cinemaAddr='" + cinemaAddr + '\'' +
                ", cinemaTag='" + cinemaTag + '\'' +
                '}';
    }
}
