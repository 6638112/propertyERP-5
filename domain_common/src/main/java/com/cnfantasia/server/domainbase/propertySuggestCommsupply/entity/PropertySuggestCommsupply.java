package com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业推荐信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertySuggestCommsupply implements Serializable{
*/


public class PropertySuggestCommsupply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 商家Id */	private BigInteger tCommunitySupplyFId;	/** 物业公司Id */	private BigInteger tPropertyCompanyFId;	/** 推荐时间 */	private String time;	/** 小区Id */	private BigInteger groupbulidId;	/** 审核状态 */	private Integer auditStatus;	/** 审核结果描述 */	private String auditDesc;	/** 审核时间 */	private String auditTime;
	public PropertySuggestCommsupply(){
	}
	public PropertySuggestCommsupply(PropertySuggestCommsupply arg){
		this.id = arg.getId();		this.tCommunitySupplyFId = arg.gettCommunitySupplyFId();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.time = arg.getTime();		this.groupbulidId = arg.getGroupbulidId();		this.auditStatus = arg.getAuditStatus();		this.auditDesc = arg.getAuditDesc();		this.auditTime = arg.getAuditTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tCommunitySupplyFId 商家Id	 * @param tPropertyCompanyFId 物业公司Id	 * @param time 推荐时间	 * @param groupbulidId 小区Id	 * @param auditStatus 审核状态	 * @param auditDesc 审核结果描述	 * @param auditTime 审核时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertySuggestCommsupply(BigInteger id,BigInteger tCommunitySupplyFId,BigInteger tPropertyCompanyFId,String time,BigInteger groupbulidId,Integer auditStatus,String auditDesc,String auditTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tCommunitySupplyFId = tCommunitySupplyFId;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.time = time;		this.groupbulidId = groupbulidId;		this.auditStatus = auditStatus;		this.auditDesc = auditDesc;		this.auditTime = auditTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tCommunitySupplyFId=").append(tCommunitySupplyFId).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("time=").append(time).append(";");		sbf.append("groupbulidId=").append(groupbulidId).append(";");		sbf.append("auditStatus=").append(auditStatus).append(";");		sbf.append("auditDesc=").append(auditDesc).append(";");		sbf.append("auditTime=").append(auditTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettCommunitySupplyFId() {		return tCommunitySupplyFId;	}	public void settCommunitySupplyFId(BigInteger tCommunitySupplyFId) {		this.tCommunitySupplyFId = tCommunitySupplyFId;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}	public BigInteger getGroupbulidId() {		return groupbulidId;	}	public void setGroupbulidId(BigInteger groupbulidId) {		this.groupbulidId = groupbulidId;	}	public Integer getAuditStatus() {		return auditStatus;	}	public void setAuditStatus(Integer auditStatus) {		this.auditStatus = auditStatus;	}	public String getAuditDesc() {		return auditDesc;	}	public void setAuditDesc(String auditDesc) {		this.auditDesc = auditDesc;	}	public String getAuditTime() {		return auditTime;	}	public void setAuditTime(String auditTime) {		this.auditTime = auditTime;	}
	
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
		PropertySuggestCommsupply other = (PropertySuggestCommsupply) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
