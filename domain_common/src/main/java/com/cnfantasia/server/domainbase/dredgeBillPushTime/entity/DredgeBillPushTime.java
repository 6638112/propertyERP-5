package com.cnfantasia.server.domainbase.dredgeBillPushTime.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.Integer;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(疏通工单推送时间记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class DredgeBillPushTime implements Serializable{
/* */

/* 
public class DredgeBillPushTime extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 维修单ID */	private BigInteger tDredgeBillFId;	/** 推送等级 */	private Integer pushLevel;	/** 推送时间 */	private String pushTime;
	public DredgeBillPushTime(){
	}
	public DredgeBillPushTime(DredgeBillPushTime arg){
		this.id = arg.getId();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.pushLevel = arg.getPushLevel();		this.pushTime = arg.getPushTime();
	}
	/**	 * 	 * @param id 	 * @param tDredgeBillFId 维修单ID	 * @param pushLevel 推送等级	 * @param pushTime 推送时间	 */
	public DredgeBillPushTime(BigInteger id,BigInteger tDredgeBillFId,Integer pushLevel,String pushTime){
		this.id = id;		this.tDredgeBillFId = tDredgeBillFId;		this.pushLevel = pushLevel;		this.pushTime = pushTime;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("pushLevel=").append(pushLevel).append(";");		sbf.append("pushTime=").append(pushTime).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public Integer getPushLevel() {		return pushLevel;	}	public void setPushLevel(Integer pushLevel) {		this.pushLevel = pushLevel;	}	public String getPushTime() {		return pushTime;	}	public void setPushTime(String pushTime) {		this.pushTime = pushTime;	}
	
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
		DredgeBillPushTime other = (DredgeBillPushTime) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
