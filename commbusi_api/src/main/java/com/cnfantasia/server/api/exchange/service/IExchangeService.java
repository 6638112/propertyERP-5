/**   
* Filename:    IExchangeService.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:26:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeBaseEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeRelationGoalEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeSuccEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;
import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;
import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * Filename:    IExchangeService.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:26:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public interface IExchangeService {
	/**
	 * 查询最热的一个换物
	 * @param userId
	 * @return
	 */
	public ExchangeEntity getMostHotContent(BigInteger userId);
	
	/**
	 * 查询热门换物列表
	 * @param userId
	 * @return
	 */
	public List<ExchangeBaseEntity> getHotContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 发起换物
	 * @param userId
	 * @param title 标题
	 * @param desc 描述
	 * @param picEntityList 换物图片列表
	 */
	public void confirmLaunchContent(BigInteger userId,String title,String desc,List<RequestFileEntity> picEntityList);
	/**
	 * 修改换物
	 * @param userId
	 * @param updContentId
	 * @param title 标题
	 * @param desc 描述
	 * @param picEntityList 换物图片列表
	 * @param delPicIds 需要删除的图片
	 */
	public void updateLaunchContent(BigInteger userId,BigInteger updContentId, String title,String desc,List<RequestFileEntity> picEntityList,List<BigInteger> delPicIds);
	
	/**
	 * 删除换物
	 * @param userId
	 * @param contentId
	 */
	public void delLaunchContent(BigInteger userId, BigInteger contentId);
	
	/**
	 * 查询我发起的换物列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> getLaunchContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 参与换物
	 * @param userId 用户Id
	 * @param goalContentId 想交换的换物Id
	 * @param title
	 * @param desc
	 * @param picEntityList
	 */
	public void confirmJoinContent(BigInteger userId,BigInteger goalContentId,String title,String desc,List<RequestFileEntity> picEntityList);
	
	/**
	 * 查询我参与的换物列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> getJoinContentList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询我发起的和我参与的换物列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> getLaunchAndJoinContentList(BigInteger userId, PageModel pageModel);
	public ExchangeRelationGoalEntity getSubJoinContentListWithRelationSucc(BigInteger userId,BigInteger exchangeContentId);
	/**
	 * 查询换物详情
	 * @param userId
	 * @param exchangeContentId
	 * @return
	 */
	public ExchangeEntity getExchangeContentDetail(BigInteger userId,BigInteger exchangeContentId);
	public ExchangeBaseEntity getExchangeContentDetailBase(BigInteger userId,BigInteger exchangeContentId);
	
	/**
	 * 查询参与换物的用户物品列表
	 * @param userId
	 * @param exchangeContentId
	 * @return
	 */
	public List<ExchangeBaseEntity> getSubJoinContentList(BigInteger userId,BigInteger exchangeContentId);
	
	/**
	 * 发起者确认交换换物
	 * 1.换物关系的状态、发起换物的状态、目标换物的状态 都不应该是交换完成
	 * 2.将换物关系的状态更改为已交换，将对应的发起换物和目标换物的状态都更改为已完成
	 * @param userId
	 * @param exchangeRelationId
	 */
	public ExchangeSuccEntity confirmExchangeRelation(BigInteger userId,BigInteger exchangeRelationId);
	
	/**
	 * 查询是否可以跪求换
	 * @param userId
	 * @param exchangeRelationId
	 * @return
	 */
	public Boolean checkCanWanted(BigInteger userId,BigInteger exchangeRelationId);
	/**
	 * 跪求换的信息
	 * 返回当前已跪求换的数量
	 * @return
	 */
	public Integer doRelationWanted(BigInteger userId,BigInteger exchangeRelationId);
	
	/**
	 * 增加消息 返回消息对应的Id
	 * @param userId 
	 * @param msgInfoKey
	 * @param msgParams
	 * 
	 */
	public BigInteger addMsgPushInfo(BigInteger userId,String msgInfoKey,Object[] msgParams,Long msgType,BigInteger detailId);	
	public BigInteger addMsgPushInfo(List<BigInteger> userIdList,String msgInfoKey,Object[] msgParams,Long msgType,BigInteger detailId);	
	
