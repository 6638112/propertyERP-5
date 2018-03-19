package com.cnfantasia.server.domainbase.easPushRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.easPushRecord.entity.EasPushRecord;

/**
 * 描述:(EAS接口推送记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEasPushRecordBaseService {
	
	/**
	 * 根据条件查询(EAS接口推送记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EasPushRecord> getEasPushRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(EAS接口推送记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EasPushRecord> getEasPushRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(EAS接口推送记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EasPushRecord> getEasPushRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(EAS接口推送记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EasPushRecord> getEasPushRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(EAS接口推送记录)信息
	 * @param id
	 * @return
	 */
	public EasPushRecord getEasPushRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEasPushRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEasPushRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(EAS接口推送记录)新增一条记录
	 * @param easPushRecord
	 * @return
	 */
	public int insertEasPushRecord(EasPushRecord easPushRecord);
	/**
	 * 批量新增(EAS接口推送记录)
	 * @param easPushRecordList
	 * @return
	 */
	public int insertEasPushRecordBatch(List<EasPushRecord> easPushRecordList);
	/**
	 * 更新(EAS接口推送记录)信息
	 * @param easPushRecord
	 * @return
	 */
	public int updateEasPushRecord(EasPushRecord easPushRecord);
	/**
	 * 批量更新(EAS接口推送记录)信息
	 * @param easPushRecordList
	 * @return
	 */
	public int updateEasPushRecordBatch(List<EasPushRecord> easPushRecordList);
	/**
	 * 根据序列号删除(EAS接口推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEasPushRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(EAS接口推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEasPushRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(EAS接口推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEasPushRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(EAS接口推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEasPushRecordBatch(List<java.math.BigInteger> idList);
	
}
