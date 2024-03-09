package com.ithWind.domain.dto;

public class UserInfo {
    /**
     * 用户名
     */
    private String userName;

    public UserInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
