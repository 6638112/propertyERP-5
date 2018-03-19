package com.cnfantasia.server.domainbase.carXmfMsg.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;

/**
 * 描述:(小蜜蜂临停车缴费通知记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarXmfMsgBaseDao {
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarXmfMsg> selectCarXmfMsgByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarXmfMsg> selectCarXmfMsgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(小蜜蜂临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	public CarXmfMsg selectCarXmfMsgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCarXmfMsgCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(小蜜蜂临停车缴费通知记录)新增一条记录
	 * @param carXmfMsg
	 * @return
	 */
	public int insertCarXmfMsg(CarXmfMsg carXmfMsg);
	
	/**
	 * 批量新增(小蜜蜂临停车缴费通知记录)信息
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
	 * 根据Id 批量删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
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
