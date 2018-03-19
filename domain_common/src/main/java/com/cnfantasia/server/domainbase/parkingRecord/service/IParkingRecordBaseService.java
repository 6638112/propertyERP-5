package com.cnfantasia.server.domainbase.parkingRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;

/**
 * 描述:(停车记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IParkingRecordBaseService {
	
	/**
	 * 根据条件查询(停车记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ParkingRecord> getParkingRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(停车记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ParkingRecord> getParkingRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(停车记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ParkingRecord> getParkingRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(停车记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ParkingRecord> getParkingRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(停车记录表)信息
	 * @param id
	 * @return
	 */
	public ParkingRecord getParkingRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getParkingRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getParkingRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(停车记录表)新增一条记录
	 * @param parkingRecord
	 * @return
	 */
	public int insertParkingRecord(ParkingRecord parkingRecord);
	/**
	 * 批量新增(停车记录表)
	 * @param parkingRecordList
	 * @return
	 */
	public int insertParkingRecordBatch(List<ParkingRecord> parkingRecordList);
	/**
	 * 更新(停车记录表)信息
	 * @param parkingRecord
	 * @return
	 */
	public int updateParkingRecord(ParkingRecord parkingRecord);
	/**
	 * 批量更新(停车记录表)信息
	 * @param parkingRecordList
	 * @return
	 */
	public int updateParkingRecordBatch(List<ParkingRecord> parkingRecordList);
	/**
	 * 根据序列号删除(停车记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteParkingRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(停车记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteParkingRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(停车记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteParkingRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(停车记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteParkingRecordBatch(List<java.math.BigInteger> idList);
	
}
