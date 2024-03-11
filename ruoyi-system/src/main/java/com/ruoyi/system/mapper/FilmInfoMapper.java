package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.dto.UpdateFilmDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 电影信息 控制层
 */
@Mapper
public interface FilmInfoMapper{
    /**
     * 查询有电影
     * @return 返回电影列表
     */
    public List<FilmInfo> selectFilmList();

    /**
     * 通过id查询电影信息
     * @return 电影详细信息
     */
    public FilmInfo selectFilmById(int filmId);

    /**
     * 根据电影id修改电影信息
     * @param param 修改电影信息的参数
     * @return 修改结果
     */
    public int updateFilmById(UpdateFilmDto param);
    /**
     * 添加电影
     */
    public int insertFilm(FilmInfo filmInfo);
    /**
     * 通过id删除电影
     */
    public int deleteFilms(int FilmId);
    /**
     * 根据电影名称查询电影
     */
    public List<FilmInfo> selectFilmsByFilmName(String filmName);
}
