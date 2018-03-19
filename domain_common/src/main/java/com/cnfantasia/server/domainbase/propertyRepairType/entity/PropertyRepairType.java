package com.cnfantasia.server.domainbase.propertyRepairType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业报修类型) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyRepairType implements Serializable{
*/


public class PropertyRepairType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/**  */	private BigInteger tPropertyManagementFId;	/**  */	private BigInteger tPropertyRepairTypeBaseFId;	/**  */	private BigInteger tDredgeTypeFId;
	public PropertyRepairType(){
	}
	public PropertyRepairType(PropertyRepairType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();		this.tPropertyRepairTypeBaseFId = arg.gettPropertyRepairTypeBaseFId();		this.tDredgeTypeFId = arg.gettDredgeTypeFId();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tPropertyManagementFId 	 * @param tPropertyRepairTypeBaseFId 	 * @param tDredgeTypeFId 	 */
	public PropertyRepairType(BigInteger id,String name,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tPropertyManagementFId,BigInteger tPropertyRepairTypeBaseFId,BigInteger tDredgeTypeFId){
		this.id = id;		this.name = name;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tPropertyManagementFId = tPropertyManagementFId;		this.tPropertyRepairTypeBaseFId = tPropertyRepairTypeBaseFId;		this.tDredgeTypeFId = tDredgeTypeFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		sbf.append("tPropertyRepairTypeBaseFId=").append(tPropertyRepairTypeBaseFId).append(";");		sbf.append("tDredgeTypeFId=").append(tDredgeTypeFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}	public BigInteger gettPropertyRepairTypeBaseFId() {		return tPropertyRepairTypeBaseFId;	}	public void settPropertyRepairTypeBaseFId(BigInteger tPropertyRepairTypeBaseFId) {		this.tPropertyRepairTypeBaseFId = tPropertyRepairTypeBaseFId;	}	public BigInteger gettDredgeTypeFId() {		return tDredgeTypeFId;	}	public void settDredgeTypeFId(BigInteger tDredgeTypeFId) {		this.tDredgeTypeFId = tDredgeTypeFId;	}
	
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
		PropertyRepairType other = (PropertyRepairType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
