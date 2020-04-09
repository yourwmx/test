package com.xzsd.pc.customer.dao;

import com.xzsd.pc.customer.entity.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CustomerDao
 * wumaoxing
 * 2020-03-30 9:52
 */
@Mapper
public interface CustomerDao {
    /**
     * 客户分页列表
     * wumaoxing
     * 2020-03-30 9:53
     */
    List<CustomerInfo> listCustomers(CustomerInfo customerInfo);
    /**
     * 当前角色为管理员（0）还是店长（3）需判断
     * wumaoxing
     * 2020-04-09 13:37
     */
    int getCurrentRole(String userId);
    /**
     * 客户分页列表（店长）
     * wumaoxing
     * 2020-04-09 13:37
     */
    List<CustomerInfo> listCustomersByStoreManager(@Param("customerInfo") CustomerInfo customerInfo, @Param("userId") String userId);
}
