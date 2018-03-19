/**   
* Filename:    QRCodeController.java   
* @version:    1.0  
* Create at:   2014年6月15日 下午3:57:16   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.web;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.common.utils.DataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.version.constant.VersionConstant;
import com.cnfantasia.server.api.version.entity.AppVersionChannelEntity;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.service.IVersionService;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.appDownLog.entity.AppDownLog;
import com.cnfantasia.server.domainbase.appDownLog.service.IAppDownLogBaseService;

/**
 * Filename:    QRCodeController.java
 * @version:    1.0.0
 * Create at:   2014年6月15日 下午3:57:16
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月15日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/qrCode")
@Controller
public class QRCodeController extends BaseController{
	private Log logger =LogFactory.getLog(getClass());
	
	/**来源*/
	private static final String PARAM_FROM = "from";
	/**目标渠道*/
	private static final String PARAM_GOAL_CHANNEL = "g";
	
	private IAppDownLogBaseService appDownLogBaseService;
	public void setAppDownLogBaseService(IAppDownLogBaseService appDownLogBaseService) {
		this.appDownLogBaseService = appDownLogBaseService;
	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
//	private ISysParamParser appDownloadParamParser;
//	public void setAppDownloadParamParser(ISysParamParser appDownloadParamParser) {
//		this.appDownloadParamParser = appDownloadParamParser;
//	}

	private IVersionService versionService;
	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}
	
