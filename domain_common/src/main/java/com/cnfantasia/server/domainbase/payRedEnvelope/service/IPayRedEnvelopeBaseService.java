package com.cnfantasia.server.domainbase.payRedEnvelope.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * 描述:(粮票信息表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayRedEnvelopeBaseService {
	
	/**
	 * 根据条件查询(粮票信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PayRedEnvelope> getPayRedEnvelopeByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(粮票信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PayRedEnvelope> getPayRedEnvelopeByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(粮票信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayRedEnvelope> getPayRedEnvelopeByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(粮票信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayRedEnvelope> getPayRedEnvelopeByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(粮票信息表)信息
	 * @param id
	 * @return
	 */
	PayRedEnvelope getPayRedEnvelopeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPayRedEnvelopeCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPayRedEnvelopeCountDim(Map<String, Object> paramMap);
	/**
	 * 往(粮票信息表)新增一条记录
	 * @param payRedEnvelope
	 * @return
	 */
	int insertPayRedEnvelope(PayRedEnvelope payRedEnvelope);
	/**
	 * 批量新增(粮票信息表)
	 * @param payRedEnvelopeList
	 * @return
	 */
	int insertPayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList);
	/**
	 * 更新(粮票信息表)信息
	 * @param payRedEnvelope
	 * @return
	 */
	int updatePayRedEnvelope(PayRedEnvelope payRedEnvelope);
	/**
	 * 批量更新(粮票信息表)信息
	 * @param payRedEnvelopeList
	 * @return
	 */
	int updatePayRedEnvelopeBatch(List<PayRedEnvelope> payRedEnvelopeList);
	/**
	 * 根据序列号删除(粮票信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deletePayRedEnvelopeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(粮票信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deletePayRedEnvelopeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(粮票信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayRedEnvelope(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(粮票信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayRedEnvelopeBatch(List<java.math.BigInteger> idList);
	
}
