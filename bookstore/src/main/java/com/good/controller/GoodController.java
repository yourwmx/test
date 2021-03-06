package com.good.controller;

import com.good.entity.GoodInfo;
import com.good.service.GoodService;
import com.util.AppResponse;
import com.util.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 增删改查Good
 * wumaoxing
 * 2020-03-26 10:42
 */
@RestController
@RequestMapping("/pc/good")
public class GoodController {

    private static final Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Resource
    private GoodService goodService;

    /**
     * 新增商品
     * wumaoxing
     * 2020-03-26 10:50
     */
    @PostMapping("addGood")
    public AppResponse addGood(GoodInfo goodInfo) {
        try {
            String userId = AuthUtils.getCurrentUserId();
            goodInfo.setCreateBy(userId);
            AppResponse appResponse = goodService.addGood(goodInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品
     * wumaoxing
     * 2020-03-26 14:15
     */
    @PostMapping("updateGoodById")
    public AppResponse updateGoodById(GoodInfo goodInfo) {
        try {
            String userId = AuthUtils.getCurrentUserId();
            goodInfo.setLastModifiedBy(userId);
            return goodService.updateGoodById(goodInfo);
        } catch (Exception e) {
            logger.error("修改商品信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品
     * wumaoxing
     * 2020-03-27 16:35
     */
    @PostMapping("deleteGood")
    public AppResponse deleteGood(String goodsId) {
        try {
            String updateUserId = AuthUtils.getCurrentUserId();
            return goodService.deleteGood(goodsId, updateUserId);
        } catch (Exception e) {
            logger.error("删除商品信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品上下架
     * wumaoxing
     * 2020-03-28 10:37
     */
    @PostMapping("updateGoodState")
    public AppResponse updateGoodState(String goodsId, String goodsState) {
        try {
            String updateUserId = AuthUtils.getCurrentUserId();
            return goodService.updateGoodState(goodsId, updateUserId, goodsState);
        } catch (Exception e) {
            logger.error("商品上架或下架错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品分类
     * wumaoxing
     * 2020-03-28 11:09
     */
    @RequestMapping(value = "queryGoodClassify")
    public AppResponse queryGoodClassify(String lastClassifyId) {
        try {
            return goodService.queryGoodClassify(lastClassifyId);
        } catch (Exception e) {
            logger.error("查询商品分类异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 商品分页列表
     * wumaoxing
     * 2020-03-28 11:27
     */
    @RequestMapping(value = "listGoods")
    public AppResponse listGoods(GoodInfo goodInfo) {
        try {
            return goodService.listGoods(goodInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
