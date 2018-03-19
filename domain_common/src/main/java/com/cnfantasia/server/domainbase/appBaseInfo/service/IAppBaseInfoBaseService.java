package com.cnfantasia.server.domainbase.appBaseInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.appBaseInfo.entity.AppBaseInfo;

/**
 * 描述:(应用信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppBaseInfoBaseService {
	
	/**
	 * 根据条件查询(应用信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppBaseInfo> getAppBaseInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(应用信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AppBaseInfo> getAppBaseInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(应用信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppBaseInfo> getAppBaseInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(应用信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AppBaseInfo> getAppBaseInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(应用信息)信息
	 * @param id
	 * @return
	 */
	public AppBaseInfo getAppBaseInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAppBaseInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(应用信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAppBaseInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(应用信息)新增一条记录
	 * @param appBaseInfo
	 * @return
	 */
	public int insertAppBaseInfo(AppBaseInfo appBaseInfo);
	/**
	 * 批量新增(应用信息)
	 * @param appBaseInfoList
	 * @return
	 */
	public int insertAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList);
	/**
	 * 更新(应用信息)信息
	 * @param appBaseInfo
	 * @return
	 */
	public int updateAppBaseInfo(AppBaseInfo appBaseInfo);
	/**
	 * 批量更新(应用信息)信息
	 * @param appBaseInfoList
	 * @return
	 */
	public int updateAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList);
	/**
	 * 根据序列号删除(应用信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAppBaseInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(应用信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAppBaseInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(应用信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAppBaseInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(应用信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAppBaseInfoBatch(List<java.math.BigInteger> idList);
	
}
