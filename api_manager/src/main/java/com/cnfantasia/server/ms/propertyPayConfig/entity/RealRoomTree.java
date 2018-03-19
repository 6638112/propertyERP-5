package com.cnfantasia.server.ms.propertyPayConfig.entity;

import java.math.BigInteger;

/**
 * @ClassName: RealRoomTree
 * @Date: 2017-09-25 15:04
 * @Auther: yanghua
 * @Description:(房间信息数据结构)
 */
public class RealRoomTree {
    private BigInteger realRoomId;
    private String realRoomName;

    public BigInteger getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(BigInteger realRoomId) {
        this.realRoomId = realRoomId;
    }

    public String getRealRoomName() {
        return realRoomName;
    }

    public void setRealRoomName(String realRoomName) {
        this.realRoomName = realRoomName;
    }
}
