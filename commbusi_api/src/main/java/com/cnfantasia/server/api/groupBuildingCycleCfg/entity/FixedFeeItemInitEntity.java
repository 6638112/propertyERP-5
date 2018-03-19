package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: FixedFeeItemInitEntity
 * @Date: 2017-07-12 16:55
 * @Auther: yanghua
 * @Description:(选择周期固定收费项数据初始化)
 */
public class FixedFeeItemInitEntity {
    /**房间id*/
    private BigInteger realRoomId;
    /**业主id*/
    private BigInteger propertyProprietorFId;

    private List<FixedFeeItemHasRoom> fixedFeeItemHasRooms;

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public List<FixedFeeItemHasRoom> getFixedFeeItemHasRooms() {
        return fixedFeeItemHasRooms;
    }

    public void setFixedFeeItemHasRooms(List<FixedFeeItemHasRoom> fixedFeeItemHasRooms) {
        this.fixedFeeItemHasRooms = fixedFeeItemHasRooms;
    }

    public BigInteger getPropertyProprietorFId() {
        return propertyProprietorFId;
    }

    public void setPropertyProprietorFId(BigInteger propertyProprietorFId) {
        this.propertyProprietorFId = propertyProprietorFId;
    }
}
