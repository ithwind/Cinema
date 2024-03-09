package com.ruoyi.system.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.FilmInfo;

import java.util.Date;

public class FilmInfoDto extends FilmInfo {
    /**
     * 票房总额
     */
    private int boxOffice;

    public FilmInfoDto(int id, String filmName, String filmEnName, String filmUrl, String filmLanguage, String filmType, String filmTime, String releaseLoc, Date releaseTime, String filmDes, String filmActor, int boxOffice) {
        super(id, filmName, filmEnName, filmUrl, filmLanguage, filmType, filmTime, releaseLoc, releaseTime, filmDes, filmActor);
        this.boxOffice = boxOffice;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(int boxOffice) {
        this.boxOffice = boxOffice;
    }
}
