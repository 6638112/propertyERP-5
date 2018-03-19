package com.cnfantasia.server.api.homeMessage.service;

import com.cnfantasia.server.api.homeMessage.entity.HomeMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.PayBillMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.UserUnReceiveDelivOrderEntity;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IHomeMessageService
 * @Date: 2017-02-23 14:14
 * @Auther: kangduo
 * @Description: ()
 */
public interface IHomeMessageService {
    public List<HomeMessageEntity> getHomeMessageListByUserId(BigInteger userId, BigInteger gbId, boolean isUserAuth, Integer version);

    /**
     * 查询当前运单的用户所有房间的未确认收货运单数
     * @param deliveryOrderId 运单ID
     * @return
     */
    public UserUnReceiveDelivOrderEntity getUnReceivedDelivOrderCount(BigInteger deliveryOrderId);

    public List<PayBillMessageEntity> getUnPaidBillMessageList(BigInteger gbId, BigInteger realRoomId, BigInteger roomId);
    
    public List<PayBillMessageEntity> getUnPaidBillMessageList(BigInteger userId);

    /**
     * 逻辑删除 UserHasHomeMessage 表， 参数 userId, code 必填, roomId 根据业务选填
     * @param messageList
     * @return
     */
    public Integer delUserHomeMsgByParam(List<UserHasHomeMessage> messageList);

    /**
     * 产生公共消息，有过期时间要填过期时间，没则不填。公告ID填至extInfo字段。
     * @param message
     * @param gbIds
     */
    public void generateGroupMsg(GroupHomeMessage message, List<BigInteger> gbIds);

    /**
     * 个人消息产生，房间号根据业务填，部分不填
     * @param message
     */
    public void generateCommonMsg(UserHasHomeMessage message);

    /**
     * 个人消息产生，房间号根据业务填，部分不填
     * @param message
     */
    public void generateCommonMsg(List<UserHasHomeMessage> messageList);
}
