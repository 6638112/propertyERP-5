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
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    MsgpushController.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午2:59:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/msgpush")
public class MsgpushController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IMsgpushService msgpushService;
	public void setMsgpushService(IMsgpushService msgpushService) {
		this.msgpushService = msgpushService;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}


	/**
	 * 上传消息推送所需的userId,channelId
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshPushConfig.json")
	@ResponseBody
	public JsonResponse refreshPushConfig(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String baiduUserId = request.getParameter("baiduUserId");
		String baiduChannelId = request.getParameter("baiduChannelId");
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		Long subChannelId = HeaderManager.getSubChannelIdLong();
		//2.交互
		logger.info("MsgpushController.refreshPushConfig.params,subChannelId is "+subChannelId+",baiduUserId is "+baiduUserId+",baiduChannelId is "+baiduChannelId+",userId is"+userId+",userType is "+userType);
		if(!StringUtils.isEmpty(baiduUserId)&&!StringUtils.isEmpty(baiduChannelId)
				&&!StringUtils.isEmpty(userId)&&!StringUtils.isEmpty(userIdType)){
			msgpushService.refreshPushClientInfo(subChannelId,userId,userType,baiduUserId, baiduChannelId, null);//syl-del-tmp-2015-5-28 09:21:57
		}else{
			logger.info("MsgpushController.refreshPushConfig.params part is Empty..");
		}
		//3.结果处理
		return jsonResponse;
	}
	
}
