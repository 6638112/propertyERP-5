package com.cnfantasia.server.domainbase.wechatDredgebillMq.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.wechatDredgebillMq.dao.IWechatDredgebillMqBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;

/**
 * 描述:(微信公众号维修单消息队列) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class WechatDredgebillMqBaseService implements IWechatDredgebillMqBaseService{
	
	private IWechatDredgebillMqBaseDao wechatDredgebillMqBaseDao;
	public void setWechatDredgebillMqBaseDao(IWechatDredgebillMqBaseDao wechatDredgebillMqBaseDao) {
		this.wechatDredgebillMqBaseDao = wechatDredgebillMqBaseDao;
	}
	/**
	 * 根据条件查询(微信公众号维修单消息队列)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> getWechatDredgebillMqByCondition(Map<String,Object> paramMap){
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微信公众号维修单消息队列)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> getWechatDredgebillMqByConditionDim(Map<String,Object> paramMap){
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微信公众号维修单消息队列)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> getWechatDredgebillMqByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微信公众号维修单消息队列)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> getWechatDredgebillMqByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微信公众号维修单消息队列)信息
	 * @param id
	 * @return
	 */
	@Override
	public WechatDredgebillMq getWechatDredgebillMqBySeqId(java.math.BigInteger id){
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微信公众号维修单消息队列)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getWechatDredgebillMqCount(Map<String,Object> paramMap){
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微信公众号维修单消息队列)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getWechatDredgebillMqCountDim(Map<String,Object> paramMap){
		return wechatDredgebillMqBaseDao.selectWechatDredgebillMqCount(paramMap,true);
	}
	/**
	 * 往(微信公众号维修单消息队列)新增一条记录
	 * @param wechatDredgebillMq
	 * @return
	 */
	@Override
	public int insertWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq){
		return wechatDredgebillMqBaseDao.insertWechatDredgebillMq(wechatDredgebillMq);
	}
	/**
	 * 批量新增(微信公众号维修单消息队列)
	 * @param wechatDredgebillMqList
	 * @return
	 */
	@Override
	public int insertWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList){
		return wechatDredgebillMqBaseDao.insertWechatDredgebillMqBatch(wechatDredgebillMqList);
	}
	/**
	 * 更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMq
	 * @return
	 */
	@Override
	public int updateWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq){
		return wechatDredgebillMqBaseDao.updateWechatDredgebillMq(wechatDredgebillMq);
	}
	/**
	 * 批量更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMqList
	 * @return
	 */
	@Override
	public int updateWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList){
		return wechatDredgebillMqBaseDao.updateWechatDredgebillMqBatch(wechatDredgebillMqList);
	}
	/**
	 * 根据序列号删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteWechatDredgebillMqLogic(java.math.BigInteger id){
		return wechatDredgebillMqBaseDao.deleteWechatDredgebillMqLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteWechatDredgebillMqLogicBatch(List<java.math.BigInteger> idList){
		return wechatDredgebillMqBaseDao.deleteWechatDredgebillMqLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微信公众号维修单消息队列)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteWechatDredgebillMq(java.math.BigInteger id){
//		return wechatDredgebillMqBaseDao.deleteWechatDredgebillMq(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信公众号维修单消息队列)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteWechatDredgebillMqBatch(List<java.math.BigInteger> idList){
//		return wechatDredgebillMqBaseDao.deleteWechatDredgebillMqBatch(idList);
//	}
	
}
