package com.cnfantasia.server.api.msgpush.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.util.XiaoMiPushUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.xiaomi.xmpush.server.Message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.Map;

public class PushActionXm extends AbsPushAction {
    private static String MY_PACKAGE_NAME = "";
    private static String APP_SECRET_KEY = "";
    private static String regId = "";
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private ISysParamManager sysParamManager;

    private void init() {
        String param = sysParamManager.getSysParaValue("xiaomi_config");
        if(!DataUtil.isEmpty(param)) {
            String[] paramArry = param.split(";");
            MY_PACKAGE_NAME = paramArry[0];
            APP_SECRET_KEY = paramArry[1];
        } else {
            logger.error("获取小米参数失败！（MY_PACKAGE_NAME，APP_SECRET_KEY）");
        }
    }
    @Override
    public MessagepushEntity push(MessagepushEntity entity) {
        if(!DataUtil.isEmpty(entity.getUserPushInfo().getChannelXm())) {
            Message message = null;
            try {
                message = buildMessageTpic(entity);
            } catch (Exception e) {
                entity.setSendStatusStr("消息模板构建异常！");
                e.printStackTrace();
                return entity;
            }
            regId = entity.getUserPushInfo().getChannelXm();
            if(DataUtil.isEmpty(regId)) {
                logger.debug("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送返回结果=t_user_push_info的channel_xm为空");
                entity.setSucceedStatus(false);
                entity.setSendStatusStr("regId为空");
                return entity;
            }
            try {
                Map<String, Object> resMap = XiaoMiPushUtil.pushtoSingle(message, regId, APP_SECRET_KEY);
                if(!DataUtil.isEmpty(resMap)) {
                    entity.setChannelId(regId);
                    if(!DataUtil.isEmpty(resMap.get("code")) && resMap.get("code").equals("0000")) {
                        entity.setSucceedStatus(true);
                    } else {
                        entity.setSucceedStatus(false);
                    }
                    entity.setSendStatusStr(resMap.get("description") != null ? resMap.get("description").toString() + (resMap.get("reason") != null ? resMap.get("reason") : "") : "");
                    entity.setThrdMessageId(resMap.get("messageId") != null ? resMap.get("messageId").toString() : "");
                }
                logger.debug("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送返回结果="+resMap);
            } catch (Exception e) {
                entity.setSucceedStatus(false);
                entity.setSendStatusStr("小米推送异常");
                logger.error("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送异常="+e);
                e.printStackTrace();
                return entity;
            }
            return entity;
        } else {
            return null;
        }
    }
    /**
     * 消息构建 Android 透传
     * @return
     * @throws Exception
     */
    private static Message buildMessage(MessagepushEntity entity) throws Exception {
        Message message = new Message.Builder()
                .payload(entity.getUserHasTMessage().getSendData())
                .restrictedPackageName(MY_PACKAGE_NAME)
                .passThrough(1)  // 消息使用透传方式
                .notifyType(1)   // 使用默认提示音提示
                .build();
        return message;
    }

    /**
     * 消息构建 Android 通知栏
     * @return
     * @throws Exception
     */
    private static Message buildMessageTpic(MessagepushEntity entity) throws Exception {
        JSONObject jsonObject = JSON.parseObject(entity.getUserHasTMessage().getSendData());
        String title = "解放区";
        String description = "解放区";
        if(!DataUtil.isEmpty((jsonObject.get("messageContent")))) {
            Object titleObj = JSON.parseObject(jsonObject.get("messageContent").toString()).get("title");
            if(!DataUtil.isEmpty(titleObj)) {
                title = titleObj.toString();
            }
            Object descriptionObj = JSON.parseObject(jsonObject.get("messageContent").toString()).get("description");
            if(!DataUtil.isEmpty(descriptionObj)) {
                description = descriptionObj.toString();
            }
        }
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(entity.getUserHasTMessage().getSendData())
                .restrictedPackageName(MY_PACKAGE_NAME)
                .passThrough(0)  // 消息使用透传方式1 通知栏 0
                .notifyType(1)   // 使用默认提示音提示
                .build();
        return message;
    }
}
