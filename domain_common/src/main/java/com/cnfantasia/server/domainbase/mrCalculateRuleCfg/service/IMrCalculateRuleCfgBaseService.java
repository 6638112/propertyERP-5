package com.cnfantasia.server.domainbase.mrCalculateRuleCfg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;

/**
 * 描述:(抄表计算规则配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrCalculateRuleCfgBaseService {
	
	/**
	 * 根据条件查询(抄表计算规则配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<MrCalculateRuleCfg> getMrCalculateRuleCfgByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(抄表计算规则配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<MrCalculateRuleCfg> getMrCalculateRuleCfgByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(抄表计算规则配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrCalculateRuleCfg> getMrCalculateRuleCfgByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(抄表计算规则配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrCalculateRuleCfg> getMrCalculateRuleCfgByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(抄表计算规则配置)信息
	 * @param id
	 * @return
	 */
	MrCalculateRuleCfg getMrCalculateRuleCfgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表计算规则配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getMrCalculateRuleCfgCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表计算规则配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getMrCalculateRuleCfgCountDim(Map<String, Object> paramMap);
	/**
	 * 往(抄表计算规则配置)新增一条记录
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	int insertMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg);
	/**
	 * 批量新增(抄表计算规则配置)
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	int insertMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList);
	/**
	 * 更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	int updateMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg);
	/**
	 * 批量更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	int updateMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList);
	/**
	 * 根据序列号删除(抄表计算规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteMrCalculateRuleCfgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表计算规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteMrCalculateRuleCfgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表计算规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrCalculateRuleCfg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表计算规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrCalculateRuleCfgBatch(List<java.math.BigInteger> idList);
	
}
