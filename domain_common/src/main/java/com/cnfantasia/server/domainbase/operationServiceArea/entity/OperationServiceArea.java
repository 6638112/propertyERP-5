package com.cnfantasia.server.domainbase.operationServiceArea.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(服务范围的运营数据表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OperationServiceArea implements Serializable{
*/


public class OperationServiceArea extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 国家Id */	private BigInteger tAddressCountryFId;	/** 省Id */	private BigInteger tAddressProvinceFId;	/** 市Id */	private BigInteger tAddressCityFId;	/** 片区Id */	private BigInteger tAddressBlockSelfFId;	/** 区Id */	private BigInteger tAddressBlockFId;	/** 小区Id */	private BigInteger tGroupBuildingFId;	/** 数据级别 */	private Integer level;	/**  */	private String addressUnique;
	public OperationServiceArea(){
	}
	public OperationServiceArea(OperationServiceArea arg){
		this.id = arg.getId();		this.tAddressCountryFId = arg.gettAddressCountryFId();		this.tAddressProvinceFId = arg.gettAddressProvinceFId();		this.tAddressCityFId = arg.gettAddressCityFId();		this.tAddressBlockSelfFId = arg.gettAddressBlockSelfFId();		this.tAddressBlockFId = arg.gettAddressBlockFId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.level = arg.getLevel();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.addressUnique = arg.getAddressUnique();
	}
	/**	 * 	 * @param id 	 * @param tAddressCountryFId 国家Id	 * @param tAddressProvinceFId 省Id	 * @param tAddressCityFId 市Id	 * @param tAddressBlockSelfFId 片区Id	 * @param tAddressBlockFId 区Id	 * @param tGroupBuildingFId 小区Id	 * @param level 数据级别	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param addressUnique 	 */
	public OperationServiceArea(BigInteger id,BigInteger tAddressCountryFId,BigInteger tAddressProvinceFId,BigInteger tAddressCityFId,BigInteger tAddressBlockSelfFId,BigInteger tAddressBlockFId,BigInteger tGroupBuildingFId,Integer level,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String addressUnique){
		this.id = id;		this.tAddressCountryFId = tAddressCountryFId;		this.tAddressProvinceFId = tAddressProvinceFId;		this.tAddressCityFId = tAddressCityFId;		this.tAddressBlockSelfFId = tAddressBlockSelfFId;		this.tAddressBlockFId = tAddressBlockFId;		this.tGroupBuildingFId = tGroupBuildingFId;		this.level = level;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.addressUnique = addressUnique;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tAddressCountryFId=").append(tAddressCountryFId).append(";");		sbf.append("tAddressProvinceFId=").append(tAddressProvinceFId).append(";");		sbf.append("tAddressCityFId=").append(tAddressCityFId).append(";");		sbf.append("tAddressBlockSelfFId=").append(tAddressBlockSelfFId).append(";");		sbf.append("tAddressBlockFId=").append(tAddressBlockFId).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("level=").append(level).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("addressUnique=").append(addressUnique).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettAddressCountryFId() {		return tAddressCountryFId;	}	public void settAddressCountryFId(BigInteger tAddressCountryFId) {		this.tAddressCountryFId = tAddressCountryFId;	}	public BigInteger gettAddressProvinceFId() {		return tAddressProvinceFId;	}	public void settAddressProvinceFId(BigInteger tAddressProvinceFId) {		this.tAddressProvinceFId = tAddressProvinceFId;	}	public BigInteger gettAddressCityFId() {		return tAddressCityFId;	}	public void settAddressCityFId(BigInteger tAddressCityFId) {		this.tAddressCityFId = tAddressCityFId;	}	public BigInteger gettAddressBlockSelfFId() {		return tAddressBlockSelfFId;	}	public void settAddressBlockSelfFId(BigInteger tAddressBlockSelfFId) {		this.tAddressBlockSelfFId = tAddressBlockSelfFId;	}	public BigInteger gettAddressBlockFId() {		return tAddressBlockFId;	}	public void settAddressBlockFId(BigInteger tAddressBlockFId) {		this.tAddressBlockFId = tAddressBlockFId;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public Integer getLevel() {		return level;	}	public void setLevel(Integer level) {		this.level = level;	}	public String getAddressUnique() {		return addressUnique;	}	public void setAddressUnique(String addressUnique) {		this.addressUnique = addressUnique;	}
	
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
		OperationServiceArea other = (OperationServiceArea) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
