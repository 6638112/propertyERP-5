package com.cnfantasia.server.domainbase.smsMq.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;

/**
 * 描述:(手机短信消息队列) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISmsMqBaseDao {
	/**
	 * 根据条件查询(手机短信消息队列)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SmsMq> selectSmsMqByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(手机短信消息队列)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SmsMq> selectSmsMqByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(手机短信消息队列)信息
	 * @param id
	 * @return
	 */
	public SmsMq selectSmsMqBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(手机短信消息队列)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectSmsMqCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(手机短信消息队列)新增一条记录
	 * @param smsMq
	 * @return
	 */
	public int insertSmsMq(SmsMq smsMq);
	
	/**
	 * 批量新增(手机短信消息队列)信息
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
	 * 根据Id 批量删除(手机短信消息队列)信息_逻辑删除
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
