package com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.entity.KitchenCookbookTypeRecommend;

/**
 * 描述:(厨房菜品类别推荐) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeRecommendBaseDao {
	/**
	 * 根据条件查询(厨房菜品类别推荐)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeRecommend> selectKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(厨房菜品类别推荐)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeRecommend> selectKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(厨房菜品类别推荐)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTypeRecommend selectKitchenCookbookTypeRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜品类别推荐)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookTypeRecommendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(厨房菜品类别推荐)新增一条记录
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	public int insertKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend);
	
	/**
	 * 批量新增(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	public int insertKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList);
	
	/**
	 * 更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	public int updateKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend);
	
	/**
	 * 批量更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	public int updateKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList);
	
	/**
	 * 根据序列号删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeRecommendLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeRecommendLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(厨房菜品类别推荐)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeRecommend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(厨房菜品类别推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeRecommendBatch(List<java.math.BigInteger> idList);
	
	
}
