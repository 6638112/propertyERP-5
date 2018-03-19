package com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;

/**
 * 描述:(购销模式结算申请表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductSettleApplyLogBaseService {
	
	/**
	 * 根据条件查询(购销模式结算申请表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(购销模式结算申请表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(购销模式结算申请表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(购销模式结算申请表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(购销模式结算申请表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductSettleApplyLog getEbuyProductSettleApplyLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductSettleApplyLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductSettleApplyLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(购销模式结算申请表)新增一条记录
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	public int insertEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog);
	/**
	 * 批量新增(购销模式结算申请表)
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	public int insertEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList);
	/**
	 * 更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	public int updateEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog);
	/**
	 * 批量更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	public int updateEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList);
	/**
	 * 根据序列号删除(购销模式结算申请表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteEbuyProductSettleApplyLogLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(购销模式结算申请表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteEbuyProductSettleApplyLogLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(购销模式结算申请表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductSettleApplyLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(购销模式结算申请表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductSettleApplyLogBatch(List<java.math.BigInteger> idList);
	
}
