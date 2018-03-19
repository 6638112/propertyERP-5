package com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity.EbuyPrepayAlipayLog;

/**
 * 描述:(淘宝预支付日志表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPrepayAlipayLogBaseDao {
	/**
	 * 根据条件查询(淘宝预支付日志表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPrepayAlipayLog> selectEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(淘宝预支付日志表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPrepayAlipayLog> selectEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(淘宝预支付日志表)信息
	 * @param id
	 * @return
	 */
	public EbuyPrepayAlipayLog selectEbuyPrepayAlipayLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(淘宝预支付日志表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyPrepayAlipayLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(淘宝预支付日志表)新增一条记录
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	public int insertEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog);
	
	/**
	 * 批量新增(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	public int insertEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList);
	
	/**
	 * 更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	public int updateEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog);
	
	/**
	 * 批量更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	public int updateEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList);
	
	/**
	 * 根据序列号删除(淘宝预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyPrepayAlipayLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(淘宝预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyPrepayAlipayLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(淘宝预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyPrepayAlipayLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(淘宝预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyPrepayAlipayLogBatch(List<java.math.BigInteger> idList);
	
	
}
