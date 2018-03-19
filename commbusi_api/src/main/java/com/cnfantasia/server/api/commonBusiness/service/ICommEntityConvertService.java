/**   
* Filename:    ICommEntityConvertService.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午11:02:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.comments.entity.CommentsHasTCommentsPointsEntity;
import com.cnfantasia.server.api.communitySupply.entity.HomeSupplyType;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.SimpleDelivAddress;
import com.cnfantasia.server.api.room.entity.AddressBlockEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.surpriseGift.entity.CouponCombiEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
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
 * Filename:    ICommEntityConvertService.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午11:02:36
 * Description: 实体类转Map服务类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public interface ICommEntityConvertService {
	/**
	 * 用户信息转Map
	 * @param baseUser
	 * @param roomEntity
	 * @return
	 */
	public Map<String,Object> baseUser2Map(UserSimpleEntity baseUser);
	/**
	 * 用户申请加入门牌信息转Map
	 * @param baseUser
	 * @param applyStatus
	 * @param applyId
	 * @return
	 */
	public Map<String,Object> baseUser2MapForApply(UserSimpleEntity baseUser,Integer applyStatus,BigInteger applyId);
	
	public Map<String,Object> baseUser2MapForPrizeRecord(UserSimpleEntity prizeUser);
	public Map<String,Object> baseUser2MapForPrizeRecordDiscount2hbPreQry(UserSimpleEntity hbUser);
	public Map<String,Object> baseUser2MapForHbRecord(UserSimpleEntity hbUser);
	public Map<String,Object> baseUser2MapForLoginRes(UserSimpleEntity loginUserEntity);
	
	public Map<String,Object> baseUser2MapForCommentsUser(UserSimpleEntity commentsUser);
	public Map<String,Object> baseUser2MapForCommentsNoticeUser(UserSimpleEntity commentsNoticeUser);
	public Map<String,Object> baseUser2MapForCommunityForumContent(UserSimpleEntity communityForumUser);
	public Map<String,Object> baseUser2MapForMicroContent(UserSimpleEntity microContentUser);
	public Map<String,Object> baseUser2MapForEbuyBuyCar(UserSimpleEntity buyerUser);
	
	public Map<String,Object> personInfo2Map(UserSimpleEntity baseUser,RoomEntityWithValidate roomEntity,List<Achievement> achievementList,List<Hobby> hobbyList);
	
	/**
	 * 成就信息转Map
	 * @param tmpAchievement
	 * @return
	 */
	public Map<String, Object> achievement2Map(Achievement tmpAchievement);
	/**
	 * 成就信息转MapFor 抽奖折扣荣耀
	 * @param tmpAchievement
	 * @return
	 */
	public Map<String, Object> achievement2MapForPrizeHonour(Achievement tmpAchievement);
	
	/**
	 * 个人兴趣爱好转Map
	 * @param tmpHobby
	 * @param haveSelectFlag
	 * @return
	 */
	public Map<String, Object> hobby2Map(Hobby tmpHobby, Integer haveSelectFlag);
	
	/**
	 * 收获地址信息转Map
	 * @param delvAddress
	 * @param simpleDelivAddress
	 * @return
	 */
	public Map<String, Object> delvAddressEntity2Map(EbuyDeliveryAddressEntity delvAddress, SimpleDelivAddress simpleDelivAddress);
	
	/**
	 * 评论信息转Map
	 * @param comment
	 * @param userInfo
	 * @param nowTimeLong
	 * @param noticeUserList
	 * @return
	 */
	public Map<String,Object> comments2Map(Comments comment,GroupBuilding userGroupBuilding,UserSimpleEntity userInfo,List<UserSimpleEntity> noticeUserList,List<CommentsLabel> commentsLabelList,List<CommentsHasTCommentsPointsEntity> commentsHasTCommentsPointsEntityList,Double totalStarLevel);
	
	/**
	 * 社区商家信息转Map
	 * @param communitySupply
	 * @param communitySupplyContectList
	 * @param topCommunitySupplyType
	 * @return
	 */
	public Map<String,Object> communitySupply2Map(CommunitySupply communitySupply
			,List<CommunitySupplyContect> communitySupplyContectList,CommunitySupplyType topCommunitySupplyType);
	
	/**
	 * 小区信息转Map
	 * @param groupBuilding
	 * @param addressBlockEntity
	 * @param propertyManagement
	 * @return
	 */
	public Map<String, Object> groupBuilding2Map(GroupBuilding groupBuilding, AddressBlockEntity addressBlockEntity,
			PropertyManagement propertyManagement);
	
	public Map<String,Object> building2Map(Building building);
	public Map<String,Object> building2Map(Building building,BigInteger buildingId,String buildingName);
	/**
	 * 真实门牌信息转Map
	 * @param realRoom
	 * @return
	 */
	public Map<String,Object> realRoom2Map(RealRoom realRoom);
	public Map<String,Object> realRoom2Map(RealRoom realRoom,BigInteger realRoomId,String realRoomNum);
	/**
	 * 物业管理处信息转Map
	 * @param propertyManagement
	 * @return
	 */
	public Map<String, Object> propertyManagement2Map(PropertyManagement propertyManagement);
	/**
	 * 抽奖记录信息转Map
	 * @param prizeRecord
	 * @param prizeUser
	 * @param isUsed
	 * @return
	 */
	public Map<String,Object> prizeRecord2Map(PrizeRecord prizeRecord,UserSimpleEntity prizeUser,Boolean isUsed);
	
	/**
	 * 商品参数信息转Map
	 * @param tmpEbuyProductParameters
	 * @return
	 */
	public Map<String, Object> ebuyProductParameters2Map(EbuyProductParameters tmpEbuyProductParameters);
	
	/**
	 * 商品类别信息转Map
	 * @param ebuyProductType
	 * @return
	 */
	public Map<String, Object> ebuyProductType2Map(EbuyProductType ebuyProductType);
	
	/**
	 * 商品配送方式转Map
	 * @param deliveryMethod
	 * @return
	 */
	public Map<String, Object> ebuyDeliveryMethod2Map(EbuyDeliveryMethod deliveryMethod);
	
	/**
	 * 积分信息转Map
	 * @param pointData
	 * @return
	 */
	public Map<String, Object> pointData2Map(PointData pointData);
	
	/**
	 * 微博信息转Map
	 * @param tmpObj
	 * @param microblogPicList
	 * @param userInfo
	 * @param nowTimeLong
	 * @param isFavour
	 * @param favourCount
	 * @param commentCount
	 * @return
	 */
	public Map<String, Object> microblogContent2Map(MicroblogContent tmpObj, List<MicroblogPic> microblogPicList, UserSimpleEntity userInfo,
			Long nowTimeLong, Integer isFavour, Integer favourCount, Integer commentCount,boolean isListData);
	
	/**
	 * 社区商家类别信息转Map
	 * @param communitySupplyType
	 * @param topCommunitySupplyType
	 * @return
	 */
	public Map<String,Object> communitySupplyType2Map(CommunitySupplyType communitySupplyType,CommunitySupplyType topCommunitySupplyType);
	/**首页图标列表数据*/
	public Map<String,Object> communitySupplyType2Map(HomeSupplyType homeSupplyType);
	
	/**
	 * 评论标签信息2Map
	 * @param commentsLabel
	 * @return
	 */
	public Map<String, Object> commentsLabel2Map(CommentsLabel commentsLabel);
	
	/**
	 * 评论评分项信息2Map
	 * @param commentsPoints
	 * @return
	 */
	public Map<String, Object> commentsPoints2Map(CommentsPoints commentsPoints);
	
	/**
	 * 产品信息转Map
	 * @param ebuyProductInfo 产品基本信息
	 * @return
	 */
