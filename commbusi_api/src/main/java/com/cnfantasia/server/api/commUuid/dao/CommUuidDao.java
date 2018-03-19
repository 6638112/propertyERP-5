package com.cnfantasia.server.api.commUuid.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;
/**
 * 描述:(唯一编号) 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
public class CommUuidDao extends AbstractBaseDao implements ICommUuidDao{
//	private ICommUuidBaseDao commUuidBaseDao;
//	public void setCommUuidBaseDao(ICommUuidBaseDao commUuidBaseDao) {
//		this.commUuidBaseDao = commUuidBaseDao;
//	}

	@Override
	public CommUuid selectCommUuidBySeqId(String tableName) {
		return sqlSession.selectOne("commUuid.select_CommUuid_BySeqId", tableName);
//		return commUuidBaseDao.selectCommUuidBySeqId(tableName);
	}

	@Override
	public Integer updateCommUuid(String tableName,Integer size) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("tableName", tableName);
		tmpMap.put("size", size);
		return sqlSession.update("commUuid.update_CommUuid", tmpMap);
//		return commUuidBaseDao.updateCommUuid(commUuid);
	}
	
	@Override
	public BigInteger fetchAndUpdateUuid(String tableName, Integer size) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("tableName", tableName);
		tmpMap.put("size", size);
		return sqlSession.selectOne("commUuid.fetch_And_Update_Uuid", tmpMap);
	}
	
}
