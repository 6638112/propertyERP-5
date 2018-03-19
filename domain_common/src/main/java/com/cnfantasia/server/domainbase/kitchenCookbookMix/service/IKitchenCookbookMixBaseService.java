package com.cnfantasia.server.domainbase.kitchenCookbookMix.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookMix.entity.KitchenCookbookMix;

/**
 * 描述:(厨房组合菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookMixBaseService {
	
	/**
	 * 根据条件查询(厨房组合菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookMix> getKitchenCookbookMixByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(厨房组合菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookMix> getKitchenCookbookMixByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(厨房组合菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookMix> getKitchenCookbookMixByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(厨房组合菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookMix> getKitchenCookbookMixByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(厨房组合菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookMix getKitchenCookbookMixBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房组合菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookMixCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(厨房组合菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookMixCountDim(Map<String,Object> paramMap);
	/**
	 * 往(厨房组合菜谱)新增一条记录
	 * @param kitchenCookbookMix
	 * @return
	 */
	public int insertKitchenCookbookMix(KitchenCookbookMix kitchenCookbookMix);
	/**
	 * 批量新增(厨房组合菜谱)
	 * @param kitchenCookbookMixList
	 * @return
	 */
	public int insertKitchenCookbookMixBatch(List<KitchenCookbookMix> kitchenCookbookMixList);
	/**
	 * 更新(厨房组合菜谱)信息
	 * @param kitchenCookbookMix
	 * @return
	 */
	public int updateKitchenCookbookMix(KitchenCookbookMix kitchenCookbookMix);
	/**
	 * 批量更新(厨房组合菜谱)信息
	 * @param kitchenCookbookMixList
	 * @return
	 */
	public int updateKitchenCookbookMixBatch(List<KitchenCookbookMix> kitchenCookbookMixList);
	/**
	 * 根据序列号删除(厨房组合菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookMixLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(厨房组合菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookMixLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(厨房组合菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookMix(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(厨房组合菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookMixBatch(List<java.math.BigInteger> idList);
	
}
