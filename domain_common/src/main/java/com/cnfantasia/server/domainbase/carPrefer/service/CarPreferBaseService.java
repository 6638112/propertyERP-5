package com.cnfantasia.server.domainbase.carPrefer.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carPrefer.dao.ICarPreferBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carPrefer.entity.CarPrefer;

/**
 * 描述:(车禁随机立减) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarPreferBaseService implements ICarPreferBaseService{
	
	private ICarPreferBaseDao carPreferBaseDao;
	public void setCarPreferBaseDao(ICarPreferBaseDao carPreferBaseDao) {
		this.carPreferBaseDao = carPreferBaseDao;
	}
	/**
	 * 根据条件查询(车禁随机立减)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarPrefer> getCarPreferByCondition(Map<String,Object> paramMap){
		return carPreferBaseDao.selectCarPreferByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(车禁随机立减)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarPrefer> getCarPreferByConditionDim(Map<String,Object> paramMap){
		return carPreferBaseDao.selectCarPreferByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(车禁随机立减)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarPrefer> getCarPreferByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carPreferBaseDao.selectCarPreferByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(车禁随机立减)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarPrefer> getCarPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carPreferBaseDao.selectCarPreferByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(车禁随机立减)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarPrefer getCarPreferBySeqId(java.math.BigInteger id){
		return carPreferBaseDao.selectCarPreferBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(车禁随机立减)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarPreferCount(Map<String,Object> paramMap){
		return carPreferBaseDao.selectCarPreferCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(车禁随机立减)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarPreferCountDim(Map<String,Object> paramMap){
		return carPreferBaseDao.selectCarPreferCount(paramMap,true);
	}
	/**
	 * 往(车禁随机立减)新增一条记录
	 * @param carPrefer
	 * @return
	 */
	@Override
	public int insertCarPrefer(CarPrefer carPrefer){
		return carPreferBaseDao.insertCarPrefer(carPrefer);
	}
	/**
	 * 批量新增(车禁随机立减)
	 * @param carPreferList
	 * @return
	 */
	@Override
	public int insertCarPreferBatch(List<CarPrefer> carPreferList){
		return carPreferBaseDao.insertCarPreferBatch(carPreferList);
	}
	/**
	 * 更新(车禁随机立减)信息
	 * @param carPrefer
	 * @return
	 */
	@Override
	public int updateCarPrefer(CarPrefer carPrefer){
		return carPreferBaseDao.updateCarPrefer(carPrefer);
	}
	/**
	 * 批量更新(车禁随机立减)信息
	 * @param carPreferList
	 * @return
	 */
	@Override
	public int updateCarPreferBatch(List<CarPrefer> carPreferList){
		return carPreferBaseDao.updateCarPreferBatch(carPreferList);
	}
	/**
	 * 根据序列号删除(车禁随机立减)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCarPreferLogic(java.math.BigInteger id){
		return carPreferBaseDao.deleteCarPreferLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(车禁随机立减)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCarPreferLogicBatch(List<java.math.BigInteger> idList){
		return carPreferBaseDao.deleteCarPreferLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(车禁随机立减)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarPrefer(java.math.BigInteger id){
//		return carPreferBaseDao.deleteCarPrefer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车禁随机立减)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarPreferBatch(List<java.math.BigInteger> idList){
//		return carPreferBaseDao.deleteCarPreferBatch(idList);
//	}
	
}
