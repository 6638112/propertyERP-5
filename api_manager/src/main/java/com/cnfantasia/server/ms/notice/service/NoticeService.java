package com.cnfantasia.server.ms.notice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.dao.IRedpointDao;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;
import com.cnfantasia.server.domainbase.message.dao.IMessageBaseDao;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageGroup.dao.IMessageGroupBaseDao;
import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;
import com.cnfantasia.server.domainbase.messageParameter.dao.IMessageParameterBaseDao;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messagePrint.dao.IMessagePrintBaseDao;
import com.cnfantasia.server.domainbase.messagePrint.entity.MessagePrint;
import com.cnfantasia.server.domainbase.redpointContent.dao.IRedpointContentBaseDao;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContentSource.dao.IRedpointContentSourceBaseDao;
import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.constant.LoginDict;
import com.cnfantasia.server.ms.notice.constant.NoticeDict;
import com.cnfantasia.server.ms.notice.dao.INoticeDao;
import com.cnfantasia.server.ms.notice.dto.NoticeListBean;
import com.cnfantasia.server.ms.notice.dto.NoticeRequest;
import com.cnfantasia.server.ms.notice.entity.MessageEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class NoticeService implements INoticeService {
	private INoticeDao noticeDao; 
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	private IMessageBaseDao messageBaseDao;
	public void setMessageBaseDao(IMessageBaseDao messageBaseDao) {
		this.messageBaseDao = messageBaseDao;
	}
	
	private IMessageParameterBaseDao messageParameterBaseDao;

	public void setMessageParameterBaseDao(IMessageParameterBaseDao messageParameterBaseDao) {
		this.messageParameterBaseDao = messageParameterBaseDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IDualDao dualDao;

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private IMessageGroupBaseDao messageGroupBaseDao;

	public void setMessageGroupBaseDao(IMessageGroupBaseDao messageGroupBaseDao) {
		this.messageGroupBaseDao = messageGroupBaseDao;
	}
	
	private IMessagePrintBaseDao messagePrintBaseDao;
	
	public void setMessagePrintBaseDao(IMessagePrintBaseDao messagePrintBaseDao) {
		this.messagePrintBaseDao = messagePrintBaseDao;
	}

	@Resource
	IRedpointDao redpointDao;
	@Resource
	IRedpointContentBaseDao redpointContentBaseDao;
	@Resource
	IRedpointContentSourceBaseDao redpointContentSourceBaseDao;
	@Resource
	private IHomeMessageService homeMessageService;

	@Override
	public MessageEntity getMessageDetail(BigInteger messageId) {
		return noticeDao.selectMessageDetail(messageId);
	}

	@Override
	public void deleteMessageById(BigInteger messageId) {
		noticeDao.deleteMessageById(messageId);
	}

	@Override
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(Map<String, Object> paramMap) {
		return noticeDao.selectGroupBuildingSimpleList(paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> getBlockList(Map<String, Object> paramMap){
		return noticeDao.selectBlockList(paramMap);
	}
	
	@Override
	public List<GroupBuildingSimpleEntity> getGroupBuildingSimpleList(BigInteger userId, int msgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isadmin", UserContext.getCurrUser().getIsadmin());
		params.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		params.put("userId", userId);
		List<GroupBuildingSimpleEntity> gbList = noticeDao.selectGroupBuildingSimpleList(params);
		List<Object> bgIds = noticeDao.select_GroupBuildingIds_byMsgId(msgId);
		for (int i = 0; i < bgIds.size(); i++)
			for (int j = 0; j < gbList.size(); j++) {
				long gbId = gbList.get(j).getId().longValue();
				if (gbId == (Long) bgIds.get(i)) {
					gbList.get(j).setIsPushed("yes");
				}
			}
		return gbList;
	}

	@Override
	public List<NoticeListBean> searchNoticeList(Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList(paramMap);
	}

	@Override
	public int delete_messageGroup_byMsgId(String msgId) {
		return noticeDao.delete_messageGroup_byMsgId(msgId);
	}

	@Override
	@Transactional
	public void saveNotice(NoticeRequest noticeRequest) {
		BigInteger messageId = null;
		if ("pushNow".equals(noticeRequest.getPushWay())) {// 立即推送
			noticeRequest.setPushTime(dualDao.getNowTime());
		}

		Message msg = new Message();
		msg.setTitle(noticeRequest.getTitle());
		msg.setContent(noticeRequest.getNoticeNontent());
		msg.setTime(noticeRequest.getPushTime());
		if(StringUtils.isNotBlank(noticeRequest.getExpiryDateStart())){
			msg.setExpiryDateStart(noticeRequest.getExpiryDateStart());
		}
		if(StringUtils.isNotBlank(noticeRequest.getExpirDateEnd())){
			msg.setExpiryDateEnd(noticeRequest.getExpirDateEnd());
		}

		if (!StringUtils.isEmpty(noticeRequest.getMsgId())) {
			messageId = new BigInteger(noticeRequest.getMsgId());
			msg.setId(messageId);
			messageBaseDao.updateMessage(msg);
		} else {
			// 新增消息
			msg.setType(3L);//type=3是物业公告
			messageId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
			msg.setId(messageId);
			msg.setSys0AddUser(UserContext.getCurrUser().getId());
			msg.setSendState(0);
			messageBaseDao.insertMessage(msg);
			
			MessageParameter tmpMessageParameter = new MessageParameter();
	        tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
	        tmpMessageParameter.setKey("pushId");
	        tmpMessageParameter.settMessageFId(msg.getId());
	        tmpMessageParameter.setValue(MsgpushDict.PushId.WyNotice);
	        messageParameterBaseDao.insertMessageParameter(tmpMessageParameter);
			
			//插入到街坊消息
	        // 公告消息流中，若公告内容为纯图片，则内容显示公告标题；若公告内容有文字，则逻辑不变
	     	String content = dealNoticeContent(noticeRequest.getNoticeNontent(), noticeRequest.getTitle());
			ApplicationContextBothUtil.getMicroblogQueueService().propertyNotice(noticeRequest.getMsgId(), noticeRequest.getTitle(), 
					content, noticeRequest.getPushTime(), noticeRequest.getGbIds());
		}
		
		MessagePrint messagePrint = new MessagePrint();
		messagePrint.setRqCode(Integer.valueOf(noticeRequest.getRqCode()));
		messagePrint.setSignature(noticeRequest.getSignature());
		messagePrint.setPageHeader(noticeRequest.getPageHeader());
		
		if(StringUtils.isNotBlank(noticeRequest.getMpId())){
			messagePrint.setId(new BigInteger(noticeRequest.getMpId()));
			messagePrint.setSys0UpdTime(dualDao.getNowTime());
			messagePrintBaseDao.updateMessagePrint(messagePrint);
		} else {
			messagePrint.settMessageFId(messageId);
			messagePrint.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_print));
			messagePrint.setSys0AddTime(dualDao.getNowTime());
			messagePrintBaseDao.insertMessagePrint(messagePrint);
		}
		
		List<BigInteger> gbIds = new ArrayList<BigInteger>();
		String[] gbIdStrs = noticeRequest.getGbIds();
		for(String gbIdStr:gbIdStrs){
			gbIds.add(new BigInteger(gbIdStr));
		}
		
		//先删除已有的，
		if (!StringUtils.isEmpty(noticeRequest.getMsgId())) {
			noticeDao.delete_messageGroup_byMsgId(noticeRequest.getMsgId());
		}

		int gdIdCount = noticeRequest.getGbIds().length;
		List<BigInteger> newIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_group, gdIdCount);
		// 往“小区消息”中间表中插入数据
		List<MessageGroup> messageGroupList = new ArrayList<MessageGroup>(gdIdCount);
		for (int i = 0; i < gdIdCount; i++) {
			String gdId = noticeRequest.getGbIds()[i];
			MessageGroup mg = new MessageGroup();
			mg.setId(newIdList.get(i));
			mg.settMessageFId(msg.getId());
			mg.setGroupbuildingId(new BigInteger(gdId));
			mg.setSys0AddUser(UserContext.getCurrUser().getId());
			messageGroupList.add(mg);
		}
		messageGroupBaseDao.insertMessageGroupBatch(messageGroupList);
		//设定关系
		saveNoticeMessage(messageId, noticeRequest.getGbIds());
	}
	
	private String dealNoticeContent(String content, String title) {
		// 公告消息流中，若公告内容为纯图片，则内容显示公告标题；若公告内容有文字，则逻辑不变；
		String c = HtmlTagFilterUtil.removeHtmlTagInfo(content);
    	if(StringUtils.isBlank(c)) {
    		c = HtmlTagFilterUtil.removeHtmlTagInfo(title);
    	}
    	return c;
	}
	
	/**
	 * 公告发布
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public JsonResponse publish(BigInteger id) {
		JsonResponse jsonResponse = new JsonResponse();
		Message message = messageBaseDao.selectMessageBySeqId(id);
		if(message.getSendState()!=null && message.getSendState()==1){
			jsonResponse.setMessage("该公告已发布，请勿重复操作！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		} else {
			message.setSendState(1);
			message.setSys0UpdUser(UserContext.getOperIdBigInteger());
			message.setSys0UpdTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
			messageBaseDao.updateMessage(message);
			
			// <====首页消息
			GroupHomeMessage groupHomeMessage = new GroupHomeMessage();
			groupHomeMessage.setMessageCode(HomeMessageDict.MessageCode.NOTICE_MESSAGE);
			
			// 公告消息流中，若公告内容为纯图片，则内容显示公告标题；若公告内容有文字，则逻辑不变
			String content = dealNoticeContent(message.getContent(), message.getTitle());
        	
			groupHomeMessage.setContent(content);
			groupHomeMessage.setExtInfo(id.toString());
			groupHomeMessage.setResourceId(id);
			if(StringUtils.isNotBlank(message.getExpiryDateStart())){
				DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
				DateTime time1 =  format.parseDateTime(message.getTime());
				DateTime time2 = format.parseDateTime(message.getExpiryDateStart());
				if(time1.isAfter(time2)){
					groupHomeMessage.setValidTime(message.getTime());
				} else {
					groupHomeMessage.setValidTime(message.getExpiryDateStart());
				}
			} else {
				groupHomeMessage.setValidTime(message.getTime());
			}
			
			if(StringUtils.isNotBlank(message.getExpiryDateEnd())){
				groupHomeMessage.setExpireTime(message.getExpiryDateEnd());
			}
			
			List<BigInteger> gbIds = noticeDao.selectGbIdsForNotice(id);
			homeMessageService.generateGroupMsg(groupHomeMessage, gbIds);
			// 首页消息====>
			
			jsonResponse.setMessage("操作成功！");
		}
		
		return jsonResponse;
	}

	private IUserHasTMessageBaseDao userHasTMessageBaseDao;

	public void setUserHasTMessageBaseDao(IUserHasTMessageBaseDao userHasTMessageBaseDao) {
		this.userHasTMessageBaseDao = userHasTMessageBaseDao;
	}

	@Override
	public int saveNoticeMessage(BigInteger messageId, String[] messageGroups) throws BusinessRuntimeException {
		//删除UserHasTMessage历史数据
		if(messageId!=null){
			noticeDao.deleteUserHasTMessageByMsgId(messageId);
		}
		
		int resCount = 0;
		if (messageGroups != null && messageGroups.length > 0) {
			List<BigInteger> groupBIds = new ArrayList<BigInteger>();
			for (String idStr : messageGroups) {
				groupBIds.add(new BigInteger(idStr.trim()));
			}
			//查询小区下的<用户,房间>列表
			List<Map> userRoomList = noticeDao.selectUserAndRoomListByGroupBuildIds(groupBIds);
			List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();
			
			//新增消息
			if (userRoomList != null && userRoomList.size() > 0) {//新增用户消息关系
				List<UserHasTMessage> userHasTMessageAddList = new ArrayList<UserHasTMessage>();
				List<BigInteger> addIdsList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, userRoomList.size());
				for (int i = 0; i < userRoomList.size(); i++) {
					UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
					tmpUserHasTMessage.setId(addIdsList.get(i));
					tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
					tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
					tmpUserHasTMessage.settMessageFId(messageId);
					BigInteger userId = new BigInteger(userRoomList.get(i).get("userId").toString());
					tmpUserHasTMessage.settUserFId(userId);
					BigInteger userRoomId = new BigInteger(userRoomList.get(i).get("roomId").toString());
					tmpUserHasTMessage.setUserRoomId(userRoomId);
					tmpUserHasTMessage.setUserType(LoginDict.UserRegistOrTmp.REGIST_USER);//已注册的用户
					userHasTMessageAddList.add(tmpUserHasTMessage);
					
					// 红点信息准备
					CommUserDataEntity tmpCommUserDataEntity = new CommUserDataEntity(userId, LoginDict.UserRegistOrTmp.REGIST_USER, userRoomId);
					commUserDataEntityList.add(tmpCommUserDataEntity);					
				}
				userHasTMessageBaseDao.insertUserHasTMessageBatch(userHasTMessageAddList);
			}
			
			new RedPointAdderThread(messageId, commUserDataEntityList).start();
			
			//根据消息类型设定消息的视图
			resCount = 1;
		}
		return resCount;
	}

	private void addRedPointAfterSaveMessage(BigInteger messageId, List<CommUserDataEntity> commUserDataEntityList) {
		{// 添加红点提醒
			List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
			sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.SystemMessage, messageId,
					"INSERT"));
			
			if(commUserDataEntityList!=null&&commUserDataEntityList.size()>0){
				for(CommUserDataEntity tmpCommUserDataEntity:commUserDataEntityList){
					BigInteger userId = tmpCommUserDataEntity.getUserId();
					Integer userType = tmpCommUserDataEntity.getUserType();
					BigInteger defaultRoomId = tmpCommUserDataEntity.getDefaultRoomId();
					addRedpointContent(userId, userType, defaultRoomId, RedpointConstant.RedpointContent_ModelCode.SYS_NOTICE, sourceList);
				}
			}
			
			/*IRedpointService redpointService = (IRedpointService) CnfantasiaCommbusiUtil.getBeanManager("redpointService");
			FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableMulti(redpointService,
					commUserDataEntityList, RedpointConstant.RedpointContent_ModelCode.SYS_NOTICE, sourceList));
			new Thread(task).start();*/
		}
	}
	
	private void addRedpointContent(BigInteger userId, Integer userType, BigInteger currRoomId,
			String modelCode, List<BasicSourceEntity> sourceList) {
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
	public int searchNoticeList_forCount(Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList_forCount(paramMap);
	}

	@Override
	public List<NoticeListBean> searchNoticeList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return noticeDao.searchNoticeList_forPage(curPageIndex, pageSize, paramMap);
	}
	
	/**
	 * 物业新增公告同步发送到街坊消息  huangzj2015-06-30
	 * */
