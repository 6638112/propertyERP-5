package com.cnfantasia.server.domainbase.newUserLocation.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.newUserLocation.entity.NewUserLocation;

/**
 * 描述:(新用户第一次打开app定位结果信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface INewUserLocationBaseService {
	
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<NewUserLocation> getNewUserLocationByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<NewUserLocation> getNewUserLocationByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<NewUserLocation> getNewUserLocationByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<NewUserLocation> getNewUserLocationByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(新用户第一次打开app定位结果信息)信息
	 * @param id
	 * @return
	 */
	public NewUserLocation getNewUserLocationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getNewUserLocationCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getNewUserLocationCountDim(Map<String,Object> paramMap);
	/**
	 * 往(新用户第一次打开app定位结果信息)新增一条记录
	 * @param newUserLocation
	 * @return
	 */
	public int insertNewUserLocation(NewUserLocation newUserLocation);
	/**
	 * 批量新增(新用户第一次打开app定位结果信息)
	 * @param newUserLocationList
	 * @return
	 */
	public int insertNewUserLocationBatch(List<NewUserLocation> newUserLocationList);
	/**
	 * 更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocation
	 * @return
	 */
	public int updateNewUserLocation(NewUserLocation newUserLocation);
	/**
	 * 批量更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocationList
	 * @return
	 */
	public int updateNewUserLocationBatch(List<NewUserLocation> newUserLocationList);
	/**
	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteNewUserLocationLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteNewUserLocationLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteNewUserLocation(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(新用户第一次打开app定位结果信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteNewUserLocationBatch(List<java.math.BigInteger> idList);
	
}
