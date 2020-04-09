package com.xzsd.pc.driver.service;

import com.neusoft.util.UUIDUtils;
import com.xzsd.pc.dictionary.dao.DictionaryDao;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserInfo;
import com.xzsd.pc.util.AppResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DriverService {

    @Resource
    private DriverDao driverDao;

    @Resource
    private UserDao userDao;

    @Resource
    private DictionaryDao dictionaryDao;

    /**
     * 新增司机
     * wumaoxing
     * 2020-04-05 14:39
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(DriverInfo driverInfo) {
        //检验司机是否存在
        int countDriverAccount = driverDao.countDriverAccount(driverInfo);
        if (countDriverAccount != 0) {
            return AppResponse.bizError("司机已存在，请重新输入！");
        }
        //检验司机手机号是否存在
        int countDriverPhone = driverDao.countDriverPhone(driverInfo.getDriverPhone());
        if (countDriverPhone != 0) {
            return AppResponse.bizError("司机手机号已存在，请重新输入！");
        }
        driverInfo.setDriverId(UUIDUtils.getUUID());
        //新增用户
        UserInfo userInfo = new UserInfo();
        String userId = UUIDUtils.getUUID();
        userInfo.setUserId(userId);
        userInfo.setName(driverInfo.getDriverName());
        userInfo.setPhone(driverInfo.getDriverPhone());
        userInfo.setUserAccount(driverInfo.getDriverAccount());
        userInfo.setUserPassword(driverInfo.getDriverPassword());
        userInfo.setIdCard(driverInfo.getIdCard());
        userInfo.setRole(1);
        int count = userDao.addUser(userInfo);
        if (count == 0) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        //新增司机
        driverInfo.setUserId(userId);
        driverInfo.setDriverId(UUIDUtils.getUUID());
        count = driverDao.addDriver(driverInfo);
        if (count == 0) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 修改司机
     * wumaoxing
     * 2020-04-05 14:39
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriverById(DriverInfo driverInfo) {
        //检验司机是否存在
        int countDriverAccount = driverDao.countDriverAccount(driverInfo);
        if (countDriverAccount != 0) {
            return AppResponse.bizError("司机已存在，请重新输入！");
        }
        //检验司机手机号是否存在
        int countDriverPhone = driverDao.countDriverPhone(driverInfo.getDriverPhone());
        if (countDriverPhone != 0) {
            return AppResponse.bizError("司机手机号已存在，请重新输入！");
        }
        //获取司机的用户编号
        String userId = driverDao.queryDriverUserIdById(driverInfo.getDriverId());
        //修改用户
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setName(driverInfo.getDriverName());
        userInfo.setPhone(driverInfo.getDriverPhone());
        userInfo.setUserAccount(driverInfo.getDriverAccount());
        userInfo.setUserPassword(driverInfo.getDriverPassword());
        userInfo.setIdCard(driverInfo.getIdCard());
        userInfo.setRole(1);
        //userInfo.setVersion(0);
        int count = userDao.updateUserById(userInfo);
        if (count == 0) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        //修改司机
        count = driverDao.updateDriverById(driverInfo);
        if (count == 0) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 删除司机
     * wumaoxing
     * 2020-04-05 14:39
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String driverId, String updateUserId) {
        List<String> listDeleteDriverId = Arrays.asList(driverId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //获取司机的用户编号
        List<String> listDeleteUserId = driverDao.queryListDriverUserIdById(listDeleteDriverId);
        // 删除用户
        int count = userDao.deleteUser(listDeleteUserId, updateUserId);
        if (count == 0) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        // 删除司机
        count = driverDao.deleteDriver(listDeleteDriverId, updateUserId);
        if (count == 0) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 司机分页列表
     * wumaoxing
     * 2020-04-05 14:39
     */
    public AppResponse listDrivers(DriverInfo driverInfo) {
        PageHelper.startPage(driverInfo.getPageNum(), driverInfo.getPageSize());
        List<DriverInfo> storeInfoList = driverDao.listDrivers(driverInfo);
        // 包装Page对象
        PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(storeInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 司机详情
     * wumaoxing
     * 2020-04-05 14:39
     */
    public AppResponse findDriverById(String driverId){
        //查询省市区名称
        DriverInfo driverInfo = driverDao.findDriverById(driverId);
        driverInfo.setDriverProvince(dictionaryDao.queryVById(driverInfo.getDriverProvinceId()));
        driverInfo.setDriverCity(dictionaryDao.queryVById(driverInfo.getDriverCityId()));
        driverInfo.setDriverArea(dictionaryDao.queryVById(driverInfo.getDriverAreaId()));
        return AppResponse.success("查询成功！",driverInfo);
    }

}
