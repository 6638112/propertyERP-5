package com.cnfantasia.server.domainbase.channelPartnerRecommend.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;

/**
 * 描述:(渠道合伙人的推荐) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelPartnerRecommendBaseService {
	
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelPartnerRecommend> getChannelPartnerRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(渠道合伙人的推荐)信息
	 * @param id
	 * @return
	 */
	public ChannelPartnerRecommend getChannelPartnerRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelPartnerRecommendCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelPartnerRecommendCountDim(Map<String,Object> paramMap);
	/**
	 * 往(渠道合伙人的推荐)新增一条记录
	 * @param channelPartnerRecommend
	 * @return
	 */
	public int insertChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend);
	/**
	 * 批量新增(渠道合伙人的推荐)
	 * @param channelPartnerRecommendList
	 * @return
	 */
	public int insertChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList);
	/**
	 * 更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommend
	 * @return
	 */
	public int updateChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend);
	/**
	 * 批量更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommendList
	 * @return
	 */
	public int updateChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList);
	/**
	 * 根据序列号删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteChannelPartnerRecommendLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteChannelPartnerRecommendLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(渠道合伙人的推荐)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteChannelPartnerRecommend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(渠道合伙人的推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteChannelPartnerRecommendBatch(List<java.math.BigInteger> idList);
	
}
