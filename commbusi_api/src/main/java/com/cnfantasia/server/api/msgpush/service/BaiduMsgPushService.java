/**
 * Filename:    BaiduMsgPushService.java
 * @version:    1.0
 * Create at:   2014年9月22日 上午3:11:54
 * Description:
 *
 * Modification History:
 * Date        Author      Version     Description
 * -----------------------------------------------------------------
 * 2014年9月22日    shiyl      1.0         1.0 Version
 */
package com.cnfantasia.server.api.msgpush.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.cnfantasia.server.common.utils.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.push.auth.PushKeyPair;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.dao.IMsgpushDao;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushConfigEntity;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushResponse;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.entity.PreMonthDiscountInfoEntity;
import com.cnfantasia.server.api.msgpush.util.BaiduSDKPushUtil;
import com.cnfantasia.server.api.msgpush.util.BaidupushUtil;
import com.cnfantasia.server.api.msgpush.util.DataConvertUtil;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.EnumFctry;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;
import com.cnfantasia.server.domainbase.userPushInfo.service.IUserPushInfoBaseService;

/**
 * Filename: BaiduMsgPushService.java
 *
 * @version: 1.0.0 Create at: 2014年9月22日 上午3:11:54 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月22日 shiyl 1.0 1.0 Version
 */
public class BaiduMsgPushService implements IMsgpushService {
    public static void main(final String[] args) {
        final String s = "jiefangqu.living/.act.MyDarwListAct;i.month=8;i.year=2014;end";
        final int lastIndex = s.lastIndexOf("end");
        System.out.println(s.substring(0, lastIndex) + "a=1;b=2;" + "end");
    }

    private final Log logger = LogFactory.getLog(getClass());
    // /**待推送的消息列表*/
    // private static List<BaiduMessageFormat> message2SendList = new
    // Vector<BaiduMessageFormat>();

    private ISysParamParser baiduPushParamParser;

    private ISysParamParser newPushParamParser;

    private ISysParamParser baiduPushParamMasterAndroidParser;

    private ISysParamParser baiduPushParamMasterIOSParser;
    private ICommonDeviceService commonDeviceService;

    private IUserPushInfoBaseService userPushInfoBaseService;

    private IUuidManager uuidManager;

    private IDualDao dualDao;

    private IUserHasTMessageBaseDao userHasTMessageBaseDao;

    protected IMsgpushDao msgpushDao;

    protected ISysParamManager sysParamManager;

    private IMessageBaseService messageBaseService;

    private IMessageParameterBaseService messageParameterBaseService;

