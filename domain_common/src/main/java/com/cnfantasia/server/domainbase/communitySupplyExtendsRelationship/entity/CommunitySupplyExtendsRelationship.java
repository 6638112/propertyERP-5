package com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(社区商家拓展对应信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyExtendsRelationship implements Serializable{
*/


public class CommunitySupplyExtendsRelationship extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 数据来源类型 */	private Integer goalType;	/** 来源数据唯一标识 */	private String goalId;	/** 本地数据唯一标识 */	private BigInteger localId;	/** 信息类别 */	private Integer infoType;
	public CommunitySupplyExtendsRelationship(){
	}
	public CommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship arg){
		this.id = arg.getId();		this.goalType = arg.getGoalType();		this.goalId = arg.getGoalId();		this.localId = arg.getLocalId();		this.infoType = arg.getInfoType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param goalType 数据来源类型	 * @param goalId 来源数据唯一标识	 * @param localId 本地数据唯一标识	 * @param infoType 信息类别	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunitySupplyExtendsRelationship(BigInteger id,Integer goalType,String goalId,BigInteger localId,Integer infoType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.goalType = goalType;		this.goalId = goalId;		this.localId = localId;		this.infoType = infoType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("goalType=").append(goalType).append(";");		sbf.append("goalId=").append(goalId).append(";");		sbf.append("localId=").append(localId).append(";");		sbf.append("infoType=").append(infoType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getGoalType() {		return goalType;	}	public void setGoalType(Integer goalType) {		this.goalType = goalType;	}	public String getGoalId() {		return goalId;	}	public void setGoalId(String goalId) {		this.goalId = goalId;	}	public BigInteger getLocalId() {		return localId;	}	public void setLocalId(BigInteger localId) {		this.localId = localId;	}	public Integer getInfoType() {		return infoType;	}	public void setInfoType(Integer infoType) {		this.infoType = infoType;	}
	
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
		CommunitySupplyExtendsRelationship other = (CommunitySupplyExtendsRelationship) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
