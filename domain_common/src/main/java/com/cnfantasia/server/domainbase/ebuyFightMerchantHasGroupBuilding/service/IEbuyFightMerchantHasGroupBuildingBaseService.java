package com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;

/**
 * 描述:(自提点服务小区关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyFightMerchantHasGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(自提点服务小区关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(自提点服务小区关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(自提点服务小区关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(自提点服务小区关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(自提点服务小区关系表)信息
	 * @param id
	 * @return
	 */
	public EbuyFightMerchantHasGroupBuilding getEbuyFightMerchantHasGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(自提点服务小区关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyFightMerchantHasGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(自提点服务小区关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyFightMerchantHasGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(自提点服务小区关系表)新增一条记录
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	public int insertEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding);
	/**
	 * 批量新增(自提点服务小区关系表)
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	public int insertEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList);
	/**
	 * 更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	public int updateEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding);
	/**
	 * 批量更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	public int updateEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList);
	/**
	 * 根据序列号删除(自提点服务小区关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyFightMerchantHasGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(自提点服务小区关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyFightMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(自提点服务小区关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyFightMerchantHasGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(自提点服务小区关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyFightMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
