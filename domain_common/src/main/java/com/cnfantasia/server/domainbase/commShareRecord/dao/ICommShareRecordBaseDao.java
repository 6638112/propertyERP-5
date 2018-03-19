package com.cnfantasia.server.domainbase.commShareRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commShareRecord.entity.CommShareRecord;

/**
 * 描述:(分享记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommShareRecordBaseDao {
	/**
	 * 根据条件查询(分享记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommShareRecord> selectCommShareRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(分享记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommShareRecord> selectCommShareRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(分享记录表)信息
	 * @param id
	 * @return
	 */
	public CommShareRecord selectCommShareRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(分享记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommShareRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(分享记录表)新增一条记录
	 * @param commShareRecord
	 * @return
	 */
	public int insertCommShareRecord(CommShareRecord commShareRecord);
	
	/**
	 * 批量新增(分享记录表)信息
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
	 * 根据Id 批量删除(分享记录表)信息_逻辑删除
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
