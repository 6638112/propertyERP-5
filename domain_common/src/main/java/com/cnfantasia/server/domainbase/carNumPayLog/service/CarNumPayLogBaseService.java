package com.cnfantasia.server.domainbase.carNumPayLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 描述:(月卡车缴费记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarNumPayLogBaseService implements ICarNumPayLogBaseService{
	
	private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	public void setCarNumPayLogBaseDao(ICarNumPayLogBaseDao carNumPayLogBaseDao) {
		this.carNumPayLogBaseDao = carNumPayLogBaseDao;
	}
	/**
	 * 根据条件查询(月卡车缴费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarNumPayLog> getCarNumPayLogByCondition(Map<String,Object> paramMap){
		return carNumPayLogBaseDao.selectCarNumPayLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(月卡车缴费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarNumPayLog> getCarNumPayLogByConditionDim(Map<String,Object> paramMap){
		return carNumPayLogBaseDao.selectCarNumPayLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(月卡车缴费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarNumPayLog> getCarNumPayLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carNumPayLogBaseDao.selectCarNumPayLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(月卡车缴费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarNumPayLog> getCarNumPayLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carNumPayLogBaseDao.selectCarNumPayLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(月卡车缴费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarNumPayLog getCarNumPayLogBySeqId(java.math.BigInteger id){
		return carNumPayLogBaseDao.selectCarNumPayLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarNumPayLogCount(Map<String,Object> paramMap){
		return carNumPayLogBaseDao.selectCarNumPayLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarNumPayLogCountDim(Map<String,Object> paramMap){
		return carNumPayLogBaseDao.selectCarNumPayLogCount(paramMap,true);
	}
	/**
	 * 往(月卡车缴费记录)新增一条记录
	 * @param carNumPayLog
	 * @return
	 */
	@Override
	public int insertCarNumPayLog(CarNumPayLog carNumPayLog){
		return carNumPayLogBaseDao.insertCarNumPayLog(carNumPayLog);
	}
	/**
	 * 批量新增(月卡车缴费记录)
	 * @param carNumPayLogList
	 * @return
	 */
	@Override
	public int insertCarNumPayLogBatch(List<CarNumPayLog> carNumPayLogList){
		return carNumPayLogBaseDao.insertCarNumPayLogBatch(carNumPayLogList);
	}
	/**
	 * 更新(月卡车缴费记录)信息
	 * @param carNumPayLog
	 * @return
	 */
	@Override
	public int updateCarNumPayLog(CarNumPayLog carNumPayLog){
		return carNumPayLogBaseDao.updateCarNumPayLog(carNumPayLog);
	}
	/**
	 * 批量更新(月卡车缴费记录)信息
	 * @param carNumPayLogList
	 * @return
	 */
	@Override
	public int updateCarNumPayLogBatch(List<? extends CarNumPayLog> carNumPayLogList){
		return carNumPayLogBaseDao.updateCarNumPayLogBatch(carNumPayLogList);
	}
	/**
	 * 根据序列号删除(月卡车缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarNumPayLogLogic(java.math.BigInteger id){
		return carNumPayLogBaseDao.deleteCarNumPayLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(月卡车缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarNumPayLogLogicBatch(List<java.math.BigInteger> idList){
		return carNumPayLogBaseDao.deleteCarNumPayLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(月卡车缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumPayLog(java.math.BigInteger id){
//		return carNumPayLogBaseDao.deleteCarNumPayLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(月卡车缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumPayLogBatch(List<java.math.BigInteger> idList){
//		return carNumPayLogBaseDao.deleteCarNumPayLogBatch(idList);
//	}
	
}
