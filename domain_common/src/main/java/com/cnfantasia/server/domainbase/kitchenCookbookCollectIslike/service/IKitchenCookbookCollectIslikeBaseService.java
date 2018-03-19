package com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.entity.KitchenCookbookCollectIslike;

/**
 * 描述:(家庭成员是否喜欢吃对应菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookCollectIslikeBaseService {
	
	/**
	 * 根据条件查询(家庭成员是否喜欢吃对应菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(家庭成员是否喜欢吃对应菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(家庭成员是否喜欢吃对应菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(家庭成员是否喜欢吃对应菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookCollectIslike> getKitchenCookbookCollectIslikeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(家庭成员是否喜欢吃对应菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookCollectIslike getKitchenCookbookCollectIslikeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭成员是否喜欢吃对应菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCollectIslikeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(家庭成员是否喜欢吃对应菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCollectIslikeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(家庭成员是否喜欢吃对应菜谱)新增一条记录
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	public int insertKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike);
	/**
	 * 批量新增(家庭成员是否喜欢吃对应菜谱)
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	public int insertKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList);
	/**
	 * 更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	public int updateKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike);
	/**
	 * 批量更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	public int updateKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList);
	/**
	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookCollectIslikeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookCollectIslikeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookCollectIslike(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookCollectIslikeBatch(List<java.math.BigInteger> idList);
	
}
