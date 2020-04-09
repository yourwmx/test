package com.xzsd.pc.goodClassify.dao;

import com.xzsd.pc.goodClassify.entity.GoodClassifyDataInfo;
import com.xzsd.pc.goodClassify.entity.GoodClassifyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GoodClassifyDao
 * wumaoxing
 * 2020-03-26 15:18
 */
public interface GoodClassifyDao {

    /**
     * 统计商品分类数量
     * wumaoxing
     * 2020-03-26 15:19
     */
    int countGoodClassifyAccount(GoodClassifyInfo goodClassifyInfo);

    /**
     * 新增商品分类
     * wumaoxing
     * 2020-03-26 15:22
     */
    int addGoodClassify(GoodClassifyInfo goodClassifyInfo);

    /**
     * 修改商品分类
     * wumaoxing
     * 2020-03-26 16:22
     */
    int updateGoodClassifyById(GoodClassifyInfo goodClassifyInfo);

    /**
     * 查询商品分类编号
     * wumaoxing
     * 2020-04-01 21:42
     */
    String queryGoodClassifyIdByName(String goodClassifyName);
    /**
     * 删除商品分类
     * wumaoxing
     * 2020-03-26 16:23
     */
    int deleteGoodClassifyById(@Param("classifyId") String classifyId, @Param("updateUserId") String updateUserId);

    /**
     * 查询商品一级分类
     * wumaoxing
     * 2020-03-27 10:43
     */
    List<GoodClassifyDataInfo> queryTempGoodClassify();

    /**
     * 查询商品二级分类
     * wumaoxing
     * 2020-03-27 11:51
     */
    List<GoodClassifyDataInfo> queryNextGoodClassify(String nextClassifyId);

    /**
     * 商品分类详情
     * wumaoxing
     * 2020-03-27 14:34
     */
    GoodClassifyInfo findGoodClassifyById(String classifyId);

    /**
     *  查询是否为一级分类
     *  wumaoxing
     *  2020-03-27 17:12
     */
    int queryLastGoodClassify(String classifyId);
}
