package com.hotgood.service;

import com.dictionary.entity.DictionaryInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hotgood.dao.HotgoodDao;
import com.hotgood.entity.HotgoodInfo;
import com.util.AppResponse;
import com.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Hotgood实现类
 * wumaoxing
 * 2020-04-04 12:12
 */
@Service
public class HotgoodService {

    @Resource
    private HotgoodDao hotgoodDao;

    /**
     * 新增热门位商品
     * wumaoxing
     * 2020-04-04 13:33
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotgood(HotgoodInfo hotgoodInfo){
        //检验热门位商品排序是否重复
        int count = hotgoodDao.queryHotgoodSort(hotgoodInfo.getHotgoodsSort());
        if (count != 0) {
            return AppResponse.bizError("热门位商品排序重复，请重试！");
        }
        //新增热门位商品
        hotgoodInfo.setHotgoodsId(StringUtil.getCommonCode(2));
        count = hotgoodDao.addHotgood(hotgoodInfo);
        if (count == 0) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 修改热门位商品
     * wumaoxing
     * 2020-04-04 13:33
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotgoodById(HotgoodInfo hotgoodInfo){
        //检验热门位商品排序是否重复
        int count = hotgoodDao.queryHotgoodSort(hotgoodInfo.getHotgoodsSort());
        if (count != 0) {
            return AppResponse.bizError("热门位商品排序重复，请重试！");
        }
        //修改轮播图
        count = hotgoodDao.updateHotgoodById(hotgoodInfo);
        if (count == 0) {
            return AppResponse.bizError("修改失败，请重试！");
        }
        return AppResponse.success("修改成功！");
    }

    /**
     * 删除热门位商品
     * wumaoxing
     * 2020-04-04 13:33
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotgood(String hotgoodsId, String updateUserId) {
        List<String> listDeleteHotgoodsId = Arrays.asList(hotgoodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        // 删除热门位商品
        int count = hotgoodDao.deleteHotgood(listDeleteHotgoodsId, updateUserId);
        if (count == 0) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 热门位商品分页列表
     * wumaoxing
     * 2020-04-04 13:33
     */
    public AppResponse listHotgoods(HotgoodInfo hotgoodInfo) {
        PageHelper.startPage(hotgoodInfo.getPageNum(), hotgoodInfo.getPageSize());
        List<HotgoodInfo> hotGoodInfoList = hotgoodDao.listHotgoods(hotgoodInfo);
        // 包装Page对象
        PageInfo<HotgoodInfo> pageData = new PageInfo<HotgoodInfo>(hotGoodInfoList);
        return AppResponse.success("查询成功！",pageData);
    }

    /**
     * 热门位商品展示数量设置
     * wumaoxing
     * 2020-04-04 13:33
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotgoodNumber(DictionaryInfo dictionaryInfo) {
        AppResponse appResponse = AppResponse.success("热门位商品展示数量设置成功！");
        // 热门位商品展示数量设置
        int count = hotgoodDao.updateHotgoodNumber(dictionaryInfo);
        if (count == 0) {
            appResponse = AppResponse.bizError("热门位商品展示数量设置失败，请重试！");
        }
        return appResponse;
    }
}
