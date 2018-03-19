package com.cnfantasia.server.domainbase.dataChangeRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dataChangeRecord.entity.DataChangeRecord;

/**
 * 描述:(数据变更记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDataChangeRecordBaseService {
	
	/**
	 * 根据条件查询(数据变更记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DataChangeRecord> getDataChangeRecordByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(数据变更记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DataChangeRecord> getDataChangeRecordByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(数据变更记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DataChangeRecord> getDataChangeRecordByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(数据变更记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DataChangeRecord> getDataChangeRecordByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(数据变更记录)信息
	 * @param id
	 * @return
	 */
	public DataChangeRecord getDataChangeRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(数据变更记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDataChangeRecordCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(数据变更记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDataChangeRecordCountDim(Map<String, Object> paramMap);
	/**
	 * 往(数据变更记录)新增一条记录
	 * @param dataChangeRecord
	 * @return
	 */
	public int insertDataChangeRecord(DataChangeRecord dataChangeRecord);
	/**
	 * 批量新增(数据变更记录)
	 * @param dataChangeRecordList
	 * @return
	 */
	public int insertDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList);
	/**
	 * 更新(数据变更记录)信息
	 * @param dataChangeRecord
	 * @return
	 */
	public int updateDataChangeRecord(DataChangeRecord dataChangeRecord);
	/**
	 * 批量更新(数据变更记录)信息
	 * @param dataChangeRecordList
	 * @return
	 */
	public int updateDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList);
	/**
	 * 根据序列号删除(数据变更记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDataChangeRecordLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(数据变更记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDataChangeRecordLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(数据变更记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDataChangeRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(数据变更记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDataChangeRecordBatch(List<java.math.BigInteger> idList);
	
}
