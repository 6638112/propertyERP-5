/**   
* Filename:    CommunitySupplyService.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午7:31:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.callable.FetchBaiduSupplyDataRunnable;
import com.cnfantasia.server.api.communitySupply.callable.FetchMljiaSupplyDataRunnable;
import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.api.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.api.communitySupply.dao.IMerchantClaimDao;
import com.cnfantasia.server.api.communitySupply.entity.*;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingSimpleWithDistance;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.userPositionInfo.dao.IUserPositionInfoBaseDao;
import com.cnfantasia.server.domainbase.userPositionInfo.entity.UserPositionInfo;

/**
 * Filename:    CommunitySupplyService.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午7:31:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyService implements ICommunitySupplyService{
	private ICommunitySupplyDao communitySupplyDao;
	public void setCommunitySupplyDao(ICommunitySupplyDao communitySupplyDao) {
		this.communitySupplyDao = communitySupplyDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}

	private IAutoFetchSupllyDataService autoFetchSupllyDataService;
	public void setAutoFetchSupllyDataService(IAutoFetchSupllyDataService autoFetchSupllyDataService) {
		this.autoFetchSupllyDataService = autoFetchSupllyDataService;
	}
	
	private IUserPositionInfoBaseDao userPositionInfoBaseDao;
	public void setUserPositionInfoBaseDao(IUserPositionInfoBaseDao userPositionInfoBaseDao) {
		this.userPositionInfoBaseDao = userPositionInfoBaseDao;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IMerchantClaimDao merchantClaimDao;
	public void setMerchantClaimDao(IMerchantClaimDao merchantClaimDao) {
		this.merchantClaimDao = merchantClaimDao;
	}
	
	private ICommunitySupplyBaseDao communitySupplyBaseDao;
	public void setCommunitySupplyBaseDao(ICommunitySupplyBaseDao communitySupplyBaseDao) {
		this.communitySupplyBaseDao = communitySupplyBaseDao;
	}

	@Override
	public BigInteger fetchGroupBuildingIdByPosition(BigInteger userId,BaiduLocation baiduLocation) {
		//获取用户所属小区Id，若未空则使用默认的小区Id
		BigInteger groupBuildId = null;
		groupBuildId = commonRoomService.getGroupBuildingIdByUserId(userId);
		if(groupBuildId.compareTo(RoomConstants.DEFAULT_GROUP_BUILDING_ID)==0){//如果时默认小区Id 则根据坐标区抓取
			GroupBuildingSimpleWithDistance nearGB = commonRoomService.getNearestGroupBuildingByBaiduLocation(baiduLocation);
			if(nearGB!=null&&nearGB.getDistance()!=null){
				if(nearGB.getDistance().compareTo(CommunitySupplyConstant.DEFAULT_MAX_LOCAT_DISTANCE)<=0){//小于指定距离
					groupBuildId = nearGB.getId();
				}
			}
		}
		if(baiduLocation!=null&&baiduLocation.isAvaible()){//缓存用户定位信息
			UserIdType tmpUserIdType = commonDeviceService.getUserIdType();
			if(tmpUserIdType!=null){
				UserPositionInfo userPositionInfoAdd = new UserPositionInfo();
				userPositionInfoAdd.setBaiduLat(baiduLocation.getLatitude().toString());
				userPositionInfoAdd.setBaiduLng(baiduLocation.getLongitude().toString());
				userPositionInfoAdd.setGroupbuildingId(groupBuildId);
				userPositionInfoAdd.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_position_info));
				userPositionInfoAdd.setUserId(tmpUserIdType.getUserId());
				userPositionInfoAdd.setUserType(tmpUserIdType.getUserType());
				userPositionInfoBaseDao.insertUserPositionInfo(userPositionInfoAdd);
			}
		}
		return groupBuildId;
	}
	
	@Override
	public GroupBuildingHasTCommunitySupply_Supply getCommunitySupplyDetail(BigInteger communitySupplyId,BigInteger userId,BigInteger groupBuildId) {
		if(groupBuildId==null){
			groupBuildId = commonRoomService.getGroupBuildingIdByUserId(userId);
		}
		GroupBuildingHasTCommunitySupply_Supply resCommunitySupplyEntity = communitySupplyDao.selectCommunitySupplyDetail(communitySupplyId,userId,groupBuildId);
		if(resCommunitySupplyEntity!=null&&resCommunitySupplyEntity.getCommunitySupplyEntity()!=null){
			//获取第一条评论的内容
			CommentsEntity firstContentDetail = commentsService.getFirstComentDetail(communitySupplyId,
					CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_SUPPLY);
			if(firstContentDetail!=null){
				resCommunitySupplyEntity.getCommunitySupplyEntity().setFirstComments(firstContentDetail);
				//syl-add-2015-2-6 11:38:42 更新星级信息
				Double averageLevel = commentsService.getCommentsAverageLevel(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_SUPPLY, communitySupplyId);
				if(averageLevel!=null){
					CommunitySupply communitySupplyUpd = new CommunitySupply();
					communitySupplyUpd.setId(communitySupplyId);
					communitySupplyUpd.setStarLevel(averageLevel);
					communitySupplyBaseDao.updateCommunitySupply(communitySupplyUpd);
					resCommunitySupplyEntity.getCommunitySupplyEntity().setStarLevel(averageLevel);
				}
			}
			//获取对应成功认领的相关信息
			BigInteger groupBuilding_communitySupply_RelaId = resCommunitySupplyEntity.getId();
			CommunitySupplyBelong succCommunitySupplyBelong = merchantClaimDao.selectCommunitySupplyBelongSuccess(groupBuilding_communitySupply_RelaId);
			resCommunitySupplyEntity.setSuccessSupplyBelong(succCommunitySupplyBelong);
		}
		return resCommunitySupplyEntity;
	}

	@Override
	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeList(BigInteger parentTypeId, Integer importanceLevel,
			PageModel pageModel) {
		return communitySupplyDao.selectCommunitySupplyTypeList(parentTypeId, importanceLevel, pageModel);
	}

	@Override
	public List<GroupBuildingHasTCommunitySupply_Supply> searchCommunitySupplyList(BigInteger supplyTypeId, String supplyNameKey,
			PageModel pageModel,BigInteger userId,BigInteger groupBuildId) {
		List<GroupBuildingHasTCommunitySupply_Supply> resList = null;
		if(groupBuildId!=null&&groupBuildId.compareTo(RoomConstants.DEFAULT_GROUP_BUILDING_ID)!=0){//不为空且不是默认小区
			{//增加自动触发任务 --百度数据抓取
				FutureTask<Boolean> task02 = new FutureTask<Boolean>(new FetchBaiduSupplyDataRunnable(autoFetchSupllyDataService, groupBuildId));
				new Thread(task02).start();
			}
			{//增加自动触发任务 --美丽家数据抓取
				FutureTask<Boolean> task02 = new FutureTask<Boolean>(new FetchMljiaSupplyDataRunnable(autoFetchSupllyDataService, groupBuildId));
				new Thread(task02).start();
			}
			//查询对应小区下的商家列表信息
			resList = communitySupplyDao.searchCommunitySupplyList(supplyTypeId, supplyNameKey, pageModel,userId,groupBuildId);
		}else{//根据用户信息 未能获取到小区信息  则返回空数据
			
		}
		return resList;
	}

	@Override
	public List<CommentsLabel> getCommentsLabelListBySupplyType(BigInteger supplyTypeId) {
		return communitySupplyDao.selectCommentsLabelListBySupplyType(supplyTypeId);
	}
	
	@Override
	public List<CommentsPoints> getCommentsPointsListBySupplyType(BigInteger supplyTypeId) {
		return communitySupplyDao.selectCommentsPointsListBySupplyType(supplyTypeId);
	}
	
	
	@Override
	public CallCountEntity addCallCount(BigInteger contectId) {
		int res = communitySupplyDao.updateSupplyContectCallCount(contectId);
		if(res<=0){
			throw new BusinessRuntimeException("communitySupply.addCallCount.failed");
		}
		Long currCount =  communitySupplyDao.selectSupplyContectCallCount(contectId);
		Long totalCallCount = communitySupplyDao.selectSupplyContectTotalCallCountByContectId(contectId);
		CallCountEntity callCountEntity = new CallCountEntity(currCount, totalCallCount);
		return callCountEntity;
	}

	@Override
	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeRecommend() {
		return communitySupplyDao.selectCommunitySupplyTypeRecommend();
	}

	@Override
	public CommunitySupplyTypeEntity getCommunitySupplyTypeBySupplyTypeId(BigInteger suppluTypeId) {
		return communitySupplyDao.selectCommunitySupplyTypeBySupplyTypeId(suppluTypeId);
	}

//	@Override
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeAll() {
//		return communitySupplyDao.selectCommunitySupplyTypeAll();
//	}
	
//	@Override
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeLevel01(){
//		return communitySupplyDao.selectCommunitySupplyTypeLevel01();
//	}
	
//	@Override
//	public List<CommunitySupplyTypeEntity> getCommunitySupplyTypeLevel02(){
//		return communitySupplyDao.selectCommunitySupplyTypeLevel02();
//	}
	
	@Override
	public void autoFetchMljiaData() {
		List<GroupBuildingEntity> groupBuildingList = commonRoomService.selectGroupBuildingIsRegisted();
		for(GroupBuildingEntity tmpGroupBuildingEntity:groupBuildingList){
			BigInteger groupBuildingId = tmpGroupBuildingEntity.getId();
			autoFetchSupllyDataService.fetchSupply2DBForMljia(groupBuildingId, null, true);
		}
	}

	public List<CommunityPhoneTypeEntity> getCommunityPhoneTypeList(Integer version, BigInteger gbId) {
		return communitySupplyDao.getCommunityPhoneTypeList(version, gbId);
	}

}
