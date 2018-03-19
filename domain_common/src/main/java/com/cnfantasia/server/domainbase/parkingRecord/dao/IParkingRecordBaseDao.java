package com.cnfantasia.server.domainbase.parkingRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;

/**
 * 描述:(停车记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IParkingRecordBaseDao {
	/**
	 * 根据条件查询(停车记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ParkingRecord> selectParkingRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(停车记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ParkingRecord> selectParkingRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(停车记录表)信息
	 * @param id
	 * @return
	 */
	public ParkingRecord selectParkingRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectParkingRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(停车记录表)新增一条记录
	 * @param parkingRecord
	 * @return
	 */
	public int insertParkingRecord(ParkingRecord parkingRecord);
	
	/**
	 * 批量新增(停车记录表)信息
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
	 * 根据Id 批量删除(停车记录表)信息_逻辑删除
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
