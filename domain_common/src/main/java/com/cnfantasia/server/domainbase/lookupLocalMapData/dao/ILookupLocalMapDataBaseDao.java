package com.cnfantasia.server.domainbase.lookupLocalMapData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lookupLocalMapData.entity.LookupLocalMapData;

/**
 * 描述:(本地地址信息数据) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILookupLocalMapDataBaseDao {
	/**
	 * 根据条件查询(本地地址信息数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LookupLocalMapData> selectLookupLocalMapDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(本地地址信息数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LookupLocalMapData> selectLookupLocalMapDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(本地地址信息数据)信息
	 * @param id
	 * @return
	 */
	public LookupLocalMapData selectLookupLocalMapDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLookupLocalMapDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(本地地址信息数据)新增一条记录
	 * @param lookupLocalMapData
	 * @return
	 */
	public int insertLookupLocalMapData(LookupLocalMapData lookupLocalMapData);
	
	/**
	 * 批量新增(本地地址信息数据)信息
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
	 * 根据Id 批量删除(本地地址信息数据)信息_逻辑删除
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
