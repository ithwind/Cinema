package com.ithWind.service.user;

import com.ithWind.domain.dto.UserDto;

public interface IUserService  {

    /**
     * 查询用户信息
     */
    UserDto getUserByName();
    /**
     * 修改用户信息
     */
    int UpdateUser(UserDto userDto);
}
