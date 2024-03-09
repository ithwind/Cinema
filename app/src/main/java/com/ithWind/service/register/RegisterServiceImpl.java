package com.ithWind.service.register;

import com.ithWind.domain.User;
import com.ithWind.domain.mapper.UserMapper;
import com.ithWind.domain.req.RegisterReq;
import com.ithWind.toolkit.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RegisterServiceImpl implements IRegisterService{
    @Resource
    private UserMapper userMapper;
    @Override
    public int Register(RegisterReq req) {
        User user = new User();
        // 密码加密
        String encryptPassword = PasswordUtil.encryptPassword(req.getPassword());
        // 设置默认参数
        user.setUserName(req.getUsername());
        user.setPassword(encryptPassword);
        user.setSex("男");
        // 默认注册时间
        user.setBirthday(new Date());
        user.setAddress("默认地址");
        user.setPhone("");
        user.setCreateTime(new Date());

        // 非空校验
        if (req.getUsername().isEmpty() || req.getPassword().isEmpty()) {
            return 0;
        }
        // 判断用户名是否重复
        boolean isDuplicate = userMapper.checkDuplicateUserName(user.getUserName());
        if (isDuplicate) {
            return 0;
        }
        // 注册成功
        return userMapper.insertUser(user);
    }
}
