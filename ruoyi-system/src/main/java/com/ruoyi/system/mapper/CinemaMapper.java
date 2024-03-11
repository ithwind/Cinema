package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.domain.dto.UpdateCinemaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 电影院信息控制层
 */
@Mapper
public interface CinemaMapper {
    /**
     * 获取全部电影院信息
     */
    public List<Cinema> selectCinemaList();
    /**
     * 通过id获取影院详细信息
     */
    public Cinema selectCinemaById(int cinemaId);
    /**
     * 修改指定id影院信息
     */
    public int updateCinema(Cinema cinema);
    /**
     * 新增影院信息
     */
    public int insertCinema(Cinema cinema);
    /**
     * 删除影院信息
     */
    public int deleteCinema(int cinemaId);
    /**
     * 根据影院名称查询影院
     */
    public List<Cinema> selectCinemaByCinemaName(String cinemaName);
}
