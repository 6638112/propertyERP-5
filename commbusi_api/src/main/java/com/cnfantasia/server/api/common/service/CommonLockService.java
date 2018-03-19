package com.cnfantasia.server.api.common.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.dao.ICommonLockDao;


public class CommonLockService implements ICommonLockService{
	
	private ICommonLockDao commonLockDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void getLock(String tableName) {
		commonLockDao.getLock(tableName);
	}

	
	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

}
