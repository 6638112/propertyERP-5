package com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao.IEbuyDeliveryPushRecorderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

/**
 * 描述:(配送单推送记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryPushRecorderBaseService implements IEbuyDeliveryPushRecorderBaseService{
	
	private IEbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao;
	public void setEbuyDeliveryPushRecorderBaseDao(IEbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao) {
		this.ebuyDeliveryPushRecorderBaseDao = ebuyDeliveryPushRecorderBaseDao;
	}
	/**
	 * 根据条件查询(配送单推送记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(配送单推送记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(配送单推送记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(配送单推送记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> getEbuyDeliveryPushRecorderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(配送单推送记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryPushRecorder getEbuyDeliveryPushRecorderBySeqId(java.math.BigInteger id){
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryPushRecorderCount(Map<String,Object> paramMap){
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryPushRecorderCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderCount(paramMap,true);
	}
	/**
	 * 往(配送单推送记录)新增一条记录
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder){
		return ebuyDeliveryPushRecorderBaseDao.insertEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
	}
	/**
	 * 批量新增(配送单推送记录)
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList){
		return ebuyDeliveryPushRecorderBaseDao.insertEbuyDeliveryPushRecorderBatch(ebuyDeliveryPushRecorderList);
	}
	/**
	 * 更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder){
		return ebuyDeliveryPushRecorderBaseDao.updateEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
	}
	/**
	 * 批量更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList){
		return ebuyDeliveryPushRecorderBaseDao.updateEbuyDeliveryPushRecorderBatch(ebuyDeliveryPushRecorderList);
	}
	/**
	 * 根据序列号删除(配送单推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryPushRecorderLogic(java.math.BigInteger id){
		return ebuyDeliveryPushRecorderBaseDao.deleteEbuyDeliveryPushRecorderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(配送单推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryPushRecorderLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryPushRecorderBaseDao.deleteEbuyDeliveryPushRecorderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(配送单推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryPushRecorder(java.math.BigInteger id){
//		return ebuyDeliveryPushRecorderBaseDao.deleteEbuyDeliveryPushRecorder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(配送单推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryPushRecorderBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryPushRecorderBaseDao.deleteEbuyDeliveryPushRecorderBatch(idList);
//	}
	
}
