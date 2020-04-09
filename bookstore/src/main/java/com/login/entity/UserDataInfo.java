package com.login.entity;

/**
 * 用户个人信息类
 * wumaoxing
 * 2020-03-27 15:21
 */
public class UserDataInfo {

    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 头像
     */
    private String photo;
    /**
     * 角色 0管理员 1用户 2司机 3客户
     */
    private int role;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
