package com.technology.bean;

public class User {
    private Integer id;

    private String name;

    private String email;

    private UserAddress userAddress;

    private  UserLoginLog userLoginLog;

    public UserLoginLog getUserLoginLog() {
        return userLoginLog;
    }

    public void setUserLoginLog(UserLoginLog userLoginLog) {
        this.userLoginLog = userLoginLog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
