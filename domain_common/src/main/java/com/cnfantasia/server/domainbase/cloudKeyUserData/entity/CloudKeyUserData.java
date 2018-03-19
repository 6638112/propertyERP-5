package com.cnfantasia.server.domainbase.cloudKeyUserData.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;

/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(业主门禁认证（可配置）信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class CloudKeyUserData implements Serializable{
/* */

/* 
public class CloudKeyUserData extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tCloudKeyUserAuditFId;	/** 键 */	private String key;	/** 值 */	private String value;
	private String label;
	private String inputType;
	public CloudKeyUserData(){
	}
	public CloudKeyUserData(CloudKeyUserData arg){
		this.id = arg.getId();		this.tCloudKeyUserAuditFId = arg.gettCloudKeyUserAuditFId();		this.key = arg.getKey();		this.value = arg.getValue();		this.label = arg.getLabel();
		this.inputType=arg.getInputType();
	}
	/**	 * 	 * @param id 	 * @param tCloudKeyUserAuditFId 	 * @param key 键	 * @param value 值	 */
	public CloudKeyUserData(BigInteger id,BigInteger tCloudKeyUserAuditFId,String key,String value, String label, String inputType){
		this.id = id;		this.tCloudKeyUserAuditFId = tCloudKeyUserAuditFId;		this.key = key;		this.value = value;
		this.label = label;
		this.inputType = inputType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tCloudKeyUserAuditFId=").append(tCloudKeyUserAuditFId).append(";");		sbf.append("key=").append(key).append(";");		sbf.append("value=").append(value).append(";");
		sbf.append("label=").append(label).append(";");
		sbf.append("inputType=").append(inputType).append(";");		return sbf.toString();
	}
	
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettCloudKeyUserAuditFId() {		return tCloudKeyUserAuditFId;	}	public void settCloudKeyUserAuditFId(BigInteger tCloudKeyUserAuditFId) {		this.tCloudKeyUserAuditFId = tCloudKeyUserAuditFId;	}	public String getKey() {		return key;	}	public void setKey(String key) {		this.key = key;	}	public String getValue() {		return value;	}	public void setValue(String value) {		this.value = value;	}
	
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
		CloudKeyUserData other = (CloudKeyUserData) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
