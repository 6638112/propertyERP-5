package com.cnfantasia.server.domainbase.pointCostRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.pointCostRecord.entity.PointCostRecord;

/**
 * 描述:(积分消费记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPointCostRecordBaseService {
	
	/**
	 * 根据条件查询(积分消费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PointCostRecord> getPointCostRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(积分消费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PointCostRecord> getPointCostRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(积分消费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PointCostRecord> getPointCostRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(积分消费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PointCostRecord> getPointCostRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(积分消费记录)信息
	 * @param id
	 * @return
	 */
	public PointCostRecord getPointCostRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(积分消费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPointCostRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(积分消费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPointCostRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(积分消费记录)新增一条记录
	 * @param pointCostRecord
	 * @return
	 */
	public int insertPointCostRecord(PointCostRecord pointCostRecord);
	/**
	 * 批量新增(积分消费记录)
	 * @param pointCostRecordList
	 * @return
	 */
	public int insertPointCostRecordBatch(List<PointCostRecord> pointCostRecordList);
	/**
	 * 更新(积分消费记录)信息
	 * @param pointCostRecord
	 * @return
	 */
	public int updatePointCostRecord(PointCostRecord pointCostRecord);
	/**
	 * 批量更新(积分消费记录)信息
	 * @param pointCostRecordList
	 * @return
	 */
	public int updatePointCostRecordBatch(List<PointCostRecord> pointCostRecordList);
	/**
	 * 根据序列号删除(积分消费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePointCostRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(积分消费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePointCostRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(积分消费记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePointCostRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(积分消费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePointCostRecordBatch(List<java.math.BigInteger> idList);
	
}
