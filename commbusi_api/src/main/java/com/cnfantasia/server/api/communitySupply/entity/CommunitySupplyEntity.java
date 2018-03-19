/**   
* Filename:    CommunitySupplyEntity.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:25:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;

/**
 * Filename:    CommunitySupplyEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:25:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyEntity extends CommunitySupply{
	private static final long serialVersionUID = 1L;
	
	/**是否物业推荐*/
	private Boolean isPropertySuggest;
	public Boolean getIsPropertySuggest() {
		return isPropertySuggest;
	}
	public void setIsPropertySuggest(Boolean isPropertySuggest) {
		this.isPropertySuggest = isPropertySuggest;
	}

	
	/**
	 * 联系方式列表
	 */
	private List<CommunitySupplyContect> communitySupplyContectList;
	public List<CommunitySupplyContect> getCommunitySupplyContectList() {
		return communitySupplyContectList;
	}
	public void setCommunitySupplyContectList(List<CommunitySupplyContect> communitySupplyContectList) {
		this.communitySupplyContectList = communitySupplyContectList;
	}
	
	/**
	 * 获取该商家联系方式的总的拨打次数
	 * @return
	 */
	public Long fetchSupplyContectTotalCallCount(){
		Long count = 0L;
		if(communitySupplyContectList!=null&&communitySupplyContectList.size()>0){
			for(CommunitySupplyContect tmpContect:communitySupplyContectList){
				if(tmpContect.getClickCount()!=null){
					count+=tmpContect.getClickCount();
				}
			}
		}
		return count;
	}
	
	/**
	 * 商家图片列表
	 */
	private List<CommunitySupplyPic> communitySupplyPicList;
	public List<CommunitySupplyPic> getCommunitySupplyPicList() {
		return communitySupplyPicList;
	}
	public void setCommunitySupplyPicList(List<CommunitySupplyPic> communitySupplyPicList) {
		this.communitySupplyPicList = communitySupplyPicList;
	}
	
	/**
	 * 第一条评论内容
	 */
	private CommentsEntity firstComments;
	public CommentsEntity getFirstComments() {
		return firstComments;
	}
	public void setFirstComments(CommentsEntity firstComments) {
		this.firstComments = firstComments;
	}
	
	/**
	 * 是否收藏标识  0 未收藏，1已收藏
	 */
	private Integer collectFlag;
	public Integer getCollectFlag() {
		return collectFlag;
	}
	public void setCollectFlag(Integer collectFlag) {
		this.collectFlag = collectFlag;
	}
	
	/**所属类别信息*/
	private CommunitySupplyTypeEntity communitySupplyTypeEntity;
	public CommunitySupplyTypeEntity getCommunitySupplyTypeEntity() {
		return communitySupplyTypeEntity;
	}
	public void setCommunitySupplyTypeEntity(CommunitySupplyTypeEntity communitySupplyTypeEntity) {
		this.communitySupplyTypeEntity = communitySupplyTypeEntity;
	}
	@Override
	public BigInteger gettCommunitySupplyTypeFId() {
		if(communitySupplyTypeEntity==null){
			return null;
		}
		return communitySupplyTypeEntity.getId();
	}
	@Override
	public void settCommunitySupplyTypeFId(BigInteger tCommunitySupplyTypeFId) {
		if(communitySupplyTypeEntity==null){
			communitySupplyTypeEntity = new CommunitySupplyTypeEntity();
		}
		communitySupplyTypeEntity.setId(tCommunitySupplyTypeFId);
	}
	
	/**总评论数*/
	private Integer commentTotalCount;
	public Integer getCommentTotalCount() {
		return commentTotalCount;
	}
	public void setCommentTotalCount(Integer commentTotalCount) {
		this.commentTotalCount = commentTotalCount;
	}
	
	
}