//	private ISysParamParser baseDownloadGuidePageParamParser;
//	public void setBaseDownloadGuidePageParamParser(ISysParamParser baseDownloadGuidePageParamParser) {
//		this.baseDownloadGuidePageParamParser = baseDownloadGuidePageParamParser;
//	}
	
	private ISysParamParser IOSDeviceKeyWordsParamParser;
	public void setIOSDeviceKeyWordsParamParser(ISysParamParser iOSDeviceKeyWordsParamParser) {
		IOSDeviceKeyWordsParamParser = iOSDeviceKeyWordsParamParser;
	}
	
	private SimpleVersionPathEntity fetchSimpleVersionPath(BigInteger userId,BigInteger downAppId,String channelCode,HttpServletRequest request){
		String version = null;
		String downPath = null;
		{
			AppVersionChannelEntity appVersionChannel = null;
			if(!StringUtils.isEmpty(channelCode)){//渠道编号不为空
				appVersionChannel = versionService.getLastVersionInfo(downAppId, userId, channelCode);
			}
			if(appVersionChannel!=null){
				version = VersionTransferUtil.long2VersionStr(appVersionChannel.getVersion());
				downPath = appVersionChannel.getVersionDwonUrl();
			}else{
				AppVersionEntity appVersionEntity=versionService.getLastVersionInfo(downAppId,userId);
				if(appVersionEntity!=null){
					version = VersionTransferUtil.long2VersionStr(appVersionEntity.getVersion());
					downPath = appVersionEntity.getVersionDwonUrl();
				}
			}
		}
		if(!StringUtils.isEmpty(downPath)){
			downPath = appendParam(downPath, PARAM_FROM, request.getParameter(PARAM_FROM));//syl--2014-10-22 10:31:21-add
		}
		return new SimpleVersionPathEntity(version, downPath);
	}
	
	private static String appendParam(String basePath,String name,String value){
		StringBuffer basePathSbf = new StringBuffer(basePath);
		if(!StringUtils.isEmpty(value)){
			if(basePath.lastIndexOf("?")==-1){
				basePathSbf.append("?");
			}
			if(!basePathSbf.toString().endsWith("?")){
				basePathSbf.append("&");
			}
			basePathSbf.append(name);
			basePathSbf.append("=");
			basePathSbf.append("'");
			basePathSbf.append(value);
			basePathSbf.append("'");
		}
		return basePathSbf.toString();
	}
	@RequestMapping("/baseDown.html")
	public ModelAndView baseDown(HttpServletRequest request){
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
//		Enumeration<String> tmpEnumeration = request.getParameterNames();
//		while(tmpEnumeration.hasMoreElements()){
//			String key = tmpEnumeration.nextElement();
//			System.out.println(key+"  "+request.getParameter(key));
//		}
		
//		String baseIndexPath = baseDownloadGuidePageParamParser.parseParamValue();
//		StringBuffer goalPath = new StringBuffer(baseIndexPath);
//		if(baseIndexPath.lastIndexOf("?")==-1){
//			goalPath.append("?");
//		}
		
//		if(goalPath.charAt(goalPath.length()-1)=='&'){
//			goalPath.deleteCharAt(goalPath.length()-1);
//		}
		{
			String channelCode = request.getParameter(PARAM_GOAL_CHANNEL);
			{
				String androiddVer = null;
				String androidPath = null;
				SimpleVersionPathEntity androidVP = fetchSimpleVersionPath(userId, VersionConstant.AndroidDownAppId, channelCode,request);
				androiddVer = androidVP.getVersion();
				String os = request.getHeader("User-Agent");
				androidPath = androidVP.getDownPath();
				if(!DataUtil.isEmpty(os)) {//为了和更新使用同一个方法  故做此处理
					os = os.toUpperCase();
					if(os.contains("XIAOMI")) {
						os = "BRAND:XIAOMI,";
					}
					if(os.contains("HUAWEI")) {
						os = "BRAND:HUAWEI,";
					}
					if(os.contains("HONOR")) {
						os = "BRAND:HUAWEI,";
					}
					androidPath = osSwitchDownPath(os, androidPath);
				}
				{//设值
					request.setAttribute("androiddVer", androiddVer);
					request.setAttribute("androidPath", androidPath);
//					goalPath.append("androiddVer="+androiddVer);
//					goalPath.append("&androidPath="+androidPath);
				}
			}
			{
				String iosVer = null;
				String iosPath = null;
				SimpleVersionPathEntity iosVP = fetchSimpleVersionPath(userId, VersionConstant.IOSDownAppId, channelCode,request);
				iosVer = iosVP.getVersion();
				iosPath = iosVP.getDownPath();
				{//设值
					request.setAttribute("iosVer", iosVer);
					request.setAttribute("iosPath", iosPath);
//					goalPath.append("&iosVer="+iosVer);
//					goalPath.append("&iosPath="+iosPath);
				}
			}
			
		}
//		return "redirect:"+goalPath;
		String userAgent = request.getHeader("User-Agent");
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo("");
			appDownLog.setAppType("baseDown");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(userAgent);
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("baseDown insert log cause error",e);
		}
		ModelAndView mav = new ModelAndView();
		if(!StringUtils.isEmpty(userAgent)){
			//判断设备类别是否为ios
			List<String> iosKeyWords = IOSDeviceKeyWordsParamParser.parseParamValue();
			for(String key:iosKeyWords){
				if(userAgent.contains(key)){
					mav.setViewName("forward:/qrCode/iosDown.html");
					return mav;
				}
			}
		}
		mav.setViewName("/qrCode/baseDown");
		return mav;
	}
	
	/**
	 * 师傅端扫码下载
	 * 
	 * @author wenfq 2015-09-09
	 * @param request
	 * @return
	 */
	@RequestMapping("/baseDownMaster.html")
	public ModelAndView baseDownMaster(HttpServletRequest request) {
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		{
			String channelCode = request.getParameter(PARAM_GOAL_CHANNEL);
			{
				String androiddVer = null;
				String androidPath = null;
				SimpleVersionPathEntity androidVP = fetchSimpleVersionPath(userId, VersionConstant.Master_AndroidDownAppId, channelCode, request);
				androiddVer = androidVP.getVersion();
				androidPath = androidVP.getDownPath();
				{//设值
					request.setAttribute("androiddVer", androiddVer);
					request.setAttribute("androidPath", androidPath);
					//					goalPath.append("androiddVer="+androiddVer);
					//					goalPath.append("&androidPath="+androidPath);
				}
			}
			{
				String iosVer = null;
				String iosPath = null;
				SimpleVersionPathEntity iosVP = fetchSimpleVersionPath(userId, VersionConstant.Master_IOSDownAppId, channelCode, request);
				iosVer = iosVP.getVersion();
				iosPath = iosVP.getDownPath();
				{//设值
					request.setAttribute("iosVer", iosVer);
					request.setAttribute("iosPath", iosPath);
					//					goalPath.append("&iosVer="+iosVer);
					//					goalPath.append("&iosPath="+iosPath);
				}
			}

		}
		//		return "redirect:"+goalPath;
		String userAgent = request.getHeader("User-Agent");
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo("");
			appDownLog.setAppType("baseDownMaster");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(userAgent);
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("baseDownMaster insert log cause error", e);
		}
		ModelAndView mav = new ModelAndView();
		if (!StringUtils.isEmpty(userAgent)) {
			//判断设备类别是否为ios
			List<String> iosKeyWords = IOSDeviceKeyWordsParamParser.parseParamValue();
			for (String key : iosKeyWords) {
				if (userAgent.contains(key)) {
					//ios先不跳转，因为师傅端的appStore还没有上架
					//					mav.setViewName("forward:/qrCode/iosDownMaster.html");
					//					return mav;
				}
			}
		}
		mav.setViewName("/qrCode/baseDownMaster");
		return mav;
	}

