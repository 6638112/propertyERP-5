package com.cnfantasia.server.domainbase.ebuyPayRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

/**
 * 描述:(支付记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPayRecordBaseService {
	
	/**
	 * 根据条件查询(支付记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPayRecord> getEbuyPayRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(支付记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPayRecord> getEbuyPayRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(支付记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPayRecord> getEbuyPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(支付记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPayRecord> getEbuyPayRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(支付记录表)信息
	 * @param id
	 * @return
	 */
	public EbuyPayRecord getEbuyPayRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPayRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPayRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(支付记录表)新增一条记录
	 * @param ebuyPayRecord
	 * @return
	 */
	public int insertEbuyPayRecord(EbuyPayRecord ebuyPayRecord);
	/**
	 * 批量新增(支付记录表)
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
	 * 根据序列号批量删除(支付记录表)信息_逻辑删除
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
