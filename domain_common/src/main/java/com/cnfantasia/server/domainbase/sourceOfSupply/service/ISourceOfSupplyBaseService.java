package com.cnfantasia.server.domainbase.sourceOfSupply.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.sourceOfSupply.entity.SourceOfSupply;

/**
 * 描述:(供应商的货源地) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISourceOfSupplyBaseService {
	
	/**
	 * 根据条件查询(供应商的货源地)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SourceOfSupply> getSourceOfSupplyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商的货源地)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SourceOfSupply> getSourceOfSupplyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商的货源地)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SourceOfSupply> getSourceOfSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商的货源地)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SourceOfSupply> getSourceOfSupplyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商的货源地)信息
	 * @param id
	 * @return
	 */
	public SourceOfSupply getSourceOfSupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSourceOfSupplyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSourceOfSupplyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商的货源地)新增一条记录
	 * @param sourceOfSupply
	 * @return
	 */
	public int insertSourceOfSupply(SourceOfSupply sourceOfSupply);
	/**
	 * 批量新增(供应商的货源地)
	 * @param sourceOfSupplyList
	 * @return
	 */
	public int insertSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList);
	/**
	 * 更新(供应商的货源地)信息
	 * @param sourceOfSupply
	 * @return
	 */
	public int updateSourceOfSupply(SourceOfSupply sourceOfSupply);
	/**
	 * 批量更新(供应商的货源地)信息
	 * @param sourceOfSupplyList
	 * @return
	 */
	public int updateSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList);
	/**
	 * 根据序列号删除(供应商的货源地)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSourceOfSupplyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商的货源地)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSourceOfSupplyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商的货源地)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSourceOfSupply(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商的货源地)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSourceOfSupplyBatch(List<java.math.BigInteger> idList);
	
}
