package com.ithWind.controller;

import com.ithWind.common.UserContext;
import com.ithWind.domain.req.LoginReq;
import com.ithWind.service.login.ILoginService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {
    @Resource
    private ILoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginReq req) {
        return success(loginService.login(req));
    }
}

