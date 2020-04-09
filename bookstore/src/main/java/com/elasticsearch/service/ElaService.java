//package com.elasticsearch.service;
//
//import com.elasticsearch.dao.UserRepository;
//import com.elasticsearch.entity.User;
//import com.util.AppResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
///**
// * 用户实现类
// * wumaoxing
// * 2020-03-24 16:38
// */
//@Service
//public class ElaService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * 新增用户
//     * wumaoxing
//     * 2020-03-24 16:56
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public AppResponse addUser(User user) {
//        userRepository.save(user);
//        return AppResponse.success("新增成功！");
//    }
//
//
//    /**
//     * 删除用户
//     * wumaoxing
//     * 2020-03-25 10:10
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteUser(String userId){
//        userRepository.deleteById(userId);
//    }
//
//    /**
//     * 用户列表
//     * wumaoxing
//     * 2020-03-25 14:50
//     */
//    public AppResponse listUsers() {
//        return AppResponse.success("查询成功！",userRepository.findAll());
//    }
//
//}
