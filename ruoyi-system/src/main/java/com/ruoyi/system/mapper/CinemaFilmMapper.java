package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CinemaFilm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CinemaFilmMapper {
    /**
     * 查询所有的电影和影院的关联数据
     */
    List<CinemaFilm> selectAllCinemaFilms();
    /**
     * 通过id查询关联数据
     */
    CinemaFilm selectCinemaFilmById(int cinemaFilmId);
    /**
     * 修改关联信息
     */
    int updateCinemaFilm(CinemaFilm cinemaFilm);
    /**
     * 删除关联信息
     */
    int deleteCinemaFilm(int cinemaFilmId);
    /**
     * 新增关联信息
     */
    int insertCinemaFilm(CinemaFilm cinemaFilm);
    /**
     * 根据filmId查询信息
     */
    List<CinemaFilm> selectCinemaFilmsById(int filmId);
    /**
     * 获取电影和影院关联id
     */
    int selectCinemaFilmId(@Param("cinemaId") int cinemaId, @Param("filmId") int filmId);
    /**
     * 通过影院id查询电影
     */
    List<Integer> selectFilmInfosByCinemaId(@Param("cinemaId") int cinemaId);

    /**
     * 通过电影id和影院id查询
     */
    CinemaFilm selectCinemaFilmByFilmIdAndCinemaId(@Param("cinemaId")int cinemaId, @Param("filmId") int filmId);
}
