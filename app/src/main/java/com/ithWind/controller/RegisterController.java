package com.ithWind.controller;

import com.ithWind.domain.req.RegisterReq;
import com.ithWind.service.register.IRegisterService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class RegisterController extends BaseController {
    @Resource
    private IRegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterReq req) {
        if (registerService.Register(req) == 1) {
            return success("注册成功");
        }
        return error("注册失败，出现错误");
    }
}
