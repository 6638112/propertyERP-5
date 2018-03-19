package com.cnfantasia.server.domainbase.channel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.channel.dao.IChannelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channel.entity.Channel;

/**
 * 描述:(主渠道表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ChannelBaseService implements IChannelBaseService{
	
	private IChannelBaseDao channelBaseDao;
	public void setChannelBaseDao(IChannelBaseDao channelBaseDao) {
		this.channelBaseDao = channelBaseDao;
	}
	/**
	 * 根据条件查询(主渠道表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Channel> getChannelByCondition(Map<String,Object> paramMap){
		return channelBaseDao.selectChannelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(主渠道表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Channel> getChannelByConditionDim(Map<String,Object> paramMap){
		return channelBaseDao.selectChannelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(主渠道表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Channel> getChannelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return channelBaseDao.selectChannelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(主渠道表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Channel> getChannelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return channelBaseDao.selectChannelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(主渠道表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Channel getChannelBySeqId(java.math.BigInteger id){
		return channelBaseDao.selectChannelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(主渠道表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelCount(Map<String,Object> paramMap){
		return channelBaseDao.selectChannelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(主渠道表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelCountDim(Map<String,Object> paramMap){
		return channelBaseDao.selectChannelCount(paramMap,true);
	}
	/**
	 * 往(主渠道表)新增一条记录
	 * @param channel
	 * @return
	 */
	@Override
	public int insertChannel(Channel channel){
		return channelBaseDao.insertChannel(channel);
	}
	/**
	 * 批量新增(主渠道表)
	 * @param channelList
	 * @return
	 */
	@Override
	public int insertChannelBatch(List<Channel> channelList){
		return channelBaseDao.insertChannelBatch(channelList);
	}
	/**
	 * 更新(主渠道表)信息
	 * @param channel
	 * @return
	 */
	@Override
	public int updateChannel(Channel channel){
		return channelBaseDao.updateChannel(channel);
	}
	/**
	 * 批量更新(主渠道表)信息
	 * @param channelList
	 * @return
	 */
	@Override
	public int updateChannelBatch(List<Channel> channelList){
		return channelBaseDao.updateChannelBatch(channelList);
	}
	/**
	 * 根据序列号删除(主渠道表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelLogic(java.math.BigInteger id){
		return channelBaseDao.deleteChannelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(主渠道表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelLogicBatch(List<java.math.BigInteger> idList){
		return channelBaseDao.deleteChannelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(主渠道表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannel(java.math.BigInteger id){
//		return channelBaseDao.deleteChannel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(主渠道表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelBatch(List<java.math.BigInteger> idList){
//		return channelBaseDao.deleteChannelBatch(idList);
//	}
	
}
