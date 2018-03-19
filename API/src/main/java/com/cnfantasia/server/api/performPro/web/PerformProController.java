package com.cnfantasia.server.api.performPro.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.performPro.entity.GlobalEntity;
import com.cnfantasia.server.api.performPro.service.IPerformProService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.api.version.constant.VersionConstant;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.service.IVersionService;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.api.version.web.VersionUtil;
import com.cnfantasia.server.business.commonBusiness.util.MailUtils;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.appCrashLog.entity.AppCrashLog;
import com.cnfantasia.server.domainbase.appCrashLog.service.IAppCrashLogBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.service.IUserHasTMessageBaseService;

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

	private IAppCrashLogBaseService appCrashLogBaseService;
	public void setAppCrashLogBaseService(IAppCrashLogBaseService appCrashLogBaseService) {
		this.appCrashLogBaseService = appCrashLogBaseService;
	}

	@Resource
	private IDredgeBillBaseService dredgeBillBaseService;
	@Resource
	private IUserHasTMessageBaseService userHasTMessageBaseService;

	@Resource
	private SysParamManager sysParamManager;

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
			AppVersionEntity lastForceVersion = versionService.getLastVersionInfo(appId,userId,1);
			AppVersionEntity lastVersion = versionService.getLastVersionInfo(appId,userId);
			//比对
			Boolean g_IsVersion = false;//是否有新版本
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
			
			//检查有无强升版本 added by wenfq 20160920
			if(lastForceVersion!=null && lastForceVersion.getVersion().compareTo(clientInfo.getVersionLong())>0){
//				g_IsForceUpd = true;//是否有新版本	
				g_IsForceUpd = lastForceVersion.fetchIsForceUpdate();//客户端是否需要强制更新
			}
			//小于518版本的，如果有维修退款，同样要求强升
			if (g_IsForceUpd != null && !g_IsForceUpd && userId != null && clientInfo.getVersionLong() < 50108) {
				DredgeBill db = new DredgeBill();
				db.setStatus(DredgeConstant.DredgeBill.Apply_Refund);
				db.settUserFId(userId);
				int count = dredgeBillBaseService.getDredgeBillCount(MapConverter.toMap(db));
				g_IsForceUpd = count > 0;
				if (!g_IsForceUpd) {
					db.setStatus(DredgeConstant.DredgeBill.Refund_Success);
					g_IsForceUpd = dredgeBillBaseService.getDredgeBillCount(MapConverter.toMap(db)) > 0;
				}
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
		String backPic = sysParamManager.getSysParaValue(SysParamKey.HOME_BACK_GROUND_PIC);
		if (!StringUtils.isEmpty(backPic)) {
			String homeBackGroundPic = sysParamManager.getImageServerUrl(null) + backPic;
			jsonResponse.putData("backgroundPic", homeBackGroundPic);
		} else {
			jsonResponse.putData("backgroundPic", null);
		}
		String addressJsonFileMd5 = sysParamManager.getSysParaValue(SysParamKey.ADDRESS_JSON_FILE_MD5);
		if (!StringUtils.isEmpty(addressJsonFileMd5)) {
			String apiBaseUrl = sysParamManager.getSysParaValue(SysParamKey.Last_Api_BaseUrl);
			jsonResponse.putData("addressJsonFileMd5", addressJsonFileMd5);
			jsonResponse.putData("addressJsonFileLink", apiBaseUrl + "json/" + addressJsonFileMd5 + ".file");
		}
		return jsonResponse;
	}
	
	@RequestMapping("/getVersion.json")
	@ResponseBody
	public JsonResponse getVersion(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String versionStr = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		Long version;
		if(versionStr != null) {
			version = VersionTransferUtil.versionStr2Long(versionStr);
		} else {
			version = (Long) request.getSession().getAttribute("version");
		}
		
		if(version == null) {
			version = 50106L;
		}
		
		jsonResponse.setDataValue(version);
		return jsonResponse;
	}

	@RequestMapping(value = "/crashLog.json")
	@ResponseBody
	public JsonResponse crashLog(AppCrashLog crashLog) {
		String version = HeaderManager.getVersion();
		if (crashLog.getCrashContent() != null
				&& crashLog.getOperatingSystem() != null
				&& crashLog.getPhoneModel() != null
				&& crashLog.getNetworkSignal() != null
				&& !DataUtil.isEmpty(version)) {
			if (crashLog.getVersion() == null) {
				crashLog.setVersion(version);
			}
			appCrashLogBaseService.insertAppCrashLog(crashLog);

			String notifyMail = sysParamManager.getSysParaValue(SysParamKey.App_Crash_Notify_Mail);
			if (!DataUtil.isEmpty(notifyMail)) {
				String content = "<div>" +
						"手机机型：" + crashLog.getPhoneModel() + "</div><div>" +
						"手机系统：" + crashLog.getOperatingSystem() + "</div><div>" +
						"网络信号：" + crashLog.getNetworkSignal() + "</div><div>" +
						"版 本 号：" + crashLog.getVersion() + "</div><div>" +
						"崩溃描述：" + crashLog.getCrashContent() + "</div>";
				new MailSendThread(content, notifyMail).start();
			}
		}
		return new JsonResponse();
	}

	@RequestMapping(value = "/reportPush.json")
	@ResponseBody
	public JsonResponse receivePush(HttpServletRequest request) {
		Integer appVersion = HeaderManager.getVersionNum();
		String pushIdStr = request.getParameter("pushIds");
		//功能在版本340添加，防止有人乱调接口刷
		if (appVersion != null && appVersion >= 340 && !DataUtil.isEmpty(pushIdStr)) {
			List<BigInteger> pushIds = JSON.parseArray(pushIdStr, BigInteger.class);
			List<UserHasTMessage> messages = new ArrayList<UserHasTMessage>(pushIds.size());
			int nowIdx = 0;
			for (int i = 0; i < pushIds.size(); i++) {
				UserHasTMessage message = new UserHasTMessage();
				message.setId(pushIds.get(nowIdx++));
				message.setClientMarkReceived(1);
				messages.add(message);
			}
			userHasTMessageBaseService.updateUserHasTMessageBatch(messages);
		}
		return new JsonResponse();
	}

	//发送邮件
	private static class MailSendThread extends Thread {
		private String mailContent;
		private String nofityEmails;
		public MailSendThread(String mailContent, String nofityEmails) {
			this.mailContent = mailContent;
			this.nofityEmails = nofityEmails;
		}
		public void run() {
			MailUtils.sendMail("存在新的APP崩溃记录", mailContent, nofityEmails);
		}
	}
}
