package com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.entity.KitchenCookbookMixHasTKitchenCookbook;

/**
 * 描述:(组合菜谱所包含的菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookMixHasTKitchenCookbookBaseService {
	
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> getKitchenCookbookMixHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(组合菜谱所包含的菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookMixHasTKitchenCookbook getKitchenCookbookMixHasTKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookMixHasTKitchenCookbookCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookMixHasTKitchenCookbookCountDim(Map<String,Object> paramMap);
	/**
	 * 往(组合菜谱所包含的菜谱)新增一条记录
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook);
	/**
	 * 批量新增(组合菜谱所包含的菜谱)
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	public int insertKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList);
	/**
	 * 更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	public int updateKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook);
	/**
	 * 批量更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	public int updateKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList);
	/**
	 * 根据序列号删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(组合菜谱所包含的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookMixHasTKitchenCookbook(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(组合菜谱所包含的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookMixHasTKitchenCookbookBatch(List<java.math.BigInteger> idList);
	
}
