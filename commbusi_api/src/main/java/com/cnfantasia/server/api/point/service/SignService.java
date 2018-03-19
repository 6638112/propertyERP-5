/**   
* Filename:    SignService.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午1:34:12   
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
import com.cnfantasia.server.api.point.dao.ISignDao;
import com.cnfantasia.server.api.point.entity.SignResultEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commSignRecord.dao.ICommSignRecordBaseDao;
import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    SignService.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午1:34:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class SignService implements ISignService{
	private ISignDao signDao;
	public void setSignDao(ISignDao signDao) {
		this.signDao = signDao;
	}

	private ICommSignRecordBaseDao commSignRecordBaseDao;
	public void setCommSignRecordBaseDao(ICommSignRecordBaseDao commSignRecordBaseDao) {
		this.commSignRecordBaseDao = commSignRecordBaseDao;
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
	public CommSignRecord getLastCommSignRecord(BigInteger userId) {
		return signDao.selectLastCommSignRecord(userId);
	}
	@Override
	public SignResultEntity getLastCommSignRecordWithTodayFirst(BigInteger userId) {
		SignResultEntity resEntity = new SignResultEntity();
		boolean todayFirst = true;//默认每天首次签到
		String nowTime = dualDao.getNowTime();
		CommSignRecord lastCommSignRecord = getLastCommSignRecord(userId);
		if(lastCommSignRecord==null){
			
		}else{
			String fromTime = lastCommSignRecord.getTime();
			Integer resNum = DateUtil.daysBetween(fromTime, nowTime);
			if(resNum>=0){//今天及以后
				todayFirst = false;//本次
			}
		}
		//结果处理
		resEntity.setCommSignRecord(lastCommSignRecord);
		resEntity.setTodayFirst(todayFirst);
		return resEntity;
	}

	@Override
	public SignResultEntity doSign(BigInteger userId) {
		SignResultEntity resEntity = new SignResultEntity();
//		boolean todayFirst = true;//默认每天首次签到
//		
//		String nowTime = dualDao.getNowTime();
//		Long signDayCount = null;
//		{//获取本次连续在线天数
//			CommSignRecord lastCommSignRecord = getLastCommSignRecord(userId);
//			if(lastCommSignRecord==null){
//				signDayCount = 1L;
//			}else{
//				String fromTime = lastCommSignRecord.getTime();
//				Integer resNum = DateUtil.daysBetween(fromTime, nowTime);
//				if(resNum>=0){//今天及以后
//					signDayCount = lastCommSignRecord.getDayCount();//签到持续天数保持不变
//					todayFirst = false;//本次
//				}else if(resNum==-1){//昨天签到
//					signDayCount = lastCommSignRecord.getDayCount()+1;//签到持续天数+1
//				}else{//昨天之前签到
//					signDayCount = 1L;//恢复为第一天签到
//				}
//			}
//		}
//		{//新增签到记录
//			CommSignRecord commSignRecordAdd = new CommSignRecord();
//			BigInteger commSignRecordAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_sign_record);
//			User currUser = userBaseDao.selectUserBySeqId(userId);
//			commSignRecordAdd.setDayCount(signDayCount);
//			commSignRecordAdd.setId(commSignRecordAddId);
//			commSignRecordAdd.setRoomId(currUser.getDefaultRoomId());
//			commSignRecordAdd.setTime(nowTime);
//			commSignRecordAdd.setUserId(userId);
//			Integer resCount = commSignRecordBaseDao.insertCommSignRecord(commSignRecordAdd);
//			if(resCount==null||resCount<=0){
//				throw new BusinessRuntimeException("SignService.doSign.failed");
//			}
//		}
//		pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.Sign);
		
		//结果处理
		resEntity.setCommSignRecord(getLastCommSignRecord(userId));
		resEntity.setTodayFirst(false);
		return resEntity;
	}
	
	
	
}
