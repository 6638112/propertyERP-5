package com.cnfantasia.server.domainbase.smsMq.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;

/**
 * 描述:(手机短信消息队列) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISmsMqBaseService {
	
	/**
	 * 根据条件查询(手机短信消息队列)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SmsMq> getSmsMqByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(手机短信消息队列)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SmsMq> getSmsMqByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(手机短信消息队列)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SmsMq> getSmsMqByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(手机短信消息队列)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SmsMq> getSmsMqByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(手机短信消息队列)信息
	 * @param id
	 * @return
	 */
	public SmsMq getSmsMqBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(手机短信消息队列)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSmsMqCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(手机短信消息队列)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSmsMqCountDim(Map<String,Object> paramMap);
	/**
	 * 往(手机短信消息队列)新增一条记录
	 * @param smsMq
	 * @return
	 */
	public int insertSmsMq(SmsMq smsMq);
	/**
	 * 批量新增(手机短信消息队列)
	 * @param smsMqList
	 * @return
	 */
	public int insertSmsMqBatch(List<SmsMq> smsMqList);
	/**
	 * 更新(手机短信消息队列)信息
	 * @param smsMq
	 * @return
	 */
	public int updateSmsMq(SmsMq smsMq);
	/**
	 * 批量更新(手机短信消息队列)信息
	 * @param smsMqList
	 * @return
	 */
	public int updateSmsMqBatch(List<SmsMq> smsMqList);
	/**
	 * 根据序列号删除(手机短信消息队列)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSmsMqLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(手机短信消息队列)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSmsMqLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(手机短信消息队列)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSmsMq(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(手机短信消息队列)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSmsMqBatch(List<java.math.BigInteger> idList);
	
}
