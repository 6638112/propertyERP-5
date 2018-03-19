/**   
* Filename:    CommonPointService.java   
* @version:    1.0  
* Create at:   2015年1月5日 上午6:37:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonPointDao;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    CommonPointService.java
 * @version:    1.0.0
 * Create at:   2015年1月5日 上午6:37:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月5日       shiyl             1.0             1.0 Version
 */
public class CommonPointService implements ICommonPointService{
	private ICommonPointDao commonPointDao;
	public void setCommonPointDao(ICommonPointDao commonPointDao) {
		this.commonPointDao = commonPointDao;
	}
	
//	private IUuidManager uuidManager;
//	public void setUuidManager(IUuidManager uuidManager) {
//		this.uuidManager = uuidManager;
//	}
//	
//	private IPointDataBaseDao pointDataBaseDao;
//	public void setPointDataBaseDao(IPointDataBaseDao pointDataBaseDao) {
//		this.pointDataBaseDao = pointDataBaseDao;
//	}

	@Override
	public PointData getPointDataByUserId(BigInteger userId) {
		PointData pointResData = null;
		synchronized (userId) {
			List<PointData> pointDataList = commonPointDao.selectPointDataByUserId(userId);
			if(pointDataList==null||pointDataList.size()<=0){
//				PointData pointDataAdd = new PointData();
//				BigInteger pointDataAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_point_data);
//				pointDataAdd.setId(pointDataAddId);
//				pointDataAdd.setPointValue(0L);
//				pointDataAdd.settUserFId(userId);
//				Integer resCount = pointDataBaseDao.insertPointData(pointDataAdd);
//				if(resCount==null||resCount<=0){
//					throw new BusinessRuntimeException("CommonPointService.insertPointData.failed");
//				}
//				pointResData = commonPointDao.selectPointDataByUserId(userId).get(0);
			}else if(pointDataList.size()==1){
				pointResData = commonPointDao.selectPointDataByUserId(userId).get(0);
			}else{
				throw new BusinessRuntimeException("CommonPointService.getPointDataByUserId.multiData");
			}
		}
		return pointResData;
	}
	
}
