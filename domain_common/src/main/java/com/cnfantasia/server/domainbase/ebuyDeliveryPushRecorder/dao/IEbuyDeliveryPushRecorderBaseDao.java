package com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

/**
 * 描述:(配送单推送记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryPushRecorderBaseDao {
	/**
	 * 根据条件查询(配送单推送记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> selectEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(配送单推送记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryPushRecorder> selectEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(配送单推送记录)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryPushRecorder selectEbuyDeliveryPushRecorderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyDeliveryPushRecorderCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(配送单推送记录)新增一条记录
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	public int insertEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder);
	
	/**
	 * 批量新增(配送单推送记录)信息
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
	 * 根据Id 批量删除(配送单推送记录)信息_逻辑删除
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
