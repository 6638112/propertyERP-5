package com.cnfantasia.server.domainbase.carKeyRoomTemp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carKeyRoomTemp.dao.ICarKeyRoomTempBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carKeyRoomTemp.entity.CarKeyRoomTemp;

/**
 * 描述:(门牌车牌信息临时表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarKeyRoomTempBaseService implements ICarKeyRoomTempBaseService{
	
	private ICarKeyRoomTempBaseDao carKeyRoomTempBaseDao;
	public void setCarKeyRoomTempBaseDao(ICarKeyRoomTempBaseDao carKeyRoomTempBaseDao) {
		this.carKeyRoomTempBaseDao = carKeyRoomTempBaseDao;
	}
	/**
	 * 根据条件查询(门牌车牌信息临时表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> getCarKeyRoomTempByCondition(Map<String,Object> paramMap){
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(门牌车牌信息临时表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> getCarKeyRoomTempByConditionDim(Map<String,Object> paramMap){
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(门牌车牌信息临时表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> getCarKeyRoomTempByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(门牌车牌信息临时表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> getCarKeyRoomTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(门牌车牌信息临时表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarKeyRoomTemp getCarKeyRoomTempBySeqId(java.math.BigInteger id){
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(门牌车牌信息临时表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarKeyRoomTempCount(Map<String,Object> paramMap){
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(门牌车牌信息临时表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarKeyRoomTempCountDim(Map<String,Object> paramMap){
		return carKeyRoomTempBaseDao.selectCarKeyRoomTempCount(paramMap,true);
	}
	/**
	 * 往(门牌车牌信息临时表)新增一条记录
	 * @param carKeyRoomTemp
	 * @return
	 */
	@Override
	public int insertCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp){
		return carKeyRoomTempBaseDao.insertCarKeyRoomTemp(carKeyRoomTemp);
	}
	/**
	 * 批量新增(门牌车牌信息临时表)
	 * @param carKeyRoomTempList
	 * @return
	 */
	@Override
	public int insertCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList){
		return carKeyRoomTempBaseDao.insertCarKeyRoomTempBatch(carKeyRoomTempList);
	}
	/**
	 * 更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTemp
	 * @return
	 */
	@Override
	public int updateCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp){
		return carKeyRoomTempBaseDao.updateCarKeyRoomTemp(carKeyRoomTemp);
	}
	/**
	 * 批量更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTempList
	 * @return
	 */
	@Override
	public int updateCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList){
		return carKeyRoomTempBaseDao.updateCarKeyRoomTempBatch(carKeyRoomTempList);
	}
	/**
	 * 根据序列号删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarKeyRoomTempLogic(java.math.BigInteger id){
		return carKeyRoomTempBaseDao.deleteCarKeyRoomTempLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarKeyRoomTempLogicBatch(List<java.math.BigInteger> idList){
		return carKeyRoomTempBaseDao.deleteCarKeyRoomTempLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(门牌车牌信息临时表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarKeyRoomTemp(java.math.BigInteger id){
//		return carKeyRoomTempBaseDao.deleteCarKeyRoomTemp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌车牌信息临时表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarKeyRoomTempBatch(List<java.math.BigInteger> idList){
//		return carKeyRoomTempBaseDao.deleteCarKeyRoomTempBatch(idList);
//	}
	
}
