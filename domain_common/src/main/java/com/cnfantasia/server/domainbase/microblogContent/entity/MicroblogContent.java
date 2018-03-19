package com.cnfantasia.server.domainbase.microblogContent.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微博信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MicroblogContent implements Serializable{
*/


public class MicroblogContent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 信息发布时间 */	private String createTime;	/** 运营活动名称 */	private String activityName;	/** 信息文本内容 */	private String text;	/** 发送者 */	private BigInteger userId;	/** 所属微博类别 */	private BigInteger tMicroblogTypeFId;	/** 所在小区 */	private BigInteger tGroupBuildingFId;	/** 最近更新时间 */	private String lastUpdateTime;	/** 总的点赞数 */	private Long totalSupportCount;	/** 总的评论数 */	private Long totalCommentCount;	/** 系统消息类别 */	private Integer sourceType;	/** 推送的消息类别编码 */	private Long showType;	/**  */	private BigInteger tMicroblogQueueFId;	/** 链接详情页面待填充数据 */	private String linkDetailJson;
	public MicroblogContent(){
	}
	public MicroblogContent(MicroblogContent arg){
		this.id = arg.getId();		this.createTime = arg.getCreateTime();		this.activityName = arg.getActivityName();		this.text = arg.getText();		this.userId = arg.getUserId();		this.tMicroblogTypeFId = arg.gettMicroblogTypeFId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.lastUpdateTime = arg.getLastUpdateTime();		this.totalSupportCount = arg.getTotalSupportCount();		this.totalCommentCount = arg.getTotalCommentCount();		this.sourceType = arg.getSourceType();		this.showType = arg.getShowType();		this.tMicroblogQueueFId = arg.gettMicroblogQueueFId();		this.linkDetailJson = arg.getLinkDetailJson();
	}
	/**	 * 	 * @param id 	 * @param createTime 信息发布时间	 * @param activityName 运营活动名称	 * @param text 信息文本内容	 * @param userId 发送者	 * @param tMicroblogTypeFId 所属微博类别	 * @param tGroupBuildingFId 所在小区	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param lastUpdateTime 最近更新时间	 * @param totalSupportCount 总的点赞数	 * @param totalCommentCount 总的评论数	 * @param sourceType 系统消息类别	 * @param showType 推送的消息类别编码	 * @param tMicroblogQueueFId 	 * @param linkDetailJson 链接详情页面待填充数据	 */
	public MicroblogContent(BigInteger id,String createTime,String activityName,String text,BigInteger userId,BigInteger tMicroblogTypeFId,BigInteger tGroupBuildingFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String lastUpdateTime,Long totalSupportCount,Long totalCommentCount,Integer sourceType,Long showType,BigInteger tMicroblogQueueFId,String linkDetailJson){
		this.id = id;		this.createTime = createTime;		this.activityName = activityName;		this.text = text;		this.userId = userId;		this.tMicroblogTypeFId = tMicroblogTypeFId;		this.tGroupBuildingFId = tGroupBuildingFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.lastUpdateTime = lastUpdateTime;		this.totalSupportCount = totalSupportCount;		this.totalCommentCount = totalCommentCount;		this.sourceType = sourceType;		this.showType = showType;		this.tMicroblogQueueFId = tMicroblogQueueFId;		this.linkDetailJson = linkDetailJson;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("activityName=").append(activityName).append(";");		sbf.append("text=").append(text).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("tMicroblogTypeFId=").append(tMicroblogTypeFId).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("lastUpdateTime=").append(lastUpdateTime).append(";");		sbf.append("totalSupportCount=").append(totalSupportCount).append(";");		sbf.append("totalCommentCount=").append(totalCommentCount).append(";");		sbf.append("sourceType=").append(sourceType).append(";");		sbf.append("showType=").append(showType).append(";");		sbf.append("tMicroblogQueueFId=").append(tMicroblogQueueFId).append(";");		sbf.append("linkDetailJson=").append(linkDetailJson).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getActivityName() {		return activityName;	}	public void setActivityName(String activityName) {		this.activityName = activityName;	}	public String getText() {		return text;	}	public void setText(String text) {		this.text = text;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger gettMicroblogTypeFId() {		return tMicroblogTypeFId;	}	public void settMicroblogTypeFId(BigInteger tMicroblogTypeFId) {		this.tMicroblogTypeFId = tMicroblogTypeFId;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public String getLastUpdateTime() {		return lastUpdateTime;	}	public void setLastUpdateTime(String lastUpdateTime) {		this.lastUpdateTime = lastUpdateTime;	}	public Long getTotalSupportCount() {		return totalSupportCount;	}	public void setTotalSupportCount(Long totalSupportCount) {		this.totalSupportCount = totalSupportCount;	}	public Long getTotalCommentCount() {		return totalCommentCount;	}	public void setTotalCommentCount(Long totalCommentCount) {		this.totalCommentCount = totalCommentCount;	}	public Integer getSourceType() {		return sourceType;	}	public void setSourceType(Integer sourceType) {		this.sourceType = sourceType;	}	public Long getShowType() {		return showType;	}	public void setShowType(Long showType) {		this.showType = showType;	}	public BigInteger gettMicroblogQueueFId() {		return tMicroblogQueueFId;	}	public void settMicroblogQueueFId(BigInteger tMicroblogQueueFId) {		this.tMicroblogQueueFId = tMicroblogQueueFId;	}	public String getLinkDetailJson() {		return linkDetailJson;	}	public void setLinkDetailJson(String linkDetailJson) {		this.linkDetailJson = linkDetailJson;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MicroblogContent other = (MicroblogContent) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
