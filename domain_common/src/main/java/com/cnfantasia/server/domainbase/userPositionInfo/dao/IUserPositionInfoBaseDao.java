package com.cnfantasia.server.domainbase.userPositionInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPositionInfo.entity.UserPositionInfo;

/**
 * 描述:(用户定位信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserPositionInfoBaseDao {
	/**
	 * 根据条件查询(用户定位信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserPositionInfo> selectUserPositionInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户定位信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserPositionInfo> selectUserPositionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户定位信息)信息
	 * @param id
	 * @return
	 */
	public UserPositionInfo selectUserPositionInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户定位信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserPositionInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户定位信息)新增一条记录
	 * @param userPositionInfo
	 * @return
	 */
	public int insertUserPositionInfo(UserPositionInfo userPositionInfo);
	
	/**
	 * 批量新增(用户定位信息)信息
	 * @param userPositionInfoList
	 * @return
	 */
	public int insertUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList);
	
	/**
	 * 更新(用户定位信息)信息
	 * @param userPositionInfo
	 * @return
	 */
	public int updateUserPositionInfo(UserPositionInfo userPositionInfo);
	
	/**
	 * 批量更新(用户定位信息)信息
	 * @param userPositionInfoList
	 * @return
	 */
	public int updateUserPositionInfoBatch(List<UserPositionInfo> userPositionInfoList);
	
	/**
	 * 根据序列号删除(用户定位信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserPositionInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户定位信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserPositionInfoLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户定位信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserPositionInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户定位信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserPositionInfoBatch(List<java.math.BigInteger> idList);
	
	
}
