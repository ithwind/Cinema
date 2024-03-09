package com.ithWind.domain.req;

public class RegisterReq {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public RegisterReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegisterReq() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
