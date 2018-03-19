package com.cnfantasia.server.domainbase.communitySupplyBelong.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商家认领) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyBelong implements Serializable{
*/


public class CommunitySupplyBelong extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 认领人 */	private BigInteger claimUserId;	/**  */	private BigInteger tCommunitySupplyCompanyFId;	/** 认领人的门牌ID */	private BigInteger claimRoomId;	/** 申请认领时间 */	private String creatTime;	/** 认领状态 */	private Integer auditStatus;	/** 审核结果描述 */	private String auditDesc;	/** 审核时间 */	private String auditTime;	/** 审核人 */	private BigInteger auditOmsUserId;	/** 小区ID */	private BigInteger groupBuildingId;	/** 店铺ID */	private BigInteger communitySupplyId;	/** 认领的哪个小区的店铺 */	private BigInteger tGroupBuildingHasTCommunitySupplyFId;	/** 商户的电话 */	private String supplyCompanyPhone;
	public CommunitySupplyBelong(){
	}
	public CommunitySupplyBelong(CommunitySupplyBelong arg){
		this.id = arg.getId();		this.claimUserId = arg.getClaimUserId();		this.tCommunitySupplyCompanyFId = arg.gettCommunitySupplyCompanyFId();		this.claimRoomId = arg.getClaimRoomId();		this.creatTime = arg.getCreatTime();		this.auditStatus = arg.getAuditStatus();		this.auditDesc = arg.getAuditDesc();		this.auditTime = arg.getAuditTime();		this.auditOmsUserId = arg.getAuditOmsUserId();		this.groupBuildingId = arg.getGroupBuildingId();		this.communitySupplyId = arg.getCommunitySupplyId();		this.tGroupBuildingHasTCommunitySupplyFId = arg.gettGroupBuildingHasTCommunitySupplyFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelState = arg.getSys0DelState();		this.sys0DelUser = arg.getSys0DelUser();		this.supplyCompanyPhone = arg.getSupplyCompanyPhone();
	}
	/**	 * 	 * @param id 	 * @param claimUserId 认领人	 * @param tCommunitySupplyCompanyFId 	 * @param claimRoomId 认领人的门牌ID	 * @param creatTime 申请认领时间	 * @param auditStatus 认领状态	 * @param auditDesc 审核结果描述	 * @param auditTime 审核时间	 * @param auditOmsUserId 审核人	 * @param groupBuildingId 小区ID	 * @param communitySupplyId 店铺ID	 * @param tGroupBuildingHasTCommunitySupplyFId 认领的哪个小区的店铺	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelState 记录状态	 * @param sys0DelUser 删除者	 * @param supplyCompanyPhone 商户的电话	 */
	public CommunitySupplyBelong(BigInteger id,BigInteger claimUserId,BigInteger tCommunitySupplyCompanyFId,BigInteger claimRoomId,String creatTime,Integer auditStatus,String auditDesc,String auditTime,BigInteger auditOmsUserId,BigInteger groupBuildingId,BigInteger communitySupplyId,BigInteger tGroupBuildingHasTCommunitySupplyFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,Integer sys0DelState,BigInteger sys0DelUser,String supplyCompanyPhone){
		this.id = id;		this.claimUserId = claimUserId;		this.tCommunitySupplyCompanyFId = tCommunitySupplyCompanyFId;		this.claimRoomId = claimRoomId;		this.creatTime = creatTime;		this.auditStatus = auditStatus;		this.auditDesc = auditDesc;		this.auditTime = auditTime;		this.auditOmsUserId = auditOmsUserId;		this.groupBuildingId = groupBuildingId;		this.communitySupplyId = communitySupplyId;		this.tGroupBuildingHasTCommunitySupplyFId = tGroupBuildingHasTCommunitySupplyFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelState = sys0DelState;		this.sys0DelUser = sys0DelUser;		this.supplyCompanyPhone = supplyCompanyPhone;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("claimUserId=").append(claimUserId).append(";");		sbf.append("tCommunitySupplyCompanyFId=").append(tCommunitySupplyCompanyFId).append(";");		sbf.append("claimRoomId=").append(claimRoomId).append(";");		sbf.append("creatTime=").append(creatTime).append(";");		sbf.append("auditStatus=").append(auditStatus).append(";");		sbf.append("auditDesc=").append(auditDesc).append(";");		sbf.append("auditTime=").append(auditTime).append(";");		sbf.append("auditOmsUserId=").append(auditOmsUserId).append(";");		sbf.append("groupBuildingId=").append(groupBuildingId).append(";");		sbf.append("communitySupplyId=").append(communitySupplyId).append(";");		sbf.append("tGroupBuildingHasTCommunitySupplyFId=").append(tGroupBuildingHasTCommunitySupplyFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("supplyCompanyPhone=").append(supplyCompanyPhone).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getClaimUserId() {		return claimUserId;	}	public void setClaimUserId(BigInteger claimUserId) {		this.claimUserId = claimUserId;	}	public BigInteger gettCommunitySupplyCompanyFId() {		return tCommunitySupplyCompanyFId;	}	public void settCommunitySupplyCompanyFId(BigInteger tCommunitySupplyCompanyFId) {		this.tCommunitySupplyCompanyFId = tCommunitySupplyCompanyFId;	}	public BigInteger getClaimRoomId() {		return claimRoomId;	}	public void setClaimRoomId(BigInteger claimRoomId) {		this.claimRoomId = claimRoomId;	}	public String getCreatTime() {		return creatTime;	}	public void setCreatTime(String creatTime) {		this.creatTime = creatTime;	}	public Integer getAuditStatus() {		return auditStatus;	}	public void setAuditStatus(Integer auditStatus) {		this.auditStatus = auditStatus;	}	public String getAuditDesc() {		return auditDesc;	}	public void setAuditDesc(String auditDesc) {		this.auditDesc = auditDesc;	}	public String getAuditTime() {		return auditTime;	}	public void setAuditTime(String auditTime) {		this.auditTime = auditTime;	}	public BigInteger getAuditOmsUserId() {		return auditOmsUserId;	}	public void setAuditOmsUserId(BigInteger auditOmsUserId) {		this.auditOmsUserId = auditOmsUserId;	}	public BigInteger getGroupBuildingId() {		return groupBuildingId;	}	public void setGroupBuildingId(BigInteger groupBuildingId) {		this.groupBuildingId = groupBuildingId;	}	public BigInteger getCommunitySupplyId() {		return communitySupplyId;	}	public void setCommunitySupplyId(BigInteger communitySupplyId) {		this.communitySupplyId = communitySupplyId;	}	public BigInteger gettGroupBuildingHasTCommunitySupplyFId() {		return tGroupBuildingHasTCommunitySupplyFId;	}	public void settGroupBuildingHasTCommunitySupplyFId(BigInteger tGroupBuildingHasTCommunitySupplyFId) {		this.tGroupBuildingHasTCommunitySupplyFId = tGroupBuildingHasTCommunitySupplyFId;	}	public String getSupplyCompanyPhone() {		return supplyCompanyPhone;	}	public void setSupplyCompanyPhone(String supplyCompanyPhone) {		this.supplyCompanyPhone = supplyCompanyPhone;	}
	
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
		CommunitySupplyBelong other = (CommunitySupplyBelong) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
