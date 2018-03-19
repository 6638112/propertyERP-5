package com.cnfantasia.server.domainbase.dataChangeRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dataChangeRecord.dao.IDataChangeRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dataChangeRecord.entity.DataChangeRecord;

/**
 * 描述:(数据变更记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DataChangeRecordBaseService implements IDataChangeRecordBaseService{
	
	private IDataChangeRecordBaseDao dataChangeRecordBaseDao;
	public void setDataChangeRecordBaseDao(IDataChangeRecordBaseDao dataChangeRecordBaseDao) {
		this.dataChangeRecordBaseDao = dataChangeRecordBaseDao;
	}
	/**
	 * 根据条件查询(数据变更记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DataChangeRecord> getDataChangeRecordByCondition(Map<String,Object> paramMap){
		return dataChangeRecordBaseDao.selectDataChangeRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(数据变更记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DataChangeRecord> getDataChangeRecordByConditionDim(Map<String,Object> paramMap){
		return dataChangeRecordBaseDao.selectDataChangeRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(数据变更记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DataChangeRecord> getDataChangeRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dataChangeRecordBaseDao.selectDataChangeRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(数据变更记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DataChangeRecord> getDataChangeRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dataChangeRecordBaseDao.selectDataChangeRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(数据变更记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public DataChangeRecord getDataChangeRecordBySeqId(java.math.BigInteger id){
		return dataChangeRecordBaseDao.selectDataChangeRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(数据变更记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDataChangeRecordCount(Map<String,Object> paramMap){
		return dataChangeRecordBaseDao.selectDataChangeRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(数据变更记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDataChangeRecordCountDim(Map<String,Object> paramMap){
		return dataChangeRecordBaseDao.selectDataChangeRecordCount(paramMap,true);
	}
	/**
	 * 往(数据变更记录)新增一条记录
	 * @param dataChangeRecord
	 * @return
	 */
	@Override
	public int insertDataChangeRecord(DataChangeRecord dataChangeRecord){
		return dataChangeRecordBaseDao.insertDataChangeRecord(dataChangeRecord);
	}
	/**
	 * 批量新增(数据变更记录)
	 * @param dataChangeRecordList
	 * @return
	 */
	@Override
	public int insertDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList){
		return dataChangeRecordBaseDao.insertDataChangeRecordBatch(dataChangeRecordList);
	}
	/**
	 * 更新(数据变更记录)信息
	 * @param dataChangeRecord
	 * @return
	 */
	@Override
	public int updateDataChangeRecord(DataChangeRecord dataChangeRecord){
		return dataChangeRecordBaseDao.updateDataChangeRecord(dataChangeRecord);
	}
	/**
	 * 批量更新(数据变更记录)信息
	 * @param dataChangeRecordList
	 * @return
	 */
	@Override
	public int updateDataChangeRecordBatch(List<DataChangeRecord> dataChangeRecordList){
		return dataChangeRecordBaseDao.updateDataChangeRecordBatch(dataChangeRecordList);
	}
	/**
	 * 根据序列号删除(数据变更记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDataChangeRecordLogic(java.math.BigInteger id){
		return dataChangeRecordBaseDao.deleteDataChangeRecordLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(数据变更记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDataChangeRecordLogicBatch(List<java.math.BigInteger> idList){
		return dataChangeRecordBaseDao.deleteDataChangeRecordLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(数据变更记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDataChangeRecord(java.math.BigInteger id){
//		return dataChangeRecordBaseDao.deleteDataChangeRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(数据变更记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDataChangeRecordBatch(List<java.math.BigInteger> idList){
//		return dataChangeRecordBaseDao.deleteDataChangeRecordBatch(idList);
//	}
	
}
