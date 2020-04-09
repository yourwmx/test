package com.xzsd.pc.customer.service;

import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.customer.dao.CustomerDao;
import com.xzsd.pc.customer.entity.CustomerInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzsd.pc.util.AppResponse;
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
        PageInfo<CustomerInfo> pageData = null;
        //当前角色为管理员（0）还是店长（3）需判断
        int count = customerDao.getCurrentRole(SecurityUtils.getCurrentUserId());
        if(count == 0){
            PageHelper.startPage(customerInfo.getPageNum(), customerInfo.getPageSize());
            List<CustomerInfo> customerInfoList = customerDao.listCustomers(customerInfo);
            // 包装Page对象
            pageData = new PageInfo<CustomerInfo>(customerInfoList);
        }else if(count == 3){
            PageHelper.startPage(customerInfo.getPageNum(), customerInfo.getPageSize());
            List<CustomerInfo> customerInfoList = customerDao.listCustomersByStoreManager(customerInfo,SecurityUtils.getCurrentUserId());
            // 包装Page对象
            pageData = new PageInfo<CustomerInfo>(customerInfoList);
        }
        return AppResponse.success("查询成功！",pageData);
    }
}
