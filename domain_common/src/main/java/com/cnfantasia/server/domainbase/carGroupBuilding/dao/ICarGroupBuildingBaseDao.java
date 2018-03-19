package com.cnfantasia.server.domainbase.carGroupBuilding.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;

/**
 * 描述:(小区车禁配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarGroupBuildingBaseDao {
	/**
	 * 根据条件查询(小区车禁配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarGroupBuilding> selectCarGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小区车禁配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarGroupBuilding> selectCarGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小区车禁配置表)信息
	 * @param id
	 * @return
	 */
	public CarGroupBuilding selectCarGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCarGroupBuildingCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小区车禁配置表)新增一条记录
	 * @param carGroupBuilding
	 * @return
	 */
	public int insertCarGroupBuilding(CarGroupBuilding carGroupBuilding);
	
	/**
	 * 批量新增(小区车禁配置表)信息
	 * @param carGroupBuildingList
	 * @return
	 */
	public int insertCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList);
	
	/**
	 * 更新(小区车禁配置表)信息
	 * @param carGroupBuilding
	 * @return
	 */
	public int updateCarGroupBuilding(CarGroupBuilding carGroupBuilding);
	
	/**
	 * 批量更新(小区车禁配置表)信息
	 * @param carGroupBuildingList
	 * @return
	 */
	public int updateCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList);
	
	/**
	 * 根据序列号删除(小区车禁配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarGroupBuildingLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(小区车禁配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarGroupBuildingLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(小区车禁配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarGroupBuilding(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小区车禁配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarGroupBuildingBatch(List<java.math.BigInteger> idList);
	
	
}
