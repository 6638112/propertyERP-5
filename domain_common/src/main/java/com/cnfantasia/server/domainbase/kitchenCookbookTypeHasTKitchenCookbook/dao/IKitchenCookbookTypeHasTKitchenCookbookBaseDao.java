package com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity.KitchenCookbookTypeHasTKitchenCookbook;

/**
 * 描述:(菜谱类别与菜谱关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTypeHasTKitchenCookbookBaseDao {
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> selectKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTypeHasTKitchenCookbook> selectKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(菜谱类别与菜谱关系表)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTypeHasTKitchenCookbook selectKitchenCookbookTypeHasTKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookTypeHasTKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(菜谱类别与菜谱关系表)新增一条记录
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook);
	
	/**
	 * 批量新增(菜谱类别与菜谱关系表)信息
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
	 * 根据Id 批量删除(菜谱类别与菜谱关系表)信息_逻辑删除
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
