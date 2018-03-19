package com.cnfantasia.server.domainbase.channelPartner.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;

/**
 * 描述:(渠道合伙人) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelPartnerBaseService {
	
	/**
	 * 根据条件查询(渠道合伙人)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelPartner> getChannelPartnerByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(渠道合伙人)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ChannelPartner> getChannelPartnerByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(渠道合伙人)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelPartner> getChannelPartnerByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(渠道合伙人)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ChannelPartner> getChannelPartnerByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(渠道合伙人)信息
	 * @param id
	 * @return
	 */
	public ChannelPartner getChannelPartnerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(渠道合伙人)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelPartnerCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(渠道合伙人)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getChannelPartnerCountDim(Map<String,Object> paramMap);
	/**
	 * 往(渠道合伙人)新增一条记录
	 * @param channelPartner
	 * @return
	 */
	public int insertChannelPartner(ChannelPartner channelPartner);
	/**
	 * 批量新增(渠道合伙人)
	 * @param channelPartnerList
	 * @return
	 */
	public int insertChannelPartnerBatch(List<ChannelPartner> channelPartnerList);
	/**
	 * 更新(渠道合伙人)信息
	 * @param channelPartner
	 * @return
	 */
	public int updateChannelPartner(ChannelPartner channelPartner);
	/**
	 * 批量更新(渠道合伙人)信息
	 * @param channelPartnerList
	 * @return
	 */
	public int updateChannelPartnerBatch(List<ChannelPartner> channelPartnerList);
	/**
	 * 根据序列号删除(渠道合伙人)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteChannelPartnerLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(渠道合伙人)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteChannelPartnerLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(渠道合伙人)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteChannelPartner(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(渠道合伙人)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteChannelPartnerBatch(List<java.math.BigInteger> idList);
	
}
