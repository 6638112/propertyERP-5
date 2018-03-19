package com.cnfantasia.server.domainbase.sourceOfSupply.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.sourceOfSupply.entity.SourceOfSupply;

/**
 * 描述:(供应商的货源地) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISourceOfSupplyBaseDao {
	/**
	 * 根据条件查询(供应商的货源地)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SourceOfSupply> selectSourceOfSupplyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商的货源地)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SourceOfSupply> selectSourceOfSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商的货源地)信息
	 * @param id
	 * @return
	 */
	public SourceOfSupply selectSourceOfSupplyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectSourceOfSupplyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商的货源地)新增一条记录
	 * @param sourceOfSupply
	 * @return
	 */
	public int insertSourceOfSupply(SourceOfSupply sourceOfSupply);
	
	/**
	 * 批量新增(供应商的货源地)信息
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
	 * 根据Id 批量删除(供应商的货源地)信息_逻辑删除
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
