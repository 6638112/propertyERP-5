package com.cnfantasia.server.domainbase.payRedEnvelopeDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;

/**
 * 描述:(粮票优惠详情) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayRedEnvelopeDetailBaseService {
	
	/**
	 * 根据条件查询(粮票优惠详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(粮票优惠详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(粮票优惠详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(粮票优惠详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayRedEnvelopeDetail> getPayRedEnvelopeDetailByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(粮票优惠详情)信息
	 * @param id
	 * @return
	 */
	PayRedEnvelopeDetail getPayRedEnvelopeDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(粮票优惠详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPayRedEnvelopeDetailCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(粮票优惠详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPayRedEnvelopeDetailCountDim(Map<String, Object> paramMap);
	/**
	 * 往(粮票优惠详情)新增一条记录
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	int insertPayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail);
	/**
	 * 批量新增(粮票优惠详情)
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	int insertPayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList);
	/**
	 * 更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetail
	 * @return
	 */
	int updatePayRedEnvelopeDetail(PayRedEnvelopeDetail payRedEnvelopeDetail);
	/**
	 * 批量更新(粮票优惠详情)信息
	 * @param payRedEnvelopeDetailList
	 * @return
	 */
	int updatePayRedEnvelopeDetailBatch(List<PayRedEnvelopeDetail> payRedEnvelopeDetailList);
	/**
	 * 根据序列号删除(粮票优惠详情)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deletePayRedEnvelopeDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(粮票优惠详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deletePayRedEnvelopeDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(粮票优惠详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayRedEnvelopeDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(粮票优惠详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayRedEnvelopeDetailBatch(List<java.math.BigInteger> idList);
	
}
