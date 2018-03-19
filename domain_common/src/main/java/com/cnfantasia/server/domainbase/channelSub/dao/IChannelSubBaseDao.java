package com.cnfantasia.server.domainbase.channelSub.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelSub.entity.ChannelSub;

/**
 * 描述:(子渠道表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelSubBaseDao {
	/**
	 * 根据条件查询(子渠道表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelSub> selectChannelSubByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(子渠道表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelSub> selectChannelSubByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(子渠道表)信息
	 * @param id
	 * @return
	 */
	public ChannelSub selectChannelSubBySeqId(java.lang.Long id);
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectChannelSubCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(子渠道表)新增一条记录
	 * @param channelSub
	 * @return
	 */
	public int insertChannelSub(ChannelSub channelSub);
	
	/**
	 * 批量新增(子渠道表)信息
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
	 * 根据Id 批量删除(子渠道表)信息_逻辑删除
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
