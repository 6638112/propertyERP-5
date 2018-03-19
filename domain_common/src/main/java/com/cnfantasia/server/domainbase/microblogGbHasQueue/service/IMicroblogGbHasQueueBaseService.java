package com.cnfantasia.server.domainbase.microblogGbHasQueue.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogGbHasQueue.entity.MicroblogGbHasQueue;

/**
 * 描述:(小区已发送的队列消息记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogGbHasQueueBaseService {
	
	/**
	 * 根据条件查询(小区已发送的队列消息记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区已发送的队列消息记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区已发送的队列消息记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区已发送的队列消息记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogGbHasQueue> getMicroblogGbHasQueueByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区已发送的队列消息记录表)信息
	 * @param id
	 * @return
	 */
	public MicroblogGbHasQueue getMicroblogGbHasQueueBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区已发送的队列消息记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogGbHasQueueCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区已发送的队列消息记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogGbHasQueueCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区已发送的队列消息记录表)新增一条记录
	 * @param microblogGbHasQueue
	 * @return
	 */
	public int insertMicroblogGbHasQueue(MicroblogGbHasQueue microblogGbHasQueue);
	/**
	 * 批量新增(小区已发送的队列消息记录表)
	 * @param microblogGbHasQueueList
	 * @return
	 */
	public int insertMicroblogGbHasQueueBatch(List<MicroblogGbHasQueue> microblogGbHasQueueList);
	/**
	 * 更新(小区已发送的队列消息记录表)信息
	 * @param microblogGbHasQueue
	 * @return
	 */
	public int updateMicroblogGbHasQueue(MicroblogGbHasQueue microblogGbHasQueue);
	/**
	 * 批量更新(小区已发送的队列消息记录表)信息
	 * @param microblogGbHasQueueList
	 * @return
	 */
	public int updateMicroblogGbHasQueueBatch(List<MicroblogGbHasQueue> microblogGbHasQueueList);
	/**
	 * 根据序列号删除(小区已发送的队列消息记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogGbHasQueueLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区已发送的队列消息记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogGbHasQueueLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区已发送的队列消息记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogGbHasQueue(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区已发送的队列消息记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogGbHasQueueBatch(List<java.math.BigInteger> idList);
	
}
