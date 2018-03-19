package com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.dao.IEbuyPrepayWeixinLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity.EbuyPrepayWeixinLog;

/**
 * 描述:(微信预支付日志表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyPrepayWeixinLogBaseService implements IEbuyPrepayWeixinLogBaseService{
	
	private IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao;
	public void setEbuyPrepayWeixinLogBaseDao(IEbuyPrepayWeixinLogBaseDao ebuyPrepayWeixinLogBaseDao) {
		this.ebuyPrepayWeixinLogBaseDao = ebuyPrepayWeixinLogBaseDao;
	}
	/**
	 * 根据条件查询(微信预支付日志表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap){
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微信预支付日志表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByConditionDim(Map<String,Object> paramMap){
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微信预支付日志表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微信预支付日志表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepayWeixinLog> getEbuyPrepayWeixinLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微信预支付日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPrepayWeixinLog getEbuyPrepayWeixinLogBySeqId(java.math.BigInteger id){
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微信预支付日志表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepayWeixinLogCount(Map<String,Object> paramMap){
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微信预支付日志表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepayWeixinLogCountDim(Map<String,Object> paramMap){
		return ebuyPrepayWeixinLogBaseDao.selectEbuyPrepayWeixinLogCount(paramMap,true);
	}
	/**
	 * 往(微信预支付日志表)新增一条记录
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	@Override
	public int insertEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog){
		return ebuyPrepayWeixinLogBaseDao.insertEbuyPrepayWeixinLog(ebuyPrepayWeixinLog);
	}
	/**
	 * 批量新增(微信预支付日志表)
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	@Override
	public int insertEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList){
		return ebuyPrepayWeixinLogBaseDao.insertEbuyPrepayWeixinLogBatch(ebuyPrepayWeixinLogList);
	}
	/**
	 * 更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLog
	 * @return
	 */
	@Override
	public int updateEbuyPrepayWeixinLog(EbuyPrepayWeixinLog ebuyPrepayWeixinLog){
		return ebuyPrepayWeixinLogBaseDao.updateEbuyPrepayWeixinLog(ebuyPrepayWeixinLog);
	}
	/**
	 * 批量更新(微信预支付日志表)信息
	 * @param ebuyPrepayWeixinLogList
	 * @return
	 */
	@Override
	public int updateEbuyPrepayWeixinLogBatch(List<EbuyPrepayWeixinLog> ebuyPrepayWeixinLogList){
		return ebuyPrepayWeixinLogBaseDao.updateEbuyPrepayWeixinLogBatch(ebuyPrepayWeixinLogList);
	}
	/**
	 * 根据序列号删除(微信预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayWeixinLogLogic(java.math.BigInteger id){
		return ebuyPrepayWeixinLogBaseDao.deleteEbuyPrepayWeixinLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微信预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayWeixinLogLogicBatch(List<java.math.BigInteger> idList){
		return ebuyPrepayWeixinLogBaseDao.deleteEbuyPrepayWeixinLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微信预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayWeixinLog(java.math.BigInteger id){
//		return ebuyPrepayWeixinLogBaseDao.deleteEbuyPrepayWeixinLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayWeixinLogBatch(List<java.math.BigInteger> idList){
//		return ebuyPrepayWeixinLogBaseDao.deleteEbuyPrepayWeixinLogBatch(idList);
//	}
	
}
