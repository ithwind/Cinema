package com.ithWind.domain.mapper;

import com.ithWind.domain.User;
import com.ithWind.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名查询用户密码
     */
    String getPasswordByUsername(String userName);
    /**
     * 注册用户
     */
    int insertUser(User user);
    /**
     * 判断用户名是否重复
     */
    boolean checkDuplicateUserName(String userName);
    /**
     * 通过用户名查找
     */
    User selectUserByName(String username);

    /**
     * 修改用户信息
     */
    int UpdateUser(UserDto userDto);
}

