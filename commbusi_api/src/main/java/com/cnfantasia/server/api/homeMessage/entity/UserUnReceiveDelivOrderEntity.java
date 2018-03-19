package com.cnfantasia.server.api.homeMessage.entity;

import java.math.BigInteger;

/**
 * @ClassName: UserUnReceiveDelivOrderEntity
 * @Date: 2017-02-27 17:58
 * @Auther: kangduo
 * @Description: ()
 */
public class UserUnReceiveDelivOrderEntity {
    private BigInteger userId;
    private BigInteger roomId;
    private Integer delivOrderCount;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getRoomId() {
        return roomId;
    }

    public void setRoomId(BigInteger roomId) {
        this.roomId = roomId;
    }

    public Integer getDelivOrderCount() {
        return delivOrderCount;
    }

    public void setDelivOrderCount(Integer delivOrderCount) {
        this.delivOrderCount = delivOrderCount;
    }
}
