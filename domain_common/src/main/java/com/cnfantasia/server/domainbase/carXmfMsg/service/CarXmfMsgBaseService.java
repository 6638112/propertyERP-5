package com.cnfantasia.server.domainbase.carXmfMsg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carXmfMsg.dao.ICarXmfMsgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;

/**
 * 描述:(小蜜蜂临停车缴费通知记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarXmfMsgBaseService implements ICarXmfMsgBaseService{
	
	private ICarXmfMsgBaseDao carXmfMsgBaseDao;
	public void setCarXmfMsgBaseDao(ICarXmfMsgBaseDao carXmfMsgBaseDao) {
		this.carXmfMsgBaseDao = carXmfMsgBaseDao;
	}
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarXmfMsg> getCarXmfMsgByCondition(Map<String,Object> paramMap){
		return carXmfMsgBaseDao.selectCarXmfMsgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarXmfMsg> getCarXmfMsgByConditionDim(Map<String,Object> paramMap){
		return carXmfMsgBaseDao.selectCarXmfMsgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarXmfMsg> getCarXmfMsgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carXmfMsgBaseDao.selectCarXmfMsgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarXmfMsg> getCarXmfMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carXmfMsgBaseDao.selectCarXmfMsgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(小蜜蜂临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarXmfMsg getCarXmfMsgBySeqId(java.math.BigInteger id){
		return carXmfMsgBaseDao.selectCarXmfMsgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarXmfMsgCount(Map<String,Object> paramMap){
		return carXmfMsgBaseDao.selectCarXmfMsgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarXmfMsgCountDim(Map<String,Object> paramMap){
		return carXmfMsgBaseDao.selectCarXmfMsgCount(paramMap,true);
	}
	/**
	 * 往(小蜜蜂临停车缴费通知记录)新增一条记录
	 * @param carXmfMsg
	 * @return
	 */
	@Override
	public int insertCarXmfMsg(CarXmfMsg carXmfMsg){
		return carXmfMsgBaseDao.insertCarXmfMsg(carXmfMsg);
	}
	/**
	 * 批量新增(小蜜蜂临停车缴费通知记录)
	 * @param carXmfMsgList
	 * @return
	 */
	@Override
	public int insertCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList){
		return carXmfMsgBaseDao.insertCarXmfMsgBatch(carXmfMsgList);
	}
	/**
	 * 更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsg
	 * @return
	 */
	@Override
	public int updateCarXmfMsg(CarXmfMsg carXmfMsg){
		return carXmfMsgBaseDao.updateCarXmfMsg(carXmfMsg);
	}
	/**
	 * 批量更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsgList
	 * @return
	 */
	@Override
	public int updateCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList){
		return carXmfMsgBaseDao.updateCarXmfMsgBatch(carXmfMsgList);
	}
	/**
	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarXmfMsgLogic(java.math.BigInteger id){
		return carXmfMsgBaseDao.deleteCarXmfMsgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarXmfMsgLogicBatch(List<java.math.BigInteger> idList){
		return carXmfMsgBaseDao.deleteCarXmfMsgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarXmfMsg(java.math.BigInteger id){
//		return carXmfMsgBaseDao.deleteCarXmfMsg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarXmfMsgBatch(List<java.math.BigInteger> idList){
//		return carXmfMsgBaseDao.deleteCarXmfMsgBatch(idList);
//	}
	
}
