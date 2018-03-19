package com.cnfantasia.server.domainbase.mrPayBillRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrPayBillRecord.dao.IMrPayBillRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;

/**
 * 描述:(抄表费收费 账单读表数) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrPayBillRecordBaseService implements IMrPayBillRecordBaseService{
	
	private IMrPayBillRecordBaseDao mrPayBillRecordBaseDao;
	public void setMrPayBillRecordBaseDao(IMrPayBillRecordBaseDao mrPayBillRecordBaseDao) {
		this.mrPayBillRecordBaseDao = mrPayBillRecordBaseDao;
	}
	/**
	 * 根据条件查询(抄表费收费 账单读表数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> getMrPayBillRecordByCondition(Map<String,Object> paramMap){
		return mrPayBillRecordBaseDao.selectMrPayBillRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表费收费 账单读表数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> getMrPayBillRecordByConditionDim(Map<String,Object> paramMap){
		return mrPayBillRecordBaseDao.selectMrPayBillRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表费收费 账单读表数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> getMrPayBillRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrPayBillRecordBaseDao.selectMrPayBillRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表费收费 账单读表数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> getMrPayBillRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrPayBillRecordBaseDao.selectMrPayBillRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表费收费 账单读表数)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrPayBillRecord getMrPayBillRecordBySeqId(java.math.BigInteger id){
		return mrPayBillRecordBaseDao.selectMrPayBillRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费 账单读表数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrPayBillRecordCount(Map<String,Object> paramMap){
		return mrPayBillRecordBaseDao.selectMrPayBillRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费 账单读表数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrPayBillRecordCountDim(Map<String,Object> paramMap){
		return mrPayBillRecordBaseDao.selectMrPayBillRecordCount(paramMap,true);
	}
	/**
	 * 往(抄表费收费 账单读表数)新增一条记录
	 * @param mrPayBillRecord
	 * @return
	 */
	@Override
	public int insertMrPayBillRecord(MrPayBillRecord mrPayBillRecord){
		return mrPayBillRecordBaseDao.insertMrPayBillRecord(mrPayBillRecord);
	}
	/**
	 * 批量新增(抄表费收费 账单读表数)
	 * @param mrPayBillRecordList
	 * @return
	 */
	@Override
	public int insertMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList){
		return mrPayBillRecordBaseDao.insertMrPayBillRecordBatch(mrPayBillRecordList);
	}
	/**
	 * 更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecord
	 * @return
	 */
	@Override
	public int updateMrPayBillRecord(MrPayBillRecord mrPayBillRecord){
		return mrPayBillRecordBaseDao.updateMrPayBillRecord(mrPayBillRecord);
	}
	/**
	 * 批量更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecordList
	 * @return
	 */
	@Override
	public int updateMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList){
		return mrPayBillRecordBaseDao.updateMrPayBillRecordBatch(mrPayBillRecordList);
	}
	/**
	 * 根据序列号删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrPayBillRecordLogic(java.math.BigInteger id){
		return mrPayBillRecordBaseDao.deleteMrPayBillRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrPayBillRecordLogicBatch(List<java.math.BigInteger> idList){
		return mrPayBillRecordBaseDao.deleteMrPayBillRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表费收费 账单读表数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrPayBillRecord(java.math.BigInteger id){
//		return mrPayBillRecordBaseDao.deleteMrPayBillRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费 账单读表数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrPayBillRecordBatch(List<java.math.BigInteger> idList){
//		return mrPayBillRecordBaseDao.deleteMrPayBillRecordBatch(idList);
//	}
	
}
