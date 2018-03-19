package com.cnfantasia.server.api.msgpush.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.msg.app.domain.ShortMsg;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.msgpush.dao.IMsgpushDao;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.util.DataConvertUtil;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.shortUrl.service.IShortUrlService;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * @author wangzhe
 * @date 2017年3月10日
 * @description 推送定时任务
 *
 */
public class MsgPushBean {

    private List<AbsPushAction> actions;

    private final Log logger = LogFactory.getLog(getClass());

    protected IMsgpushDao msgpushDao;

    private IUuidManager uuidManager;
    private IUserHasTMessageBaseDao userHasTMessageBaseDao;
    private IShortUrlService shortUrlService;
    public void setShortUrlService(IShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

   public Integer changeMessageStatus(MessagepushEntity entity) {
    if (entity == null) {
            return 0;
        }
        final BigInteger userHasMessageId = entity.getUserHasTMessage().getId();

        Integer resCount = null;
        final UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
        tmpUserHasTMessage.setSendStatus(entity.getSucceedStatus() == true ? 1 : 0);
        tmpUserHasTMessage.settUserPushInfoFId(entity.getUserPushInfo().getId());// 记录推送时用的推送配置
        if (!entity.getSucceedStatus()) {
            tmpUserHasTMessage.setLastErrorMsg(entity.getSendStatusStr());
        } else {
            tmpUserHasTMessage.setLastSuccMsg(entity.getSendStatusStr());
        }
        tmpUserHasTMessage.setSendData(JSON.toJSONString(entity.getUserHasTMessage().getSendData()));

        //记录推送渠道信息
        tmpUserHasTMessage.setChannel(entity.getUserPushInfo().getUserAgent());//通道
        tmpUserHasTMessage.setThrdMessageId(entity.getThrdMessageId());//第三方消息id


        if (userHasMessageId != null) {
            final UserHasTMessage dbUserHasTMessage = userHasTMessageBaseDao.selectUserHasTMessageBySeqId(userHasMessageId);
            if (dbUserHasTMessage != null) {
                tmpUserHasTMessage.setId(userHasMessageId);
                if (!entity.getSucceedStatus()) {
                    final long failedTotalCount = (dbUserHasTMessage.getTryFailedCount() == null ? 0 : dbUserHasTMessage.getTryFailedCount()) + 1;
                    tmpUserHasTMessage.setTryFailedCount(failedTotalCount);
                }
                resCount = userHasTMessageBaseDao.updateUserHasTMessage(tmpUserHasTMessage);
            }
        } else {
            final BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message);
            tmpUserHasTMessage.setId(addId);
            tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
            tmpUserHasTMessage.settMessageFId(entity.getMessage().getId());
            tmpUserHasTMessage.settUserFId(entity.getUserPushInfo().getUserId());
            tmpUserHasTMessage.setUserType(entity.getUserHasTMessage().getUserType());
            tmpUserHasTMessage.setTryFailedCount(!entity.getSucceedStatus() ? 1L : 0L);
            resCount = userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
        }
        if (resCount == null || resCount <= 0) {
            throw new BusinessRuntimeException("BaiduMsgPush.changeMessageStatus.failed");
        }
        return resCount;
    }

    public List<AbsPushAction> getActions() {
        return actions;
    }

