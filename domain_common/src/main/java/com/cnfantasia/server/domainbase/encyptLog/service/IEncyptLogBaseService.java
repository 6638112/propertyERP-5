package com.cnfantasia.server.domainbase.encyptLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.encyptLog.entity.EncyptLog;

/**
 * 描述:(加密日志记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEncyptLogBaseService {
	
	/**
	 * 根据条件查询(加密日志记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EncyptLog> getEncyptLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(加密日志记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EncyptLog> getEncyptLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(加密日志记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EncyptLog> getEncyptLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(加密日志记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EncyptLog> getEncyptLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(加密日志记录)信息
	 * @param id
	 * @return
	 */
	public EncyptLog getEncyptLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEncyptLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(加密日志记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEncyptLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(加密日志记录)新增一条记录
	 * @param encyptLog
	 * @return
	 */
	public int insertEncyptLog(EncyptLog encyptLog);
	/**
	 * 批量新增(加密日志记录)
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
	 * 根据序列号批量删除(加密日志记录)信息_逻辑删除
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
