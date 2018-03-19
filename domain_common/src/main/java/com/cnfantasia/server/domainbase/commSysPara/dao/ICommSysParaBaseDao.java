package com.cnfantasia.server.domainbase.commSysPara.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysPara.entity.CommSysPara;

/**
 * 描述:(系统参数) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSysParaBaseDao {
	/**
	 * 根据条件查询(系统参数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSysPara> selectCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(系统参数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSysPara> selectCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(系统参数)信息
	 * @param id
	 * @return
	 */
	public CommSysPara selectCommSysParaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(系统参数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommSysParaCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(系统参数)新增一条记录
	 * @param commSysPara
	 * @return
	 */
	public int insertCommSysPara(CommSysPara commSysPara);
	
	/**
	 * 批量新增(系统参数)信息
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
	 * 根据Id 批量删除(系统参数)信息_逻辑删除
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
