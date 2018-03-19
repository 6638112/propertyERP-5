package com.cnfantasia.server.domainbase.smsMq.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.smsMq.dao.ISmsMqBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;

/**
 * 描述:(手机短信消息队列) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SmsMqBaseService implements ISmsMqBaseService{
	
	private ISmsMqBaseDao smsMqBaseDao;
	public void setSmsMqBaseDao(ISmsMqBaseDao smsMqBaseDao) {
		this.smsMqBaseDao = smsMqBaseDao;
	}
	/**
	 * 根据条件查询(手机短信消息队列)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SmsMq> getSmsMqByCondition(Map<String,Object> paramMap){
		return smsMqBaseDao.selectSmsMqByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(手机短信消息队列)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SmsMq> getSmsMqByConditionDim(Map<String,Object> paramMap){
		return smsMqBaseDao.selectSmsMqByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(手机短信消息队列)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SmsMq> getSmsMqByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return smsMqBaseDao.selectSmsMqByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(手机短信消息队列)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SmsMq> getSmsMqByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return smsMqBaseDao.selectSmsMqByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(手机短信消息队列)信息
	 * @param id
	 * @return
	 */
	@Override
	public SmsMq getSmsMqBySeqId(java.math.BigInteger id){
		return smsMqBaseDao.selectSmsMqBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(手机短信消息队列)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSmsMqCount(Map<String,Object> paramMap){
		return smsMqBaseDao.selectSmsMqCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(手机短信消息队列)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSmsMqCountDim(Map<String,Object> paramMap){
		return smsMqBaseDao.selectSmsMqCount(paramMap,true);
	}
	/**
	 * 往(手机短信消息队列)新增一条记录
	 * @param smsMq
	 * @return
	 */
	@Override
	public int insertSmsMq(SmsMq smsMq){
		return smsMqBaseDao.insertSmsMq(smsMq);
	}
	/**
	 * 批量新增(手机短信消息队列)
	 * @param smsMqList
	 * @return
	 */
	@Override
	public int insertSmsMqBatch(List<SmsMq> smsMqList){
		return smsMqBaseDao.insertSmsMqBatch(smsMqList);
	}
	/**
	 * 更新(手机短信消息队列)信息
	 * @param smsMq
	 * @return
	 */
	@Override
	public int updateSmsMq(SmsMq smsMq){
		return smsMqBaseDao.updateSmsMq(smsMq);
	}
	/**
	 * 批量更新(手机短信消息队列)信息
	 * @param smsMqList
	 * @return
	 */
	@Override
	public int updateSmsMqBatch(List<SmsMq> smsMqList){
		return smsMqBaseDao.updateSmsMqBatch(smsMqList);
	}
	/**
	 * 根据序列号删除(手机短信消息队列)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSmsMqLogic(java.math.BigInteger id){
		return smsMqBaseDao.deleteSmsMqLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(手机短信消息队列)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSmsMqLogicBatch(List<java.math.BigInteger> idList){
		return smsMqBaseDao.deleteSmsMqLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(手机短信消息队列)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSmsMq(java.math.BigInteger id){
//		return smsMqBaseDao.deleteSmsMq(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(手机短信消息队列)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSmsMqBatch(List<java.math.BigInteger> idList){
//		return smsMqBaseDao.deleteSmsMqBatch(idList);
//	}
	
}
