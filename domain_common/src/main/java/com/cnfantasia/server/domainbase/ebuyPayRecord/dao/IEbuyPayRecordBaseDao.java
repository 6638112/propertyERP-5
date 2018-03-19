package com.cnfantasia.server.domainbase.ebuyPayRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

/**
 * 描述:(支付记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPayRecordBaseDao {
	/**
	 * 根据条件查询(支付记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPayRecord> selectEbuyPayRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(支付记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPayRecord> selectEbuyPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(支付记录表)信息
	 * @param id
	 * @return
	 */
	public EbuyPayRecord selectEbuyPayRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyPayRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(支付记录表)新增一条记录
	 * @param ebuyPayRecord
	 * @return
	 */
	public int insertEbuyPayRecord(EbuyPayRecord ebuyPayRecord);
	
	/**
	 * 批量新增(支付记录表)信息
	 * @param ebuyPayRecordList
	 * @return
	 */
	public int insertEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList);
	
	/**
	 * 更新(支付记录表)信息
	 * @param ebuyPayRecord
	 * @return
	 */
	public int updateEbuyPayRecord(EbuyPayRecord ebuyPayRecord);
	
	/**
	 * 批量更新(支付记录表)信息
	 * @param ebuyPayRecordList
	 * @return
	 */
	public int updateEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList);
	
	/**
	 * 根据序列号删除(支付记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyPayRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(支付记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyPayRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(支付记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyPayRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(支付记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyPayRecordBatch(List<java.math.BigInteger> idList);
	
	
}
