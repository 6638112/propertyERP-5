package com.cnfantasia.server.domainbase.ebuyPrepaySqLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyPrepaySqLog.dao.IEbuyPrepaySqLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity.EbuyPrepaySqLog;

/**
 * 描述:(双乾预支付记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyPrepaySqLogBaseService implements IEbuyPrepaySqLogBaseService{
	
	private IEbuyPrepaySqLogBaseDao ebuyPrepaySqLogBaseDao;
	public void setEbuyPrepaySqLogBaseDao(IEbuyPrepaySqLogBaseDao ebuyPrepaySqLogBaseDao) {
		this.ebuyPrepaySqLogBaseDao = ebuyPrepaySqLogBaseDao;
	}
	/**
	 * 根据条件查询(双乾预支付记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByCondition(Map<String,Object> paramMap){
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(双乾预支付记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByConditionDim(Map<String,Object> paramMap){
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(双乾预支付记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(双乾预支付记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepaySqLog> getEbuyPrepaySqLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(双乾预支付记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPrepaySqLog getEbuyPrepaySqLogBySeqId(java.math.BigInteger id){
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(双乾预支付记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepaySqLogCount(Map<String,Object> paramMap){
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(双乾预支付记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepaySqLogCountDim(Map<String,Object> paramMap){
		return ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogCount(paramMap,true);
	}
	/**
	 * 往(双乾预支付记录表)新增一条记录
	 * @param ebuyPrepaySqLog
	 * @return
	 */
	@Override
	public int insertEbuyPrepaySqLog(EbuyPrepaySqLog ebuyPrepaySqLog){
		return ebuyPrepaySqLogBaseDao.insertEbuyPrepaySqLog(ebuyPrepaySqLog);
	}
	/**
	 * 批量新增(双乾预支付记录表)
	 * @param ebuyPrepaySqLogList
	 * @return
	 */
	@Override
	public int insertEbuyPrepaySqLogBatch(List<EbuyPrepaySqLog> ebuyPrepaySqLogList){
		return ebuyPrepaySqLogBaseDao.insertEbuyPrepaySqLogBatch(ebuyPrepaySqLogList);
	}
	/**
	 * 更新(双乾预支付记录表)信息
	 * @param ebuyPrepaySqLog
	 * @return
	 */
	@Override
	public int updateEbuyPrepaySqLog(EbuyPrepaySqLog ebuyPrepaySqLog){
		return ebuyPrepaySqLogBaseDao.updateEbuyPrepaySqLog(ebuyPrepaySqLog);
	}
	/**
	 * 批量更新(双乾预支付记录表)信息
	 * @param ebuyPrepaySqLogList
	 * @return
	 */
	@Override
	public int updateEbuyPrepaySqLogBatch(List<EbuyPrepaySqLog> ebuyPrepaySqLogList){
		return ebuyPrepaySqLogBaseDao.updateEbuyPrepaySqLogBatch(ebuyPrepaySqLogList);
	}
	/**
	 * 根据序列号删除(双乾预支付记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepaySqLogLogic(java.math.BigInteger id){
		return ebuyPrepaySqLogBaseDao.deleteEbuyPrepaySqLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(双乾预支付记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepaySqLogLogicBatch(List<java.math.BigInteger> idList){
		return ebuyPrepaySqLogBaseDao.deleteEbuyPrepaySqLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(双乾预支付记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepaySqLog(java.math.BigInteger id){
//		return ebuyPrepaySqLogBaseDao.deleteEbuyPrepaySqLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(双乾预支付记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepaySqLogBatch(List<java.math.BigInteger> idList){
//		return ebuyPrepaySqLogBaseDao.deleteEbuyPrepaySqLogBatch(idList);
//	}
	
}
