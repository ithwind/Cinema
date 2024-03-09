package com.ithWind.controller;

import com.ithWind.domain.req.OrderDto;
import com.ithWind.service.order.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/order")
public class OrderController extends BaseController {
    @Resource
    private IOrderService orderService;

    @PostMapping
    public AjaxResult placeOrder(@RequestBody OrderDto orderDto) {
        int res = orderService.placeOrder(orderDto);
        if (res == 0) {
            return error();
        }
        return success("下单成功");
    }
}
