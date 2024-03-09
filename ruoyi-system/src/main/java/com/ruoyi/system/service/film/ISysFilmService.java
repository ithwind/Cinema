package com.ruoyi.system.service.film;

import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.dto.FilmInfoDto;
import com.ruoyi.system.domain.dto.UpdateFilmDto;

import java.util.List;

public interface ISysFilmService {
    /**
     * 获取全部电影信息
     */
    public List<FilmInfoDto> selectFilmList();

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
     * 删除电影
     */
    public int deleteFilms(int FilmId);

    /**
     * 根据电影名称查询电影
     */
    public List<FilmInfo> selectFilmsByFilmName(String filmName);
}
