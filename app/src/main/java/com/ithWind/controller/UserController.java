package com.ithWind.controller;

import com.ithWind.domain.dto.UserDto;
import com.ithWind.service.user.IUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private IUserService userService;

    @GetMapping("/info")
    public AjaxResult getUserInfo() {
        UserDto user = userService.getUserByName();
        if (user == null) {
            return error("未登录");
        }
        return success(user);
    }

    @PostMapping
    public AjaxResult updateUserInfo(@RequestBody UserDto userDto) {
        int res = userService.UpdateUser(userDto);
        if (res <= 0) {
            return error("修改失败");
        }
        return success("修改信息成功");
    }
}
