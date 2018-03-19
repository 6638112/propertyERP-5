package com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业管理与后账号关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyManagementHasOmsUser implements Serializable{
*/


public class PropertyManagementHasOmsUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 管理处电话 */	private String tel;	/** 名称 */	private String name;	/** 门禁审核权限：{0 */	private Integer doorKeyStatus;	/**  */	private BigInteger tOmsUserFId;	/**  */	private BigInteger tPropertyManagementFId;
	public PropertyManagementHasOmsUser(){
	}
	public PropertyManagementHasOmsUser(PropertyManagementHasOmsUser arg){
		this.id = arg.getId();		this.tel = arg.getTel();		this.name = arg.getName();		this.doorKeyStatus = arg.getDoorKeyStatus();		this.tOmsUserFId = arg.gettOmsUserFId();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tel 管理处电话	 * @param name 名称	 * @param doorKeyStatus 门禁审核权限：{0	 * @param tOmsUserFId 	 * @param tPropertyManagementFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyManagementHasOmsUser(BigInteger id,String tel,String name,Integer doorKeyStatus,BigInteger tOmsUserFId,BigInteger tPropertyManagementFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tel = tel;		this.name = name;		this.doorKeyStatus = doorKeyStatus;		this.tOmsUserFId = tOmsUserFId;		this.tPropertyManagementFId = tPropertyManagementFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tel=").append(tel).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("doorKeyStatus=").append(doorKeyStatus).append(";");		sbf.append("tOmsUserFId=").append(tOmsUserFId).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTel() {		return tel;	}	public void setTel(String tel) {		this.tel = tel;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Integer getDoorKeyStatus() {		return doorKeyStatus;	}	public void setDoorKeyStatus(Integer doorKeyStatus) {		this.doorKeyStatus = doorKeyStatus;	}	public BigInteger gettOmsUserFId() {		return tOmsUserFId;	}	public void settOmsUserFId(BigInteger tOmsUserFId) {		this.tOmsUserFId = tOmsUserFId;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}
	
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
		PropertyManagementHasOmsUser other = (PropertyManagementHasOmsUser) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
