package com.cnfantasia.server.api.homeMessage.dao;

import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.entity.HomeMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.PayBillMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.UserUnReceiveDelivOrderEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;
import com.cnfantasia.server.domainbase.groupHomeMessage.service.IGroupHomeMessageBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userHasHomeMessage.service.IUserHasHomeMessageBaseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HomeMessageDao
 * @Date: 2017-02-23 14:15
 * @Auther: kangduo
 * @Description: ()
 */
public class HomeMessageDao extends AbstractBaseDao implements IHomeMessageDao {

    private IGroupHomeMessageBaseService groupHomeMessageBaseService;
    public void setGroupHomeMessageBaseService(IGroupHomeMessageBaseService groupHomeMessageBaseService) {
        this.groupHomeMessageBaseService = groupHomeMessageBaseService;
    }

    private IUserHasHomeMessageBaseService userHasHomeMessageBaseService;
    public void setUserHasHomeMessageBaseService(IUserHasHomeMessageBaseService userHasHomeMessageBaseService) {
        this.userHasHomeMessageBaseService = userHasHomeMessageBaseService;
    }

    @Override
    public List<HomeMessageEntity> getHomeMessageListByUserId(Map<String, Object> param) {
        return sqlSession.selectList("homeMessage.getHomeMessageListByUserId", param);
    }

    @Override
    public UserUnReceiveDelivOrderEntity getUnReceivedDelivOrderCount(BigInteger deliveryOrderId) {
        return sqlSession.selectOne("homeMessage.getUnReceivedDelivOrderCount", deliveryOrderId);
    }

    @Override
    public List<PayBillMessageEntity> getUnPaidBillMessageList(Map<String, Object> param) {
        return sqlSession.selectList("homeMessage.getUnPaidBillMessageList", param);
    }

    @Override
    public BigInteger getGbIdByRoomId(BigInteger roomId) {
        return sqlSession.selectOne("homeMessage.getGbIdByRoomId", roomId);
    }

    @Override
    public Integer delGroupHomeMsgByGbIds(List<BigInteger> gbIds, String code) {
        Map<String, Object> param = new HashMap<>();
        int count = 0;
        while (!DataUtil.isEmpty(gbIds)) {
            param.clear();
            param.put("code", code);
            List<BigInteger> batchList = gbIds.subList(0, batchSize < gbIds.size() ? batchSize : gbIds.size());
            param.put("gbIds", batchList);
            count = count + sqlSession.update("homeMessage.delGroupHomeMsgByGbIds", param);
            gbIds.removeAll(batchList);
        }
        return count;
    }

    @Override
    public Integer delUserHomeMsgByParam(BigInteger userId, BigInteger roomId, String code, BigInteger resourceId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("roomId", roomId);
        param.put("code", code);
        param.put("resourceId", resourceId);
        return sqlSession.update("homeMessage.delUserHomeMsgByParam", param);
    }

    @Override
    public void generateGroupMsg(List<GroupHomeMessage> messageList) {
        List<BigInteger> gbIds = new ArrayList<>(messageList.size());
        for (GroupHomeMessage groupHomeMessage : messageList) {
            gbIds.add(groupHomeMessage.getGbId());
        }
        delGroupHomeMsgByGbIds(gbIds, HomeMessageDict.MessageCode.NOTICE_MESSAGE);
        groupHomeMessageBaseService.insertGroupHomeMessageBatch(messageList);
    }

    @Override
    public void generateCommonMsg(UserHasHomeMessage message) {
        delUserHomeMsgByParam(message.getUserId(), message.gettRoomFId(), message.getMessageCode(), null);
        userHasHomeMessageBaseService.insertUserHasHomeMessage(message);
    }

    @Override
    public void generateCommonMsg(List<UserHasHomeMessage> messageList, boolean needDelOld) {
        if (needDelOld) {
            for (UserHasHomeMessage message : messageList) {
                delUserHomeMsgByParam(message.getUserId(), message.gettRoomFId(), message.getMessageCode(), message.getResouceId());
            }
        }
        userHasHomeMessageBaseService.insertUserHasHomeMessageBatch(messageList);
    }

    @Override
    public Integer canTransRepairCount(BigInteger userId, BigInteger roomId, Integer canTransDay) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("roomId", roomId);
        param.put("canTransDay", canTransDay);
        return sqlSession.selectOne("homeMessage.canTransRepairCount", param);
    }

    @Override
    public Integer getDredgeBillInServiceCount(BigInteger userId, Integer dredgeOrRepair) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("dredgeOrRepair", dredgeOrRepair);
        return sqlSession.selectOne("homeMessage.getDredgeBillInServiceCount", param);
    }
}
