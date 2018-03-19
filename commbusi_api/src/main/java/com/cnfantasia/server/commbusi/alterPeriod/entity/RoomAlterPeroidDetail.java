package com.cnfantasia.server.commbusi.alterPeriod.entity;

import com.cnfantasia.server.api.plotproperty.entity.AlterFeeItem;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: RoomAlterPeroidDetail
 * @Date: 2016-10-25 9:14
 * @Auther: kangduo
 * @Description:(单个房间的收费项详情)
 */
public class RoomAlterPeroidDetail implements Serializable{
    private static final long serialVersionUID = -5793848921696166790L;
    //小区ID
    private BigInteger gbId;
    //真实房间ID
    private BigInteger realRoomId;
    //选择周期数据详情ID
    private BigInteger alterPeriodDataId;
    //小区名
    private String gbName;
    //楼栋名
    private String bName;
    //单元
    private String unitName;
    //房号
    private String roomNumTail;
    //账单开始日期
    private String billMonthStart;
    //滞纳金开始计算日期
    private String latefeeStart;
    //滞纳金
    private Long latefeeAmount;
    //主体费用列表
    private List<AlterRoomHasFeeItem> hasFeeItemList;

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public BigInteger getAlterPeriodDataId() {
        return alterPeriodDataId;
    }

    public void setAlterPeriodDataId(BigInteger alterPeriodDataId) {
        this.alterPeriodDataId = alterPeriodDataId;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomNumTail() {
        return roomNumTail;
    }

    public void setRoomNumTail(String roomNumTail) {
        this.roomNumTail = roomNumTail;
    }

    public String getBillMonthStart() {
        return billMonthStart;
    }

    public void setBillMonthStart(String billMonthStart) {
        this.billMonthStart = billMonthStart;
    }

    public String getLatefeeStart() {
        return latefeeStart;
    }

    public void setLatefeeStart(String latefeeStart) {
        this.latefeeStart = latefeeStart;
    }

    public BigDecimal getLatefeeAmount() {
        return latefeeAmount == null ? BigDecimal.ZERO : PriceUtil.div100(latefeeAmount);
    }

    public void setLatefeeAmount(Long latefeeAmount) {
        this.latefeeAmount = latefeeAmount;
    }

    public List<AlterRoomHasFeeItem> getHasFeeItemList() {
        return hasFeeItemList;
    }

    public void setHasFeeItemList(List<AlterRoomHasFeeItem> hasFeeItemList) {
        this.hasFeeItemList = hasFeeItemList;
    }
}
