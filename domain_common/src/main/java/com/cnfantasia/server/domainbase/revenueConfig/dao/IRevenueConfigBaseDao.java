package com.cnfantasia.server.domainbase.revenueConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 描述:(收益规则配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueConfigBaseDao {
	/**
	 * 根据条件查询(收益规则配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueConfig> selectRevenueConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(收益规则配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueConfig> selectRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(收益规则配置表)信息
	 * @param id
	 * @return
	 */
	public RevenueConfig selectRevenueConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(收益规则配置表)新增一条记录
	 * @param revenueConfig
	 * @return
	 */
	public int insertRevenueConfig(RevenueConfig revenueConfig);
	
	/**
	 * 批量新增(收益规则配置表)信息
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
	 * 根据Id 批量删除(收益规则配置表)信息_逻辑删除
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
