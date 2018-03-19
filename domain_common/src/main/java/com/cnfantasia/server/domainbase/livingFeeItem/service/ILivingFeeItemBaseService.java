package com.cnfantasia.server.domainbase.livingFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.livingFeeItem.entity.LivingFeeItem;

/**
 * 描述:(生活缴费支持的项目类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILivingFeeItemBaseService {
	
	/**
	 * 根据条件查询(生活缴费支持的项目类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LivingFeeItem> getLivingFeeItemByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(生活缴费支持的项目类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LivingFeeItem> getLivingFeeItemByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(生活缴费支持的项目类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LivingFeeItem> getLivingFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(生活缴费支持的项目类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LivingFeeItem> getLivingFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(生活缴费支持的项目类别)信息
	 * @param id
	 * @return
	 */
	public LivingFeeItem getLivingFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(生活缴费支持的项目类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLivingFeeItemCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(生活缴费支持的项目类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLivingFeeItemCountDim(Map<String,Object> paramMap);
	/**
	 * 往(生活缴费支持的项目类别)新增一条记录
	 * @param livingFeeItem
	 * @return
	 */
	public int insertLivingFeeItem(LivingFeeItem livingFeeItem);
	/**
	 * 批量新增(生活缴费支持的项目类别)
	 * @param livingFeeItemList
	 * @return
	 */
	public int insertLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList);
	/**
	 * 更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItem
	 * @return
	 */
	public int updateLivingFeeItem(LivingFeeItem livingFeeItem);
	/**
	 * 批量更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItemList
	 * @return
	 */
	public int updateLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList);
	/**
	 * 根据序列号删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLivingFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLivingFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(生活缴费支持的项目类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLivingFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(生活缴费支持的项目类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLivingFeeItemBatch(List<java.math.BigInteger> idList);
	
}
