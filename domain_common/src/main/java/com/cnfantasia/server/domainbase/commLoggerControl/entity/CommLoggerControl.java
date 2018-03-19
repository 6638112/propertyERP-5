package com.cnfantasia.server.domainbase.commLoggerControl.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(公共日志记录控制表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommLoggerControl implements Serializable{
*/


public class CommLoggerControl extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tCommSysUrlFId;	/** 是否忽略请求的参数 */	private Integer ignoreParams;	/** 是否忽略返回的结果 */	private Integer ignoreResponse;	/** 是否需要记录日志 */	private Integer needRecord;
	public CommLoggerControl(){
	}
	public CommLoggerControl(CommLoggerControl arg){
		this.id = arg.getId();		this.tCommSysUrlFId = arg.gettCommSysUrlFId();		this.ignoreParams = arg.getIgnoreParams();		this.ignoreResponse = arg.getIgnoreResponse();		this.needRecord = arg.getNeedRecord();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tCommSysUrlFId 	 * @param ignoreParams 是否忽略请求的参数	 * @param ignoreResponse 是否忽略返回的结果	 * @param needRecord 是否需要记录日志	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommLoggerControl(BigInteger id,BigInteger tCommSysUrlFId,Integer ignoreParams,Integer ignoreResponse,Integer needRecord,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tCommSysUrlFId = tCommSysUrlFId;		this.ignoreParams = ignoreParams;		this.ignoreResponse = ignoreResponse;		this.needRecord = needRecord;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tCommSysUrlFId=").append(tCommSysUrlFId).append(";");		sbf.append("ignoreParams=").append(ignoreParams).append(";");		sbf.append("ignoreResponse=").append(ignoreResponse).append(";");		sbf.append("needRecord=").append(needRecord).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettCommSysUrlFId() {		return tCommSysUrlFId;	}	public void settCommSysUrlFId(BigInteger tCommSysUrlFId) {		this.tCommSysUrlFId = tCommSysUrlFId;	}	public Integer getIgnoreParams() {		return ignoreParams;	}	public void setIgnoreParams(Integer ignoreParams) {		this.ignoreParams = ignoreParams;	}	public Integer getIgnoreResponse() {		return ignoreResponse;	}	public void setIgnoreResponse(Integer ignoreResponse) {		this.ignoreResponse = ignoreResponse;	}	public Integer getNeedRecord() {		return needRecord;	}	public void setNeedRecord(Integer needRecord) {		this.needRecord = needRecord;	}
	
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
		CommLoggerControl other = (CommLoggerControl) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
