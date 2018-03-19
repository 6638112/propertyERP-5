package com.cnfantasia.server.domainbase.kitchenCookbookType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;

/**
 * 描述:(厨房菜谱分类) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeBaseService {
	
	/**
	 * 根据条件查询(厨房菜谱分类)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookType> getKitchenCookbookTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(厨房菜谱分类)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookType> getKitchenCookbookTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(厨房菜谱分类)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookType> getKitchenCookbookTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(厨房菜谱分类)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookType> getKitchenCookbookTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(厨房菜谱分类)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookType getKitchenCookbookTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(厨房菜谱分类)新增一条记录
	 * @param kitchenCookbookType
	 * @return
	 */
	public int insertKitchenCookbookType(KitchenCookbookType kitchenCookbookType);
	/**
	 * 批量新增(厨房菜谱分类)
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
	 * 根据序列号批量删除(厨房菜谱分类)信息_逻辑删除
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
