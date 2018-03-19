package com.cnfantasia.server.domainbase.kitchenCookbookCollect.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookCollect.entity.KitchenCookbookCollect;

/**
 * 描述:(菜谱收藏) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookCollectBaseService {
	
	/**
	 * 根据条件查询(菜谱收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(菜谱收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(菜谱收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(菜谱收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollect> getKitchenCookbookCollectByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(菜谱收藏)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookCollect getKitchenCookbookCollectBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCollectCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(菜谱收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCollectCountDim(Map<String,Object> paramMap);
	/**
	 * 往(菜谱收藏)新增一条记录
	 * @param kitchenCookbookCollect
	 * @return
	 */
	public int insertKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect);
	/**
	 * 批量新增(菜谱收藏)
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	public int insertKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList);
	/**
	 * 更新(菜谱收藏)信息
	 * @param kitchenCookbookCollect
	 * @return
	 */
	public int updateKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect);
	/**
	 * 批量更新(菜谱收藏)信息
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	public int updateKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList);
	/**
	 * 根据序列号删除(菜谱收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookCollectLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(菜谱收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookCollectLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(菜谱收藏)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookCollect(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(菜谱收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookCollectBatch(List<java.math.BigInteger> idList);
	
}
