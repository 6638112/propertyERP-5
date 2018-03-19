package com.cnfantasia.server.api.common.service;

import com.cnfantasia.server.api.common.dao.AppVersionDao;


public class AppVersionService {
	
	private AppVersionDao appVersionDao;
	
	public int getMaxIosVersion() {
		return appVersionDao.getMaxIosVersion();
	}

	public void setAppVersionDao(AppVersionDao appVersionDao) {
		this.appVersionDao = appVersionDao;
	}
	
}
