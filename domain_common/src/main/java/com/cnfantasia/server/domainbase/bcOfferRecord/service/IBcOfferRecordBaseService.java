package com.cnfantasia.server.domainbase.bcOfferRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;

/**
 * 描述:(发盘记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcOfferRecordBaseService {
	
	/**
	 * 根据条件查询(发盘记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcOfferRecord> getBcOfferRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(发盘记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcOfferRecord> getBcOfferRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(发盘记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcOfferRecord> getBcOfferRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(发盘记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcOfferRecord> getBcOfferRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(发盘记录)信息
	 * @param id
	 * @return
	 */
	public BcOfferRecord getBcOfferRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcOfferRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcOfferRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(发盘记录)新增一条记录
	 * @param bcOfferRecord
	 * @return
	 */
	public int insertBcOfferRecord(BcOfferRecord bcOfferRecord);
	/**
	 * 批量新增(发盘记录)
	 * @param bcOfferRecordList
	 * @return
	 */
	public int insertBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList);
	/**
	 * 更新(发盘记录)信息
	 * @param bcOfferRecord
	 * @return
	 */
	public int updateBcOfferRecord(BcOfferRecord bcOfferRecord);
	/**
	 * 批量更新(发盘记录)信息
	 * @param bcOfferRecordList
	 * @return
	 */
	public int updateBcOfferRecordBatch(List<BcOfferRecord> bcOfferRecordList);
	/**
	 * 根据序列号删除(发盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteBcOfferRecordLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(发盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteBcOfferRecordLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(发盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcOfferRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(发盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcOfferRecordBatch(List<java.math.BigInteger> idList);
	
}
