package com.neusoft.webauth.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.user.dao.UserDao;
import com.neusoft.webauth.user.entity.UserInfo;
import com.neusoft.webauth.user.entity.UserSettingDTO;
import com.neusoft.webauth.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName UserService
 * @Description 用户管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     * 部门：南京软件研发中心
     * 功能：新增用户
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */

    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(UserInfo userInfo) {

        // 校验账号是否存在
        int countUserAccount = userDao.countUserAccount(userInfo);
        if (countUserAccount != 0) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 检验手机是否存在
        int countUserPhone = userDao.countUserPhone(userInfo);
        if (countUserPhone != 0) {
            return AppResponse.bizError("用户手机号已存在，请重新输入！");
        }
        // 密码加密 默认为123456
        String password = PasswordUtils.generatePassword("123456");
        userInfo.setUserId(UUIDUtils.getUUID());
        userInfo.setUserPassword(password);
        userInfo.setIsDeleted(0);
        // 新增用户
        int count = userDao.addUser(userInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }



    /**
     * 通过用户代码查找用户
     *
     * @return 用户信息
     */
    public UserInfo findUserById(String userId) {
        return userDao.findUserById(userId);
    }


    /**
     * 部门：南京软件研发中心
     * 功能：获取用户列表
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/29
     */
    //@SystemLog(operation = "获取用户列表。。。。。")
    public AppResponse listUsers(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsers(userInfo);
        // 包装Page对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改用户信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/3
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserById(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int countUserAcct = userDao.countUserAccount(userInfo);
        if(0 != countUserAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        int countUserPhone = userDao.countUserPhone(userInfo);
        if (countUserPhone != 0) {
            return AppResponse.bizError("用户手机号已存在，请重新输入！");
        }
        // 修改用户信息
        int count = userDao.updateUserById(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        // 新增用部门
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除用户信息
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId,String updateUserId) {
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除用户
        List<String> listDeleteUserId = Arrays.asList(userId.split(","));
        int count = userDao.deleteUser(listDeleteUserId,updateUserId);
        if(0 == count) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 回滚
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：修改密码
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/30
     */
    public AppResponse updatePassword(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改密码成功！");
        // 需要校验原密码是否正确
        if(null != userInfo.getUserPassword() && !"".equals(userInfo.getUserPassword())) {
            String oldPwd = PasswordUtils.generatePassword(userInfo.getUserPassword());
            // 获取用户信息
            UserInfo userDetail = userDao.findUserById(userInfo.getUserId());
            if(null == userDetail) {
                return AppResponse.bizError("用户不存在或已被删除！");
            } else {
                if(!oldPwd.equals(userDetail.getUserPassword())) {
                    return AppResponse.bizError("原密码不匹配，请重新输入！");
                }
            }
        }
        // 修改密码
        userInfo.setNewPassword(PasswordUtils.generatePassword(userInfo.getNewPassword()));
        int count = userDao.updateUserPassword(userInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改密码失败，请重试！");
        }
        return appResponse;
    }
}
