package com.cnfantasia.server.domainbase.ebuyLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyLog.entity.EbuyLog;

/**
 * 描述:(扫二维码进商品详情日志表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyLogBaseDao {
	/**
	 * 根据条件查询(扫二维码进商品详情日志表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyLog> selectEbuyLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(扫二维码进商品详情日志表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyLog> selectEbuyLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(扫二维码进商品详情日志表)信息
	 * @param id
	 * @return
	 */
	public EbuyLog selectEbuyLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(扫二维码进商品详情日志表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(扫二维码进商品详情日志表)新增一条记录
	 * @param ebuyLog
	 * @return
	 */
	public int insertEbuyLog(EbuyLog ebuyLog);
	
	/**
	 * 批量新增(扫二维码进商品详情日志表)信息
	 * @param ebuyLogList
	 * @return
	 */
	public int insertEbuyLogBatch(List<EbuyLog> ebuyLogList);
	
	/**
	 * 更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLog
	 * @return
	 */
	public int updateEbuyLog(EbuyLog ebuyLog);
	
	/**
	 * 批量更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLogList
	 * @return
	 */
	public int updateEbuyLogBatch(List<EbuyLog> ebuyLogList);
	
	/**
	 * 根据序列号删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(扫二维码进商品详情日志表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(扫二维码进商品详情日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyLogBatch(List<java.math.BigInteger> idList);
	
	
}
