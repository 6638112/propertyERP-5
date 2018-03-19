package com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.entity.OmsUserHasTEbuySupplyMerchant;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasTEbuySupplyMerchantBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> getOmsUserHasTEbuySupplyMerchantByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public OmsUserHasTEbuySupplyMerchant getOmsUserHasTEbuySupplyMerchantBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasTEbuySupplyMerchantCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasTEbuySupplyMerchantCountDim(Map<String, Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	public int insertOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant);
	/**
	 * 批量新增()
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	public int insertOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList);
	/**
	 * 更新()信息
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	public int updateOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant);
	/**
	 * 批量更新()信息
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	public int updateOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsUserHasTEbuySupplyMerchantLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsUserHasTEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsUserHasTEbuySupplyMerchant(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsUserHasTEbuySupplyMerchantBatch(List<java.math.BigInteger> idList);
	
}
