package com.cnfantasia.server.api.common.util;

import java.util.List;

import com.cnfantasia.server.api.common.service.AppVersionService;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

public class LicaiUil {
	
	private static AppVersionService appVersionService = (AppVersionService) CnfantasiaCommbusiUtil.getBeanManager("appVersionService");
	
	public static void filter(List<? extends LicaiDo> resList) {
		Integer version = HeaderManager.getVersionNum();
		Long subChannelId = HeaderManager.getSubChannelIdLong();
		int maxersion = appVersionService.getMaxIosVersion();
		if(version != null && version > maxersion && HeaderConstant.SubChannelId.ISO_Phone.equals(subChannelId)) {
			for(int i = resList.size() - 1; i >=0; i--) {
				if(resList.get(i).isLicai()) {
					resList.remove(i);
				}
			}
		}
	}

}
