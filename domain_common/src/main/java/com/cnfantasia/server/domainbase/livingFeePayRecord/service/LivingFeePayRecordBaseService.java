package com.cnfantasia.server.domainbase.livingFeePayRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.livingFeePayRecord.dao.ILivingFeePayRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeePayRecord.entity.LivingFeePayRecord;

/**
 * 描述:(生活缴费记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LivingFeePayRecordBaseService implements ILivingFeePayRecordBaseService{
	
	private ILivingFeePayRecordBaseDao livingFeePayRecordBaseDao;
	public void setLivingFeePayRecordBaseDao(ILivingFeePayRecordBaseDao livingFeePayRecordBaseDao) {
		this.livingFeePayRecordBaseDao = livingFeePayRecordBaseDao;
	}
	/**
	 * 根据条件查询(生活缴费记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeePayRecord> getLivingFeePayRecordByCondition(Map<String,Object> paramMap){
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(生活缴费记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeePayRecord> getLivingFeePayRecordByConditionDim(Map<String,Object> paramMap){
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(生活缴费记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeePayRecord> getLivingFeePayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(生活缴费记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeePayRecord> getLivingFeePayRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(生活缴费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public LivingFeePayRecord getLivingFeePayRecordBySeqId(java.math.BigInteger id){
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(生活缴费记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeePayRecordCount(Map<String,Object> paramMap){
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(生活缴费记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeePayRecordCountDim(Map<String,Object> paramMap){
		return livingFeePayRecordBaseDao.selectLivingFeePayRecordCount(paramMap,true);
	}
	/**
	 * 往(生活缴费记录)新增一条记录
	 * @param livingFeePayRecord
	 * @return
	 */
	@Override
	public int insertLivingFeePayRecord(LivingFeePayRecord livingFeePayRecord){
		return livingFeePayRecordBaseDao.insertLivingFeePayRecord(livingFeePayRecord);
	}
	/**
	 * 批量新增(生活缴费记录)
	 * @param livingFeePayRecordList
	 * @return
	 */
	@Override
	public int insertLivingFeePayRecordBatch(List<LivingFeePayRecord> livingFeePayRecordList){
		return livingFeePayRecordBaseDao.insertLivingFeePayRecordBatch(livingFeePayRecordList);
	}
	/**
	 * 更新(生活缴费记录)信息
	 * @param livingFeePayRecord
	 * @return
	 */
	@Override
	public int updateLivingFeePayRecord(LivingFeePayRecord livingFeePayRecord){
		return livingFeePayRecordBaseDao.updateLivingFeePayRecord(livingFeePayRecord);
	}
	/**
	 * 批量更新(生活缴费记录)信息
	 * @param livingFeePayRecordList
	 * @return
	 */
	@Override
	public int updateLivingFeePayRecordBatch(List<LivingFeePayRecord> livingFeePayRecordList){
		return livingFeePayRecordBaseDao.updateLivingFeePayRecordBatch(livingFeePayRecordList);
	}
	/**
	 * 根据序列号删除(生活缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLivingFeePayRecordLogic(java.math.BigInteger id){
		return livingFeePayRecordBaseDao.deleteLivingFeePayRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(生活缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLivingFeePayRecordLogicBatch(List<java.math.BigInteger> idList){
		return livingFeePayRecordBaseDao.deleteLivingFeePayRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(生活缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeePayRecord(java.math.BigInteger id){
//		return livingFeePayRecordBaseDao.deleteLivingFeePayRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(生活缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeePayRecordBatch(List<java.math.BigInteger> idList){
//		return livingFeePayRecordBaseDao.deleteLivingFeePayRecordBatch(idList);
//	}
	
}
