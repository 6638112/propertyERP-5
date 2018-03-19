package com.cnfantasia.server.domainbase.appVersion.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersion.entity.AppVersion;

/**
 * 描述:(应用版本信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppVersionBaseDao {
	/**
	 * 根据条件查询(应用版本信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppVersion> selectAppVersionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(应用版本信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppVersion> selectAppVersionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(应用版本信息)信息
	 * @param id
	 * @return
	 */
	public AppVersion selectAppVersionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAppVersionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(应用版本信息)新增一条记录
	 * @param appVersion
	 * @return
	 */
	public int insertAppVersion(AppVersion appVersion);
	
	/**
	 * 批量新增(应用版本信息)信息
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
	 * 根据Id 批量删除(应用版本信息)信息_逻辑删除
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