    /**
     * @author wangzhe
     * @date 2017年3月10日
     * @description 可推送消息类型，详询 NoticeDict.Message_Type
     *
     * @return
     */
    private List<Long> getTaskTypeList() {
        final List<Long> resList = new ArrayList<Long>();
        final String typeStr = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.MSG_TASK_TYPES);
        if (!StringUtils.isEmpty(typeStr)) {
            final String[] arrStr = typeStr.trim().split(",");
            for (final String tmpAA : arrStr) {
                resList.add(Long.valueOf(tmpAA));
            }
        }
        // Long messageType = NoticeDict.Message_Type.Property;
        return resList;
    }

    /**
     * @author wangzhe
     * @date 2017年3月10日
     * @description task timer任务
     *
     */
    public void pushTask() {
        int succMsgCount = 0;
        int failedMsgCount = 0;
        logger.debug("Start to execute mesSendTask.");
        final List<Long> messageType = getTaskTypeList();
        logger.debug("MsgpushTask autoSendTask messageType info:" + messageType == null ? null : JSON.toJSONString(messageType));
        if (messageType != null && messageType.size() > 0) {
            final List<BigInteger> msgIdList = msgpushDao.selectToSendMsgIdListNow(messageType);
            logger.debug("MsgpushTask autoSendTask toSendMsgCount is:" + msgIdList == null ? 0 : msgIdList.size());
            if (msgIdList != null && msgIdList.size() > 0) {
                for (final BigInteger msgId : msgIdList) {// 逐个获取消息对应的userhasmsgList
                    final int tmpCount = sendSignalByMsgId(msgId);
                    if (tmpCount > 0) {
                        succMsgCount += 1;
                    } else {
                        failedMsgCount += 1;
                    }
                }
            }
        }
        logger.debug("Finished to execute mesSendTask,succMsgCount is:" + succMsgCount + ",failedMsgCount is:" + failedMsgCount);
    }

    public int sendSignalByMsgId(final BigInteger msgId) {
        int succCount = 0;
        int failedCount = 0;
        logger.debug("MsgpushTask sendSignalByMsgId start,msgId is:" + msgId);
        final List<BigInteger> userHasMsgIdList = msgpushDao.selectToSendUserHasMsgListByMsgId(msgId);
        if (userHasMsgIdList != null && userHasMsgIdList.size() > 0) {
            for (final BigInteger userHasMsgId : userHasMsgIdList) {// 逐个发送
                MessagepushEntity messagepushEntity = msgpushDao.selectToSendMsgById(userHasMsgId);
                boolean tmpCount = sendSignalByUserHasMsgId(messagepushEntity);
                if (tmpCount) {
                    succCount += 1;
                } else {
                    failedCount += 1;
                }
            }
        }
        logger.debug("MsgpushTask sendSignalByMsgId finished,successCount is:" + succCount + ",failedCount is:" + failedCount);
        return succCount;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 60)
    public boolean sendSignalByUserHasMsgId(MessagepushEntity messagepushEntity) {// TODO// 测试继承事务
        boolean isSuccess = true;
        if (messagepushEntity != null) {
            final String version = messagepushEntity.getUserPushInfo().getAppVersion();
            boolean oldversion = false;
            if (!TextUtils.isEmpty(version)) {
                final String tmp = version.replaceAll("\\.", "");
                try {
                    final Integer ver = Integer.parseInt(tmp);
                    oldversion = ver <= 502;
                } catch (final NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                oldversion = true;
            }
            if (!oldversion) {
            	
            	if(messagepushEntity.getUserPushInfo().getAppVersion().equals("5.0.7")
            			|| messagepushEntity.getUserPushInfo().getAppVersion().equals("5.0.8")){//如果是老版本的，pushId要置为0，跳到解放区的首页
            		for(MessageParameter  messageParameter : messagepushEntity.getMessage().getMessageParameterList()) {
                        if("pushId".equals(messageParameter.getKey())) {
                        	messageParameter.setValue("0");
                        	break;
                        }
                    }
            	}
            	
                // 同以前的百度推送一样，组装数据 json格式的
                BaiduMessageFormat baiduMessageFormat = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(messagepushEntity);
                String content = JSON.toJSONString(baiduMessageFormat).replaceAll("\\n|\\t|\\r", "");
                messagepushEntity.getUserHasTMessage().setSendData(content);
                List<MessagepushEntity> changeMessageStatusList = new ArrayList<MessagepushEntity>();
                for (final AbsPushAction action : actions) {
                    MessagepushEntity entity = action.push(messagepushEntity);
                    if(!DataUtil.isEmpty(entity)) {//百度推送在  push的时候进行了状态的更新，返回的entity==null
                        changeMessageStatusList.add(entity);
                    }
                }
                // 记录推送结果
                try {
                    for(MessagepushEntity entity : changeMessageStatusList) {
                        try {
                            changeMessageStatus(entity);
                        } catch (final Exception e) {
                            logger.error("sendSignalMsgBatch.update msg sendStatus failed,msg is " + e.getMessage(), e);
                            isSuccess = false;
                        }
                    }
                    isSuccess = changeMessageStatusList.size() > 0;
                } catch (final Exception e) {
                    logger.error("sendSignalMsgBatch.update msg sendStatus failed,msg is " + e.getMessage(), e);
                    isSuccess = false;
                } finally {
                        //发送短息--安卓才进行短息发送
                        if(messagepushEntity.getUserPushInfo().gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID))==0) {
                            String messageContent = "";
                            String shortUrl_Home = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.SHORTURL_HOME);
                            if(messagepushEntity.getMessage().getType() == 701) {//对帮帮忙的消息做特殊处理，因为帮帮忙的内容比标题还短，所以这样处理
                                messageContent = messagepushEntity.getMessage().getContent() + ": " +  messagepushEntity.getMessage().getTitle();
                            } else if (NoticeDict.Message_Type.MerchantAddLimitBuy.equals(messagepushEntity.getMessage().getType())) {//限时购的短链接自动生成
                                Integer appVersion = getVersionNum(version);
                                messageContent = messagepushEntity.getMessage().getContent();
                                List<MessageParameter> messageParameterList = messagepushEntity.getMessage().getMessageParameterList();
                                if (!DataUtil.isEmpty(messageParameterList)) {
                                    BigInteger storeId = null;
                                    for (MessageParameter messageParameter : messageParameterList) {
                                        if ("storeId".equals(messageParameter.getKey())) {
                                            storeId = new BigInteger(messageParameter.getValue());
                                            break;
                                        }
                                    }
                                    if (storeId != null) {
                                        String resourcePath = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.ResourcePath);
                                        String shortUrlServer = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.ShortUrlServer);
                                        if (appVersion != null && appVersion >= 506) {
                                            String realUrl = resourcePath + "/urlToApp/index.htm?" +
                                                    "schemeAdr=jiefangqu%3a%2f%2fjiefangqu.com%2fopenMainActGroup%3fDLJ%3d1%26tab%3d2%26clazz%3dcom.jiefangqu.living.act.buy.DownstairsProductListAct%26paramsStr%3dS.shopId-" + storeId +
                                                    "&schemeIOS=jiefangqu%3a%2f%2fjiefangqu.com%3fparams%3d%7b%27type%27%3a%271%27%2c%27controll%27%3a%27ShopDetailViewController%27%2c%27params%27%3a%7b%27shopId%27%3a%27" + storeId + "%27%7d%7d";
                                            shortUrl_Home = shortUrlServer + shortUrlService.createShortUrl(realUrl);
                                        }
                                    }
                                }
                            } else {
                                messageContent = messagepushEntity.getMessage().getContent();
                            }
                            messageContent = getTextFromHtml(messageContent);
                            if(messageContent.length() < 200 && messageContent.length() > 10) {//短息内容长度大于10小于200
                            	messageContent = messageContent.length() > 95 ? messageContent.substring(0,95) + "..." : messageContent;

                                logger.info("===========开始发送短息====="+messagepushEntity.getUserHasTMessage().getId()+"======" + messageContent + shortUrl_Home);
                                ShortMsgUtil.sendMessage(new ShortMsg(messagepushEntity.getMobile(), messageContent + shortUrl_Home + " 回复T退订", messagepushEntity.getUserHasTMessage().getId().intValue()));
                                logger.info("===========结束发送短息===="+messagepushEntity.getUserHasTMessage().getId()+"=======" + messageContent);
                            }
                        }
                    }
                }
            }
        return isSuccess;
    }

    public void setActions(final List<AbsPushAction> actions) {
        this.actions = actions;
    }

    public void setMsgpushDao(final IMsgpushDao msgpushDao) {
        this.msgpushDao = msgpushDao;
    }

    public void setUserHasTMessageBaseDao(final IUserHasTMessageBaseDao userHasTMessageBaseDao) {
        this.userHasTMessageBaseDao = userHasTMessageBaseDao;
    }

    public void setUuidManager(final IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        String regEx_space = "\\|\t|\r|\n";//定义回车换行符
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(" "); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(" "); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(" "); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签*/
        return htmlStr.trim(); // 返回文本字符串
    }

    public static String getTextFromHtml(String htmlStr){
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", " ");
        return htmlStr;
    }

    private Integer getVersionNum(String version) {
        if(version != null) {
            version = version.replace(".", "").trim();
            if(version.length() > 3) {
                version = version.substring(0, 3);
            }
            return Integer.valueOf(version);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getTextFromHtml("<p>短公告链接！@#</p><p><br></p><p><br></p><p><br></p><p>123</p><p>0</p>"));
    }
}
