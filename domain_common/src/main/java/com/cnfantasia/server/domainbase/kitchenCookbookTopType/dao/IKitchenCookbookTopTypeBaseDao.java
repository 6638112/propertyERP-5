package com.cnfantasia.server.domainbase.kitchenCookbookTopType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;

/**
 * 描述:(顶级类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTopTypeBaseDao {
	/**
	 * 根据条件查询(顶级类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTopType> selectKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(顶级类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenCookbookTopType> selectKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(顶级类别)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTopType selectKitchenCookbookTopTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenCookbookTopTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(顶级类别)新增一条记录
	 * @param kitchenCookbookTopType
	 * @return
	 */
	public int insertKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType);
	
	/**
	 * 批量新增(顶级类别)信息
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	public int insertKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList);
	
	/**
	 * 更新(顶级类别)信息
	 * @param kitchenCookbookTopType
	 * @return
	 */
	public int updateKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType);
	
	/**
	 * 批量更新(顶级类别)信息
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	public int updateKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList);
	
	/**
	 * 根据序列号删除(顶级类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenCookbookTopTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(顶级类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenCookbookTopTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(顶级类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenCookbookTopType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(顶级类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenCookbookTopTypeBatch(List<java.math.BigInteger> idList);
	
	
}
