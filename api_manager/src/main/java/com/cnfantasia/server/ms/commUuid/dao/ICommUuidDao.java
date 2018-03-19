package com.cnfantasia.server.ms.commUuid.dao;

import java.math.BigInteger;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidDao;
import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;
/**
 * 描述:(唯一编号) 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUuidDao extends IUuidDao<CommUuid>{
	
	/**
	 * 获取并更新对应Id
	 * @param tableName
	 * @return 返回获取的第一个Id
	 */
	public BigInteger fetchAndUpdateUuid(String tableName,Integer size);
}
