package com.cnfantasia.server.domainbase.carHpfCardsn.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carHpfCardsn.dao.ICarHpfCardsnBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHpfCardsn.entity.CarHpfCardsn;

/**
 * 描述:(华鹏飞临停车cardsn) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarHpfCardsnBaseService implements ICarHpfCardsnBaseService{
	
	private ICarHpfCardsnBaseDao carHpfCardsnBaseDao;
	public void setCarHpfCardsnBaseDao(ICarHpfCardsnBaseDao carHpfCardsnBaseDao) {
		this.carHpfCardsnBaseDao = carHpfCardsnBaseDao;
	}
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> getCarHpfCardsnByCondition(Map<String,Object> paramMap){
		return carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> getCarHpfCardsnByConditionDim(Map<String,Object> paramMap){
		return carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> getCarHpfCardsnByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> getCarHpfCardsnByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(华鹏飞临停车cardsn)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarHpfCardsn getCarHpfCardsnBySeqId(java.math.BigInteger id){
		return carHpfCardsnBaseDao.selectCarHpfCardsnBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarHpfCardsnCount(Map<String,Object> paramMap){
		return carHpfCardsnBaseDao.selectCarHpfCardsnCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarHpfCardsnCountDim(Map<String,Object> paramMap){
		return carHpfCardsnBaseDao.selectCarHpfCardsnCount(paramMap,true);
	}
	/**
	 * 往(华鹏飞临停车cardsn)新增一条记录
	 * @param carHpfCardsn
	 * @return
	 */
	@Override
	public int insertCarHpfCardsn(CarHpfCardsn carHpfCardsn){
		return carHpfCardsnBaseDao.insertCarHpfCardsn(carHpfCardsn);
	}
	/**
	 * 批量新增(华鹏飞临停车cardsn)
	 * @param carHpfCardsnList
	 * @return
	 */
	@Override
	public int insertCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList){
		return carHpfCardsnBaseDao.insertCarHpfCardsnBatch(carHpfCardsnList);
	}
	/**
	 * 更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsn
	 * @return
	 */
	@Override
	public int updateCarHpfCardsn(CarHpfCardsn carHpfCardsn){
		return carHpfCardsnBaseDao.updateCarHpfCardsn(carHpfCardsn);
	}
	/**
	 * 批量更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsnList
	 * @return
	 */
	@Override
	public int updateCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList){
		return carHpfCardsnBaseDao.updateCarHpfCardsnBatch(carHpfCardsnList);
	}
	/**
	 * 根据序列号删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarHpfCardsnLogic(java.math.BigInteger id){
		return carHpfCardsnBaseDao.deleteCarHpfCardsnLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarHpfCardsnLogicBatch(List<java.math.BigInteger> idList){
		return carHpfCardsnBaseDao.deleteCarHpfCardsnLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(华鹏飞临停车cardsn)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarHpfCardsn(java.math.BigInteger id){
//		return carHpfCardsnBaseDao.deleteCarHpfCardsn(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华鹏飞临停车cardsn)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarHpfCardsnBatch(List<java.math.BigInteger> idList){
//		return carHpfCardsnBaseDao.deleteCarHpfCardsnBatch(idList);
//	}
	
}
