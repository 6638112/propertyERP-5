package com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(业主门禁认证信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CloudKeyUserAudit implements Serializable{
*/


public class CloudKeyUserAudit extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 手机号 */	private String phoneNo;	/** 身份证号 */	private String userNo;	/** 用户姓名 */	private String userName;	/** 身份证图片 */	private String photoUrl;	/** 购买人解放号 */	private BigInteger huaId;	/** 楼栋Id */	private BigInteger tBuildingFId;	/** 门牌Id */	private BigInteger tRealRoomFId;	/** 小区Id */	private BigInteger tGroupBuildingFId;	/** 物业管理处Id */	private BigInteger tPropertyManagementFId;	/** 审核时间 */	private String auditTime;	/** 审核失败给出原因 */	private String auditReason;	/** 审核状态 */	private Integer status;
	public CloudKeyUserAudit(){
	}
	public CloudKeyUserAudit(CloudKeyUserAudit arg){
		this.id = arg.getId();		this.phoneNo = arg.getPhoneNo();		this.userNo = arg.getUserNo();		this.userName = arg.getUserName();		this.photoUrl = arg.getPhotoUrl();		this.huaId = arg.getHuaId();		this.tBuildingFId = arg.gettBuildingFId();		this.tRealRoomFId = arg.gettRealRoomFId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();		this.auditTime = arg.getAuditTime();		this.auditReason = arg.getAuditReason();		this.status = arg.getStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param phoneNo 手机号	 * @param userNo 身份证号	 * @param userName 用户姓名	 * @param photoUrl 身份证图片	 * @param huaId 购买人解放号	 * @param tBuildingFId 楼栋Id	 * @param tRealRoomFId 门牌Id	 * @param tGroupBuildingFId 小区Id	 * @param tPropertyManagementFId 物业管理处Id	 * @param auditTime 审核时间	 * @param auditReason 审核失败给出原因	 * @param status 审核状态	 * @param sys0AddTime 申请时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CloudKeyUserAudit(BigInteger id,String phoneNo,String userNo,String userName,String photoUrl,BigInteger huaId,BigInteger tBuildingFId,BigInteger tRealRoomFId,BigInteger tGroupBuildingFId,BigInteger tPropertyManagementFId,String auditTime,String auditReason,Integer status,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.phoneNo = phoneNo;		this.userNo = userNo;		this.userName = userName;		this.photoUrl = photoUrl;		this.huaId = huaId;		this.tBuildingFId = tBuildingFId;		this.tRealRoomFId = tRealRoomFId;		this.tGroupBuildingFId = tGroupBuildingFId;		this.tPropertyManagementFId = tPropertyManagementFId;		this.auditTime = auditTime;		this.auditReason = auditReason;		this.status = status;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("phoneNo=").append(phoneNo).append(";");		sbf.append("userNo=").append(userNo).append(";");		sbf.append("userName=").append(userName).append(";");		sbf.append("photoUrl=").append(photoUrl).append(";");		sbf.append("huaId=").append(huaId).append(";");		sbf.append("tBuildingFId=").append(tBuildingFId).append(";");		sbf.append("tRealRoomFId=").append(tRealRoomFId).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		sbf.append("auditTime=").append(auditTime).append(";");		sbf.append("auditReason=").append(auditReason).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPhoneNo() {		return phoneNo;	}	public void setPhoneNo(String phoneNo) {		this.phoneNo = phoneNo;	}	public String getUserNo() {		return userNo;	}	public void setUserNo(String userNo) {		this.userNo = userNo;	}	public String getUserName() {		return userName;	}	public void setUserName(String userName) {		this.userName = userName;	}	public String getPhotoUrl() {		return photoUrl;	}	public void setPhotoUrl(String photoUrl) {		this.photoUrl = photoUrl;	}	public BigInteger getHuaId() {		return huaId;	}	public void setHuaId(BigInteger huaId) {		this.huaId = huaId;	}	public BigInteger gettBuildingFId() {		return tBuildingFId;	}	public void settBuildingFId(BigInteger tBuildingFId) {		this.tBuildingFId = tBuildingFId;	}	public BigInteger gettRealRoomFId() {		return tRealRoomFId;	}	public void settRealRoomFId(BigInteger tRealRoomFId) {		this.tRealRoomFId = tRealRoomFId;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}	public String getAuditTime() {		return auditTime;	}	public void setAuditTime(String auditTime) {		this.auditTime = auditTime;	}	public String getAuditReason() {		return auditReason;	}	public void setAuditReason(String auditReason) {		this.auditReason = auditReason;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}
	
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
		CloudKeyUserAudit other = (CloudKeyUserAudit) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
