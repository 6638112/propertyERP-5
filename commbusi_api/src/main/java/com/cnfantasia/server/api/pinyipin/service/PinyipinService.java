/**   
* Filename:    PinyipinService.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:25:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.service;

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
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pinyipin.constant.PinyipinConstant;
import com.cnfantasia.server.api.pinyipin.constant.PinyipinDict;
import com.cnfantasia.server.api.pinyipin.dao.IPinyipinDao;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinReserveEntity;
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
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communityPinyipinContent.dao.ICommunityPinyipinContentBaseDao;
import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;
import com.cnfantasia.server.domainbase.communityPinyipinPic.dao.ICommunityPinyipinPicBaseDao;
import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.dao.ICommunityPinyipinReserveBaseDao;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * Filename:    PinyipinService.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:25:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public class PinyipinService implements IPinyipinService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPinyipinDao pinyipinDao;
	public void setPinyipinDao(IPinyipinDao pinyipinDao) {
		this.pinyipinDao = pinyipinDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommunityPinyipinPicBaseDao communityPinyipinPicBaseDao;
	public void setCommunityPinyipinPicBaseDao(ICommunityPinyipinPicBaseDao communityPinyipinPicBaseDao) {
		this.communityPinyipinPicBaseDao = communityPinyipinPicBaseDao;
	}
	
	private ICommunityPinyipinReserveBaseDao communityPinyipinReserveBaseDao;
	public void setCommunityPinyipinReserveBaseDao(ICommunityPinyipinReserveBaseDao communityPinyipinReserveBaseDao) {
		this.communityPinyipinReserveBaseDao = communityPinyipinReserveBaseDao;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao;
	public void setCommunityPinyipinContentBaseDao(ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao) {
		this.communityPinyipinContentBaseDao = communityPinyipinContentBaseDao;
	}
	
	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
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
	
	private BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		BigInteger groupBuildingId = null;
		if(xanaduService.getCurrStatus(userId)){
			groupBuildingId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}else{
			groupBuildingId = commonRoomService.getGroupBuildingIdByUserId(userId);
		}
		return groupBuildingId;
	}
	
	@Override
	public PinyipinContentEntity getMostHotContent(BigInteger userId) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		PinyipinContentEntity resPinyipinContentEntity =  pinyipinDao.selectMostHotContent(userId,groupBuildingId);
		appendPinyipinContentDetail(userId, resPinyipinContentEntity);
		return resPinyipinContentEntity;
	}

	@Override
	public List<PinyipinContentEntity> getHotContentList(BigInteger userId,PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		PinyipinContentEntity mostHotEntity = null;
		if(pageModel.begin<=1){
			mostHotEntity = pinyipinDao.selectMostHotContent(userId,groupBuildingId);
		}
		List<PinyipinContentEntity> tmpList = pinyipinDao.selectHotContentList(userId, groupBuildingId,pageModel);
		List<PinyipinContentEntity> resList = new ArrayList<PinyipinContentEntity>();
		if(mostHotEntity!=null){
			resList.add(mostHotEntity);
		}
		if(tmpList!=null){
			for(PinyipinContentEntity tmpEntity:tmpList){
				if(mostHotEntity!=null&&mostHotEntity.getId().compareTo(tmpEntity.getId())==0){
					continue;
				}
				resList.add(tmpEntity);
			}
		}
		return resList;
	}

	@Override
	public List<PinyipinContentEntity> getLaunchContentList(BigInteger userId,PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		return pinyipinDao.selectLaunchContentList(userId, groupBuildingId,pageModel);
	}
	
	/**
	 * 增加拼单其它信息
	 * @param userId
	 * @param resPinyipinContentEntity
	 */
	private void appendPinyipinContentDetail(BigInteger userId,PinyipinContentEntity resPinyipinContentEntity){
		if(resPinyipinContentEntity!=null){
			BigInteger contentId = resPinyipinContentEntity.getId();
			Boolean isDeleteAble = false;//是否可删除
			Boolean isEditAble = false; //是否可编辑
			try {
				validateUpdLaunchContent(userId,contentId);
				isDeleteAble = true;
				isEditAble = true;
			} catch (Exception e) {}
			CommentsEntity firstComment = commentsService.getFirstComments(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_PINYIPIN, contentId);//第一条评论信息
//			Boolean isSupported;//是否已点赞
//			Integer commentTotalCount;//总评论数
//			Integer totalSupportCount;//总点赞数
//			Boolean isCollected;
			
			resPinyipinContentEntity.setIsDeleteAble(isDeleteAble);
			resPinyipinContentEntity.setIsEditAble(isEditAble);
			resPinyipinContentEntity.setFirstComment(firstComment);
			//参与的拼单列表
			List<UserSimpleEntity> reserveUserList = pinyipinDao.selectLaunchPinyipinReserveUserListByTime(userId, contentId);
			resPinyipinContentEntity.setReserveUserList(reserveUserList);
		}
	}
	
	@Override
	public PinyipinContentEntity getPinyipinContentDetail(BigInteger userId,BigInteger contentId) {
		PinyipinContentEntity resPinyipinContentEntity =  pinyipinDao.selectPinyipinContentDetail(userId, contentId);
		appendPinyipinContentDetail(userId, resPinyipinContentEntity);
		return resPinyipinContentEntity;
	}
	@Override
	public CommunityPinyipinContent getPinyipinContentById(BigInteger contentId) {
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentBySeqId(contentId);
	}
	