//	public Map<String,Object> productInfo2Map(EbuyProduct ebuyProductInfo,EbuyProductSpec ebuyProductSpec);
	public Map<String,Object> productInfo2Map(EbuyProductWithCurrProductSpec ebuyProductWithCurrProductSpec);
	/**
	 * 菜谱信息转Map
	 * @param kitchenCookbook
	 * @return
	 */
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook);
	
	/** 
	 * 意外惊喜信息转Map
	 * @param tmpEntity
	 * @return
	 */
	public Map<String, Object> prizeSurpriseGift2Map(PrizeSurpriseGiftEntity tmpEntity);
	public Map<String, Object> prizeSurpriseGift2Map(PrizeRecordTmpData tmpEntity);
	
	/**
	 * 增加返回是否选中的标识
	 * @param tmpEntity
	 * @param existIdList
	 * @return
	 */
	public Map<String, Object> prizeSurpriseGift2Map(PrizeSurpriseGiftEntity tmpEntity,List<BigInteger> existIdList);
	
	/**
	 * 优惠券组合信息转Map
	 * @param couponCombiEntity
	 * @return
	 */
	public Map<String, Object> couponCombiEntity2Map(CouponCombiEntity couponCombiEntity);
	
	/**
	 * 首页Logo图标转Map
	 * @param tmpEntity
	 * @return
	 */
	public Map<String, Object> surpriseGiftConfigPic2Map(SurpriseGiftConfigPic tmpEntity);
	
}
