package com.ithWind.controller;

import com.ithWind.service.film.IFilmService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/film")
public class FilmController extends BaseController {
    @Resource
    private IFilmService filmService;

    @GetMapping("/list")
    public AjaxResult getAllFilms() {
        return success(filmService.allFilms());
    }

    @GetMapping("/{filmId}")
    public AjaxResult getFilmById(@PathVariable("filmId") int filmId) {
        return success(filmService.getFilmById(filmId));
    }

    @GetMapping("/by-cinema/{cinemaId}")
    public AjaxResult getFilmsByCinemaId(@PathVariable("cinemaId")int cinemaId) {
        return success(filmService.getFilmsByCinemaId(cinemaId));
    }

    @GetMapping("/seat/{cinemaId}/{filmId}")
    public AjaxResult getSeat(@PathVariable("cinemaId") int cinemaId, @PathVariable("filmId") int filmId){
        return success(filmService.getSeats(cinemaId, filmId));
    }
}
