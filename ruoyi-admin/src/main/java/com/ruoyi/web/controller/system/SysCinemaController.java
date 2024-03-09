package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Cinema;
import com.ruoyi.system.service.cinema.ISysCinemaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/cinema")
public class SysCinemaController extends BaseController {
    @Resource
    private ISysCinemaService cinemaService;

    @PreAuthorize("@ss.hasPermi('system:cinema:list')")
    @GetMapping("/list")
    public TableDataInfo selectAllCinemas() {
        startPage();
        List<Cinema> cinemas = cinemaService.selectCinemaList();
        return getDataTable(cinemas);
    }
    @PreAuthorize("@ss.hasPermi('system:cinema:query')")
    @GetMapping("/{cinemaId}")
    public AjaxResult selectCinemaById(@PathVariable("cinemaId") int cinemaId) {
        Cinema cinema = cinemaService.selectCinemaById(cinemaId);
        return success(cinema);
    }
    @PreAuthorize("@ss.hasPermi('system:cinema:update')")
    @PutMapping
    public AjaxResult updateCinema(@RequestBody Cinema param) {
        return success(cinemaService.updateCinema(param));
    }
    @PreAuthorize("@ss.hasPermi('system:cinema:insert')")
    @PostMapping
    public AjaxResult insertCinema(@RequestBody Cinema cinema) {
        return success(cinemaService.insertCinema(cinema));
    }

    @PreAuthorize("@ss.hasPermi('system:cinema:delete')")
    @DeleteMapping("/{cinemaId}")
    public AjaxResult deleteCinema(@PathVariable("cinemaId") int cinemaId) {
        return success(cinemaService.deleteCinema(cinemaId));
    }

    @PreAuthorize("@ss.hasPermi('system:cinema:query')")
    @GetMapping("/name/{cinemaName}")
    public AjaxResult selectCinemaByName(@PathVariable("cinemaName") String cinemaName) {
        List<Cinema> cinemas = cinemaService.selectCinemaByCinemaName(cinemaName);
        return success(cinemas);
    }
}
