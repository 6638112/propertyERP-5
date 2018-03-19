package com.cnfantasia.server.ms.logger.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.ms.logger.dao.ICommLoggerBaseDao;
import com.cnfantasia.server.ms.logger.entity.ManagerLogger;


public class SysLoggerMsService implements ISysLoggerMsService{
	private Log logger = LogFactory.getLog(getClass());
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommLoggerBaseDao commLoggerBaseDao;
	public void setCommLoggerBaseDao(ICommLoggerBaseDao commLoggerBaseDao) {
		this.commLoggerBaseDao = commLoggerBaseDao;
	}
	
	@Override
	public List<ManagerLogger> synch2Database(List<ManagerLogger> logCatcheList) { 
		if (logCatcheList != null && logCatcheList.size() > 0) {
			for (int k = 1; k <= 1; k++) {// 尝试1次
				logger.info("start to insertCommLoggerBatch,logCatcheList size is:"+logCatcheList.size());
				try {
					commLoggerBaseDao.insertCommLoggerBatch(logCatcheList);
					logCatcheList.clear();
					logger.info("process insertCommLoggerBatch success,curr try count is "+k);
					break;
				} catch (Exception e) {
					logger.debug("SysLoggerService synch2Database insertCommLoggerBatch cause exception.", e);
					logger.info("process insertCommLoggerBatch failed,curr try count is "+k);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}// 5秒后再尝试
				}
			}
			if (logCatcheList != null && logCatcheList.size() > 0) {// 如果上面批量执行不成功，尝试逐个处理
				logger.info("start to insertCommLogger by signal ,logCatcheList size is:"+logCatcheList.size());
				for (int m = 0; m < logCatcheList.size(); m++) {
					ManagerLogger signal = logCatcheList.get(m);
					try {
						commLoggerBaseDao.insertCommLogger(signal);
//						logCatcheList.remove(signal);//成功则移除
					} catch (Exception e) {
						logger.debug(
								"SysLoggerService synch2Database insertCommLogger signal  cause exception,data is:" + signal, e);
					}finally{
						logCatcheList.remove(signal);//成功或失败都移除
					}
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}// 每隔100毫秒新增一次
				}
			}
		}
		if(logCatcheList.size()>0){
			logger.error("SysLoggerService synch2Database has failed records,result logCatcheList size is:" + logCatcheList.size());
			logger.error("result data is:" + JSON.toJSONString(logCatcheList));
		}
		return logCatcheList;
	}
	
	/**
	 * 根据条件查询(公共日志记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ManagerLogger> getCommLoggerByCondition(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(公共日志记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ManagerLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(公共日志记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ManagerLogger> getCommLoggerByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(公共日志记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ManagerLogger> getCommLoggerByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commLoggerBaseDao.selectCommLoggerByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(公共日志记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ManagerLogger getCommLoggerBySeqId(java.math.BigInteger id){
		return commLoggerBaseDao.selectCommLoggerBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerCount(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommLoggerCountDim(Map<String,Object> paramMap){
		return commLoggerBaseDao.selectCommLoggerCount(paramMap,true);
	}
	/**
	 * 往(公共日志记录表)新增一条记录
	 * @param commLogger
	 * @return
	 */
	@Override
	public int insertCommLogger(ManagerLogger commLogger){
		return commLoggerBaseDao.insertCommLogger(commLogger);
	}
	/**
	 * 批量新增(公共日志记录表)
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int insertCommLoggerBatch(List<ManagerLogger> commLoggerList){
		return commLoggerBaseDao.insertCommLoggerBatch(commLoggerList);
	}
	/**
	 * 更新(公共日志记录表)信息
	 * @param commLogger
	 * @return
	 */
	@Override
	public int updateCommLogger(ManagerLogger commLogger){
		return commLoggerBaseDao.updateCommLogger(commLogger);
	}
	/**
	 * 批量更新(公共日志记录表)信息
	 * @param commLoggerList
	 * @return
	 */
	@Override
	public int updateCommLoggerBatch(List<ManagerLogger> commLoggerList){
		return commLoggerBaseDao.updateCommLoggerBatch(commLoggerList);
	}
	/**
	 * 根据序列号删除(公共日志记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogic(java.math.BigInteger id){
		return commLoggerBaseDao.deleteCommLoggerLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(公共日志记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerLogicBatch(List<java.math.BigInteger> idList){
		return commLoggerBaseDao.deleteCommLoggerLogicBatch(idList);
	}

}
