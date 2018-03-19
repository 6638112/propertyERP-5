/**   
* Filename:    CommonDeviceService.java   
* @version:    1.0  
* Create at:   2014年7月6日 下午1:39:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonUserDao;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.userTmp.dao.IUserTmpBaseDao;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * Filename:    CommonDeviceService.java
 * @version:    1.0.0
 * Create at:   2014年7月6日 下午1:39:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月6日       shiyl             1.0             1.0 Version
 */
public class CommonDeviceService implements ICommonDeviceService{
	private Log logger = LogFactory.getLog(getClass());
	private IUserTmpBaseDao userTmpBaseDao;
	public void setUserTmpBaseDao(IUserTmpBaseDao userTmpBaseDao) {
		this.userTmpBaseDao = userTmpBaseDao;
	}
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommonUserDao commonUserDao;
	public void setCommonUserDao(ICommonUserDao commonUserDao) {
		this.commonUserDao = commonUserDao;
	}
	@Override
	public UserTmp checkAndCreate(String deviceId, Long deviceType) {
			//创建临时用户记录（校验是否已存在设备Id的临时用户护记录）
			UserTmp userTmp = new UserTmp();
			userTmp.setDeviceId(deviceId);
			userTmp.setDeviceType(deviceType);//使用渠道标识标识设备类别
//			List<UserTmp> userTmpList = userTmpBaseDao.selectUserTmpByCondition(MapConverter.toMap(userTmp),false);
			List<UserTmp> userTmpList = commonUserDao.selectUserListByDeviceInfo(deviceId, deviceType);
			BigInteger userTmpId = null;
			if(userTmpList.size()==0){//设备未存在，新增
				userTmpId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_tmp);
				userTmp.setId(userTmpId);
				int addCount = userTmpBaseDao.insertUserTmp(userTmp);
				if(addCount<=0){
					throw new BusinessRuntimeException("CommonDeviceService.checkAndCreate.insertUserTmp.failed");
				}
			}else{
				userTmpId = userTmpList.get(0).getId();
			}
			UserTmp resUser = new UserTmp();
			resUser.setDeviceId(deviceId);
			resUser.setDeviceType(deviceType);
			resUser.setId(userTmpId);
			return resUser;
	}
	@Override
	public UserIdType getUserIdType(){
		BigInteger userId = null;
		Integer userType = null;
		if(UserContext.getOperIdBigInteger()!=null){
			userId = UserContext.getOperIdBigInteger();
			userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		}else{
			String deviceId = HeaderManager.getDeviceId();//设备Id
			Long deviceType = HeaderManager.getSubChannelIdLong();
			UserTmp userTmp=checkAndCreate(deviceId, deviceType);
			userId = userTmp.getId();
			userType = LoginDict.UserRegistOrTmp.TMP_USER;
		}
		UserIdType userIdType = new UserIdType(userId, userType);
		return userIdType;
	}
	
	@Override
	public void checkAndCreateQueue(String deviceId, Long deviceType) {
		int existCount = commonUserDao.selectUserCountByDeviceInfo(deviceId, deviceType);
		if(existCount<=0){//不存在则新增
			UserTmp userTmp = new UserTmp();
			userTmp.setDeviceId(deviceId);
			userTmp.setDeviceType(deviceType);//使用渠道标识标识设备类别
			BigInteger userTmpId = null;
			userTmpId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_tmp);
			userTmp.setId(userTmpId);
			int addCount = userTmpBaseDao.insertUserTmp(userTmp);
			if(addCount<=0){
				throw new BusinessRuntimeException("CommonDeviceService.checkAndCreate.insertUserTmp.failed");
			}
		}
	}
	
}
