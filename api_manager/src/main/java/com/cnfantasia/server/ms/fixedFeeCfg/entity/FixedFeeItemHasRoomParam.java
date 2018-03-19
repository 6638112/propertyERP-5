package com.cnfantasia.server.ms.fixedFeeCfg.entity;

import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

import java.math.BigInteger;

/**
 * @className: FixedFeeItemHasRoomParam
 * @date: 2017-12-19 14:22
 * @author: yanghua
 * @description:()
 */
public class FixedFeeItemHasRoomParam extends FixedFeeItemHasRoom {
    public BigInteger getFixedFeeItemId() {
        return fixedFeeItemId;
    }

    public void setFixedFeeItemId(BigInteger fixedFeeItemId) {
        this.fixedFeeItemId = fixedFeeItemId;
    }

    private BigInteger fixedFeeItemId;

}
