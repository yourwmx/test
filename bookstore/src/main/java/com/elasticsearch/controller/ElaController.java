//package com.elasticsearch.controller;
//
//import com.elasticsearch.entity.User;
//import com.elasticsearch.service.ElaService;
//import com.util.AppResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * 增删改查User
// * wumaoxing
// * 2020-03-24 15:50
// */
//@RestController
//@RequestMapping("/elasticsearch")
//public class ElaController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ElaController.class);
//
//    @Resource
//    private ElaService elaService;
//
//    /**
//     * 新增用户
//     * wumaoxing
//     * 2020-03-24 15:57
//     */
//    @PostMapping("addUser")
//    public AppResponse addUser(User user) {
//        try {
//            AppResponse appResponse = elaService.addUser(user);
//            return appResponse;
//        } catch (Exception e) {
//            logger.error("用户新增失败", e);
//            System.out.println(e.toString());
//            throw e;
//        }
//    }
//
//    /**
//     * 删除用户
//     * wumaoxing
//     * 2020-03-25 10:00
//     */
//    @PostMapping("deleteUser")
//    public void deleteUser(String userId){
//        try{
//            elaService.deleteUser(userId);
//        } catch (Exception e) {
//            logger.error("删除用户信息错误", e);
//            System.out.println(e.toString());
//            throw e;
//        }
//    }
//
//    /**
//     * 用户列表
//     * wumaoxing
//     * 2020-03-25 14:50
//     */
//    @RequestMapping(value = "listUsers")
//    public AppResponse listUsers() {
//        try {
//            return elaService.listUsers();
//        } catch (Exception e) {
//            logger.error("查询用户列表异常", e);
//            System.out.println(e.toString());
//            throw e;
//        }
//    }
//
//}
