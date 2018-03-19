/**   
* Filename:    PrizeRecordTmpDataServiceUn.java   
* @version:    1.0  
* Create at:   2015年7月17日 下午5:57:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.serviceUn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.prize.dao.IPrizeRecordTmpDataDao;
import com.cnfantasia.server.api.prize.service.IPrizeRecordTmpDataService;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * Filename:    PrizeRecordTmpDataServiceUn.java
 * @version:    1.0.0
 * Create at:   2015年7月17日 下午5:57:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月17日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordTmpDataServiceUn {
	
	private IPrizeRecordTmpDataService prizeRecordTmpDataService;
	public void setPrizeRecordTmpDataService(IPrizeRecordTmpDataService prizeRecordTmpDataService) {
		this.prizeRecordTmpDataService = prizeRecordTmpDataService;
	}
	
	private IPrizeRecordTmpDataDao prizeRecordTmpDataDao;
	public void setPrizeRecordTmpDataDao(IPrizeRecordTmpDataDao prizeRecordTmpDataDao) {
		this.prizeRecordTmpDataDao = prizeRecordTmpDataDao;
	}

	//private static AtomicInteger isFineshed = new AtomicInteger(0);
	private Log logger = LogFactory.getLog(getClass());
	
	public void autoFreshTmpPrizeData2Formal() {
	//	commonLockService.getLock(Lock.PRIZE_RECORD_TMP_DATA);
		//	logger.debug("autoFreshTmpPrizeData2Formal,start,"+new Date());
	//	if(isFineshed.compareAndSet(0, 1)){
	//		try {
				List<PrizeRecordTmpData> prizeRecordTmpDataList = prizeRecordTmpDataDao.selectTmpPrizeDataListNotFailedAndTimeOut();
				if(prizeRecordTmpDataList!=null&&prizeRecordTmpDataList.size()>0){
					for(PrizeRecordTmpData tmpData:prizeRecordTmpDataList){
						try {
							prizeRecordTmpDataService.transferTmpPrizeData2Formal(tmpData.getId());
						} catch (Exception e) {
							logger.debug("autoFreshTmpPrizeData2Formal,failed,tmpData id is:"+tmpData.getId(),e);
						}
					}
	//			}
	//		}finally{
	//			isFineshed.set(0);
	//		}
		}
		//	logger.debug("autoFreshTmpPrizeData2Formal,finished,"+new Date());
	}
	
	
}
