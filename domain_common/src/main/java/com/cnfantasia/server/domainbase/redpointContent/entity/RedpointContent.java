package com.cnfantasia.server.domainbase.redpointContent.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(红点信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RedpointContent implements Serializable{
*/


public class RedpointContent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 用户类别 */	private Integer userType;	/** 门牌Id */	private BigInteger roomId;	/**  */	private String modelCode;	/** 点击状态 */	private Integer clickStatus;	/** 红点有效时间，过期失效 */	private String expireTime;	/** 最后一次修改时间 */	private String lastModifyTime;	/** 最后一次点击时间 */	private String lastClickTime;
	public RedpointContent(){
	}
	public RedpointContent(RedpointContent arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.userType = arg.getUserType();		this.roomId = arg.getRoomId();		this.modelCode = arg.getModelCode();		this.clickStatus = arg.getClickStatus();		this.expireTime = arg.getExpireTime();		this.lastModifyTime = arg.getLastModifyTime();		this.lastClickTime = arg.getLastClickTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param userType 用户类别	 * @param roomId 门牌Id	 * @param modelCode 	 * @param clickStatus 点击状态	 * @param expireTime 红点有效时间，过期失效	 * @param lastModifyTime 最后一次修改时间	 * @param lastClickTime 最后一次点击时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RedpointContent(BigInteger id,BigInteger userId,Integer userType,BigInteger roomId,String modelCode,Integer clickStatus,String expireTime,String lastModifyTime,String lastClickTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.userType = userType;		this.roomId = roomId;		this.modelCode = modelCode;		this.clickStatus = clickStatus;		this.expireTime = expireTime;		this.lastModifyTime = lastModifyTime;		this.lastClickTime = lastClickTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("userType=").append(userType).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("modelCode=").append(modelCode).append(";");		sbf.append("clickStatus=").append(clickStatus).append(";");		sbf.append("expireTime=").append(expireTime).append(";");		sbf.append("lastModifyTime=").append(lastModifyTime).append(";");		sbf.append("lastClickTime=").append(lastClickTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public Integer getUserType() {		return userType;	}	public void setUserType(Integer userType) {		this.userType = userType;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public String getModelCode() {		return modelCode;	}	public void setModelCode(String modelCode) {		this.modelCode = modelCode;	}	public Integer getClickStatus() {		return clickStatus;	}	public void setClickStatus(Integer clickStatus) {		this.clickStatus = clickStatus;	}	public String getExpireTime() {		return expireTime;	}	public void setExpireTime(String expireTime) {		this.expireTime = expireTime;	}	public String getLastModifyTime() {		return lastModifyTime;	}	public void setLastModifyTime(String lastModifyTime) {		this.lastModifyTime = lastModifyTime;	}	public String getLastClickTime() {		return lastClickTime;	}	public void setLastClickTime(String lastClickTime) {		this.lastClickTime = lastClickTime;	}
	
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
		RedpointContent other = (RedpointContent) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
