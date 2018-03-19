package com.cnfantasia.server.domainbase.dredgeTypePriceConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;

/**
 * 描述:(维修价格配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeTypePriceConfigBaseService {
	
	/**
	 * 根据条件查询(维修价格配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<DredgeTypePriceConfig> getDredgeTypePriceConfigByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修价格配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<DredgeTypePriceConfig> getDredgeTypePriceConfigByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修价格配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DredgeTypePriceConfig> getDredgeTypePriceConfigByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修价格配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DredgeTypePriceConfig> getDredgeTypePriceConfigByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修价格配置表)信息
	 * @param id
	 * @return
	 */
	DredgeTypePriceConfig getDredgeTypePriceConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getDredgeTypePriceConfigCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getDredgeTypePriceConfigCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修价格配置表)新增一条记录
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	int insertDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig);
	/**
	 * 批量新增(维修价格配置表)
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	int insertDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList);
	/**
	 * 更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	int updateDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig);
	/**
	 * 批量更新(维修价格配置表)信息
	 * @param dredgeTypePriceConfigList
	 * @return
	 */
	int updateDredgeTypePriceConfigBatch(List<DredgeTypePriceConfig> dredgeTypePriceConfigList);
	/**
	 * 根据序列号删除(维修价格配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteDredgeTypePriceConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(维修价格配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteDredgeTypePriceConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(维修价格配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeTypePriceConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修价格配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeTypePriceConfigBatch(List<java.math.BigInteger> idList);
	
}
