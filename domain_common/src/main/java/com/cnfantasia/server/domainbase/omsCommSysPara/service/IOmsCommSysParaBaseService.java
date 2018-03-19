package com.cnfantasia.server.domainbase.omsCommSysPara.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsCommSysParaBaseService {
	
	/**
	 * 根据条件查询(OMS系统参数表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS系统参数表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS系统参数表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsCommSysPara> getOmsCommSysParaByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param id
	 * @return
	 */
	public OmsCommSysPara getOmsCommSysParaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsCommSysParaCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsCommSysParaCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param omsCommSysPara
	 * @return
	 */
	public int insertOmsCommSysPara(OmsCommSysPara omsCommSysPara);
	/**
	 * 批量新增(OMS系统参数表)
	 * @param omsCommSysParaList
	 * @return
	 */
	public int insertOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList);
	/**
	 * 更新(OMS系统参数表)信息
	 * @param omsCommSysPara
	 * @return
	 */
	public int updateOmsCommSysPara(OmsCommSysPara omsCommSysPara);
	/**
	 * 批量更新(OMS系统参数表)信息
	 * @param omsCommSysParaList
	 * @return
	 */
	public int updateOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList);
	/**
	 * 根据序列号删除(OMS系统参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsCommSysParaLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS系统参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsCommSysParaLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS系统参数表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsCommSysPara(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS系统参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsCommSysParaBatch(List<java.math.BigInteger> idList);
	
}