//	/**
//	 * 换物信息转Map
//	 * @param currUserId 当前用户Id
//	 * @param exchangeContentOwner 换物拥有者
//	 * @param exchangeContent
//	 * @param exchangePicList
//	 * @param exchangeRelationGoalEntityList
//	 * @return
//	 */
//	public Map<String,Object> exhcangeContent2Map(BigInteger currUserId,User exchangeContentOwner,CommunityExchangeContent exchangeContent
//			,List<CommunityExchangePic> exchangePicList,List<ExchangeRelationGoalEntity>  exchangeRelationGoalEntityList,Integer relationCount);
	
//	/**
//	 * 查询所有热门换物列表最新更新时间
//	 * @return
//	 */
//	public String fetchAllHotContentListLastUpdTime(BigInteger userId);
//	/**
//	 * 查询所有我发起的和我参与的换物最新更新时间
//	 * @return
//	 */
//	public String fetchAllLaunchAndJoinContentListLastUpdTime(BigInteger userId);
	
	/**
	 * 换物信息转Map
	 * @param isDeleteAble 是否可删除
	 * @param isEditAble 是否可编辑
	 * @param firstComment 第一条评论信息
	 * @param commentTotalCount 总评论数
	 * @param isSupported 是否已点赞
	 * @param totalSupportCount 已赞总数
	 * @param isCollected 是否已收藏
	 */
	public Map<String,Object> exhcangeContent2Map(Long nowTimeLong,BigInteger currUserId, UserSimpleEntity exchangeContentOwner,
			CommunityExchangeContent exchangeContent, List<CommunityExchangePic> exchangePicList,
			List<ExchangeRelationGoalEntity> exchangeRelationGoalEntityList,Integer relationCount,Integer userBelong
			,ExchangeRelationGoalEntity succExchRelaGoalEntity
			,Boolean isDeleteAble, Boolean isEditAble,
			CommentsEntity firstComment, Integer commentTotalCount, Boolean isSupported,Integer totalSupportCount
			,Boolean isCollected);
	
	
	/** 
	 * 换物关系信息转Map
	 * @param preCommentsEntityList 前几个评论信息
	 * @param commentTotalCount 评论总数
	 * @param wantedCount 跪求换的数量
	 * @param isWantedAble 是否可以跪求换
	 * @param isSupported 是否已点赞
	 * @param totalSupportCount 已赞总数
	 * @return
	 */
	public Map<String,Object> exchangeRelationGoalEntity2Map(Long nowTimeLong,BigInteger currUserId,CommunityExchangeRelation exchangeRelation,ExchangeBaseEntity goalExchangeEntity
			,List<CommentsEntity> preCommentsEntityList,Integer commentTotalCount,Integer wantedCount,Boolean isWantedAble,Boolean isSupported,Integer totalSupportCount);
	
	/**
	 * 图片信息转Map
	 * @param exchangePic
	 * @return
	 */
	public Map<String,Object> exchangePic2Map(CommunityExchangePic exchangePic);

	/**
	 * @param userId
	 * @param exchangeEntity
	 * @return
	 */
	public Map<String, Object> exhcangeContent2Map(Long nowTimeLong,BigInteger userId, ExchangeEntity exchangeEntity);

	/**
	 * @param userId
	 * @param exchangeEntity
	 * @return
	 */
	public Map<String, Object> exhcangeContent2Map(Long nowTimeLong,BigInteger userId, ExchangeBaseEntity exchangeEntity);
	
	public List<Map<String, Object>> exhcangeContent2MapList(BigInteger userId, List<ExchangeBaseEntity> exchangeEntityList);
}
