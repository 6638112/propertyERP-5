package com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity.JfqStoreProductTypeInfo;

/**
 * 描述:(解放区体验店商品类型信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IJfqStoreProductTypeInfoBaseDao {
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> selectJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<JfqStoreProductTypeInfo> selectJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(解放区体验店商品类型信息)信息
	 * @param id
	 * @return
	 */
	public JfqStoreProductTypeInfo selectJfqStoreProductTypeInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectJfqStoreProductTypeInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(解放区体验店商品类型信息)新增一条记录
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	public int insertJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo);
	
	/**
	 * 批量新增(解放区体验店商品类型信息)信息
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
	 * 根据Id 批量删除(解放区体验店商品类型信息)信息_逻辑删除
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
