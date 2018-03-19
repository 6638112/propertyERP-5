package com.cnfantasia.server.api.version.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.version.constant.VersionConstant;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.service.IVersionService;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

@Controller
@RequestMapping("/version")
public class VersionController extends BaseController{
	private IVersionService versionService;
	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}
	
	
	@RequestMapping("/checkUpd.json")
	@ResponseBody
	public String checkUpd(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String version = request.getParameter("version");
		if(StringUtils.isEmpty(version)){
			version=AppVersionEntity.DEFAULT_START_VERSION;
		}
		BigInteger appId = null;
		try {
			appId = new BigInteger(request.getParameter("appId"));
		} catch (Exception e) {
			if(HeaderManager.getSubChannelIdLong().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID))==0){
				appId = VersionConstant.AndroidDownAppId;
			}else if(HeaderManager.getSubChannelIdLong().compareTo(Long.valueOf(DictConstants.Channel_Sub.IOS))==0){
				appId = VersionConstant.IOSDownAppId;
			}else{
				throw new ValidateRuntimeException("VersionController.checkUpd.unknown.appId");
			}
		}
		Long versionLong = VersionTransferUtil.versionStr2Long(version);
		BigInteger userId = VersionUtil.getUserIdForUpd(request);
		//2.交互
		//查询最新版本信息
		AppVersionEntity lastForceVersion = versionService.getLastVersionInfo(appId,userId,1);//检查有无强升版本 added by wenfq 20160920
		AppVersionEntity lastVersion = versionService.getLastVersionInfo(appId,userId);
		
		if(lastVersion==null){//没有版本信息
			throw new BusinessRuntimeException("VersionController.checkUpd.versionInfo.isNull");
		}
		
		//比对
		boolean needUpdate = lastVersion.getVersion().compareTo(versionLong)>0;
		
		//3.结果处理
		jsonResponse.putData("appId", lastVersion.gettAppBaseInfoFId());
		jsonResponse.putData("appName", lastVersion.getAppBaseInfo().getName());
		jsonResponse.putData("version", VersionTransferUtil.long2VersionStr(lastVersion.getVersion()));
		jsonResponse.putData("forceUpdate", lastForceVersion.getVersion().compareTo(versionLong) > 0);
		jsonResponse.putData("url", lastVersion.fetchDownloadUrl());
		jsonResponse.putData("textDesc", lastVersion.getTextDesc());
//		String[] picDesc = new String[]{"/a/001.jpg","/a/002.jpg","/a/003.jpg"};
		jsonResponse.putData("picDesc", lastVersion.getPicDesc());
		jsonResponse.putData("releaseDate", lastVersion.getCreateTime());
		jsonResponse.putData("needUpdate", needUpdate);
		return JSON.toJSONString(jsonResponse);
	}
}