//	public static void main(String[] args) {
//		String userAgent="hyn/1.1.0 (iPhone; iOS 7.1.2; Scale/2.00)";
//		if(userAgent.contains("iPad")||userAgent.contains("iPhone")||userAgent.contains("iPod")){
//			System.out.println(true);
//		}
//	}
	
	@RequestMapping("/androidDownMaster.html")
	public String androidDownMaster(HttpServletRequest request) {
		logger.info("androidDownMaster start...");
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		//		AppDownloadConfig appDownloadConfig = appDownloadParamParser.parseParamValue();
		//		String downPath = appDownloadConfig.getAndroidDownloadUrl();
		//获取最新的版本信息
		AppVersionEntity appVersionEntity = versionService.getLastVersionInfo(VersionConstant.Master_AndroidDownAppId, userId);
		String downPath = appVersionEntity.getVersionDwonUrl();
		downPath = appendParam(downPath, PARAM_FROM, request.getParameter(PARAM_FROM));//syl--2014-10-22 10:31:21-add
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo(downPath);
			appDownLog.setAppType("android");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(request.getHeader("User-Agent"));
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("androidDown insert log cause error", e);
		}

		request.setAttribute("downPath", downPath);
		//		ModelAndView mav = new ModelAndView();
		//		mav.setViewName("qrCode/androidDown");
		//		return mav;
		if (isWenXin(request)) {
			//downPath = "http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
			downPath = "http://www.jiefangqu.com:8080/API300/qrCode/baseDownMaster.html";
		}
		return "redirect:" + downPath;
	}

	@RequestMapping("/androidDown.html")
	public String androidDown(HttpServletRequest request){
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		String os = request.getHeader("User-Agent");
//		AppDownloadConfig appDownloadConfig = appDownloadParamParser.parseParamValue();
//		String downPath = appDownloadConfig.getAndroidDownloadUrl();
		//获取最新的版本信息
		AppVersionEntity appVersionEntity=versionService.getLastVersionInfo(VersionConstant.AndroidDownAppId,userId);
		String downPath = appVersionEntity.getVersionDwonUrl();
		downPath = appendParam(downPath, PARAM_FROM, request.getParameter(PARAM_FROM));//syl--2014-10-22 10:31:21-add
		if(!DataUtil.isEmpty(os) && !DataUtil.isEmpty(downPath)) {//走不同的机型
			downPath = osSwitchDownPath(os, downPath);
		}
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo(downPath);
			appDownLog.setAppType("android");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(os);
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("androidDown insert log cause error",e);
		}
		
		request.setAttribute("downPath", downPath);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("qrCode/androidDown");
