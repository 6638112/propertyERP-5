package com.cnfantasia.server.domainbase.gbSoftwareConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;

/**
 * 描述:(小区物业软件配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGbSoftwareConfigBaseDao {
	/**
	 * 根据条件查询(小区物业软件配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GbSoftwareConfig> selectGbSoftwareConfigByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(小区物业软件配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<GbSoftwareConfig> selectGbSoftwareConfigByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(小区物业软件配置)信息
	 * @param id
	 * @return
	 */
	public GbSoftwareConfig selectGbSoftwareConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区物业软件配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectGbSoftwareConfigCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(小区物业软件配置)新增一条记录
	 * @param gbSoftwareConfig
	 * @return
	 */
	public int insertGbSoftwareConfig(GbSoftwareConfig gbSoftwareConfig);
	
	/**
	 * 批量新增(小区物业软件配置)信息
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
	 * 根据Id 批量删除(小区物业软件配置)信息_逻辑删除
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
