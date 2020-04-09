package com.login.dao;

import com.login.entity.UserInfo;
import com.login.entity.UserDataInfo;

/**
 * LoginDao
 * wumaoxing
 * 2020-03-26 9:10
 */
public interface LoginDao {
    /**
     * 个人信息查询
     * wumaoxing
     * 2020-03-26 9:10
     * @param userId
     * @return
     */
    UserDataInfo findInformationById(String userId);
    /**
     * 统计用户账号数量
     * wumaoxing
     * 2020-03-26 9:54
     */
    int countUserAccount(UserInfo userInfo);
    /**
     * 用户密码查询
     * wumaoxing
     * 2020-03-26 9:56
     */
    String inspetUserPassword(UserInfo userInfo);
    /**
     * 用户登陆
     * wumaoxing
     * 2020-03-26 9:59
     */
    UserDataInfo signInUser(UserInfo userInfo);
}
