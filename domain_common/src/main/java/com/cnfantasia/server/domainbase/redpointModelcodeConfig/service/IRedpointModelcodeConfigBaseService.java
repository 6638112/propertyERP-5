package com.cnfantasia.server.domainbase.redpointModelcodeConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.redpointModelcodeConfig.entity.RedpointModelcodeConfig;

/**
 * 描述:(红点模块编码配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRedpointModelcodeConfigBaseService {
	
	/**
	 * 根据条件查询(红点模块编码配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(红点模块编码配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(红点模块编码配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(红点模块编码配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointModelcodeConfig> getRedpointModelcodeConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(红点模块编码配置表)信息
	 * @param id
	 * @return
	 */
	public RedpointModelcodeConfig getRedpointModelcodeConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointModelcodeConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointModelcodeConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(红点模块编码配置表)新增一条记录
	 * @param redpointModelcodeConfig
	 * @return
	 */
	public int insertRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig);
	/**
	 * 批量新增(红点模块编码配置表)
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	public int insertRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList);
	/**
	 * 更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfig
	 * @return
	 */
	public int updateRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig);
	/**
	 * 批量更新(红点模块编码配置表)信息
	 * @param redpointModelcodeConfigList
	 * @return
	 */
	public int updateRedpointModelcodeConfigBatch(List<RedpointModelcodeConfig> redpointModelcodeConfigList);
	/**
	 * 根据序列号删除(红点模块编码配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRedpointModelcodeConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(红点模块编码配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRedpointModelcodeConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(红点模块编码配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRedpointModelcodeConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(红点模块编码配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRedpointModelcodeConfigBatch(List<java.math.BigInteger> idList);
	
}
