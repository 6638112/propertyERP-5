/**   
* Filename:    CommEntityConvertService.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午11:03:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.comments.entity.CommentsHasTCommentsPointsEntity;
import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.commonBusiness.util.UserShowNameUtil;
import com.cnfantasia.server.api.communitySupply.entity.HomeSupplyType;
import com.cnfantasia.server.api.communitySupply.util.CommunitySupplyTypeUtil;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductShelf;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.SimpleDelivAddress;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.microblog.constant.MicroblogDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.room.entity.AddressBlockEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.api.support.constant.SupportDict;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftConstant;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftDict;
import com.cnfantasia.server.api.surpriseGift.entity.CouponCombiEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueConstant;
import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueDict;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
import com.cnfantasia.server.domainbase.commentsLabel.entity.CommentsLabel;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;
import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;
import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    CommEntityConvertService.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午11:03:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public class CommEntityConvertService implements ICommEntityConvertService{
//	private Log logger = LogFactory.getLog(getClass());
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	private ISysParamParser userImageParamParser;
	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
		this.userImageParamParser = userImageParamParser;
	}
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ISysParamParser microblogPicParamParser;
	public final void setMicroblogPicParamParser(ISysParamParser microblogPicParamParser) {
		this.microblogPicParamParser = microblogPicParamParser;
	}
	
	private ISysParamParser productPicSpecialPathParamParser;
	public void setProductPicSpecialPathParamParser(ISysParamParser productPicSpecialPathParamParser) {
		this.productPicSpecialPathParamParser = productPicSpecialPathParamParser;
	}
	
	private ISysParamParser kitchenCookbookPicParamParser;
	public void setKitchenCookbookPicParamParser(ISysParamParser kitchenCookbookPicParamParser) {
		this.kitchenCookbookPicParamParser = kitchenCookbookPicParamParser;
	}
	
	/**
	 * 附加用户拓展信息
	 * @param resMap
	 * @param baseUser
	 */
	private void appendRoomIsAdmin(Map<String,Object> resMap,UserSimpleEntity baseUser){
		resMap.put("ext_room_isAdmin", baseUser.fetchIsAdmin());//是否门牌管理员
	}
	
	@Override
	public Map<String, Object> baseUser2Map(UserSimpleEntity baseUser) {
		Map<String,Object> resMap = new SimpleResultMap<String, Object>();
		if(baseUser!=null){
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			resMap.put("userId", baseUser.getId());
			resMap.put("huaId", baseUser.getHuaId());
			resMap.put("nickName", baseUser.getNickName());
			resMap.put("imgProfile", StringUtils.isEmpty(baseUser.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+baseUser.getImgprofile(),baseUser));
			resMap.put("signature", baseUser.getSignature());//个人签名
			resMap.put("mobile", baseUser.getMobile());
			resMap.put("realName", baseUser.getRealName());
			resMap.put("sex", baseUser.getSex());
			resMap.put("birthday", baseUser.getBirthday());
            resMap.put("role", baseUser.getFamilyRole());
			
			appendRoomIsAdmin(resMap, baseUser);//附加用户拓展信息
		}
		return resMap;
	}
	@Override
	public Map<String,Object> baseUser2MapForApply(UserSimpleEntity baseUser,Integer applyStatus,BigInteger applyId){
		Map<String,Object> resMap = baseUser2Map(baseUser);
		resMap.put("applyStatus", applyStatus);//0:未处理;1: 已同意;2:已拒绝 ;(目前只返回状态为0和1的数据)
		if(applyId!=null){
			resMap.put("applyId", applyId);
		}
		return resMap;
	}
	@Override
	public Map<String, Object> baseUser2MapForPrizeRecordDiscount2hbPreQry(UserSimpleEntity hbUser) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(hbUser!=null){
			tmpMap.put("prizeUserInfo", UserShowNameUtil.getUserShowName(hbUser));
			tmpMap.put("prizeUserId", hbUser.getId());
			appendRoomIsAdmin(tmpMap, hbUser);//附加用户拓展信息
		}else{
			tmpMap.put("prizeUserInfo", null);
			tmpMap.put("prizeUserId", null);
		}
		return tmpMap;
	}
	@Override
	public Map<String, Object> baseUser2MapForHbRecord(UserSimpleEntity hbUser) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(hbUser!=null){
			tmpMap.put("userId", hbUser.getId());
			tmpMap.put("userHuaId", hbUser.getHuaId());
			tmpMap.put("userName", hbUser.getRealName());
			appendRoomIsAdmin(tmpMap, hbUser);//附加用户拓展信息
		}
		return tmpMap;
	}
	@Override
	public Map<String, Object> baseUser2MapForPrizeRecord(UserSimpleEntity prizeUser) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(prizeUser!=null){
			tmpMap.put("userShowName", UserShowNameUtil.getUserShowName(prizeUser));
			tmpMap.put("userId", prizeUser.getId());
			tmpMap.put("nickName", prizeUser.getNickName());
			tmpMap.put("userName", prizeUser.getRealName());
			tmpMap.put("userHuaId", prizeUser.getHuaId());
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			tmpMap.put("imgProfile",  StringUtils.isEmpty(prizeUser.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+prizeUser.getImgprofile(),prizeUser));
			appendRoomIsAdmin(tmpMap, prizeUser);//附加用户拓展信息
		}
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> baseUser2MapForLoginRes(UserSimpleEntity loginUserEntity){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(loginUserEntity!=null){
			tmpMap.put("realName", loginUserEntity.getRealName());
			tmpMap.put("nickName", loginUserEntity.getNickName());
			tmpMap.put("userId", loginUserEntity.getId());
			tmpMap.put("mobile", loginUserEntity.getMobile());
			tmpMap.put("phoneBindState", loginUserEntity.getPhoneBindState());
			tmpMap.put("sex", loginUserEntity.getSex());
			tmpMap.put("birthday", loginUserEntity.getBirthday());
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			tmpMap.put("imgProfile",StringUtils.isEmpty(loginUserEntity.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath() + loginUserEntity.getImgprofile(),loginUserEntity));
			tmpMap.put("defaultRoomId", loginUserEntity.getDefaultRoomId());
			tmpMap.put("huaId", loginUserEntity.getHuaId());
			tmpMap.put("inviteNo", loginUserEntity.getInviteNo());
			appendRoomIsAdmin(tmpMap, loginUserEntity);//附加用户拓展信息
		}
		return tmpMap;
	}
	
	public Map<String,Object> baseUser2MapForCommentsUser(UserSimpleEntity commentsUser){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(commentsUser!=null){
			tmpMap.put("userId", commentsUser.getId());
			tmpMap.put("userName", commentsUser.getNickName());
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			tmpMap.put("userPicUrl", StringUtils.isEmpty(commentsUser.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+commentsUser.getImgprofile(),commentsUser));
			appendRoomIsAdmin(tmpMap, commentsUser);//附加用户拓展信息
		}
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> baseUser2MapForCommentsNoticeUser(UserSimpleEntity commentsNoticeUser){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(commentsNoticeUser!=null){
			tmpMap.put("id", commentsNoticeUser.getId());
			tmpMap.put("userName", commentsNoticeUser.getNickName());
			appendRoomIsAdmin(tmpMap, commentsNoticeUser);//附加用户拓展信息
		}
		return tmpMap;
	}
	@Override
	public Map<String,Object> baseUser2MapForCommunityForumContent(UserSimpleEntity communityForumUser){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(communityForumUser!=null){
			tmpMap.put("userName", communityForumUser.getNickName());
			tmpMap.put("userId", communityForumUser.getId());
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			tmpMap.put("userPicUrl", StringUtils.isEmpty(communityForumUser.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+communityForumUser.getImgprofile(),communityForumUser));
			appendRoomIsAdmin(tmpMap, communityForumUser);//附加用户拓展信息
		}
		return tmpMap;
	}
	@Override
	public Map<String,Object> baseUser2MapForMicroContent(UserSimpleEntity microContentUser){
		return baseUser2MapForCommunityForumContent(microContentUser);
	}
	
	@Override
	public Map<String, Object> baseUser2MapForEbuyBuyCar(UserSimpleEntity buyerUser) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(buyerUser!=null){
			tmpMap.put("name", UserShowNameUtil.getUserShowName(buyerUser));
			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
			tmpMap.put("imgProfile", StringUtils.isEmpty(buyerUser.getImgprofile())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+buyerUser.getImgprofile(),buyerUser));
		}
		appendRoomIsAdmin(tmpMap, buyerUser);//附加用户拓展信息
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> personInfo2Map(UserSimpleEntity baseUser,RoomEntityWithValidate roomEntity,List<Achievement> achievementList,List<Hobby> hobbyList){
		if(baseUser==null){return null;}
		Map<String,Object> resMap = baseUser2Map(baseUser);
		resMap.put("inviteNo", baseUser.getInviteNo());//syl-add 2015-4-16 11:49:57
		if(roomEntity!=null){
			Map<String,Object> defaultRoomInfoMap = CommonRoomUtil.getRoomInfo(baseUser.getId(),roomEntity, roomEntity.getRoomValidate(), roomEntity.getId());
			resMap.put("defaultRoomInfo",defaultRoomInfoMap);
		}else{
			resMap.put("defaultRoomInfo",null);
		}
		if (achievementList != null) {//个人成就列表
			List<Map<String,Object>> achieveResList = new ArrayList<Map<String,Object>>();
			if(achievementList.size()>0){
				for(Achievement tmpAchievement:achievementList){
					Map<String,Object> tmpMap = achievement2Map(tmpAchievement);
					achieveResList.add(tmpMap);
				}
			}
			resMap.put("achievementList",achieveResList);
		}
		if (hobbyList != null) {//个人兴趣列表
			List<Map<String,Object>> hobbyResList = new ArrayList<Map<String,Object>>();
			if(hobbyList.size()>0){
				for(Hobby tmpHobby:hobbyList){
					Map<String,Object> tmpMap = hobby2Map(tmpHobby,null);
					hobbyResList.add(tmpMap);
				}
			}
			resMap.put("hobbyList",hobbyResList);
		}
		return resMap;
	}
	@Override
	public Map<String,Object> achievement2Map(Achievement tmpAchievement){
		if(tmpAchievement==null){return null;}
		String achievementIco = sysParamManager.getSysParaValue(SysParamKey.ACHIEVEMENT_PIC_BASEPATH);
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", tmpAchievement.getId());
		tmpMap.put("name", tmpAchievement.getName());
		tmpMap.put("iconUrl",StringUtils.isEmpty(tmpAchievement.getIconUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(achievementIco+tmpAchievement.getIconUrl(),tmpAchievement));
		return tmpMap;
	}
	@Override
	public Map<String, Object> achievement2MapForPrizeHonour(Achievement tmpAchievement){
		if(tmpAchievement==null){return null;}
		String achievementIco = sysParamManager.getSysParaValue(SysParamKey.ACHIEVEMENT_PIC_BASEPATH);
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("ext_honour_name", tmpAchievement.getName());
		tmpMap.put("ext_honour_icon_url",StringUtils.isEmpty(tmpAchievement.getIconUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(achievementIco+tmpAchievement.getIconUrl(),tmpAchievement));
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> hobby2Map(Hobby tmpHobby,Integer haveSelectFlag){
		if(tmpHobby==null){return null;}
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		String hobbyIco = sysParamManager.getSysParaValue(SysParamKey.HOBBY_PIC_BASEPATH);
		tmpMap.put("id", tmpHobby.getId());
		tmpMap.put("name", tmpHobby.getName());
		tmpMap.put("iconUrl",StringUtils.isEmpty(tmpHobby.getIconUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(hobbyIco+tmpHobby.getIconUrl(),tmpHobby));
		tmpMap.put("iconUrlSelect",StringUtils.isEmpty(tmpHobby.getIconUrlSelect())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(hobbyIco+tmpHobby.getIconUrlSelect(),tmpHobby));
		if (haveSelectFlag != null) {//用户是否已经选中 haveSelectFlag
			boolean resBool = false;
			if(haveSelectFlag!=null&&UserDict.Hobby_HaveSelectFlag.YES.compareTo(haveSelectFlag)==0){
				resBool = true;
			}
			tmpMap.put("haveSelectFlagBool", resBool);
		}
		return tmpMap;
	}
	
	/**
	 * 收获地址信息转Map
	 * @param delvAddress
	 * @param simpleDelivAddress
	 * @return
	 */
	@Override
	public Map<String,Object> delvAddressEntity2Map(EbuyDeliveryAddressEntity delvAddress, SimpleDelivAddress  simpleDelivAddress){
		if(delvAddress==null){return null;}
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", delvAddress.getId());
		tmpMap.put("targetType", delvAddress.getTargetType());
		if(StringUtils.isEmpty(delvAddress.getIsdefault())){
			tmpMap.put("isDefault", null);
		}else{
			if(EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.compareTo(delvAddress.getIsdefault())==0){
				tmpMap.put("isDefault", EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE);
			}else if(EbuyDict.EbuyDeliveryAddress_ISDEFAULT.FALSE.compareTo(delvAddress.getIsdefault())==0){
				tmpMap.put("isDefault", EbuyDict.EbuyDeliveryAddress_ISDEFAULT.FALSE);
			}else{
				tmpMap.put("isDefault", null);
			}
		}
//		map02.put("provinceId", "1");
		//		map02.put("provinceName", "广东省");
//		map02.put("cityId", "2");
		//		map02.put("cityName", "深圳市");
//		map02.put("blockId", "3");
		//		map02.put("blockName", "福田区");
//		map02.put("groupBuildingId", "4");
		//		map02.put("groupBuildingName", "福年广场");
		tmpMap.put("userName", delvAddress.getPeopleName());
		tmpMap.put("userPhone", delvAddress.getPhone());
		if(simpleDelivAddress!=null){
			tmpMap.put("addressArea", simpleDelivAddress.getAddressArea());
			tmpMap.put("addressDetail", delvAddress.getAddressDetail());
			tmpMap.put("addressTotal", delvAddress.getAddressDetail());
			tmpMap.put("targetId", simpleDelivAddress.getTargetId());
		}
		return tmpMap;
	}
	
	@Override
	public Map<String, Object> comments2Map(Comments comment,GroupBuilding userGroupBuilding, UserSimpleEntity userInfo,List<UserSimpleEntity> noticeUserList,List<CommentsLabel> commentsLabelList,List<CommentsHasTCommentsPointsEntity> commentsHasTCommentsPointsEntityList,Double totalStarLevel) {
		if(comment==null){return null;}
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
//		UserImageParamEntity userImageParamEntity=userImageParamParser.parseParamValue();
		tmpMap.put("id", comment.getId());
		tmpMap.put("starLevel", comment.getLevel() == null ? 0 : comment.getLevel());//syl-add 2015-1-30 11:24:30 评论星级
		//		tmpMap.put("costPerformLevel", comment.getCostPerformLevel()==null?0:comment.getCostPerformLevel());//性价比分数
		//		tmpMap.put("techLevel", comment.getTechLevel()==null?0:comment.getTechLevel());//技师评分
		//		tmpMap.put("envLevel", comment.getEnvLevel()==null?0:comment.getEnvLevel());//环境评分
		if(commentsLabelList!=null&&commentsLabelList.size()>0){
			StringBuffer preContent = new StringBuffer();
			for(CommentsLabel tmpCommentsLabel:commentsLabelList){
				preContent.append(tmpCommentsLabel.getName() + "，");
			}
			if(comment.getContent()!=null){
				tmpMap.put("txtContent",preContent.toString()+comment.getContent());
			}else{
				tmpMap.put("txtContent",preContent.toString());
			}
		}else{
			tmpMap.put("txtContent",comment.getContent());
		}
		if (userGroupBuilding != null) {//syl-add 2015-1-30 11:24:30 所属小区信息
			tmpMap.put("ext_user_BelongGbName",userGroupBuilding.getName()==null?"":userGroupBuilding.getName());
		}
		if(userInfo!=null){
			Map<String,Object> tmpUserMap = baseUser2MapForCommentsUser(userInfo);
			tmpMap.putAll(tmpUserMap);
//			tmpMap.put("userId", userInfo.getId());
//			tmpMap.put("userName", userInfo.getNickName());
//			if(userInfo.getImgprofile()!=null){
//				tmpMap.put("userPicUrl", fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+userInfo.getImgprofile(),userInfo));
//			}else{
//				tmpMap.put("userPicUrl",null);
//			}
		}
		tmpMap.put("time",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(comment.getTime()),nowTimeLong));
		if(noticeUserList!=null){
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			for(UserSimpleEntity tmpNoticeUser:noticeUserList){
				Map<String,Object> tmpUserMap = baseUser2MapForCommentsNoticeUser(tmpNoticeUser);
				tmpList.add(tmpUserMap);
//				Map<String,Object> aMap = new SimpleResultMap<String, Object>();
//				aMap.put("id", tmpNoticeUser.getId());
//				aMap.put("userName", tmpNoticeUser.getNickName());
//				tmpList.add(aMap);
			}
			tmpMap.put("noticeUserList", tmpList);
		}
		//评论包含的评分项信息
		if(commentsHasTCommentsPointsEntityList!=null){
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			for(CommentsHasTCommentsPointsEntity tmpEntity:commentsHasTCommentsPointsEntityList){
				Map<String,Object> tmpPointMap = new SimpleResultMap<String, Object>();
				tmpPointMap.put("id", tmpEntity.getId());
				tmpPointMap.put("name", tmpEntity.getCommentsPoints().getName());
				tmpPointMap.put("value", tmpEntity.getValue());
				tmpList.add(tmpPointMap);
			}
			tmpMap.put("pointsList", tmpList);
		}
		//评论星级总评分平均值
		if(totalStarLevel!=null){
			tmpMap.put("ext_totalStarLevel", totalStarLevel);
		}
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> communitySupply2Map(CommunitySupply communitySupply
			,List<CommunitySupplyContect> communitySupplyContectList,CommunitySupplyType topCommunitySupplyType){

		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", communitySupply.getId());
		tmpMap.put("addressDetail", communitySupply.getAddressDetail());
		if(communitySupply.getAvgConsume()!=null){
			tmpMap.put("avgConsume", PriceUtil.div100(communitySupply.getAvgConsume()));
		}
		tmpMap.put("desc", communitySupply.getDesc());
		tmpMap.put("name", communitySupply.getName());
		tmpMap.put("supplyTypeId", communitySupply.gettCommunitySupplyTypeFId());//所属类别Id
		if (topCommunitySupplyType != null) {//所属顶级类别Id
			tmpMap.put("supplyTypeTopId", topCommunitySupplyType.getId());
		}else{
			tmpMap.put("supplyTypeTopId", null);
		}
		tmpMap.put("openHoursDesc", communitySupply.getOpenHoursDesc());//营业时间
		tmpMap.put("picUrl", StringUtils.isEmpty(communitySupply.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_BASIC_PICPATH)+communitySupply.getPicUrl(), communitySupply));
		
		if(communitySupplyContectList!=null){
			List<Map<String,Object>> aaListMap = new ArrayList<Map<String,Object>>();
			Long totalCallCount = 0L;
			for(CommunitySupplyContect tmpSupplyContect:communitySupplyContectList){
				Map<String,Object> aaMap = new SimpleResultMap<String,Object>();
				aaMap.put("clickCount", tmpSupplyContect.getClickCount());
				aaMap.put("contectInfo", tmpSupplyContect.getContectInfo());
				aaMap.put("esqName", tmpSupplyContect.getEsqName());
				aaMap.put("userIdentity", tmpSupplyContect.getUserIdentity());
				aaMap.put("id", tmpSupplyContect.getId());
				aaMap.put("type", tmpSupplyContect.getType());
				aaListMap.add(aaMap);
				if(tmpSupplyContect.getClickCount()!=null){
					totalCallCount+=tmpSupplyContect.getClickCount();
				}
			}
			tmpMap.put("ext_ContectList", aaListMap);
			tmpMap.put("ext_ContectTotalCallCount", totalCallCount);//总拨打次数
		}
		
		tmpMap.put("ext_starLevel", communitySupply.getStarLevel() == null ? 0 : communitySupply.getStarLevel());//星级
		//		tmpMap.put("ext_distance", communitySupply.getDistance()==null?null:communitySupply.getDistance()+"m");//距离 数据应存储于小区商家关系表中
		//		tmpMap.put("ext_jfqSuggest", "2");//解放区推荐
		return tmpMap; 
	}
	
	@Override
	public Map<String,Object> propertyManagement2Map(PropertyManagement propertyManagement){
		Map<String,Object> resMap = new SimpleResultMap<String, Object>();
		if(propertyManagement!=null){
			resMap.put("id", propertyManagement.getId());
			resMap.put("tel", propertyManagement.getTel());
		}else{
			resMap.put("id", null);
			resMap.put("tel", "暂无");
		}
		return resMap;
	}
	
	@Override
	public Map<String,Object> groupBuilding2Map(GroupBuilding groupBuilding,AddressBlockEntity addressBlockEntity,PropertyManagement propertyManagement){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		if(groupBuilding!=null){
			tmpMap.put("id", groupBuilding.getId());
			tmpMap.put("name", groupBuilding.getName());
			tmpMap.put("signStatus", groupBuilding.getSignStatus());
			if(addressBlockEntity!=null){
				tmpMap.put("totalName", addressBlockEntity.getAddressCityEntity().getName()+addressBlockEntity.getName()+groupBuilding.getName());
				tmpMap.put("blockId", addressBlockEntity.getId());
				tmpMap.put("blockName", addressBlockEntity.getName());
				if (addressBlockEntity.getAddressCityEntity() != null) {
					tmpMap.put("cityId", addressBlockEntity.getAddressCityEntity().getId());
					tmpMap.put("cityName", addressBlockEntity.getAddressCityEntity().getName());
					tmpMap.put("provinceId", addressBlockEntity.getAddressCityEntity().gettProvinceFId());
					tmpMap.put("provinceName", addressBlockEntity.getAddressCityEntity().getAddressProvinceEntity().getName());
				}
			}
			if (propertyManagement != null) {//物业处信息
				Map<String,Object> propManagent = propertyManagement2Map(propertyManagement);
				tmpMap.put("ext_propertyManagement", propManagent);
			}
		}else{
			tmpMap.put("id",null);
		}
		return tmpMap;
	}
	

	
	@Override
	public Map<String, Object> building2Map(Building building) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", building.getId());
//		String name = building.getName();
//		if(name!=null&&isNumeric(name)){
		//			name = name+"栋";
//		}
		String name = RoomEntityConvertUtil.getBuildingShowName(building);
		tmpMap.put("name", name);
		return tmpMap;
	}
	@Override
	public Map<String, Object> building2Map(Building building, BigInteger buildingId,String buildingName) {
		Map<String,Object> tmpMap = building2Map(building);
		if(buildingId!=null&&building.getId().compareTo(buildingId)==0){
			tmpMap.put("select", true);
		}
		if(buildingName!=null&&buildingName.trim().equals(building.getName())){
			tmpMap.put("select", true);
		}
		return tmpMap;
	}
	@Override
	public Map<String, Object> realRoom2Map(RealRoom realRoom) {
		Map<String, Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", realRoom.getId());
//		StringBuffer nameSbf = new StringBuffer();
//		if(!StringUtils.isEmpty(realRoom.getUnitName())){
//			if(isNumeric(realRoom.getUnitName())&&isNumeric(realRoom.getNumTail())){
		//				nameSbf.append(realRoom.getUnitName()+"单元");
//				nameSbf.append(realRoom.getNumTail());
//			}else{
////				nameSbf.append(realRoom.getUnitName());
////				nameSbf.append(realRoom.getNumTail());
//				nameSbf.append(realRoom.getNum());
//			}
//		}else{
//			nameSbf.append(realRoom.getNum());
//		}
		String nameShow = RoomEntityConvertUtil.getRealRoomShowName(realRoom);
		tmpMap.put("name", nameShow);
		tmpMap.put("num", nameShow);//保留，为了兼容以前，其实取值跟name相同
		return tmpMap;
	}

	@Override
	public Map<String, Object> realRoom2Map(RealRoom realRoom, BigInteger realRoomId,String realRoomNum) {
		Map<String,Object> tmpMap = realRoom2Map(realRoom);
		if(realRoomId!=null&&realRoom.getId().compareTo(realRoomId)==0){
			tmpMap.put("select", true);
		}
		if(realRoomNum!=null&&realRoomNum.trim().equals(realRoom.getNum())){
			tmpMap.put("select", true);
		}
		return tmpMap;
	}

	@Override
	public Map<String,Object> prizeRecord2Map(PrizeRecord prizeRecord,UserSimpleEntity prizeUser,Boolean isUsed) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("prizeTime", prizeRecord.getPrizeTime());
		tmpMap.put("prizeTimeLong", DateUtil.timeToLong(prizeRecord.getPrizeTime(),DateUtil.formatSecond.get()));
		tmpMap.put("endTime", prizeRecord.getEndTime());
		tmpMap.put("discount", prizeRecord.getDiscount());
		if(prizeUser!=null){
			Map<String,Object> userMap = baseUser2MapForPrizeRecord(prizeUser);
			tmpMap.putAll(userMap);
//			tmpMap.put("userShowName", UserShowNameUtil.getUserShowName(prizeUser));
//			tmpMap.put("userId", prizeUser.getId());
//			tmpMap.put("nickName", prizeUser.getNickName());
//			tmpMap.put("userName", prizeUser.getRealName());
//			tmpMap.put("userHuaId", prizeUser.getHuaId());
//			UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
//			tmpMap.put("imgProfile",  fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+prizeUser.getImgprofile(),prizeUser));
		}
		if(isUsed!=null){
			tmpMap.put("isUsed", isUsed);//是否使用的标识
		}
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> ebuyProductParameters2Map(EbuyProductParameters tmpEbuyProductParameters){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", tmpEbuyProductParameters.getId());
		tmpMap.put("tEbuyProductFId", tmpEbuyProductParameters.gettEbuyProductFId());
		tmpMap.put("tPropName", tmpEbuyProductParameters.gettPropName());
		tmpMap.put("tPropValue", tmpEbuyProductParameters.gettPropValue());
		return tmpMap;
	}
	@Override
	public Map<String,Object> ebuyProductType2Map(EbuyProductType ebuyProductType){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", ebuyProductType.getId());
		tmpMap.put("typeName", ebuyProductType.getTypeName());
		return tmpMap;
	}
	@Override
	public Map<String, Object> ebuyDeliveryMethod2Map(EbuyDeliveryMethod deliveryMethod) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", deliveryMethod.getId());
		tmpMap.put("name", deliveryMethod.getName());
		tmpMap.put("fee", deliveryMethod.getFee());
		if(deliveryMethod.getFee()!=null){
			tmpMap.put("feeYuan", PriceUtil.div100(deliveryMethod.getFee()));
		}else{
			tmpMap.put("feeYuan", null);
		}
		tmpMap.put("fastTime", deliveryMethod.getFastTime());
		tmpMap.put("desc", deliveryMethod.getDesc());
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> pointData2Map(PointData pointData){
		Map<String,Object> resMap = new SimpleResultMap<String, Object>();
		if(pointData!=null){
			resMap.put("id", pointData.getId());
			resMap.put("pointValue", pointData.getPointValue());
			resMap.put("userId", pointData.gettUserFId());
		}else{
			resMap.put("id", null);
			resMap.put("pointValue", 0);
			resMap.put("userId", null);
		}
		return resMap;
	}
	
	private Integer getSourceType(MicroblogContent tmpObj){
		Integer resType = null;
		if(tmpObj!=null){
			resType = tmpObj.getSourceType().compareTo(MicroblogDict.MicroblogContent_SourceType.CommMsg)==0?MicroblogDict.MicroblogContent_SourceType.CommMsg:MicroblogDict.MicroblogContent_SourceType.SysMsg;
		}
		return resType;
	}
	private Long getShowType(MicroblogContent tmpObj){
		Long resType = null;
		if(tmpObj!=null){
			if(tmpObj.getShowType()!=null){
				resType = tmpObj.getShowType();
			}else{
				resType = tmpObj.getSourceType().longValue();
			}
		}
		return resType;
	}
	@Override
	public Map<String,Object> microblogContent2Map(MicroblogContent tmpObj,List<MicroblogPic> microblogPicList,UserSimpleEntity userInfo,Long nowTimeLong,Integer isFavour,Integer favourCount,Integer commentCount,boolean isListData){
		String microBlogBasePath = microblogPicParamParser.parseParamValue();
//		UserImageParamEntity userImageParamEntity=userImageParamParser.parseParamValue();
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", tmpObj.getId());
		tmpMap.put("sourceType", getSourceType(tmpObj));//syl-add-2015-7-6 10:42:13 消息类别区分普通消息还是系统消息
		//		tmpMap.put("showType", getShowType(tmpObj));//syl-add-2015-7-13 11:12:37  显示类别
		try {
			tmpMap.put("createTime",  RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(tmpObj.getCreateTime()),nowTimeLong));
		} catch (Exception e) {
			tmpMap.put("createTime","");
//			logger.error(e);
		}
		
		{
			List<String> picList = new ArrayList<String>();
			if(microblogPicList!=null&&microblogPicList.size()>0){
				for(MicroblogPic tmpPic:microblogPicList){
					picList.add(StringUtils.isEmpty(tmpPic.getUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(microBlogBasePath+tmpPic.getUrl(),tmpPic));
				}
			} else {//系统消息的图片展示
				String tmmPicUrl = MicroblogQueueDict.MQ_Level.Map_Code_Pic.get(getShowType(tmpObj));
				if(!StringUtils.isEmpty(tmmPicUrl)){
					picList.add(StringUtils.isEmpty(tmmPicUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(microBlogBasePath+tmmPicUrl,tmpObj));
				}
			}
			tmpMap.put("picList", picList);
		}
		
		if(userInfo!=null){
			Map<String,Object> tmpUserMap = baseUser2MapForMicroContent(userInfo);
			tmpMap.putAll(tmpUserMap);
//			tmpMap.put("userName", userInfo.getNickName());
//			tmpMap.put("userId", userInfo.getId());
//			if(userInfo.getImgprofile()!=null){
//				tmpMap.put("userPicUrl", fileServerService.getAccessUrl(userImageParamEntity.getBasePath()+userInfo.getImgprofile(),userInfo));
//			}else{
//				tmpMap.put("userPicUrl",null);
//			}
		}
		if(favourCount==null){favourCount=0;}
		tmpMap.put("favourCount", favourCount);
		if(commentCount==null){commentCount=0;}
		tmpMap.put("commentCount", commentCount);
		if(isFavour!=null){
			if(SupportDict.Support_IsFavour.YES.compareTo(isFavour)==0){
				tmpMap.put("isFavour", true);
			}else{
				tmpMap.put("isFavour", false);
			}
		}else{
			tmpMap.put("isFavour", false);
		}
		
		tmpMap.put("txt", tmpObj.getText());
		tmpMap.put("activityName", tmpObj.getActivityName());
		//喜报信息，跳转到详情页面
		if(MicroblogQueueDict.MQ_Level.Code_40.compareTo(getShowType(tmpObj))==0){
			String currServerBaseUrl = sysParamManager.getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
			tmpMap.put("linkDetail", currServerBaseUrl+"microblog/linkDetail.html?"+MicroblogQueueConstant.Param_MicroBlogId+"="+tmpObj.getId());
			tmpMap.put("linkTitle", "详情");
		}
		return tmpMap;
	}

	@Override
	public Map<String, Object> communitySupplyType2Map(CommunitySupplyType communitySupplyType,
			CommunitySupplyType topCommunitySupplyType) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", communitySupplyType.getId());
		if(communitySupplyType.getImportanceLevel()!=null){
			String iconUrl = CommunitySupplyTypeUtil.getIconName(communitySupplyType);
//			Integer level = communitySupplyType.getImportanceLevel();
//			String iconUrl = null;
//			if(CommunitySupplyDict.CommunitySupply_Type_Importance_Level.level01.compareTo(level)==0){
//				iconUrl = communitySupplyType.getIconBigUrl();
//			}else if(CommunitySupplyDict.CommunitySupply_Type_Importance_Level.level02.compareTo(level)==0){
//				iconUrl = communitySupplyType.getIconSmallUrl();
//			}
			tmpMap.put("iconUrl",StringUtils.isEmpty(iconUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH)+iconUrl, communitySupplyType));
		}
		tmpMap.put("name", communitySupplyType.getName());
		if(topCommunitySupplyType!=null){
			tmpMap.put("topParentId", topCommunitySupplyType.getId());
		}else{
			tmpMap.put("topParentId",null);
		}
		return tmpMap;
	}
	
	@Override
	public Map<String, Object> communitySupplyType2Map(HomeSupplyType homeSupplyType) {
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", homeSupplyType.getId());
		tmpMap.put("name", homeSupplyType.getName());
		tmpMap.put("topParentId", homeSupplyType.getTopParentId());
		tmpMap.put("supplyTypeId", homeSupplyType.getSupplyTypeId());//syl-add-2015-8-20 18:09:03关联到的商家类别Id
		{
			String iconUrl = homeSupplyType.getIconName();
			String lastUpdTime = homeSupplyType.getLastUpdTime();
//		tmpMap.put("iconUrl", homeSupplyType.getIconUrl());
			tmpMap.put("iconUrl",StringUtils.isEmpty(iconUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH)+iconUrl, lastUpdTime));
		}
		tmpMap.put("isAppUrl", homeSupplyType.getIsAppUrl());
		tmpMap.put("isHot", homeSupplyType.getIsHot());
		tmpMap.put("isLink", homeSupplyType.getIsLink());
		tmpMap.put("linkUrl", homeSupplyType.getLinkUrl());
		if(homeSupplyType.getSubList()!=null&&homeSupplyType.getSubList().size()>0){
			List<Map<String,Object>> subListResult = new ArrayList<Map<String,Object>>();
			List<HomeSupplyType> subList = homeSupplyType.getSubList();
			for(HomeSupplyType subHomeSupplyType:subList){
				subListResult.add(communitySupplyType2Map(subHomeSupplyType));
			}
			tmpMap.put("subList", subListResult);
		}
		tmpMap.put("code", homeSupplyType.getCode());
		tmpMap.put("desc", homeSupplyType.getDesc());
		tmpMap.put("propfeeCanpay", homeSupplyType.getPropfeeCanpay());
		tmpMap.put("verifyStatus", homeSupplyType.getVerifyStatus());
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> commentsLabel2Map(CommentsLabel commentsLabel){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", commentsLabel.getId());
		tmpMap.put("name", commentsLabel.getName());
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> commentsPoints2Map(CommentsPoints commentsPoints){
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", commentsPoints.getId());
		tmpMap.put("name", commentsPoints.getName());
		return tmpMap;
	}
	
	/**
	 * 产品信息转Map
	 * @param ebuyProductInfo 产品基本信息
	 * @return
	 */
	@Override
//	public Map<String,Object> productInfo2Map(EbuyProduct ebuyProductInfoBase,EbuyProductSpec ebuyProductSpec){
	public Map<String,Object> productInfo2Map(EbuyProductWithCurrProductSpec ebuyProductInfo){
		String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
		String picSpecialBasePath = productPicSpecialPathParamParser.parseParamValue();//产品特殊图片信息根路径
		
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("sosName", ebuyProductInfo.doEntity().getSosName());
		tmpMap.put("sosIntroduceUrl", ebuyProductInfo.doEntity().getSosIntroduceUrl());
		tmpMap.put("productSpecId", ebuyProductInfo.getProductSpecId());//syl-add-2015-3-24 14:24:58 商品规格Id
		if(ebuyProductInfo.getShelfProduct() == null) {
			tmpMap.put("id", ebuyProductInfo.getId());
		} else {
			tmpMap.put("id", ebuyProductInfo.getShelfProduct().getId());
		}
		
		tmpMap.put("name", ebuyProductInfo.getName());
		if(ebuyProductInfo.getPrice()!=null){
			tmpMap.put("price", PriceUtil.div100(ebuyProductInfo.getPrice()));
		}
		tmpMap.put("priceUnit", ebuyProductInfo.getPriceUnit()==null?"":ebuyProductInfo.getPriceUnit());
		tmpMap.put("specification", ebuyProductInfo.getSpecification()==null?"":ebuyProductInfo.getSpecification());
		tmpMap.put("desc", ebuyProductInfo.getDesc()==null?"":ebuyProductInfo.getDesc());
		tmpMap.put("priceDiscount", PriceUtil.div100(ebuyProductInfo.getPriceDiscount()));
		tmpMap.put("selNum", ebuyProductInfo.getSelNum());
		tmpMap.put("leftCount", ebuyProductInfo.getLeftCount());
		tmpMap.put("pointType", ebuyProductInfo.getPointType());//商品归类=={"1":"电商商品","2":"积分商品"}
		tmpMap.put("pricePoint", ebuyProductInfo.getPricePoint());//积分原始价
		tmpMap.put("priceDiscountPoint", ebuyProductInfo.getPriceDiscountPoint() == null ? null : (PriceUtil.div100(ebuyProductInfo.getPriceDiscountPoint())));//积分折扣价 元
		tmpMap.put("specialProductType", ebuyProductInfo.getSpecialProductType());//特殊商品类别=={"1":"普通商品","2":"手机话费","3":"现金券"}
		{
			tmpMap.put("applicationRang", ebuyProductInfo.getApplicationRang());//适用范围
			tmpMap.put("attentionInfo", ebuyProductInfo.getAttentionInfo());//注意事项
			tmpMap.put("endTime", ebuyProductInfo.getEndTime());//截止时间
			if(ebuyProductInfo.getEndTime()!=null){
				tmpMap.put("endTimeLong", DateUtil.timeToLong(ebuyProductInfo.getEndTime()));//截止时间Long
			}
			tmpMap.put("convertProcess", ebuyProductInfo.getConvertProcess());//兑换流程
		}
		//积分价格描述
		if(ebuyProductInfo.getPriceDiscountPoint()!=null){
			StringBuffer sbf = new StringBuffer();
			if(ebuyProductInfo.getSpecialProductType().compareTo(EbuyDict.Product_SpecialType.PhoneFee)==0){
				if(StringUtils.isEmpty(ebuyProductInfo.getNameMini())){
					sbf.append(ebuyProductInfo.getName());
				}else{
					sbf.append(ebuyProductInfo.getNameMini());
				}
				sbf.append("=");
			}
			sbf.append(ebuyProductInfo.getPricePoint());
			tmpMap.put("priceDiscountPointDesc", sbf.toString());//积分价描述
		}
		
		String picBase = null;
		String picBaseMini = null;
		String picSpecial = null;
		picBase = StringUtils.isEmpty(ebuyProductInfo.getPicBase())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+ebuyProductInfo.getPicBase(),ebuyProductInfo);
		picBaseMini = StringUtils.isEmpty(ebuyProductInfo.getPicBaseMini())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+ebuyProductInfo.getPicBaseMini(),ebuyProductInfo);
		picSpecial = StringUtils.isEmpty(ebuyProductInfo.getPicSpecial())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(picSpecialBasePath+ebuyProductInfo.getPicSpecial(),ebuyProductInfo);
		tmpMap.put("picBase", picBase);
		tmpMap.put("picBaseMini", picBaseMini);
		tmpMap.put("picSpecial", picSpecial);
		tmpMap.put("filmTickets", ebuyProductInfo.getFilmTicketNum() == null ? 0 : ebuyProductInfo.getFilmTicketNum());
		
		EbuyProductShelf prodShelf = ebuyProductInfo.getShelfProduct();
		if(prodShelf != null) {
			tmpMap.put("opName", prodShelf.getOpName());
			tmpMap.put("opType", prodShelf.getOpType());
			tmpMap.put("canBuyNum", prodShelf.getCanBuyNum());
		}
		
		return tmpMap;
	}
	
	@Override
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook) {
		String cookbookPicBasePath = kitchenCookbookPicParamParser.parseParamValue();
		Map<String, Object> resMap = new SimpleResultMap<String, Object>();
		resMap.put("id", kitchenCookbook.getId());
		resMap.put("name", kitchenCookbook.getName());
		resMap.put("picUrl", StringUtils.isEmpty(kitchenCookbook.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(cookbookPicBasePath + kitchenCookbook.getPicUrl(),kitchenCookbook));
		resMap.put("desc", kitchenCookbook.getDesc());
        resMap.put("effect", kitchenCookbook.getEffect());
		resMap.put("eatWeight", kitchenCookbook.getEatWeight());
		resMap.put("cookTime", kitchenCookbook.getCookTime());
		// resMap.put("cookStep", kitchenCookbook.getCookStep());//更改为使区分步骤的
		resMap.put("taste", kitchenCookbook.getTaste());
		resMap.put("cookTech", kitchenCookbook.getCookTech());//工艺
		resMap.put("tips", kitchenCookbook.getTips());//小贴士
		//syl-add-2014-11-14 11:38:25 创建时间
		String createTime = kitchenCookbook.getCreateTime()!=null?kitchenCookbook.getCreateTime():kitchenCookbook.getSys0AddTime();
		if(!StringUtils.isEmpty(createTime)){
			createTime = DateUtil.strFormate(createTime, DateUtil.formatSecond.get(), DateUtil.formatDay.get());
		}
		resMap.put("createTime", createTime);
		
		return resMap;
	}
	@Override
	public Map<String, Object> prizeSurpriseGift2Map(PrizeRecordTmpData tmpEntity) {
		if(tmpEntity==null||tmpEntity.getSurpriseName()==null||tmpEntity.getSurpriseType()==null){
			return null;
		}
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("name", tmpEntity.getSurpriseName());
		tmpMap.put("type", tmpEntity.getSurpriseType());
		{
			Long expiryTimeLong = null;
			String expiryTime = tmpEntity.getSurpriseExpirytime();
			if(!StringUtils.isEmpty(expiryTime)){
				expiryTimeLong = DateUtil.timeToLong(expiryTime);
			}
			tmpMap.put("expiryTimeLong", expiryTimeLong==null?"":expiryTimeLong);
		}
//		if(tmpEntity.getSurpriseType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount)==0){
		if(tmpEntity.getSurpriseType()!=null&&tmpEntity.getSurpriseHbamount()!=null){//syl-upd-2015-9-17 17:21:27
			tmpMap.put("hbAmount", tmpEntity.getSurpriseHbamount());
		}
		String currServerBaseUrl = sysParamManager.getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
		tmpMap.put("detailUrl", currServerBaseUrl+"surpriseGift/toFlashPageTmp.html?"+SurpriseGiftConstant.Param_SupriseGiftId+"="+tmpEntity.getId());
//		logger.debug("detailUrl is:"+tmpMap.get("detailUrl"));
		return tmpMap;
	}
	@Override
	public Map<String,Object> prizeSurpriseGift2Map(PrizeSurpriseGiftEntity tmpEntity){
		if(tmpEntity==null){
			Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
			tmpMap.put("hbAmount", 0);
			return tmpMap;
		}
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", tmpEntity.getId());
		tmpMap.put("name", tmpEntity.getName());
		tmpMap.put("type", tmpEntity.getDataType());
		{
			Long expiryTimeLong = null;
			String expiryTime = tmpEntity.getExpiryTime();
			if(!StringUtils.isEmpty(expiryTime)){
				expiryTimeLong = DateUtil.timeToLong(expiryTime);
			}
			tmpMap.put("expiryTimeLong", expiryTimeLong==null?"":expiryTimeLong);
		}
		{
			Long createTimeLong = null;
			String createTime = tmpEntity.getCreateTime();
			if(!StringUtils.isEmpty(createTime)){
				createTimeLong = DateUtil.timeToLong(createTime);
			}
			tmpMap.put("createTimeLong", createTimeLong==null?"":createTimeLong);
		}
		if(tmpEntity.getDataType()!=null&&tmpEntity.getHbAmount()!=null){
			if(/*tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.HBAmount)==0
					|| tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.FmlTicket58)==0
					|| tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.YiBaoTicket)==0
					|| tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.MovieTicket)==0
					|| tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.AT_Taxi)==0*/
					true//syl-upd-2015-9-17 17:22:09
				){
				tmpMap.put("hbAmount", PriceUtil.div100(tmpEntity.getHbAmount()));
			}
		}
		tmpMap.put("fetchStatus", tmpEntity.getFetchStatus());
		String currServerBaseUrl = sysParamManager.getSysParaValue(SysParamKey.Curr_Server_BaseUrl);
		tmpMap.put("detailUrl", currServerBaseUrl+"surpriseGift/toFlashPage.html?"+SurpriseGiftConstant.Param_SupriseGiftId+"="+tmpEntity.getId());
		tmpMap.put("useStatus", tmpEntity.getUseStatus());//使用状态
		tmpMap.put("exchCode", tmpEntity.getExchCode());
		{//附加图标信息
			String iconBaseUrl = FileServerConstant.DEFAULT_NULL_PIC_URL;
			if(tmpEntity.getDataType().compareTo(SurpriseGiftDict.PrizeSurpriseGift_DataType.MARKET_OPT)==0){//使用奖项的图标
				iconBaseUrl = ApplicationContextBothUtil.getAbsolutePath(tmpEntity.getMarketIcon(), SysParamKey.PrizeOption_Icon_BasePath, tmpEntity.getSys0UpdTime());
			}else{
				boolean isAvailable = false;
				if(SurpriseGiftDict.PrizeSurpriseGift_UseStatus.NotUse.compareTo(tmpEntity.getUseStatus())==0){isAvailable = true;}
				if(isAvailable){
						iconBaseUrl = ApplicationContextBothUtil.getAbsolutePath(sysParamManager.getSysParaValue(SysParamKey.Coupon_AvailableIcon_Url), SysParamKey.SurpriseGift_Pic_BasePath, tmpEntity.getSys0UpdTime());
				}else{
					iconBaseUrl = ApplicationContextBothUtil.getAbsolutePath(sysParamManager.getSysParaValue(SysParamKey.Coupon_ExpiredIcon_Url), SysParamKey.SurpriseGift_Pic_BasePath, tmpEntity.getSys0UpdTime());
				}
			}
			tmpMap.put("iconUrl", iconBaseUrl);
		}
		tmpMap.put("showCount", tmpEntity.getShowCount()==null?"":tmpEntity.getShowCount());
		return tmpMap;
	}
	
	@Override
	public Map<String, Object> prizeSurpriseGift2Map(PrizeSurpriseGiftEntity tmpEntity,List<BigInteger> existIdList){
		 Map<String,Object> tmpMap = prizeSurpriseGift2Map(tmpEntity);
		 if(tmpMap!=null){
			 boolean isSelected = false;
			 if(tmpEntity!=null&&tmpEntity.getId()!=null&&existIdList!=null){
				 for(BigInteger tmpId:existIdList){
					 if(tmpId.compareTo(tmpEntity.getId())==0){
						 isSelected = true;
					 }
				 }
			 }
			 tmpMap.put("isSelected", isSelected);
		 }
		return tmpMap;
	}
	
	@Override
	public Map<String,Object> couponCombiEntity2Map(CouponCombiEntity couponCombiEntity){
		Map<String,Object> resMap = new SimpleResultMap<String, Object>();
		Long totalAmount = couponCombiEntity.getTotalAmount();
		Double payPercent = couponCombiEntity.getPayPercent();
		List<BigInteger> suggestIdList = couponCombiEntity.getSuggestIdList();
		Long suggestSavedAmount = couponCombiEntity.getSuggestSavedAmount();
		Long maxCouponAmount = couponCombiEntity.getMaxCouponAmount();
		List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList = couponCombiEntity.getPrizeSurpriseGiftList();
		List<Map<String,Object>> totalCouponList = null;
		if(prizeSurpriseGiftList!=null){
			totalCouponList = new ArrayList<Map<String,Object>>();
			for(PrizeSurpriseGiftEntity tmpPrizeSurpriseGift:prizeSurpriseGiftList){
				Map<String,Object> tmpMap = prizeSurpriseGift2Map(tmpPrizeSurpriseGift,suggestIdList);
				totalCouponList.add(tmpMap);
			}
		}
		resMap.put("totalAmount", totalAmount==null?null:PriceUtil.div100(totalAmount));
		resMap.put("maxCouponAmount", maxCouponAmount==null?null:PriceUtil.div100(maxCouponAmount));
		resMap.put("payPercent", payPercent);
		resMap.put("suggestIdList", suggestIdList);
		resMap.put("suggestSavedAmount", suggestSavedAmount==null?0:PriceUtil.div100(suggestSavedAmount));
		resMap.put("totalCouponList", totalCouponList);//总的优惠列表
		return resMap;
	}
	
	@Override
	public Map<String,Object> surpriseGiftConfigPic2Map(SurpriseGiftConfigPic tmpEntity){
		if(tmpEntity==null){return null;}
		String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.SurpriseGift_Pic_BasePath);
		Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("picUrl", StringUtils.isEmpty(tmpEntity.getPicUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(iconBasePath+tmpEntity.getPicUrl(),tmpEntity));
		tmpMap.put("backPicUrl", tmpEntity.getPicUrl());//点击后图片信息
		tmpMap.put("srcId", tmpEntity.getId());
		tmpMap.put("srcType", tmpEntity.getType());
		return tmpMap;
	}

}
