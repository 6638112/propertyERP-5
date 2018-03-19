package com.cnfantasia.server.api.msgpush.service;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.service.IUserHasTMessageBaseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsgAddService
 * @Date: 2017-01-22 17:09
 * @Auther: kangduo
 * @Description: (添加推送service)
 */
public class PushAddService implements IPushAddService {
    private IUuidManager uuidManager;
    private IMessageBaseService messageBaseService;
    private IMessageParameterBaseService messageParameterBaseService;
    private IUserHasTMessageBaseService userHasTMessageBaseService;

    @Override
    public Integer addMessage2Pool(BigInteger sourceId, List<CommUserDataEntity> commUserDataList, MsgToAddBasic msgToAddBasic) {
        return addMessage2Pool(sourceId, commUserDataList, msgToAddBasic, DateUtils.getCurrentDate());
    }

    @Override
    public Integer addMessage2Pool(BigInteger sourceId, List<CommUserDataEntity> commUserDataList, MsgToAddBasic msgToAddBasic, String sendTime) {
        //msg
        Message push = new Message();
        push.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
        push.setTitle(msgToAddBasic.getTitle());
        push.setContent(msgToAddBasic.getContent());
        push.setTime(sendTime);
        push.setIsRelateRoom(msgToAddBasic.getIsReleatRoom());//消息与门牌无关
        push.setType(msgToAddBasic.getiOSMsgType());
        push.setSourceId(sourceId);
        messageBaseService.insertMessage(push);

        //msgParameter
        List<MessageParameter> parameterList = new ArrayList<MessageParameter>();
        MessageParameter parameter = new MessageParameter();
        parameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
        parameter.settMessageFId(push.getId());
        parameter.setKey("pushId");
        parameter.setValue(msgToAddBasic.getAndroidPushId());
        parameterList.add(parameter);
        //添加msgId参数
        parameter = new MessageParameter();
        parameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
        parameter.settMessageFId(push.getId());
        parameter.setKey("msgId");
        parameter.setValue(push.getId().toString());
        parameterList.add(parameter);

        if (!DataUtil.isEmpty(msgToAddBasic.getMsgParameters())) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter, msgToAddBasic.getMsgParameters().size());
            int idx = 0;
            for (Map.Entry<String, Object> stringObjectEntry : msgToAddBasic.getMsgParameters().entrySet()) {
                String key = stringObjectEntry.getKey();
                Object value = stringObjectEntry.getValue();

                parameter = new MessageParameter();
                parameter.setId(ids.get(idx++));
                parameter.settMessageFId(push.getId());
                parameter.setKey(key);
                parameter.setValue(value.toString());
                parameterList.add(parameter);
            }
        }
        messageParameterBaseService.insertMessageParameterBatch(parameterList);

        //userHasMessage
        int affect = 0;
        if (!DataUtil.isEmpty(commUserDataList)) {
            List<UserHasTMessage> hasTMessageList = new ArrayList<UserHasTMessage>(commUserDataList.size());
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, commUserDataList.size());
            int idx = 0;
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);
            for (CommUserDataEntity commUserDataEntity : commUserDataList) {
                UserHasTMessage hasTMessage = new UserHasTMessage();
                hasTMessage.setId(ids.get(idx++));
                hasTMessage.setTryFailedCount(0L);
                hasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
                hasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
                hasTMessage.settMessageFId(push.getId());
                hasTMessage.settUserFId(commUserDataEntity.getUserId());
                hasTMessage.setUserType(commUserDataEntity.getUserType());
                hasTMessage.setExpiryTime(DateUtil.formatDay.get().format(c.getTime()));
                hasTMessage.setUserRoomId(commUserDataEntity.getDefaultRoomId());
                hasTMessageList.add(hasTMessage);
            }
            affect = userHasTMessageBaseService.insertUserHasTMessageBatch(hasTMessageList);
        }

        return affect;
    }

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    public void setMessageBaseService(IMessageBaseService messageBaseService) {
        this.messageBaseService = messageBaseService;
    }

    public void setMessageParameterBaseService(IMessageParameterBaseService messageParameterBaseService) {
        this.messageParameterBaseService = messageParameterBaseService;
    }

    public void setUserHasTMessageBaseService(IUserHasTMessageBaseService userHasTMessageBaseService) {
        this.userHasTMessageBaseService = userHasTMessageBaseService;
    }
}
