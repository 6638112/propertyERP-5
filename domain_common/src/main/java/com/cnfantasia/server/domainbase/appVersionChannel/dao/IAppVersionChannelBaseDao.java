package com.cnfantasia.server.domainbase.appVersionChannel.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersionChannel.entity.AppVersionChannel;

/**
 * 描述:(应用版本不同渠道信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAppVersionChannelBaseDao {
	/**
	 * 根据条件查询(应用版本不同渠道信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppVersionChannel> selectAppVersionChannelByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(应用版本不同渠道信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AppVersionChannel> selectAppVersionChannelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(应用版本不同渠道信息)信息
	 * @param id
	 * @return
	 */
	public AppVersionChannel selectAppVersionChannelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(应用版本不同渠道信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAppVersionChannelCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(应用版本不同渠道信息)新增一条记录
	 * @param appVersionChannel
	 * @return
	 */
	public int insertAppVersionChannel(AppVersionChannel appVersionChannel);
	
	/**
	 * 批量新增(应用版本不同渠道信息)信息
	 * @param appVersionChannelList
	 * @return
	 */
	public int insertAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList);
	
	/**
	 * 更新(应用版本不同渠道信息)信息
	 * @param appVersionChannel
	 * @return
	 */
	public int updateAppVersionChannel(AppVersionChannel appVersionChannel);
	
	/**
	 * 批量更新(应用版本不同渠道信息)信息
	 * @param appVersionChannelList
	 * @return
	 */
	public int updateAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList);
	
	/**
	 * 根据序列号删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAppVersionChannelLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAppVersionChannelLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(应用版本不同渠道信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAppVersionChannel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(应用版本不同渠道信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAppVersionChannelBatch(List<java.math.BigInteger> idList);
	
	
}
