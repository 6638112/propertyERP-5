package com.cnfantasia.server.domainbase.communityExchangeContent.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(换物信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityExchangeContent implements Serializable{
*/


public class CommunityExchangeContent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 标题 */	private String title;	/** 物品描述 */	private String desc;	/** 所属用户Id */	private BigInteger userId;	/** 创建时间 */	private String createTime;	/** 换物状态 */	private Integer status;	/**  */	private BigInteger tGroupBuildingFId;	/** 创建方式 */	private Integer createType;
	public CommunityExchangeContent(){
	}
	public CommunityExchangeContent(CommunityExchangeContent arg){
		this.id = arg.getId();		this.title = arg.getTitle();		this.desc = arg.getDesc();		this.userId = arg.getUserId();		this.createTime = arg.getCreateTime();		this.status = arg.getStatus();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.createType = arg.getCreateType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param title 标题	 * @param desc 物品描述	 * @param userId 所属用户Id	 * @param createTime 创建时间	 * @param status 换物状态	 * @param tGroupBuildingFId 	 * @param createType 创建方式	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityExchangeContent(BigInteger id,String title,String desc,BigInteger userId,String createTime,Integer status,BigInteger tGroupBuildingFId,Integer createType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.title = title;		this.desc = desc;		this.userId = userId;		this.createTime = createTime;		this.status = status;		this.tGroupBuildingFId = tGroupBuildingFId;		this.createType = createType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("createType=").append(createType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public Integer getCreateType() {		return createType;	}	public void setCreateType(Integer createType) {		this.createType = createType;	}
	
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
		CommunityExchangeContent other = (CommunityExchangeContent) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
