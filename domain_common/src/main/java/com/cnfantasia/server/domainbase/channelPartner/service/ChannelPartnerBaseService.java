package com.cnfantasia.server.domainbase.channelPartner.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.channelPartner.dao.IChannelPartnerBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;

/**
 * 描述:(渠道合伙人) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ChannelPartnerBaseService implements IChannelPartnerBaseService{
	
	private IChannelPartnerBaseDao channelPartnerBaseDao;
	public void setChannelPartnerBaseDao(IChannelPartnerBaseDao channelPartnerBaseDao) {
		this.channelPartnerBaseDao = channelPartnerBaseDao;
	}
	/**
	 * 根据条件查询(渠道合伙人)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelPartner> getChannelPartnerByCondition(Map<String,Object> paramMap){
		return channelPartnerBaseDao.selectChannelPartnerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(渠道合伙人)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ChannelPartner> getChannelPartnerByConditionDim(Map<String,Object> paramMap){
		return channelPartnerBaseDao.selectChannelPartnerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(渠道合伙人)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelPartner> getChannelPartnerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return channelPartnerBaseDao.selectChannelPartnerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(渠道合伙人)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ChannelPartner> getChannelPartnerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return channelPartnerBaseDao.selectChannelPartnerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(渠道合伙人)信息
	 * @param id
	 * @return
	 */
	@Override
	public ChannelPartner getChannelPartnerBySeqId(java.math.BigInteger id){
		return channelPartnerBaseDao.selectChannelPartnerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(渠道合伙人)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelPartnerCount(Map<String,Object> paramMap){
		return channelPartnerBaseDao.selectChannelPartnerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(渠道合伙人)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getChannelPartnerCountDim(Map<String,Object> paramMap){
		return channelPartnerBaseDao.selectChannelPartnerCount(paramMap,true);
	}
	/**
	 * 往(渠道合伙人)新增一条记录
	 * @param channelPartner
	 * @return
	 */
	@Override
	public int insertChannelPartner(ChannelPartner channelPartner){
		return channelPartnerBaseDao.insertChannelPartner(channelPartner);
	}
	/**
	 * 批量新增(渠道合伙人)
	 * @param channelPartnerList
	 * @return
	 */
	@Override
	public int insertChannelPartnerBatch(List<ChannelPartner> channelPartnerList){
		return channelPartnerBaseDao.insertChannelPartnerBatch(channelPartnerList);
	}
	/**
	 * 更新(渠道合伙人)信息
	 * @param channelPartner
	 * @return
	 */
	@Override
	public int updateChannelPartner(ChannelPartner channelPartner){
		return channelPartnerBaseDao.updateChannelPartner(channelPartner);
	}
	/**
	 * 批量更新(渠道合伙人)信息
	 * @param channelPartnerList
	 * @return
	 */
	@Override
	public int updateChannelPartnerBatch(List<ChannelPartner> channelPartnerList){
		return channelPartnerBaseDao.updateChannelPartnerBatch(channelPartnerList);
	}
	/**
	 * 根据序列号删除(渠道合伙人)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerLogic(java.math.BigInteger id){
		return channelPartnerBaseDao.deleteChannelPartnerLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(渠道合伙人)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerLogicBatch(List<java.math.BigInteger> idList){
		return channelPartnerBaseDao.deleteChannelPartnerLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(渠道合伙人)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartner(java.math.BigInteger id){
//		return channelPartnerBaseDao.deleteChannelPartner(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(渠道合伙人)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartnerBatch(List<java.math.BigInteger> idList){
//		return channelPartnerBaseDao.deleteChannelPartnerBatch(idList);
//	}
	
}
