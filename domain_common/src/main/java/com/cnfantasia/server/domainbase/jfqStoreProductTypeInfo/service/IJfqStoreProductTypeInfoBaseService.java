package com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity.JfqStoreProductTypeInfo;

/**
 * 描述:(解放区体验店商品类型信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IJfqStoreProductTypeInfoBaseService {
	
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(解放区体验店商品类型信息)信息
	 * @param id
	 * @return
	 */
	public JfqStoreProductTypeInfo getJfqStoreProductTypeInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getJfqStoreProductTypeInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getJfqStoreProductTypeInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(解放区体验店商品类型信息)新增一条记录
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	public int insertJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo);
	/**
	 * 批量新增(解放区体验店商品类型信息)
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	public int insertJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList);
	/**
	 * 更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	public int updateJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo);
	/**
	 * 批量更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	public int updateJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList);
	/**
	 * 根据序列号删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteJfqStoreProductTypeInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteJfqStoreProductTypeInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(解放区体验店商品类型信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteJfqStoreProductTypeInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(解放区体验店商品类型信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteJfqStoreProductTypeInfoBatch(List<java.math.BigInteger> idList);
	
}
