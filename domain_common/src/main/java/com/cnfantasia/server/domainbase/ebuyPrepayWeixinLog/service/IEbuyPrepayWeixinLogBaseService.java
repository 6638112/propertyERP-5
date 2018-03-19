package com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;

/**
 * 描述:(微信预支付日志表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPrepayWeixinLogBaseService {
	
	/**
	 * 根据条件查询(微信预支付日志表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微信预支付日志表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微信预支付日志表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微信预支付日志表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微信预支付日志表)信息
	 * @param id
	 * @return
	 */
	public EbuyPrepayWeixinLog getEbuyPrepayWeixinLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微信预支付日志表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPrepayWeixinLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微信预支付日志表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPrepayWeixinLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微信预支付日志表)新增一条记录
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	public int insertEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog);
	/**
	 * 批量新增(微信预支付日志表)
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	public int insertEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList);
	/**
	 * 更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	public int updateEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog);
	/**
	 * 批量更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	public int updateEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList);
	/**
	 * 根据序列号删除(微信预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyPrepayWeixinLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微信预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyPrepayWeixinLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微信预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyPrepayWeixinLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微信预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyPrepayWeixinLogBatch(List<java.math.BigInteger> idList);
	
}
