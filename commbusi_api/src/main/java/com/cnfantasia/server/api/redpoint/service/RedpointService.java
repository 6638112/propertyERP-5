/**   
* Filename:    RedpointService.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:06:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.propertycard.dao.PropertyCardDao;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.dao.IRedpointDao;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.redpointContent.dao.IRedpointContentBaseDao;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContentSource.dao.IRedpointContentSourceBaseDao;
import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;

/**
 * Filename:    RedpointService.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:06:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public class RedpointService implements IRedpointService{
	private Log logger = LogFactory.getLog(getClass());
	private IRedpointDao redpointDao;
	public void setRedpointDao(IRedpointDao redpointDao) {
		this.redpointDao = redpointDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IRedpointContentBaseDao redpointContentBaseDao;
	public void setRedpointContentBaseDao(IRedpointContentBaseDao redpointContentBaseDao) {
		this.redpointContentBaseDao = redpointContentBaseDao;
	}
	
	private IRedpointContentSourceBaseDao redpointContentSourceBaseDao;
	public void setRedpointContentSourceBaseDao(IRedpointContentSourceBaseDao redpointContentSourceBaseDao) {
		this.redpointContentSourceBaseDao = redpointContentSourceBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	@Resource 
	private PropertyCardDao propertyCardDao;
	@Resource
	private IPropertySoftwareDockDao propertySoftwareDockDao;

	@Override
	public RedpointContentEntity qryRedpointInfo(BigInteger userId, Integer userType, String modelCode) {
		BigInteger defaultRoomId = RoomConstants.ROOM_NULL_ID_REAL;
		if(userType!=null&&userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){//注册用户尝试获取门牌
			defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		}
		return redpointDao.selectRedpointContentByModelCode(userId,userType,defaultRoomId,modelCode);
	}

	@Override
	public List<RedpointContentEntity> qryRedpointInfoMulti(BigInteger userId, Integer userType, List<String> modelCodeList) {
		BigInteger defaultRoomId = RoomConstants.ROOM_NULL_ID_REAL;
		if(userType!=null&&userType.compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){//注册用户尝试获取门牌
			defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		}
		return redpointDao.selectRedpointContentByModelCodeList(userId,userType,defaultRoomId,modelCodeList);
	}

	@Override
	public RedpointContentEntity clickRedpointInfo(BigInteger userId, Integer userType, String modelCode) {
		BigInteger defaultRoomId = null;
		if (!RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON.equals(modelCode)) {
			defaultRoomId =	commonRoomService.getDefaultRoomIdByUserId(userId);
		}
		Integer resCount = redpointDao.updateRedpointContentClicked(userId,userType,defaultRoomId,modelCode);
		/*if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("RedpointService.clickRedpointInfo.updateRedpointContentClicked.failed");
		}*/
		return redpointDao.selectRedpointContentByModelCode(userId,userType,defaultRoomId,modelCode);
	}

	@Override
	public void addRedpointContentForFamily_HasLogin(BigInteger userId, String modelCode,
			List<BasicSourceEntity> sourceList) {
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;//默认已登录用户
		List<UserSimpleEntity> familyUserList = commonUserService.getFamilyMembersWithoutSelf(userId, false);
		List<CommUserDataEntity> toRedPointUserList = null;
		if(familyUserList!=null&&familyUserList.size()>0){
			toRedPointUserList = new ArrayList<CommUserDataEntity>();
			for(UserSimpleEntity tmpUserSimpleEntity:familyUserList){
				CommUserDataEntity tmpCommUserDataEntity = new CommUserDataEntity(tmpUserSimpleEntity.getId(), userType, tmpUserSimpleEntity.getDefaultRoomId());
				toRedPointUserList.add(tmpCommUserDataEntity);
			}
		}
		if(toRedPointUserList!=null&&toRedPointUserList.size()>0){
			addRedpointContentMulti(toRedPointUserList, modelCode, sourceList);
		}
	}

	@Override
	public void addRedpointContent(BigInteger userId, Integer userType, BigInteger currRoomId, String modelCode, List<BasicSourceEntity> sourceList) {
		this.addRedpointContent(userId, userType, currRoomId, modelCode, sourceList, null);
	}
	
	private void addRedpointContent(BigInteger userId, Integer userType, BigInteger currRoomId, String modelCode, List<BasicSourceEntity> sourceList, String expireTime) {
		String nowTime = dualDao.getNowTime();
		RedpointContentEntity existContent = redpointDao.selectRedpointContentByModelCode(userId,userType,currRoomId,modelCode);
		BigInteger contentId = null;
		if(existContent==null){//新增
			BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content);
			RedpointContent redpointContent = new RedpointContent();
			redpointContent.setId(toAddId);
			redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);//未点
			redpointContent.setLastClickTime(null);//点击时间为空
			redpointContent.setLastModifyTime(nowTime);
			redpointContent.setModelCode(modelCode);
			redpointContent.setRoomId(currRoomId);
			redpointContent.setUserId(userId);
			redpointContent.setUserType(userType);
			redpointContent.setExpireTime(expireTime);
			Integer resCount = redpointContentBaseDao.insertRedpointContent(redpointContent);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.insertRedpointContent.failed");
			}
			contentId = toAddId;
		}else{//更新
			contentId = existContent.getId();
			RedpointContent toUpdateContent = new RedpointContent();
			toUpdateContent.setId(contentId);
			toUpdateContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);//未点
			toUpdateContent.setLastModifyTime(nowTime);
			toUpdateContent.setExpireTime(expireTime);
			Integer resCount = redpointContentBaseDao.updateRedpointContent(toUpdateContent);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.updateRedpointContent.failed");
			}
		}
		if(sourceList!=null&&sourceList.size()>0){
			List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content_source, sourceList.size());
			List<RedpointContentSource> toAddSourceList = new ArrayList<RedpointContentSource>();
			for(int i=0;i<sourceList.size();i++){
				BasicSourceEntity tmpSource = sourceList.get(i);
				BigInteger toAddId = toAddIdList.get(i);
				BigInteger sourceId = tmpSource.getSourceId();
				Integer sourceType = tmpSource.getSourceType();
				String operationType = tmpSource.getOperationType();
				RedpointContentSource toAddSource = new RedpointContentSource();
				toAddSource.setId(toAddId);
				toAddSource.setLastModifyTime(nowTime);
				toAddSource.setSourceId(sourceId);
				toAddSource.setSourceType(sourceType);
				toAddSource.settRedpointContentFId(contentId);
				toAddSource.setOperationType(operationType);
				toAddSourceList.add(toAddSource);
			}
			Integer resCount = redpointContentSourceBaseDao.insertRedpointContentSourceBatch(toAddSourceList);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.insertRedpointContentSourceBatch.failed");
			}
		}
		redpointDao.freshRedpointContentClickStatus(userId, userType,currRoomId, modelCode);//刷新同步红点状态信息
	}

	@Override
	public void addRedpointContentMulti(List<CommUserDataEntity> commUserDataEntityList,
			String modelCode, List<BasicSourceEntity> sourceList) {
		if(commUserDataEntityList!=null&&commUserDataEntityList.size()>0){
			for(CommUserDataEntity tmpCommUserDataEntity:commUserDataEntityList){
				BigInteger userId = tmpCommUserDataEntity.getUserId();
				Integer userType = tmpCommUserDataEntity.getUserType();
				BigInteger defaultRoomId = tmpCommUserDataEntity.getDefaultRoomId();
				addRedpointContent(userId, userType, defaultRoomId, modelCode, sourceList);
			}
		}
	}

	@Override
	public boolean addPropertyBillRedPoint(BigInteger userId) {
		RealRoom rr = commonRoomService.selectRealRoomByUserId(userId);
		if(propertySoftwareDockDao.getRealRoomSoftwareInfo(rr.getId()) == null) {//不是第三方小区才进行红点查询
			BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
			//1 待缴费账单（不再区分月度和周期）
			List<PayBill> payBillMonthList = propertyCardDao.qryPayBillByRealRoomId(rr.getId(), false);
			if (payBillMonthList.size() > 0) {
				addPropertyBillRedPoint(userId, payBillMonthList);
				return true;
			}

			{//可能的删除或过期的账单，即本次请求时，已没有可缴账单，清除用户红点信息
				RedpointContent rc = new RedpointContent();
				rc.setClickStatus(RedpointDict.RedpointContent_clickStatus.HAVE_CLICK);
				rc.setUserId(userId);
				rc.setRoomId(roomId);
				rc.setUserType(LoginDict.UserRegistOrTmp.REGIST_USER);
				rc.setModelCode(RedpointConstant.RedpointContent_ModelCode.IS_HAS_PROPERTY_BILL);
				int resCount = redpointDao.freshRedpointContentClickStatus2(rc);
				if (resCount <= 0) {
					logger.info("RedpointService.clickRedpointInfo.updateRedpointContentClicked.failed");
				}
			}
		} else {
			return true;
		}
		return false;
	}

	private void addPropertyBillRedPoint(BigInteger userId, List<PayBill> payBillMonthList) {
		List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
		BasicSourceEntity basicSourceEntity = new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.PayBill, payBillMonthList.get(payBillMonthList.size()-1).getId(),
				"INSERT");
		sourceList.add(basicSourceEntity);		
		
		addRedpointContent(userId, LoginDict.UserRegistOrTmp.REGIST_USER, commonRoomService.getDefaultRoomIdByUserId(userId), RedpointConstant.RedpointContent_ModelCode.IS_HAS_PROPERTY_BILL, sourceList,  payBillMonthList.get(payBillMonthList.size()-1).getPayDayEnd());
	}
}
