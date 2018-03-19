/**   
* Filename:    SysLoggerService.java   
* @version:    1.0  
* Create at:   2014年8月6日 上午2:11:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysLogger.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerService;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commLogger.dao.ICommLoggerBaseDao;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;
import com.cnfantasia.server.domainbase.commLoggerControl.dao.ICommLoggerControlBaseDao;
import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;

/**
 * Filename:    SysLoggerService.java
 * @version:    1.0.0
 * Create at:   2014年8月6日 上午2:11:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月6日       shiyl             1.0             1.0 Version
 */
public class SysLoggerService implements ISysLoggerService<CommLogger,CommLoggerControl>{
	private Log logger = LogFactory.getLog(getClass());
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommLoggerBaseDao commLoggerBaseDao;
	public void setCommLoggerBaseDao(ICommLoggerBaseDao commLoggerBaseDao) {
		this.commLoggerBaseDao = commLoggerBaseDao;
	}
	
	private ICommLoggerControlBaseDao commLoggerControlBaseDao;
	public void setCommLoggerControlBaseDao(ICommLoggerControlBaseDao commLoggerControlBaseDao) {
		this.commLoggerControlBaseDao = commLoggerControlBaseDao;
	}


	@Override
	public List<CommLogger> synch2Database(List<CommLogger> logCatcheList) { 
		if (logCatcheList != null && logCatcheList.size() > 0) {
//			List<BigInteger> uuidList = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_logger, logCatcheList.size());
//			for (int i = 0; i < logCatcheList.size(); i++) { //会有 同步问题 导致越界,logCatcheList 通过使用new List，可以解决
//				BigInteger toAddId = uuidList.get(i);
//				logCatcheList.get(i).setId(toAddId);
//			}
//			for (int i = 0; i < uuidList.size(); i++) {
//				BigInteger toAddId = uuidList.get(i);
//				try {
//					logCatcheList.get(i).setId(toAddId);
//				} catch (Exception e) {
//					logger.error("SysLoggerService synch2Database init logCatcheList id cause exception,toAddId is:"
//				+toAddId+",index i is "+i+",logCatcheList.get(i) is "+logCatcheList==null?null:logCatcheList.get(i),e);
//				}
//			}

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
					CommLogger signal = logCatcheList.get(m);
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


	@Override
	public List<CommLoggerControl> getLoggerCtrlList() {
		List<CommLoggerControl> logCtrlList = commLoggerControlBaseDao.selectCommLoggerControlByCondition(null, false);
		return logCtrlList;
	}

}
