package com.cnfantasia.server.api.msgpush.util;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.common.utils.DataUtil;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

/**
 * @ClassName: GeTuiPushUtil
 * @Date: 2017-03-11 9:30
 * @Auther: yanghua
 * @Description:(个推)
 */
public class GeTuiPushUtil {

    public static Map<String, Object> pushtoSingle(String appId, String appKey, String masterSecret, String host, String CID,  NotificationTemplate template) throws Exception{
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        Map<String, Object> resMap = new HashMap<String, Object>();
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null && ret.getTaskId() != null) {
            IPushResult pushResult = push.getPushResult(ret.getTaskId());
            if(!DataUtil.isEmpty(pushResult)) {
                Map<String, Object> res = push.getPushResult(ret.getTaskId()).getResponse();
                if(!DataUtil.isEmpty(res)) {
                    resMap.put("result", res.get("result"));
                    resMap.put("reason", "");
                    resMap.put("code", "0000");
                    resMap.put("description", res.get("GT"));
                } else {
                    resMap.put("result", "成功");
                    resMap.put("code", "0000");
                    resMap.put("reason", "");
                    resMap.put("description", "追踪状态失败！");
                }
            } else {
                resMap.put("result", "成功");
                resMap.put("code", "0000");
                resMap.put("reason", "");
                resMap.put("description", "追踪状态失败！");
            }
            System.out.println(ret.getResponse().toString());
        } else {
            resMap.put("result", "TokenMD5NoUsers");
            resMap.put("reason", "客户端注册CID不正确");
            resMap.put("code", "0001");
            resMap.put("description", "推送失败！");
            System.out.println("客户端注册CID不正确");
        }

        return resMap;
    }

}
