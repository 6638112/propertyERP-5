/**   
* Filename:    ExchangeService.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:26:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommCommunityService;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.exchange.constant.ExchangeConstant;
import com.cnfantasia.server.api.exchange.constant.ExchangeDict;
import com.cnfantasia.server.api.exchange.dao.IExchangeDao;
import com.cnfantasia.server.api.exchange.entity.ExchangeBaseEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeRelationGoalEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeSuccEntity;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.service.IPointService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.api.xanadu.service.IXanaduService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communityExchangeContent.dao.ICommunityExchangeContentBaseDao;
import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;
import com.cnfantasia.server.domainbase.communityExchangePic.dao.ICommunityExchangePicBaseDao;
import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;
import com.cnfantasia.server.domainbase.communityExchangeRelation.dao.ICommunityExchangeRelationBaseDao;
import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;
import com.cnfantasia.server.domainbase.communityExchangeWanted.dao.ICommunityExchangeWantedBaseDao;
import com.cnfantasia.server.domainbase.communityExchangeWanted.entity.CommunityExchangeWanted;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * Filename:    ExchangeService.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:26:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public class ExchangeService implements IExchangeService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IExchangeDao exchangeDao;
	public void setExchangeDao(IExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}

	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommunityExchangeContentBaseDao communityExchangeContentBaseDao;
	public void setCommunityExchangeContentBaseDao(ICommunityExchangeContentBaseDao communityExchangeContentBaseDao) {
		this.communityExchangeContentBaseDao = communityExchangeContentBaseDao;
	}
	private ICommunityExchangePicBaseDao communityExchangePicBaseDao;
	public void setCommunityExchangePicBaseDao(ICommunityExchangePicBaseDao communityExchangePicBaseDao) {
		this.communityExchangePicBaseDao = communityExchangePicBaseDao;
	}

	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommunityExchangeRelationBaseDao communityExchangeRelationBaseDao;
	public void setCommunityExchangeRelationBaseDao(ICommunityExchangeRelationBaseDao communityExchangeRelationBaseDao) {
		this.communityExchangeRelationBaseDao = communityExchangeRelationBaseDao;
	}
	
	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}
	
	private ICommunityExchangeWantedBaseDao communityExchangeWantedBaseDao;
	public void setCommunityExchangeWantedBaseDao(ICommunityExchangeWantedBaseDao communityExchangeWantedBaseDao) {
		this.communityExchangeWantedBaseDao = communityExchangeWantedBaseDao;
	}
	
	private IMsgpushService msgpushService;
	public void setMsgpushService(IMsgpushService msgpushService) {
		this.msgpushService = msgpushService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private ICommCommunityService commCommunityService;
	public void setCommCommunityService(ICommCommunityService commCommunityService) {
		this.commCommunityService = commCommunityService;
	}
	
	private IXanaduService xanaduService;
	public void setXanaduService(IXanaduService xanaduService) {
		this.xanaduService = xanaduService;
	}
	
	private IPointService pointService;
	public void setPointService(IPointService pointService) {
		this.pointService = pointService;
	}

	private BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		BigInteger groupBuildingId = null;
		if(xanaduService.getCurrStatus(userId)){
			groupBuildingId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}else{
			groupBuildingId = commonRoomService.getGroupBuildingIdByUserId(userId);
		}
		return groupBuildingId;
	}
	
	/**
	 * 附加换物关系相关信息
	 * @param userId 当前用户Id
	 * @param tmpGoal 换物关系信息实体
	 */
	private void appendExchangeRelationGoalEntityExtendData(BigInteger userId,ExchangeRelationGoalEntity tmpGoal){
		if(tmpGoal!=null){
			BigInteger exchRelationId = tmpGoal.getId();
			List<CommentsEntity> preCommentsEntityList = commentsService.getCommentsList(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_EXCHANGE_RELATION, exchRelationId, new PageModel(0, 2));
			Boolean isWantedAble = checkCanWanted(userId, exchRelationId);
//			Integer wantedCount;
//			Integer commentTotalCount;
//			Boolean isSupported;
//			Integer totalSupportCount;
//			Boolean isCollected;
			tmpGoal.setPreCommentsEntityList(preCommentsEntityList);
			tmpGoal.setIsWantedAble(isWantedAble);
		}
	}
	
	/**
	 * 如果有成功的则附加对应的成功信息
	 * @param userId
	 * @param exchangeBaseEntityList
	 */
	private void appendExchangeEntityListData(BigInteger userId,List<ExchangeBaseEntity> exchangeBaseEntityList){
		for(ExchangeBaseEntity tmpEntity:exchangeBaseEntityList){
			if(ExchangeDict.ExchangeContent_Status.Finished.compareTo(tmpEntity.getStatus())==0){//已成功的
				ExchangeRelationGoalEntity succExchRelaGoalEntity = getSubJoinContentListWithRelationSucc(userId, tmpEntity.getId());
				tmpEntity.setSuccExchRelaGoalEntity(succExchRelaGoalEntity);
			}
		}
	}
	
	/**
	 * 附加换物所需的扩展的详细信息
	 * @param userId 当前用户Id
	 * @param resExchangeEntity 换物信息
	 */
	private void appendExchangeEntityExtendData(BigInteger userId,ExchangeEntity resExchangeEntity){
		if(resExchangeEntity!=null){
			BigInteger exchangeContentId = resExchangeEntity.getId();
			Boolean isDeleteAble = false;
			Boolean isEditAble = false;
			try {
				validateUpdLaunchContent(userId,exchangeContentId);
				isDeleteAble = true;
				isEditAble = true;
			} catch (Exception e) {
				if(e instanceof FocRuntimeException){
					
				}else{
					logger.debug("validateUpdLaunchContent cause exception,userId="+userId+",exchangeContentId="+exchangeContentId, e);
				}
			}
			CommentsEntity firstComment = commentsService.getFirstComments(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_EXCHANGE, exchangeContentId);
//			Integer commentTotalCount;
//			Boolean isSupported;
//			Integer totalSupportCount;
			List<ExchangeRelationGoalEntity> subJoinList = exchangeDao.selectSubJoinContentListWithRelation(userId, resExchangeEntity.getId());
			if(subJoinList!=null){
				for(ExchangeRelationGoalEntity tmpGoal:subJoinList){
					appendExchangeRelationGoalEntityExtendData(userId,tmpGoal);
				}
			}
			resExchangeEntity.setExchangeRelationGoalEntityList(subJoinList);
			resExchangeEntity.setIsDeleteAble(isDeleteAble);
			resExchangeEntity.setIsEditAble(isEditAble);
			resExchangeEntity.setFirstComment(firstComment);
		}
	}
	
	@Override
	public ExchangeEntity getMostHotContent(BigInteger userId) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		
		ExchangeEntity resExchangeEntity = exchangeDao.selectMostHotContent(userId, groupBuildingId);
		appendExchangeEntityExtendData(userId, resExchangeEntity);
		return resExchangeEntity;
	}

	@Override
	public List<ExchangeBaseEntity> getHotContentList(BigInteger userId, PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		ExchangeEntity mostHotEntity = null;//先查询最热的
		if(pageModel.begin<=1){
			mostHotEntity = exchangeDao.selectMostHotContent(userId, groupBuildingId);//先查询最热的
		}
		List<ExchangeBaseEntity> tmpList =  exchangeDao.selectHotContentList(userId, groupBuildingId, pageModel);//再查询余下的
		List<ExchangeBaseEntity> resList = new ArrayList<ExchangeBaseEntity>();
		if(mostHotEntity!=null){
			resList.add(mostHotEntity);
		}
		if(tmpList!=null){
			for(ExchangeBaseEntity tmpEntity:tmpList){
				if(mostHotEntity!=null&&mostHotEntity.getId().compareTo(tmpEntity.getId())==0){
					continue;
				}
				resList.add(tmpEntity);
			}
		}
		appendExchangeEntityListData(userId, resList);
		return resList;
	}
	
	/**
	 * 保存换一换图片信息
	 */
	private void uploadExchangePic(BigInteger userId,List<RequestFileEntity> picList,BigInteger contentId){
			if(picList!=null&&picList.size()>0){
				String pinyipinPicBase = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_EXCHANGE_PIC_BASEPATH);
				List<String> picUrlList = new ArrayList<String>();
				for(int i=0;i<picList.size();i++){//上传图片
					RequestFileEntity requestFile = picList.get(i);
					//生成文件名 userId+time+index+3位随机数
					String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
							.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
							.append(".").append(requestFile.getFileType()).toString();
					picUrlList.add(resFileName);
					String serverPath = pinyipinPicBase+resFileName;
					try {
						fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					} catch (IOException e) {
						logger.info("uploadExchangePic upload exchange file cause exception:"+e.getMessage(), e);
						throw new BusinessRuntimeException("ExchangeService.uploadExchangePic.uploadFile.error",new Object[]{requestFile.getFileName()});
					}
					//增加生成小图的任务处理
					FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.Exchange,fileServerService.getFileServierUploadBasePath(),pinyipinPicBase, resFileName, requestFile.getFileContent()));
					new Thread(task).start();
				}
				List<CommunityExchangePic> communityExchangePicAddList = new ArrayList<CommunityExchangePic>();
				List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_exchange_pic,picUrlList.size());
				for(int i=0;i<picUrlList.size();i++){
					String picUrl = picUrlList.get(i);
					BigInteger picAddId = picAddIdList.get(i);
					CommunityExchangePic communityExchangePic = new CommunityExchangePic();
					communityExchangePic.setDesc(null);
					communityExchangePic.setId(picAddId);
					communityExchangePic.setName(null);
					communityExchangePic.settCommunityExchangeContentFId(contentId);
					communityExchangePic.setUploader(userId);
					communityExchangePic.setPicUrl(picUrl);
					communityExchangePicAddList.add(communityExchangePic);
				}
				int res = communityExchangePicBaseDao.insertCommunityExchangePicBatch(communityExchangePicAddList);
				if(res!=communityExchangePicAddList.size()){
					throw new BusinessRuntimeException("ExchangeService.uploadExchangePic.insertCommunityExchangePicBatch.failed");
				}
			}
	}
	
	@Override
	public void confirmLaunchContent(BigInteger userId,String title, String desc, List<RequestFileEntity> picEntityList) {
		commCommunityService.checkXanaduOperation(userId);
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		String nowTime = dualDao.getNowTime();
		BigInteger communityExchangeContentAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_exchange_content);
		//1.新增换物基本信息
		{
			CommunityExchangeContent communityExchangeContent = new CommunityExchangeContent();
			communityExchangeContent.setCreateTime(nowTime);
			communityExchangeContent.setDesc(desc);
			communityExchangeContent.setId(communityExchangeContentAddId);
			communityExchangeContent.setStatus(ExchangeDict.ExchangeContent_Status.ExchangeAble);//默认状态 可交换
			communityExchangeContent.settGroupBuildingFId(groupBuildingId);
			communityExchangeContent.setTitle(title);
			communityExchangeContent.setUserId(userId);
			communityExchangeContent.setCreateType(ExchangeDict.ExchangeContent_CreateType.Launch);//创建方式：发起
			int res = communityExchangeContentBaseDao.insertCommunityExchangeContent(communityExchangeContent);
			if(res<=0){
				throw new BusinessRuntimeException("ExchangeService.confirmLaunchContent.insert.failed");
			}
		}
		//2.保存图片信息
		uploadExchangePic(userId, picEntityList, communityExchangeContentAddId);
		pointService.checkAndAddPoint(userId, PointConstant.PointSourceType.PublishExchange);
	}
	
	private void validateUpdLaunchContent(BigInteger userId,BigInteger updContentId){
		//只有创建者才可以删除
		CommunityExchangeContent tmpCommunityExchangeContent = communityExchangeContentBaseDao.selectCommunityExchangeContentBySeqId(updContentId);
		if(userId==null||tmpCommunityExchangeContent.getUserId().compareTo(userId)!=0){
			throw new BusinessRuntimeException("ExchangeService.updLaunchConent.notOwn.error");
		}
		Integer joinCount = exchangeDao.selectJoinTotalCount(updContentId);
		if(joinCount!=null&&joinCount>0){//已有用户参与换物则不可删除
			throw new BusinessRuntimeException("ExchangeService.validateUpdLaunchContent.hasJoined.error");
		}
	}
	
	@Override
	public void updateLaunchContent(BigInteger userId, BigInteger updContentId, String title, String desc,
			List<RequestFileEntity> picEntityList,List<BigInteger> delPicIds) {
		//校验是否可编辑
		validateUpdLaunchContent(userId,updContentId);
		//1.更新换物信息
		{
			CommunityExchangeContent exchangeContent = new CommunityExchangeContent();
			exchangeContent.setId(updContentId);
			exchangeContent.setTitle(title);
			exchangeContent.setDesc(desc);
			int res = communityExchangeContentBaseDao.updateCommunityExchangeContent(exchangeContent);
			if(res<=0){
				throw new BusinessRuntimeException("ExchangeService.updLaunchConent.updateCommunityExchangeContent.failed");
			}
		}
		//2.保存图片信息
		uploadExchangePic(userId, picEntityList, updContentId);
		//3.删除图片Ids
		if(delPicIds!=null&&delPicIds.size()>0){
			communityExchangePicBaseDao.deleteCommunityExchangePicLogicBatch(delPicIds);
		}
	}
	
	@Override
	public void delLaunchContent(BigInteger userId, BigInteger contentId) {
		//校验是否可编辑
		validateUpdLaunchContent(userId,contentId);
		//若可删除则执行删除
		Integer delCount = exchangeDao.deleteLaunchContent(userId, contentId);
		if(delCount==null||delCount<=0){
			throw new BusinessRuntimeException("ExchangeService.delLaunchContent.delCount.failed");
		}
	}
	
	@Override
	public List<ExchangeBaseEntity> getLaunchContentList(BigInteger userId, PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		List<ExchangeBaseEntity> resList = exchangeDao.selectLaunchContentList(userId, groupBuildingId, pageModel);
		appendExchangeEntityListData(userId, resList);
		return resList;
	}
	
	@Override
	public void confirmJoinContent(BigInteger userId,BigInteger goalContentId,String title,String desc,List<RequestFileEntity> picEntityList){
		commCommunityService.checkXanaduOperation(userId);
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		String nowTime = dualDao.getNowTime();
		BigInteger communityExchangeContentAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_exchange_content);
		//1.新增换物基本信息
		{
			CommunityExchangeContent communityExchangeContent = new CommunityExchangeContent();
			communityExchangeContent.setCreateTime(nowTime);
			communityExchangeContent.setDesc(desc);
			communityExchangeContent.setId(communityExchangeContentAddId);
			communityExchangeContent.setStatus(ExchangeDict.ExchangeContent_Status.IDLE);//默认状态,闲置中,不让其他用户参与换
			communityExchangeContent.settGroupBuildingFId(groupBuildingId);
			communityExchangeContent.setTitle(title);
			communityExchangeContent.setUserId(userId);
			communityExchangeContent.setCreateType(ExchangeDict.ExchangeContent_CreateType.JOIN);//创建方式：参与
			int res = communityExchangeContentBaseDao.insertCommunityExchangeContent(communityExchangeContent);
			if(res<=0){
				throw new BusinessRuntimeException("ExchangeService.confirmJoinContent.insert.failed");
			}
		}
		//2.保持图片信息
		uploadExchangePic(userId, picEntityList, communityExchangeContentAddId);
		//3.保存关系信息
		{
			BigInteger communityExchangeRelationAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_exchange_relation);
			CommunityExchangeRelation communityExchangeRelation = new CommunityExchangeRelation();
			communityExchangeRelation.setId(communityExchangeRelationAddId);
			communityExchangeRelation.setCreateTime(nowTime);
			communityExchangeRelation.setConfirmTime(null);
			communityExchangeRelation.setCreaterId(userId);
			communityExchangeRelation.setLaunchExchgId(goalContentId);//使用goalContentId
			communityExchangeRelation.setJoinExchgId(communityExchangeContentAddId);//使用communityExchangeContentAddId
			communityExchangeRelation.setStatus(ExchangeDict.ExchangeRelation_Status.ToDo);//"1":"待交换"
			int res = communityExchangeRelationBaseDao.insertCommunityExchangeRelation(communityExchangeRelation);
			if(res<=0){
				throw new BusinessRuntimeException("ExchangeService.confirmJoinContent.insertRelation.failed");
			}
		}
		// {0}有物品想跟您换，去看看是否中意？
		ExchangeBaseEntity tmpEntity = getExchangeContentDetailBase(null, goalContentId);
		addMsgPushInfo(tmpEntity.getUserId(), "exchange.launch.haveJoin", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.ExchangePush,tmpEntity.getId());
	}
	
	@Override
	public List<ExchangeBaseEntity> getJoinContentList(BigInteger userId, PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		List<ExchangeBaseEntity> resList = exchangeDao.selectJoinContentList(userId, groupBuildingId, pageModel);
		appendExchangeEntityListData(userId, resList);
		return resList;
	}
	
	@Override
	public List<ExchangeBaseEntity> getLaunchAndJoinContentList(BigInteger userId, PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		List<ExchangeBaseEntity> resList = exchangeDao.selectLaunchAndJoinContentList(userId, groupBuildingId, pageModel);
		appendExchangeEntityListData(userId, resList);
		return resList;
	}
	
	@Override
	public ExchangeEntity getExchangeContentDetail(BigInteger userId, BigInteger exchangeContentId) {
		ExchangeEntity resExchangeEntity = exchangeDao.selectExchangeContentDetail(userId, exchangeContentId);
		appendExchangeEntityExtendData(userId, resExchangeEntity);
		return resExchangeEntity;
	}
	@Override
	public ExchangeBaseEntity getExchangeContentDetailBase(BigInteger userId, BigInteger exchangeContentId) {
		ExchangeBaseEntity resExchangeEntity = exchangeDao.selectExchangeContentDetail(userId, exchangeContentId);
		return resExchangeEntity;
	}

	@Override
	public List<ExchangeBaseEntity> getSubJoinContentList(BigInteger userId, BigInteger exchangeContentId) {
		return exchangeDao.selectSubJoinContentList(userId, exchangeContentId);
	}
	
	@Override
	public ExchangeRelationGoalEntity getSubJoinContentListWithRelationSucc(BigInteger userId,
			BigInteger exchangeContentId) {
		return exchangeDao.selectSubJoinContentWithRelationSucc(userId, exchangeContentId);
	}
	
	@Override
	public ExchangeSuccEntity confirmExchangeRelation(BigInteger userId, BigInteger exchangeRelationId) {
		Integer res = exchangeDao.updateExchangeRelation2Finished(userId, exchangeRelationId);
		if(res==null||res<=0){
			throw new BusinessRuntimeException("ExchangeService.confirmExchangeRelation.updateRelation.failed");
		}
		//关系信息
		CommunityExchangeRelation exchangeRelation = communityExchangeRelationBaseDao.selectCommunityExchangeRelationBySeqId(exchangeRelationId);
		//发起的换物
//		ExchangeBaseEntity launchExchgContent = getExchangeContentDetailBase(userId, exchangeRelation.getLaunchExchgId());
		ExchangeEntity launchExchgContent = getExchangeContentDetail(userId, exchangeRelation.getLaunchExchgId());
		//参与的换物
		ExchangeBaseEntity joinExchgContent = getExchangeContentDetailBase(userId, exchangeRelation.getJoinExchgId());
		{
			//syl-add-2014-11-23 17:49:27 增加消息推送
			//昵称（{0}）确定跟您换，快去看看吧！
			addMsgPushInfo(joinExchgContent.getUserId(), "exchange.join.selected", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.ExchangePush,launchExchgContent.getId());
			//参与换物的列表
			List<BigInteger> joinFailedUserList = new ArrayList<BigInteger>();
			{
				List<ExchangeRelationGoalEntity>  exchangeRelationGoalEntityList = launchExchgContent.getExchangeRelationGoalEntityList();
				if(exchangeRelationGoalEntityList!=null&&exchangeRelationGoalEntityList.size()>0){
					for(ExchangeRelationGoalEntity tmpExchangeRelationGoalEntity:exchangeRelationGoalEntityList){
						if(joinExchgContent.getId().compareTo(tmpExchangeRelationGoalEntity.getJoinExchgId())!=0){//排除成功的换物
							BigInteger joinUserId = tmpExchangeRelationGoalEntity.getGoalExchangeEntity().getUserId();
							joinFailedUserList.add(joinUserId);
						}
					}
				}
			}
			if(joinFailedUserList!=null&&joinFailedUserList.size()>0){
				//太遗憾了，昵称（{0})换了别人的物品，赶紧去瞧瞧吧！
				addMsgPushInfo(joinFailedUserList, "exchange.join.failed", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.ExchangePush,launchExchgContent.getId());
			}
		}
		//换物成功的实体类
		ExchangeSuccEntity resExchangeSuccEntity = new ExchangeSuccEntity(launchExchgContent, joinExchgContent); 
		return resExchangeSuccEntity;
	}
	
	@Override
	public Boolean checkCanWanted(BigInteger userId, BigInteger exchangeRelationId) {
		return exchangeDao.selectCanWantedBoolean(userId, exchangeRelationId);
	}

	@Override
	public Integer doRelationWanted(BigInteger userId, BigInteger exchangeRelationId) {
		Boolean canWanted = checkCanWanted(userId, exchangeRelationId);
		if(canWanted==null||canWanted==false){
			throw new BusinessRuntimeException("ExchangeService.doRelationWanted.checkCanWanted.failed");
		}
		{//新增跪求换的记录
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_exchange_wanted);
			String nowTime = dualDao.getNowTime();
			CommunityExchangeWanted communityExchangeWanted = new CommunityExchangeWanted();
			communityExchangeWanted.setCreateTime(nowTime);
			communityExchangeWanted.setId(addId);
			communityExchangeWanted.settCommunityExchangeRelationFId(exchangeRelationId);
			communityExchangeWanted.setUserId(userId);
			int res = communityExchangeWantedBaseDao.insertCommunityExchangeWanted(communityExchangeWanted);;
			if(res<=0){
				throw new BusinessRuntimeException("ExchangeService.doRelationWanted.res.failed");
			}
		}
		return exchangeDao.selectCanWantedCount(userId, exchangeRelationId);
	}
	
	private Message packingMessage(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType){
		//新增一条消息
		Message messageAdd = null;
		{//组装消息信息
			messageAdd = new Message();
			String content = MessageSourceUtil.getMessage(msgInfoKey, msgParams);
			String nowTime = dualDao.getNowTime();
			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
			messageAdd.setAndroidTargetView(null);
			messageAdd.setContent(content);
			messageAdd.setCreater(userId);
			messageAdd.setId(addId);
			messageAdd.setIosTargetView(null);
			messageAdd.setMsggroupFid(null);
			messageAdd.setPicUrl(null);
			messageAdd.setTime(nowTime);
			messageAdd.setTitle(ExchangeConstant.Msgpush_Title);
//			messageAdd.setType(NoticeDict.Message_Type.ExchangePush);
			messageAdd.setType(msgType);
		}
		return messageAdd;
	}
	@Override
	public BigInteger addMsgPushInfo(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType,BigInteger detailId) {
		//新增一条消息
		Message messageAdd = packingMessage(userId, msgInfoKey, msgParams,msgType);
		//设定消息内容，
		String expiryTime = null;//失效时间为空
		List<MessageParameter> paramList = new ArrayList<MessageParameter>();
		{
			MessageParameter tmpMessageParameter = new MessageParameter();
			tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
			tmpMessageParameter.setKey("id");
			tmpMessageParameter.settMessageFId(messageAdd.getId());
			tmpMessageParameter.setValue(detailId==null?null:detailId.toString());
			paramList.add(tmpMessageParameter);
		}
		BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		CommUserDataEntity commUserDataEntity = new CommUserDataEntity(userId, LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
		msgpushService.addMessage2Pool(commUserDataEntity, messageAdd, expiryTime,paramList);
		return messageAdd.getId();
	}
	
	@Override
	public BigInteger addMsgPushInfo(List<BigInteger> userIdList, String msgInfoKey, Object[] msgParams,Long msgType,BigInteger detailId) {
		//新增一条消息
		Message messageAdd = packingMessage(null, msgInfoKey, msgParams,msgType);
		//设定消息内容，
		String expiryTime = null;//失效时间为空
		List<MessageParameter> paramList = new ArrayList<MessageParameter>();
		{
			MessageParameter tmpMessageParameter = new MessageParameter();
			tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
			tmpMessageParameter.setKey("id");
			tmpMessageParameter.settMessageFId(messageAdd.getId());
			tmpMessageParameter.setValue(detailId==null?null:detailId.toString());
			paramList.add(tmpMessageParameter);
		}
		List<UserSimpleEntity> tmpUserList = commonRoomService.getUserListByUserIds(userIdList);
		if(tmpUserList!=null&&tmpUserList.size()>0){
			List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();
			for(UserSimpleEntity tmpUser:tmpUserList){
				BigInteger defaultRoomId = tmpUser.getDefaultRoomId();
				CommUserDataEntity commUserDataEntity = new CommUserDataEntity(tmpUser.getId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
				commUserDataEntityList.add(commUserDataEntity);
			}
			msgpushService.addMessage2Pool(commUserDataEntityList, messageAdd, expiryTime,paramList);
		}
		return messageAdd.getId();
	}
	
//	@Override
//	public String fetchAllHotContentListLastUpdTime(BigInteger userId) {
//		return exchangeDao.selectAllHotContentListLastUpdTime(userId);
//	}
//
//	@Override
//	public String fetchAllLaunchAndJoinContentListLastUpdTime(BigInteger userId) {
//		return exchangeDao.selectAllLaunchAndJoinContentListLastUpdTime(userId);
//	}
	
//	@Override
	private Map<String,Object> exhcangeContent2Map(Long nowTimeLong,BigInteger currUserId,UserSimpleEntity exchangeContentOwner,CommunityExchangeContent exchangeContent
			,List<CommunityExchangePic> exchangePicList,List<ExchangeRelationGoalEntity>  exchangeRelationGoalEntityList,Integer relationCount,Integer userBelong
			,ExchangeRelationGoalEntity succExchRelaGoalEntity){
		if(nowTimeLong==null){
			String nowTime = dualDao.getNowTime();
			nowTimeLong = DateUtil.timeToLong(nowTime);
		}
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", exchangeContent.getId());
		resMap.put("title", exchangeContent.getTitle());
		resMap.put("desc", exchangeContent.getDesc());
		resMap.put("status", exchangeContent.getStatus());
		resMap.put("createTime", exchangeContent.getCreateTime());
		resMap.put("createTimeRelative",RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(exchangeContent.getCreateTime()),nowTimeLong));
		resMap.put("userId", exchangeContent.getUserId());
		resMap.put("groupBuildingId", exchangeContent.gettGroupBuildingFId());
		if(exchangeContentOwner!=null){//换物拥有者
			Map<String,Object> tmpMap = commEntityConvertService.baseUser2Map(exchangeContentOwner);
			resMap.put("ext_contentOwner", tmpMap);
		}
		if(exchangePicList!=null){//换物图片列表
			List<Map<String,Object>> picList = new ArrayList<Map<String,Object>>();
			if(exchangePicList.size()>0){
				for(CommunityExchangePic tmpExchangePic:exchangePicList){
					Map<String,Object> tmpMap = exchangePic2Map(tmpExchangePic);
					picList.add(tmpMap);
				}
				resMap.put("ext_picList", picList);
			}
		}
		{//默认图片
			String defaultIcon = null;
			if(exchangePicList!=null&&exchangePicList.size()>0){
				CommunityExchangePic defaultExchangePic = exchangePicList.get(0);
				String pinyipinPicBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_EXCHANGE_PIC_BASEPATH);
				defaultIcon = StringUtils.isEmpty(defaultExchangePic.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(pinyipinPicBasePath+defaultExchangePic.getPicUrl(), defaultExchangePic);
			}
			resMap.put("ext_defaultIcon", defaultIcon);
		}
		if(userBelong!=null){
			resMap.put("ext_userBelong", userBelong);
		}
//		{//用户是发起者还是参与者
//			Integer userBelong = null;
//			if(currUserId!=null&&currUserId.compareTo(exchangeContent.getUserId())==0){
//				userBelong = ExchangeDict.ExchangeContent_UserBelong.Launcher;//发起者
//			}else{
//				boolean isJoiner = false;
//				if(currUserId!=null){
//					if(exchangeRelationGoalEntityList!=null){
//						for(ExchangeRelationGoalEntity tmpRelation:exchangeRelationGoalEntityList){
//							if(tmpRelation.getCreaterId().compareTo(currUserId)==0){
//								isJoiner = true;
//								break;
//							}
//						}
//					}
//				}
//				if(isJoiner){
//					userBelong = ExchangeDict.ExchangeContent_UserBelong.Joiner;//参与者
//				}else{
//					userBelong = ExchangeDict.ExchangeContent_UserBelong.None;//都不是
//				}
//			}
//			resMap.put("ext_userBelong", userBelong);
//		}
		{//已参与换的数量
			Integer relationCountTmp = null;
			if(relationCount!=null){//率先使用传入的relationCount
				relationCountTmp = relationCount;
			}else{
				if(exchangeRelationGoalEntityList!=null){
					relationCountTmp = exchangeRelationGoalEntityList.size();
				}
			}
			if(relationCountTmp!=null){
				resMap.put("ext_relationCount", relationCountTmp);
			}
		}
		ExchangeRelationGoalEntity succExchRelaGoalEntityTmp = null;
		List<Map<String,Object>> resOtherList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> resCurrUserList = new ArrayList<Map<String,Object>>();
		if(exchangeRelationGoalEntityList!=null){//参与置换的物品列表
			for(ExchangeRelationGoalEntity tmpEntity:exchangeRelationGoalEntityList){
				if(ExchangeDict.ExchangeRelation_Status.Finished.compareTo(tmpEntity.getStatus())==0){//已成功的
					if(succExchRelaGoalEntityTmp!=null){//确保只有一个换物成功的,否则抛出异常
						throw new BusinessRuntimeException("exchange.content2Map.multiSuccRelationGoal.error");
					}
					succExchRelaGoalEntityTmp = tmpEntity;
				}else if(currUserId!=null&&tmpEntity.getGoalExchangeEntity()!=null
						&&tmpEntity.getGoalExchangeEntity().getUserId().compareTo(currUserId)==0){//当前用户的
					Map<String,Object> tmpMap = exchangeRelationGoalEntity2Map(nowTimeLong,currUserId, tmpEntity,tmpEntity.getGoalExchangeEntity(),tmpEntity.getPreCommentsEntityList(),tmpEntity.getCommentTotalCount()
							,tmpEntity.getWantedCount(),tmpEntity.getIsWantedAble(),tmpEntity.getIsSupported(),tmpEntity.getTotalSupportCount());
					resCurrUserList.add(tmpMap);
				}else{
					Map<String,Object> tmpMap = exchangeRelationGoalEntity2Map(nowTimeLong,currUserId, tmpEntity,tmpEntity.getGoalExchangeEntity(),tmpEntity.getPreCommentsEntityList(),tmpEntity.getCommentTotalCount()
							,tmpEntity.getWantedCount(),tmpEntity.getIsWantedAble(),tmpEntity.getIsSupported(),tmpEntity.getTotalSupportCount());
					resOtherList.add(tmpMap);
				}
			}
		}
		//成功的换物
		if(succExchRelaGoalEntity!=null){//率先使用传入的成功的换物信息
			succExchRelaGoalEntityTmp = succExchRelaGoalEntity;
		}
		if(succExchRelaGoalEntityTmp!=null){
			resMap.put("ext_exchangeRelationSucc", exchangeRelationGoalEntity2Map(nowTimeLong,currUserId, succExchRelaGoalEntityTmp,succExchRelaGoalEntityTmp.getGoalExchangeEntity(),succExchRelaGoalEntityTmp.getPreCommentsEntityList(),succExchRelaGoalEntityTmp.getCommentTotalCount()
					,succExchRelaGoalEntityTmp.getWantedCount(),succExchRelaGoalEntityTmp.getIsWantedAble(),succExchRelaGoalEntityTmp.getIsSupported(),succExchRelaGoalEntityTmp.getTotalSupportCount()));
		}
		//我的置换物品
		if(resCurrUserList!=null){resMap.put("ext_exchangeRelationListCurrUser", resCurrUserList);}
		//其它置换物品
		if(resOtherList!=null){
			resMap.put("ext_exchangeRelationList", resOtherList);
		}
		return resMap;
	}
	
	@Override
	public Map<String, Object> exhcangeContent2Map(Long nowTimeLong,BigInteger currUserId, UserSimpleEntity exchangeContentOwner,
			CommunityExchangeContent exchangeContent, List<CommunityExchangePic> exchangePicList,
			List<ExchangeRelationGoalEntity> exchangeRelationGoalEntityList,Integer relationCount,Integer userBelong
			,ExchangeRelationGoalEntity succExchRelaGoalEntity
			,Boolean isDeleteAble, Boolean isEditAble,
			CommentsEntity firstComment, Integer commentTotalCount, Boolean isSupported,Integer totalSupportCount
			,Boolean isCollected) {
		Map<String, Object> resMap = exhcangeContent2Map(nowTimeLong,currUserId, exchangeContentOwner, exchangeContent, exchangePicList, exchangeRelationGoalEntityList,relationCount,userBelong,succExchRelaGoalEntity);
		if(isDeleteAble!=null){resMap.put("ext_isDeleteAble", isDeleteAble);}
		if(isEditAble!=null){resMap.put("ext_isEditAble", isEditAble);}
		if(firstComment!=null){resMap.put("ext_firstComment", commEntityConvertService.comments2Map(firstComment,firstComment.getUserGroupBuilding(),firstComment.getUser(), firstComment.getNoticeUserList(), firstComment.getCommentsLabelList(),firstComment.getCommentsHasTCommentsPointsEntityList(),firstComment.getAvgTotalStarLevel()));}
		if(commentTotalCount!=null){resMap.put("ext_commentTotalCount", commentTotalCount);}
		if(isSupported!=null){resMap.put("ext_isSupported", isSupported);}
		if(totalSupportCount!=null){resMap.put("ext_totalSupportCount", totalSupportCount);}
		if(isCollected!=null){resMap.put("ext_isCollected", isCollected);}
		return resMap;
	}
	
	@Override
	public Map<String,Object> exchangeRelationGoalEntity2Map(Long nowTimeLong,BigInteger currUserId,CommunityExchangeRelation exchangeRelation,ExchangeBaseEntity goalExchangeEntity
			,List<CommentsEntity> preCommentsEntityList,Integer commentTotalCount,Integer wantedCount,Boolean isWantedAble,Boolean isSupported,Integer totalSupportCount){
		if(nowTimeLong==null){
			String nowTime = dualDao.getNowTime();
			nowTimeLong = DateUtil.timeToLong(nowTime);
		}
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", exchangeRelation.getId());//记录Id
		resMap.put("createrId", exchangeRelation.getCreaterId());//关系建立者Id
		resMap.put("createTime", exchangeRelation.getCreateTime());//换物关系建立时间
		resMap.put("createTimeRelative", RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(exchangeRelation.getCreateTime()),nowTimeLong));//换物关系建立时间 
		resMap.put("srcExchgId", exchangeRelation.getLaunchExchgId());//原始换物Id
		resMap.put("goalExchgId", exchangeRelation.getJoinExchgId());//目标换物Id
		resMap.put("status", exchangeRelation.getStatus());//交换状态 {"1":"待交换","2":"已交换"}
		if(exchangeRelation.getConfirmTime()!=null){
			resMap.put("confirmTime", exchangeRelation.getConfirmTime());//确认换时间
			resMap.put("confirmTimeRelative", RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(exchangeRelation.getConfirmTime()),nowTimeLong));//确认换时间 
		}
		{//目标换物详情
			resMap.put("ext_GoalExchangeEntity", exhcangeContent2Map(nowTimeLong,currUserId,goalExchangeEntity.getOwner(), goalExchangeEntity, goalExchangeEntity.getExchangePicList(),null,goalExchangeEntity.getRelationCount(),goalExchangeEntity.getUserBelong(),goalExchangeEntity.getSuccExchRelaGoalEntity()));
		}
		if(wantedCount!=null){resMap.put("ext_wantedCount", wantedCount);}//跪求换数量
		if(isWantedAble!=null){resMap.put("ext_isWantedAble", isWantedAble);}//是否可以跪求换
		if(preCommentsEntityList!=null){//前几个评论信息
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			for(CommentsEntity tmpComment:preCommentsEntityList){
				Map<String,Object> tmpMap = commEntityConvertService.comments2Map(tmpComment,tmpComment.getUserGroupBuilding(),tmpComment.getUser(), tmpComment.getNoticeUserList(), tmpComment.getCommentsLabelList(),tmpComment.getCommentsHasTCommentsPointsEntityList(),tmpComment.getAvgTotalStarLevel());
				tmpList.add(tmpMap);
			}
			resMap.put("ext_preCommentsEntityList", tmpList);
		}
		if(commentTotalCount!=null){resMap.put("ext_commentTotalCount", commentTotalCount);}
		if(isSupported!=null){resMap.put("ext_isSupported", isSupported);}
		if(totalSupportCount!=null){resMap.put("ext_totalSupportCount", totalSupportCount);}
		return resMap;
	}
	
	@Override
	public Map<String,Object> exchangePic2Map(CommunityExchangePic exchangePic){
		String exchangePicBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_EXCHANGE_PIC_BASEPATH);
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", exchangePic.getId());
		resMap.put("name", exchangePic.getName());
		resMap.put("desc", exchangePic.getDesc());
		resMap.put("picUrl", StringUtils.isEmpty(exchangePic.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(exchangePicBasePath+exchangePic.getPicUrl(), exchangePic));
		resMap.put("exchangeContentId", exchangePic.gettCommunityExchangeContentFId());
		resMap.put("uploader", exchangePic.getUploader());
		return resMap;
	}
	@Override
	public Map<String,Object> exhcangeContent2Map(Long nowTimeLong,BigInteger userId,ExchangeEntity exchangeEntity){
		if(exchangeEntity==null){return null;}
		Map<String,Object> resMap = exhcangeContent2Map(nowTimeLong,userId,exchangeEntity.getOwner(), exchangeEntity, exchangeEntity.getExchangePicList(), exchangeEntity.getExchangeRelationGoalEntityList(),exchangeEntity.getRelationCount(),exchangeEntity.getUserBelong(),exchangeEntity.getSuccExchRelaGoalEntity()
				,exchangeEntity.getIsDeleteAble(), exchangeEntity.getIsEditAble(), exchangeEntity.getFirstComment(), exchangeEntity.getCommentTotalCount(), exchangeEntity.getIsSupported(),exchangeEntity.getTotalSupportCount(),exchangeEntity.getIsCollected());
		return resMap;
	}
	@Override
	public Map<String,Object> exhcangeContent2Map(Long nowTimeLong,BigInteger userId,ExchangeBaseEntity exchangeEntity){
		if(exchangeEntity==null){return null;}
		Map<String,Object> resMap = exhcangeContent2Map(nowTimeLong,userId,exchangeEntity.getOwner(), exchangeEntity, exchangeEntity.getExchangePicList(), null,exchangeEntity.getRelationCount(),exchangeEntity.getUserBelong(),exchangeEntity.getSuccExchRelaGoalEntity()
				, exchangeEntity.getIsDeleteAble(), exchangeEntity.getIsEditAble(), exchangeEntity.getFirstComment()
				, exchangeEntity.getCommentTotalCount(), exchangeEntity.getIsSupported(),exchangeEntity.getTotalSupportCount(),exchangeEntity.getIsCollected());
		return resMap;
	}

	@Override
	public List<Map<String, Object>> exhcangeContent2MapList(BigInteger userId,
			List<ExchangeBaseEntity> exchangeEntityList) {
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(exchangeEntityList!=null){
			String nowTime = dualDao.getNowTime();
			Long nowTimeLong = DateUtil.timeToLong(nowTime);
			for(ExchangeBaseEntity tmpExchangeBaseEntity:exchangeEntityList){
				Map<String,Object> tmpMap = exhcangeContent2Map(nowTimeLong,userId, tmpExchangeBaseEntity);
				resList.add(tmpMap);
			}
		}
		return resList;
	}


}
