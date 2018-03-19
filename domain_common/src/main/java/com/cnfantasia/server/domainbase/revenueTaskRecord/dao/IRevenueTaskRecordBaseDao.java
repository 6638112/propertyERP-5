package com.cnfantasia.server.domainbase.revenueTaskRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueTaskRecord.entity.RevenueTaskRecord;

/**
 * 描述:(定时同步收益额记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueTaskRecordBaseDao {
	/**
	 * 根据条件查询(定时同步收益额记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueTaskRecord> selectRevenueTaskRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(定时同步收益额记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueTaskRecord> selectRevenueTaskRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(定时同步收益额记录表)信息
	 * @param id
	 * @return
	 */
	public RevenueTaskRecord selectRevenueTaskRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(定时同步收益额记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueTaskRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(定时同步收益额记录表)新增一条记录
	 * @param revenueTaskRecord
	 * @return
	 */
	public int insertRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord);
	
	/**
	 * 批量新增(定时同步收益额记录表)信息
	 * @param revenueTaskRecordList
	 * @return
	 */
	public int insertRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList);
	
	/**
	 * 更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecord
	 * @return
	 */
	public int updateRevenueTaskRecord(RevenueTaskRecord revenueTaskRecord);
	
	/**
	 * 批量更新(定时同步收益额记录表)信息
	 * @param revenueTaskRecordList
	 * @return
	 */
	public int updateRevenueTaskRecordBatch(List<RevenueTaskRecord> revenueTaskRecordList);
	
	/**
	 * 根据序列号删除(定时同步收益额记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRevenueTaskRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(定时同步收益额记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRevenueTaskRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(定时同步收益额记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueTaskRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(定时同步收益额记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueTaskRecordBatch(List<java.math.BigInteger> idList);
	
	
}
