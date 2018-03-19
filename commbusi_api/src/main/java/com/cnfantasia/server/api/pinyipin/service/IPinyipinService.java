/**   
* Filename:    IPinyipinService.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:25:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinReserveEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;
import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;


/**
 * Filename:    IPinyipinService.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:25:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public interface IPinyipinService {
	
	/**
	 * 查询最热的一个拼单
	 * @param userId
	 * @return
	 */
	public PinyipinContentEntity getMostHotContent(BigInteger userId);
	
	/**
	 * 查询热门拼单列表
	 * @param userId
	 * @return
	 */
	public List<PinyipinContentEntity> getHotContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询我发起的拼单列表
	 * @param userId
	 * @return
	 */
	public List<PinyipinContentEntity> getLaunchContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询拼单详情
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
	public PinyipinContentEntity getPinyipinContentDetail(BigInteger userId,BigInteger contentId);
	/**
	 * 查询拼单基本信息
	 * @param contentId 拼单Id
	 * @return
	 */
	public CommunityPinyipinContent getPinyipinContentById(BigInteger contentId);
	
//	/**
//	 * 查询查询我发起的拼单详情
//	 * @param userId 用户Id
//	 * @param contentId 拼单Id
//	 * @return
//	 */
//	public PinyipinContentEntity getLaunchContentDetail(BigInteger userId,BigInteger contentId);
	
	/**
	 * 查询我参与的拼单列表
	 * @param userId 用户Id
	 * @return
	 */
	public List<PinyipinContentEntity> getJoinContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询我发起的和参与的拼单列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<PinyipinContentEntity> getLaunchAndJoinContentList(BigInteger userId, PageModel pageModel);
	
	/**
	 * 用户发起拼单
	 * @param userId 用户Id
	 * @param title 标题
	 * @param desc 描述
	 * @param marketPrice 市场价格
	 * @param realPrice 实际价格
	 * @param priceUnit 计价单位
	 * @param goalCount 目标数量
	 * @param sendType 配送方式
	 * @param selfAddress 自提地点
	 * @param endDate 结束时间
	 * @param sendDate 配送时间
	 * @param contectInfo 联系方式
	 * @param picEntityList 图片列表
	 * @return
	 */
	public void confirmLaunchContent(BigInteger userId,String title,String desc,Long marketPrice,Long realPrice,String priceUnit,Long goalCount
			,Integer sendType,String selfAddress,String endDate,String sendDate,String contectInfo,List<RequestFileEntity> picEntityList);
	
	/**
	 * 用户删除发起的拼单
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
	public void delLaunchContent(BigInteger userId,BigInteger contentId);
	
	/**
	 * 用户修改发起的拼单
	 * @param userId
	 * @param contentId 拼单Id
	 * @param title
	 * @param desc
	 * @param marketPrice
	 * @param realPrice
	 * @param priceUnit
	 * @param goalCount
	 * @param sendType
	 * @param selfAddress
	 * @param endDate
	 * @param sendDate
	 * @param contectInfo
	 * @param picEntityList
	 * @param delPicIds 需要删除的图片Id列表
	 * @return
	 */
	public void updLaunchConent(BigInteger userId,BigInteger contentId,String title,String desc,Long marketPrice,Long realPrice,String priceUnit,Long goalCount
			,Integer sendType,String selfAddress,String endDate,String sendDate,String contectInfo,List<RequestFileEntity> picEntityList,List<BigInteger> delPicIds);
	
//	/**
//	 * 参与拼单用户查看预定拼单详情
//	 * @param userId 用户Id
//	 * @param contentId 拼单Id
//	 * @return
//	 */
//	public PinyipinContentEntity getJoinReserveContentDetail(BigInteger userId,BigInteger contentId);
	
	/**
	 * 用户确认参与拼单
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @param count 数量
	 * @param deliveryAddressId 配送地址Id
	 * @return
	 */
	public void confirmJoinContent(BigInteger userId,BigInteger contentId,Long count,BigInteger deliveryAddressId);
	
	/**
	 * 发起拼单用户查看预定列表
	 * @param userId 用户Id
	 * @param contentId 拼单Id
	 * @return
	 */
