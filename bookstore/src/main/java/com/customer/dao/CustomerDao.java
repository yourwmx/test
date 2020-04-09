package com.customer.dao;

import com.customer.entity.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;

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
}
