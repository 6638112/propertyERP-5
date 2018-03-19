/**   
* Filename:    ShareService.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午1:34:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commShareRecord.dao.ICommShareRecordBaseDao;
import com.cnfantasia.server.domainbase.commShareRecord.entity.CommShareRecord;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    ShareService.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午1:34:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class ShareService implements IShareService{
	
	private ICommShareRecordBaseDao commShareRecordBaseDao;
	public void setCommShareRecordBaseDao(ICommShareRecordBaseDao commShareRecordBaseDao) {
		this.commShareRecordBaseDao = commShareRecordBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IPointService pointService;
	public void setPointService(IPointService pointService) {
		this.pointService = pointService;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	@Override
	public void doShare(BigInteger userId, Integer shareType, String title, String content) {
		{//新增记录
			CommShareRecord commShareRecordAdd = new CommShareRecord();
			BigInteger commShareRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_share_record);
			User currUser = userBaseDao.selectUserBySeqId(userId);
			String nowTime = dualDao.getNowTime();
			commShareRecordAdd.setContent(content);
			commShareRecordAdd.setId(commShareRecordAddId);
			commShareRecordAdd.setRoomId(currUser.getDefaultRoomId());
			commShareRecordAdd.setTime(nowTime);
			commShareRecordAdd.setTitle(title);
			commShareRecordAdd.setType(shareType);
			commShareRecordAdd.setUserId(userId);
			Integer resCount = commShareRecordBaseDao.insertCommShareRecord(commShareRecordAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("ShareService.doShare.failed");
			}
		}
		//积分处理
		pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.Share);
	}
	
	
}
