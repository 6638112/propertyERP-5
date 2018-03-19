package com.cnfantasia.server.domainbase.livingFeePayRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeePayRecord.entity.LivingFeePayRecord;

/**
 * 描述:(生活缴费记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILivingFeePayRecordBaseDao {
	/**
	 * 根据条件查询(生活缴费记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LivingFeePayRecord> selectLivingFeePayRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(生活缴费记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LivingFeePayRecord> selectLivingFeePayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(生活缴费记录)信息
	 * @param id
	 * @return
	 */
	public LivingFeePayRecord selectLivingFeePayRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(生活缴费记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLivingFeePayRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(生活缴费记录)新增一条记录
	 * @param livingFeePayRecord
	 * @return
	 */
	public int insertLivingFeePayRecord(LivingFeePayRecord livingFeePayRecord);
	
	/**
	 * 批量新增(生活缴费记录)信息
	 * @param livingFeePayRecordList
	 * @return
	 */
	public int insertLivingFeePayRecordBatch(List<LivingFeePayRecord> livingFeePayRecordList);
	
	/**
	 * 更新(生活缴费记录)信息
	 * @param livingFeePayRecord
	 * @return
	 */
	public int updateLivingFeePayRecord(LivingFeePayRecord livingFeePayRecord);
	
	/**
	 * 批量更新(生活缴费记录)信息
	 * @param livingFeePayRecordList
	 * @return
	 */
	public int updateLivingFeePayRecordBatch(List<LivingFeePayRecord> livingFeePayRecordList);
	
	/**
	 * 根据序列号删除(生活缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLivingFeePayRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(生活缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLivingFeePayRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(生活缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLivingFeePayRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(生活缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLivingFeePayRecordBatch(List<java.math.BigInteger> idList);
	
	
}