    @Override
    public Integer addMessage2Pool(final CommUserDataEntity commUserData, final BigInteger messageId, final String expiryTime) {
        final BigInteger userId = commUserData.getUserId();
        final Integer userType = commUserData.getUserType();
        final BigInteger defaultRoomId = commUserData.getDefaultRoomId();
        final BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message);
        final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
        tmpUserHasTMessage.setId(toAddId);
        tmpUserHasTMessage.setTryFailedCount(0L);
        tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
        tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
        tmpUserHasTMessage.settMessageFId(messageId);
        tmpUserHasTMessage.settUserFId(userId);
        tmpUserHasTMessage.setUserType(userType);
        tmpUserHasTMessage.setExpiryTime(expiryTime);
        tmpUserHasTMessage.setUserRoomId(defaultRoomId);
        userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
        return pushMessageImmediately(toAddId);
    }

    @Override
    public Integer addMessage2Pool(final CommUserDataEntity commUserData, final List<BigInteger> messageIdList, final String expiryTime) {
        final BigInteger userId = commUserData.getUserId();
        final Integer userType = commUserData.getUserType();
        final BigInteger defaultRoomId = commUserData.getDefaultRoomId();
        final List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, messageIdList.size());
        final List<UserHasTMessage> batchAddEntityList = new ArrayList<UserHasTMessage>();
        for (int i = 0; i < messageIdList.size(); i++) {
            final BigInteger toAddId = toAddIdList.get(i);
            final BigInteger messageId = messageIdList.get(i);
            final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
            tmpUserHasTMessage.setId(toAddId);
            tmpUserHasTMessage.setTryFailedCount(0L);
            tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
            tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
            tmpUserHasTMessage.settMessageFId(messageId);
            tmpUserHasTMessage.settUserFId(userId);
            tmpUserHasTMessage.setUserType(userType);
            tmpUserHasTMessage.setExpiryTime(expiryTime);
            tmpUserHasTMessage.setUserRoomId(defaultRoomId);
            batchAddEntityList.add(tmpUserHasTMessage);
        }
        return userHasTMessageBaseDao.insertUserHasTMessageBatch(batchAddEntityList);
    }

    @Override
    public Integer addMessage2Pool(final CommUserDataEntity commUserData, final Message toAddMessage, final String expiryTime,
            final List<MessageParameter> paramList) {
        BigInteger messageId = toAddMessage.getId();
        if (messageId == null) {
            messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
            toAddMessage.setId(messageId);
        }
        messageBaseService.insertMessage(toAddMessage);
        if (paramList != null && paramList.size() > 0) {
            messageParameterBaseService.insertMessageParameterBatch(paramList);
        }
        return addMessage2Pool(commUserData, messageId, expiryTime);
    }

    @Override
    public Integer addMessage2Pool(final List<CommUserDataEntity> commUserDataList, final BigInteger messageId, final String expiryTime) {
        final List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, commUserDataList.size());
        final List<UserHasTMessage> batchAddEntityList = new ArrayList<UserHasTMessage>();
        for (int i = 0; i < commUserDataList.size(); i++) {
            final CommUserDataEntity commUserData = commUserDataList.get(i);
            final BigInteger userId = commUserData.getUserId();
            final Integer userType = commUserData.getUserType();
            final BigInteger defaultRoomId = commUserData.getDefaultRoomId();
            final BigInteger toAddId = toAddIdList.get(i);
            final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
            tmpUserHasTMessage.setId(toAddId);
            tmpUserHasTMessage.setTryFailedCount(0L);
            tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
            tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
            tmpUserHasTMessage.settMessageFId(messageId);
            tmpUserHasTMessage.settUserFId(userId);
            tmpUserHasTMessage.setUserType(userType);
            tmpUserHasTMessage.setExpiryTime(expiryTime);
            tmpUserHasTMessage.setUserRoomId(defaultRoomId);
            batchAddEntityList.add(tmpUserHasTMessage);
        }
        return userHasTMessageBaseDao.insertUserHasTMessageBatch(batchAddEntityList);
        // return pushMessageImmediately(toAddIdList);
    }

    @Override
    public Integer addMessage2Pool(final List<CommUserDataEntity> commUserDataList, final Message toAddMessage, final String expiryTime,
            final List<MessageParameter> paramList) {
        BigInteger messageId = toAddMessage.getId();
        if (messageId == null) {
            messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
            toAddMessage.setId(messageId);
        }
        messageBaseService.insertMessage(toAddMessage);
        if (paramList != null && paramList.size() > 0) {
            messageParameterBaseService.insertMessageParameterBatch(paramList);
        }
        return addMessage2Pool(commUserDataList, messageId, expiryTime);
    }

    private void addPrizeDiscountData(final List<PreMonthDiscountInfoEntity> preMonthListSign, final boolean isSign) {
        final List<PreMonthDiscountInfoEntity> preMonthList = preMonthListSign;
        if (preMonthList != null && preMonthList.size() > 0) {
            final String nowTime = dualDao.getNowTime();
            final List<UserHasTMessage> userHasTMessageAddList = new ArrayList<UserHasTMessage>();
            final List<MessageParameter> messageParameterAddList = new ArrayList<MessageParameter>();
            final Message tmpMessage = new Message();
            final String month = preMonthList.get(0).getMonth();
            final String year = preMonthList.get(0).getYear();
            final BigInteger messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
            {
                if (isSign) {// 已签约
                    tmpMessage.setContent("快回解放区用折扣缴费吧~");
                } else {
                    tmpMessage.setContent("快回解放区用折扣兑粮票吧~");
                }
                tmpMessage.setId(messageId);
                tmpMessage.setMsggroupFid(null);
                tmpMessage.setPicUrl(null);
                tmpMessage.setTime(nowTime);
                tmpMessage.setTitle("折扣未使用提醒");
                tmpMessage.setType(NoticeDict.Message_Type.Discount);
                final List<BigInteger> messageParameterAddIdLit = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter, 2);
                {
                    final MessageParameter messageParameter = new MessageParameter();
                    messageParameter.setId(messageParameterAddIdLit.get(0));
                    messageParameter.setKey("month");
                    messageParameter.settMessageFId(messageId);
                    messageParameter.setValue(month);
                    messageParameterAddList.add(messageParameter);
                }
                {
                    final MessageParameter messageParameter = new MessageParameter();
                    messageParameter.setId(messageParameterAddIdLit.get(1));
                    messageParameter.setKey("year");
                    messageParameter.settMessageFId(messageId);
                    messageParameter.setValue(year);
                    messageParameterAddList.add(messageParameter);
                }
                messageBaseService.insertMessage(tmpMessage);
                messageParameterBaseService.insertMessageParameterBatch(messageParameterAddList);
            }
            // 批量新增用户消息关系
            if (preMonthList != null && preMonthList.size() > 0) {// 新增用户消息关系
                final List<BigInteger> addIdsList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, preMonthList.size());
                for (int i = 0; i < preMonthList.size(); i++) {
                    final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
                    tmpUserHasTMessage.setId(addIdsList.get(i));
                    tmpUserHasTMessage.setTryFailedCount(0L);
                    tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
                    tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
                    tmpUserHasTMessage.settMessageFId(messageId);
                    tmpUserHasTMessage.settUserFId(preMonthList.get(i).getUserId());
                    tmpUserHasTMessage.setUserRoomId(preMonthList.get(i).getDefaultRoomId());
                    tmpUserHasTMessage.setUserType(LoginDict.UserRegistOrTmp.REGIST_USER);// 已注册的用户
                    userHasTMessageAddList.add(tmpUserHasTMessage);
                }
                userHasTMessageBaseDao.insertUserHasTMessageBatch(userHasTMessageAddList);
            }
        }
    }

    @Override
    public void autoSaveUnusedPrizeDiscountList() {
        // for(int i=0;i<5;i++){
        // System.out.println(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
        // }
        logger.info("autoSaveUnusedPrizeDiscountList start.");
        final List<PreMonthDiscountInfoEntity> preMonthList = msgpushDao.selectUnusedPrizeDiscountList();
        final List<PreMonthDiscountInfoEntity> preMonthListSign = new ArrayList<PreMonthDiscountInfoEntity>();
        final List<PreMonthDiscountInfoEntity> preMonthListUnSign = new ArrayList<PreMonthDiscountInfoEntity>();
        if (preMonthList != null && preMonthList.size() > 0) {
            for (final PreMonthDiscountInfoEntity tmpData : preMonthList) {
                if (tmpData.isSign()) {
                    preMonthListSign.add(tmpData);
                } else {
                    preMonthListUnSign.add(tmpData);
                }
            }
        }
        addPrizeDiscountData(preMonthListSign, true);
        addPrizeDiscountData(preMonthListUnSign, false);
        logger.info("autoSaveUnusedPrizeDiscountList end.");
    }

    @Override
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
        // if (null != contentType &&
        // (NoticeDict.Message_Type.Cookbook_List_changed.equals(contentType) ||
        // NoticeDict.Message_Type.You_have_a_new_msg.equals(contentType)
        // || NoticeDict.Message_Type.Property.equals(contentType) ||
        // NoticeDict.Message_Type.Car_bill_expire_msg.equals(contentType)
        // )) {
        // canPush = true;
        // }
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

    @Override
    public List<BaiduMessageFormat> getSendList() {
        final List<MessagepushEntity> tmpList = msgpushDao.selectSendListSignal();
        List<BaiduMessageFormat> resList = new Vector<BaiduMessageFormat>();
        resList = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(tmpList);
        return resList;
    }

    @Override
    public String pushMessage(final String msg_keys, final String messageContent, final String push_type, final String device_type,
            final String message_type, final String tag, final String deploy_status) {
        String res = null;
        // 查询当前用户对应的推送渠道信息
        final Long subChannelId = HeaderManager.getSubChannelIdLong();
        final UserIdType userIdType = commonDeviceService.getUserIdType();
        if (userIdType != null && userIdType.getUserId() != null && userIdType.getUserType() != null) {
            final UserPushInfo userPushInfoQry = new UserPushInfo();
            userPushInfoQry.setUserId(userIdType.getUserId());
            userPushInfoQry.setUserType(userIdType.getUserType());
            userPushInfoQry.settChannelSubFId(subChannelId);
            final List<UserPushInfo> tmpUserPushInfoList = userPushInfoBaseService.getUserPushInfoByCondition(MapConverter.toMap(userPushInfoQry));
            if (tmpUserPushInfoList != null && tmpUserPushInfoList.size() > 0) {
                final UserPushInfo userPushInfo = tmpUserPushInfoList.get(0);
                final String baiduChannelId = userPushInfo.getChannelBd();
                final String baiduUserId = userPushInfo.getBaiduUserId();
                res = pushMessage(msg_keys, messageContent, baiduChannelId, baiduUserId, push_type, device_type, message_type, tag, deploy_status);
                return res;
            } else {
                throw new BusinessRuntimeException("BaiduMsgPush.userPushInfo.empty");
            }
        } else {
            throw new BusinessRuntimeException("BaiduMsgPush.getUserIdType.failed");
        }
    }

    @Override
    public String pushMessage(final String msg_keys, final String messageContent, final String baiduChannelId, final String baiduUserId,
            final String push_type, final String device_type, final String message_type, final String tag, final String deploy_status) {
        return pushMessage(msg_keys, messageContent, baiduChannelId, baiduUserId, push_type, device_type, message_type, tag, deploy_status, null);
    }

    @Override
    public String pushMessage(final String msg_keys, final String messageContent, final String baiduChannelId, final String baiduUserId,
            final String push_type, final String device_type, final String message_type, final String tag, final String deploy_status,
            final Long messageType) {
        // 查询推送的配置信息
        final BaiduPushConfigEntity baiduPushConfigEntity = baiduPushParamParser.parseParamValue();// 解放工app
        // ios配置
        final BaiduPushConfigEntity newPushConfigEntity = newPushParamParser.parseParamValue();// 解放区app
        // android配置
        final BaiduPushConfigEntity pushParamMasterAndroidConfigEntity = baiduPushParamMasterAndroidParser.parseParamValue();
        final BaiduPushConfigEntity pushParamMasterIOSConfigEntity = baiduPushParamMasterIOSParser.parseParamValue();

        String apiUrl = null;
        String apikey = null;
        String secret_key = null;
        // syl-upd 调整推送配置
        logger.debug("pushMessage:" + device_type);
        if (MsgpushDict.BaiDu_device_type.iOS.equals(device_type)) {// ios保持之前的推送配置
            if (NoticeDict.Message_Type.PropertyRepair.equals(messageType) || NoticeDict.Message_Type.Dredge.equals(messageType)) {
                // 师傅端推送
                apiUrl = pushParamMasterIOSConfigEntity.getApiUrl();
                apikey = pushParamMasterIOSConfigEntity.getApiKey();
                secret_key = pushParamMasterIOSConfigEntity.getSecretKey();
            } else {
                apiUrl = baiduPushConfigEntity.getApiUrl();
                apikey = baiduPushConfigEntity.getApiKey();
                secret_key = baiduPushConfigEntity.getSecretKey();
            }
        } else {// 安卓使用新的配置
            if (NoticeDict.Message_Type.PropertyRepair.equals(messageType) || NoticeDict.Message_Type.Dredge.equals(messageType)) {
                // 师傅端推送
                apiUrl = pushParamMasterAndroidConfigEntity.getApiUrl();
                apikey = pushParamMasterAndroidConfigEntity.getApiKey();
                secret_key = pushParamMasterAndroidConfigEntity.getSecretKey();
            } else {
                apiUrl = newPushConfigEntity.getApiUrl();
                apikey = newPushConfigEntity.getApiKey();
                secret_key = newPushConfigEntity.getSecretKey();
            }
        }
        final Long timestamp = DateUtil.time2Linux(DateUtil.timeToLong(dualDao.getNowTime()));
        String res = null;
        try {
            final Long expires = null;
            res = BaidupushUtil.pushMessage(apiUrl, apikey, secret_key, msg_keys, messageContent, baiduChannelId, baiduUserId, push_type, device_type,
                    message_type, tag, timestamp, expires, deploy_status);
        } catch (final Exception e) {
            logger.info("BaiduMsgPushService pushMessage cause exception,error message is " + e.getMessage(), e);
            throw new BusinessRuntimeException("BaiduMsgPush.pushMessage.failed", e);
        }
        return res;
    }

    @Override
    public Integer pushMessageImmediately(final BigInteger userHasMsgId) {
        Integer resCount = null;
        final MessagepushEntity messagepushEntity = msgpushDao.selectToSendMsgById(userHasMsgId);
        final BaiduMessageFormat tmpBaiduMessageFormat = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(messagepushEntity);
        if (tmpBaiduMessageFormat != null) {
            final List<BaiduMessageFormat> toSendList = new ArrayList<BaiduMessageFormat>();
            toSendList.add(tmpBaiduMessageFormat);
            resCount = sendSignalMsgBatch(toSendList);
        }
        return resCount;
    }

    @Override
    public Integer pushMessageImmediately(final List<BigInteger> userHasMsgIdList) {
        Integer resCount = null;
        final List<MessagepushEntity> messagepushEntityList = msgpushDao.selectToSendMsgListById(userHasMsgIdList);
        final List<BaiduMessageFormat> tmpBaiduMessageFormatList = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(messagepushEntityList);
        if (tmpBaiduMessageFormatList != null && tmpBaiduMessageFormatList.size() > 0) {
            resCount = sendSignalMsgBatch(tmpBaiduMessageFormatList);
        }
        return resCount;
    }

    // @Override
    // public void refreshPushClientInfo(Long subChannelId,BigInteger
    // userId,Integer userType,String baiduUserId, String baiduChannelId) {
    // if (userId != null && userType != null) {
    // msgpushDao.removeHistoryBindInfo(subChannelId, userId, userType,
    // baiduUserId, baiduChannelId);//移除历史绑定信息
    //// {//删除当前设备之前绑定的用户信息
    //// UserPushInfo userPushInfoQry = new UserPushInfo();
    //// userPushInfoQry.setBaiduUserId(baiduUserId);
    //// userPushInfoQry.setBaiduChannelId(baiduChannelId);
    //// userPushInfoQry.settChannelSubFId(subChannelId);
    //// List<UserPushInfo> tmpUserPushInfoList =
    // userPushInfoBaseService.getUserPushInfoByCondition(MapConverter
    //// .toMap(userPushInfoQry));
    ////
    //// if (tmpUserPushInfoList != null && tmpUserPushInfoList.size() > 0) {
    //// List<BigInteger> toDelIds = new ArrayList<BigInteger>();
    //// for (UserPushInfo tmpUserPushInfo : tmpUserPushInfoList) {
    //// toDelIds.add(tmpUserPushInfo.getId());
    //// }
    //// userPushInfoBaseService.deleteUserPushInfoLogicBatch(toDelIds);//
    // 存在则逻辑删除
    //// }
    //// }
    //
    //// {//删除用户之前绑定的设备推送信息
    //// UserPushInfo userPushInfoQry = new UserPushInfo();
    //// userPushInfoQry.setUserId(userId);
    //// userPushInfoQry.setUserType(userType);
    //// userPushInfoQry.settChannelSubFId(subChannelId);
    //// List<UserPushInfo> tmpUserPushInfoList =
    // userPushInfoBaseService.getUserPushInfoByCondition(MapConverter
    //// .toMap(userPushInfoQry));
    //// if (tmpUserPushInfoList != null && tmpUserPushInfoList.size() > 0) {
    //// List<BigInteger> toDelIds = new ArrayList<BigInteger>();
    //// for (UserPushInfo tmpUserPushInfo : tmpUserPushInfoList) {
    //// toDelIds.add(tmpUserPushInfo.getId());
    //// }
    //// userPushInfoBaseService.deleteUserPushInfoLogicBatch(toDelIds);//
    // 存在则逻辑删除
    //// }
    //// }
    // // 新增新记录
    // {
    // UserPushInfo userPushInfoAdd = new UserPushInfo();
    // BigInteger addId =
    // uuidManager.getNextUuidBigInteger(SEQConstants.t_user_push_info);
    // userPushInfoAdd.setId(addId);
    // userPushInfoAdd.setBaiduChannelId(baiduChannelId);
    // userPushInfoAdd.setBaiduUserId(baiduUserId);
    // userPushInfoAdd.settChannelSubFId(subChannelId);
    // userPushInfoAdd.setUserId(userId);
    // userPushInfoAdd.setUserType(userType);
    // int res = userPushInfoBaseService.insertUserPushInfo(userPushInfoAdd);
    // if (res <= 0) {
    // throw new
    // BusinessRuntimeException("BaiduMsgPush.refreshPushClientInfo.insertUserPushInfo.failed");
    // }
    // }
    // } else {
    // throw new BusinessRuntimeException("BaiduMsgPush.getUserIdType.failed");
    // }
    // }

    @Override
    public void refreshPushClientInfo(final Long subChannelId, final BigInteger userId, final Integer userType, final String appVersion,
            final String channelid, final int channeltype, String userAgent) {
        final EnumPushChannel epc = EnumFctry.ToEnum(EnumPushChannel.class, channeltype);
        // 1.根据用户查询列表
        // userId相等的所有upi
        final List<UserPushInfo> upis_user = msgpushDao.selectUserPushInfoByUser(subChannelId, userId, userType);
        // 2.根据配置查询列表
        // channelid相等但是userId不等的所有upi
        final List<UserPushInfo> upis_channel = msgpushDao.selectUserPushInfoByChannel(subChannelId, channelid, epc.getColumnName(), upis_user);

        // 删除旧的channelid
        if (null != upis_channel && 0 < upis_channel.size()) {
            for (final UserPushInfo upi : upis_channel) {
                epc.updateId(upi, null);
            }
            msgpushDao.updateChannelid(upis_channel, epc.getColumnName(), null);
        } else {
        }

        // 更新该用户的channelid
        if (null != upis_user && 0 < upis_user.size()) {
            boolean changed = false;
            for (final UserPushInfo upiu : upis_user) {
                changed |= epc.updateId(upiu, channelid);
                upiu.setAppVersion(appVersion);
                upiu.setUserAgent(userAgent);
                upiu.setSys0UpdTime(DateUtils.getCurrentDate());
                changed = true;
            }
            if (changed) {
                msgpushDao.updateUserPushInfoBatch(upis_user);
            } else {
            }
        } else {
            // 新用户
            final UserPushInfo upi_new = new UserPushInfo();
            final BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_push_info);
            upi_new.setId(addId);
            epc.updateId(upi_new, channelid);
            upi_new.settChannelSubFId(subChannelId);
            upi_new.setUserId(userId);
            upi_new.setUserType(userType);
            upi_new.setAppVersion(appVersion);
            upi_new.setUserAgent(userAgent);
            final int res = userPushInfoBaseService.insertUserPushInfo(upi_new);
            if (res <= 0) {
                throw new BusinessRuntimeException("BaiduMsgPush.refreshPushClientInfo.insertUserPushInfo.failed");
            }
        }
    }

    @Override
    public void refreshPushClientInfo(final Long subChannelId, final BigInteger userId, final Integer userType, final String baiduUserId,
            final String baiduChannelId, final String appVersion) {
        logger.debug("BaiduMsgPushService.refreshPushClientInfo params, subChannelId is:" + subChannelId + ",userId is:" + userId + ",userType is:"
                + userType + ",baiduUserId is:" + baiduUserId + ",baiduChannelId is:" + baiduChannelId);
        if (userId == null || userType == null) {
            throw new BusinessRuntimeException("BaiduMsgPush.getUserIdType.failed");
        }
        if (subChannelId == null || baiduUserId == null || baiduChannelId == null) {
            throw new BusinessRuntimeException("BaiduMsgPush.baiduConfig.failed");
        }
        // 1.根据用户查询列表
        final List<UserPushInfo> userPushInfoByUserList = msgpushDao.selectUserPushInfoByUser(subChannelId, userId, userType);
        // 2.根据配置查询列表
        final List<UserPushInfo> userPushInfoByBaiduList = msgpushDao.selectUserPushInfoByBaidu(subChannelId, baiduUserId, baiduChannelId);
        // 3.1计算两者Id相同的
        BigInteger currId = null;
        if (userPushInfoByUserList != null && userPushInfoByUserList.size() > 0 && userPushInfoByBaiduList != null
                && userPushInfoByBaiduList.size() > 0) {
            for (final UserPushInfo tmpByUser : userPushInfoByUserList) {
                for (final UserPushInfo tmpByBaidu : userPushInfoByBaiduList) {
                    if (tmpByUser.getId().compareTo(tmpByBaidu.getId()) == 0) {
                        currId = tmpByUser.getId();
                        break;// 取到就停止
                    }
                }
            }
        }
        // 3.2计算待删除的Ids
        final Set<BigInteger> toDelIdsSet = new HashSet<BigInteger>();
        {
            if (userPushInfoByUserList != null && userPushInfoByUserList.size() > 0) {
                for (final UserPushInfo tmpByUser : userPushInfoByUserList) {
                    toDelIdsSet.add(tmpByUser.getId());
                }
            }
            if (userPushInfoByBaiduList != null && userPushInfoByBaiduList.size() > 0) {
                for (final UserPushInfo tmpByBaidu : userPushInfoByBaiduList) {
                    toDelIdsSet.add(tmpByBaidu.getId());
                }
            }
            if (currId != null) {
                toDelIdsSet.remove(currId);
            }
        }
        // 4.删除部分记录
        {
            if (toDelIdsSet != null && toDelIdsSet.size() > 0) {
                final List<BigInteger> toDelIdsList = new ArrayList<BigInteger>();
                toDelIdsList.addAll(toDelIdsSet);
                userPushInfoBaseService.deleteUserPushInfoLogicBatch(toDelIdsList);// 存在则逻辑删除
            }
        }
        // 5.判断是否需要新增
        if (currId == null) {
            final UserPushInfo userPushInfoAdd = new UserPushInfo();
            final BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_push_info);
            userPushInfoAdd.setId(addId);
            userPushInfoAdd.setBaiduChannelId(baiduChannelId);
            userPushInfoAdd.setBaiduUserId(baiduUserId);
            userPushInfoAdd.settChannelSubFId(subChannelId);
            userPushInfoAdd.setUserId(userId);
            userPushInfoAdd.setUserType(userType);
            userPushInfoAdd.setAppVersion(appVersion);
            final int res = userPushInfoBaseService.insertUserPushInfo(userPushInfoAdd);
            if (res <= 0) {
                throw new BusinessRuntimeException("BaiduMsgPush.refreshPushClientInfo.insertUserPushInfo.failed");
            }
        } else {
            final UserPushInfo userPushInfoAdd = new UserPushInfo();
            userPushInfoAdd.setId(currId);
            userPushInfoAdd.setAppVersion(appVersion);
            userPushInfoBaseService.updateUserPushInfo(userPushInfoAdd);
        }

    }

    // @Override
    // public List<BaiduMessageFormat> getSendListOfGroup() {
    // List<MessagePushGroupEntity> tmpList =
    // msgpushDao.selectSendListOfGroup();
    // List<BaiduMessageFormat> resList = new ArrayList<BaiduMessageFormat>();
    // resList = messagePushBaseEntityListConvert(tmpList);
    // return resList;
    // }

    @Override
    public int sendSignalMsgBatch(final List<BaiduMessageFormat> msgList) {
        int successCount = 0;
        for (final BaiduMessageFormat tmpMsg : msgList) {
            logger.debug("start to send  " + JSON.toJSONString(tmpMsg));
            Integer currResStatus = null;
            String resStr = null;
            Boolean isFailed = true;
            try {
                final Long contentType = tmpMsg.getMessageType();
                logger.debug("sendSignalMsgBatch-contentType:" + contentType);
                final boolean canPush = checkCanPush(contentType);
                if (canPush) {
                    final PushKeyPair pushKeyPair = getBaiduPushKeyPair(tmpMsg.getDevice_type(), tmpMsg.getMessageType());
                    final BaiduPushResponse response = BaiduSDKPushUtil.push2SingleDevice(pushKeyPair, tmpMsg);
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
                // changeMessageStatus(tmpMsg.getUserIdType().getUserId(),
                // tmpMsg.getUserIdType().getUserType(),
                // tmpMsg.getMessageId(), tmpMsg.getUserHasMessageId(),
                // currResStatus);
                changeMessageStatus(tmpMsg, currResStatus, resStr, isFailed);
            } catch (final Exception e) {
                logger.info("sendSignalMsgBatch.update msg sendStatus failed,msg is " + e.getMessage(), e);
            }

        }
        return successCount;
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

    public void setCommonDeviceService(final ICommonDeviceService commonDeviceService) {
        this.commonDeviceService = commonDeviceService;
    }

    public void setDualDao(final IDualDao dualDao) {
        this.dualDao = dualDao;
    }

    public void setMessageBaseService(final IMessageBaseService messageBaseService) {
        this.messageBaseService = messageBaseService;
    }

    // @Override
    // public void autoSendTask() {
    // synchronized (this) {
    // logger.debug("Start to execute mesSendTask.");
    // logger.debug("message2SendList catch left size
    // is:"+message2SendList.size());
    // if(message2SendList.size()>0){
    // logger.debug("mesSendTask pre finished.");
    // return;
    // }
    // {//从数据库加载待发送的信息
    // List<BaiduMessageFormat> tmp1 = getSendList();
    // if (tmp1 != null && tmp1.size() > 0) {
    // message2SendList.addAll(tmp1);
    // }
    // }
    // }
    //
    // if (message2SendList != null && message2SendList.size() > 0) {
    // logger.info("autoSendTask pre count is :" + message2SendList.size());
    // int successCount = sendSignalMsgBatch(message2SendList);
    // logger.info("autoSendTask successCount count is :" + successCount);
    // }
    //
    // synchronized (this) {
    // message2SendList.clear();
    // logger.debug("Finished to execute mesSendTask.");
    // }
    //
    // }

    public void setMessageParameterBaseService(final IMessageParameterBaseService messageParameterBaseService) {
        this.messageParameterBaseService = messageParameterBaseService;
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

    // @Override
    // public List<BaiduMessageFormat> getSendListOfGroup() {
    // List<MessagePushGroupEntity> tmpList =
    // msgpushDao.selectSendListOfGroup();
    // List<BaiduMessageFormat> resList = new ArrayList<BaiduMessageFormat>();
    // resList = messagePushBaseEntityListConvert(tmpList);
    // return resList;
    // }

    public void setUserPushInfoBaseService(final IUserPushInfoBaseService userPushInfoBaseService) {
        this.userPushInfoBaseService = userPushInfoBaseService;
    }

    public void setUuidManager(final IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

}
