package com.user.controller;

import com.user.entity.UserInfo;
import com.user.service.UserService;
import com.util.AppResponse;
import com.util.AuthUtils;
import com.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 增删改查User
 * wumaoxing
 * 2020-03-24 15:50
 */
@RestController
@RequestMapping("/pc/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 新增用户
     * wumaoxing
     * 2020-03-24 15:57
     */
    @PostMapping("addUser")
    public AppResponse addUser(UserInfo userInfo) {
        try {
            String userId = AuthUtils.getCurrentUserId();
            //userInfo.setUserId(StringUtil.getCommonCode(2));
            userInfo.setCreateBy(userId);
            AppResponse appResponse = userService.addUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户
     * wumaoxing
     * 2020-03-25 9:17
     */
    @PostMapping("updateUserById")
    public AppResponse updateUserById(UserInfo userInfo){
        try{
            String userId = AuthUtils.getCurrentUserId();
            userInfo.setLastModifiedBy(userId);
            return userService.updateUserById(userInfo);
        } catch (Exception e) {
            logger.error("修改用户信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除用户
     * wumaoxing
     * 2020-03-25 10:00
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userId){
        try{
            String updateUserId = AuthUtils.getCurrentUserId();
            return userService.deleteUser(userId,updateUserId);
        } catch (Exception e) {
            logger.error("删除用户信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 用户列表
     * wumaoxing
     * 2020-03-25 14:50
     */
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(UserInfo userInfo) {
        try {
            return userService.listUsers(userInfo);
        } catch (Exception e) {
            logger.error("查询用户列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 用户详情
     * wumaoxing
     * 2020-03-25
     * 15:36
     */
    @RequestMapping(value = "findUserById")
    public AppResponse findUserById(String userId) {
        try {
            return userService.findUserById(userId);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
