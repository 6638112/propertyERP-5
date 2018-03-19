package com.cnfantasia.server.ms.fixedFeeCfg.entity;

import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: RoomFixedFeeItemDetail
 * @Date: 2017-1-19 9:14
 * @Auther: yanghua
 * @Description:(单个房间的收费项详情)
 */
public class RoomFixedFeeItemDetail implements Serializable{
    private static final long serialVersionUID = -5793848921696166790L;
    //小区ID
    private BigInteger gbId;
    //真实房间ID
    private BigInteger realRoomId;
    //数据详情ID
    private BigInteger fixedFeeItemHasRoomDataId;
    //小区名
    private String gbName;
    //楼栋名
    private String bName;
    //单元
    private String unitName;
    //房号
    private String roomNumTail;
    //主体费用列表（列表使用）
    private List<FixedFeeItemHasRoom> hasFeeItemList;
    //费用列表（修改使用）
    private List<FixedFeeItemHasRoomEntity> editFeeItemList;

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

    public List<FixedFeeItemHasRoom> getHasFeeItemList() {
        return hasFeeItemList;
    }

    public void setHasFeeItemList(List<FixedFeeItemHasRoom> hasFeeItemList) {
        this.hasFeeItemList = hasFeeItemList;
    }

    public BigInteger getFixedFeeItemHasRoomDataId() {
        return fixedFeeItemHasRoomDataId;
    }

    public void setFixedFeeItemHasRoomDataId(BigInteger fixedFeeItemHasRoomDataId) {
        this.fixedFeeItemHasRoomDataId = fixedFeeItemHasRoomDataId;
    }

    public List<FixedFeeItemHasRoomEntity> getEditFeeItemList() {
        return editFeeItemList;
    }

    public void setEditFeeItemList(List<FixedFeeItemHasRoomEntity> editFeeItemList) {
        this.editFeeItemList = editFeeItemList;
    }
}
