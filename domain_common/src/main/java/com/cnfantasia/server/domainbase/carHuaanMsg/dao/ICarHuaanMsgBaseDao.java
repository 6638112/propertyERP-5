package com.cnfantasia.server.domainbase.carHuaanMsg.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;

/**
 * 描述:(华安临停车缴费通知记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarHuaanMsgBaseDao {
	/**
	 * 根据条件查询(华安临停车缴费通知记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<CarHuaanMsg> selectCarHuaanMsgByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(华安临停车缴费通知记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<CarHuaanMsg> selectCarHuaanMsgByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(华安临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	CarHuaanMsg selectCarHuaanMsgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(华安临停车缴费通知记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectCarHuaanMsgCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(华安临停车缴费通知记录)新增一条记录
	 * @param carHuaanMsg
	 * @return
	 */
	int insertCarHuaanMsg(CarHuaanMsg carHuaanMsg);
	
	/**
	 * 批量新增(华安临停车缴费通知记录)信息
	 * @param carHuaanMsgList
	 * @return
	 */
	int insertCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList);
	
	/**
	 * 更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsg
	 * @return
	 */
	int updateCarHuaanMsg(CarHuaanMsg carHuaanMsg);
	
	/**
	 * 批量更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsgList
	 * @return
	 */
	int updateCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList);
	
	/**
	 * 根据序列号删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteCarHuaanMsgLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteCarHuaanMsgLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(华安临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarHuaanMsg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(华安临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarHuaanMsgBatch(List<java.math.BigInteger> idList);
	
	
}
