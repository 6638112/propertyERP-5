package com.cnfantasia.server.domainbase.kitchenCookbookStepTips.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;

/**
 * 描述:(菜谱步骤tips信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookStepTipsBaseService {
	
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(菜谱步骤tips信息)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookStepTips getKitchenCookbookStepTipsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookStepTipsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookStepTipsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(菜谱步骤tips信息)新增一条记录
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	public int insertKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips);
	/**
	 * 批量新增(菜谱步骤tips信息)
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	public int insertKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList);
	/**
	 * 更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	public int updateKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips);
	/**
	 * 批量更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	public int updateKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList);
	/**
	 * 根据序列号删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookStepTipsLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookStepTipsLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(菜谱步骤tips信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookStepTips(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(菜谱步骤tips信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookStepTipsBatch(List<java.math.BigInteger> idList);
	
}
