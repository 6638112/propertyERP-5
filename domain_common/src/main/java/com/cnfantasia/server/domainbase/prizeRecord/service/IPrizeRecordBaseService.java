package com.cnfantasia.server.domainbase.prizeRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;

/**
 * 描述:(中奖记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrizeRecordBaseService {
	
	/**
	 * 根据条件查询(中奖记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRecord> getPrizeRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(中奖记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrizeRecord> getPrizeRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(中奖记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecord> getPrizeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(中奖记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecord> getPrizeRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(中奖记录)信息
	 * @param id
	 * @return
	 */
	public PrizeRecord getPrizeRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(中奖记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrizeRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(中奖记录)新增一条记录
	 * @param prizeRecord
	 * @return
	 */
	public int insertPrizeRecord(PrizeRecord prizeRecord);
	/**
	 * 批量新增(中奖记录)
	 * @param prizeRecordList
	 * @return
	 */
	public int insertPrizeRecordBatch(List<PrizeRecord> prizeRecordList);
	/**
	 * 更新(中奖记录)信息
	 * @param prizeRecord
	 * @return
	 */
	public int updatePrizeRecord(PrizeRecord prizeRecord);
	/**
	 * 批量更新(中奖记录)信息
	 * @param prizeRecordList
	 * @return
	 */
	public int updatePrizeRecordBatch(List<PrizeRecord> prizeRecordList);
	/**
	 * 根据序列号删除(中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrizeRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrizeRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrizeRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrizeRecordBatch(List<java.math.BigInteger> idList);
	
}
