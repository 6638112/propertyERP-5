package com.cnfantasia.server.domainbase.redpointModelcodeConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointModelcodeConfig.entity.RedpointModelcodeConfig;

/**
 * 描述:(红点模块编码配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRedpointModelcodeConfigBaseDao {
	/**
	 * 根据条件查询(红点模块编码配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RedpointModelcodeConfig> selectRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(红点模块编码配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RedpointModelcodeConfig> selectRedpointModelcodeConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(红点模块编码配置表)信息
	 * @param id
	 * @return
	 */
	public RedpointModelcodeConfig selectRedpointModelcodeConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(红点模块编码配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRedpointModelcodeConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(红点模块编码配置表)新增一条记录
	 * @param redpointModelcodeConfig
	 * @return
	 */
	public int insertRedpointModelcodeConfig(RedpointModelcodeConfig redpointModelcodeConfig);
	
	/**
	 * 批量新增(红点模块编码配置表)信息
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
	 * 根据Id 批量删除(红点模块编码配置表)信息_逻辑删除
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
