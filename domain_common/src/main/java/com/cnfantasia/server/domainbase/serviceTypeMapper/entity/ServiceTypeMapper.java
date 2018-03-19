package com.cnfantasia.server.domainbase.serviceTypeMapper.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(与第三方供应商类型对应关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ServiceTypeMapper implements Serializable{
*/


public class ServiceTypeMapper extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 服务端的维修类型id */	private String serviceTypeId;	/** 我方类型 */	private BigInteger tDredgeTypeFId;	/**  */	private BigInteger tDredgeType2ndFId;	/** 第三方类型id */	private BigInteger tDredge3rdSplFId;
	public ServiceTypeMapper(){
	}
	public ServiceTypeMapper(ServiceTypeMapper arg){
		this.id = arg.getId();		this.serviceTypeId = arg.getServiceTypeId();		this.tDredgeTypeFId = arg.gettDredgeTypeFId();		this.tDredgeType2ndFId = arg.gettDredgeType2ndFId();		this.tDredge3rdSplFId = arg.gettDredge3rdSplFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param serviceTypeId 服务端的维修类型id	 * @param tDredgeTypeFId 我方类型	 * @param tDredgeType2ndFId 	 * @param tDredge3rdSplFId 第三方类型id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public ServiceTypeMapper(BigInteger id,String serviceTypeId,BigInteger tDredgeTypeFId,BigInteger tDredgeType2ndFId,BigInteger tDredge3rdSplFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.serviceTypeId = serviceTypeId;		this.tDredgeTypeFId = tDredgeTypeFId;		this.tDredgeType2ndFId = tDredgeType2ndFId;		this.tDredge3rdSplFId = tDredge3rdSplFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("serviceTypeId=").append(serviceTypeId).append(";");		sbf.append("tDredgeTypeFId=").append(tDredgeTypeFId).append(";");		sbf.append("tDredgeType2ndFId=").append(tDredgeType2ndFId).append(";");		sbf.append("tDredge3rdSplFId=").append(tDredge3rdSplFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getServiceTypeId() {		return serviceTypeId;	}	public void setServiceTypeId(String serviceTypeId) {		this.serviceTypeId = serviceTypeId;	}	public BigInteger gettDredgeTypeFId() {		return tDredgeTypeFId;	}	public void settDredgeTypeFId(BigInteger tDredgeTypeFId) {		this.tDredgeTypeFId = tDredgeTypeFId;	}	public BigInteger gettDredgeType2ndFId() {		return tDredgeType2ndFId;	}	public void settDredgeType2ndFId(BigInteger tDredgeType2ndFId) {		this.tDredgeType2ndFId = tDredgeType2ndFId;	}	public BigInteger gettDredge3rdSplFId() {		return tDredge3rdSplFId;	}	public void settDredge3rdSplFId(BigInteger tDredge3rdSplFId) {		this.tDredge3rdSplFId = tDredge3rdSplFId;	}
	
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
		ServiceTypeMapper other = (ServiceTypeMapper) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
