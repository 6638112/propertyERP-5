package com.cnfantasia.server.domainbase.kitchenCookbook.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * 描述:(厨房菜谱) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookBaseDao {
	/**
	 * 根据条件查询(厨房菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbook> selectKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(厨房菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbook> selectKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(厨房菜谱)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbook selectKitchenCookbookBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(厨房菜谱)新增一条记录
	 * @param kitchenCookbook
	 * @return
	 */
	public int insertKitchenCookbook(KitchenCookbook kitchenCookbook);
	
	/**
	 * 批量新增(厨房菜谱)信息
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
	 * 根据Id 批量删除(厨房菜谱)信息_逻辑删除
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
