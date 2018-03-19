package com.cnfantasia.server.domainbase.addressProvince.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(省) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AddressProvince implements Serializable{
*/


public class AddressProvince extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private String name;	/** 国家标准代码 */	private String countryCode;	/** 国家标准代码,包含后面的0 */	private String countryCodeZero;	/**  */	private BigInteger tAddressCountryFId;
	public AddressProvince(){
	}
	public AddressProvince(AddressProvince arg){
		this.id = arg.getId();		this.name = arg.getName();		this.countryCode = arg.getCountryCode();		this.countryCodeZero = arg.getCountryCodeZero();		this.tAddressCountryFId = arg.gettAddressCountryFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 	 * @param countryCode 国家标准代码	 * @param countryCodeZero 国家标准代码,包含后面的0	 * @param tAddressCountryFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public AddressProvince(BigInteger id,String name,String countryCode,String countryCodeZero,BigInteger tAddressCountryFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.countryCode = countryCode;		this.countryCodeZero = countryCodeZero;		this.tAddressCountryFId = tAddressCountryFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("countryCode=").append(countryCode).append(";");		sbf.append("countryCodeZero=").append(countryCodeZero).append(";");		sbf.append("tAddressCountryFId=").append(tAddressCountryFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getCountryCode() {		return countryCode;	}	public void setCountryCode(String countryCode) {		this.countryCode = countryCode;	}	public String getCountryCodeZero() {		return countryCodeZero;	}	public void setCountryCodeZero(String countryCodeZero) {		this.countryCodeZero = countryCodeZero;	}	public BigInteger gettAddressCountryFId() {		return tAddressCountryFId;	}	public void settAddressCountryFId(BigInteger tAddressCountryFId) {		this.tAddressCountryFId = tAddressCountryFId;	}
	
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
		AddressProvince other = (AddressProvince) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
