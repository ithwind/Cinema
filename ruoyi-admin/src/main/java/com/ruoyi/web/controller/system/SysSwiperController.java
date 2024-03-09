package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.Swiper;
import com.ruoyi.system.service.swiper.ISysSwiperService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/swiper")
public class SysSwiperController extends BaseController {
    @Resource
    private ISysSwiperService sysSwiperService;

    @PreAuthorize("@ss.hasPermi('system:swiper:list')")
    @GetMapping("/list")
    public TableDataInfo selectAllSwipers() {
        startPage();
        List<Swiper> swipers = sysSwiperService.selectAllSwiper();
        return getDataTable(swipers);
    }

    @PreAuthorize("@ss.hasPermi('system:swiper:query')")
    @GetMapping("/{swiperId}")
    public AjaxResult selectSwiperById(@PathVariable("swiperId") int swiperId) {
        return success(sysSwiperService.selectSwiperById(swiperId));
    }

    @PreAuthorize("@ss.hasPermi('system:swiper:update')")
    @PutMapping
    public AjaxResult updateSwiper(@RequestBody Swiper swiper) {
        return success(sysSwiperService.updateSwiper(swiper));
    }

    @PreAuthorize("@ss.hasPermi('system:swiper:delete')")
    @DeleteMapping("/{swiperId}")
    public AjaxResult deleteSwiperById(@PathVariable("swiperId") int swiperId) {
        return success(sysSwiperService.deleteSwiperById(swiperId));
    }

    @PreAuthorize("@ss.hasPermi('system:swiper:add')")
    @PostMapping
    public AjaxResult insertSwiper(@RequestBody Swiper swiper) {
        return success(sysSwiperService.addSwiper(swiper));
    }

    @PreAuthorize("@ss.hasPermi('system:swiper:query')")
    @GetMapping("/name/{filmName}")
    public AjaxResult selectSwipersByFilmName(@PathVariable("filmName") String filmName) {
        return success(sysSwiperService.selectSwipersByFilmName(filmName));
    }
}
