package com.cnfantasia.server.api.msgpush.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.HttpClientUtil;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * @author wangzhe
 * @date 2017年3月10日
 * @description 华为推送
 *
 */
public class PushActionHw extends AbsPushAction {

    @Resource
    private RedisCacheHandler redisCacheHandler;

    private HwToken fetch_AT() {
        HwToken hwtoken = fetch_AT_Redis();
        if (null != hwtoken) {
            if (new Date().getTime() - hwtoken.getRefreshtime().getTime() < hwtoken.getExpires_in() * 1000L) {
                // 尚未过期
            } else {
                hwtoken = requst_AT_HW();
                flush_AT(hwtoken);
            }
        } else {
            hwtoken = requst_AT_HW();
            flush_AT(hwtoken);
        }
        return hwtoken;
    }

    private HwToken fetch_AT_Redis() {
        HwToken hwtoken = null;
        final String strtoken = RedisCacheHandler.get(HwParam.KEY_REDIS_HW_ACCESS_TOKEN);
        if (!TextUtils.isEmpty(strtoken)) {
            hwtoken = JSON.parseObject(strtoken, HwToken.class);
        } else {
        }
        return hwtoken;
    }

    private void flush_AT(final HwToken hwtoken) {
        if (null != hwtoken) {
            hwtoken.setRefreshtime(new Date());
            RedisCacheHandler.set(HwParam.KEY_REDIS_HW_ACCESS_TOKEN, JSON.toJSONString(hwtoken));
        } else {
        }
    }

    public void init() {
        fetch_AT();
    }

