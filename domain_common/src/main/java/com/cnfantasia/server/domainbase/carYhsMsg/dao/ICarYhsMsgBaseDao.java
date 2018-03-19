package com.cnfantasia.server.domainbase.carYhsMsg.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 描述:(临停车缴费消息发送表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarYhsMsgBaseDao {
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarYhsMsg> selectCarYhsMsgByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarYhsMsg> selectCarYhsMsgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(临停车缴费消息发送表)信息
	 * @param id
	 * @return
	 */
	public CarYhsMsg selectCarYhsMsgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCarYhsMsgCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(临停车缴费消息发送表)新增一条记录
	 * @param carYhsMsg
	 * @return
	 */
	public int insertCarYhsMsg(CarYhsMsg carYhsMsg);
	
	/**
	 * 批量新增(临停车缴费消息发送表)信息
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
	 * 根据Id 批量删除(临停车缴费消息发送表)信息_逻辑删除
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
