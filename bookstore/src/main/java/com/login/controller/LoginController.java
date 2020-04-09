package com.login.controller;

import com.login.entity.UserInfo;
import com.login.service.LoginService;
import com.util.AppResponse;
import com.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 个人信息、登陆User
 */
@RestController
@RequestMapping("/pc/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;
    /**
     * 个人信息查询
     * wumaoxing
     * 2020-03-26 9:00
     */
    @RequestMapping(value = "findInformationById")
    public AppResponse findInformationById(String userId) {
        try {
            return loginService.findInformationById(userId);
        } catch (Exception e) {
            logger.error("查询个人信息异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 用户登陆
     * wumaoxing
     * 2020-03-26 9:42
     */
    @RequestMapping(value = "signInUser")
    public AppResponse signInUser(UserInfo userInfo){
        try {
            return loginService.signInUser(userInfo);
        } catch (Exception e) {
            logger.error("用户登陆异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
