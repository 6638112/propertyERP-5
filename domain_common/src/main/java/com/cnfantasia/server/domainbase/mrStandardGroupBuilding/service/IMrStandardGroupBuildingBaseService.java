package com.cnfantasia.server.domainbase.mrStandardGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;

/**
 * 描述:(抄表收费标准(小区)) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrStandardGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(抄表收费标准(小区))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardGroupBuilding> getMrStandardGroupBuildingByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(抄表收费标准(小区))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardGroupBuilding> getMrStandardGroupBuildingByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(抄表收费标准(小区))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardGroupBuilding> getMrStandardGroupBuildingByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(抄表收费标准(小区))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardGroupBuilding> getMrStandardGroupBuildingByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(抄表收费标准(小区))信息
	 * @param id
	 * @return
	 */
	MrStandardGroupBuilding getMrStandardGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(小区))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardGroupBuildingCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(小区))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardGroupBuildingCountDim(Map<String, Object> paramMap);
	/**
	 * 往(抄表收费标准(小区))新增一条记录
	 * @param mrStandardGroupBuilding
	 * @return
	 */
	int insertMrStandardGroupBuilding(MrStandardGroupBuilding mrStandardGroupBuilding);
	/**
	 * 批量新增(抄表收费标准(小区))
	 * @param mrStandardGroupBuildingList
	 * @return
	 */
	int insertMrStandardGroupBuildingBatch(List<MrStandardGroupBuilding> mrStandardGroupBuildingList);
	/**
	 * 更新(抄表收费标准(小区))信息
	 * @param mrStandardGroupBuilding
	 * @return
	 */
	int updateMrStandardGroupBuilding(MrStandardGroupBuilding mrStandardGroupBuilding);
	/**
	 * 批量更新(抄表收费标准(小区))信息
	 * @param mrStandardGroupBuildingList
	 * @return
	 */
	int updateMrStandardGroupBuildingBatch(List<MrStandardGroupBuilding> mrStandardGroupBuildingList);
	/**
	 * 根据序列号删除(抄表收费标准(小区))信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteMrStandardGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表收费标准(小区))信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteMrStandardGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表收费标准(小区))信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrStandardGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(小区))信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrStandardGroupBuildingBatch(List<java.math.BigInteger> idList);
	
}
