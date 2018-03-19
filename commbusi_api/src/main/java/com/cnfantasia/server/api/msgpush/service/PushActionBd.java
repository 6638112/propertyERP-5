package com.cnfantasia.server.api.msgpush.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.auth.PushKeyPair;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.dao.IMsgpushDao;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushConfigEntity;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushResponse;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.util.BaiduSDKPushUtil;
import com.cnfantasia.server.api.msgpush.util.DataConvertUtil;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

public class PushActionBd extends AbsPushAction {

    public static void main(final String[] args) {
        final String s = "jiefangqu.living/.act.MyDarwListAct;i.month=8;i.year=2014;end";
        final int lastIndex = s.lastIndexOf("end");
        System.out.println(s.substring(0, lastIndex) + "a=1;b=2;" + "end");
    }

    // /**待推送的消息列表*/
    // private static List<BaiduMessageFormat> message2SendList = new
    // Vector<BaiduMessageFormat>();

    private ISysParamParser baiduPushParamParser;

    private ISysParamParser newPushParamParser;

    private ISysParamParser baiduPushParamMasterAndroidParser;

    private ISysParamParser baiduPushParamMasterIOSParser;

    private IUuidManager uuidManager;

    private IUserHasTMessageBaseDao userHasTMessageBaseDao;

    protected IMsgpushDao msgpushDao;

    protected ISysParamManager sysParamManager;

