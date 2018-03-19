package com.cnfantasia.server.domainbase.carGroupBuilding.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;

/**
 * 描述:(小区车禁配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarGroupBuildingBaseService {
	
	/**
	 * 根据条件查询(小区车禁配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarGroupBuilding> getCarGroupBuildingByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小区车禁配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarGroupBuilding> getCarGroupBuildingByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小区车禁配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarGroupBuilding> getCarGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小区车禁配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarGroupBuilding> getCarGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小区车禁配置表)信息
	 * @param id
	 * @return
	 */
	public CarGroupBuilding getCarGroupBuildingBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarGroupBuildingCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarGroupBuildingCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小区车禁配置表)新增一条记录
	 * @param carGroupBuilding
	 * @return
	 */
	public int insertCarGroupBuilding(CarGroupBuilding carGroupBuilding);
	/**
	 * 批量新增(小区车禁配置表)
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
	 * 根据序列号批量删除(小区车禁配置表)信息_逻辑删除
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
