package com.cnfantasia.server.domainbase.loginNoBindRelation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(登录账号绑定关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LoginNoBindRelation implements Serializable{
*/


public class LoginNoBindRelation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 主账号的用户Id */	private BigInteger mainUserId;	/** 申请绑定的用户Id */	private BigInteger applyUserId;	/** 主账号Id */	private BigInteger mainLoginNoId;	/** 申请绑定的账号Id */	private BigInteger applyLoginNoId;	/** 绑定关系创建时间 */	private String createTime;	/** 创建者用户Id */	private BigInteger createUserId;	/** 创建者门牌Id */	private BigInteger createRoomId;	/** 主账号No */	private String mainAccNo;	/** 主账号Type */	private Long mainAccType;	/** 申请绑定的账号No */	private String applyAccNo;	/** 申请绑定的账号Type */	private Long applyAccType;
	public LoginNoBindRelation(){
	}
	public LoginNoBindRelation(LoginNoBindRelation arg){
		this.id = arg.getId();		this.mainUserId = arg.getMainUserId();		this.applyUserId = arg.getApplyUserId();		this.mainLoginNoId = arg.getMainLoginNoId();		this.applyLoginNoId = arg.getApplyLoginNoId();		this.createTime = arg.getCreateTime();		this.createUserId = arg.getCreateUserId();		this.createRoomId = arg.getCreateRoomId();		this.mainAccNo = arg.getMainAccNo();		this.mainAccType = arg.getMainAccType();		this.applyAccNo = arg.getApplyAccNo();		this.applyAccType = arg.getApplyAccType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param mainUserId 主账号的用户Id	 * @param applyUserId 申请绑定的用户Id	 * @param mainLoginNoId 主账号Id	 * @param applyLoginNoId 申请绑定的账号Id	 * @param createTime 绑定关系创建时间	 * @param createUserId 创建者用户Id	 * @param createRoomId 创建者门牌Id	 * @param mainAccNo 主账号No	 * @param mainAccType 主账号Type	 * @param applyAccNo 申请绑定的账号No	 * @param applyAccType 申请绑定的账号Type	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public LoginNoBindRelation(BigInteger id,BigInteger mainUserId,BigInteger applyUserId,BigInteger mainLoginNoId,BigInteger applyLoginNoId,String createTime,BigInteger createUserId,BigInteger createRoomId,String mainAccNo,Long mainAccType,String applyAccNo,Long applyAccType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.mainUserId = mainUserId;		this.applyUserId = applyUserId;		this.mainLoginNoId = mainLoginNoId;		this.applyLoginNoId = applyLoginNoId;		this.createTime = createTime;		this.createUserId = createUserId;		this.createRoomId = createRoomId;		this.mainAccNo = mainAccNo;		this.mainAccType = mainAccType;		this.applyAccNo = applyAccNo;		this.applyAccType = applyAccType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("mainUserId=").append(mainUserId).append(";");		sbf.append("applyUserId=").append(applyUserId).append(";");		sbf.append("mainLoginNoId=").append(mainLoginNoId).append(";");		sbf.append("applyLoginNoId=").append(applyLoginNoId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("createUserId=").append(createUserId).append(";");		sbf.append("createRoomId=").append(createRoomId).append(";");		sbf.append("mainAccNo=").append(mainAccNo).append(";");		sbf.append("mainAccType=").append(mainAccType).append(";");		sbf.append("applyAccNo=").append(applyAccNo).append(";");		sbf.append("applyAccType=").append(applyAccType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getMainUserId() {		return mainUserId;	}	public void setMainUserId(BigInteger mainUserId) {		this.mainUserId = mainUserId;	}	public BigInteger getApplyUserId() {		return applyUserId;	}	public void setApplyUserId(BigInteger applyUserId) {		this.applyUserId = applyUserId;	}	public BigInteger getMainLoginNoId() {		return mainLoginNoId;	}	public void setMainLoginNoId(BigInteger mainLoginNoId) {		this.mainLoginNoId = mainLoginNoId;	}	public BigInteger getApplyLoginNoId() {		return applyLoginNoId;	}	public void setApplyLoginNoId(BigInteger applyLoginNoId) {		this.applyLoginNoId = applyLoginNoId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public BigInteger getCreateUserId() {		return createUserId;	}	public void setCreateUserId(BigInteger createUserId) {		this.createUserId = createUserId;	}	public BigInteger getCreateRoomId() {		return createRoomId;	}	public void setCreateRoomId(BigInteger createRoomId) {		this.createRoomId = createRoomId;	}	public String getMainAccNo() {		return mainAccNo;	}	public void setMainAccNo(String mainAccNo) {		this.mainAccNo = mainAccNo;	}	public Long getMainAccType() {		return mainAccType;	}	public void setMainAccType(Long mainAccType) {		this.mainAccType = mainAccType;	}	public String getApplyAccNo() {		return applyAccNo;	}	public void setApplyAccNo(String applyAccNo) {		this.applyAccNo = applyAccNo;	}	public Long getApplyAccType() {		return applyAccType;	}	public void setApplyAccType(Long applyAccType) {		this.applyAccType = applyAccType;	}
	
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
		LoginNoBindRelation other = (LoginNoBindRelation) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
