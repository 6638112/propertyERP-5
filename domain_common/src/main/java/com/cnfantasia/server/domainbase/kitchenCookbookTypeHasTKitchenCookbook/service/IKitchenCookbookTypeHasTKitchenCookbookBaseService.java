package com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity.KitchenCookbookTypeHasTKitchenCookbook;

/**
 * 描述:(菜谱类别与菜谱关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeHasTKitchenCookbookBaseService {
	
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> getKitchenCookbookTypeHasTKitchenCookbookByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(菜谱类别与菜谱关系表)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTypeHasTKitchenCookbook getKitchenCookbookTypeHasTKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeHasTKitchenCookbookCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTypeHasTKitchenCookbookCountDim(Map<String,Object> paramMap);
	/**
	 * 往(菜谱类别与菜谱关系表)新增一条记录
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook);
	/**
	 * 批量新增(菜谱类别与菜谱关系表)
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	public int insertKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList);
	/**
	 * 更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	public int updateKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook);
	/**
	 * 批量更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	public int updateKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList);
	/**
	 * 根据序列号删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(菜谱类别与菜谱关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeHasTKitchenCookbook(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别与菜谱关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookTypeHasTKitchenCookbookBatch(List<java.math.BigInteger> idList);
	
}
