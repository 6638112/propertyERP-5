package com.cnfantasia.server.domainbase.livingFeeSp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeeSp.entity.LivingFeeSp;

/**
 * 描述:(宽带运营商) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILivingFeeSpBaseDao {
	/**
	 * 根据条件查询(宽带运营商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LivingFeeSp> selectLivingFeeSpByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(宽带运营商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<LivingFeeSp> selectLivingFeeSpByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(宽带运营商)信息
	 * @param id
	 * @return
	 */
	LivingFeeSp selectLivingFeeSpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectLivingFeeSpCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(宽带运营商)新增一条记录
	 * @param livingFeeSp
	 * @return
	 */
	int insertLivingFeeSp(LivingFeeSp livingFeeSp);
	
	/**
	 * 批量新增(宽带运营商)信息
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
	 * 根据Id 批量删除(宽带运营商)信息_逻辑删除
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
