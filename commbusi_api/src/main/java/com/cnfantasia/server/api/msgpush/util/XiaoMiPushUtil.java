package com.cnfantasia.server.api.msgpush.util;

import com.cnfantasia.server.common.utils.DataUtil;
import com.tenpay.util.JsonUtil;
import com.xiaomi.xmpush.server.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: XiaoMiPushUtil
 * @Date: 2017-03-10 15:03
 * @Auther: yanghua
 * @Description:(小米推送)
 */
public class XiaoMiPushUtil {

    /**
     * 单个regid消息
     * @throws Exception
     */
    public static Map<String, Object> pushtoSingle(Message message, String regId, String APP_SECRET_KEY) throws Exception {
        Constants.useOfficial();
        Sender sender = new Sender(APP_SECRET_KEY);
        Result result = sender.send(message, regId, 3);//根据regID，发送消息到指定设备上

        Map<String, Object> resMap = new HashMap<String, Object>();
        if(result != null && result.getMessageId() != null) {
            String messageStatus = new Tracer(APP_SECRET_KEY).getMessageStatus(result.getMessageId(), 3);
            resMap.put("result", JsonUtil.getJsonValue(messageStatus, "result"));
            if(!DataUtil.isEmpty(JsonUtil.getJsonValue(messageStatus, "result")) && JsonUtil.getJsonValue(messageStatus, "result").equals("error")) {
                resMap.put("reason", JsonUtil.getJsonValue(messageStatus, "reason"));
                resMap.put("code", "0001");
            } else {
                resMap.put("code", "0000");
            }
            resMap.put("description", JsonUtil.getJsonValue(messageStatus, "description"));
        } else {
            resMap.put("result", "失败");
            resMap.put("reason", "服务器异常！");
            resMap.put("code", "0001");
            resMap.put("description", "服务器异常！");
        }

        return resMap;
    }
}
