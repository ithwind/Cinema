package com.ithWind.service.login;

import com.ithWind.domain.req.LoginReq;
import com.ithWind.domain.resp.LoginResp;

public interface ILoginService {
    /**
     * 登录操作
     */
    LoginResp login(LoginReq req);
}
