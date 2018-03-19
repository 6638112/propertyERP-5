package com.cnfantasia.server.ms.flashDealActivity;

import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealRemindEntity;
import com.cnfantasia.server.api.flashDealActivity.service.FlashDealActivityService;
import com.cnfantasia.server.api.flashDealActivity.service.IFlashDealActivityService;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.flashDealRemind.entity.FlashDealRemind;
import com.cnfantasia.server.domainbase.flashDealRemind.service.FlashDealRemindBaseService;
import com.cnfantasia.server.domainbase.flashDealRemind.service.IFlashDealRemindBaseService;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.service.IUserHasTMessageBaseService;
import com.cnfantasia.server.ms.revenue.task.ISynTask;
import com.cnfantasia.server.domainbase.message.entity.Message;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FlashDealActivityRemindSynTask
 * @Date: 2016-12-09 17:31
 * @Auther: yanghua
 * @Description:(幸运购活动提醒)
 */
public class FlashDealActivityRemindSynTask implements ISynTask {
    @Resource
    private IFlashDealActivityService flashDealActivityService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IMessageBaseService messageBaseService;
    @Resource
    private IUserHasTMessageBaseService userHasTMessageBaseService;
    @Resource
    private IMessageParameterBaseService messageParameterBaseService;
    @Resource
    private IFlashDealRemindBaseService flashDealRemindBaseService;

    @Override
    public Integer execTask() {
        List<FlashDealRemindEntity> flashDealRemindList = flashDealActivityService.getFlashDealRemindList();
        if(!DataUtil.isEmpty(flashDealRemindList)) {
            //推送
            pushMsg(flashDealRemindList);
        }
        return null;
    }

    private void pushMsg(List<FlashDealRemindEntity> flashDealRemindList) {
        for (FlashDealRemindEntity flashDealRemind : flashDealRemindList){
            String pushStr = "解放区“幸运GO”活动马上开始啦，"+flashDealRemind.getTitle()+"只需要幸运！";
            Message sucPush = new Message();
            sucPush.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
            sucPush.setTitle("幸运GO");
            sucPush.setContent(pushStr);
            sucPush.setTime(DateUtils.getCurrentDate());
            sucPush.setIsRelateRoom(0);//消息与门牌无关
            sucPush.setType(NoticeDict.Message_Type.FlashDealRemind);

            UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
            tmpUserHasTMessage.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message));
            tmpUserHasTMessage.setTryFailedCount(0L);
            tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
            tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
            tmpUserHasTMessage.settUserFId(flashDealRemind.getUserId());
            tmpUserHasTMessage.setUserType(1);
            tmpUserHasTMessage.settMessageFId(sucPush.getId());

            //messageParameter
            MessageParameter sucParam = new MessageParameter();
            sucParam.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
            sucParam.settMessageFId(sucPush.getId());
            sucParam.setKey("pushId");
            sucParam.setValue(MsgpushDict.PushId.FlashDealActivity_remind);

            messageBaseService.insertMessage(sucPush);
            messageParameterBaseService.insertMessageParameter(sucParam);
            userHasTMessageBaseService.insertUserHasTMessage(tmpUserHasTMessage);

            FlashDealRemind flashDealRemindBase = flashDealRemindBaseService.getFlashDealRemindBySeqId(flashDealRemind.getRemindId());
            flashDealRemindBase.setRemindTime(DateUtils.getCurrentDate());
            flashDealRemindBase.setRemindStatus(1);//已经提醒
            flashDealRemindBaseService.updateFlashDealRemind(flashDealRemindBase);
        }
    }
}
