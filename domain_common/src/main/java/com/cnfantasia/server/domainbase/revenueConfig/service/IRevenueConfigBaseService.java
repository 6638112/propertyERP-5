package com.cnfantasia.server.domainbase.revenueConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 描述:(收益规则配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueConfigBaseService {
	
	/**
	 * 根据条件查询(收益规则配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueConfig> getRevenueConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(收益规则配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueConfig> getRevenueConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(收益规则配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueConfig> getRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(收益规则配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueConfig> getRevenueConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(收益规则配置表)信息
	 * @param id
	 * @return
	 */
	public RevenueConfig getRevenueConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(收益规则配置表)新增一条记录
	 * @param revenueConfig
	 * @return
	 */
	public int insertRevenueConfig(RevenueConfig revenueConfig);
	/**
	 * 批量新增(收益规则配置表)
	 * @param revenueConfigList
	 * @return
	 */
	public int insertRevenueConfigBatch(List<RevenueConfig> revenueConfigList);
	/**
	 * 更新(收益规则配置表)信息
	 * @param revenueConfig
	 * @return
	 */
	public int updateRevenueConfig(RevenueConfig revenueConfig);
	/**
	 * 批量更新(收益规则配置表)信息
	 * @param revenueConfigList
	 * @return
	 */
	public int updateRevenueConfigBatch(List<RevenueConfig> revenueConfigList);
	/**
	 * 根据序列号删除(收益规则配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRevenueConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(收益规则配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRevenueConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(收益规则配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(收益规则配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueConfigBatch(List<java.math.BigInteger> idList);
	
}
