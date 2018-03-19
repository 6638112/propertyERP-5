package com.cnfantasia.server.domainbase.appBaseInfo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appBaseInfo.entity.AppBaseInfo;

/**
 * 描述:(应用信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppBaseInfoBaseDao {
	/**
	 * 根据条件查询(应用信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppBaseInfo> selectAppBaseInfoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(应用信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppBaseInfo> selectAppBaseInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(应用信息)信息
	 * @param id
	 * @return
	 */
	public AppBaseInfo selectAppBaseInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAppBaseInfoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(应用信息)新增一条记录
	 * @param appBaseInfo
	 * @return
	 */
	public int insertAppBaseInfo(AppBaseInfo appBaseInfo);
	
	/**
	 * 批量新增(应用信息)信息
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
	 * 根据Id 批量删除(应用信息)信息_逻辑删除
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
