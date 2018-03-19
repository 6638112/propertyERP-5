package com.cnfantasia.server.domainbase.parkingRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.parkingRecord.dao.IParkingRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;

/**
 * 描述:(停车记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ParkingRecordBaseService implements IParkingRecordBaseService{
	
	private IParkingRecordBaseDao parkingRecordBaseDao;
	public void setParkingRecordBaseDao(IParkingRecordBaseDao parkingRecordBaseDao) {
		this.parkingRecordBaseDao = parkingRecordBaseDao;
	}
	/**
	 * 根据条件查询(停车记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ParkingRecord> getParkingRecordByCondition(Map<String,Object> paramMap){
		return parkingRecordBaseDao.selectParkingRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(停车记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ParkingRecord> getParkingRecordByConditionDim(Map<String,Object> paramMap){
		return parkingRecordBaseDao.selectParkingRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(停车记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ParkingRecord> getParkingRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return parkingRecordBaseDao.selectParkingRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(停车记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ParkingRecord> getParkingRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return parkingRecordBaseDao.selectParkingRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(停车记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ParkingRecord getParkingRecordBySeqId(java.math.BigInteger id){
		return parkingRecordBaseDao.selectParkingRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getParkingRecordCount(Map<String,Object> paramMap){
		return parkingRecordBaseDao.selectParkingRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(停车记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getParkingRecordCountDim(Map<String,Object> paramMap){
		return parkingRecordBaseDao.selectParkingRecordCount(paramMap,true);
	}
	/**
	 * 往(停车记录表)新增一条记录
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public int insertParkingRecord(ParkingRecord parkingRecord){
		return parkingRecordBaseDao.insertParkingRecord(parkingRecord);
	}
	/**
	 * 批量新增(停车记录表)
	 * @param parkingRecordList
	 * @return
	 */
	@Override
	public int insertParkingRecordBatch(List<ParkingRecord> parkingRecordList){
		return parkingRecordBaseDao.insertParkingRecordBatch(parkingRecordList);
	}
	/**
	 * 更新(停车记录表)信息
	 * @param parkingRecord
	 * @return
	 */
	@Override
	public int updateParkingRecord(ParkingRecord parkingRecord){
		return parkingRecordBaseDao.updateParkingRecord(parkingRecord);
	}
	/**
	 * 批量更新(停车记录表)信息
	 * @param parkingRecordList
	 * @return
	 */
	@Override
	public int updateParkingRecordBatch(List<ParkingRecord> parkingRecordList){
		return parkingRecordBaseDao.updateParkingRecordBatch(parkingRecordList);
	}
	/**
	 * 根据序列号删除(停车记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteParkingRecordLogic(java.math.BigInteger id){
		return parkingRecordBaseDao.deleteParkingRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(停车记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteParkingRecordLogicBatch(List<java.math.BigInteger> idList){
		return parkingRecordBaseDao.deleteParkingRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(停车记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteParkingRecord(java.math.BigInteger id){
//		return parkingRecordBaseDao.deleteParkingRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(停车记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteParkingRecordBatch(List<java.math.BigInteger> idList){
//		return parkingRecordBaseDao.deleteParkingRecordBatch(idList);
//	}
	
}
