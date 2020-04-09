//package com.elasticsearch.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//
//@Document(indexName = "user",type = "user")
//public class User {
//    /**
//     * 用户代码
//     */
//    @Id
//    private String userId;
//    /**
//     * 用户名称
//     */
//    private String userName;
//    /**
//     * 用户登录名
//     */
//    private String userAccount;
//    /**
//     * 用户密码
//     */
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(String userAccount) {
//        this.userAccount = userAccount;
//    }
//}