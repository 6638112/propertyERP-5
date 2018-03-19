package com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.entity.KitchenCookbookTypeCollect;

/**
 * 描述:(菜谱类别收藏) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeCollectBaseDao {
	/**
	 * 根据条件查询(菜谱类别收藏)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> selectKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(菜谱类别收藏)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeCollect> selectKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(菜谱类别收藏)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTypeCollect selectKitchenCookbookTypeCollectBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookTypeCollectCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(菜谱类别收藏)新增一条记录
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	public int insertKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect);
	
	/**
	 * 批量新增(菜谱类别收藏)信息
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
	 * 根据Id 批量删除(菜谱类别收藏)信息_逻辑删除
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
