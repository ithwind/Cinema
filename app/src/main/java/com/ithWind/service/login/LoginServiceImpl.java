package com.ithWind.service.login;

import com.ithWind.domain.mapper.UserMapper;
import com.ithWind.domain.req.LoginReq;
import com.ithWind.domain.resp.LoginResp;
import com.ithWind.service.login.ILoginService;
import com.ithWind.toolkit.JwtTokenUtil;
import com.ithWind.toolkit.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements ILoginService {
    @Resource
    private UserMapper userMapper;
    @Override
    public LoginResp login(LoginReq req) {
        LoginResp resp;
        String encryptPassword = userMapper.getPasswordByUsername(req.getUsername());
        if (encryptPassword == null) {
            resp = new LoginResp();
            resp.setError("密码或账号错误");
            return resp;
        }
        boolean res = PasswordUtil.validatePassword(req.getPassword(), encryptPassword);
        if (res) {
            String token = JwtTokenUtil.generateToken(req);
            resp = new LoginResp(token);
        } else {
            resp = new LoginResp();
            resp.setError("登录失败");
        }
        return resp;
    }
}
