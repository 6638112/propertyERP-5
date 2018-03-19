package com.cnfantasia.server.domainbase.dredgeProductSpecification.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

/**
 * 描述:(维修服务商品规格表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeProductSpecificationBaseDao {
	/**
	 * 根据条件查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeProductSpecification> selectDredgeProductSpecificationByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeProductSpecification> selectDredgeProductSpecificationByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	public DredgeProductSpecification selectDredgeProductSpecificationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeProductSpecificationCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeProductSpecification
	 * @return
	 */
	public int insertDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification);
	
	/**
	 * 批量新增(维修服务商品规格表)信息
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	public int insertDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList);
	
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecification
	 * @return
	 */
	public int updateDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification);
	
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	public int updateDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList);
	
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeProductSpecificationLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeProductSpecificationLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeProductSpecification(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeProductSpecificationBatch(List<java.math.BigInteger> idList);
	
	
}