//	@Override
//	public PinyipinContentEntity getLaunchContentDetail(BigInteger userId, BigInteger contentId) {
//		return pinyipinDao.selectLaunchContentDetail(userId, contentId);
//	}

	@Override
	public List<PinyipinContentEntity> getJoinContentList(BigInteger userId,PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		return pinyipinDao.selectJoinContentList(userId, groupBuildingId,pageModel);
	}
	
	@Override
	public List<PinyipinContentEntity> getLaunchAndJoinContentList(BigInteger userId, PageModel pageModel) {
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		return pinyipinDao.selectLaunchAndJoinContentList(userId, groupBuildingId,pageModel);
	}
	
	/**
	 * 保存拼一拼图片信息
	 */
	private void uploadPinyipinPic(BigInteger userId,List<RequestFileEntity> picList,BigInteger contentId){
			if(picList!=null&&picList.size()>0){
				String pinyipinPicBase = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_PINYIPIN_PIC_BASEPATH);
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
						logger.info("uploadPinyipinPic upload pinyipin file cause exception:"+e.getMessage(), e);
						throw new BusinessRuntimeException("PinyipinService.uploadPinyipinPic.uploadFile.error",new Object[]{requestFile.getFileName()});
					}
					//增加生成小图的任务处理
					FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.Pinyipin,fileServerService.getFileServierUploadBasePath(),pinyipinPicBase, resFileName, requestFile.getFileContent()));
					new Thread(task).start();
				}
				List<CommunityPinyipinPic> communityPinyipinPicAddList = new ArrayList<CommunityPinyipinPic>();
				List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_pinyipin_pic,picUrlList.size());
				for(int i=0;i<picUrlList.size();i++){
					String picUrl = picUrlList.get(i);
					BigInteger picAddId = picAddIdList.get(i);
					CommunityPinyipinPic communityPinyipinPic = new CommunityPinyipinPic();
					communityPinyipinPic.setDesc(null);
					communityPinyipinPic.setId(picAddId);
					communityPinyipinPic.setName(null);
					communityPinyipinPic.settCommunityPinyipinContentFId(contentId);
					communityPinyipinPic.setUploader(userId);
					communityPinyipinPic.setPicUrl(picUrl);
					communityPinyipinPicAddList.add(communityPinyipinPic);
				}
				int res = communityPinyipinPicBaseDao.insertCommunityPinyipinPicBatch(communityPinyipinPicAddList);
				if(res!=communityPinyipinPicAddList.size()){
					throw new BusinessRuntimeException("PinyipinService.uploadPinyipinPic.insertCommunityPinyipinPicBatch.failed");
				}
			}
	}
	
	@Override
	public void confirmLaunchContent(BigInteger userId, String title, String desc, Long marketPrice, Long realPrice,
			String priceUnit, Long goalCount, Integer sendType, String selfAddress, String endDate, String sendDate,
			String contectInfo, List<RequestFileEntity> picList) {
		commCommunityService.checkXanaduOperation(userId);
		BigInteger groupBuildingId = getGroupBuildingIdByUserId(userId);
		BigInteger pinyipinContentAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_pinyipin_content);
		String nowTime = dualDao.getNowTime();
		//新增拼单
		{
			CommunityPinyipinContent pinyipinContent = new CommunityPinyipinContent();
			pinyipinContent.setContectInfo(contectInfo);
			pinyipinContent.setCreateTime(nowTime);
			pinyipinContent.setDesc(desc);
			pinyipinContent.setEndDate(endDate);
			pinyipinContent.setGoalCount(goalCount);
			pinyipinContent.setId(pinyipinContentAddId);
			pinyipinContent.setMarketPrice(marketPrice);
			pinyipinContent.setPriceUnit(priceUnit);
			pinyipinContent.setRealPrice(realPrice);
			pinyipinContent.setSelfAddress(selfAddress);
			pinyipinContent.setSendDate(sendDate);
			pinyipinContent.setSendType(sendType);
			pinyipinContent.setDeliveryStatus(PinyipinDict.PinyipinContent_DeliveryStatus.Start);//新增时默认状态为 预定中
			pinyipinContent.settGroupBuildingFId(groupBuildingId);
			pinyipinContent.setTitle(title);
			pinyipinContent.setUserId(userId);
			int res = communityPinyipinContentBaseDao.insertCommunityPinyipinContent(pinyipinContent);
			if(res<=0){
				throw new BusinessRuntimeException("PinyipinService.confirmLaunchContent.insertCommunityPinyipinContent.failed");
			}
		}
		//保存图片信息
		uploadPinyipinPic(userId, picList, pinyipinContentAddId);
	}
	
	@Override
	public void updLaunchConent(BigInteger userId,BigInteger contentId,String title,String desc,Long marketPrice,Long realPrice,String priceUnit,Long goalCount
			,Integer sendType,String selfAddress,String endDate,String sendDate,String contectInfo,List<RequestFileEntity> picList,List<BigInteger> delPicIds) {
		//校验是否可编辑
		validateUpdLaunchContent(userId,contentId);
		//1.更新拼单信息
		{
			CommunityPinyipinContent pinyipinContent = new CommunityPinyipinContent();
			pinyipinContent.setId(contentId);
			pinyipinContent.setContectInfo(contectInfo);
//			pinyipinContent.setCreateTime(nowTime);
			pinyipinContent.setDesc(desc);
			pinyipinContent.setEndDate(endDate);
			pinyipinContent.setGoalCount(goalCount);
			pinyipinContent.setMarketPrice(marketPrice);
			pinyipinContent.setPriceUnit(priceUnit);
			pinyipinContent.setRealPrice(realPrice);
			pinyipinContent.setSelfAddress(selfAddress);
			pinyipinContent.setSendDate(sendDate);
			pinyipinContent.setSendType(sendType);
//			pinyipinContent.setStatus(PinyipinDict.PinyipinContent_Status.Start);//新增时默认状态为 预定中
//			pinyipinContent.settGroupBuildingFId(groupBuildingId);
			pinyipinContent.setTitle(title);
//			pinyipinContent.setUserId(userId);
			int res = communityPinyipinContentBaseDao.updateCommunityPinyipinContent(pinyipinContent);
			if(res<=0){
				throw new BusinessRuntimeException("PinyipinService.updLaunchConent.updateCommunityPinyipinContent.failed");
			}
		}
		//2.保存图片信息
		uploadPinyipinPic(userId, picList, contentId);
		//3.删除图片Ids
		if(delPicIds!=null&&delPicIds.size()>0){
			communityPinyipinPicBaseDao.deleteCommunityPinyipinPicLogicBatch(delPicIds);
		}
	}
	
	/**
	 * 校验是否可编辑
	 * @param contentId
	 */
	private void validateUpdLaunchContent(BigInteger userId,BigInteger contentId){
		//只有创建者可编辑
		CommunityPinyipinContent tmpCommunityPinyipinContent = communityPinyipinContentBaseDao.selectCommunityPinyipinContentBySeqId(contentId);
		if(userId==null||tmpCommunityPinyipinContent.getUserId().compareTo(userId)!=0){
			throw new BusinessRuntimeException("PinyipinService.updLaunchConent.notOwn.error");
		}
		Integer joinCount = pinyipinDao.selectJoinTotalCount(contentId);
		if(joinCount!=null&&joinCount>0){//已有用户参与拼单则不可删除
			throw new BusinessRuntimeException("PinyipinService.validateUpdLaunchContent.hasReserved.error");
		}
	}
	
	@Override
	public void delLaunchContent(BigInteger userId, BigInteger contentId) {
		//校验是否可编辑
		validateUpdLaunchContent(userId,contentId);
		//若可删除则执行删除
		Integer delCount = pinyipinDao.deleteLaunchContent(userId, contentId);
		if(delCount==null||delCount<=0){
			throw new BusinessRuntimeException("PinyipinService.delLaunchContent.delCount.failed");
		}
	}

