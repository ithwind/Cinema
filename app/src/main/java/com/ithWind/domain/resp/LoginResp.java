package com.ithWind.domain.resp;

import java.io.Serializable;

/**
 * 登录响应
 */
public class LoginResp implements Serializable {
    private static final long serialVersionUID = 1L;

    // 成功时返回的属性
    private String token; // 登录成功的Token

    // 失败时返回的属性
    private boolean success; // 登录是否成功，默认为false，成功时设为true
    private String error; // 错误信息，登录失败时返回

    // 构造方法
    public LoginResp() {}

    // 假设已存在的构造函数，用于接收一个字符串参数
    public LoginResp(String tokenOrErrorMessage) {
        // 这里可以根据传入的字符串判断是成功还是失败
        // 并据此设置相应字段的值
        if (isSuccessToken(tokenOrErrorMessage)) {
            // 成功时的逻辑，比如：
            this.token = tokenOrErrorMessage;
            this.success = true;
        } else {
            // 失败时的逻辑，比如：
            this.error = tokenOrErrorMessage;
            this.success = false;
        }
    }

        // Getter and Setter methods
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    // 可能的方法用于验证token有效性
    private boolean isSuccessToken(String token) {
        return !token.isEmpty();
    }
}
