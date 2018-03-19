package com.cnfantasia.server.domainbase.commUuid.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;

/**
 * 描述:(唯一编号) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUuidBaseDao {
	/**
	 * 根据条件查询(唯一编号)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommUuid> selectCommUuidByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(唯一编号)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommUuid> selectCommUuidByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(唯一编号)信息
	 * @param tableName
	 * @return
	 */
	public CommUuid selectCommUuidBySeqId(java.lang.String tableName);
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommUuidCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(唯一编号)新增一条记录
	 * @param commUuid
	 * @return
	 */
	public int insertCommUuid(CommUuid commUuid);
	
	/**
	 * 批量新增(唯一编号)信息
	 * @param commUuidList
	 * @return
	 */
	public int insertCommUuidBatch(List<CommUuid> commUuidList);
	
	/**
	 * 更新(唯一编号)信息
	 * @param commUuid
	 * @return
	 */
	public int updateCommUuid(CommUuid commUuid);
	
	/**
	 * 批量更新(唯一编号)信息
	 * @param commUuidList
	 * @return
	 */
	public int updateCommUuidBatch(List<CommUuid> commUuidList);
	
	/**
	 * 根据序列号删除(唯一编号)信息_逻辑删除
	 * @param tableName
	 * @return
	 */
	
	public int deleteCommUuidLogic(java.lang.String tableName);
	
	/**
	 * 根据Id 批量删除(唯一编号)信息_逻辑删除
	 * @param tableNameList
	 * @return
	 */
	
	public int deleteCommUuidLogicBatch(List<java.lang.String> tableNameList);
	
	
//	/**
//	 * 根据序列号删除(唯一编号)信息
//	 * @param tableName
//	 * @return
//	 */
//	public int deleteCommUuid(java.lang.String tableName);
//	
//	/**
//	 * 根据序列号批量删除(唯一编号)信息
//	 * @param tableNameList
//	 * @return
//	 */
//	public int deleteCommUuidBatch(List<java.lang.String> tableNameList);
	
	
}
