package com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

/**
 * 描述:(配送单推送记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryPushRecorderBaseService {
	
	/**
	 * 根据条件查询(配送单推送记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(配送单推送记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(配送单推送记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(配送单推送记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(配送单推送记录)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryPushRecorder getEbuyDeliveryPushRecorderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryPushRecorderCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryPushRecorderCountDim(Map<String,Object> paramMap);
	/**
	 * 往(配送单推送记录)新增一条记录
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	public int insertEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder);
	/**
	 * 批量新增(配送单推送记录)
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	public int insertEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList);
	/**
	 * 更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	public int updateEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder);
	/**
	 * 批量更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	public int updateEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList);
	/**
	 * 根据序列号删除(配送单推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryPushRecorderLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(配送单推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryPushRecorderLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(配送单推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryPushRecorder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(配送单推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryPushRecorderBatch(List<java.math.BigInteger> idList);
	
}
