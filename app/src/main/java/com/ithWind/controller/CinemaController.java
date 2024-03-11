package com.ithWind.controller;

import com.ithWind.service.cinema.ICinemaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/cinema")
public class CinemaController extends BaseController {
    @Resource
    private ICinemaService cinemaService;

    @GetMapping("/{filmId}")
    public AjaxResult getCinemasByFilmId(@PathVariable("filmId") int filmId) {
        return success(cinemaService.getCinemaListByFilmId(filmId));
    }

}
