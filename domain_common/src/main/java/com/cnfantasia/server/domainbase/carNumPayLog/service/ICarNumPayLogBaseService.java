package com.cnfantasia.server.domainbase.carNumPayLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 描述:(月卡车缴费记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarNumPayLogBaseService {
	
	/**
	 * 根据条件查询(月卡车缴费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarNumPayLog> getCarNumPayLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(月卡车缴费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarNumPayLog> getCarNumPayLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(月卡车缴费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarNumPayLog> getCarNumPayLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(月卡车缴费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarNumPayLog> getCarNumPayLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(月卡车缴费记录)信息
	 * @param id
	 * @return
	 */
	public CarNumPayLog getCarNumPayLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarNumPayLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarNumPayLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(月卡车缴费记录)新增一条记录
	 * @param carNumPayLog
	 * @return
	 */
	public int insertCarNumPayLog(CarNumPayLog carNumPayLog);
	/**
	 * 批量新增(月卡车缴费记录)
	 * @param carNumPayLogList
	 * @return
	 */
	public int insertCarNumPayLogBatch(List<CarNumPayLog> carNumPayLogList);
	/**
	 * 更新(月卡车缴费记录)信息
	 * @param carNumPayLog
	 * @return
	 */
	public int updateCarNumPayLog(CarNumPayLog carNumPayLog);
	/**
	 * 批量更新(月卡车缴费记录)信息
	 * @param carNumPayLogList
	 * @return
	 */
	public int updateCarNumPayLogBatch(List<? extends CarNumPayLog> carNumPayLogList);
	/**
	 * 根据序列号删除(月卡车缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarNumPayLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(月卡车缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarNumPayLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(月卡车缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarNumPayLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(月卡车缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarNumPayLogBatch(List<java.math.BigInteger> idList);
	
}
