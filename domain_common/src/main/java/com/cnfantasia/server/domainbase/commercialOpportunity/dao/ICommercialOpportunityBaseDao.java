package com.cnfantasia.server.domainbase.commercialOpportunity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commercialOpportunity.entity.CommercialOpportunity;

/**
 * 描述:(商机) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommercialOpportunityBaseDao {
	/**
	 * 根据条件查询(商机)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommercialOpportunity> selectCommercialOpportunityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商机)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommercialOpportunity> selectCommercialOpportunityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商机)信息
	 * @param id
	 * @return
	 */
	public CommercialOpportunity selectCommercialOpportunityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商机)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommercialOpportunityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商机)新增一条记录
	 * @param commercialOpportunity
	 * @return
	 */
	public int insertCommercialOpportunity(CommercialOpportunity commercialOpportunity);
	
	/**
	 * 批量新增(商机)信息
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
	 * 根据Id 批量删除(商机)信息_逻辑删除
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
