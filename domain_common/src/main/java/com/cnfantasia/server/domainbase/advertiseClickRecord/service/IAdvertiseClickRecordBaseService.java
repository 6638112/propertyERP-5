package com.cnfantasia.server.domainbase.advertiseClickRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.advertiseClickRecord.entity.AdvertiseClickRecord;

/**
 * 描述:(广告点击记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAdvertiseClickRecordBaseService {
	
	/**
	 * 根据条件查询(广告点击记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(广告点击记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(广告点击记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(广告点击记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AdvertiseClickRecord> getAdvertiseClickRecordByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(广告点击记录表)信息
	 * @param id
	 * @return
	 */
	public AdvertiseClickRecord getAdvertiseClickRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(广告点击记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAdvertiseClickRecordCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(广告点击记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAdvertiseClickRecordCountDim(Map<String, Object> paramMap);
	/**
	 * 往(广告点击记录表)新增一条记录
	 * @param advertiseClickRecord
	 * @return
	 */
	public int insertAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord);
	/**
	 * 批量新增(广告点击记录表)
	 * @param advertiseClickRecordList
	 * @return
	 */
	public int insertAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList);
	/**
	 * 更新(广告点击记录表)信息
	 * @param advertiseClickRecord
	 * @return
	 */
	public int updateAdvertiseClickRecord(AdvertiseClickRecord advertiseClickRecord);
	/**
	 * 批量更新(广告点击记录表)信息
	 * @param advertiseClickRecordList
	 * @return
	 */
	public int updateAdvertiseClickRecordBatch(List<AdvertiseClickRecord> advertiseClickRecordList);
	/**
	 * 根据序列号删除(广告点击记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAdvertiseClickRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(广告点击记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAdvertiseClickRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(广告点击记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAdvertiseClickRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(广告点击记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAdvertiseClickRecordBatch(List<java.math.BigInteger> idList);
	
}
