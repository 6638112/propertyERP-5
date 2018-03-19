package com.cnfantasia.server.domainbase.ebuyPrepaySqLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity.EbuyPrepaySqLog;

/**
 * 描述:(双乾预支付记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyPrepaySqLogBaseDao {
	/**
	 * 根据条件查询(双乾预支付记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPrepaySqLog> selectEbuyPrepaySqLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(双乾预支付记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyPrepaySqLog> selectEbuyPrepaySqLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(双乾预支付记录表)信息
	 * @param id
	 * @return
	 */
	public EbuyPrepaySqLog selectEbuyPrepaySqLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(双乾预支付记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyPrepaySqLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(双乾预支付记录表)新增一条记录
	 * @param ebuyPrepaySqLog
	 * @return
	 */
	public int insertEbuyPrepaySqLog(EbuyPrepaySqLog ebuyPrepaySqLog);
	
	/**
	 * 批量新增(双乾预支付记录表)信息
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
	 * 根据Id 批量删除(双乾预支付记录表)信息_逻辑删除
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
