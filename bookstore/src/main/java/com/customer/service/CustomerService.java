package com.customer.service;

import com.customer.dao.CustomerDao;
import com.customer.entity.CustomerInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.AppResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户实现类
 * wumaoxing
 * 2020-03-30 9:48
 */
@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;
    /**
     * 客户分页列表
     * wumaoxing
     * 2020-03-30 9:50
     */
    public AppResponse listCustomers(CustomerInfo customerInfo) {
        PageHelper.startPage(customerInfo.getPageNum(), customerInfo.getPageSize());
        List<CustomerInfo> customerInfoList = customerDao.listCustomers(customerInfo);
        // 包装Page对象
        PageInfo<CustomerInfo> pageData = new PageInfo<CustomerInfo>(customerInfoList);
        return AppResponse.success("查询成功！",pageData);
    }
}
