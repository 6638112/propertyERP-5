/**   
* Filename:    ExchangeRelationGoalEntity.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午3:41:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * Filename:    ExchangeRelationGoalEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午3:41:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public class ExchangeRelationGoalEntity extends CommunityExchangeRelation{
	private static final long serialVersionUID = 1L;
	
	/**目标换物*/
	private ExchangeBaseEntity goalExchangeEntity;
	public ExchangeBaseEntity getGoalExchangeEntity() {
		return goalExchangeEntity;
	}
	public void setGoalExchangeEntity(ExchangeBaseEntity goalExchangeEntity) {
		this.goalExchangeEntity = goalExchangeEntity;
	}
	
	@Override
	public BigInteger getJoinExchgId() {
		if(goalExchangeEntity==null){
			return null;
		}
		return goalExchangeEntity.getId();
	}
	@Override
	public void setJoinExchgId(BigInteger joinExchgId) {
		if(goalExchangeEntity==null){
			goalExchangeEntity = new ExchangeBaseEntity();
		}
		goalExchangeEntity.setId(joinExchgId);
	}
	
	
	/**是否可跪求换*/
	private Boolean isWantedAble;
	public Boolean getIsWantedAble() {
		return isWantedAble;
	}
	public void setIsWantedAble(Boolean isWantedAble) {
		this.isWantedAble = isWantedAble;
	}
	
	/**跪求换数量*/
	private Integer wantedCount;
	public Integer getWantedCount() {
		return wantedCount;
	}
	public void setWantedCount(Integer wantedCount) {
		this.wantedCount = wantedCount;
	}
	
	/**默认加载的评论列表*/
	private List<CommentsEntity> preCommentsEntityList;
	public List<CommentsEntity> getPreCommentsEntityList() {
		return preCommentsEntityList;
	}
	public void setPreCommentsEntityList(List<CommentsEntity> preCommentsEntityList) {
		this.preCommentsEntityList = preCommentsEntityList;
	}
	
	/**
	 * 评价总数
	 */
	private Integer commentTotalCount;
	public Integer getCommentTotalCount() {
		return commentTotalCount;
	}
	public void setCommentTotalCount(Integer commentTotalCount) {
		this.commentTotalCount = commentTotalCount;
	}
	
	/**
	 * 是否已点赞，true是false否
	 */
	private Boolean isSupported;
	public Boolean getIsSupported() {
		return isSupported;
	}
	public void setIsSupported(Boolean isSupported) {
		this.isSupported = isSupported;
	}
	
	/**
	 * 点赞总数
	 */
	private Integer totalSupportCount;
	public Integer getTotalSupportCount() {
		return totalSupportCount;
	}
	public void setTotalSupportCount(Integer totalSupportCount) {
		this.totalSupportCount = totalSupportCount;
	}
	
	
	
}
