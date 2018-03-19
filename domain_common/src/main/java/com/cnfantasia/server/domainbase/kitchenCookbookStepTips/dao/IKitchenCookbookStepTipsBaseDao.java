package com.cnfantasia.server.domainbase.kitchenCookbookStepTips.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;

/**
 * 描述:(菜谱步骤tips信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookStepTipsBaseDao {
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookStepTips> selectKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookStepTips> selectKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(菜谱步骤tips信息)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookStepTips selectKitchenCookbookStepTipsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookStepTipsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(菜谱步骤tips信息)新增一条记录
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	public int insertKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips);
	
	/**
	 * 批量新增(菜谱步骤tips信息)信息
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
	 * 根据Id 批量删除(菜谱步骤tips信息)信息_逻辑删除
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
