package com.cnfantasia.server.api.homeMessage.dao;

import com.cnfantasia.server.api.homeMessage.entity.HomeMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.PayBillMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.UserUnReceiveDelivOrderEntity;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IHomeMessageDao
 * @Date: 2017-02-23 14:15
 * @Auther: kangduo
 * @Description: ()
 */
public interface IHomeMessageDao {
    public List<HomeMessageEntity> getHomeMessageListByUserId(Map<String, Object> param);

    public UserUnReceiveDelivOrderEntity getUnReceivedDelivOrderCount(BigInteger deliveryOrderId);

    public List<PayBillMessageEntity> getUnPaidBillMessageList(Map<String, Object> param);

    public BigInteger getGbIdByRoomId(BigInteger roomId);

    public Integer delGroupHomeMsgByGbIds(List<BigInteger> gbIds, String code);

    /**
     * 逻辑删除UserHasTHomeMessage
     * @param userId 用户ID，可不填
     * @param roomId 可不填
     * @param code 必填
     * @return
     */
    public Integer delUserHomeMsgByParam(BigInteger userId, BigInteger roomId, String code, BigInteger resourceId);

    /**
     * 产生公共消息
     * @param messageList
     */
    public void generateGroupMsg(List<GroupHomeMessage> messageList);

    /**
     * 产生个人消息
     * @param message
     */
    public void generateCommonMsg(UserHasHomeMessage message);

    /**
     * 产生个人消息
     * @param message
     */
    public void generateCommonMsg(List<UserHasHomeMessage> messageList, boolean needDelOld);

    public Integer canTransRepairCount(BigInteger userId, BigInteger roomId, Integer canTransDay);

    /**
     * 查询服务中的维修/报修单数
     * @param userId
     * @param dredgeOrRepair 1 维修 2 报修
     * @return
     */
    Integer getDredgeBillInServiceCount(BigInteger userId, Integer dredgeOrRepair);
}
