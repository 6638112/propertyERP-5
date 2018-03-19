package com.cnfantasia.server.domainbase.inviteRewardRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(邀请奖励纪录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class InviteRewardRecord implements Serializable{
*/


public class InviteRewardRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 邀请奖励关系表ID */	private BigInteger tInviteRewardRelationFId;	/** 纪录类型 */	private Integer recordType;	/** 记录金额，【28,0】小数点2位 */	private Long recordBonus;	/** 纪录时间 */	private String recordTime;	/** 当前用户ID */	private BigInteger userId;	/** 当前门牌ID */	private BigInteger roomId;
	public InviteRewardRecord(){
	}
	public InviteRewardRecord(InviteRewardRecord arg){
		this.id = arg.getId();		this.tInviteRewardRelationFId = arg.gettInviteRewardRelationFId();		this.recordType = arg.getRecordType();		this.recordBonus = arg.getRecordBonus();		this.recordTime = arg.getRecordTime();		this.userId = arg.getUserId();		this.roomId = arg.getRoomId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tInviteRewardRelationFId 邀请奖励关系表ID	 * @param recordType 纪录类型	 * @param recordBonus 记录金额，【28,0】小数点2位	 * @param recordTime 纪录时间	 * @param userId 当前用户ID	 * @param roomId 当前门牌ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 修改时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public InviteRewardRecord(BigInteger id,BigInteger tInviteRewardRelationFId,Integer recordType,Long recordBonus,String recordTime,BigInteger userId,BigInteger roomId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tInviteRewardRelationFId = tInviteRewardRelationFId;		this.recordType = recordType;		this.recordBonus = recordBonus;		this.recordTime = recordTime;		this.userId = userId;		this.roomId = roomId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tInviteRewardRelationFId=").append(tInviteRewardRelationFId).append(";");		sbf.append("recordType=").append(recordType).append(";");		sbf.append("recordBonus=").append(recordBonus).append(";");		sbf.append("recordTime=").append(recordTime).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettInviteRewardRelationFId() {		return tInviteRewardRelationFId;	}	public void settInviteRewardRelationFId(BigInteger tInviteRewardRelationFId) {		this.tInviteRewardRelationFId = tInviteRewardRelationFId;	}	public Integer getRecordType() {		return recordType;	}	public void setRecordType(Integer recordType) {		this.recordType = recordType;	}	public Long getRecordBonus() {		return recordBonus;	}	public void setRecordBonus(Long recordBonus) {		this.recordBonus = recordBonus;	}	public String getRecordTime() {		return recordTime;	}	public void setRecordTime(String recordTime) {		this.recordTime = recordTime;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}
	
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
		InviteRewardRecord other = (InviteRewardRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
