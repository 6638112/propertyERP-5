package com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.dao.IEbuyProductSettleApplyLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;

/**
 * 描述:(购销模式结算申请表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductSettleApplyLogBaseService implements IEbuyProductSettleApplyLogBaseService{
	
	private IEbuyProductSettleApplyLogBaseDao ebuyProductSettleApplyLogBaseDao;
	public void setEbuyProductSettleApplyLogBaseDao(IEbuyProductSettleApplyLogBaseDao ebuyProductSettleApplyLogBaseDao) {
		this.ebuyProductSettleApplyLogBaseDao = ebuyProductSettleApplyLogBaseDao;
	}
	/**
	 * 根据条件查询(购销模式结算申请表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap){
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(购销模式结算申请表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByConditionDim(Map<String,Object> paramMap){
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(购销模式结算申请表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(购销模式结算申请表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductSettleApplyLog> getEbuyProductSettleApplyLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(购销模式结算申请表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductSettleApplyLog getEbuyProductSettleApplyLogBySeqId(java.math.BigInteger id){
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductSettleApplyLogCount(Map<String,Object> paramMap){
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(购销模式结算申请表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductSettleApplyLogCountDim(Map<String,Object> paramMap){
		return ebuyProductSettleApplyLogBaseDao.selectEbuyProductSettleApplyLogCount(paramMap,true);
	}
	/**
	 * 往(购销模式结算申请表)新增一条记录
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	@Override
	public int insertEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog){
		return ebuyProductSettleApplyLogBaseDao.insertEbuyProductSettleApplyLog(ebuyProductSettleApplyLog);
	}
	/**
	 * 批量新增(购销模式结算申请表)
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	@Override
	public int insertEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList){
		return ebuyProductSettleApplyLogBaseDao.insertEbuyProductSettleApplyLogBatch(ebuyProductSettleApplyLogList);
	}
	/**
	 * 更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLog
	 * @return
	 */
	@Override
	public int updateEbuyProductSettleApplyLog(EbuyProductSettleApplyLog ebuyProductSettleApplyLog){
		return ebuyProductSettleApplyLogBaseDao.updateEbuyProductSettleApplyLog(ebuyProductSettleApplyLog);
	}
	/**
	 * 批量更新(购销模式结算申请表)信息
	 * @param ebuyProductSettleApplyLogList
	 * @return
	 */
	@Override
	public int updateEbuyProductSettleApplyLogBatch(List<EbuyProductSettleApplyLog> ebuyProductSettleApplyLogList){
		return ebuyProductSettleApplyLogBaseDao.updateEbuyProductSettleApplyLogBatch(ebuyProductSettleApplyLogList);
	}
	/**
	 * 根据序列号删除(购销模式结算申请表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyProductSettleApplyLogLogic(java.math.BigInteger id){
		return ebuyProductSettleApplyLogBaseDao.deleteEbuyProductSettleApplyLogLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(购销模式结算申请表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyProductSettleApplyLogLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductSettleApplyLogBaseDao.deleteEbuyProductSettleApplyLogLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(购销模式结算申请表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSettleApplyLog(java.math.BigInteger id){
//		return ebuyProductSettleApplyLogBaseDao.deleteEbuyProductSettleApplyLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(购销模式结算申请表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSettleApplyLogBatch(List<java.math.BigInteger> idList){
//		return ebuyProductSettleApplyLogBaseDao.deleteEbuyProductSettleApplyLogBatch(idList);
//	}
	
}
