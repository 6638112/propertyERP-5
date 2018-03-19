package com.cnfantasia.server.domainbase.payConfigHb.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payConfigHb.entity.PayConfigHb;

/**
 * 描述:(粮票支付配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayConfigHbBaseService {
	
	/**
	 * 根据条件查询(粮票支付配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<PayConfigHb> getPayConfigHbByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(粮票支付配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<PayConfigHb> getPayConfigHbByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(粮票支付配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayConfigHb> getPayConfigHbByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(粮票支付配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<PayConfigHb> getPayConfigHbByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(粮票支付配置表)信息
	 * @param id
	 * @return
	 */
	PayConfigHb getPayConfigHbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(粮票支付配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getPayConfigHbCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(粮票支付配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getPayConfigHbCountDim(Map<String, Object> paramMap);
	/**
	 * 往(粮票支付配置表)新增一条记录
	 * @param payConfigHb
	 * @return
	 */
	int insertPayConfigHb(PayConfigHb payConfigHb);
	/**
	 * 批量新增(粮票支付配置表)
	 * @param payConfigHbList
	 * @return
	 */
	int insertPayConfigHbBatch(List<PayConfigHb> payConfigHbList);
	/**
	 * 更新(粮票支付配置表)信息
	 * @param payConfigHb
	 * @return
	 */
	int updatePayConfigHb(PayConfigHb payConfigHb);
	/**
	 * 批量更新(粮票支付配置表)信息
	 * @param payConfigHbList
	 * @return
	 */
	int updatePayConfigHbBatch(List<PayConfigHb> payConfigHbList);
	/**
	 * 根据序列号删除(粮票支付配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deletePayConfigHbLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(粮票支付配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deletePayConfigHbLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(粮票支付配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayConfigHb(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(粮票支付配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayConfigHbBatch(List<java.math.BigInteger> idList);
	
}
