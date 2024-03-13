package com.ithWind.controller;

import com.ithWind.service.swiper.ISwiperService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/swiper")
public class SwiperController extends BaseController {
    @Resource
    private ISwiperService swiperService;

    @GetMapping("/list")
    public AjaxResult selectAllSwipers() {
        return success(swiperService.selectAllSwipers());
    }

}
