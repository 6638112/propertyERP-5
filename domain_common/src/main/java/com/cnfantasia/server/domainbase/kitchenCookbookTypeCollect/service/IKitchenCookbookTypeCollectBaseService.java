package com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.entity.KitchenCookbookTypeCollect;

/**
 * 描述:(菜谱类别收藏) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeCollectBaseService {
	
	/**
	 * 根据条件查询(菜谱类别收藏)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(菜谱类别收藏)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(菜谱类别收藏)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(菜谱类别收藏)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> getKitchenCookbookTypeCollectByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(菜谱类别收藏)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTypeCollect getKitchenCookbookTypeCollectBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeCollectCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeCollectCountDim(Map<String,Object> paramMap);
	/**
	 * 往(菜谱类别收藏)新增一条记录
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	public int insertKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect);
	/**
	 * 批量新增(菜谱类别收藏)
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	public int insertKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList);
	/**
	 * 更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	public int updateKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect);
	/**
	 * 批量更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	public int updateKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList);
	/**
	 * 根据序列号删除(菜谱类别收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeCollectLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(菜谱类别收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeCollectLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(菜谱类别收藏)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeCollect(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeCollectBatch(List<java.math.BigInteger> idList);
	
}
