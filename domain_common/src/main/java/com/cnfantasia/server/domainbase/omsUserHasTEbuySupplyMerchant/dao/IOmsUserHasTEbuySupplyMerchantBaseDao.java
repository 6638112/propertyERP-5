package com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.entity.OmsUserHasTEbuySupplyMerchant;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasTEbuySupplyMerchantBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> selectOmsUserHasTEbuySupplyMerchantByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasTEbuySupplyMerchant> selectOmsUserHasTEbuySupplyMerchantByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public OmsUserHasTEbuySupplyMerchant selectOmsUserHasTEbuySupplyMerchantBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsUserHasTEbuySupplyMerchantCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	public int insertOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
