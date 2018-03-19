package com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.dao.IEbuyPrepayAlipayLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity.EbuyPrepayAlipayLog;

/**
 * 描述:(淘宝预支付日志表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyPrepayAlipayLogBaseService implements IEbuyPrepayAlipayLogBaseService{
	
	private IEbuyPrepayAlipayLogBaseDao ebuyPrepayAlipayLogBaseDao;
	public void setEbuyPrepayAlipayLogBaseDao(IEbuyPrepayAlipayLogBaseDao ebuyPrepayAlipayLogBaseDao) {
		this.ebuyPrepayAlipayLogBaseDao = ebuyPrepayAlipayLogBaseDao;
	}
	/**
	 * 根据条件查询(淘宝预支付日志表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> getEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap){
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(淘宝预支付日志表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> getEbuyPrepayAlipayLogByConditionDim(Map<String,Object> paramMap){
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(淘宝预支付日志表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> getEbuyPrepayAlipayLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(淘宝预支付日志表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyPrepayAlipayLog> getEbuyPrepayAlipayLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(淘宝预支付日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPrepayAlipayLog getEbuyPrepayAlipayLogBySeqId(java.math.BigInteger id){
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(淘宝预支付日志表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepayAlipayLogCount(Map<String,Object> paramMap){
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(淘宝预支付日志表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyPrepayAlipayLogCountDim(Map<String,Object> paramMap){
		return ebuyPrepayAlipayLogBaseDao.selectEbuyPrepayAlipayLogCount(paramMap,true);
	}
	/**
	 * 往(淘宝预支付日志表)新增一条记录
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	@Override
	public int insertEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog){
		return ebuyPrepayAlipayLogBaseDao.insertEbuyPrepayAlipayLog(ebuyPrepayAlipayLog);
	}
	/**
	 * 批量新增(淘宝预支付日志表)
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	@Override
	public int insertEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList){
		return ebuyPrepayAlipayLogBaseDao.insertEbuyPrepayAlipayLogBatch(ebuyPrepayAlipayLogList);
	}
	/**
	 * 更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLog
	 * @return
	 */
	@Override
	public int updateEbuyPrepayAlipayLog(EbuyPrepayAlipayLog ebuyPrepayAlipayLog){
		return ebuyPrepayAlipayLogBaseDao.updateEbuyPrepayAlipayLog(ebuyPrepayAlipayLog);
	}
	/**
	 * 批量更新(淘宝预支付日志表)信息
	 * @param ebuyPrepayAlipayLogList
	 * @return
	 */
	@Override
	public int updateEbuyPrepayAlipayLogBatch(List<EbuyPrepayAlipayLog> ebuyPrepayAlipayLogList){
		return ebuyPrepayAlipayLogBaseDao.updateEbuyPrepayAlipayLogBatch(ebuyPrepayAlipayLogList);
	}
	/**
	 * 根据序列号删除(淘宝预支付日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayAlipayLogLogic(java.math.BigInteger id){
		return ebuyPrepayAlipayLogBaseDao.deleteEbuyPrepayAlipayLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(淘宝预支付日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPrepayAlipayLogLogicBatch(List<java.math.BigInteger> idList){
		return ebuyPrepayAlipayLogBaseDao.deleteEbuyPrepayAlipayLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(淘宝预支付日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayAlipayLog(java.math.BigInteger id){
//		return ebuyPrepayAlipayLogBaseDao.deleteEbuyPrepayAlipayLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(淘宝预支付日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPrepayAlipayLogBatch(List<java.math.BigInteger> idList){
//		return ebuyPrepayAlipayLogBaseDao.deleteEbuyPrepayAlipayLogBatch(idList);
//	}
	
}
