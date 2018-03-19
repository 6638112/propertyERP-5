/**   
* Filename:    XanaduService.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午3:33:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.xanadu.constant.XanaduDict;
import com.cnfantasia.server.api.xanadu.dao.IXanaduDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.userXanaduRecord.dao.IUserXanaduRecordBaseDao;
import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * Filename:    XanaduService.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午3:33:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public class XanaduService implements IXanaduService{
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IXanaduDao xanaduDao;
	public void setXanaduDao(IXanaduDao xanaduDao) {
		this.xanaduDao = xanaduDao;
	}
	
	private IUserXanaduRecordBaseDao userXanaduRecordBaseDao;
	public void setUserXanaduRecordBaseDao(IUserXanaduRecordBaseDao userXanaduRecordBaseDao) {
		this.userXanaduRecordBaseDao = userXanaduRecordBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public Boolean getCurrStatus(BigInteger userId) {
		if(StringUtils.isEmpty(userId)){return true;}
		RoomEntity defaultRoom = commonRoomService.getDefaultRoomInfo(userId);
		if(defaultRoom.checkIsEmptyRoom()){return true;}
		Integer count = xanaduDao.selectXanaduTrueCount(userId);//查询用户属于世外桃源的记录数
		if(count!=null&&count>0){return true;}//有记录则表示在室外桃园
		return false;
	}

	@Override
	public Boolean changeXanaduStatus(BigInteger userId, Boolean statusReq) {
		if(StringUtils.isEmpty(userId)){return true;}
		List<UserXanaduRecord> userXanaduRecordList= xanaduDao.selectXanaduRecordList(userId);
		if(userXanaduRecordList!=null&&userXanaduRecordList.size()==1){//更新记录
			UserXanaduRecord currUserXanaduRecord = userXanaduRecordList.get(0);
			UserXanaduRecord toUpdData = new UserXanaduRecord();
			toUpdData.setId(currUserXanaduRecord.getId());
			toUpdData.setStatus(statusReq?XanaduDict.UserXanaduRecord_status.TRUE:XanaduDict.UserXanaduRecord_status.FALSE);
			Integer resCount = userXanaduRecordBaseDao.updateUserXanaduRecord(toUpdData);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("xanadu.changeXanaduStatus.upd.failed");
			}
		}else{
			if(userXanaduRecordList!=null&&userXanaduRecordList.size()>1){//删除全部
				List<BigInteger> delIds = new ArrayList<BigInteger>();
				for(UserXanaduRecord tmp:userXanaduRecordList){
					delIds.add(tmp.getId());
				}
				userXanaduRecordBaseDao.deleteUserXanaduRecordLogicBatch(delIds);
			}
			//新增记录
			UserXanaduRecord toAddData = new UserXanaduRecord();
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_xanadu_record);
			toAddData.setId(addId);
			toAddData.setUserId(userId);
			toAddData.setStatus(statusReq?XanaduDict.UserXanaduRecord_status.TRUE:XanaduDict.UserXanaduRecord_status.FALSE);
			Integer resCount = userXanaduRecordBaseDao.insertUserXanaduRecord(toAddData);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("xanadu.changeXanaduStatus.add.failed");
			}
		}
		return getCurrStatus(userId);
	}
	
}
