package com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.entity.KitchenCookbookMixHasTKitchenCookbook;

/**
 * 描述:(组合菜谱所包含的菜谱) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookMixHasTKitchenCookbookBaseDao {
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> selectKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookMixHasTKitchenCookbook> selectKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(组合菜谱所包含的菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookMixHasTKitchenCookbook selectKitchenCookbookMixHasTKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookMixHasTKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(组合菜谱所包含的菜谱)新增一条记录
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook);
	
	/**
	 * 批量新增(组合菜谱所包含的菜谱)信息
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
	 * 根据Id 批量删除(组合菜谱所包含的菜谱)信息_逻辑删除
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
