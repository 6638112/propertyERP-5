package com.cnfantasia.server.domainbase.appVersion.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.appVersion.entity.AppVersion;

/**
 * 描述:(应用版本信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppVersionBaseService {
	
	/**
	 * 根据条件查询(应用版本信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppVersion> getAppVersionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(应用版本信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppVersion> getAppVersionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(应用版本信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppVersion> getAppVersionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(应用版本信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppVersion> getAppVersionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(应用版本信息)信息
	 * @param id
	 * @return
	 */
	public AppVersion getAppVersionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAppVersionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAppVersionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(应用版本信息)新增一条记录
	 * @param appVersion
	 * @return
	 */
	public int insertAppVersion(AppVersion appVersion);
	/**
	 * 批量新增(应用版本信息)
	 * @param appVersionList
	 * @return
	 */
	public int insertAppVersionBatch(List<AppVersion> appVersionList);
	/**
	 * 更新(应用版本信息)信息
	 * @param appVersion
	 * @return
	 */
	public int updateAppVersion(AppVersion appVersion);
	/**
	 * 批量更新(应用版本信息)信息
	 * @param appVersionList
	 * @return
	 */
	public int updateAppVersionBatch(List<AppVersion> appVersionList);
	/**
	 * 根据序列号删除(应用版本信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAppVersionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(应用版本信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAppVersionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(应用版本信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAppVersion(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(应用版本信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAppVersionBatch(List<java.math.BigInteger> idList);
	
}