//		return mav;
		if(isWenXin(request)){
			downPath ="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
		}
		return "redirect:"+downPath;
	}

	@RequestMapping("/iosDownMaster.html")
	public String iosDownMaster(HttpServletRequest request) {
		logger.info("iosDownMaster start...");
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		//		AppDownloadConfig appDownloadConfig = appDownloadParamParser.parseParamValue();
		//		String downPath = appDownloadConfig.getIosDownloadUrl();
		//获取最新的版本信息
		AppVersionEntity appVersionEntity = versionService.getLastVersionInfo(VersionConstant.Master_IOSDownAppId, userId);
		String downPath = appVersionEntity.getVersionDwonUrl();
		downPath = appendParam(downPath, PARAM_FROM, request.getParameter(PARAM_FROM));//syl--2014-10-22 10:31:21-add
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo(downPath);
			appDownLog.setAppType("ios");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(request.getHeader("User-Agent"));
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("iosDown insert log cause error", e);
		}
		request.setAttribute("downPath", downPath);
		//		ModelAndView mav = new ModelAndView();
		//		mav.setViewName("qrCode/iosDown");
		//		return mav;
		if (isWenXin(request)) {
			downPath = "http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living.master";
		}
		return "redirect:" + downPath;
	}

	@RequestMapping("/iosDown.html")
	public String iosDown(HttpServletRequest request){
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
//		AppDownloadConfig appDownloadConfig = appDownloadParamParser.parseParamValue();
//		String downPath = appDownloadConfig.getIosDownloadUrl();
		//获取最新的版本信息
		AppVersionEntity appVersionEntity=versionService.getLastVersionInfo(VersionConstant.IOSDownAppId,userId);
		String downPath = appVersionEntity.getVersionDwonUrl();
		downPath = appendParam(downPath, PARAM_FROM, request.getParameter(PARAM_FROM));//syl--2014-10-22 10:31:21-add
		try {
			//记录下载日志
			AppDownLog appDownLog = new AppDownLog();
			appDownLog.setAppInfo(downPath);
			appDownLog.setAppType("ios");
			appDownLog.setDevice(null);
			appDownLog.setIp(request.getRemoteAddr());
			appDownLog.setOs(request.getHeader("User-Agent"));
			appDownLog.setOsVer(null);
			appDownLog.setTime(dualDao.getNowTime());
			appDownLogBaseService.insertAppDownLog(appDownLog);
		} catch (Exception e) {
			logger.debug("iosDown insert log cause error",e);
		}
		request.setAttribute("downPath", downPath);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("qrCode/iosDown");
//		return mav;
		if(isWenXin(request)){
			downPath ="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
		}
		return "redirect:"+downPath;
	}
	
	private static boolean isWenXin(HttpServletRequest request){
		//ua.match(/MicroMessenger/i) == 'micromessenger'
		//Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12B435 MicroMessenger/6.0 NetType/WIFI
		String userAgent = request.getHeader("User-Agent");
		return userAgent.toLowerCase().contains("micromessenger");
	}
	
//	public static void main(String[] args) {
//		String src = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12B435 MicroMessenger/6.0 NetType/WIFI";
//		System.out.println(src.toLowerCase().contains("micromessenger"));
//	}

	/**
	 * 根据不同的机型  来返回不同的下载地址
	 * @param os
	 * @param downPath
     * @return
     */
	private static String osSwitchDownPath(String os, String downPath) {
		int start = downPath.indexOf("HynLiving");
		if(start == 0) {
			return downPath;
		}
		String basePath = downPath.substring(0, start);
		if(DataUtil.isEmpty(basePath)) {
			return downPath;
		}
		String apkName = downPath.substring(start, downPath.length());
		if(DataUtil.isEmpty(apkName)) {
			return downPath;
		}
		int i = os.indexOf(":");
		int j = os.indexOf(",");
		if(i == 0 || j == 0) {
			return downPath;
		}
		String phoneOs = os.substring(i + 1, j).toUpperCase();
		if(phoneOs.equalsIgnoreCase("huawei") || phoneOs.equalsIgnoreCase("xiaomi") || phoneOs.equalsIgnoreCase("honor")) {//目前只做华为和小米的特殊包
			if(phoneOs.equalsIgnoreCase("honor")) phoneOs = "HUAWEI";
			downPath = basePath + phoneOs + "/" + apkName;
		}
		return downPath;
	}
	
}
