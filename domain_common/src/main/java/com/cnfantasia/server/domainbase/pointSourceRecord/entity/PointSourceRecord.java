package com.cnfantasia.server.domainbase.pointSourceRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(积分来源记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PointSourceRecord implements Serializable{
*/


public class PointSourceRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 门牌Id */	private BigInteger roomId;	/** 积分取值 */	private Long value;	/** 积分获取时间 */	private String fromTime;	/** 来源类型 */	private Integer type;	/** 积分获取描述 */	private String desc;	/** 签到积分对应的天数,f_type=3时有效 */	private Long signDayCount;
	public PointSourceRecord(){
	}
	public PointSourceRecord(PointSourceRecord arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.roomId = arg.getRoomId();		this.value = arg.getValue();		this.fromTime = arg.getFromTime();		this.type = arg.getType();		this.desc = arg.getDesc();		this.signDayCount = arg.getSignDayCount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param roomId 门牌Id	 * @param value 积分取值	 * @param fromTime 积分获取时间	 * @param type 来源类型	 * @param desc 积分获取描述	 * @param signDayCount 签到积分对应的天数,f_type=3时有效	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PointSourceRecord(BigInteger id,BigInteger userId,BigInteger roomId,Long value,String fromTime,Integer type,String desc,Long signDayCount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.roomId = roomId;		this.value = value;		this.fromTime = fromTime;		this.type = type;		this.desc = desc;		this.signDayCount = signDayCount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("value=").append(value).append(";");		sbf.append("fromTime=").append(fromTime).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("signDayCount=").append(signDayCount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public Long getValue() {		return value;	}	public void setValue(Long value) {		this.value = value;	}	public String getFromTime() {		return fromTime;	}	public void setFromTime(String fromTime) {		this.fromTime = fromTime;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Long getSignDayCount() {		return signDayCount;	}	public void setSignDayCount(Long signDayCount) {		this.signDayCount = signDayCount;	}
	
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
		PointSourceRecord other = (PointSourceRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
