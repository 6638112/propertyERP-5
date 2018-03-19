package com.cnfantasia.server.domainbase.encyptLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encyptLog.entity.EncyptLog;

/**
 * 描述:(加密日志记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEncyptLogBaseDao {
	/**
	 * 根据条件查询(加密日志记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EncyptLog> selectEncyptLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(加密日志记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EncyptLog> selectEncyptLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(加密日志记录)信息
	 * @param id
	 * @return
	 */
	public EncyptLog selectEncyptLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEncyptLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(加密日志记录)新增一条记录
	 * @param encyptLog
	 * @return
	 */
	public int insertEncyptLog(EncyptLog encyptLog);
	
	/**
	 * 批量新增(加密日志记录)信息
	 * @param encyptLogList
	 * @return
	 */
	public int insertEncyptLogBatch(List<EncyptLog> encyptLogList);
	
	/**
	 * 更新(加密日志记录)信息
	 * @param encyptLog
	 * @return
	 */
	public int updateEncyptLog(EncyptLog encyptLog);
	
	/**
	 * 批量更新(加密日志记录)信息
	 * @param encyptLogList
	 * @return
	 */
	public int updateEncyptLogBatch(List<EncyptLog> encyptLogList);
	
	/**
	 * 根据序列号删除(加密日志记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEncyptLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(加密日志记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEncyptLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(加密日志记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEncyptLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(加密日志记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEncyptLogBatch(List<java.math.BigInteger> idList);
	
	
}
