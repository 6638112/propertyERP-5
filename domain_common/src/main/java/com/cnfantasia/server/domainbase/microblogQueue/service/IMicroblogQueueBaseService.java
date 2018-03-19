package com.cnfantasia.server.domainbase.microblogQueue.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * 描述:(小区博客队列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogQueueBaseService {
	
	/**
	 * 根据条件查询(小区博客队列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogQueue> getMicroblogQueueByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区博客队列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MicroblogQueue> getMicroblogQueueByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区博客队列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogQueue> getMicroblogQueueByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区博客队列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MicroblogQueue> getMicroblogQueueByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区博客队列表)信息
	 * @param id
	 * @return
	 */
	public MicroblogQueue getMicroblogQueueBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区博客队列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogQueueCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区博客队列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMicroblogQueueCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区博客队列表)新增一条记录
	 * @param microblogQueue
	 * @return
	 */
	public int insertMicroblogQueue(MicroblogQueue microblogQueue);
	/**
	 * 批量新增(小区博客队列表)
	 * @param microblogQueueList
	 * @return
	 */
	public int insertMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList);
	/**
	 * 更新(小区博客队列表)信息
	 * @param microblogQueue
	 * @return
	 */
	public int updateMicroblogQueue(MicroblogQueue microblogQueue);
	/**
	 * 批量更新(小区博客队列表)信息
	 * @param microblogQueueList
	 * @return
	 */
	public int updateMicroblogQueueBatch(List<MicroblogQueue> microblogQueueList);
	/**
	 * 根据序列号删除(小区博客队列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogQueueLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区博客队列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogQueueLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区博客队列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogQueue(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区博客队列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogQueueBatch(List<java.math.BigInteger> idList);
	
}
