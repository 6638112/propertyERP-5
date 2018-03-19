package com.cnfantasia.server.domainbase.channelSub.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.channelSub.entity.ChannelSub;

/**
 * 描述:(子渠道表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelSubBaseService {
	
	/**
	 * 根据条件查询(子渠道表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelSub> getChannelSubByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(子渠道表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelSub> getChannelSubByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(子渠道表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelSub> getChannelSubByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(子渠道表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelSub> getChannelSubByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(子渠道表)信息
	 * @param id
	 * @return
	 */
	public ChannelSub getChannelSubBySeqId(java.lang.Long id);
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelSubCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelSubCountDim(Map<String,Object> paramMap);
	/**
	 * 往(子渠道表)新增一条记录
	 * @param channelSub
	 * @return
	 */
	public int insertChannelSub(ChannelSub channelSub);
	/**
	 * 批量新增(子渠道表)
	 * @param channelSubList
	 * @return
	 */
	public int insertChannelSubBatch(List<ChannelSub> channelSubList);
	/**
	 * 更新(子渠道表)信息
	 * @param channelSub
	 * @return
	 */
	public int updateChannelSub(ChannelSub channelSub);
	/**
	 * 批量更新(子渠道表)信息
	 * @param channelSubList
	 * @return
	 */
	public int updateChannelSubBatch(List<ChannelSub> channelSubList);
	/**
	 * 根据序列号删除(子渠道表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteChannelSubLogic(java.lang.Long id);
	
	/**
	 * 根据序列号批量删除(子渠道表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteChannelSubLogicBatch(List<java.lang.Long> idList);
	
//	/**
//	 * 根据序列号删除(子渠道表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteChannelSub(java.lang.Long id);
//	
//	/**
//	 * 根据序列号批量删除(子渠道表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteChannelSubBatch(List<java.lang.Long> idList);
	
}
