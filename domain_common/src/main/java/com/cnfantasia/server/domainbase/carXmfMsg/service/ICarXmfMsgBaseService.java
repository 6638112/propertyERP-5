package com.cnfantasia.server.domainbase.carXmfMsg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;

/**
 * 描述:(小蜜蜂临停车缴费通知记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarXmfMsgBaseService {
	
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarXmfMsg> getCarXmfMsgByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarXmfMsg> getCarXmfMsgByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarXmfMsg> getCarXmfMsgByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarXmfMsg> getCarXmfMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(小蜜蜂临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	public CarXmfMsg getCarXmfMsgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarXmfMsgCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarXmfMsgCountDim(Map<String,Object> paramMap);
	/**
	 * 往(小蜜蜂临停车缴费通知记录)新增一条记录
	 * @param carXmfMsg
	 * @return
	 */
	public int insertCarXmfMsg(CarXmfMsg carXmfMsg);
	/**
	 * 批量新增(小蜜蜂临停车缴费通知记录)
	 * @param carXmfMsgList
	 * @return
	 */
	public int insertCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList);
	/**
	 * 更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsg
	 * @return
	 */
	public int updateCarXmfMsg(CarXmfMsg carXmfMsg);
	/**
	 * 批量更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsgList
	 * @return
	 */
	public int updateCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList);
	/**
	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarXmfMsgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarXmfMsgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarXmfMsg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarXmfMsgBatch(List<java.math.BigInteger> idList);
	
}