//	private void excutePushMicroblog(String text, String[] gbIds){
//		try{
//			
////			if(gbIds.length>0){
////				for (String groupbuildingId : gbIds) {
////					MicroblogQueuePool.addActivity(
////							MicroblogQueuePool.packageMicroblogQueue(text, 
////									MicroblogQueueConstant.Source_Type.PC, MicroblogQueueConstant.Timing.NO, 
////									new BigInteger(groupbuildingId), MicroblogQueueConstant.MQ_Level.Code_70));
////				}
////			}
//			
//			/*int gdIdCount = gbIds.length;
//			List<BigInteger> newIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_group, gdIdCount);
//			List<MicroblogContent> contents = new ArrayList<MicroblogContent>();
//			MicroblogContent mc;
//			for (int i = 0; i < gdIdCount; i++) {
//				mc = new MicroblogContent();
//				mc.setId(newIdList.get(i));
//				mc.settGroupBuildingFId(new BigInteger(gbIds[i]));
//				mc.setText(text);
//				mc.setCreateTime(CnfantasiaCommUtil.getCurrentTime());
//				mc.setUserId(MicroblogQueueConstant.Sys_User_Id.PC);
//				mc.settMicroblogTypeFId(MicroblogConstant.DEFAULT_MICROBLOG_TYPEID);
//				contents.add(mc);
//			}
//			if(!contents.isEmpty()){
//				IMicroblogContentBaseDao microblogContentBaseDao = (IMicroblogContentBaseDao)CnfantasiaCommUtil.getBeanManager("microblogContentBaseDao");
//				microblogContentBaseDao.insertMicroblogContentBatch(contents);
//			}*/
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 小区autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> gbFilter(String gbName){
		return noticeDao.gbFilter(gbName);
	}
	
	/**
	 * 物业autocomplete框后台实现
	 * 
	 * @param pcName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> propertyCompanyFilter(String pcName){
		return noticeDao.propertyCompanyFilter(pcName);
	}
	
	class RedPointAdderThread extends Thread {
		BigInteger messageId;
		List<CommUserDataEntity> commUserDataEntityList;

		public RedPointAdderThread(BigInteger messageId, List<CommUserDataEntity> commUserDataEntityList) {
			super();
			this.messageId = messageId;
			this.commUserDataEntityList = commUserDataEntityList;
		}

		@Override
		public void run() {
			addRedPointAfterSaveMessage(messageId, commUserDataEntityList);
		}
	}
}
