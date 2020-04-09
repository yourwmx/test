package com.xzsd.pc.store.dao;

import com.xzsd.pc.store.entity.StoreDataInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * StoreDao
 * wumaoxing
 * 2020-04-04 21:40
 */
public interface StoreDao {

    /**
     * 检验门店是否存在
     * wumaoxing
     * 2020-04-04 21:36
     */
    int countStoreAccount(StoreInfo storeInfo);
    /**
     * 新增门店
     * wumaoxing
     * 2020-04-04 21:54
     */
    int addStore(StoreInfo storeInfo);
    /**
     * 修改门店
     * wumaoxing
     * 2020-04-04 22:05
     */
    int updateStoreById(StoreInfo storeInfo);
    /**
     * 删除门店
     * wumaoxing
     * 2020-04-04 22:15
     */
    int deleteStore(@Param("listDeleteStoreId") List<String> listDeleteStoreId, @Param("updateUserId") String updateUserId);
    /**
     * 门店列表
     * wumaoxing
     * 2020-04-04 22:23
     */
    List<StoreInfo> listStores(StoreInfo storeInfo);
    /**
     * 门店详情
     * wumaoxing
     * 2020-04-04 22:26
     */
    StoreInfo findStoreById(String storeId);
    /**
     * 门店所在位置分类
     * wumaoxing
     * 2020-04-05 13:46
     */
    List<StoreDataInfo> queryStoreLocalClassify(String lastClassifyId);
}
