package com.cnfantasia.server.domainbase.wechatDredgebillMq.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;

/**
 * 描述:(微信公众号维修单消息队列) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IWechatDredgebillMqBaseService {
	
	/**
	 * 根据条件查询(微信公众号维修单消息队列)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<WechatDredgebillMq> getWechatDredgebillMqByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微信公众号维修单消息队列)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<WechatDredgebillMq> getWechatDredgebillMqByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微信公众号维修单消息队列)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<WechatDredgebillMq> getWechatDredgebillMqByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微信公众号维修单消息队列)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<WechatDredgebillMq> getWechatDredgebillMqByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微信公众号维修单消息队列)信息
	 * @param id
	 * @return
	 */
	public WechatDredgebillMq getWechatDredgebillMqBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微信公众号维修单消息队列)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getWechatDredgebillMqCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微信公众号维修单消息队列)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getWechatDredgebillMqCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微信公众号维修单消息队列)新增一条记录
	 * @param wechatDredgebillMq
	 * @return
	 */
	public int insertWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq);
	/**
	 * 批量新增(微信公众号维修单消息队列)
	 * @param wechatDredgebillMqList
	 * @return
	 */
	public int insertWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList);
	/**
	 * 更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMq
	 * @return
	 */
	public int updateWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq);
	/**
	 * 批量更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMqList
	 * @return
	 */
	public int updateWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList);
	/**
	 * 根据序列号删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteWechatDredgebillMqLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteWechatDredgebillMqLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微信公众号维修单消息队列)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteWechatDredgebillMq(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微信公众号维修单消息队列)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteWechatDredgebillMqBatch(List<java.math.BigInteger> idList);
	
}
