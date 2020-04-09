package com.xzsd.pc.good.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzsd.pc.good.dao.GoodDao;
import com.xzsd.pc.good.entity.GoodClassifyDataInfo;
import com.xzsd.pc.good.entity.GoodInfo;
import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 商品实现类
 * wumaoxing
 * 2020-03-26 10:52
 */
@Service
public class GoodService {
    @Resource
    private GoodDao goodDao;
//    @Autowired
//    private RedisOperator redisOperator;

    /**
     * 新增商品
     * wumaoxing
     * 2020-03-26 10:53
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGood(GoodInfo goodInfo) {
        //检验商品是否存在
        int countGoodAccount = goodDao.countGoodAccount(goodInfo);
        if (countGoodAccount != 0) {
            return AppResponse.bizError("商品已存在，请重新输入！");
        }
        goodInfo.setGoodsId(StringUtil.getCommonCode(2));
        //新增商品
        int count = goodDao.addGood(goodInfo);
        if (count == 0) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 修改商品
     * wumaoxing
     * 2020-03-26 14:20
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodById(GoodInfo goodInfo) {
        //检验商品是否存在
        int countGoodAccount = goodDao.countGoodAccount(goodInfo);
        if (countGoodAccount != 0) {
            return AppResponse.bizError("商品已存在，请重新输入！");
        }
        //查询商品编号
        goodInfo.setGoodsId(goodDao.queryGoodIdByName(goodInfo.getGoodsName()));
        //修改商品
        int count = goodDao.updateGoodById(goodInfo);
        if (count == 0) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 删除商品
     * wumaoxing
     * 2020-03-28 10:24
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGood(String goodsId, String updateUserId) {
        List<String> listDeleteGoodsId = Arrays.asList(goodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除商品
        int count = goodDao.deleteGood(listDeleteGoodsId, updateUserId);
        if (count == 0) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 商品上下架
     * wumaoxing
     * 2020-03-28 10:41
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodState(String goodsId, String updateUserId, String goodsState) {
        List<String> listUpdateGoodsId = Arrays.asList(goodsId.split(","));
        AppResponse appResponse = AppResponse.success("商品上架或下架成功！");
        // 商品上架或下架
        int count = goodDao.updateGoodState(listUpdateGoodsId, updateUserId, goodsState);
        if (count == 0) {
            appResponse = AppResponse.bizError("商品上架或下架失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品分类
     * wumaoxing
     * 2020-03-28 11:09
     */
    public AppResponse queryGoodClassify(String lastClassifyId) {
        List<GoodClassifyDataInfo> tempDataInfos = goodDao.queryTempGoodClassify(lastClassifyId);
        return AppResponse.success("查询成功！", tempDataInfos);
    }

    /**
     * 商品分页列表
     * wumaoxing
     * 2020-03-28 11:29
     */
    public AppResponse listGoods(GoodInfo goodInfo) {
//        if( redisOperator.get(goodInfo.toString()) != null ){
//            System.out.println("从redis查数据");
//            PageInfo<GoodInfo> pageData = JsonUtils.fromJson(redisOperator.get(goodInfo.toString()),PageInfo.class);
//            return AppResponse.success("查询成功！",pageData);
//        }else{
            System.out.println("从mysql查数据");
            PageHelper.startPage(goodInfo.getPageNum(), goodInfo.getPageSize());
            List<GoodInfo> goodInfoList = goodDao.listGoods(goodInfo);
            // 包装Page对象
            PageInfo<GoodInfo> pageData = new PageInfo<GoodInfo>(goodInfoList);
//            redisOperator.set(goodInfo.toString(), JsonUtils.toJson(pageData),300);
            return AppResponse.success("查询成功！",pageData);
//        }
    }
}
