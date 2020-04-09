package com.slideshow.dao;

import com.good.entity.GoodInfo;
import com.slideshow.entity.SlideshowInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SlideshowDao
 * wumaoxing
 * 2020-03-28 14:57
 */

public interface SlideshowDao {
    /**
     * 新增轮播图
     * wumaoxing
     * 2020-03-28 14:57
     */
    int addSlideshow(SlideshowInfo slideshowInfo);
    /**
     * 检验轮播图排序是否重复
     * wumaoxing
     * 2020-04-01 21:20
     */
    int querySlideshowSort(int slideshowSort);
    /**
     * 修改轮播图
     * wumaoxing
     * 2020-03-28 15:37
     */
    int updateSlideshowById (SlideshowInfo slideshowInfo);
    /**
     * 删除轮播图
     * wumaoxing
     * 2020-03-29 20:56
     */
    int deleteSlideshow(@Param("listDeleteSlideshowsId") List<String> listDeleteSlideshowsId, @Param("updateUserId") String updateUserId);
    /**
     * 轮播图启用禁用
     * wumaoxing
     * 2020-03-29 20:56
     */
    int updateSlideshowState(@Param("listUpdateSlideshowsId") List<String> listUpdateSlideshowsId,@Param("updateUserId") String updateUserId,@Param("slideshowsState") String slideshowsState);
    /**
     * 轮播图分页列表
     * wumaoxing
     * 2020-03-29 21:34
     */
    List<SlideshowInfo> listSlideshows(SlideshowInfo slideshowInfo);
    /**
     * 商品分页列表
     * wumaoxing
     * 2020-03-28 11:33
     */
    List<GoodInfo> listGoods(GoodInfo goodInfo);
}
