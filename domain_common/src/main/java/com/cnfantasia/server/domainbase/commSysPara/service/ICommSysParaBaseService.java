package com.cnfantasia.server.domainbase.commSysPara.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commSysPara.entity.CommSysPara;

/**
 * 描述:(系统参数) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSysParaBaseService {
	
	/**
	 * 根据条件查询(系统参数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSysPara> getCommSysParaByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(系统参数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSysPara> getCommSysParaByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(系统参数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSysPara> getCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(系统参数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSysPara> getCommSysParaByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(系统参数)信息
	 * @param id
	 * @return
	 */
	public CommSysPara getCommSysParaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(系统参数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSysParaCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(系统参数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSysParaCountDim(Map<String,Object> paramMap);
	/**
	 * 往(系统参数)新增一条记录
	 * @param commSysPara
	 * @return
	 */
	public int insertCommSysPara(CommSysPara commSysPara);
	/**
	 * 批量新增(系统参数)
	 * @param commSysParaList
	 * @return
	 */
	public int insertCommSysParaBatch(List<CommSysPara> commSysParaList);
	/**
	 * 更新(系统参数)信息
	 * @param commSysPara
	 * @return
	 */
	public int updateCommSysPara(CommSysPara commSysPara);
	/**
	 * 批量更新(系统参数)信息
	 * @param commSysParaList
	 * @return
	 */
	public int updateCommSysParaBatch(List<CommSysPara> commSysParaList);
	/**
	 * 根据序列号删除(系统参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommSysParaLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(系统参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommSysParaLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(系统参数)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommSysPara(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(系统参数)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommSysParaBatch(List<java.math.BigInteger> idList);
	
}
