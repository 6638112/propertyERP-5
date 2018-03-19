package com.cnfantasia.server.domainbase.dredgeTypePriceConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;

/**
 * 描述:(维修价格配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeTypePriceConfigBaseDao {
	/**
	 * 根据条件查询(维修价格配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DredgeTypePriceConfig> selectDredgeTypePriceConfigByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修价格配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DredgeTypePriceConfig> selectDredgeTypePriceConfigByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修价格配置表)信息
	 * @param id
	 * @return
	 */
	DredgeTypePriceConfig selectDredgeTypePriceConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修价格配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectDredgeTypePriceConfigCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修价格配置表)新增一条记录
	 * @param dredgeTypePriceConfig
	 * @return
	 */
	int insertDredgeTypePriceConfig(DredgeTypePriceConfig dredgeTypePriceConfig);
	
	/**
	 * 批量新增(维修价格配置表)信息
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
	 * 根据Id 批量删除(维修价格配置表)信息_逻辑删除
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
