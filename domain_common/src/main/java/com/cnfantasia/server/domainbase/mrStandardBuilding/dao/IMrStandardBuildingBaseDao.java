package com.cnfantasia.server.domainbase.mrStandardBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;

/**
 * 描述:(抄表收费标准(楼栋)) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrStandardBuildingBaseDao {
	/**
	 * 根据条件查询(抄表收费标准(楼栋))信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<MrStandardBuilding> selectMrStandardBuildingByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(抄表收费标准(楼栋))信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<MrStandardBuilding> selectMrStandardBuildingByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(抄表收费标准(楼栋))信息
	 * @param id
	 * @return
	 */
	MrStandardBuilding selectMrStandardBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表收费标准(楼栋))记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectMrStandardBuildingCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(抄表收费标准(楼栋))新增一条记录
	 * @param mrStandardBuilding
	 * @return
	 */
	int insertMrStandardBuilding(MrStandardBuilding mrStandardBuilding);
	
	/**
	 * 批量新增(抄表收费标准(楼栋))信息
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
	 * 根据Id 批量删除(抄表收费标准(楼栋))信息_逻辑删除
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
