package com.cnfantasia.server.domainbase.ebuyPrepaySqLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity.EbuyPrepaySqLog;

/**
 * 描述:(双乾预支付记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPrepaySqLogBaseService {
	
	/**
	 * 根据条件查询(双乾预支付记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(双乾预支付记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(双乾预支付记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(双乾预支付记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(双乾预支付记录表)信息
	 * @param id
	 * @return
	 */
	public EbuyPrepaySqLog getEbuyPrepaySqLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(双乾预支付记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPrepaySqLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(双乾预支付记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyPrepaySqLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(双乾预支付记录表)新增一条记录
	 * @param ebuyPrepaySqLog
	 * @return
	 */
	public int insertEbuyPrepaySqLog(EbuyPrepaySqLog ebuyPrepaySqLog);
	/**
	 * 批量新增(双乾预支付记录表)
	 * @param ebuyPrepaySqLogList
	 * @return
	 */
	public int insertEbuyPrepaySqLogBatch(List<EbuyPrepaySqLog> ebuyPrepaySqLogList);
	/**
	 * 更新(双乾预支付记录表)信息
	 * @param ebuyPrepaySqLog
	 * @return
	 */
	public int updateEbuyPrepaySqLog(EbuyPrepaySqLog ebuyPrepaySqLog);
	/**
	 * 批量更新(双乾预支付记录表)信息
	 * @param ebuyPrepaySqLogList
	 * @return
	 */
	public int updateEbuyPrepaySqLogBatch(List<EbuyPrepaySqLog> ebuyPrepaySqLogList);
	/**
	 * 根据序列号删除(双乾预支付记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyPrepaySqLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(双乾预支付记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyPrepaySqLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(双乾预支付记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyPrepaySqLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(双乾预支付记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyPrepaySqLogBatch(List<java.math.BigInteger> idList);
	
}
