package com.cnfantasia.server.domainbase.livingFeeSp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.livingFeeSp.entity.LivingFeeSp;

/**
 * 描述:(宽带运营商) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILivingFeeSpBaseService {
	
	/**
	 * 根据条件查询(宽带运营商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<LivingFeeSp> getLivingFeeSpByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(宽带运营商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<LivingFeeSp> getLivingFeeSpByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(宽带运营商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LivingFeeSp> getLivingFeeSpByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(宽带运营商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<LivingFeeSp> getLivingFeeSpByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(宽带运营商)信息
	 * @param id
	 * @return
	 */
	LivingFeeSp getLivingFeeSpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getLivingFeeSpCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getLivingFeeSpCountDim(Map<String, Object> paramMap);
	/**
	 * 往(宽带运营商)新增一条记录
	 * @param livingFeeSp
	 * @return
	 */
	int insertLivingFeeSp(LivingFeeSp livingFeeSp);
	/**
	 * 批量新增(宽带运营商)
	 * @param livingFeeSpList
	 * @return
	 */
	int insertLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList);
	/**
	 * 更新(宽带运营商)信息
	 * @param livingFeeSp
	 * @return
	 */
	int updateLivingFeeSp(LivingFeeSp livingFeeSp);
	/**
	 * 批量更新(宽带运营商)信息
	 * @param livingFeeSpList
	 * @return
	 */
	int updateLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList);
	/**
	 * 根据序列号删除(宽带运营商)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteLivingFeeSpLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(宽带运营商)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteLivingFeeSpLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(宽带运营商)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLivingFeeSp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(宽带运营商)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLivingFeeSpBatch(List<java.math.BigInteger> idList);
	
}
