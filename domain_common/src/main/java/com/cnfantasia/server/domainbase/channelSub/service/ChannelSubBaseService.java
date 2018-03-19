package com.cnfantasia.server.domainbase.channelSub.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.channelSub.dao.IChannelSubBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelSub.entity.ChannelSub;

/**
 * 描述:(子渠道表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ChannelSubBaseService implements IChannelSubBaseService{
	
	private IChannelSubBaseDao channelSubBaseDao;
	public void setChannelSubBaseDao(IChannelSubBaseDao channelSubBaseDao) {
		this.channelSubBaseDao = channelSubBaseDao;
	}
	/**
	 * 根据条件查询(子渠道表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelSub> getChannelSubByCondition(Map<String,Object> paramMap){
		return channelSubBaseDao.selectChannelSubByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(子渠道表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelSub> getChannelSubByConditionDim(Map<String,Object> paramMap){
		return channelSubBaseDao.selectChannelSubByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(子渠道表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelSub> getChannelSubByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return channelSubBaseDao.selectChannelSubByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(子渠道表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelSub> getChannelSubByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return channelSubBaseDao.selectChannelSubByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(子渠道表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ChannelSub getChannelSubBySeqId(java.lang.Long id){
		return channelSubBaseDao.selectChannelSubBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelSubCount(Map<String,Object> paramMap){
		return channelSubBaseDao.selectChannelSubCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelSubCountDim(Map<String,Object> paramMap){
		return channelSubBaseDao.selectChannelSubCount(paramMap,true);
	}
	/**
	 * 往(子渠道表)新增一条记录
	 * @param channelSub
	 * @return
	 */
	@Override
	public int insertChannelSub(ChannelSub channelSub){
		return channelSubBaseDao.insertChannelSub(channelSub);
	}
	/**
	 * 批量新增(子渠道表)
	 * @param channelSubList
	 * @return
	 */
	@Override
	public int insertChannelSubBatch(List<ChannelSub> channelSubList){
		return channelSubBaseDao.insertChannelSubBatch(channelSubList);
	}
	/**
	 * 更新(子渠道表)信息
	 * @param channelSub
	 * @return
	 */
	@Override
	public int updateChannelSub(ChannelSub channelSub){
		return channelSubBaseDao.updateChannelSub(channelSub);
	}
	/**
	 * 批量更新(子渠道表)信息
	 * @param channelSubList
	 * @return
	 */
	@Override
	public int updateChannelSubBatch(List<ChannelSub> channelSubList){
		return channelSubBaseDao.updateChannelSubBatch(channelSubList);
	}
	/**
	 * 根据序列号删除(子渠道表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelSubLogic(java.lang.Long id){
		return channelSubBaseDao.deleteChannelSubLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(子渠道表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelSubLogicBatch(List<java.lang.Long> idList){
		return channelSubBaseDao.deleteChannelSubLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(子渠道表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannelSub(java.lang.Long id){
//		return channelSubBaseDao.deleteChannelSub(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(子渠道表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelSubBatch(List<java.lang.Long> idList){
//		return channelSubBaseDao.deleteChannelSubBatch(idList);
//	}
	
}