//	@Override
//	public PinyipinContentEntity getJoinReserveContentDetail(BigInteger userId, BigInteger contentId) {
//		return pinyipinDao.selectJoinReserveContentDetail(userId, contentId);
//	}
	
	/**
	 * 根据拼单Id校验拼单是否已结束
	 */
	private void validateContentIsFinishedByContentId(BigInteger contentId){
		boolean isFinished = true;
		if(contentId!=null){
			CommunityPinyipinContent tmpCommunityPinyipinContent = communityPinyipinContentBaseDao.selectCommunityPinyipinContentBySeqId(contentId);
			if(tmpCommunityPinyipinContent!=null){
				if(PinyipinDict.PinyipinContent_DeliveryStatus.Success.compareTo(tmpCommunityPinyipinContent.getDeliveryStatus())==0){
					
				}else{
					isFinished = false;
				}
			}
		}
		if(isFinished){
			throw new ValidateRuntimeException("PinyipinService.pinyipinContent.hasFinished.failed");
		}
	}
	
	/**
	 * 根据预定Id校验对应拼单是否已结束
	 */
	private void validateContentIsFinishedByReservedId(BigInteger reservedId){
		CommunityPinyipinReserve tmpCommunityPinyipinReserve = communityPinyipinReserveBaseDao.selectCommunityPinyipinReserveBySeqId(reservedId);
		BigInteger contentId = null;
		if(tmpCommunityPinyipinReserve!=null){
			contentId = tmpCommunityPinyipinReserve.gettCommunityPinyipinContentFId();
		}
		validateContentIsFinishedByContentId(contentId);
	}
	
	@Override
	public void confirmJoinContent(BigInteger userId, BigInteger contentId, Long count, BigInteger deliveryAddressId) {
		commCommunityService.checkXanaduOperation(userId);
		//校验拼单是否已结束
		validateContentIsFinishedByContentId(contentId);
		//查询当前拼单信息
		PinyipinContentEntity pinyipinContentEntity = null;
		if(!StringUtils.isEmpty(contentId)){
			pinyipinContentEntity = pinyipinDao.selectPinyipinContentDetail(null, contentId);
		}
		if(pinyipinContentEntity==null){
			throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.noExist.error");
		}
		//用户不能拼自己的单
		if(pinyipinContentEntity.getUserId().compareTo(userId)==0){
			throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.isOwn.error");
		}
		if(pinyipinContentEntity!=null){
			String nowTime = dualDao.getNowTime();
			Long nowTimeLong = DateUtil.timeToLong(nowTime);
			Long endDateLong = DateUtil.timeToLong(pinyipinContentEntity.getEndDate());
			if(endDateLong!=null&&nowTimeLong>=endDateLong){//校验是否超时
				throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.isTimeOut.error");
			}else if(pinyipinContentEntity.getReserveCount()!=null){//校验数量是否超过
				Long reserveCount = pinyipinContentEntity.getReserveCount();
				reserveCount = reserveCount+count;
				Long leftCount = (pinyipinContentEntity.getGoalCount()-pinyipinContentEntity.getReserveCount());
				if(reserveCount.compareTo(pinyipinContentEntity.getGoalCount())>0){//超过数量
					throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.toMany.error",new Object[]{leftCount,pinyipinContentEntity.getPriceUnit()});
				}
			}
		}
		boolean isReserved = false;//是否已经拼过单
		List<CommunityPinyipinReserve> tmpCommunityPinyipinReserveList = pinyipinDao.selectPinyipinReserveById(userId, contentId);
		if(tmpCommunityPinyipinReserveList!=null&&tmpCommunityPinyipinReserveList.size()>0){
			for(CommunityPinyipinReserve tmpCommunityPinyipinReserve:tmpCommunityPinyipinReserveList){
			//if(tmpCommunityPinyipinReserve!=null){//拼过相同的单
				if(tmpCommunityPinyipinReserve!=null&&tmpCommunityPinyipinReserve.gettEbuyDeliveryAddressFId().compareTo(deliveryAddressId)==0){
					//拼过相同的单且拼单的收货地址也相同 则更新数量
					CommunityPinyipinReserve pinyipinReserveUpd = new CommunityPinyipinReserve();
					pinyipinReserveUpd.setId(tmpCommunityPinyipinReserve.getId());
//						pinyipinReserveUpd.settEbuyDeliveryAddressFId(deliveryAddressId);//使用最新的配送地址
					pinyipinReserveUpd.setCount(count+tmpCommunityPinyipinReserve.getCount());//数量合并
					Integer res = communityPinyipinReserveBaseDao.updateCommunityPinyipinReserve(pinyipinReserveUpd);
					if(res<=0){
						throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.upd.failed");
					}
					isReserved = true;//标记为已拼
					break;
				}
			}
		}
		if(!isReserved){//否则新增
			//新增预定拼单信息
			BigInteger pinyipinReserveAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_pinyipin_reserve);
			CommunityPinyipinReserve pinyipinReserve = new CommunityPinyipinReserve();
			pinyipinReserve.setCancleStaus(PinyipinDict.PinyipinReserve_CancleStatus.NotCancel);//默认未取消
			pinyipinReserve.setClaimStatus(PinyipinDict.PinyipinReserve_ClaimStatus.UnClaim);//默认未领取
			pinyipinReserve.setCount(count);
			pinyipinReserve.setId(pinyipinReserveAddId);
			pinyipinReserve.settCommunityPinyipinContentFId(contentId);
			pinyipinReserve.settEbuyDeliveryAddressFId(deliveryAddressId);
			pinyipinReserve.setUserId(userId);
			Integer res = communityPinyipinReserveBaseDao.insertCommunityPinyipinReserve(pinyipinReserve);
			if(res<=0){
				throw new BusinessRuntimeException("PinyipinService.confirmJoinContent.add.failed");
			}
		}
		//消息推送,{0}参与了您发起的拼一拼，快去看看吧
		addMsgPushInfo(pinyipinContentEntity.getUserId(), "pinyipin.launch.joinOrder", new Object[]{commonUserService.getUserShowNameById(userId)},NoticeDict.Message_Type.PinyipinPush,contentId);
	}

	@Override
	public List<PinyipinReserveEntity> getLaunchPinyipinReserveList(BigInteger userId, BigInteger contentId) {
		return pinyipinDao.selectLaunchPinyipinReserveList(userId, contentId);
	}
	
	@Override
	public void updReserveContentByTotalCount(BigInteger currUserId,
			BigInteger pinyipinReverseId, Integer totalCount) {
		//校验拼单是否已结束
		validateContentIsFinishedByReservedId(pinyipinReverseId);
		Integer resCount = pinyipinDao.updReserveContentByTotalCount(currUserId,pinyipinReverseId, totalCount);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PinyipinService.updReserveContentByTotalCount.failed");
		}
	}
	
	@Override
	public void updReserveContentByChangeCount(BigInteger currUserId,BigInteger pinyipinReverseId, Integer changeCount) {
		//校验拼单是否已结束
		validateContentIsFinishedByReservedId(pinyipinReverseId);
		Integer resCount = pinyipinDao.updReserveContentByChangeCount(currUserId,pinyipinReverseId, changeCount);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PinyipinService.updReserveContentByChangeCount.failed");
		}
	}
	
	@Override
	public void confirmDeliveryOrder(BigInteger userId, BigInteger contentId) {
		Integer resCount = pinyipinDao.updatePinyipinContent2Delivery(userId,contentId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PinyipinService.confirmDeliveryOrder.failed");
		}
	}
	
	@Override
	public void markClaimStatus(BigInteger currUserId,BigInteger pinyipinReverseId,Integer calimStatus) {
		Integer resCount = pinyipinDao.updateClaimStatus(currUserId,pinyipinReverseId, calimStatus);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PinyipinService.markClaimStatus.failed");
		}
	}
	
	@Override
	public void autoCheckPinyipinContentTimeOut() {
		logger.info("PinyipinService start to autoCheckPinyipinContentTimeOut");
		Integer resCount = pinyipinDao.updatePinyipinContentTimeOut();
		logger.info("PinyipinService update record count is:"+resCount);
		logger.info("PinyipinService autoCheckPinyipinContentTimeOut finished.");
	}
	
	@Override
	public void freshContentDeliveryStatus(BigInteger contentId) {
		//用户不能拼自己的单
		PinyipinContentEntity pinyipinContentEntity = null;
		if(!StringUtils.isEmpty(contentId)){
			pinyipinContentEntity = pinyipinDao.selectPinyipinContentDetail(null, contentId);
		}
		if(pinyipinContentEntity!=null){//校验是否超时或者数量是否超过
			String nowTime = dualDao.getNowTime();
			Long nowTimeLong = DateUtil.timeToLong(nowTime);
			Long endDateLong = DateUtil.timeToLong(pinyipinContentEntity.getEndDate());
			if(
					(endDateLong!=null&&nowTimeLong>=endDateLong)||//已过时
					(pinyipinContentEntity.getReserveCount()!=null&&pinyipinContentEntity.getReserveCount().compareTo(pinyipinContentEntity.getGoalCount())>=0)){//数量已达到
				if(PinyipinDict.PinyipinContent_DeliveryStatus.Start.compareTo(pinyipinContentEntity.getDeliveryStatus())==0){
					CommunityPinyipinContent updCommunityPinyipinContent = new CommunityPinyipinContent();
					updCommunityPinyipinContent.setId(contentId);
					updCommunityPinyipinContent.setDeliveryStatus(PinyipinDict.PinyipinContent_DeliveryStatus.TimeOut);
					communityPinyipinContentBaseDao.updateCommunityPinyipinContent(updCommunityPinyipinContent);
					
					//查询拼单发起者和所有的参与者
					PinyipinContentEntity tmpEntity = getPinyipinContentDetail(null, contentId);
					BigInteger launcherId = tmpEntity.getUserId();
					//您参与的拼单目标已经达成，准备收货吧！
					List<BigInteger> userIdList = new ArrayList<BigInteger>();
					{
						List<UserSimpleEntity> reserveUserList = tmpEntity.getReserveUserList();
						if(reserveUserList!=null&&reserveUserList.size()>0){
							for(UserSimpleEntity aaUser:reserveUserList){
								userIdList.add(aaUser.getId());
							}
						}
					}
					//数量达到时提示
					if(pinyipinContentEntity.getReserveCount()!=null&&pinyipinContentEntity.getReserveCount().compareTo(pinyipinContentEntity.getGoalCount())>=0){
						//您发起的拼一拼目标已经达成，快准备发货吧！
						BigInteger messageId = addMsgPushInfo(launcherId, "pinyipin.launch.orderGoalSucc", null,NoticeDict.Message_Type.PinyipinPush,pinyipinContentEntity.getId());
						//您参与的拼单目标已经达成，准备收货吧！
						if(userIdList!=null&&userIdList.size()>0){
							List<UserSimpleEntity> tmpUserList = commonRoomService.getUserListByUserIds(userIdList);
							if(tmpUserList!=null&&tmpUserList.size()>0){
								List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();
								for(UserSimpleEntity tmpUser:tmpUserList){
									BigInteger defaultRoomId = tmpUser.getDefaultRoomId();
									CommUserDataEntity commUserDataEntity = new CommUserDataEntity(tmpUser.getId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
									commUserDataEntityList.add(commUserDataEntity);
								}
								msgpushService.addMessage2Pool(commUserDataEntityList, messageId, null);
							}
						}
					}else if(endDateLong!=null&&nowTimeLong>=endDateLong){//已过时
						//拼单时间结束了，快看看拼单详细情况吧！
						BigInteger messageId = addMsgPushInfo(launcherId, "pinyipin.both.orderTimeOut", null,NoticeDict.Message_Type.PinyipinPush,pinyipinContentEntity.getId());
						if(userIdList!=null&&userIdList.size()>0){
							List<UserSimpleEntity> tmpUserList = commonRoomService.getUserListByUserIds(userIdList);
							if(tmpUserList!=null&&tmpUserList.size()>0){
								List<CommUserDataEntity> commUserDataEntityList = new ArrayList<CommUserDataEntity>();
								for(UserSimpleEntity tmpUser:tmpUserList){
									BigInteger defaultRoomId = tmpUser.getDefaultRoomId();
									CommUserDataEntity commUserDataEntity = new CommUserDataEntity(tmpUser.getId(), LoginDict.UserRegistOrTmp.REGIST_USER, defaultRoomId);
									commUserDataEntityList.add(commUserDataEntity);
								}
								msgpushService.addMessage2Pool(commUserDataEntityList, messageId, null);
							}
						}
					}
					
				}
			}
		}
	}
	
//	@Override
//	public String fetchAllHotContentListLastUpdTime(BigInteger userId) {
//		return pinyipinDao.selectAllHotContentListLastUpdTime(userId);
//	}
//
//	@Override
//	public String fetchAllLaunchAndJoinContentListLastUpdTime(BigInteger userId) {
//		return pinyipinDao.selectAllLaunchAndJoinContentListLastUpdTime(userId);
//	}
	
//	@Override
	private Map<String,Object> pinyipinContent2Map(String nowTime,BigInteger currUserId,CommunityPinyipinContent pinyipinContent
			,UserSimpleEntity createUser,List<CommunityPinyipinPic> pinyipinPicList,List<UserSimpleEntity> pinyipinReserveList,Boolean deliverAble
			,Integer userBelong,Long reserveCount,Integer currUserCount,Long currUserReserveCount){
		if(StringUtils.isEmpty(nowTime)){
			nowTime = dualDao.getNowTime();
		}
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("contectInfo", pinyipinContent.getContectInfo());
		resMap.put("createTime", pinyipinContent.getCreateTime());
		resMap.put("desc", pinyipinContent.getDesc());
		resMap.put("endDate", pinyipinContent.getEndDate());
		resMap.put("endDateLong", DateUtil.timeToLong(pinyipinContent.getEndDate()));
		resMap.put("goalCount", pinyipinContent.getGoalCount());
		resMap.put("id", pinyipinContent.getId());
		resMap.put("marketPrice", PriceUtil.div100(pinyipinContent.getMarketPrice()));
		resMap.put("priceUnit", pinyipinContent.getPriceUnit());
		resMap.put("realPrice", PriceUtil.div100(pinyipinContent.getRealPrice()));
		resMap.put("selfAddress", pinyipinContent.getSelfAddress());
		resMap.put("sendDate", pinyipinContent.getSendDate());
		resMap.put("sendType", pinyipinContent.getSendType());
		{//发货清单状态 
			resMap.put("deliveryStatus", pinyipinContent.getDeliveryStatus());
			Integer status = null;
				Long nowTimeLong = DateUtil.timeToLong(nowTime);
				Long endDateLong = DateUtil.timeToLong(pinyipinContent.getEndDate());
				if(
						(endDateLong!=null&&nowTimeLong>=endDateLong)||//已过时
						(reserveCount!=null&&reserveCount.compareTo(pinyipinContent.getGoalCount())>=0)){//数量已达到
						status = PinyipinDict.PinyipinContent_Ext_Status.Finished;
				}else{
					if(PinyipinDict.PinyipinContent_DeliveryStatus.Start.compareTo( pinyipinContent.getDeliveryStatus())==0){
						status = PinyipinDict.PinyipinContent_Ext_Status.Start;
					}else{
						status = PinyipinDict.PinyipinContent_Ext_Status.Finished;
					}
				}
			/*if(endDateLong!=null&&nowTimeLong>=endDateLong){//已过时
				status = PinyipinDict.PinyipinContent_Ext_Status.Finished;
			}else{//预定状态
				
			}*/
			resMap.put("status", status);
		}
		if(currUserReserveCount!=null){//当前用户已拼数量之和
			resMap.put("ext_currUserReserveCount", currUserReserveCount);
		}
		if(deliverAble!=null){//是否可以生成发货清单 deliverAble
			//时间达到或者数量达到 只要有一个满足就 可以 生成发货清单
			resMap.put("ext_deliverAble", deliverAble);
		}
		resMap.put("groupBuildingId", pinyipinContent.gettGroupBuildingFId());
		resMap.put("title", pinyipinContent.getTitle());
		resMap.put("userId", pinyipinContent.getUserId());
		if(pinyipinPicList!=null){//图片列表
			List<Map<String,Object>> picList = new ArrayList<Map<String,Object>>();
			if(pinyipinPicList.size()>0){
				for(CommunityPinyipinPic tmpPinyipinPic:pinyipinPicList){
					Map<String,Object> tmpMap = pinyipinPic2Map(tmpPinyipinPic);
					picList.add(tmpMap);
				}
				resMap.put("ext_picList", picList);
			}
		}
		{//默认图片
			String defaultIcon = null;
			if(pinyipinPicList!=null&&pinyipinPicList.size()>0){
				CommunityPinyipinPic defaultPinyipinPic = pinyipinPicList.get(0);
				String pinyipinPicBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_PINYIPIN_PIC_BASEPATH);
				defaultIcon = StringUtils.isEmpty(defaultPinyipinPic.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(pinyipinPicBasePath+defaultPinyipinPic.getPicUrl(), defaultPinyipinPic);
			}
			resMap.put("ext_defaultIcon", defaultIcon);
		}
		{//拼单创建者
			resMap.put("ext_createUser", commEntityConvertService.baseUser2Map(createUser));
		}
		if(userBelong!=null){//用户是发起者还是参与者
			resMap.put("ext_userBelong", userBelong);
		}
//		{//用户是发起者还是参与者
//			Integer userBelong = null;
//			if(currUserId!=null&&currUserId.compareTo(pinyipinContent.getUserId())==0){
//				userBelong = PinyipinDict.PinyipinContent_UserBelong.Launcher;//发起者
//			}else{
//				boolean isJoiner = false;
//				if(currUserId!=null){
//					if(pinyipinReserveList!=null){
//						for(CommunityPinyipinReserve tmpReserve:pinyipinReserveList){
//							if(tmpReserve.getUserId().compareTo(currUserId)==0){
//								isJoiner = true;
//								break;
//							}
//						}
//					}
//				}
//				if(isJoiner){
//					userBelong = PinyipinDict.PinyipinContent_UserBelong.Joiner;//参与者
//				}else{
//					userBelong = PinyipinDict.PinyipinContent_UserBelong.None;//都不是
//				}
//			}
//			resMap.put("ext_userBelong", userBelong);
//		}
		if(reserveCount!=null){//预定总量
			resMap.put("ext_reserveCount", reserveCount);
		}
		if(currUserCount!=null){//预定人数
			resMap.put("ext_currUserCount", currUserCount);
		}
		if(pinyipinReserveList!=null){
			List<Map<String,Object>> resUserList = new ArrayList<Map<String,Object>>();
			if(pinyipinReserveList!=null){
				for(UserSimpleEntity tmpUser:pinyipinReserveList){
					Map<String,Object> tmpMap = commEntityConvertService.baseUser2Map(tmpUser);
					resUserList.add(tmpMap);
				}
			}
			resMap.put("ext_reserveUserList", resUserList);
		}
//		if(pinyipinReserveList!=null){
//			List<Map<String,Object>> resPinyipinReserveList = new ArrayList<Map<String,Object>>();
//			if(pinyipinReserveList!=null){
//				for(PinyipinReserveEntity tmpCommunityPinyipinReserve:pinyipinReserveList){
//					Map<String,Object> tmpReserveMap = pinyipinReserve2Map(tmpCommunityPinyipinReserve, tmpCommunityPinyipinReserve.getUser(), tmpCommunityPinyipinReserve.getEbuyDeliveryAddressEntity());
//					resPinyipinReserveList.add(tmpReserveMap);
//				}
//			}
//			resMap.put("ext_reserveList", resPinyipinReserveList);
//		}
//		{//已拼人数和数量
//			Long reserveCount = 0L;//预定总量
//			Integer currUserCount = 0;//预定人数
//			List<Map<String,Object>> resPinyipinReserveList = new ArrayList<Map<String,Object>>();
//			if(pinyipinReserveList!=null){
//				currUserCount = pinyipinReserveList.size();
//				for(PinyipinReserveEntity tmpCommunityPinyipinReserve:pinyipinReserveList){
//					reserveCount+=tmpCommunityPinyipinReserve.getCount();
//					Map<String,Object> tmpReserveMap = pinyipinReserve2Map(tmpCommunityPinyipinReserve, tmpCommunityPinyipinReserve.getUser(), tmpCommunityPinyipinReserve.getEbuyDeliveryAddressEntity());
//					resPinyipinReserveList.add(tmpReserveMap);
//				}
//			}
//			resMap.put("ext_reserveCount", reserveCount);
//			resMap.put("ext_currUserCount", currUserCount);
//			resMap.put("ext_reserveList", resPinyipinReserveList);
//		}
		return resMap;
	}
	
	@Override
	public Map<String, Object> pinyipinContent2Map(String nowTime,BigInteger currUserId,CommunityPinyipinContent pinyipinContent
			,UserSimpleEntity createUser,List<CommunityPinyipinPic> pinyipinPicList,List<UserSimpleEntity> pinyipinReserveList,Boolean deliverAble
			,Integer userBelong,Long reserveCount,Integer currUserCount,Long currUserReserveCount
			,Boolean isDeleteAble,Boolean isEditAble,CommentsEntity firstComment,Integer commentTotalCount,Boolean isSupported,Integer totalSupportCount,Boolean isCollected) {
		Map<String, Object> resMap = pinyipinContent2Map(nowTime,currUserId, pinyipinContent, createUser, pinyipinPicList, pinyipinReserveList,deliverAble
				,userBelong,reserveCount,currUserCount,currUserReserveCount);
		if(isDeleteAble!=null){resMap.put("ext_isDeleteAble", isDeleteAble);}
		if(isEditAble!=null){resMap.put("ext_isEditAble", isEditAble);}
		if(firstComment!=null){resMap.put("ext_firstComment", commEntityConvertService.comments2Map(firstComment,firstComment.getUserGroupBuilding(), firstComment.getUser(), firstComment.getNoticeUserList(), firstComment.getCommentsLabelList(),firstComment.getCommentsHasTCommentsPointsEntityList(),firstComment.getAvgTotalStarLevel()));}
		if(commentTotalCount!=null){resMap.put("ext_commentTotalCount", commentTotalCount);}
		if(isSupported!=null){resMap.put("ext_isSupported", isSupported);}
		if(totalSupportCount!=null){resMap.put("ext_totalSupportCount", totalSupportCount);}
		if(isCollected!=null){resMap.put("ext_isCollected", isCollected);}
		return resMap;
	}
	@Override
	public Map<String,Object> pinyipinPic2Map(CommunityPinyipinPic pinyipinPic){
		String pinyipinPicBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_PINYIPIN_PIC_BASEPATH);
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("desc", pinyipinPic.getDesc());
		resMap.put("id", pinyipinPic.getId());
		resMap.put("name", pinyipinPic.getName());
		resMap.put("picUrl", StringUtils.isEmpty(pinyipinPic.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(pinyipinPicBasePath+pinyipinPic.getPicUrl(), pinyipinPic));
		resMap.put("pinyipinContentId", pinyipinPic.gettCommunityPinyipinContentFId());
		resMap.put("uploader", pinyipinPic.getUploader());
		return resMap;
	}

	@Override
	public Map<String, Object> pinyipinReserve2Map(String nowTime,CommunityPinyipinReserve pinyipinReserve
			,UserSimpleEntity user,EbuyDeliveryAddressEntity deliveryAddressEntity) {
		if(StringUtils.isEmpty(nowTime)){
			nowTime = dualDao.getNowTime();
		}
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("cancleStaus", pinyipinReserve.getCancleStaus());
		resMap.put("claimStatus", pinyipinReserve.getClaimStatus());
		resMap.put("count", pinyipinReserve.getCount());
		resMap.put("id", pinyipinReserve.getId());
		resMap.put("pinyipinContentId", pinyipinReserve.gettCommunityPinyipinContentFId());
		resMap.put("deliveryAddressId", pinyipinReserve.gettEbuyDeliveryAddressFId());
		resMap.put("userId", pinyipinReserve.getUserId());
		if(user!=null){//用户信息
			resMap.put("ext_user", commEntityConvertService.baseUser2Map(user));
		}
		if(deliveryAddressEntity!=null){//配送地址信息
			resMap.put("ext_delvAddress", commEntityConvertService.delvAddressEntity2Map(deliveryAddressEntity, deliveryAddressEntity.getSimpleDelivAddress()));
		}
		
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		if(pinyipinReserve.getSys0AddTime()!=null){
			resMap.put("createTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(pinyipinReserve.getSys0AddTime()),nowTimeLong));
		}
		return resMap;
	}
	@Override
	public Map<String,Object> pinyipinContent2Map(String nowTime,BigInteger userId,CommunityPinyipinContent communityPinyipinContent){
		if(communityPinyipinContent==null){return null;}
		Map<String,Object> tmpMap = pinyipinContent2Map(nowTime,userId, communityPinyipinContent, null, null, null, null, null, null, null, null, null, null, null,null, null, null,null);
		return tmpMap;
	}
	@Override
	public Map<String,Object> pinyipinContent2Map(String nowTime,BigInteger userId,PinyipinContentEntity tmpPinyipinContentEntity){
		if(tmpPinyipinContentEntity==null){return null;}
		Map<String,Object> tmpMap = pinyipinContent2Map(nowTime,userId, tmpPinyipinContentEntity,tmpPinyipinContentEntity.getCreateUser(), tmpPinyipinContentEntity.getPinyipinPicList()
				,tmpPinyipinContentEntity.getReserveUserList(),tmpPinyipinContentEntity.getDeliverAble()
				,tmpPinyipinContentEntity.getUserBelong(),tmpPinyipinContentEntity.getReserveCount(),tmpPinyipinContentEntity.getCurrUserCount(),tmpPinyipinContentEntity.getCurrUserReserveCount()
			,tmpPinyipinContentEntity.getIsDeleteAble(),tmpPinyipinContentEntity.getIsEditAble(),tmpPinyipinContentEntity.getFirstComment()
			,tmpPinyipinContentEntity.getCommentTotalCount(),tmpPinyipinContentEntity.getIsSupported(),tmpPinyipinContentEntity.getTotalSupportCount()
			,tmpPinyipinContentEntity.getIsCollected());
		return tmpMap;
	}

	@Override
	public List<Map<String, Object>> pinyipinReserve2MapList(BigInteger userId,
			List<PinyipinReserveEntity> tmpPinyipinReserveEntityList) {
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(tmpPinyipinReserveEntityList!=null){
			String nowTime = dualDao.getNowTime();
			for(PinyipinReserveEntity tmpEntity:tmpPinyipinReserveEntityList){
				Map<String,Object> tmpMap = pinyipinReserve2Map(nowTime, tmpEntity, tmpEntity.getUser(), tmpEntity.getEbuyDeliveryAddressEntity());
				resList.add(tmpMap);
			}
		}
		return resList;
	}

	@Override
	public List<Map<String, Object>> pinyipinContent2MapList(BigInteger userId,
			List<PinyipinContentEntity> tmpPinyipinContentEntityList) {
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(tmpPinyipinContentEntityList!=null){
			String nowTime = dualDao.getNowTime();
			for(PinyipinContentEntity tmpEntity:tmpPinyipinContentEntityList){
				Map<String,Object> tmpMap = pinyipinContent2Map(nowTime, userId, tmpEntity);
				resList.add(tmpMap);
			}
		}
		return resList;
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
			messageAdd.setTitle(PinyipinConstant.Msgpush_Title);
			messageAdd.setType(msgType);
		}
		return messageAdd;
	}
	@Override
	public BigInteger addMsgPushInfo(BigInteger userId, String msgInfoKey, Object[] msgParams,Long msgType,BigInteger detailId) {
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
	

}
