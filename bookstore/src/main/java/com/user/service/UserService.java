package com.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.user.dao.UserDao;
import com.user.entity.UserInfo;
import com.util.AppResponse;
import com.util.JsonUtils;
import com.util.RedisOperator;
import com.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 用户实现类
 * wumaoxing
 * 2020-03-24 16:38
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     * wumaoxing
     * 2020-03-24 16:56
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(UserInfo userInfo) {
        //检验用户是否存在
        int countUserAccount = userDao.countUserAccount(userInfo);
        if (countUserAccount != 0) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        userInfo.setUserId(StringUtil.getCommonCode(2));
        //userInfo.setUserId(String.valueOf(4));
        //新增用户
        int count = userDao.addUser(userInfo);
        if (count == 0) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 修改用户
     * wumaoxing
     * 2020-03-25 9:17
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserById(UserInfo userInfo) {
        //检验用户是否存在
        int countUserAccount = userDao.countUserAccount(userInfo);
        if (countUserAccount != 0) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        //修改用户
        int count = userDao.updateUserById(userInfo);
        if (count == 0) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 删除用户
     * wumaoxing
     * 2020-03-25 10:10
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId,String updateUserId){
        List<String> listDeleteUserId = Arrays.asList(userId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        int count = userDao.deleteUser(listDeleteUserId,updateUserId);
        if(count == 0) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 用户列表
     * wumaoxing
     * 2020-03-25 14:50
     */
    public AppResponse listUsers(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsers(userInfo);
        // 包装Page对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 用户详情
     * wumaoxing
     * 2020-03-25 15:37
     */
    public AppResponse findUserById(String userId) {
        UserInfo userInfo=userDao.findUserById(userId);
        if(userInfo == null){
            return AppResponse.bizError("用户账户不存在，请重新输入！");
        }
        return AppResponse.success("查询成功！",userInfo);
    }
}
