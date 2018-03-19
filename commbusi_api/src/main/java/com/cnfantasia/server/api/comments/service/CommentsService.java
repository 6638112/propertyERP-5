package com.cnfantasia.server.api.comments.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.room.service.GroupBuildingService;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.comments.constant.CommentsConstant;
import com.cnfantasia.server.api.comments.dao.ICommentsDao;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsAvgEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsParamEntity;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonGatherDataService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.comments.dao.ICommentsBaseDao;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.dao.ICommentsHasTCommentsLabelBaseDao;
import com.cnfantasia.server.domainbase.commentsHasTCommentsLabel.entity.CommentsHasTCommentsLabel;
import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.dao.ICommentsHasTCommentsPointsBaseDao;
import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;
import com.cnfantasia.server.domainbase.communityExchangeContent.dao.ICommunityExchangeContentBaseDao;
import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;
import com.cnfantasia.server.domainbase.communityPinyipinContent.dao.ICommunityPinyipinContentBaseDao;
import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;
import com.cnfantasia.server.domainbase.dredgeBill.dao.DredgeBillBaseDao;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.microblogContent.dao.IMicroblogContentBaseDao;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

import javax.annotation.Resource;
//import com.cnfantasia.server.api.comments.dao.ICommentsDao;
/**
 * 描述:(评论) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommentsService implements ICommentsService{
//	private ICommentsBaseDao commentsBaseDao;
//	public void setCommentsBaseDao(ICommentsBaseDao commentsBaseDao) {
//		this.commentsBaseDao = commentsBaseDao;
//	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommentsDao commentsDao;
	public void setCommentsDao(ICommentsDao commentsDao) {
		this.commentsDao = commentsDao;
	}
	
	private ICommentsBaseDao commentsBaseDao;
	public void setCommentsBaseDao(ICommentsBaseDao commentsBaseDao) {
		this.commentsBaseDao = commentsBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ICommentsHasTCommentsLabelBaseDao commentsHasTCommentsLabelBaseDao;
	public void setCommentsHasTCommentsLabelBaseDao(ICommentsHasTCommentsLabelBaseDao commentsHasTCommentsLabelBaseDao) {
		this.commentsHasTCommentsLabelBaseDao = commentsHasTCommentsLabelBaseDao;
	}
	
	private IMsgpushService msgpushService;
	public void setMsgpushService(IMsgpushService msgpushService) {
		this.msgpushService = msgpushService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private IMicroblogContentBaseDao microblogContentBaseDao;
	public void setMicroblogContentBaseDao(IMicroblogContentBaseDao microblogContentBaseDao) {
		this.microblogContentBaseDao = microblogContentBaseDao;
	}
	
	private ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao;
	public void setCommunityPinyipinContentBaseDao(ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao) {
		this.communityPinyipinContentBaseDao = communityPinyipinContentBaseDao;
	}
	
	private ICommunityExchangeContentBaseDao communityExchangeContentBaseDao;
	public void setCommunityExchangeContentBaseDao(ICommunityExchangeContentBaseDao communityExchangeContentBaseDao) {
		this.communityExchangeContentBaseDao = communityExchangeContentBaseDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ICommentsHasTCommentsPointsBaseDao commentsHasTCommentsPointsBaseDao;
	public void setCommentsHasTCommentsPointsBaseDao(ICommentsHasTCommentsPointsBaseDao commentsHasTCommentsPointsBaseDao) {
		this.commentsHasTCommentsPointsBaseDao = commentsHasTCommentsPointsBaseDao;
	}
	
	private ICommonGatherDataService commonGatherDataService;
	public void setCommonGatherDataService(ICommonGatherDataService commonGatherDataService) {
		this.commonGatherDataService = commonGatherDataService;
	}

	@Resource
	private IAddressOperationService addressOperationService;

	@Override
	public CommentsEntity getFirstComentDetail(BigInteger targetId, Integer targetType) {
		CommentsEntity resCommentsEntity = commentsDao.selectFirstComentDetail(targetId, targetType);
		appendAvgStarLevel(resCommentsEntity);
		return resCommentsEntity;
	}
	
	@Override
	public void postComments(Integer goalType, BigInteger goalId, BigInteger goalId2nd, String txtContent, BigInteger userId,
			List<BigInteger> labelIds, Double starLevel, List<CommentsPointsParamEntity> pointsList) {
		BigInteger commentsId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comments);
		String nowTime = dualDao.getNowTime();
		Comments commentsAdd = new Comments();
		commentsAdd.setContent(txtContent);
		commentsAdd.setId(commentsId);
		if(starLevel!=null&&starLevel>=0){
			commentsAdd.setLevel(Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(starLevel)));
		}
		commentsAdd.setTargetId(goalId);
		commentsAdd.setTargetId2nd(goalId2nd);
		commentsAdd.setTargetType(goalType);
		commentsAdd.setTime(nowTime);
		commentsAdd.setUserId(userId);
		try {
			int res = commentsBaseDao.insertComments(commentsAdd);
			if(res<=0){
				throw new BusinessRuntimeException("CommentsService.postComments.insertComments.failed");
			}
		} catch (Exception e) {
			throw new BusinessRuntimeException("CommentsService.postComments.insertComments.other.failed",e);
		}
		//设置评论的评分项信息
		if(pointsList!=null&&pointsList.size()>0){
			List<BigInteger> addIdList =  uuidManager.getNextUuidBigInteger(SEQConstants.t_comments_has_t_comments_points, pointsList.size());
			List<CommentsHasTCommentsPoints> toAddList = new ArrayList<CommentsHasTCommentsPoints>();
			for(int i=0;i<pointsList.size();i++){
				CommentsPointsParamEntity currPoint = pointsList.get(i);
				Double pointsValue = currPoint.getPointsValue();
				BigInteger tCommentsPointsFId = currPoint.getCommentsPointsId();
				BigInteger commentsHasTCommentsPointsAddId = addIdList.get(i);
				CommentsHasTCommentsPoints commentsHasTCommentsPointsAdd = new CommentsHasTCommentsPoints();
				commentsHasTCommentsPointsAdd.setId(commentsHasTCommentsPointsAddId);
				commentsHasTCommentsPointsAdd.settCommentsFId(commentsId);
				commentsHasTCommentsPointsAdd.settCommentsPointsFId(tCommentsPointsFId);
				commentsHasTCommentsPointsAdd.setValue(pointsValue);
				toAddList.add(commentsHasTCommentsPointsAdd);
			}
			//批量新增评论所包含的评分项信息
			commentsHasTCommentsPointsBaseDao.insertCommentsHasTCommentsPointsBatch(toAddList);
		}
		
		if(goalType.intValue() == 122 && goalId2nd !=null){//维修单的评价 
			// goalId:'${dataValue.masterId}', goalId2nd:'${dataValue.id}',
			DredgeBillBaseDao dredgeBillBaseDao = (DredgeBillBaseDao) CnfantasiaCommbusiUtil.getBeanManager("dredgeBillBaseDao");
			DredgeBill dredgeBill = dredgeBillBaseDao.selectDredgeBillBySeqId(goalId2nd);
			if (dredgeBill.getStatus().intValue() == DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment) {
				DredgeBill db = new DredgeBill();
				db.setId(goalId2nd);
				db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment);
				dredgeBillBaseDao.updateDredgeBill(db);
			}
		}

		//设置评论的标签信息
		if(labelIds!=null&&labelIds.size()>0){
			List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_comments_has_t_comments_label, labelIds.size());
			List<CommentsHasTCommentsLabel> toAddList = new ArrayList<CommentsHasTCommentsLabel>();
			for(int i=0;i<labelIds.size();i++){
				BigInteger tCommentsLabelFId = labelIds.get(i);
				BigInteger commentsHasTCommentsLabelAddId = addIdList.get(i);
				CommentsHasTCommentsLabel commentsHasTCommentsLabel = new CommentsHasTCommentsLabel();
				commentsHasTCommentsLabel.setId(commentsHasTCommentsLabelAddId);
				commentsHasTCommentsLabel.settCommentsFId(commentsId);
				commentsHasTCommentsLabel.settCommentsLabelFId(tCommentsLabelFId);
				toAddList.add(commentsHasTCommentsLabel);
			}
			//批量新增评论所包含的标签信息
			commentsHasTCommentsLabelBaseDao.insertCommentsHasTCommentsLabelBatch(toAddList);
		}
		{//消息推送
			
			if(CommonModuleDict.CommonModule_TargetType.T_MICRO_BLOG.compareTo(goalType)==0){//评论对象为微博
				MicroblogContent microblogContent = microblogContentBaseDao.selectMicroblogContentBySeqId(goalId);
				if(microblogContent!=null&&userId!=null&&userId.compareTo(microblogContent.getUserId())!=0){
					//{0}回复了您的唠嗑，去瞧瞧~
					Message toAddMessage = packingMessage(userId, "microblog.creater.haveReply", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.MicroBlogPush);
					List<MessageParameter> paramList = new ArrayList<MessageParameter>();
					{
						MessageParameter tmpMessageParameter = new MessageParameter();
						tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
						tmpMessageParameter.setKey("wbid");
						tmpMessageParameter.settMessageFId(toAddMessage.getId());
						tmpMessageParameter.setValue(goalId==null?null:goalId.toString());
						paramList.add(tmpMessageParameter);
					}
					BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(microblogContent.getUserId());
					CommUserDataEntity commUserDataEntity = new CommUserDataEntity(microblogContent.getUserId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
					msgpushService.addMessage2Pool(commUserDataEntity, toAddMessage, null,paramList);
				}
			}else if(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_PINYIPIN.compareTo(goalType)==0){//评论对象为拼单
				CommunityPinyipinContent communityPinyipinContent = communityPinyipinContentBaseDao.selectCommunityPinyipinContentBySeqId(goalId);
				if(communityPinyipinContent!=null&&userId!=null&&userId.compareTo(communityPinyipinContent.getUserId())!=0){
					//{0}评论了您的拼一拼，去瞧瞧~
					Message toAddMessage = packingMessage(userId, "pinyipin.launch.comment", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.Pinyipin_CommentList);
					List<MessageParameter> paramList = new ArrayList<MessageParameter>();
					{
						MessageParameter tmpMessageParameter = new MessageParameter();
						tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
						tmpMessageParameter.setKey("id");
						tmpMessageParameter.settMessageFId(toAddMessage.getId());
						tmpMessageParameter.setValue(goalId==null?null:goalId.toString());
						paramList.add(tmpMessageParameter);
					}
					BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(communityPinyipinContent.getUserId());
					CommUserDataEntity commUserDataEntity = new CommUserDataEntity(communityPinyipinContent.getUserId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
					msgpushService.addMessage2Pool(commUserDataEntity, toAddMessage, null,paramList);
				}
			}else if(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_EXCHANGE.compareTo(goalType)==0){//评论对象为换物
				CommunityExchangeContent communityExchangeContent = communityExchangeContentBaseDao.selectCommunityExchangeContentBySeqId(goalId);
				if(communityExchangeContent!=null&&userId!=null&&userId.compareTo(communityExchangeContent.getUserId())!=0){
					//有街坊评论了您的换一换，去瞧瞧~
					Message toAddMessage = packingMessage(userId, "exchange.launch.comment", null/*new Object[]{commonUserService.getUserShowNameById(userId)}*/,NoticeDict.Message_Type.Exchange_CommentList);
					List<MessageParameter> paramList = new ArrayList<MessageParameter>();
					{ 
						MessageParameter tmpMessageParameter = new MessageParameter();
						tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
						tmpMessageParameter.setKey("id");
						tmpMessageParameter.settMessageFId(toAddMessage.getId());
						tmpMessageParameter.setValue(goalId==null?null:goalId.toString());
						paramList.add(tmpMessageParameter);
					}
					BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(communityExchangeContent.getUserId());
					CommUserDataEntity commUserDataEntity = new CommUserDataEntity(communityExchangeContent.getUserId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
					msgpushService.addMessage2Pool(commUserDataEntity, toAddMessage, null,paramList);
				}
			}
		}
		commonGatherDataService.appendTotalCommentCount(goalType, goalId, 1);//syl-add-2015-7-1 17:25:57
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
			messageAdd.setTitle("有评论信息");
			messageAdd.setType(msgType);
		}
		return messageAdd;
	}
	
	@Override
	public List<CommentsEntity> getCommentsList(Integer goalType, BigInteger goalId, PageModel pageModel) {
		List<CommentsEntity> resList =  commentsDao.selectCommentsList(goalType, goalId, pageModel);
		appendAvgStarLevel(resList, goalType, goalId);
		return resList;
	}
	@Override
	public List<CommentsEntity> getCommentsList(Integer goalType, BigInteger goalId) {
		List<CommentsEntity> resList =  commentsDao.selectCommentsList(goalType, goalId);
		appendAvgStarLevel(resList, goalType, goalId);
		return resList;
	}
	
	@Override
	public CommentsEntity getFirstComments(Integer goalType, BigInteger goalId) {
		PageModel pageModel = new PageModel(0, 1);
		CommentsEntity resCommentsEntity = null;
		List<CommentsEntity> resList = getCommentsList(goalType, goalId, pageModel);
		if(resList!=null&&resList.size()>0){
			resCommentsEntity = resList.get(0);
		}
		appendAvgStarLevel(resCommentsEntity);
		return resCommentsEntity;
	}
	
	@Override
	public CommentsEntity getCommentsDetail(BigInteger commentId) {
		return commentsDao.selectCommentsDetail(commentId);
	}

	@Override
	public void delComments(BigInteger userId, BigInteger commentId) {
		int res = commentsDao.delCommentsLogic(userId, commentId);
		if(res<=0){
			throw new BusinessRuntimeException("CommentsService.postComments.delComments.failed");
		}
	}

	@Override
	public Double getCommentsAverageLevel(Integer goalType, BigInteger goalId) {
		Double avgLevel = commentsDao.selectCommentsAverageLevel(goalType, goalId);
//		if(avgLevel!=null){
//			avgLevel = Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(avgLevel));
//		}
		return avgLevel;
	}

	@Override
	public List<CommentsPointsAvgEntity> getCommentsPointsAverageLevel(Integer goalType, BigInteger goalId) {
		return commentsDao.selectCommentsPointsAverageLevel(goalType, goalId);
	}
	
	/**
	 * 附加总的平均评分
	 * @param resCommentsEntity
	 */
	private void appendAvgStarLevel(CommentsEntity resCommentsEntity){
		Double totalStarLevel = null;
		if(resCommentsEntity!=null){
			Integer goalType = resCommentsEntity.getTargetType();
			BigInteger goalId = resCommentsEntity.getTargetId();
			totalStarLevel = commentsDao.selectCommentsAverageLevel(goalType, goalId);
			resCommentsEntity.setAvgTotalStarLevel(totalStarLevel);
		}
	}
	
	/**
	 * 附加总的平均评分
	 * @param resCommentsEntity
	 */
	private void appendAvgStarLevel(List<CommentsEntity> resCommentsList,Integer goalType, BigInteger goalId){
		if(resCommentsList!=null&&resCommentsList.size()>0){
			Double totalStarLevel = commentsDao.selectCommentsAverageLevel(goalType, goalId);
			for(CommentsEntity tmpCommentsEntity:resCommentsList){
				if(tmpCommentsEntity!=null){
					tmpCommentsEntity.setAvgTotalStarLevel(totalStarLevel);
				}
			}
		}
	}

	@Override
	public List<GoalIdCommentsEntity> getCommentsListMulti(Integer goalType, List<BigInteger> goalIdList,
			Integer commentsLength) {
		return commentsDao.selectCommentsListMulti(goalType, goalIdList, commentsLength);
	}

	@Override
	public void testUuid() {
		System.out.println(JSON.toJSONString(uuidManager.getNextUuidBigInteger(SEQConstants.t_achievement, 2)));
		System.out.println(JSON.toJSONString(uuidManager.getNextUuidBigInteger(SEQConstants.t_achievement, 2)));
		System.out.println(JSON.toJSONString(uuidManager.getNextUuidBigInteger(SEQConstants.t_achievement, 1)));
		System.out.println(JSON.toJSONString(uuidManager.getNextUuidBigInteger(SEQConstants.t_achievement, 1)));
		throw new BusinessRuntimeException();
	}

	@Override
	public Map<String, Object> qryActivityUrl(String placeCode, BigInteger userId) {
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, groupBuilding.getId());
		Map<String, Object> resMap = new HashMap<String, Object>();
		String url = commentsDao.qryActivityUrl(placeCode, codeIdList);
		if(!DataUtil.isEmpty(url)) {
			resMap.put("isHasmd", true);
			resMap.put("url", url);
		} else {
			resMap.put("url","");
			resMap.put("isHasmd", false);
		}
		return resMap;
	}
	//	public static void main(String[] args) {
//		{
//			Double avgLevel = 1.13242565;
//			avgLevel = Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(avgLevel));
//			System.out.println(avgLevel);
//		}
//		{
//			Double avgLevel = 1.14242565;
//			avgLevel = Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(avgLevel));
//			System.out.println(avgLevel);
//		}
//		{
//			Double avgLevel = 1.15242565;
//			avgLevel = Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(avgLevel));
//			System.out.println(avgLevel);
//		}
//		{
//			Double avgLevel = 1.16242565;
//			avgLevel = Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(avgLevel));
//			System.out.println(avgLevel);
//		}
//		
//	}
	
}
