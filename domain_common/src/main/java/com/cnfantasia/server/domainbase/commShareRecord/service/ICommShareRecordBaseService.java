package com.cnfantasia.server.domainbase.commShareRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commShareRecord.entity.CommShareRecord;

/**
 * 描述:(分享记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommShareRecordBaseService {
	
	/**
	 * 根据条件查询(分享记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommShareRecord> getCommShareRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(分享记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommShareRecord> getCommShareRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(分享记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommShareRecord> getCommShareRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(分享记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommShareRecord> getCommShareRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(分享记录表)信息
	 * @param id
	 * @return
	 */
	public CommShareRecord getCommShareRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommShareRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommShareRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(分享记录表)新增一条记录
	 * @param commShareRecord
	 * @return
	 */
	public int insertCommShareRecord(CommShareRecord commShareRecord);
	/**
	 * 批量新增(分享记录表)
	 * @param commShareRecordList
	 * @return
	 */
	public int insertCommShareRecordBatch(List<CommShareRecord> commShareRecordList);
	/**
	 * 更新(分享记录表)信息
	 * @param commShareRecord
	 * @return
	 */
	public int updateCommShareRecord(CommShareRecord commShareRecord);
	/**
	 * 批量更新(分享记录表)信息
	 * @param commShareRecordList
	 * @return
	 */
	public int updateCommShareRecordBatch(List<CommShareRecord> commShareRecordList);
	/**
	 * 根据序列号删除(分享记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommShareRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(分享记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommShareRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(分享记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommShareRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(分享记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommShareRecordBatch(List<java.math.BigInteger> idList);
	
}
