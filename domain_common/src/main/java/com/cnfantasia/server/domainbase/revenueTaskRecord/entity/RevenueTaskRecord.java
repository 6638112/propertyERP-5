package com.cnfantasia.server.domainbase.revenueTaskRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(定时同步收益额记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RevenueTaskRecord implements Serializable{
*/


public class RevenueTaskRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private String startTime;	/**  */	private String finishTime;	/** 所属最小粒度角色类型 */	private Integer miniRoleType;	/**  */	private BigInteger miniRoleId;	/** 提款对象类型 */	private Integer goalType;	/**  */	private Long succCount;	/**  */	private Long failedCount;	/** 最近同步成功的时间 */	private String lastSynTime;	/** 错误堆栈信息 */	private String excepStackInfo;
	public RevenueTaskRecord(){
	}
	public RevenueTaskRecord(RevenueTaskRecord arg){
		this.id = arg.getId();		this.startTime = arg.getStartTime();		this.finishTime = arg.getFinishTime();		this.miniRoleType = arg.getMiniRoleType();		this.miniRoleId = arg.getMiniRoleId();		this.goalType = arg.getGoalType();		this.succCount = arg.getSuccCount();		this.failedCount = arg.getFailedCount();		this.lastSynTime = arg.getLastSynTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.excepStackInfo = arg.getExcepStackInfo();
	}
	/**	 * 	 * @param id 	 * @param startTime 	 * @param finishTime 	 * @param miniRoleType 所属最小粒度角色类型	 * @param miniRoleId 	 * @param goalType 提款对象类型	 * @param succCount 	 * @param failedCount 	 * @param lastSynTime 最近同步成功的时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param excepStackInfo 错误堆栈信息	 */
	public RevenueTaskRecord(BigInteger id,String startTime,String finishTime,Integer miniRoleType,BigInteger miniRoleId,Integer goalType,Long succCount,Long failedCount,String lastSynTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String excepStackInfo){
		this.id = id;		this.startTime = startTime;		this.finishTime = finishTime;		this.miniRoleType = miniRoleType;		this.miniRoleId = miniRoleId;		this.goalType = goalType;		this.succCount = succCount;		this.failedCount = failedCount;		this.lastSynTime = lastSynTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.excepStackInfo = excepStackInfo;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("startTime=").append(startTime).append(";");		sbf.append("finishTime=").append(finishTime).append(";");		sbf.append("miniRoleType=").append(miniRoleType).append(";");		sbf.append("miniRoleId=").append(miniRoleId).append(";");		sbf.append("goalType=").append(goalType).append(";");		sbf.append("succCount=").append(succCount).append(";");		sbf.append("failedCount=").append(failedCount).append(";");		sbf.append("lastSynTime=").append(lastSynTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("excepStackInfo=").append(excepStackInfo).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getStartTime() {		return startTime;	}	public void setStartTime(String startTime) {		this.startTime = startTime;	}	public String getFinishTime() {		return finishTime;	}	public void setFinishTime(String finishTime) {		this.finishTime = finishTime;	}	public Integer getMiniRoleType() {		return miniRoleType;	}	public void setMiniRoleType(Integer miniRoleType) {		this.miniRoleType = miniRoleType;	}	public BigInteger getMiniRoleId() {		return miniRoleId;	}	public void setMiniRoleId(BigInteger miniRoleId) {		this.miniRoleId = miniRoleId;	}	public Integer getGoalType() {		return goalType;	}	public void setGoalType(Integer goalType) {		this.goalType = goalType;	}	public Long getSuccCount() {		return succCount;	}	public void setSuccCount(Long succCount) {		this.succCount = succCount;	}	public Long getFailedCount() {		return failedCount;	}	public void setFailedCount(Long failedCount) {		this.failedCount = failedCount;	}	public String getLastSynTime() {		return lastSynTime;	}	public void setLastSynTime(String lastSynTime) {		this.lastSynTime = lastSynTime;	}	public String getExcepStackInfo() {		return excepStackInfo;	}	public void setExcepStackInfo(String excepStackInfo) {		this.excepStackInfo = excepStackInfo;	}
	
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
		RevenueTaskRecord other = (RevenueTaskRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
