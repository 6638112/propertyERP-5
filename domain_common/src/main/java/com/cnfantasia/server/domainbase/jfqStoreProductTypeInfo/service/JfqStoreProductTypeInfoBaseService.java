package com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.dao.IJfqStoreProductTypeInfoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity.JfqStoreProductTypeInfo;

/**
 * 描述:(解放区体验店商品类型信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class JfqStoreProductTypeInfoBaseService implements IJfqStoreProductTypeInfoBaseService{
	
	private IJfqStoreProductTypeInfoBaseDao jfqStoreProductTypeInfoBaseDao;
	public void setJfqStoreProductTypeInfoBaseDao(IJfqStoreProductTypeInfoBaseDao jfqStoreProductTypeInfoBaseDao) {
		this.jfqStoreProductTypeInfoBaseDao = jfqStoreProductTypeInfoBaseDao;
	}
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap){
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByConditionDim(Map<String,Object> paramMap){
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> getJfqStoreProductTypeInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(解放区体验店商品类型信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public JfqStoreProductTypeInfo getJfqStoreProductTypeInfoBySeqId(java.math.BigInteger id){
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getJfqStoreProductTypeInfoCount(Map<String,Object> paramMap){
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getJfqStoreProductTypeInfoCountDim(Map<String,Object> paramMap){
		return jfqStoreProductTypeInfoBaseDao.selectJfqStoreProductTypeInfoCount(paramMap,true);
	}
	/**
	 * 往(解放区体验店商品类型信息)新增一条记录
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	@Override
	public int insertJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo){
		return jfqStoreProductTypeInfoBaseDao.insertJfqStoreProductTypeInfo(jfqStoreProductTypeInfo);
	}
	/**
	 * 批量新增(解放区体验店商品类型信息)
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	@Override
	public int insertJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList){
		return jfqStoreProductTypeInfoBaseDao.insertJfqStoreProductTypeInfoBatch(jfqStoreProductTypeInfoList);
	}
	/**
	 * 更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	@Override
	public int updateJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo){
		return jfqStoreProductTypeInfoBaseDao.updateJfqStoreProductTypeInfo(jfqStoreProductTypeInfo);
	}
	/**
	 * 批量更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	@Override
	public int updateJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList){
		return jfqStoreProductTypeInfoBaseDao.updateJfqStoreProductTypeInfoBatch(jfqStoreProductTypeInfoList);
	}
	/**
	 * 根据序列号删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteJfqStoreProductTypeInfoLogic(java.math.BigInteger id){
		return jfqStoreProductTypeInfoBaseDao.deleteJfqStoreProductTypeInfoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteJfqStoreProductTypeInfoLogicBatch(List<java.math.BigInteger> idList){
		return jfqStoreProductTypeInfoBaseDao.deleteJfqStoreProductTypeInfoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(解放区体验店商品类型信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteJfqStoreProductTypeInfo(java.math.BigInteger id){
//		return jfqStoreProductTypeInfoBaseDao.deleteJfqStoreProductTypeInfo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(解放区体验店商品类型信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteJfqStoreProductTypeInfoBatch(List<java.math.BigInteger> idList){
//		return jfqStoreProductTypeInfoBaseDao.deleteJfqStoreProductTypeInfoBatch(idList);
//	}
	
}
