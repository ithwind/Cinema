package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.dto.CinemaFilmDto;
import com.ruoyi.system.domain.dto.OrderDto;
import com.ruoyi.system.service.order.ISysOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/order")
public class SysOrderController extends BaseController {
    @Resource
    private ISysOrderService orderService;

    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo selectAllOrder() {
        startPage();
        List<OrderDto> orderDtos = orderService.selectOrderDtos();
        return getDataTable(orderDtos);
    }

    @GetMapping("/name/{userName}")
    public AjaxResult selectOrdersByUserName(@PathVariable("userName") String userName) {
        List<OrderDto> orderDtos = orderService.selectOrderDtosByUserName(userName);
        return success(orderDtos);
    }
}
