package com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.entity.DredgeBillHasProductSpec;

/**
 * 描述:(维修服务商品规格表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillHasProductSpecBaseService {
	
	/**
	 * 根据条件查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修服务商品规格表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasProductSpec> getDredgeBillHasProductSpecByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	public DredgeBillHasProductSpec getDredgeBillHasProductSpecBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasProductSpecCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasProductSpecCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	public int insertDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec);
	/**
	 * 批量新增(维修服务商品规格表)
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	public int insertDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList);
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	public int updateDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec);
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	public int updateDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList);
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillHasProductSpecLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillHasProductSpecLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillHasProductSpec(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillHasProductSpecBatch(List<java.math.BigInteger> idList);
	
}
