package com.cnfantasia.server.domainbase.microblogGbHasQueue.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogGbHasQueue.dao.IMicroblogGbHasQueueBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogGbHasQueue.entity.MicroblogGbHasQueue;

/**
 * 描述:(小区已发送的队列消息记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogGbHasQueueBaseService implements IMicroblogGbHasQueueBaseService{
	
	private IMicroblogGbHasQueueBaseDao microblogGbHasQueueBaseDao;
	public void setMicroblogGbHasQueueBaseDao(IMicroblogGbHasQueueBaseDao microblogGbHasQueueBaseDao) {
		this.microblogGbHasQueueBaseDao = microblogGbHasQueueBaseDao;
	}
	/**
	 * 根据条件查询(小区已发送的队列消息记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByCondition(Map<String,Object> paramMap){
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区已发送的队列消息记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByConditionDim(Map<String,Object> paramMap){
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区已发送的队列消息记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区已发送的队列消息记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区已发送的队列消息记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogGbHasQueue getMicroblogGbHasQueueBySeqId(java.math.BigInteger id){
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区已发送的队列消息记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogGbHasQueueCount(Map<String,Object> paramMap){
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区已发送的队列消息记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogGbHasQueueCountDim(Map<String,Object> paramMap){
		return microblogGbHasQueueBaseDao.selectMicroblogGbHasQueueCount(paramMap,true);
	}
	/**
	 * 往(小区已发送的队列消息记录表)新增一条记录
	 * @param microblogGbHasQueue
	 * @return
	 */
	@Override
	public int insertMicroblogGbHasQueue(MicroblogGbHasQueue microblogGbHasQueue){
		return microblogGbHasQueueBaseDao.insertMicroblogGbHasQueue(microblogGbHasQueue);
	}
	/**
	 * 批量新增(小区已发送的队列消息记录表)
	 * @param microblogGbHasQueueList
	 * @return
	 */
	@Override
	public int insertMicroblogGbHasQueueBatch(List<MicroblogGbHasQueue> microblogGbHasQueueList){
		return microblogGbHasQueueBaseDao.insertMicroblogGbHasQueueBatch(microblogGbHasQueueList);
	}
	/**
	 * 更新(小区已发送的队列消息记录表)信息
	 * @param microblogGbHasQueue
	 * @return
	 */
	@Override
	public int updateMicroblogGbHasQueue(MicroblogGbHasQueue microblogGbHasQueue){
		return microblogGbHasQueueBaseDao.updateMicroblogGbHasQueue(microblogGbHasQueue);
	}
	/**
	 * 批量更新(小区已发送的队列消息记录表)信息
	 * @param microblogGbHasQueueList
	 * @return
	 */
	@Override
	public int updateMicroblogGbHasQueueBatch(List<MicroblogGbHasQueue> microblogGbHasQueueList){
		return microblogGbHasQueueBaseDao.updateMicroblogGbHasQueueBatch(microblogGbHasQueueList);
	}
	/**
	 * 根据序列号删除(小区已发送的队列消息记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogGbHasQueueLogic(java.math.BigInteger id){
		return microblogGbHasQueueBaseDao.deleteMicroblogGbHasQueueLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区已发送的队列消息记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogGbHasQueueLogicBatch(List<java.math.BigInteger> idList){
		return microblogGbHasQueueBaseDao.deleteMicroblogGbHasQueueLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区已发送的队列消息记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogGbHasQueue(java.math.BigInteger id){
//		return microblogGbHasQueueBaseDao.deleteMicroblogGbHasQueue(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区已发送的队列消息记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogGbHasQueueBatch(List<java.math.BigInteger> idList){
//		return microblogGbHasQueueBaseDao.deleteMicroblogGbHasQueueBatch(idList);
//	}
	
}
