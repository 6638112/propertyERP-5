package com.cnfantasia.server.domainbase.mrStandardGroupBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;

/**
 * 描述:(抄表收费标准(小区)) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrStandardGroupBuildingBaseDao {
	/**
	 * 根据条件查询(抄表收费标准(小区))信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<MrStandardGroupBuilding> selectMrStandardGroupBuildingByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(抄表收费标准(小区))信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<MrStandardGroupBuilding> selectMrStandardGroupBuildingByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(抄表收费标准(小区))信息
	 * @param id
	 * @return
	 */
	MrStandardGroupBuilding selectMrStandardGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(小区))记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectMrStandardGroupBuildingCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(抄表收费标准(小区))新增一条记录
	 * @param mrStandardGroupBuilding
	 * @return
	 */
	int insertMrStandardGroupBuilding(MrStandardGroupBuilding mrStandardGroupBuilding);
	
	/**
	 * 批量新增(抄表收费标准(小区))信息
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
	 * 根据Id 批量删除(抄表收费标准(小区))信息_逻辑删除
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
