package com.cnfantasia.server.domainbase.lookupLocalMapData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.lookupLocalMapData.entity.LookupLocalMapData;

/**
 * 描述:(本地地址信息数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILookupLocalMapDataBaseService {
	
	/**
	 * 根据条件查询(本地地址信息数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LookupLocalMapData> getLookupLocalMapDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(本地地址信息数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LookupLocalMapData> getLookupLocalMapDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(本地地址信息数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LookupLocalMapData> getLookupLocalMapDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(本地地址信息数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LookupLocalMapData> getLookupLocalMapDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(本地地址信息数据)信息
	 * @param id
	 * @return
	 */
	public LookupLocalMapData getLookupLocalMapDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLookupLocalMapDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLookupLocalMapDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(本地地址信息数据)新增一条记录
	 * @param lookupLocalMapData
	 * @return
	 */
	public int insertLookupLocalMapData(LookupLocalMapData lookupLocalMapData);
	/**
	 * 批量新增(本地地址信息数据)
	 * @param lookupLocalMapDataList
	 * @return
	 */
	public int insertLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList);
	/**
	 * 更新(本地地址信息数据)信息
	 * @param lookupLocalMapData
	 * @return
	 */
	public int updateLookupLocalMapData(LookupLocalMapData lookupLocalMapData);
	/**
	 * 批量更新(本地地址信息数据)信息
	 * @param lookupLocalMapDataList
	 * @return
	 */
	public int updateLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList);
	/**
	 * 根据序列号删除(本地地址信息数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteLookupLocalMapDataLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(本地地址信息数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteLookupLocalMapDataLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(本地地址信息数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLookupLocalMapData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(本地地址信息数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLookupLocalMapDataBatch(List<java.math.BigInteger> idList);
	
}
