package com.cnfantasia.server.domainbase.roomValidate.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Integer;
import java.lang.String;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(门牌校验信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RoomValidate implements Serializable{
*/


public class RoomValidate extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 验证状态 */
	private Integer verifyStatus;
	/** 提交验证的图片地址 */
	private String verifyPicUrl;
	/** 审核结果描述 */
	private String verifyResultDesc;
	/** 所属虚拟room门牌Id */
	private BigInteger tRoomFId;
	/** 提交验证的用户Id */
	private BigInteger userId;
	/** 用户提交的记录数 */
	private Integer recordNum;
	/** 验证通过时间 */
	private String verifySuccTime;
	/** 验证信息来源类型 */
	private Integer sourceType;
	/** 绑定前的old验证用户Id */
	private BigInteger oldUserId;

	public RoomValidate(){
	}
	public RoomValidate(RoomValidate arg){
		this.id = arg.getId();
		this.verifyStatus = arg.getVerifyStatus();
		this.verifyPicUrl = arg.getVerifyPicUrl();
		this.verifyResultDesc = arg.getVerifyResultDesc();
		this.tRoomFId = arg.gettRoomFId();
		this.userId = arg.getUserId();
		this.recordNum = arg.getRecordNum();
		this.verifySuccTime = arg.getVerifySuccTime();
		this.sourceType = arg.getSourceType();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.oldUserId = arg.getOldUserId();

	}
	/**
	 * 
	 * @param id 
	 * @param verifyStatus 验证状态
	 * @param verifyPicUrl 提交验证的图片地址
	 * @param verifyResultDesc 审核结果描述
	 * @param tRoomFId 所属虚拟room门牌Id
	 * @param userId 提交验证的用户Id
	 * @param recordNum 用户提交的记录数
	 * @param verifySuccTime 验证通过时间
	 * @param sourceType 验证信息来源类型
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param oldUserId 绑定前的old验证用户Id
	 */

	public RoomValidate(BigInteger id,Integer verifyStatus,String verifyPicUrl,String verifyResultDesc,BigInteger tRoomFId,BigInteger userId,Integer recordNum,String verifySuccTime,Integer sourceType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger oldUserId){
		this.id = id;
		this.verifyStatus = verifyStatus;
		this.verifyPicUrl = verifyPicUrl;
		this.verifyResultDesc = verifyResultDesc;
		this.tRoomFId = tRoomFId;
		this.userId = userId;
		this.recordNum = recordNum;
		this.verifySuccTime = verifySuccTime;
		this.sourceType = sourceType;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.oldUserId = oldUserId;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("verifyStatus=").append(verifyStatus).append(";");
		sbf.append("verifyPicUrl=").append(verifyPicUrl).append(";");
		sbf.append("verifyResultDesc=").append(verifyResultDesc).append(";");
		sbf.append("tRoomFId=").append(tRoomFId).append(";");
		sbf.append("userId=").append(userId).append(";");
		sbf.append("recordNum=").append(recordNum).append(";");
		sbf.append("verifySuccTime=").append(verifySuccTime).append(";");
		sbf.append("sourceType=").append(sourceType).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("oldUserId=").append(oldUserId).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Integer getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getVerifyPicUrl() {
		return verifyPicUrl;
	}
	public void setVerifyPicUrl(String verifyPicUrl) {
		this.verifyPicUrl = verifyPicUrl;
	}
	public String getVerifyResultDesc() {
		return verifyResultDesc;
	}
	public void setVerifyResultDesc(String verifyResultDesc) {
		this.verifyResultDesc = verifyResultDesc;
	}
	public BigInteger gettRoomFId() {
		return tRoomFId;
	}
	public void settRoomFId(BigInteger tRoomFId) {
		this.tRoomFId = tRoomFId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Integer getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}
	public String getVerifySuccTime() {
		return verifySuccTime;
	}
	public void setVerifySuccTime(String verifySuccTime) {
		this.verifySuccTime = verifySuccTime;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public BigInteger getOldUserId() {
		return oldUserId;
	}
	public void setOldUserId(BigInteger oldUserId) {
		this.oldUserId = oldUserId;
	}

	
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
		RoomValidate other = (RoomValidate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
