/**   
* Filename:    CollectionsService.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午8:32:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.collections.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.collections.dao.ICollectionsDao;
import com.cnfantasia.server.api.commonBusiness.service.ICommonGatherDataService;
import com.cnfantasia.server.api.user.entity.CollectionsEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.collections.dao.ICollectionsBaseDao;
import com.cnfantasia.server.domainbase.collections.entity.Collections;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    CollectionsService.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午8:32:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public class CollectionsService implements ICollectionsService{
	private ICollectionsDao collectionsDao;
	public void setCollectionsDao(ICollectionsDao collectionsDao) {
		this.collectionsDao = collectionsDao;
	}
	private ICollectionsBaseDao collectionsBaseDao;
	public void setCollectionsBaseDao(ICollectionsBaseDao collectionsBaseDao) {
		this.collectionsBaseDao = collectionsBaseDao;
	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommonGatherDataService commonGatherDataService;
	public void setCommonGatherDataService(ICommonGatherDataService commonGatherDataService) {
		this.commonGatherDataService = commonGatherDataService;
	}

	@Override
	public int doCollections(BigInteger userId, Integer goalType, BigInteger goalId, boolean isCollections) {
		Collections collectionsQry = new Collections();
		collectionsQry.setUserId(userId);
		collectionsQry.setTargetId(goalId);
		collectionsQry.setTargetType(goalType);
		BigInteger updateCollectionsId = null;
		{//查询是否存在
			List<Collections> tmpList = collectionsBaseDao.selectCollectionsByCondition(MapConverter.toMap(collectionsQry), false);
			if(tmpList!=null&&tmpList.size()>0){
				updateCollectionsId = tmpList.get(0).getId();
			}
		}
		if(updateCollectionsId!=null){
			if(isCollections){//保持
			}else{
				int res = collectionsBaseDao.deleteCollectionsLogic(updateCollectionsId);
				if(res<=0){
					throw new BusinessRuntimeException("CollectionsService.doCollections.deleteCollectionsLogic.failed");
				}
			}
		}else{
			BigInteger collectionsAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_collections);
			String nowTime = dualDao.getNowTime();
			Collections collectionsAdd = new Collections();
			collectionsAdd.setUserId(userId);
			collectionsAdd.setTargetId(goalId);
			collectionsAdd.setTargetType(goalType);
			collectionsAdd.setId(collectionsAddId);
			collectionsAdd.setTime(nowTime);
			int res = collectionsBaseDao.insertCollections(collectionsAdd);
			if(res<=0){
				throw new BusinessRuntimeException("CollectionsService.doCollections.insertCollections.failed");
			}
		}
		commonGatherDataService.appendTotalCollectCount(goalType, goalId, isCollections?1:-1);//syl-add-2015-7-1 17:17:49
		int totalCount = qryCollectionsCount(goalType, goalId);
		return totalCount;
	}

	@Override
	public boolean qryIsCollections(BigInteger userId, Integer goalType, BigInteger goalId) {
		Collections collectionsQry = new Collections();
		collectionsQry.setUserId(userId);
		collectionsQry.setTargetId(goalId);
		collectionsQry.setTargetType(goalType);
		int count = collectionsBaseDao.selectCollectionsCount(MapConverter.toMap(collectionsQry), false);
		return count>0?true:false;
	}

	@Override
	public int qryCollectionsCount(Integer goalType, BigInteger goalId) {
//		Collections collectionsQry = new Collections();
//		collectionsQry.setTargetId(goalId);
//		collectionsQry.setTargetType(goalType);
//		int count = collectionsBaseDao.selectCollectionsCount(MapConverter.toMap(collectionsQry), false);
//		return count;
		
		CommonGatherData signal = commonGatherDataService.getGatherDataSignal(goalType, goalId);
		return signal==null?0:signal.getTotalCollectCount().intValue();
	}
	
	@Override
	public List<CollectionsEntity> getCollectionsCommunitySupplyList(BigInteger userId,PageModel pageModel) {
		return collectionsDao.selectCollectionsCommunitySupplyList(userId, pageModel);
	}
	
}
