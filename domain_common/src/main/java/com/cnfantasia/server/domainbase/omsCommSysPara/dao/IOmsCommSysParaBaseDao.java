package com.cnfantasia.server.domainbase.omsCommSysPara.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsCommSysParaBaseDao {
	/**
	 * 根据条件查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param id
	 * @return
	 */
	public OmsCommSysPara selectOmsCommSysParaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsCommSysParaCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param omsCommSysPara
	 * @return
	 */
	public int insertOmsCommSysPara(OmsCommSysPara omsCommSysPara);
	
	/**
	 * 批量新增(OMS系统参数表)信息
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
	 * 根据Id 批量删除(OMS系统参数表)信息_逻辑删除
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
