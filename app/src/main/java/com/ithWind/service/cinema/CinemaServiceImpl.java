package com.ithWind.service.cinema;

import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.domain.CinemaFilm;
import com.ruoyi.system.mapper.CinemaFilmMapper;
import com.ruoyi.system.service.cinema.ISysCinemaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaServiceImpl implements ICinemaService{
    @Resource
    private ISysCinemaService cinemaService;
    @Resource
    private CinemaFilmMapper cinemaFilmMapper;
    @Override
    public List<Cinema> getCinemaListByFilmId(int filmId) {
        List<Cinema> cinemas = new ArrayList<>();
        List<CinemaFilm> cinemaFilms = cinemaFilmMapper.selectCinemaFilmsById(filmId);
        for (CinemaFilm cinemaFilm : cinemaFilms) {
            Cinema cinema = cinemaService.selectCinemaById(cinemaFilm.getCinemaId());
            cinemas.add(cinema);
        }
        return cinemas;
    }
}
