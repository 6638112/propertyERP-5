package com.cnfantasia.server.domainbase.carNumList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

/**
 * 描述:(车牌表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarNumListBaseService implements ICarNumListBaseService{
	
	private ICarNumListBaseDao carNumListBaseDao;
	public void setCarNumListBaseDao(ICarNumListBaseDao carNumListBaseDao) {
		this.carNumListBaseDao = carNumListBaseDao;
	}
	/**
	 * 根据条件查询(车牌表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarNumList> getCarNumListByCondition(Map<String,Object> paramMap){
		return carNumListBaseDao.selectCarNumListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(车牌表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarNumList> getCarNumListByConditionDim(Map<String,Object> paramMap){
		return carNumListBaseDao.selectCarNumListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(车牌表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarNumList> getCarNumListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carNumListBaseDao.selectCarNumListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(车牌表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarNumList> getCarNumListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carNumListBaseDao.selectCarNumListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarNumList getCarNumListBySeqId(java.math.BigInteger id){
		return carNumListBaseDao.selectCarNumListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarNumListCount(Map<String,Object> paramMap){
		return carNumListBaseDao.selectCarNumListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarNumListCountDim(Map<String,Object> paramMap){
		return carNumListBaseDao.selectCarNumListCount(paramMap,true);
	}
	/**
	 * 往(车牌表)新增一条记录
	 * @param carNumList
	 * @return
	 */
	@Override
	public int insertCarNumList(CarNumList carNumList){
		return carNumListBaseDao.insertCarNumList(carNumList);
	}
	/**
	 * 批量新增(车牌表)
	 * @param carNumListList
	 * @return
	 */
	@Override
	public int insertCarNumListBatch(List<CarNumList> carNumListList){
		return carNumListBaseDao.insertCarNumListBatch(carNumListList);
	}
	/**
	 * 更新(车牌表)信息
	 * @param carNumList
	 * @return
	 */
	@Override
	public int updateCarNumList(CarNumList carNumList){
		return carNumListBaseDao.updateCarNumList(carNumList);
	}
	/**
	 * 批量更新(车牌表)信息
	 * @param carNumListList
	 * @return
	 */
	@Override
	public int updateCarNumListBatch(List<CarNumList> carNumListList){
		return carNumListBaseDao.updateCarNumListBatch(carNumListList);
	}
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarNumListLogic(java.math.BigInteger id){
		return carNumListBaseDao.deleteCarNumListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarNumListLogicBatch(List<java.math.BigInteger> idList){
		return carNumListBaseDao.deleteCarNumListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumList(java.math.BigInteger id){
//		return carNumListBaseDao.deleteCarNumList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumListBatch(List<java.math.BigInteger> idList){
//		return carNumListBaseDao.deleteCarNumListBatch(idList);
//	}
	
}
