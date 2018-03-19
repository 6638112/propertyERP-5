package com.cnfantasia.server.api.performPro.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.performPro.entity.GlobalEntity;
import com.cnfantasia.server.api.performPro.service.IPerformProService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.version.constant.VersionConstant;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.service.IVersionService;
import com.cnfantasia.server.api.version.web.VersionUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * 性能优化Controller类
* Filename:    PerformProController.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午2:51:19
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/performPro")
public class PerformProController extends BaseController{
	private IPerformProService performProService;
	public void setPerformProService(IPerformProService performProService) {
		this.performProService = performProService;
	}
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}
	
	private IVersionService versionService;
	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}


	/**
	 * 应用启动请求全局变量
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGlobalValue.json")
	@ResponseBody
	public JsonResponse getGlobalValue(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Long lastBizTypeUpdTime =  ParamUtils.getLong(request, "bizTypeUpdTime", null);
		RequestClientInfoEntity clientInfo = new RequestClientInfoEntity(request);
		BigInteger appId = null;
		if(clientInfo.getSubChannelIdLong()!=null){
			if(clientInfo.getSubChannelIdLong().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID))==0){
				appId = VersionConstant.AndroidDownAppId;
			}else if(clientInfo.getSubChannelIdLong().compareTo(Long.valueOf(DictConstants.Channel_Sub.IOS))==0){
				appId = VersionConstant.IOSDownAppId;
			}else{}
		}
		
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		
		//2.交互
		GlobalEntity globalEntity = performProService.getGlobalDataAll(userId, lastBizTypeUpdTime, clientInfo);
		//附加其他信息
		if(appId!=null){
		//查询最新版本信息
			AppVersionEntity lastVersion = versionService.getLastVersionInfo(appId,userId);
			//比对
			Boolean g_IsVersion;//是否有新版本
			Boolean g_IsForceUpd;//客户端是否需要强制更新	
			String g_DownloadAdress;//新版本下载地址
			String g_VersionDesc;
			if(lastVersion!=null&&lastVersion.getVersion().compareTo(clientInfo.getVersionLong())>0){
				g_IsVersion = true;//是否有新版本	
				g_IsForceUpd = lastVersion.fetchIsForceUpdate();//客户端是否需要强制更新	
				g_DownloadAdress = lastVersion.fetchDownloadUrl();//新版本下载地址
				g_VersionDesc = lastVersion.getTextDesc();
			}else{
				g_IsVersion = false;//是否有新版本	
				g_IsForceUpd = null;//客户端是否需要强制更新	
				g_DownloadAdress = null;//新版本下载地址
				g_VersionDesc = null;
			}
			globalEntity.setG_IsVersion(g_IsVersion);
			globalEntity.setG_IsForceUpd(g_IsForceUpd);
			globalEntity.setG_DownloadAdress(g_DownloadAdress);
			globalEntity.setG_VersionDesc(g_VersionDesc);
		}
		
		if(globalEntity.getG_IsNewUser()){//是新用户，则判断是否创建临时用户
			if(clientInfo.getDeviceId()!=null){
				//创建临时用户
				commonDeviceService.checkAndCreateQueue(clientInfo.getDeviceId(), clientInfo.getDeviceType());
			}
			globalEntity.setG_DiscountNum(3);//新用户剩余折扣次数为3
		}
		//3.结果处理
		Boolean g_IsFamalyMember = globalEntity.getG_IsFamalyMember();//是否有其他家庭成员
		Boolean g_IsMultDevice = globalEntity.getG_IsMultDevice();//是否有多个设备
		Boolean g_IsBizType = globalEntity.getG_IsBizType();//商家类别是否有更新
		Long g_bizTypeUpdTime = globalEntity.getG_bizTypeUpdTime();//商家类别最近更新时间
		Integer g_DiscountNum = globalEntity.getG_DiscountNum();//剩余抽奖次数
		Boolean g_IsVersion = globalEntity.getG_IsVersion();//是否有新版本
		Boolean g_IsForceUpd = globalEntity.getG_IsForceUpd();//客户端是否需要强制更新
		String g_DownloadAdress = globalEntity.getG_DownloadAdress();//新版本下载地址
		String g_VersionDesc = globalEntity.getG_VersionDesc();
		Boolean g_IsNewUser = globalEntity.getG_IsNewUser();//是否为新注册用户
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(g_IsFamalyMember!=null){resMap.put("g_IsFamalyMember", g_IsFamalyMember);}
		if(g_IsMultDevice){resMap.put("g_IsMultDevice", g_IsMultDevice);}
		if(g_IsBizType!=null){resMap.put("g_IsBizType", g_IsBizType);}
		if(g_bizTypeUpdTime!=null){resMap.put("g_bizTypeUpdTime", g_bizTypeUpdTime);}
		if(g_DiscountNum!=null){resMap.put("g_DiscountNum", g_DiscountNum);}
		if(g_IsVersion!=null){resMap.put("g_IsVersion", g_IsVersion);}
		if(g_IsForceUpd!=null){resMap.put("g_IsForceUpd", g_IsForceUpd);}
		if(g_VersionDesc!=null){resMap.put("g_VersionDesc", g_VersionDesc);}
		if(g_DownloadAdress!=null){resMap.put("g_DownloadAdress", g_DownloadAdress);}
		if(g_IsNewUser!=null){resMap.put("g_IsNewUser", g_IsNewUser);}
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
}
