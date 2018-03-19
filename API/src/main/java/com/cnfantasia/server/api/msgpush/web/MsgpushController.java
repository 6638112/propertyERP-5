/**
* Filename:    MsgpushController.java
* @version:    1.0
* Create at:   2014年9月22日 上午2:59:47
* Description:
*
* Modification History:
* Date        Author      Version     Description
* -----------------------------------------------------------------
* 2014年9月22日    shiyl      1.0         1.0 Version
*/
package com.cnfantasia.server.api.msgpush.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.msgpush.service.EnumPushChannel;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: MsgpushController.java
 *
 * @version: 1.0.0 Create at: 2014年9月22日 上午2:59:47 Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/msgpush")
public class MsgpushController extends BaseController {
    private final Log logger = LogFactory.getLog(getClass());

    private IMsgpushService msgpushService;

    private ICommonDeviceService commonDeviceService;

    /**
     * 上传消息推送所需的userId,channelId
     * 
     * @param request
     * @return
     */
    @RequestMapping("/refreshPushConfig.json")
    @ResponseBody
    public JsonResponse refreshPushConfig(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        final String baiduUserId = request.getParameter("baiduUserId");
        final String baiduChannelId = request.getParameter("baiduChannelId");
        final UserIdType userIdType = commonDeviceService.getUserIdType();
        final BigInteger userId = userIdType.getUserId();
        final Integer userType = userIdType.getUserType();
        final Long subChannelId = HeaderManager.getSubChannelIdLong();
        // 2.交互
        logger.info("MsgpushController.refreshPushConfig.params,subChannelId is " + subChannelId + ",baiduUserId is " + baiduUserId
                + ",baiduChannelId is " + baiduChannelId + ",userId is" + userId + ",userType is " + userType);
        if (!StringUtils.isEmpty(baiduUserId) && !StringUtils.isEmpty(baiduChannelId) && !StringUtils.isEmpty(userId)
                && !StringUtils.isEmpty(userIdType)) {
            msgpushService.refreshPushClientInfo(subChannelId, userId, userType, baiduUserId, baiduChannelId, HeaderManager.getVersion());// syl-del-tmp-2015-5-28
                                                                                                                    // 09:21:57
        } else {
            logger.info("MsgpushController.refreshPushConfig.params part is Empty..");
        }
        // 3.结果处理
        return jsonResponse;
    }

    public void setCommonDeviceService(final ICommonDeviceService commonDeviceService) {
        this.commonDeviceService = commonDeviceService;
    }

    public void setMsgpushService(final IMsgpushService msgpushService) {
        this.msgpushService = msgpushService;
    }

    /**
     * 上传消息推送所需的userId,channelId
     *
     * @param request
     * @return
     */
    @RequestMapping("/uploadPushConfig.json")
    @ResponseBody
    public JsonResponse uploadPushConfig(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        final String strctype = request.getParameter("jfq.channeltype");
        Integer ctype = EnumPushChannel.BAIDU.ordinal(); // 默认值
        try {
            ctype = Integer.parseInt(strctype);
        } catch (final NumberFormatException e) {
            logger.error(e);
        }

        final String channelid = request.getParameter("channelid");

        final UserIdType userIdType = commonDeviceService.getUserIdType();
        final BigInteger userId = userIdType.getUserId();
        final Integer userType = userIdType.getUserType();
        final Long subChannelId = HeaderManager.getSubChannelIdLong();
        //华为：HUAWEI;小米：XIAOMI;其他：OTHER
        //String userAgent = getUserAgent(request.getParameter("User-Agent"));
        //设计时候是为了和手机绑定，后来结合实际根据包来进行推送匹配
        String userAgent = request.getParameter("packageChannel");
        if(!DataUtil.isEmpty(userAgent)) {
            if("Huawei".equals(userAgent)) {
                userAgent = "HUAWEI";
            } else if("Mi".equals(userAgent)) {
                userAgent = "XIAOMI";
            } else {
                userAgent = "OTHER";
            }
        } else {
            userAgent = "OTHER";
        }

        // 2.交互
        if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(userIdType)) {
            msgpushService.refreshPushClientInfo(subChannelId, userId, userType, HeaderManager.getVersion(), channelid, ctype, userAgent);
        } else {
            logger.info("MsgpushController.refreshPushConfig.params part is Empty..");
        }
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 获取机型--品牌
     * @param os
     * @param downPath
     * @return
     */
    private static String getUserAgent(String os) {
        String userAgent = "OTHER";
        int i = os.indexOf(":");
        int j = os.indexOf(",");
        if(DataUtil.isEmpty(os)) {
            return userAgent;
        }
        if(i == 0 || j == 0) {
            return userAgent;
        }
        String phoneOs = os.substring(i + 1, j).toUpperCase();
        if(phoneOs.equalsIgnoreCase("huawei") || phoneOs.equalsIgnoreCase("xiaomi") || phoneOs.equalsIgnoreCase("honor")) {
            if(phoneOs.equalsIgnoreCase("honor")) phoneOs = "HUAWEI";
            userAgent = phoneOs;
        }
        return userAgent;
    }
}
