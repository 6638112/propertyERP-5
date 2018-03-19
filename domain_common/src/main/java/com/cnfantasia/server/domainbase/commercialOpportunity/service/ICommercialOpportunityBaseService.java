package com.cnfantasia.server.domainbase.commercialOpportunity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commercialOpportunity.entity.CommercialOpportunity;

/**
 * 描述:(商机) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommercialOpportunityBaseService {
	
	/**
	 * 根据条件查询(商机)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommercialOpportunity> getCommercialOpportunityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商机)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommercialOpportunity> getCommercialOpportunityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商机)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommercialOpportunity> getCommercialOpportunityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商机)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommercialOpportunity> getCommercialOpportunityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商机)信息
	 * @param id
	 * @return
	 */
	public CommercialOpportunity getCommercialOpportunityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商机)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommercialOpportunityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商机)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommercialOpportunityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商机)新增一条记录
	 * @param commercialOpportunity
	 * @return
	 */
	public int insertCommercialOpportunity(CommercialOpportunity commercialOpportunity);
	/**
	 * 批量新增(商机)
	 * @param commercialOpportunityList
	 * @return
	 */
	public int insertCommercialOpportunityBatch(List<CommercialOpportunity> commercialOpportunityList);
	/**
	 * 更新(商机)信息
	 * @param commercialOpportunity
	 * @return
	 */
	public int updateCommercialOpportunity(CommercialOpportunity commercialOpportunity);
	/**
	 * 批量更新(商机)信息
	 * @param commercialOpportunityList
	 * @return
	 */
	public int updateCommercialOpportunityBatch(List<CommercialOpportunity> commercialOpportunityList);
	/**
	 * 根据序列号删除(商机)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommercialOpportunityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商机)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommercialOpportunityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商机)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommercialOpportunity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商机)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommercialOpportunityBatch(List<java.math.BigInteger> idList);
	
}
