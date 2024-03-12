package com.ithWind.controller;

import com.ithWind.domain.OrderVo;
import com.ithWind.domain.req.OrderDto;
import com.ithWind.service.order.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping
    public AjaxResult getHistory() {
        List<OrderVo> orderVoList = orderService.selectAllOrders();
        return success(orderVoList);
    }
}
