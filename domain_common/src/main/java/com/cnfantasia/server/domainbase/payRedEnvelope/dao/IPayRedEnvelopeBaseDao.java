package com.cnfantasia.server.domainbase.payRedEnvelope.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * 描述:(粮票信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayRedEnvelopeBaseDao {
	/**
	 * 根据条件查询(粮票信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<PayRedEnvelope> selectPayRedEnvelopeByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(粮票信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<PayRedEnvelope> selectPayRedEnvelopeByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(粮票信息表)信息
	 * @param id
	 * @return
	 */
	PayRedEnvelope selectPayRedEnvelopeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(粮票信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectPayRedEnvelopeCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(粮票信息表)新增一条记录
	 * @param payRedEnvelope
	 * @return
	 */
	int insertPayRedEnvelope(PayRedEnvelope payRedEnvelope);
	
	/**
	 * 批量新增(粮票信息表)信息
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
	 * 根据Id 批量删除(粮票信息表)信息_逻辑删除
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
