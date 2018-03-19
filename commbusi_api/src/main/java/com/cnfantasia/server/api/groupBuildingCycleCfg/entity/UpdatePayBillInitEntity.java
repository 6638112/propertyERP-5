package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: UpdatePayBillInitEntity
 * @Date: 2017-08-31 14:56
 * @Auther: yanghua
 * @Description:(更新账单初始化参数)
 */
public class UpdatePayBillInitEntity {
    private BigInteger realRoomId;
    private int type;//1 根据已有的房间收费项金额,2 新增收费项（账单配置中已经包含的收费项）,3新增房间,4收费项删除
    private List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList;

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public List<FixedFeeItemHasRoom> getFixedFeeItemHasRoomList() {
        return fixedFeeItemHasRoomList;
    }

    public void setFixedFeeItemHasRoomList(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList) {
        this.fixedFeeItemHasRoomList = fixedFeeItemHasRoomList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
