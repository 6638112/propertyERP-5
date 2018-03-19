package com.cnfantasia.server.domainbase.dredgeCouponConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;

/**
 * 描述:(维修券使用配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeCouponConfigBaseDao {
	/**
	 * 根据条件查询(维修券使用配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeCouponConfig> selectDredgeCouponConfigByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修券使用配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeCouponConfig> selectDredgeCouponConfigByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修券使用配置表)信息
	 * @param id
	 * @return
	 */
	public DredgeCouponConfig selectDredgeCouponConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeCouponConfigCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修券使用配置表)新增一条记录
	 * @param dredgeCouponConfig
	 * @return
	 */
	public int insertDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig);
	
	/**
	 * 批量新增(维修券使用配置表)信息
	 * @param dredgeCouponConfigList
	 * @return
	 */
	public int insertDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList);
	
	/**
	 * 更新(维修券使用配置表)信息
	 * @param dredgeCouponConfig
	 * @return
	 */
	public int updateDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig);
	
	/**
	 * 批量更新(维修券使用配置表)信息
	 * @param dredgeCouponConfigList
	 * @return
	 */
	public int updateDredgeCouponConfigBatch(List<DredgeCouponConfig> dredgeCouponConfigList);
	
	/**
	 * 根据序列号删除(维修券使用配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeCouponConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(维修券使用配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeCouponConfigLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(维修券使用配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeCouponConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修券使用配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeCouponConfigBatch(List<java.math.BigInteger> idList);
	
	
}
