package com.cnfantasia.server.domainbase.ebuyLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyLog.dao.IEbuyLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyLog.entity.EbuyLog;

/**
 * 描述:(扫二维码进商品详情日志表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyLogBaseService implements IEbuyLogBaseService{
	
	private IEbuyLogBaseDao ebuyLogBaseDao;
	public void setEbuyLogBaseDao(IEbuyLogBaseDao ebuyLogBaseDao) {
		this.ebuyLogBaseDao = ebuyLogBaseDao;
	}
	/**
	 * 根据条件查询(扫二维码进商品详情日志表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyLog> getEbuyLogByCondition(Map<String,Object> paramMap){
		return ebuyLogBaseDao.selectEbuyLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(扫二维码进商品详情日志表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyLog> getEbuyLogByConditionDim(Map<String,Object> paramMap){
		return ebuyLogBaseDao.selectEbuyLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(扫二维码进商品详情日志表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyLog> getEbuyLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyLogBaseDao.selectEbuyLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(扫二维码进商品详情日志表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyLog> getEbuyLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyLogBaseDao.selectEbuyLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(扫二维码进商品详情日志表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyLog getEbuyLogBySeqId(java.math.BigInteger id){
		return ebuyLogBaseDao.selectEbuyLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(扫二维码进商品详情日志表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyLogCount(Map<String,Object> paramMap){
		return ebuyLogBaseDao.selectEbuyLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(扫二维码进商品详情日志表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyLogCountDim(Map<String,Object> paramMap){
		return ebuyLogBaseDao.selectEbuyLogCount(paramMap,true);
	}
	/**
	 * 往(扫二维码进商品详情日志表)新增一条记录
	 * @param ebuyLog
	 * @return
	 */
	@Override
	public int insertEbuyLog(EbuyLog ebuyLog){
		return ebuyLogBaseDao.insertEbuyLog(ebuyLog);
	}
	/**
	 * 批量新增(扫二维码进商品详情日志表)
	 * @param ebuyLogList
	 * @return
	 */
	@Override
	public int insertEbuyLogBatch(List<EbuyLog> ebuyLogList){
		return ebuyLogBaseDao.insertEbuyLogBatch(ebuyLogList);
	}
	/**
	 * 更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLog
	 * @return
	 */
	@Override
	public int updateEbuyLog(EbuyLog ebuyLog){
		return ebuyLogBaseDao.updateEbuyLog(ebuyLog);
	}
	/**
	 * 批量更新(扫二维码进商品详情日志表)信息
	 * @param ebuyLogList
	 * @return
	 */
	@Override
	public int updateEbuyLogBatch(List<EbuyLog> ebuyLogList){
		return ebuyLogBaseDao.updateEbuyLogBatch(ebuyLogList);
	}
	/**
	 * 根据序列号删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyLogLogic(java.math.BigInteger id){
		return ebuyLogBaseDao.deleteEbuyLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(扫二维码进商品详情日志表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyLogLogicBatch(List<java.math.BigInteger> idList){
		return ebuyLogBaseDao.deleteEbuyLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(扫二维码进商品详情日志表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyLog(java.math.BigInteger id){
//		return ebuyLogBaseDao.deleteEbuyLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(扫二维码进商品详情日志表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyLogBatch(List<java.math.BigInteger> idList){
//		return ebuyLogBaseDao.deleteEbuyLogBatch(idList);
//	}
	
}
