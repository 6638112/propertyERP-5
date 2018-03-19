package com.cnfantasia.server.api.msgpush.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.msgpush.util.GeTuiPushUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.Map;

public class PushActionGt extends AbsPushAction {

    private static String appId = "";
    private static String appKey = "";
    private static String masterSecret = "";
    private static String host = "";
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private ISysParamManager sysParamManager;

    private void init() {
        String param = sysParamManager.getSysParaValue("gettui_config");
        if(!DataUtil.isEmpty(param)) {
            String[] paramArry = param.split(";");
            appId = paramArry[0];
            appKey = paramArry[1];
            masterSecret = paramArry[2];
            host = paramArry[3];
        } else {
            logger.error("获取个推参数失败！（appId，appKey，masterSecret，host）");
        }
    }

    @Override
    public MessagepushEntity push(MessagepushEntity entity) {
        if(!DataUtil.isEmpty(entity.getUserPushInfo().getChannelGt())) {
            //组装消息
            NotificationTemplate template = null;
            try {
                template = notificationTemplate(entity);
            } catch (Exception e) {
                entity.setSucceedStatus(false);
                entity.setSendStatusStr("模板创建异常");
                e.printStackTrace();
                return entity;
            }
            String CID = entity.getUserPushInfo().getChannelGt();
            if(DataUtil.isEmpty(CID)) {
                logger.debug("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送返回结果=t_user_push_info的channel_gt为空");
                entity.setSucceedStatus(false);
                entity.setSendStatusStr("个推客户端CID为空");
                return entity;
            }
            //发送
            try {
                Map<String, Object> resMap = GeTuiPushUtil.pushtoSingle(appId, appKey, masterSecret, host, CID, template);
                if(!DataUtil.isEmpty(resMap.get("code")) && resMap.get("code").equals("0000")) {
                    entity.setSucceedStatus(true);
                    if(resMap.get("description") != null) {
                        entity.setSendStatusStr(resMap.get("description").toString());
                    }
                    if(resMap.get("taskId") != null) {
                        entity.setThrdMessageId(resMap.get("taskId").toString());
                    }
                    entity.setChannelId(CID);
                }
                logger.debug("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送返回结果="+resMap);
            } catch (Exception e) {
                logger.error("====messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送异常="+e);
                entity.setSucceedStatus(false);
                entity.setSendStatusStr("个推推送异常！");
                e.printStackTrace();
                return entity;
            }
            return entity;
        } else {
            return null;
        }
    }

    private static TransmissionTemplate transmissionTemplateDemo(MessagepushEntity entity) throws Exception{
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(entity.getUserHasTMessage().getSendData());
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    private static NotificationTemplate notificationTemplate(MessagepushEntity entity) throws Exception{
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("解放区");
        template.setText("解放区");
        template.setLogo("解放区");
        ///Style0系统样式和Style1个推样式
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
        Style0 style0 = new Style0();
        style0.setTitle(title);
        style0.setText(description);
        style0.setLogo("icon_app.png");
        template.setStyle(style0);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(entity.getUserHasTMessage().getSendData());
        return template;
    }


}
