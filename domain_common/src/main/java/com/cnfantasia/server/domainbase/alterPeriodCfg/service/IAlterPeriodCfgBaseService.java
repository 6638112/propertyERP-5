package com.cnfantasia.server.domainbase.alterPeriodCfg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;

/**
 * 描述:(选择周期配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterPeriodCfgBaseService {
	
	/**
	 * 根据条件查询(选择周期配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodCfg> getAlterPeriodCfgByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(选择周期配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodCfg> getAlterPeriodCfgByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(选择周期配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodCfg> getAlterPeriodCfgByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(选择周期配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodCfg> getAlterPeriodCfgByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(选择周期配置)信息
	 * @param id
	 * @return
	 */
	AlterPeriodCfg getAlterPeriodCfgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(选择周期配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodCfgCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(选择周期配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodCfgCountDim(Map<String, Object> paramMap);
	/**
	 * 往(选择周期配置)新增一条记录
	 * @param alterPeriodCfg
	 * @return
	 */
	int insertAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg);
	/**
	 * 批量新增(选择周期配置)
	 * @param alterPeriodCfgList
	 * @return
	 */
	int insertAlterPeriodCfgBatch(List<AlterPeriodCfg> alterPeriodCfgList);
	/**
	 * 更新(选择周期配置)信息
	 * @param alterPeriodCfg
	 * @return
	 */
	int updateAlterPeriodCfg(AlterPeriodCfg alterPeriodCfg);
	/**
	 * 批量更新(选择周期配置)信息
	 * @param alterPeriodCfgList
	 * @return
	 */
	int updateAlterPeriodCfgBatch(List<AlterPeriodCfg> alterPeriodCfgList);
	/**
	 * 根据序列号删除(选择周期配置)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteAlterPeriodCfgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(选择周期配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteAlterPeriodCfgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(选择周期配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAlterPeriodCfg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(选择周期配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAlterPeriodCfgBatch(List<java.math.BigInteger> idList);
	
}
