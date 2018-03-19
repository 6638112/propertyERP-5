package com.cnfantasia.server.domainbase.dredgeCouponConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;

/**
 * 描述:(维修券使用配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeCouponConfigBaseService {
	
	/**
	 * 根据条件查询(维修券使用配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeCouponConfig> getDredgeCouponConfigByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修券使用配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeCouponConfig> getDredgeCouponConfigByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修券使用配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeCouponConfig> getDredgeCouponConfigByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修券使用配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeCouponConfig> getDredgeCouponConfigByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修券使用配置表)信息
	 * @param id
	 * @return
	 */
	public DredgeCouponConfig getDredgeCouponConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeCouponConfigCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修券使用配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeCouponConfigCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修券使用配置表)新增一条记录
	 * @param dredgeCouponConfig
	 * @return
	 */
	public int insertDredgeCouponConfig(DredgeCouponConfig dredgeCouponConfig);
	/**
	 * 批量新增(维修券使用配置表)
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
	 * 根据序列号批量删除(维修券使用配置表)信息_逻辑删除
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
