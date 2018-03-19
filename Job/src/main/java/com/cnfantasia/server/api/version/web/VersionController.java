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
import com.cnfantasia.server.api.version.constant.VersionDict;
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
		boolean needUpdate = false;
		//查询最新版本信息
		AppVersionEntity lastVersion = versionService.getLastVersionInfo(appId,userId);
		if(lastVersion==null){//没有版本信息
			throw new BusinessRuntimeException("VersionController.checkUpd.versionInfo.isNull");
		}
		//比对
		if(lastVersion.getVersion().compareTo(versionLong)>0){
			needUpdate = true;
		}else{
			needUpdate = false;
		}
		//3.结果处理
		jsonResponse.putData("appId", lastVersion.gettAppBaseInfoFId());
		jsonResponse.putData("appName", lastVersion.getAppBaseInfo().getName());
		jsonResponse.putData("version", VersionTransferUtil.long2VersionStr(lastVersion.getVersion()));
		if(VersionDict.AppVersion_ForceUpdate.TRUE.compareTo(lastVersion.getForceUpdate())==0){
			jsonResponse.putData("forceUpdate", true);
		}else{
			jsonResponse.putData("forceUpdate", false);
		}
		jsonResponse.putData("url", lastVersion.fetchDownloadUrl());
		jsonResponse.putData("textDesc", lastVersion.getTextDesc());
//		String[] picDesc = new String[]{"/a/001.jpg","/a/002.jpg","/a/003.jpg"};
		jsonResponse.putData("picDesc", lastVersion.getPicDesc());
		jsonResponse.putData("releaseDate", lastVersion.getCreateTime());
		jsonResponse.putData("needUpdate", needUpdate);
		return JSON.toJSONString(jsonResponse);
	}
}
