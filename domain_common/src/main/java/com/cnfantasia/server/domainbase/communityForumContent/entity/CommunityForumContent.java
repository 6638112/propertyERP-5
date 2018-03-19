package com.cnfantasia.server.domainbase.communityForumContent.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(社区论坛的帖子内容) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityForumContent implements Serializable{
*/


public class CommunityForumContent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 发帖时间 */	private String createTime;	/** 标题 */	private String title;	/** 帖子文本 */	private String text;	/** 发帖人 */	private BigInteger userId;	/** 小图描述地址 */	private String smallIconUrl;	/** 文本摘要信息 */	private String smallTxt;	/** 详情url地址 */	private String detailUrl;	/** 所属小区Id */	private BigInteger tGroupBuildingFId;	/**  */	private BigInteger tCommunityForumTypeFId;
	public CommunityForumContent(){
	}
	public CommunityForumContent(CommunityForumContent arg){
		this.id = arg.getId();		this.createTime = arg.getCreateTime();		this.title = arg.getTitle();		this.text = arg.getText();		this.userId = arg.getUserId();		this.smallIconUrl = arg.getSmallIconUrl();		this.smallTxt = arg.getSmallTxt();		this.detailUrl = arg.getDetailUrl();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.tCommunityForumTypeFId = arg.gettCommunityForumTypeFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param createTime 发帖时间	 * @param title 标题	 * @param text 帖子文本	 * @param userId 发帖人	 * @param smallIconUrl 小图描述地址	 * @param smallTxt 文本摘要信息	 * @param detailUrl 详情url地址	 * @param tGroupBuildingFId 所属小区Id	 * @param tCommunityForumTypeFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityForumContent(BigInteger id,String createTime,String title,String text,BigInteger userId,String smallIconUrl,String smallTxt,String detailUrl,BigInteger tGroupBuildingFId,BigInteger tCommunityForumTypeFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.createTime = createTime;		this.title = title;		this.text = text;		this.userId = userId;		this.smallIconUrl = smallIconUrl;		this.smallTxt = smallTxt;		this.detailUrl = detailUrl;		this.tGroupBuildingFId = tGroupBuildingFId;		this.tCommunityForumTypeFId = tCommunityForumTypeFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("text=").append(text).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("smallIconUrl=").append(smallIconUrl).append(";");		sbf.append("smallTxt=").append(smallTxt).append(";");		sbf.append("detailUrl=").append(detailUrl).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("tCommunityForumTypeFId=").append(tCommunityForumTypeFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getText() {		return text;	}	public void setText(String text) {		this.text = text;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getSmallIconUrl() {		return smallIconUrl;	}	public void setSmallIconUrl(String smallIconUrl) {		this.smallIconUrl = smallIconUrl;	}	public String getSmallTxt() {		return smallTxt;	}	public void setSmallTxt(String smallTxt) {		this.smallTxt = smallTxt;	}	public String getDetailUrl() {		return detailUrl;	}	public void setDetailUrl(String detailUrl) {		this.detailUrl = detailUrl;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public BigInteger gettCommunityForumTypeFId() {		return tCommunityForumTypeFId;	}	public void settCommunityForumTypeFId(BigInteger tCommunityForumTypeFId) {		this.tCommunityForumTypeFId = tCommunityForumTypeFId;	}
	
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
		CommunityForumContent other = (CommunityForumContent) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
