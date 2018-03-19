package com.cnfantasia.server.domainbase.kitchenCookbookType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;

/**
 * 描述:(厨房菜谱分类) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeBaseDao {
	/**
	 * 根据条件查询(厨房菜谱分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookType> selectKitchenCookbookTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(厨房菜谱分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookType> selectKitchenCookbookTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(厨房菜谱分类)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookType selectKitchenCookbookTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(厨房菜谱分类)新增一条记录
	 * @param kitchenCookbookType
	 * @return
	 */
	public int insertKitchenCookbookType(KitchenCookbookType kitchenCookbookType);
	
	/**
	 * 批量新增(厨房菜谱分类)信息
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	public int insertKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList);
	
	/**
	 * 更新(厨房菜谱分类)信息
	 * @param kitchenCookbookType
	 * @return
	 */
	public int updateKitchenCookbookType(KitchenCookbookType kitchenCookbookType);
	
	/**
	 * 批量更新(厨房菜谱分类)信息
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	public int updateKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList);
	
	/**
	 * 根据序列号删除(厨房菜谱分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(厨房菜谱分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(厨房菜谱分类)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱分类)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeBatch(List<java.math.BigInteger> idList);
	
	
}
