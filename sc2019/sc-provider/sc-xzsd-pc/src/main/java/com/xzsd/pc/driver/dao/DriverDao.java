package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.DriverInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DriverDao
 * wumaoxing
 * 2020-04-05 14:36
 */
public interface DriverDao {

    /**
     * 检验司机是否存在
     * wumaoxing
     * 2020-04-05 21:49
     */
    int countDriverAccount(DriverInfo driverInfo);
    /**
     * 检验司机手机号是否存在
     * wumaoxing
     * 2020-04-05 21:49
     */
    int countDriverPhone(String phone);
    /**
     * 新增用户
     * wumaoxing
     * 2020-04-05 15:13
     */
    int addUser(DriverInfo driverInfo);
    /**
     * 新增司机
     * wumaoxing
     * 2020-04-05 21:49
     */
    int addDriver(DriverInfo driverInfo);
    /**
     * 获取司机的用户编号
     * wumaoxing
     * 2020-04-05 15:43
     */
    String queryDriverUserIdById(String driverId);
    /**
     * 修改司机
     * wumaoxing
     * 2020-04-05 21:49
     */
    int updateDriverById(DriverInfo driverInfo);
    /**
     * 获取司机的用户编号列表
     */
    List<String> queryListDriverUserIdById(@Param("listDeleteDriverId") List<String> listDeleteDriverId);
    /**
     * 删除司机
     * wumaoxing
     * 2020-04-05 21:49
     */
    int deleteDriver(@Param("listDeleteDriverId") List<String> listDeleteDriverId, @Param("updateUserId") String updateUserId);
    /**
     * 司机列表
     * wumaoxing
     * 2020-04-05 21:49
     */
    List<DriverInfo> listDrivers(DriverInfo driverInfo);
    /**
     * 司机详情
     * wumaoxing
     * 2020-04-05 21:49
     */
    DriverInfo findDriverById(String driverId);
}
