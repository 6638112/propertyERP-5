package com.cnfantasia.server.domainbase.carGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carGroupBuilding.dao.ICarGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;

/**
 * 描述:(小区车禁配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarGroupBuildingBaseService implements ICarGroupBuildingBaseService{
	
	private ICarGroupBuildingBaseDao carGroupBuildingBaseDao;
	public void setCarGroupBuildingBaseDao(ICarGroupBuildingBaseDao carGroupBuildingBaseDao) {
		this.carGroupBuildingBaseDao = carGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(小区车禁配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> getCarGroupBuildingByCondition(Map<String,Object> paramMap){
		return carGroupBuildingBaseDao.selectCarGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小区车禁配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> getCarGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return carGroupBuildingBaseDao.selectCarGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小区车禁配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> getCarGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carGroupBuildingBaseDao.selectCarGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小区车禁配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> getCarGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carGroupBuildingBaseDao.selectCarGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小区车禁配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarGroupBuilding getCarGroupBuildingBySeqId(java.math.BigInteger id){
		return carGroupBuildingBaseDao.selectCarGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarGroupBuildingCount(Map<String,Object> paramMap){
		return carGroupBuildingBaseDao.selectCarGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarGroupBuildingCountDim(Map<String,Object> paramMap){
		return carGroupBuildingBaseDao.selectCarGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(小区车禁配置表)新增一条记录
	 * @param carGroupBuilding
	 * @return
	 */
	@Override
	public int insertCarGroupBuilding(CarGroupBuilding carGroupBuilding){
		return carGroupBuildingBaseDao.insertCarGroupBuilding(carGroupBuilding);
	}
	/**
	 * 批量新增(小区车禁配置表)
	 * @param carGroupBuildingList
	 * @return
	 */
	@Override
	public int insertCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList){
		return carGroupBuildingBaseDao.insertCarGroupBuildingBatch(carGroupBuildingList);
	}
	/**
	 * 更新(小区车禁配置表)信息
	 * @param carGroupBuilding
	 * @return
	 */
	@Override
	public int updateCarGroupBuilding(CarGroupBuilding carGroupBuilding){
		return carGroupBuildingBaseDao.updateCarGroupBuilding(carGroupBuilding);
	}
	/**
	 * 批量更新(小区车禁配置表)信息
	 * @param carGroupBuildingList
	 * @return
	 */
	@Override
	public int updateCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList){
		return carGroupBuildingBaseDao.updateCarGroupBuildingBatch(carGroupBuildingList);
	}
	/**
	 * 根据序列号删除(小区车禁配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarGroupBuildingLogic(java.math.BigInteger id){
		return carGroupBuildingBaseDao.deleteCarGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小区车禁配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return carGroupBuildingBaseDao.deleteCarGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小区车禁配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarGroupBuilding(java.math.BigInteger id){
//		return carGroupBuildingBaseDao.deleteCarGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区车禁配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return carGroupBuildingBaseDao.deleteCarGroupBuildingBatch(idList);
//	}
	
}
