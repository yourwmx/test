package com.xzsd.pc.goodClassify.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 * wumaoxing
 * 2020-03-27 10:40
 */
public class GoodClassifyDataInfo {
    /**
     * 当前分类id
     */
    private String tempClassifyId;
    /**
     * 当前分类名称
     */
    private String tempClassifyName;
    /**
     * 备注
     */
    private String classifyNote;
    /**
     * 下一级分类
     */
    private List<GoodClassifyDataInfo> nextDataInfos = new ArrayList<>();

    public String getTempClassifyId() {
        return tempClassifyId;
    }

    public void setTempClassifyId(String tempClassifyId) {
        this.tempClassifyId = tempClassifyId;
    }

    public String getTempClassifyName() {
        return tempClassifyName;
    }

    public void setTempClassifyName(String tempClassifyName) {
        this.tempClassifyName = tempClassifyName;
    }

    public String getClassifyNote() {
        return classifyNote;
    }

    public void setClassifyNote(String classifyNote) {
        this.classifyNote = classifyNote;
    }

    public List<GoodClassifyDataInfo> getNextDataInfos() {
        return nextDataInfos;
    }

    public void setNextDataInfos(List<GoodClassifyDataInfo> nextDataInfos) {
        this.nextDataInfos = nextDataInfos;
    }
}
