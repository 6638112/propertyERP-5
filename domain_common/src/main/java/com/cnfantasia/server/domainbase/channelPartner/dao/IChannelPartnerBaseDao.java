package com.cnfantasia.server.domainbase.channelPartner.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;

/**
 * 描述:(渠道合伙人) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IChannelPartnerBaseDao {
	/**
	 * 根据条件查询(渠道合伙人)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelPartner> selectChannelPartnerByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(渠道合伙人)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ChannelPartner> selectChannelPartnerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(渠道合伙人)信息
	 * @param id
	 * @return
	 */
	public ChannelPartner selectChannelPartnerBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(渠道合伙人)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectChannelPartnerCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(渠道合伙人)新增一条记录
	 * @param channelPartner
	 * @return
	 */
	public int insertChannelPartner(ChannelPartner channelPartner);
	
	/**
	 * 批量新增(渠道合伙人)信息
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
	 * 根据Id 批量删除(渠道合伙人)信息_逻辑删除
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
