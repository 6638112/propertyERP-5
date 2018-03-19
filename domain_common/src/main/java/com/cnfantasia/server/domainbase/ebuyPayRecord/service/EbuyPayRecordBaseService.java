package com.cnfantasia.server.domainbase.ebuyPayRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyPayRecord.dao.IEbuyPayRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

/**
 * 描述:(支付记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyPayRecordBaseService implements IEbuyPayRecordBaseService{
	
	private IEbuyPayRecordBaseDao ebuyPayRecordBaseDao;
	public void setEbuyPayRecordBaseDao(IEbuyPayRecordBaseDao ebuyPayRecordBaseDao) {
		this.ebuyPayRecordBaseDao = ebuyPayRecordBaseDao;
	}
	/**
	 * 根据条件查询(支付记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> getEbuyPayRecordByCondition(Map<String,Object> paramMap){
		return ebuyPayRecordBaseDao.selectEbuyPayRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(支付记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> getEbuyPayRecordByConditionDim(Map<String,Object> paramMap){
		return ebuyPayRecordBaseDao.selectEbuyPayRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(支付记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> getEbuyPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPayRecordBaseDao.selectEbuyPayRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(支付记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> getEbuyPayRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPayRecordBaseDao.selectEbuyPayRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(支付记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPayRecord getEbuyPayRecordBySeqId(java.math.BigInteger id){
		return ebuyPayRecordBaseDao.selectEbuyPayRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPayRecordCount(Map<String,Object> paramMap){
		return ebuyPayRecordBaseDao.selectEbuyPayRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPayRecordCountDim(Map<String,Object> paramMap){
		return ebuyPayRecordBaseDao.selectEbuyPayRecordCount(paramMap,true);
	}
	/**
	 * 往(支付记录表)新增一条记录
	 * @param ebuyPayRecord
	 * @return
	 */
	@Override
	public int insertEbuyPayRecord(EbuyPayRecord ebuyPayRecord){
		return ebuyPayRecordBaseDao.insertEbuyPayRecord(ebuyPayRecord);
	}
	/**
	 * 批量新增(支付记录表)
	 * @param ebuyPayRecordList
	 * @return
	 */
	@Override
	public int insertEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList){
		return ebuyPayRecordBaseDao.insertEbuyPayRecordBatch(ebuyPayRecordList);
	}
	/**
	 * 更新(支付记录表)信息
	 * @param ebuyPayRecord
	 * @return
	 */
	@Override
	public int updateEbuyPayRecord(EbuyPayRecord ebuyPayRecord){
		return ebuyPayRecordBaseDao.updateEbuyPayRecord(ebuyPayRecord);
	}
	/**
	 * 批量更新(支付记录表)信息
	 * @param ebuyPayRecordList
	 * @return
	 */
	@Override
	public int updateEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList){
		return ebuyPayRecordBaseDao.updateEbuyPayRecordBatch(ebuyPayRecordList);
	}
	/**
	 * 根据序列号删除(支付记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPayRecordLogic(java.math.BigInteger id){
		return ebuyPayRecordBaseDao.deleteEbuyPayRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(支付记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPayRecordLogicBatch(List<java.math.BigInteger> idList){
		return ebuyPayRecordBaseDao.deleteEbuyPayRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(支付记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPayRecord(java.math.BigInteger id){
//		return ebuyPayRecordBaseDao.deleteEbuyPayRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(支付记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPayRecordBatch(List<java.math.BigInteger> idList){
//		return ebuyPayRecordBaseDao.deleteEbuyPayRecordBatch(idList);
//	}
	
}
