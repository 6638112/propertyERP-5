package com.cnfantasia.server.domainbase.gbSoftwareConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;

/**
 * 描述:(小区物业软件配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGbSoftwareConfigBaseService {
	
	/**
	 * 根据条件查询(小区物业软件配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<GbSoftwareConfig> getGbSoftwareConfigByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(小区物业软件配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<GbSoftwareConfig> getGbSoftwareConfigByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(小区物业软件配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GbSoftwareConfig> getGbSoftwareConfigByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(小区物业软件配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<GbSoftwareConfig> getGbSoftwareConfigByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(小区物业软件配置)信息
	 * @param id
	 * @return
	 */
	public GbSoftwareConfig getGbSoftwareConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getGbSoftwareConfigCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getGbSoftwareConfigCountDim(Map<String, Object> paramMap);
	/**
	 * 往(小区物业软件配置)新增一条记录
	 * @param gbSoftwareConfig
	 * @return
	 */
	public int insertGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig);
	/**
	 * 批量新增(小区物业软件配置)
	 * @param gbSoftwareConfigList
	 * @return
	 */
	public int insertGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList);
	/**
	 * 更新(小区物业软件配置)信息
	 * @param gbSoftwareConfig
	 * @return
	 */
	public int updateGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig);
	/**
	 * 批量更新(小区物业软件配置)信息
	 * @param gbSoftwareConfigList
	 * @return
	 */
	public int updateGbSoftwareConfigBatch(List<GbSoftwareConfig> gbSoftwareConfigList);
	/**
	 * 根据序列号删除(小区物业软件配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteGbSoftwareConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小区物业软件配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteGbSoftwareConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小区物业软件配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteGbSoftwareConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区物业软件配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteGbSoftwareConfigBatch(List<java.math.BigInteger> idList);
	
}
