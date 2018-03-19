package com.cnfantasia.server.domainbase.newUserLocation.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.newUserLocation.entity.NewUserLocation;

/**
 * 描述:(新用户第一次打开app定位结果信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface INewUserLocationBaseDao {
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<NewUserLocation> selectNewUserLocationByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<NewUserLocation> selectNewUserLocationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(新用户第一次打开app定位结果信息)信息
	 * @param id
	 * @return
	 */
	public NewUserLocation selectNewUserLocationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectNewUserLocationCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(新用户第一次打开app定位结果信息)新增一条记录
	 * @param newUserLocation
	 * @return
	 */
	public int insertNewUserLocation(NewUserLocation newUserLocation);
	
	/**
	 * 批量新增(新用户第一次打开app定位结果信息)信息
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
	 * 根据Id 批量删除(新用户第一次打开app定位结果信息)信息_逻辑删除
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