//	public List<PinyipinReserveEntity> getLaunchPinyipinReserveList(BigInteger userId,BigInteger contentId,PageModel pageModel);
	public List<PinyipinReserveEntity> getLaunchPinyipinReserveList(BigInteger userId,BigInteger contentId);
	
	/**
	 * 修改指定用户参与的拼单数量(总量)
	 * @param currUserId 当前用户Id
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param totalCount 待修改的总记录数
	 * @return 返回当前总的记录数
	 */
	public void updReserveContentByTotalCount(BigInteger currUserId,BigInteger pinyipinReverseId,Integer totalCount);
	
	/**
	 * 修改指定用户参与的拼单数量(增量)
	 * @param currUserId 当前用户Id
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param changeCount 待修改的增量记录数
	 * @return 返回当前总的记录数	
	 */
	public void updReserveContentByChangeCount(BigInteger currUserId,BigInteger pinyipinReverseId,Integer changeCount);
	
	/**
	 * 生成发货清单
	 * @param userId
	 * @param contentId
	 */
	public void confirmDeliveryOrder(BigInteger userId, BigInteger contentId);
	
	/**
	 * 发起拼单者标记已领和未领
	 * @param currUserId
	 * @param pinyipinReverseId 待修改的记录Id
	 * @param calimStatus 指定的领取状态
	 * @return
	 */
	public void markClaimStatus(BigInteger currUserId,BigInteger pinyipinReverseId,Integer calimStatus);
	
	/**
	 * 系统自动检查拼单是否已过时，若过时则更改拼单状态
	 */
	public void autoCheckPinyipinContentTimeOut();
	
	/**
	 * 刷新拼单状态
	 * @param contentId
	 */
	public void freshContentDeliveryStatus(BigInteger contentId);
	
	/**
	 * 增加消息 返回消息对应的Id
	 * @param userId 
	 * @param msgInfoKey
	 * @param msgParams
	 * 
	 */
	public BigInteger addMsgPushInfo(BigInteger userId,String msgInfoKey,Object[] msgParams,Long msgType,BigInteger detailId);
	
	/**
	 * @param userIdList
	 * @param msgInfoKey
	 * @param msgParams
	 * @return
	 */
	public BigInteger addMsgPushInfo(List<BigInteger> userIdList, String msgInfoKey, Object[] msgParams,Long msgType,BigInteger detailId);
	
//	/**
//	 * 查询所有热门拼单列表最新更新时间
//	 * @return
//	 */
//	public String fetchAllHotContentListLastUpdTime(BigInteger userId);
//	/**
//	 * 查询所有我发起的和我参与的拼单最新更新时间
//	 * @return
//	 */
//	public String fetchAllLaunchAndJoinContentListLastUpdTime(BigInteger userId);
	
//	/**
//	 * 拼一拼信息转Map
//	 * @param currUserId
//	 * @param pinyipinContent
//	 * @param createUser 拼单创建者
//	 * @param pinyipinPicList
//	 * @param pinyipinReserveList
//	 * @return
//	 */
//	public Map<String,Object> pinyipinContent2Map(BigInteger currUserId,CommunityPinyipinContent pinyipinContent
//			,User createUser,List<CommunityPinyipinPic> pinyipinPicList,List<CommunityPinyipinReserve> pinyipinReserveList);
	/**
	 * 拼一拼信息转Map
	 * @param currUserId
	 * @param pinyipinContent
	 * @param createUser
	 * @param pinyipinPicList
	 * @param pinyipinReserveList
	 * @param isDeleteAble 是否可删除
	 * @param isEditAble 是否可编辑
	 * @param firstComment 第一条评论信息
	 * @param commentTotalCount 总评论数
	 * @param isSupported 是否已点赞
	 * @param totalSupportCount 已赞总数
	 * @param isCollected 是否已收藏
	 * @return
	 */
	public Map<String,Object> pinyipinContent2Map(String nowTime,BigInteger currUserId,CommunityPinyipinContent pinyipinContent
			,UserSimpleEntity createUser,List<CommunityPinyipinPic> pinyipinPicList,List<UserSimpleEntity> pinyipinReserveList,Boolean deliverAble
			,Integer userBelong,Long reserveCount,Integer currUserCount,Long currUserReserveCount
			,Boolean isDeleteAble,Boolean isEditAble,CommentsEntity firstComment,Integer commentTotalCount,Boolean isSupported,Integer totalSupportCount,Boolean isCollected);
	/**
	 * 图片信息转Map
	 * @param pinyipinPic
	 * @return
	 */
	public Map<String,Object> pinyipinPic2Map(CommunityPinyipinPic pinyipinPic);
	
	/**
	 * 拼一拼预定拼单转Map
	 * @param pinyipinReserve
	 * @param user
	 * @param deliveryAddressEntity
	 * @return
	 */
	public Map<String, Object> pinyipinReserve2Map(String nowTime,CommunityPinyipinReserve pinyipinReserve,UserSimpleEntity user,EbuyDeliveryAddressEntity deliveryAddressEntity);

	/**
	 * @param userId
	 * @param communityPinyipinContent
	 * @return
	 */
	public Map<String, Object> pinyipinContent2Map(String nowTime,BigInteger userId, CommunityPinyipinContent communityPinyipinContent);

	/**
	 * @param userId
	 * @param tmpPinyipinContentEntity
	 * @return
	 */
	public Map<String, Object> pinyipinContent2Map(String nowTime,BigInteger userId, PinyipinContentEntity tmpPinyipinContentEntity);
	
	
	public List<Map<String, Object>> pinyipinReserve2MapList(BigInteger userId, List<PinyipinReserveEntity> tmpPinyipinReserveEntityList);
	public List<Map<String, Object>> pinyipinContent2MapList(BigInteger userId, List<PinyipinContentEntity> tmpPinyipinContentEntityList);


}
