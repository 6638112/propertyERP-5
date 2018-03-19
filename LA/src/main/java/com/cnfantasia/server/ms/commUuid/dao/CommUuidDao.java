package com.cnfantasia.server.ms.commUuid.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
/**
 * 描述:(唯一编号) 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommUuidDao extends AbstractBaseDao implements ICommUuidDao{

	@Override
	public CommUuid selectCommUuidBySeqId(String tableName) {
		return sqlSession.selectOne("commUuid.select_CommUuid_BySeqId", tableName);
	}

	@Override
	public Integer updateCommUuid(String tableName,Integer size) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("tableName", tableName);
		tmpMap.put("size", size);
		return sqlSession.update("commUuid.update_CommUuid", tmpMap);
	}

	@Override
	public BigInteger fetchAndUpdateUuid(String tableName, Integer size) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("tableName", tableName);
		tmpMap.put("size", size);
		return sqlSession.selectOne("commUuid.fetch_And_Update_Uuid", tmpMap);
	}
	
}
