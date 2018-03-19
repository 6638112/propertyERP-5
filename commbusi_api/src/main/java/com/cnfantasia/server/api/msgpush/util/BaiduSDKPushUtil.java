package com.cnfantasia.server.api.msgpush.util;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.*;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.BaiduPushResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: BaiduSDKPushUtil
 * @Date: 2017-01-06 14:00
 * @Auther: kangduo
 * @Description: (百度推送工具包，用百度SDK推送)
 */
public class BaiduSDKPushUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 向单个设备推送
     * @param keyPair 推送证书
     * @param messageFormat 包含的用户信息
     * @return 推送结果
     */
    public static BaiduPushResponse push2SingleDevice(PushKeyPair keyPair, BaiduMessageFormat messageFormat) {

        BaiduPushResponse pushResponse = new BaiduPushResponse();
        BaiduPushClient pushClient = new BaiduPushClient(keyPair, BaiduPushConstants.CHANNEL_REST_URL);
        //注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler (new YunLogHandler() {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println("百度推送数据情况：" + event.getMessage());
            }
        });

        try {
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().
                    addChannelId(messageFormat.getBaiduChannelId()).
                    addMessageType(Integer.parseInt(messageFormat.getMessage_type())).
                    addMessage(messageFormat.getMessageContent().replaceAll("\\n|\\t|\\r", "")).
                    addDeviceType(Integer.valueOf(messageFormat.getDevice_type()));
            //iOS才用到deployStatus
            if (messageFormat.getDevice_type().equals(MsgpushDict.BaiDu_device_type.iOS)) {
                request.setDeployStatus(Integer.valueOf(messageFormat.getDeploy_status()));
            }
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
            pushResponse.setMsgId(response.getMsgId());
            pushResponse.setSendTime(response.getSendTime());
            pushResponse.setSendTimeFormat(sdf.format(new Date(response.getSendTime() * 1000L)));
        } catch (PushClientException e) {
            pushResponse.setSuccess(false);
            e.printStackTrace();
        } catch (PushServerException e) {
            pushResponse.setSuccess(false);
            pushResponse.setRequestId(e.getRequestId());
            pushResponse.setErrorCode(String.valueOf(e.getErrorCode()));
            pushResponse.setErrorMsg(e.getErrorMsg());
            e.printStackTrace();
        }
        return pushResponse;
    }

    public static void main(String[] args) throws PushClientException,PushServerException {
        PushKeyPair IOSPushKeyPair = new PushKeyPair("cimduL99nuW9yTXch59s7w9G", "O4xG1yAgyTZBIsZicAKfsiHAugXe4tUn");
        PushKeyPair IOSMasterPushKeyPair = new PushKeyPair("V5Oa0lp9DWa5Ip6EGiFRN5Lp", "d418e67b2059a643477b9eae3f97dee9");
        PushKeyPair AndroidPushKeyPair = new PushKeyPair("nfIPFeMEW1CLMirh4wkGNPk3", "GPr9NYuGYSPjRVBfF9b6fshCyCdhVX8w");
        PushKeyPair AndroidMasterPushKeyPair = new PushKeyPair("B8GDSXI0EWY1WWYXuPPi1M7O", "1U9ZGUj4GKZy2WqruwoDzhngRnKcdMNX");
//        String channelId = "5711044642281437780"; // kangduo
        String channelId = "5294664088585825779"; //
        String deployStatus = "1";//1 开发   2 生产
        String deviceType = "4";//3 安卓   4 iOS
        String messageType = "1"; // 0 透传  1 消息
        long l = System.currentTimeMillis();
        BaiduMessageFormat format = new BaiduMessageFormat(null, "{\"title\":\"提醒\",\"description\":\"解放区祝您生活愉快!\"}", channelId,
                null, null, deviceType, messageType, null, null, null, deployStatus, 456L, null);
        BaiduPushResponse pushResponse = push2SingleDevice(IOSMasterPushKeyPair, format);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(JSON.toJSONString(pushResponse));
    }

}
