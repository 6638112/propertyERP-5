package com.cnfantasia.server.api.common.dao;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class CommonLockDao extends AbstractBaseDao implements ICommonLockDao{
	
	@Override
	public void getLock(String tableName) {
		sqlSession.selectOne("commonLock.getLock", tableName);
	}

}
