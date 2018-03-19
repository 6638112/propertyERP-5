package com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;

/**
 * 描述:(购销模式结算申请表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductSettleApplyLogBaseDao {
	/**
	 * 根据条件查询(购销模式结算申请表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> selectEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(购销模式结算申请表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductSettleApplyLog> selectEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(购销模式结算申请表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductSettleApplyLog selectEbuyProductSettleApplyLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductSettleApplyLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(购销模式结算申请表)新增一条记录
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	public int insertEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog);
	
	/**
	 * 批量新增(购销模式结算申请表)信息
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
	 * 根据Id 批量删除(购销模式结算申请表)信息_逻辑删除
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
