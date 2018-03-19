package com.cnfantasia.server.api.common.dao;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class AppVersionDao extends AbstractBaseDao{
	
	public int getMaxIosVersion() {
		return sqlSession.selectOne("appVersion.getMaxIosVersion");
	}
	

}
