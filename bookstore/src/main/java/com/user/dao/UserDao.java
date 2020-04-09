package com.user.dao;

import com.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserDao
 * wumaoxing
 * 2020-03-24 16:41
 */
//@Mapper
public interface UserDao {
    /**
     * 新增用户
     * @param userInfo 用户信息
     * wumaoxing
     * 2020-03-24 16:43
     */
    int addUser(UserInfo userInfo);
    /**
     * 统计用户账户数量
     * @param userInfo 用户信息
     * wumaoxing
     * 2020-03-24 17:04
     */
    int countUserAccount(UserInfo userInfo);
    /**
     * 修改用户
     * @param userInfo 用户信息
     * wumaoxing
     * 2020-03-25 9:29
     */
    int updateUserById(UserInfo userInfo);
    /**
     * 删除用户
     * wumaoxing
     * 2020-03-25 11:24
     * @param listDeleteUserId  要删除的用户id集合
     * @param updateUserId    更新人
     * @return
     */
    int deleteUser(@Param("listDeleteUserId") List<String> listDeleteUserId, @Param("updateUserId") String updateUserId);
    /**
     * 用户列表
     * wumaoxing
     * 2020-03-25 14:59
     */
    List<UserInfo> listUsers(UserInfo userInfo);
    /**
     * 用户详情
     * wumaoxing
     * 2020-03-25 15:40
     */
    UserInfo findUserById(String userId);
}
