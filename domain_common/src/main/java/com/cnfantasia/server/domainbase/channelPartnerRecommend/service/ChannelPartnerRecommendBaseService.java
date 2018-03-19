package com.cnfantasia.server.domainbase.channelPartnerRecommend.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.channelPartnerRecommend.dao.IChannelPartnerRecommendBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;

/**
 * 描述:(渠道合伙人的推荐) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ChannelPartnerRecommendBaseService implements IChannelPartnerRecommendBaseService{
	
	private IChannelPartnerRecommendBaseDao channelPartnerRecommendBaseDao;
	public void setChannelPartnerRecommendBaseDao(IChannelPartnerRecommendBaseDao channelPartnerRecommendBaseDao) {
		this.channelPartnerRecommendBaseDao = channelPartnerRecommendBaseDao;
	}
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByCondition(Map<String,Object> paramMap){
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByConditionDim(Map<String,Object> paramMap){
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(渠道合伙人的推荐)信息
	 * @param id
	 * @return
	 */
	@Override
	public ChannelPartnerRecommend getChannelPartnerRecommendBySeqId(java.math.BigInteger id){
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelPartnerRecommendCount(Map<String,Object> paramMap){
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelPartnerRecommendCountDim(Map<String,Object> paramMap){
		return channelPartnerRecommendBaseDao.selectChannelPartnerRecommendCount(paramMap,true);
	}
	/**
	 * 往(渠道合伙人的推荐)新增一条记录
	 * @param channelPartnerRecommend
	 * @return
	 */
	@Override
	public int insertChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend){
		return channelPartnerRecommendBaseDao.insertChannelPartnerRecommend(channelPartnerRecommend);
	}
	/**
	 * 批量新增(渠道合伙人的推荐)
	 * @param channelPartnerRecommendList
	 * @return
	 */
	@Override
	public int insertChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList){
		return channelPartnerRecommendBaseDao.insertChannelPartnerRecommendBatch(channelPartnerRecommendList);
	}
	/**
	 * 更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommend
	 * @return
	 */
	@Override
	public int updateChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend){
		return channelPartnerRecommendBaseDao.updateChannelPartnerRecommend(channelPartnerRecommend);
	}
	/**
	 * 批量更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommendList
	 * @return
	 */
	@Override
	public int updateChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList){
		return channelPartnerRecommendBaseDao.updateChannelPartnerRecommendBatch(channelPartnerRecommendList);
	}
	/**
	 * 根据序列号删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerRecommendLogic(java.math.BigInteger id){
		return channelPartnerRecommendBaseDao.deleteChannelPartnerRecommendLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerRecommendLogicBatch(List<java.math.BigInteger> idList){
		return channelPartnerRecommendBaseDao.deleteChannelPartnerRecommendLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(渠道合伙人的推荐)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartnerRecommend(java.math.BigInteger id){
//		return channelPartnerRecommendBaseDao.deleteChannelPartnerRecommend(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(渠道合伙人的推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartnerRecommendBatch(List<java.math.BigInteger> idList){
//		return channelPartnerRecommendBaseDao.deleteChannelPartnerRecommendBatch(idList);
//	}
	
}
