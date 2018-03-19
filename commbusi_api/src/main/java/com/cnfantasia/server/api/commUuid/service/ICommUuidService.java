package com.cnfantasia.server.api.commUuid.service;

import java.math.BigInteger;
import java.util.List;

/**
 * 描述:(唯一编号) 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUuidService /*extends ICommUuidBaseService*/ {
	/**
	 * 获取下个Uuid
	 * 
	 * @param tableName
	 * @return
	 */
	public BigInteger getNextUuidBigInteger(String tableName);

	/**
	 * 获取多个Uuid
	 * 
	 * @param tableName
	 * @param size
	 * @return
	 */
	public List<BigInteger> getNextUuidBigInteger(String tableName, int size);
	
}
