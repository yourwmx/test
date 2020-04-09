package com.neusoft.webauth.user.dao;


import com.neusoft.webauth.user.entity.UserInfo;
import com.neusoft.webauth.user.entity.UserSettingDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserDao
 * @Description 用户管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
public interface UserDao {
    /**
     * 新增用户
     * @param userInfo 用户信息
     * @return
     */
    int addUser(UserInfo userInfo);

    /**
     * 获取用户信息
     * @return 用户信息
     */
    UserInfo findUserById(@Param("userId") String userId);

    /**
     * 获取所有用户信息
     * @param userInfo 用户信息
     * @return 所有用户信息
     */
    List<UserInfo> listUsers(UserInfo userInfo);

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return 修改结果
     */
    int updateUserById(UserInfo userInfo);

    /**
     * 删除用户信息
     */
//    int deleteUser(UserSettingDTO userSettingDTO);
    int deleteUser(@Param("listDeleteUserId") List<String> listDeleteUserId, @Param("updateUserId") String updateUserId);
    /**
     * 修改密码
     * @param userInfo 用户信息
     * @return
     */
    int updateUserPassword(UserInfo userInfo);
    /**
     * 统计用户账号数量
     * @param userInfo 用户信息
     * @return
     */
    int countUserAccount(UserInfo userInfo);
    /**
     * 统计用户手机号数量
     */
    int countUserPhone(UserInfo userInfo);
}
