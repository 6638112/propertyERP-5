package com.cnfantasia.server.domainbase.pointCostRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(积分消费记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PointCostRecord implements Serializable{
*/


public class PointCostRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 门牌Id */	private BigInteger roomId;	/** 消费金额 */	private Long value;	/** 积分消费时间 */	private String costTime;	/** 积分消费描述 */	private String costDesc;	/** 消费类型 */	private Integer costType;
	public PointCostRecord(){
	}
	public PointCostRecord(PointCostRecord arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.roomId = arg.getRoomId();		this.value = arg.getValue();		this.costTime = arg.getCostTime();		this.costDesc = arg.getCostDesc();		this.costType = arg.getCostType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param roomId 门牌Id	 * @param value 消费金额	 * @param costTime 积分消费时间	 * @param costDesc 积分消费描述	 * @param costType 消费类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PointCostRecord(BigInteger id,BigInteger userId,BigInteger roomId,Long value,String costTime,String costDesc,Integer costType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.roomId = roomId;		this.value = value;		this.costTime = costTime;		this.costDesc = costDesc;		this.costType = costType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("value=").append(value).append(";");		sbf.append("costTime=").append(costTime).append(";");		sbf.append("costDesc=").append(costDesc).append(";");		sbf.append("costType=").append(costType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public Long getValue() {		return value;	}	public void setValue(Long value) {		this.value = value;	}	public String getCostTime() {		return costTime;	}	public void setCostTime(String costTime) {		this.costTime = costTime;	}	public String getCostDesc() {		return costDesc;	}	public void setCostDesc(String costDesc) {		this.costDesc = costDesc;	}	public Integer getCostType() {		return costType;	}	public void setCostType(Integer costType) {		this.costType = costType;	}
	
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
		PointCostRecord other = (PointCostRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
