package com.cnfantasia.server.domainbase.channelPartnerRecommend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;

/**
 * 描述:(渠道合伙人的推荐) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelPartnerRecommendBaseDao {
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelPartnerRecommend> selectChannelPartnerRecommendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelPartnerRecommend> selectChannelPartnerRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(渠道合伙人的推荐)信息
	 * @param id
	 * @return
	 */
	public ChannelPartnerRecommend selectChannelPartnerRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectChannelPartnerRecommendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(渠道合伙人的推荐)新增一条记录
	 * @param channelPartnerRecommend
	 * @return
	 */
	public int insertChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend);
	
	/**
	 * 批量新增(渠道合伙人的推荐)信息
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
	 * 根据Id 批量删除(渠道合伙人的推荐)信息_逻辑删除
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
