package com.cnfantasia.server.api.cache.dao;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

public class Cache2Dao extends AbstractBaseDao implements ICache2Dao {
	
	@Override
	public void clearCacheForCommunitySupply() {
		sqlSession.selectOne("communitySupply.cleanCache");
	}

	@Override
	public void clearCacheForEbuyAdvertise() {
		sqlSession.selectOne("ebuyAdvertise.cleanCache");
	}

	@Override
	public void clearCacheForEbuyNew() {
		sqlSession.selectOne("ebuyNew.cleanCache");
	}

	@Override
	public void clearCacheForEncrypt() {
		sqlSession.selectOne("encrypt.cleanCache");
	}

	@Override
	public void clearCacheForOperation() {
		sqlSession.selectOne("operation.cleanCache");
	}

	@Override
	public void clearCacheForVersion() {
		sqlSession.selectOne("version.cleanCache");
	}

	public void clearAppFunctionBar() {
		sqlSession.selectOne("appFunctionBar.cleanCache");
	}

}
