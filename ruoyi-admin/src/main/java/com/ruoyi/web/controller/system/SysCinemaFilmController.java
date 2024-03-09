package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.CinemaFilm;
import com.ruoyi.system.domain.dto.CinemaFilmDto;
import com.ruoyi.system.service.cinemaFilm.ISysCinemaFilmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/cinema-film")
public class SysCinemaFilmController extends BaseController {
    @Resource
    private ISysCinemaFilmService cinemaFilmService;

    @PreAuthorize("@ss.hasPermi('system:cinema-film:query')")
    @GetMapping("/list")
    public TableDataInfo selectAllCinemaFilms() {
        startPage();
        List<CinemaFilmDto> cinemaFilmDtos = cinemaFilmService.selectAllCinemaFilms();
        return getDataTable(cinemaFilmDtos);
    }

    @PreAuthorize("@ss.hasPermi('system:cinema-film:query')")
    @GetMapping("/{cinemaFilmId}")
    public AjaxResult selectCinemaFilmById(@PathVariable("cinemaFilmId") int cinemaFilmId) {
        return success(cinemaFilmService.selectCinemaFilmById(cinemaFilmId));
    }

    @PreAuthorize("@ss.hasPermi('system:cinema-film:update')")
    @PutMapping
    public AjaxResult updateCinemaFilm(@RequestBody CinemaFilm cinemaFilm) {
        return success(cinemaFilmService.updateCinemaFilm(cinemaFilm));
    }


    @PreAuthorize("@ss.hasPermi('system:cinema-film:delete')")
    @DeleteMapping("/{cinemaFilmId}")
    public AjaxResult deleteCinemaFilm(@PathVariable("cinemaFilmId") int cinemaFilmId) {
        return success(cinemaFilmService.deleteCinemaFilm(cinemaFilmId));
    }
    @PreAuthorize("@ss.hasPermi('system:cinema-film:add')")
    @PostMapping
    public AjaxResult insertCinemaFilm(@RequestBody CinemaFilm cinemaFilm) {
        return success(cinemaFilmService.insertCinemaFilm(cinemaFilm));
    }
    @PreAuthorize("@ss.hasPermi('system:cinema-film:query')")
    @GetMapping("/filmId/{filmId}")
    public AjaxResult selectCinemaFilmsByFilmId(@PathVariable("filmId") int filmId) {
        return success(cinemaFilmService.selectCinemaFilmsById(filmId));
    }
}


