package com.cnfantasia.server.domainbase.carYhsMsg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 描述:(临停车缴费消息发送表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarYhsMsgBaseService {
	
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarYhsMsg> getCarYhsMsgByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarYhsMsg> getCarYhsMsgByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarYhsMsg> getCarYhsMsgByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarYhsMsg> getCarYhsMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(临停车缴费消息发送表)信息
	 * @param id
	 * @return
	 */
	public CarYhsMsg getCarYhsMsgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarYhsMsgCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarYhsMsgCountDim(Map<String,Object> paramMap);
	/**
	 * 往(临停车缴费消息发送表)新增一条记录
	 * @param carYhsMsg
	 * @return
	 */
	public int insertCarYhsMsg(CarYhsMsg carYhsMsg);
	/**
	 * 批量新增(临停车缴费消息发送表)
	 * @param carYhsMsgList
	 * @return
	 */
	public int insertCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList);
	/**
	 * 更新(临停车缴费消息发送表)信息
	 * @param carYhsMsg
	 * @return
	 */
	public int updateCarYhsMsg(CarYhsMsg carYhsMsg);
	/**
	 * 批量更新(临停车缴费消息发送表)信息
	 * @param carYhsMsgList
	 * @return
	 */
	public int updateCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList);
	/**
	 * 根据序列号删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarYhsMsgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarYhsMsgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(临停车缴费消息发送表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarYhsMsg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(临停车缴费消息发送表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarYhsMsgBatch(List<java.math.BigInteger> idList);
	
}
