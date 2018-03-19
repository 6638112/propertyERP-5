package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;

/**
 * 描述:(供应商与小区关联表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(供应商与小区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(供应商与小区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(供应商与小区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(供应商与小区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(供应商与小区关联表)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasGroupBuilding getEbuySupplyMerchantHasGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuySupplyMerchantHasGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(供应商与小区关联表)新增一条记录
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	public int insertEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding);
	/**
	 * 批量新增(供应商与小区关联表)
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	public int insertEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList);
	/**
	 * 更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	public int updateEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding);
	/**
	 * 批量更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	public int updateEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList);
	/**
	 * 根据序列号删除(供应商与小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(供应商与小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuySupplyMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(供应商与小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(供应商与小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuySupplyMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
