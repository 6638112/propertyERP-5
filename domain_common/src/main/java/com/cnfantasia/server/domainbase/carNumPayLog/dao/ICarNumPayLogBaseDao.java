package com.cnfantasia.server.domainbase.carNumPayLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 描述:(月卡车缴费记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarNumPayLogBaseDao {
	/**
	 * 根据条件查询(月卡车缴费记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarNumPayLog> selectCarNumPayLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(月卡车缴费记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarNumPayLog> selectCarNumPayLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(月卡车缴费记录)信息
	 * @param id
	 * @return
	 */
	public CarNumPayLog selectCarNumPayLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCarNumPayLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(月卡车缴费记录)新增一条记录
	 * @param carNumPayLog
	 * @return
	 */
	public int insertCarNumPayLog(CarNumPayLog carNumPayLog);
	
	/**
	 * 批量新增(月卡车缴费记录)信息
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
	 * 根据Id 批量删除(月卡车缴费记录)信息_逻辑删除
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
