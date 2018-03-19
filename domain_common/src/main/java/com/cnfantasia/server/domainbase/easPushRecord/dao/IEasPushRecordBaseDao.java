package com.cnfantasia.server.domainbase.easPushRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushRecord.entity.EasPushRecord;

/**
 * 描述:(EAS接口推送记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEasPushRecordBaseDao {
	/**
	 * 根据条件查询(EAS接口推送记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EasPushRecord> selectEasPushRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(EAS接口推送记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EasPushRecord> selectEasPushRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(EAS接口推送记录)信息
	 * @param id
	 * @return
	 */
	public EasPushRecord selectEasPushRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(EAS接口推送记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEasPushRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(EAS接口推送记录)新增一条记录
	 * @param easPushRecord
	 * @return
	 */
	public int insertEasPushRecord(EasPushRecord easPushRecord);
	
	/**
	 * 批量新增(EAS接口推送记录)信息
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
	 * 根据Id 批量删除(EAS接口推送记录)信息_逻辑删除
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
