package com.cnfantasia.server.domainbase.kitchenCookbookStep.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;

/**
 * 描述:(亨饪步骤信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookStepBaseService {
	
	/**
	 * 根据条件查询(亨饪步骤信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookStep> getKitchenCookbookStepByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(亨饪步骤信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookStep> getKitchenCookbookStepByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(亨饪步骤信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookStep> getKitchenCookbookStepByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(亨饪步骤信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookStep> getKitchenCookbookStepByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(亨饪步骤信息)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookStep getKitchenCookbookStepBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(亨饪步骤信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookStepCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(亨饪步骤信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookStepCountDim(Map<String,Object> paramMap);
	/**
	 * 往(亨饪步骤信息)新增一条记录
	 * @param kitchenCookbookStep
	 * @return
	 */
	public int insertKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep);
	/**
	 * 批量新增(亨饪步骤信息)
	 * @param kitchenCookbookStepList
	 * @return
	 */
	public int insertKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList);
	/**
	 * 更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStep
	 * @return
	 */
	public int updateKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep);
	/**
	 * 批量更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStepList
	 * @return
	 */
	public int updateKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList);
	/**
	 * 根据序列号删除(亨饪步骤信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookStepLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(亨饪步骤信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookStepLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(亨饪步骤信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookStep(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(亨饪步骤信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookStepBatch(List<java.math.BigInteger> idList);
	
}