    @Override
    public MessagepushEntity push(MessagepushEntity entity) {
        if(!DataUtil.isEmpty(entity.getUserPushInfo().getChannelHw())) {
            if (null != entity && null != entity.getUserPushInfo() && null != entity.getUserPushInfo().getChannelHw()
                    && null != entity.getUserHasTMessage() && null != entity.getUserHasTMessage().getSendData()) {
                final UserPushInfo userPushInfo = entity.getUserPushInfo();
                BigInteger userMessageId = entity.getUserHasTMessage().getId();
                if (userPushInfo.gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID)) == 0
                        || userPushInfo.gettChannelSubFId().compareTo(HeaderConstant.SubChannelId.Jfq_Master_App_Android) == 0) {

                    final HwToken hwtoken = fetch_AT();
                    if (null != hwtoken) {
                        final Map<String, Object> parameters = new HashMap<String, Object>();
                        parameters.put("access_token", hwtoken.getAccess_token());
                        parameters.put("cacheMode", 1); // 0：不缓存, 1：缓存
                        parameters.put("msgType", -1); // 可将msgType取值为-1，这样只有过期后才会删除
                        parameters.put("nsp_fmt", "JSON"); // 必选 ，
                        // 取值建议固定为“JSON”，另外两种取值为：
                        // “php-rpc”和“JS”。
                        final Date date = new Date();
                        final long now = date.getTime();
                        //parameters.put("expire_time", HwParam.DATAFORMAT.get().format(new Date(now + 1000L * 60 * 60 * 48))); // 2天后过期

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
                    String pushId = "";
                    String storeId = "";
                    String msgId = "";
                    String huaweiPushIntent = ""; //当pushID==9时（即运营自己push的消息），用这个作为PushIntent
                    for(MessageParameter  messageParameter : entity.getMessage().getMessageParameterList()) {
                        if("storeId".equals(messageParameter.getKey())) {
                            storeId = messageParameter.getValue();
                        }
                        if("pushId".equals(messageParameter.getKey())) {
                            pushId = messageParameter.getValue();
                        }
                        if ("msgId".equals(messageParameter.getKey())) {
                            msgId = messageParameter.getValue();
                        }
                        
                        if ("huaweiParams".equals(messageParameter.getKey())) {
                        	huaweiPushIntent = messageParameter.getValue();
                        }
                    }
                    	if(MsgpushDict.PushId.OperateMessge.equals(pushId)){
                    		String android = "{\"notification_title\":\""+title+"\",\"notification_content\":\""+description+"\",\"doings\":2,\"intent\":\""+ huaweiPushIntent.replace("userMsgId", userMessageId +"") +"\"}";
                    		parameters.put("android", android); // 发送的PUSH消息体
                    	}else{
                    		String android = "{\"notification_title\":\""+title+"\",\"notification_content\":\""+description+"\",\"doings\":2,\"intent\":\""+getPushIntent(pushId, storeId, msgId, userMessageId)+"\"}";
                    		parameters.put("android", android); // 发送的PUSH消息体
                    	}

                        // parameters.put("message", entity.getUserHasTMessage().getSendData()); // 发送的PUSH消息体
                        parameters.put("priority", 0); // 0：高优先级
                        // parameters.put("nsp_svc",
                        // "openpush.message.single_send"); // 本接口固定为 openpush.message.single_send
                        parameters.put("nsp_svc", "openpush.message.psSingleSend"); // 本接口固定为
                        // openpush.message.single_send
                        parameters.put("nsp_ts", now / 1000); // 服务请求时间戳，自 GMT 时间
                        // 1970-1-1
                        // 0:0:0
                        // 至今的秒数
                        parameters.put("deviceToken", entity.getUserPushInfo().getChannelHw());
                        byte[] bytes = null;
                        try {
                            bytes = HttpClientUtil.doPost(HwParam.HOST_MSG, parameters);
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }finally {
                            logger.debug("==messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+"pushFiled20170620:"+bytes);
                        }
                        if (null != bytes && 0 < bytes.length) {
                            final String str = new String(bytes);
                            final HwResponse response = JSON.parseObject(str, HwResponse.class);
                            // TODO 记录推送结果
                            if(0 == response.getResultcode()) {
                                entity.setSucceedStatus(true);
                                entity.setSendStatusStr(str);
                                entity.setChannelId(entity.getUserPushInfo().getChannelHw());
                            } else {
                                entity.setSucceedStatus(true);
                                entity.setSendStatusStr(response.getError());
                            }
                            entity.setThrdMessageId(response.getRequestID());//查询消息id
                            logger.debug("==华为推送：==messageId="+entity.getMessage().getId()+"====userHashMessageId="+entity.getUserHasTMessage().getId()+"===userHasInfo="+entity.getUserPushInfo().getId()+":推送返回结果="+str);
                        } else {
                            entity.setSucceedStatus(true);
                            entity.setSendStatusStr("发送失败！");
                        }
                    } else {
                        entity.setSucceedStatus(true);
                        entity.setSendStatusStr("发送失败！");
                    }
                } else {
                    // iOS的跳过
                }
            } else {
            }
            return entity;
        } else {
            return null;
        }
    }

    private HwToken requst_AT_HW() {

        HwToken hwtoken = null;
        byte[] bytes = null;
        try {
            bytes = HttpClientUtil.doPost(HwParam.HOST_ACCESS_TOKEN, HwParam.MAP_ACCESSTOKEN);
        } catch (final IOException e) {
            logger.error(e);
        }
        if (null != bytes && 0 < bytes.length) {
            final String str = new String(bytes);
            hwtoken = JSON.parseObject(str, HwToken.class);
        } else {
        }
        return hwtoken;
    }

    /**
     * 根据不同的id返回不同的前端界面跳转
     * @param pushId
     * @return
     */
    private static String getPushIntent(String pushId0, String storeId, String msgId, BigInteger userMsgId) {
       if(!DataUtil.isEmpty(pushId0)) {
            String intent = "";
            int pushId = Integer.parseInt(pushId0.toString());
            switch (pushId) {
                case 0:
                    intent = "intent:#Intent;action=com.jiefangqu.living.MainActGroup;launchFlags=0x10000000;component=com.jiefangqu.living/.act.MainActGroup;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 2:
                    intent = "intent:#Intent;action=com.jiefangqu.living.FamilyMessageListAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.message.FamilyMessageListAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 3:
                    intent = "intent:#Intent;action=com.jiefangqu.living.BarrierHomeAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.accesscontrol.BarrierHomeAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 4:
                    intent = "intent:#Intent;action=com.jiefangqu.living.PropertyRepairOrdersActivity;launchFlags=0x10000000;component=com.jiefangqu.living/.act.dredge.PropertyRepairOrdersActivity;i.position=1;i.type=1;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 5:
                    intent = "intent:#Intent;action=com.jiefangqu.living.PropertyAnnouncementAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.property.PropertyAnnouncementAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 6:
                    intent = "intent:#Intent;action=com.jiefangqu.living.BarrierHomeAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.accesscontrol.BarrierHomeAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 7:
                    intent = "intent:#Intent;action=com.jiefangqu.living.DownstairsProductListAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.buy.DownstairsProductListAct;B.FLAG_FROM_PUSH=true;S.shopId="+storeId+";B.fromPush=true;S.userHasMessageId="+userMsgId+";end";
                    break;
                case 8:
                    intent = "intent:#Intent;action=com.jiefangqu.living.AnnouncementDetailsAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.property.AnnouncementDetailsAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;i.from=2;S.id="+ msgId +";S.userHasMessageId="+userMsgId+";end";
                    break;
            }
           return intent;
       }
        return "";
    }
}
