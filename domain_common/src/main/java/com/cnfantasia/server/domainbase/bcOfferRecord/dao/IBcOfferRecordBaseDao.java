package com.cnfantasia.server.domainbase.bcOfferRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;

/**
 * 描述:(发盘记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcOfferRecordBaseDao {
	/**
	 * 根据条件查询(发盘记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcOfferRecord> selectBcOfferRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(发盘记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcOfferRecord> selectBcOfferRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(发盘记录)信息
	 * @param id
	 * @return
	 */
	public BcOfferRecord selectBcOfferRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(发盘记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcOfferRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(发盘记录)新增一条记录
	 * @param bcOfferRecord
	 * @return
	 */
	public int insertBcOfferRecord(BcOfferRecord bcOfferRecord);
	
	/**
	 * 批量新增(发盘记录)信息
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
	 * 根据Id 批量删除(发盘记录)信息_逻辑删除
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
