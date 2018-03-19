package com.cnfantasia.server.domainbase.commUuid.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;

/**
 * 描述:(唯一编号) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUuidBaseService {
	
	/**
	 * 根据条件查询(唯一编号)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommUuid> getCommUuidByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(唯一编号)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommUuid> getCommUuidByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(唯一编号)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommUuid> getCommUuidByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(唯一编号)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommUuid> getCommUuidByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(唯一编号)信息
	 * @param tableName
	 * @return
	 */
	public CommUuid getCommUuidBySeqId(java.lang.String tableName);
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommUuidCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommUuidCountDim(Map<String,Object> paramMap);
	/**
	 * 往(唯一编号)新增一条记录
	 * @param commUuid
	 * @return
	 */
	public int insertCommUuid(CommUuid commUuid);
	/**
	 * 批量新增(唯一编号)
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
	 * 根据序列号批量删除(唯一编号)信息_逻辑删除
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
