package com.ithWind.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class UserDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 电话
     */
    private String phone;

    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 生日
     */
    private Date birthday;

    public UserDto(int userId, String userName, String phone, Date createTime, String sex, String address, Date birthday) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.createTime = createTime;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
    }

    public UserDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
