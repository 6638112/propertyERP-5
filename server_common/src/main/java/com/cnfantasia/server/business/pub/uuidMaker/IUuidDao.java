package com.cnfantasia.server.business.pub.uuidMaker;


/**
 * 描述:(唯一编号) 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUuidDao<T>{
	/**
	 * 根据序列号查询(唯一编号)信息
	 * @param tableName
	 * @return
	 */
	public T selectCommUuidBySeqId(java.lang.String tableName);
	/**
	 * 更新(唯一编号)信息
	 * @param commUuid
	 * @return
	 */
//	public Integer updateCommUuid(CommUuid commUuid);
	public Integer updateCommUuid(String tableName,Integer size);
}
