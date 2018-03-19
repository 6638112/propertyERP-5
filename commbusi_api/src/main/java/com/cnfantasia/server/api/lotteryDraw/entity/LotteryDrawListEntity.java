package com.cnfantasia.server.api.lotteryDraw.entity;


import com.cnfantasia.server.domainbase.lotteryDrawProduct.entity.LotteryDrawProduct;

import java.util.List;

/**
 * @ClassName: LotteryDrawListEntity
 * @Date: 2016-11-28 18:24
 * @Auther: yanghua
 * @Description:(幸运抽奖页面数据)
 */
public class LotteryDrawListEntity {
    /**其他用户中奖记录*/
    private List<LotteryDrawEntity> userRecordList;
    /**当前用户中奖记录*/
    private LotteryDrawEntity userRecord;
    /**奖品列表*/
    private List<LotteryDrawProductEntity> products;
    private List<String> names;
    /**中奖状态1中，2不中*/
    private Integer prizeStatus;
    /**抽奖状态2未抽奖， 1 已经抽奖*/
    private Integer drawStatus;

    public List<LotteryDrawEntity> getUserRecordList() {
        return userRecordList;
    }

    public void setUserRecordList(List<LotteryDrawEntity> userRecordList) {
        this.userRecordList = userRecordList;
    }

    public LotteryDrawEntity getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(LotteryDrawEntity userRecord) {
        this.userRecord = userRecord;
    }

    public List<LotteryDrawProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<LotteryDrawProductEntity> products) {
        this.products = products;
    }

    public Integer getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(Integer prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public Integer getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
