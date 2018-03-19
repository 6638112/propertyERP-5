package com.cnfantasia.server.api.ebuy.dao;

import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class EbuyIdentifyInfoDao extends AbstractBaseDao {

	public int insertIdentifyInfo(EbuyIdentifyInfo identifyInfo) {
		return sqlSession.insert("ebuyIdentifyInfo.insertIdentifyInfo", identifyInfo);
	}
	
	public int updateIdentifyInfo(EbuyIdentifyInfo identifyInfo) {
		return sqlSession.update("ebuyIdentifyInfo.updateIdentifyInfo", identifyInfo);
	}
	
	public EbuyIdentifyInfo getIdentifyInfo(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyIdentifyInfo.getIdentifyInfo", paramMap);
	}
	
	public int delIdentifyInfo(Map<String, Object> paramMap) {
		return sqlSession.update("ebuyIdentifyInfo.delIdentifyInfo", paramMap);
	}
	
}
