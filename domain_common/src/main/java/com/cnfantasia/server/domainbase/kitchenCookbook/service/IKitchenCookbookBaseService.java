package com.cnfantasia.server.domainbase.kitchenCookbook.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * 描述:(厨房菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookBaseService {
	
	/**
	 * 根据条件查询(厨房菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbook> getKitchenCookbookByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(厨房菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbook> getKitchenCookbookByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(厨房菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbook> getKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(厨房菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbook> getKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(厨房菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbook getKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookCountDim(Map<String,Object> paramMap);
	/**
	 * 往(厨房菜谱)新增一条记录
	 * @param kitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbook(KitchenCookbook kitchenCookbook);
	/**
	 * 批量新增(厨房菜谱)
	 * @param kitchenCookbookList
	 * @return
	 */
	public int insertKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList);
	/**
	 * 更新(厨房菜谱)信息
	 * @param kitchenCookbook
	 * @return
	 */
	public int updateKitchenCookbook(KitchenCookbook kitchenCookbook);
	/**
	 * 批量更新(厨房菜谱)信息
	 * @param kitchenCookbookList
	 * @return
	 */
	public int updateKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList);
	/**
	 * 根据序列号删除(厨房菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(厨房菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(厨房菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbook(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookBatch(List<java.math.BigInteger> idList);
	
}
