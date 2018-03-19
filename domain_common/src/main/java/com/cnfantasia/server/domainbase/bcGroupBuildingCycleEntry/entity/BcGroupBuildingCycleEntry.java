package com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(银行托收账期明细) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BcGroupBuildingCycleEntry implements Serializable{
*/


public class BcGroupBuildingCycleEntry extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账期id */	private BigInteger gbbcId;	/** 修改时间 */	private String sys0EditTime;	/**  */	private BigInteger tBcGroupBuildingCycleFId;
	public BcGroupBuildingCycleEntry(){
	}
	public BcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry arg){
		this.id = arg.getId();		this.gbbcId = arg.getGbbcId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0EditTime = arg.getSys0EditTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tBcGroupBuildingCycleFId = arg.gettBcGroupBuildingCycleFId();
	}
	/**	 * 	 * @param id 	 * @param gbbcId 账期id	 * @param sys0AddTime 新增时间	 * @param sys0EditTime 修改时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tBcGroupBuildingCycleFId 	 */
	public BcGroupBuildingCycleEntry(BigInteger id,BigInteger gbbcId,String sys0AddTime,String sys0EditTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tBcGroupBuildingCycleFId){
		this.id = id;		this.gbbcId = gbbcId;		this.sys0AddTime = sys0AddTime;		this.sys0EditTime = sys0EditTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("gbbcId=").append(gbbcId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0EditTime=").append(sys0EditTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tBcGroupBuildingCycleFId=").append(tBcGroupBuildingCycleFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getGbbcId() {		return gbbcId;	}	public void setGbbcId(BigInteger gbbcId) {		this.gbbcId = gbbcId;	}	public String getSys0EditTime() {		return sys0EditTime;	}	public void setSys0EditTime(String sys0EditTime) {		this.sys0EditTime = sys0EditTime;	}	public BigInteger gettBcGroupBuildingCycleFId() {		return tBcGroupBuildingCycleFId;	}	public void settBcGroupBuildingCycleFId(BigInteger tBcGroupBuildingCycleFId) {		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;	}
	
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
		BcGroupBuildingCycleEntry other = (BcGroupBuildingCycleEntry) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
