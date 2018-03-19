package com.cnfantasia.server.domainbase.redpointContentSource.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(红点来源表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RedpointContentSource implements Serializable{
*/


public class RedpointContentSource extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tRedpointContentFId;	/** 来源类型 */	private Integer sourceType;	/** 来源Id */	private BigInteger sourceId;	/**  */	private String lastModifyTime;	/** 记录操作描述,CUD */	private String operationType;
	public RedpointContentSource(){
	}
	public RedpointContentSource(RedpointContentSource arg){
		this.id = arg.getId();		this.tRedpointContentFId = arg.gettRedpointContentFId();		this.sourceType = arg.getSourceType();		this.sourceId = arg.getSourceId();		this.lastModifyTime = arg.getLastModifyTime();		this.operationType = arg.getOperationType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRedpointContentFId 	 * @param sourceType 来源类型	 * @param sourceId 来源Id	 * @param lastModifyTime 	 * @param operationType 记录操作描述,CUD	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RedpointContentSource(BigInteger id,BigInteger tRedpointContentFId,Integer sourceType,BigInteger sourceId,String lastModifyTime,String operationType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRedpointContentFId = tRedpointContentFId;		this.sourceType = sourceType;		this.sourceId = sourceId;		this.lastModifyTime = lastModifyTime;		this.operationType = operationType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRedpointContentFId=").append(tRedpointContentFId).append(";");		sbf.append("sourceType=").append(sourceType).append(";");		sbf.append("sourceId=").append(sourceId).append(";");		sbf.append("lastModifyTime=").append(lastModifyTime).append(";");		sbf.append("operationType=").append(operationType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRedpointContentFId() {		return tRedpointContentFId;	}	public void settRedpointContentFId(BigInteger tRedpointContentFId) {		this.tRedpointContentFId = tRedpointContentFId;	}	public Integer getSourceType() {		return sourceType;	}	public void setSourceType(Integer sourceType) {		this.sourceType = sourceType;	}	public BigInteger getSourceId() {		return sourceId;	}	public void setSourceId(BigInteger sourceId) {		this.sourceId = sourceId;	}	public String getLastModifyTime() {		return lastModifyTime;	}	public void setLastModifyTime(String lastModifyTime) {		this.lastModifyTime = lastModifyTime;	}	public String getOperationType() {		return operationType;	}	public void setOperationType(String operationType) {		this.operationType = operationType;	}
	
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
		RedpointContentSource other = (RedpointContentSource) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
