package com.cnfantasia.server.domainbase.communitySupplyContect.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(社区商家联系方式) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyContect implements Serializable{
*/


public class CommunitySupplyContect extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 客服名称 */	private String esqName;	/** 用户身份描述 */	private String userIdentity;	/** 联系方式内容 */	private String contectInfo;	/** 点击次数 */	private Long clickCount;	/** 联系方式类型 */	private Integer type;	/**  */	private BigInteger tCommunitySupplyFId;	/** 临时商家ID，商家维护使用 */	private BigInteger tCommunitySupplyTmpFId;
	public CommunitySupplyContect(){
	}
	public CommunitySupplyContect(CommunitySupplyContect arg){
		this.id = arg.getId();		this.esqName = arg.getEsqName();		this.userIdentity = arg.getUserIdentity();		this.contectInfo = arg.getContectInfo();		this.clickCount = arg.getClickCount();		this.type = arg.getType();		this.tCommunitySupplyFId = arg.gettCommunitySupplyFId();		this.tCommunitySupplyTmpFId = arg.gettCommunitySupplyTmpFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param esqName 客服名称	 * @param userIdentity 用户身份描述	 * @param contectInfo 联系方式内容	 * @param clickCount 点击次数	 * @param type 联系方式类型	 * @param tCommunitySupplyFId 	 * @param tCommunitySupplyTmpFId 临时商家ID，商家维护使用	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunitySupplyContect(BigInteger id,String esqName,String userIdentity,String contectInfo,Long clickCount,Integer type,BigInteger tCommunitySupplyFId,BigInteger tCommunitySupplyTmpFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.esqName = esqName;		this.userIdentity = userIdentity;		this.contectInfo = contectInfo;		this.clickCount = clickCount;		this.type = type;		this.tCommunitySupplyFId = tCommunitySupplyFId;		this.tCommunitySupplyTmpFId = tCommunitySupplyTmpFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("esqName=").append(esqName).append(";");		sbf.append("userIdentity=").append(userIdentity).append(";");		sbf.append("contectInfo=").append(contectInfo).append(";");		sbf.append("clickCount=").append(clickCount).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("tCommunitySupplyFId=").append(tCommunitySupplyFId).append(";");		sbf.append("tCommunitySupplyTmpFId=").append(tCommunitySupplyTmpFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getEsqName() {		return esqName;	}	public void setEsqName(String esqName) {		this.esqName = esqName;	}	public String getUserIdentity() {		return userIdentity;	}	public void setUserIdentity(String userIdentity) {		this.userIdentity = userIdentity;	}	public String getContectInfo() {		return contectInfo;	}	public void setContectInfo(String contectInfo) {		this.contectInfo = contectInfo;	}	public Long getClickCount() {		return clickCount;	}	public void setClickCount(Long clickCount) {		this.clickCount = clickCount;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public BigInteger gettCommunitySupplyFId() {		return tCommunitySupplyFId;	}	public void settCommunitySupplyFId(BigInteger tCommunitySupplyFId) {		this.tCommunitySupplyFId = tCommunitySupplyFId;	}	public BigInteger gettCommunitySupplyTmpFId() {		return tCommunitySupplyTmpFId;	}	public void settCommunitySupplyTmpFId(BigInteger tCommunitySupplyTmpFId) {		this.tCommunitySupplyTmpFId = tCommunitySupplyTmpFId;	}
	
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
		CommunitySupplyContect other = (CommunitySupplyContect) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
