package com.cnfantasia.server.domainbase.kitchenCookbookTopType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;

/**
 * 描述:(顶级类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenCookbookTopTypeBaseService {
	
	/**
	 * 根据条件查询(顶级类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(顶级类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(顶级类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(顶级类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<KitchenCookbookTopType> getKitchenCookbookTopTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(顶级类别)信息
	 * @param id
	 * @return
	 */
	public KitchenCookbookTopType getKitchenCookbookTopTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTopTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getKitchenCookbookTopTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(顶级类别)新增一条记录
	 * @param kitchenCookbookTopType
	 * @return
	 */
	public int insertKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType);
	/**
	 * 批量新增(顶级类别)
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
	 * 根据序列号批量删除(顶级类别)信息_逻辑删除
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
