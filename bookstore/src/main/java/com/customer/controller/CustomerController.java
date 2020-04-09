package com.customer.controller;

import com.customer.entity.CustomerInfo;
import com.customer.service.CustomerService;
import com.good.controller.GoodController;
import com.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 查Customer
 * wumaoxing
 * 2020-03-30 9:45
 */
@RestController
@RequestMapping("/pc/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Resource
    private CustomerService customerService;

    /**
     * 客户分页列表
     * wumaoxing
     * 2020-03-30 9:47
     */
    @RequestMapping(value = "listCustomers")
    public AppResponse listCustomers(CustomerInfo customerInfo) {
        try {
            return customerService.listCustomers(customerInfo);
        } catch (Exception e) {
            logger.error("查询客户列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
