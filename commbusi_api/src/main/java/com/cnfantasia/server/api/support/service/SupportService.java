/**   
* Filename:    SupportService.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午8:32:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.service.ICommonGatherDataService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.support.dao.ISupportDao;
import com.cnfantasia.server.api.support.util.SupportCountUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.support.dao.ISupportBaseDao;
import com.cnfantasia.server.domainbase.support.entity.Support;

/**
 * Filename:    SupportService.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午8:32:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public class SupportService implements ISupportService{
	private ISupportBaseDao supportBaseDao;
	public void setSupportBaseDao(ISupportBaseDao supportBaseDao) {
		this.supportBaseDao = supportBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ISupportDao supportDao;
	public void setSupportDao(ISupportDao supportDao) {
		this.supportDao = supportDao;
	}
	
//	private IMsgpushService msgpushService;
//	public void setMsgpushService(IMsgpushService msgpushService) {
//		this.msgpushService = msgpushService;
//	}
//	
//	private ICommonUserService commonUserService;
//	public void setCommonUserService(ICommonUserService commonUserService) {
//		this.commonUserService = commonUserService;
//	}
//	
//	private IMicroblogContentBaseDao microblogContentBaseDao;
//	public void setMicroblogContentBaseDao(IMicroblogContentBaseDao microblogContentBaseDao) {
//		this.microblogContentBaseDao = microblogContentBaseDao;
//	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private ICommonGatherDataService commonGatherDataService;
	public void setCommonGatherDataService(ICommonGatherDataService commonGatherDataService) {
		this.commonGatherDataService = commonGatherDataService;
	}
	
	@Override
	public int doSupport(BigInteger userId, Integer goalType, BigInteger goalId, boolean isSupport) {
		if(goalId==null){
			if(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_NOTICE_SUMMON.compareTo(goalType)==0){//如果是物业公共召唤的
				if(userId!=null){
					goalId = commonRoomService.getGroupBuildingIdByUserId(userId);
				}
			}
		}
		if(goalId==null){
			throw new BusinessRuntimeException("SupportService.doSupport.goalId.empty",new Object[]{goalType});
		}
		Support supportQry = new Support();
		supportQry.setUserId(userId);
		supportQry.setTargetId(goalId);
		supportQry.setTargetType(goalType);
		BigInteger updateSupportId = null;
		{//查询是否存在
			List<Support> tmpList = supportBaseDao.selectSupportByCondition(MapConverter.toMap(supportQry), false);
			if(tmpList!=null&&tmpList.size()>0){
				updateSupportId = tmpList.get(0).getId();
			}
		}
		if(updateSupportId!=null){
			if(isSupport){//保持
			}else{
				int res = supportBaseDao.deleteSupportLogic(updateSupportId);
				if(res<=0){
					throw new BusinessRuntimeException("SupportService.doSupport.deleteSupportLogic.failed");
				}
			}
		}else{
			BigInteger supportAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_support);
			String nowTime = dualDao.getNowTime();
			Support supportAdd = new Support();
			supportAdd.setUserId(userId);
			supportAdd.setTargetId(goalId);
			supportAdd.setTargetType(goalType);
			supportAdd.setId(supportAddId);
			supportAdd.setTime(nowTime);
			int res = supportBaseDao.insertSupport(supportAdd);
			if(res<=0){
				throw new BusinessRuntimeException("SupportService.doSupport.insertSupport.failed");
			}
		}
//		if(isSupport){//消息推送点赞
//			if(CommonModuleDict.CommonModule_TargetType.T_MICRO_BLOG.compareTo(goalType)==0){//评论对象为微博
//				MicroblogContent microblogContent = microblogContentBaseDao.selectMicroblogContentBySeqId(goalId);
//				if(microblogContent!=null&&userId!=null&&userId.compareTo(microblogContent.getUserId())!=0){
//					//{0}为您的唠嗑点了赞
//					Message toAddMessage = packingMessage(userId, "microblog.creater.isSupported", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.MicroBlogPush);
//					List<MessageParameter> paramList = new ArrayList<MessageParameter>();
//					{
//						MessageParameter tmpMessageParameter = new MessageParameter();
//						tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
//						tmpMessageParameter.setKey("wbid");
//						tmpMessageParameter.settMessageFId(toAddMessage.getId());
//						tmpMessageParameter.setValue(goalId==null?null:goalId.toString());
//						paramList.add(tmpMessageParameter);
//					}
//					msgpushService.addMessage2Pool(microblogContent.getUserId(), LoginDict.UserRegistOrTmp.REGIST_USER, toAddMessage, null,paramList);
//				}
//			}
//		}
		commonGatherDataService.appendTotalSupportCount(goalType, goalId, isSupport?1:-1);//syl-add-2015-7-1 17:27:51
		int totalCount = qrySupportCount(goalType, goalId);
		return totalCount;
	}
	
//	private Message packingMessage(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType){
//		//新增一条消息
//		Message messageAdd = null;
//		{//组装消息信息
//			messageAdd = new Message();
//			String content = MessageSourceUtil.getMessage(msgInfoKey, msgParams);
//			String nowTime = dualDao.getNowTime();
//			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_message);
//			messageAdd.setAndroidTargetView(null);
//			messageAdd.setContent(content);
//			messageAdd.setCreater(userId);
//			messageAdd.setId(addId);
//			messageAdd.setIosTargetView(null);
//			messageAdd.setMsggroupFid(null);
//			messageAdd.setPicUrl(null);
//			messageAdd.setTime(nowTime);
//			messageAdd.setTitle("有点赞信息");
//			messageAdd.setType(msgType);
//		}
//		return messageAdd;
//	}
	
	@Override
	public boolean qryIsSupport(BigInteger userId, Integer goalType, BigInteger goalId) {
		Support supportQry = new Support();
		supportQry.setUserId(userId);
		supportQry.setTargetId(goalId);
		supportQry.setTargetType(goalType);
		int count = supportBaseDao.selectSupportCount(MapConverter.toMap(supportQry), false);
		return count>0?true:false;
	}

	@Override
	public int qrySupportCount(Integer goalType, BigInteger goalId) {
//		Support supportQry = new Support();
//		supportQry.setTargetId(goalId);
//		supportQry.setTargetType(goalType);
//		int count = supportBaseDao.selectSupportCount(MapConverter.toMap(supportQry), false);
		
		CommonGatherData signal = commonGatherDataService.getGatherDataSignal(goalType, goalId);
		int count = signal==null?0:signal.getTotalSupportCount().intValue();
		
		//小区支持的 审核通过的 返回数量增加80
		if(goalType!=null&&goalType.compareTo(CommonModuleDict.CommonModule_TargetType.T_GROUPBUILD_SUPPORT)==0){
			GroupBuilding currGroupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(goalId);
			count = SupportCountUtil.getSupportCount(count, currGroupBuilding);
		}
		return count;
	}

	@Override
	public List<KitchenEntity> getKitchenCookbookSupportList(BigInteger userId,PageModel pageModel) {
		return supportDao.selectKitchenCookbookSupportList(userId,pageModel);
	}

}
