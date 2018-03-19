package com.cnfantasia.server.domainbase.cloudKeyUserData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 描述:(业主门禁认证（可配置）信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserDataBaseDao {
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(业主门禁认证（可配置）信息)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserData selectCloudKeyUserDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCloudKeyUserDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(业主门禁认证（可配置）信息)新增一条记录
	 * @param cloudKeyUserData
	 * @return
	 */
	public int insertCloudKeyUserData(CloudKeyUserData cloudKeyUserData);
	
	/**
	 * 批量新增(业主门禁认证（可配置）信息)信息
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
	 * 根据Id 批量删除(业主门禁认证（可配置）信息)信息_逻辑删除
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
