package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;

/**
 * 描述:(供应商与小区关联表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuySupplyMerchantHasGroupBuildingBaseDao {
	/**
	 * 根据条件查询(供应商与小区关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> selectEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(供应商与小区关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuySupplyMerchantHasGroupBuilding> selectEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(供应商与小区关联表)信息
	 * @param id
	 * @return
	 */
	public EbuySupplyMerchantHasGroupBuilding selectEbuySupplyMerchantHasGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuySupplyMerchantHasGroupBuildingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(供应商与小区关联表)新增一条记录
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	public int insertEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding);
	
	/**
	 * 批量新增(供应商与小区关联表)信息
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
	 * 根据Id 批量删除(供应商与小区关联表)信息_逻辑删除
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
