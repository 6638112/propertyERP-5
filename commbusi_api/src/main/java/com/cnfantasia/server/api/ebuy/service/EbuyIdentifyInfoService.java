package com.cnfantasia.server.api.ebuy.service;

import java.util.Map;

import com.cnfantasia.server.api.ebuy.dao.EbuyIdentifyInfoDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;

public class EbuyIdentifyInfoService {
	
	private EbuyIdentifyInfoDao ebuyIdentifyInfoDao;
	
	public int insertIdentifyInfo(EbuyIdentifyInfo identifyInfo) {
		return ebuyIdentifyInfoDao.insertIdentifyInfo(identifyInfo);
	}
	
	public int updateIdentifyInfo(EbuyIdentifyInfo identifyInfo) {
		return ebuyIdentifyInfoDao.updateIdentifyInfo(identifyInfo);
	}
	
	public EbuyIdentifyInfo getIdentifyInfo(Map<String, Object> paramMap) {
		return ebuyIdentifyInfoDao.getIdentifyInfo(paramMap);
	}
	
	public int delIdentifyInfo(Map<String, Object> paramMap) {
		return ebuyIdentifyInfoDao.delIdentifyInfo(paramMap);
	}

	public void setEbuyIdentifyInfoDao(EbuyIdentifyInfoDao ebuyIdentifyInfoDao) {
		this.ebuyIdentifyInfoDao = ebuyIdentifyInfoDao;
	}

}
