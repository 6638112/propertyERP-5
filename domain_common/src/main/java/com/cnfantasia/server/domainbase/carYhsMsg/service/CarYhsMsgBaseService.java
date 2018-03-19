package com.cnfantasia.server.domainbase.carYhsMsg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.carYhsMsg.dao.ICarYhsMsgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 描述:(临停车缴费消息发送表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CarYhsMsgBaseService implements ICarYhsMsgBaseService{
	
	private ICarYhsMsgBaseDao carYhsMsgBaseDao;
	public void setCarYhsMsgBaseDao(ICarYhsMsgBaseDao carYhsMsgBaseDao) {
		this.carYhsMsgBaseDao = carYhsMsgBaseDao;
	}
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarYhsMsg> getCarYhsMsgByCondition(Map<String,Object> paramMap){
		return carYhsMsgBaseDao.selectCarYhsMsgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CarYhsMsg> getCarYhsMsgByConditionDim(Map<String,Object> paramMap){
		return carYhsMsgBaseDao.selectCarYhsMsgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarYhsMsg> getCarYhsMsgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return carYhsMsgBaseDao.selectCarYhsMsgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CarYhsMsg> getCarYhsMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return carYhsMsgBaseDao.selectCarYhsMsgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(临停车缴费消息发送表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarYhsMsg getCarYhsMsgBySeqId(java.math.BigInteger id){
		return carYhsMsgBaseDao.selectCarYhsMsgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarYhsMsgCount(Map<String,Object> paramMap){
		return carYhsMsgBaseDao.selectCarYhsMsgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCarYhsMsgCountDim(Map<String,Object> paramMap){
		return carYhsMsgBaseDao.selectCarYhsMsgCount(paramMap,true);
	}
	/**
	 * 往(临停车缴费消息发送表)新增一条记录
	 * @param carYhsMsg
	 * @return
	 */
	@Override
	public int insertCarYhsMsg(CarYhsMsg carYhsMsg){
		return carYhsMsgBaseDao.insertCarYhsMsg(carYhsMsg);
	}
	/**
	 * 批量新增(临停车缴费消息发送表)
	 * @param carYhsMsgList
	 * @return
	 */
	@Override
	public int insertCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList){
		return carYhsMsgBaseDao.insertCarYhsMsgBatch(carYhsMsgList);
	}
	/**
	 * 更新(临停车缴费消息发送表)信息
	 * @param carYhsMsg
	 * @return
	 */
	@Override
	public int updateCarYhsMsg(CarYhsMsg carYhsMsg){
		return carYhsMsgBaseDao.updateCarYhsMsg(carYhsMsg);
	}
	/**
	 * 批量更新(临停车缴费消息发送表)信息
	 * @param carYhsMsgList
	 * @return
	 */
	@Override
	public int updateCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList){
		return carYhsMsgBaseDao.updateCarYhsMsgBatch(carYhsMsgList);
	}
	/**
	 * 根据序列号删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarYhsMsgLogic(java.math.BigInteger id){
		return carYhsMsgBaseDao.deleteCarYhsMsgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarYhsMsgLogicBatch(List<java.math.BigInteger> idList){
		return carYhsMsgBaseDao.deleteCarYhsMsgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(临停车缴费消息发送表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarYhsMsg(java.math.BigInteger id){
//		return carYhsMsgBaseDao.deleteCarYhsMsg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临停车缴费消息发送表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarYhsMsgBatch(List<java.math.BigInteger> idList){
//		return carYhsMsgBaseDao.deleteCarYhsMsgBatch(idList);
//	}
	
}
