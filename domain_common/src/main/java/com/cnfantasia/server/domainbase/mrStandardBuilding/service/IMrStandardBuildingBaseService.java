package com.cnfantasia.server.domainbase.mrStandardBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;

/**
 * 描述:(抄表收费标准(楼栋)) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrStandardBuildingBaseService {
	
	/**
	 * 根据条件查询(抄表收费标准(楼栋))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardBuilding> getMrStandardBuildingByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(抄表收费标准(楼栋))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<MrStandardBuilding> getMrStandardBuildingByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(抄表收费标准(楼栋))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardBuilding> getMrStandardBuildingByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(抄表收费标准(楼栋))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<MrStandardBuilding> getMrStandardBuildingByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(抄表收费标准(楼栋))信息
	 * @param id
	 * @return
	 */
	MrStandardBuilding getMrStandardBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(楼栋))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardBuildingCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(楼栋))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getMrStandardBuildingCountDim(Map<String, Object> paramMap);
	/**
	 * 往(抄表收费标准(楼栋))新增一条记录
	 * @param mrStandardBuilding
	 * @return
	 */
	int insertMrStandardBuilding(MrStandardBuilding mrStandardBuilding);
	/**
	 * 批量新增(抄表收费标准(楼栋))
	 * @param mrStandardBuildingList
	 * @return
	 */
	int insertMrStandardBuildingBatch(List<MrStandardBuilding> mrStandardBuildingList);
	/**
	 * 更新(抄表收费标准(楼栋))信息
	 * @param mrStandardBuilding
	 * @return
	 */
	int updateMrStandardBuilding(MrStandardBuilding mrStandardBuilding);
	/**
	 * 批量更新(抄表收费标准(楼栋))信息
	 * @param mrStandardBuildingList
	 * @return
	 */
	int updateMrStandardBuildingBatch(List<MrStandardBuilding> mrStandardBuildingList);
	/**
	 * 根据序列号删除(抄表收费标准(楼栋))信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteMrStandardBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表收费标准(楼栋))信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteMrStandardBuildingLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表收费标准(楼栋))信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrStandardBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(楼栋))信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrStandardBuildingBatch(List<java.math.BigInteger> idList);
	
}
