package com.cnfantasia.server.domainbase.channel.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.channel.entity.Channel;

/**
 * 描述:(主渠道表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelBaseService {
	
	/**
	 * 根据条件查询(主渠道表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Channel> getChannelByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(主渠道表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Channel> getChannelByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(主渠道表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Channel> getChannelByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(主渠道表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Channel> getChannelByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(主渠道表)信息
	 * @param id
	 * @return
	 */
	public Channel getChannelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(主渠道表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(主渠道表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelCountDim(Map<String,Object> paramMap);
	/**
	 * 往(主渠道表)新增一条记录
	 * @param channel
	 * @return
	 */
	public int insertChannel(Channel channel);
	/**
	 * 批量新增(主渠道表)
	 * @param channelList
	 * @return
	 */
	public int insertChannelBatch(List<Channel> channelList);
	/**
	 * 更新(主渠道表)信息
	 * @param channel
	 * @return
	 */
	public int updateChannel(Channel channel);
	/**
	 * 批量更新(主渠道表)信息
	 * @param channelList
	 * @return
	 */
	public int updateChannelBatch(List<Channel> channelList);
	/**
	 * 根据序列号删除(主渠道表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteChannelLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(主渠道表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteChannelLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(主渠道表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteChannel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(主渠道表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteChannelBatch(List<java.math.BigInteger> idList);
	
}
