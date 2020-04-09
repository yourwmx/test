package com.login.service;

import com.login.dao.LoginDao;
import com.login.entity.UserInfo;
import com.login.entity.UserDataInfo;
import com.util.AppResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户实现类
 * wumaoxing
 * 2020-03-26 9:07
 */
@Service
public class LoginService {
    @Resource
    private LoginDao loginDao;

    /**
     * 个人信息查询
     * wumaoxing
     * 2020-03-26 9:08
     *
     * @param userId
     * @return
     */
    public AppResponse findInformationById(String userId) {
        UserDataInfo userPersonalMsgInfo = loginDao.findInformationById(userId);
        return AppResponse.success("查询成功！", userPersonalMsgInfo);
    }

    /**
     * 用户登陆
     * wumaoxing
     * 2020-03-26 9:50
     */
    public AppResponse signInUser(UserInfo userInfo) {
        //检验用户是否存在
        int countUserAccount = loginDao.countUserAccount(userInfo);
        if (countUserAccount == 0) {
            return AppResponse.bizError("用户账号不存在，请重新输入！");
        }
        //检验密码是否正确
        String password = loginDao.inspetUserPassword(userInfo);
        if(password.equals(userInfo.getUserPassword()) != true){
            return AppResponse.bizError("用户密码错误，请重新输入！");
        }
        //用户登陆
        UserDataInfo userPersonalMsgInfo = loginDao.signInUser(userInfo);
        return AppResponse.success("登陆成功！", userPersonalMsgInfo);
    }
}