    public Integer changeMessageStatus(final BaiduMessageFormat baiduMessageFormat, final Integer sendStatus, final String resCode,
            final Boolean isFailed) {
        if (baiduMessageFormat == null) {
            return 0;
        }
        final BigInteger userHasMessageId = baiduMessageFormat.getUserHasMessageId();

        Integer resCount = null;
        final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
        tmpUserHasTMessage.setSendStatus(sendStatus);
        tmpUserHasTMessage.settUserPushInfoFId(baiduMessageFormat.getUserPushInfoId());// 记录推送时用的推送配置
        if (isFailed) {
            tmpUserHasTMessage.setLastErrorMsg(resCode);
        } else {
            tmpUserHasTMessage.setLastSuccMsg(resCode);
        }
        tmpUserHasTMessage.setSendData(JSON.toJSONString(baiduMessageFormat));

        if (userHasMessageId != null) {
            final UserHasTMessage dbUserHasTMessage = userHasTMessageBaseDao.selectUserHasTMessageBySeqId(userHasMessageId);
            if (dbUserHasTMessage != null) {
                tmpUserHasTMessage.setId(userHasMessageId);
                if (isFailed) {
                    final long failedTotalCount = (dbUserHasTMessage.getTryFailedCount() == null ? 0 : dbUserHasTMessage.getTryFailedCount()) + 1;
                    tmpUserHasTMessage.setTryFailedCount(failedTotalCount);
                }
                resCount = userHasTMessageBaseDao.updateUserHasTMessage(tmpUserHasTMessage);
            }
        } else {
            final BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message);
            tmpUserHasTMessage.setId(addId);
            tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
            tmpUserHasTMessage.settMessageFId(baiduMessageFormat.getMessageId());
            tmpUserHasTMessage.settUserFId(baiduMessageFormat.getUserIdType().getUserId());
            tmpUserHasTMessage.setUserType(baiduMessageFormat.getUserIdType().getUserType());
            tmpUserHasTMessage.setTryFailedCount(isFailed ? 1L : 0L);
            resCount = userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
        }
        if (resCount == null || resCount <= 0) {
            throw new BusinessRuntimeException("BaiduMsgPush.changeMessageStatus.failed");
        }
        return resCount;
    }

    private boolean checkCanPush(final Long contentType) {
        boolean canPush = false;
        final String msgPushTypesStr = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MSG_PUSH_TYPES);
        if (!StringUtils.isEmpty(msgPushTypesStr)) {
            final String[] msgPushTypes = msgPushTypesStr.trim().split(",");
            if (msgPushTypes != null && msgPushTypes.length > 0) {
                for (final String tmpTypeStr : msgPushTypes) {
                    if (Long.valueOf(tmpTypeStr).compareTo(contentType) == 0) {
                        canPush = true;
                        break;
                    }
                }
            }
        }
        return canPush;
    }

    private PushKeyPair getBaiduPushKeyPair(final String device_type, final Long messageType) {
        final BaiduPushConfigEntity iOSApp = baiduPushParamParser.parseParamValue();
        final BaiduPushConfigEntity androidApp = newPushParamParser.parseParamValue();
        final BaiduPushConfigEntity androidMaster = baiduPushParamMasterAndroidParser.parseParamValue();
        final BaiduPushConfigEntity iOSMaster = baiduPushParamMasterIOSParser.parseParamValue();

        BaiduPushConfigEntity desConfig = null;
        if (MsgpushDict.BaiDu_device_type.iOS.equals(device_type)) {
            desConfig = messageType >= 1000 ? iOSMaster : iOSApp;
        } else {
            desConfig = messageType >= 1000 ? androidMaster : androidApp;
        }
        return new PushKeyPair(desConfig.getApiKey(), desConfig.getSecretKey());
    }

    public List<BaiduMessageFormat> getSendList() {
        final List<MessagepushEntity> tmpList = msgpushDao.selectSendListSignal();
        List<BaiduMessageFormat> resList = new Vector<BaiduMessageFormat>();
        resList = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(tmpList);
        return resList;
    }

    @Override
    public MessagepushEntity push(MessagepushEntity entity) {
        final BaiduMessageFormat baiduMessageFormat = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(entity);
        // 发送,锁超时处理,标识结果状态,释放锁
        sendSignalMsg(baiduMessageFormat);
        return null;
    }

    public boolean sendSignalMsg(final BaiduMessageFormat msg) {
        int successCount = 0;
        logger.debug("start to send  " + JSON.toJSONString(msg));
        Integer currResStatus = null;
        String resStr = null;
        Boolean isFailed = true;
        try {
            final Long contentType = msg.getMessageType();
            logger.debug("sendSignalMsgBatch-contentType:" + contentType);
            final boolean canPush = checkCanPush(contentType);
            if (canPush) {
                final PushKeyPair pushKeyPair = getBaiduPushKeyPair(msg.getDevice_type(), msg.getMessageType());
                final BaiduPushResponse response = BaiduSDKPushUtil.push2SingleDevice(pushKeyPair, msg);
                resStr = JSON.toJSONString(response);
                if (response.isSuccess()) {
                    currResStatus = NoticeDict.Message_SendStatus.SendSuccess;// 标记为成功
                    isFailed = false;
                    successCount++;
                    logger.info("sendSignalMsgBatch.success,resStr is " + resStr);
                } else {
                    currResStatus = NoticeDict.Message_SendStatus.SendFailed;// 标记为失败
                    logger.info("sendSignalMsgBatch.failed with error code,resStr is " + resStr);
                }
            } else {
                currResStatus = NoticeDict.Message_SendStatus.SendFailed;// 标记为失败
                resStr = "jfq not open";
                logger.info("sendSignalMsgBatch.failed with error code,resStr is " + resStr);
            }

        } catch (final Exception e) {
            currResStatus = NoticeDict.Message_SendStatus.SendFailed;// 标记为失败
            logger.info("sendSignalMsgBatch.failed,resStr is " + resStr, e);
            logger.error(e.getMessage(), e);
        }

        try {
            // changeMessageStatus(msgList.getUserIdType().getUserId(),
            // msgList.getUserIdType().getUserType(),
            // msgList.getMessageId(), msgList.getUserHasMessageId(),
            // currResStatus);
            changeMessageStatus(msg, currResStatus, resStr, isFailed);
        } catch (final Exception e) {
            logger.info("sendSignalMsgBatch.update msg sendStatus failed,msg is " + e.getMessage(), e);
        }
        return successCount > 0;
    }

    public void setBaiduPushParamMasterAndroidParser(final ISysParamParser baiduPushParamMasterAndroidParser) {
        this.baiduPushParamMasterAndroidParser = baiduPushParamMasterAndroidParser;
    }

    public void setBaiduPushParamMasterIOSParser(final ISysParamParser baiduPushParamMasterIOSParser) {
        this.baiduPushParamMasterIOSParser = baiduPushParamMasterIOSParser;
    }

    public void setBaiduPushParamParser(final ISysParamParser baiduPushParamParser) {
        this.baiduPushParamParser = baiduPushParamParser;
    }

    public void setMsgpushDao(final IMsgpushDao msgpushDao) {
        this.msgpushDao = msgpushDao;
    }

    public void setNewPushParamParser(final ISysParamParser newPushParamParser) {
        this.newPushParamParser = newPushParamParser;
    }

    public void setSysParamManager(final ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    public void setUserHasTMessageBaseDao(final IUserHasTMessageBaseDao userHasTMessageBaseDao) {
        this.userHasTMessageBaseDao = userHasTMessageBaseDao;
    }

    public void setUuidManager(final IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }
}
