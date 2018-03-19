package com.cnfantasia.server.domainbase.cloudKeyUserData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 描述:(业主门禁认证（可配置）信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserDataBaseService {
	
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserData> getCloudKeyUserDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserData> getCloudKeyUserDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserData> getCloudKeyUserDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserData> getCloudKeyUserDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(业主门禁认证（可配置）信息)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserData getCloudKeyUserDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(业主门禁认证（可配置）信息)新增一条记录
	 * @param cloudKeyUserData
	 * @return
	 */
	public int insertCloudKeyUserData(CloudKeyUserData cloudKeyUserData);
	/**
	 * 批量新增(业主门禁认证（可配置）信息)
	 * @param cloudKeyUserDataList
	 * @return
	 */
	public int insertCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList);
	/**
	 * 更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserData
	 * @return
	 */
	public int updateCloudKeyUserData(CloudKeyUserData cloudKeyUserData);
	/**
	 * 批量更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	public int updateCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList);
	/**
	 * 根据序列号删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteCloudKeyUserDataLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteCloudKeyUserDataLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(业主门禁认证（可配置）信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCloudKeyUserData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证（可配置）信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCloudKeyUserDataBatch(List<java.math.BigInteger> idList);
	
}
