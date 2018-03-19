package com.cnfantasia.server.domainbase.carHuaanMsg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carHuaanMsg.dao.ICarHuaanMsgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;

/**
 * 描述:(华安临停车缴费通知记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarHuaanMsgBaseService implements ICarHuaanMsgBaseService{
	
	private ICarHuaanMsgBaseDao carHuaanMsgBaseDao;
	public void setCarHuaanMsgBaseDao(ICarHuaanMsgBaseDao carHuaanMsgBaseDao) {
		this.carHuaanMsgBaseDao = carHuaanMsgBaseDao;
	}
	/**
	 * 根据条件查询(华安临停车缴费通知记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> getCarHuaanMsgByCondition(Map<String,Object> paramMap){
		return carHuaanMsgBaseDao.selectCarHuaanMsgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(华安临停车缴费通知记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> getCarHuaanMsgByConditionDim(Map<String,Object> paramMap){
		return carHuaanMsgBaseDao.selectCarHuaanMsgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(华安临停车缴费通知记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> getCarHuaanMsgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carHuaanMsgBaseDao.selectCarHuaanMsgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(华安临停车缴费通知记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> getCarHuaanMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carHuaanMsgBaseDao.selectCarHuaanMsgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(华安临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarHuaanMsg getCarHuaanMsgBySeqId(java.math.BigInteger id){
		return carHuaanMsgBaseDao.selectCarHuaanMsgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(华安临停车缴费通知记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarHuaanMsgCount(Map<String,Object> paramMap){
		return carHuaanMsgBaseDao.selectCarHuaanMsgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(华安临停车缴费通知记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarHuaanMsgCountDim(Map<String,Object> paramMap){
		return carHuaanMsgBaseDao.selectCarHuaanMsgCount(paramMap,true);
	}
	/**
	 * 往(华安临停车缴费通知记录)新增一条记录
	 * @param carHuaanMsg
	 * @return
	 */
	@Override
	public int insertCarHuaanMsg(CarHuaanMsg carHuaanMsg){
		return carHuaanMsgBaseDao.insertCarHuaanMsg(carHuaanMsg);
	}
	/**
	 * 批量新增(华安临停车缴费通知记录)
	 * @param carHuaanMsgList
	 * @return
	 */
	@Override
	public int insertCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList){
		return carHuaanMsgBaseDao.insertCarHuaanMsgBatch(carHuaanMsgList);
	}
	/**
	 * 更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsg
	 * @return
	 */
	@Override
	public int updateCarHuaanMsg(CarHuaanMsg carHuaanMsg){
		return carHuaanMsgBaseDao.updateCarHuaanMsg(carHuaanMsg);
	}
	/**
	 * 批量更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsgList
	 * @return
	 */
	@Override
	public int updateCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList){
		return carHuaanMsgBaseDao.updateCarHuaanMsgBatch(carHuaanMsgList);
	}
	/**
	 * 根据序列号删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarHuaanMsgLogic(java.math.BigInteger id){
		return carHuaanMsgBaseDao.deleteCarHuaanMsgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarHuaanMsgLogicBatch(List<java.math.BigInteger> idList){
		return carHuaanMsgBaseDao.deleteCarHuaanMsgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(华安临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarHuaanMsg(java.math.BigInteger id){
//		return carHuaanMsgBaseDao.deleteCarHuaanMsg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华安临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarHuaanMsgBatch(List<java.math.BigInteger> idList){
//		return carHuaanMsgBaseDao.deleteCarHuaanMsgBatch(idList);
//	}
	
}
