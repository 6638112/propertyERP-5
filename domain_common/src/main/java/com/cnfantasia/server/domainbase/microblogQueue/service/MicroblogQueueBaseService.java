package com.cnfantasia.server.domainbase.microblogQueue.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogQueue.dao.IMicroblogQueueBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * 描述:(小区博客队列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogQueueBaseService implements IMicroblogQueueBaseService{
	
	private IMicroblogQueueBaseDao microblogQueueBaseDao;
	public void setMicroblogQueueBaseDao(IMicroblogQueueBaseDao microblogQueueBaseDao) {
		this.microblogQueueBaseDao = microblogQueueBaseDao;
	}
	/**
	 * 根据条件查询(小区博客队列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogQueue> getMicroblogQueueByCondition(Map<String,Object> paramMap){
		return microblogQueueBaseDao.selectMicroblogQueueByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区博客队列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogQueue> getMicroblogQueueByConditionDim(Map<String,Object> paramMap){
		return microblogQueueBaseDao.selectMicroblogQueueByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区博客队列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogQueue> getMicroblogQueueByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogQueueBaseDao.selectMicroblogQueueByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区博客队列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogQueue> getMicroblogQueueByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogQueueBaseDao.selectMicroblogQueueByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区博客队列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogQueue getMicroblogQueueBySeqId(java.math.BigInteger id){
		return microblogQueueBaseDao.selectMicroblogQueueBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区博客队列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogQueueCount(Map<String,Object> paramMap){
		return microblogQueueBaseDao.selectMicroblogQueueCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区博客队列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogQueueCountDim(Map<String,Object> paramMap){
		return microblogQueueBaseDao.selectMicroblogQueueCount(paramMap,true);
	}
	/**
	 * 往(小区博客队列表)新增一条记录
	 * @param microblogQueue
	 * @return
	 */
	@Override
	public int insertMicroblogQueue(MicroblogQueue microblogQueue){
		return microblogQueueBaseDao.insertMicroblogQueue(microblogQueue);
	}
	/**
	 * 批量新增(小区博客队列表)
	 * @param microblogQueueList
	 * @return
	 */
	@Override
	public int insertMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList){
		return microblogQueueBaseDao.insertMicroblogQueueBatch(microblogQueueList);
	}
	/**
	 * 更新(小区博客队列表)信息
	 * @param microblogQueue
	 * @return
	 */
	@Override
	public int updateMicroblogQueue(MicroblogQueue microblogQueue){
		return microblogQueueBaseDao.updateMicroblogQueue(microblogQueue);
	}
	/**
	 * 批量更新(小区博客队列表)信息
	 * @param microblogQueueList
	 * @return
	 */
	@Override
	public int updateMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList){
		return microblogQueueBaseDao.updateMicroblogQueueBatch(microblogQueueList);
	}
	/**
	 * 根据序列号删除(小区博客队列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogQueueLogic(java.math.BigInteger id){
		return microblogQueueBaseDao.deleteMicroblogQueueLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区博客队列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogQueueLogicBatch(List<java.math.BigInteger> idList){
		return microblogQueueBaseDao.deleteMicroblogQueueLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区博客队列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogQueue(java.math.BigInteger id){
//		return microblogQueueBaseDao.deleteMicroblogQueue(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区博客队列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogQueueBatch(List<java.math.BigInteger> idList){
//		return microblogQueueBaseDao.deleteMicroblogQueueBatch(idList);
//	}
	
}
