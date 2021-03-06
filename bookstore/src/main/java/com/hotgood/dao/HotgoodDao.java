package com.hotgood.dao;

import com.dictionary.entity.DictionaryInfo;
import com.hotgood.entity.HotgoodInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * HotgoodDao
 * wumaoxing
 * 2020-04-04 13:35
 */
public interface HotgoodDao {
    /**
     * 新增热门位商品
     * wumaoxing
     * 2020-04-04 13:37
     */
    int addHotgood(HotgoodInfo hotgoodInfo);
    /**
     * 检验热门位商品排序是否重复
     * wumaoxing
     * 2020-04-04 13:37
     */
    int queryHotgoodSort(int hotgoodsSort);
    /**
     * 修改热门位商品
     * wumaoxing
     * 2020-04-04 13:37
     */
    int updateHotgoodById (HotgoodInfo hotgoodInfo);
    /**
     * 删除热门位商品
     * wumaoxing
     * 2020-04-04 13:37
     */
    int deleteHotgood(@Param("listDeleteHotgoodsId") List<String> listDeleteHotgoodsId, @Param("updateUserId") String updateUserId);
    /**
     * 热门位商品分页列表
     * wumaoxing
     * 2020-04-04 13:37
     */
    List<HotgoodInfo> listHotgoods(HotgoodInfo hotgoodInfo);
    /**
     * 热门位商品展示数量设置
     * wumaoxing
     * 2020-04-04 13:37
     */
    int updateHotgoodNumber(DictionaryInfo dictionaryInfo);
}
