package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.FilmInfo;
import com.ruoyi.system.domain.dto.FilmInfoDto;
import com.ruoyi.system.domain.dto.UpdateFilmDto;
import com.ruoyi.system.service.film.ISysFilmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/film")
public class SysFilmController extends BaseController {
    @Resource
    private ISysFilmService filmService;

    @PreAuthorize("@ss.hasPermi('system:film:list')")
    @GetMapping("/list")
    public TableDataInfo filmList() {
        startPage();
        List<FilmInfoDto> filmInfos = filmService.selectFilmList();
        return getDataTable(filmInfos);
    }
    @PreAuthorize("@ss.hasPermi('system:film:query')")
    @RequestMapping("/{filmId}")
    public AjaxResult filmById(@PathVariable("filmId") int filmId) {
        FilmInfo filmInfo = filmService.selectFilmById(filmId);
        return success(filmInfo);
    }

    @PreAuthorize("@ss.hasPermi('system:film:update')")
    @PutMapping
    public AjaxResult updateFilm(@RequestBody UpdateFilmDto param) {
        int res = filmService.updateFilmById(param);
        return success(res);
    }

    @PreAuthorize("@ss.hasPermi('system:film:insert')")
    @PostMapping
    public AjaxResult createFilm(@RequestBody FilmInfo filmInfo) {
        return success(filmService.insertFilm(filmInfo));
    }

    @PreAuthorize("@ss.hasPermi('system:film:delete')")
    @DeleteMapping("/{filmId}")
    public AjaxResult deleteFilm(@PathVariable int filmId) {
        return toAjax(filmService.deleteFilms(filmId));
    }

    @PreAuthorize("@ss.hasPermi('system:film:query')")
    @GetMapping("/name/{filmName}")
    public AjaxResult filmByFilmName(@PathVariable("filmName") String filmName) {
        List<FilmInfo> filmInfos = filmService.selectFilmsByFilmName(filmName);
        return success(filmInfos);
    }
}
