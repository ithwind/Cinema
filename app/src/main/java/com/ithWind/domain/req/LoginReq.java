package com.ithWind.domain.req;

/**
 * 登录参数
 */
public class LoginReq {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq() {
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
