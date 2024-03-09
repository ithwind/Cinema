package com.ithWind.service.register;

import com.ithWind.domain.req.RegisterReq;

public interface IRegisterService {
    /**
     * 注册
     */
    public int Register(RegisterReq req);
}
