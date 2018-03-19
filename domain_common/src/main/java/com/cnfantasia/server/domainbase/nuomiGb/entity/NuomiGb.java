package com.cnfantasia.server.domainbase.nuomiGb.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(百度糯米对接小区信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class NuomiGb implements Serializable{
*/


public class NuomiGb extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 对应的解放区小区id */	private BigInteger tGroupBuildingFId;	/** 城市名 */	private String cityName;	/** 行政区名 */	private String blockName;	/** 小区名 */	private String gbName;
	public NuomiGb(){
	}
	public NuomiGb(NuomiGb arg){
		this.id = arg.getId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.cityName = arg.getCityName();		this.blockName = arg.getBlockName();		this.gbName = arg.getGbName();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGroupBuildingFId 对应的解放区小区id	 * @param cityName 城市名	 * @param blockName 行政区名	 * @param gbName 小区名	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public NuomiGb(BigInteger id,BigInteger tGroupBuildingFId,String cityName,String blockName,String gbName,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGroupBuildingFId = tGroupBuildingFId;		this.cityName = cityName;		this.blockName = blockName;		this.gbName = gbName;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("cityName=").append(cityName).append(";");		sbf.append("blockName=").append(blockName).append(";");		sbf.append("gbName=").append(gbName).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public String getCityName() {		return cityName;	}	public void setCityName(String cityName) {		this.cityName = cityName;	}	public String getBlockName() {		return blockName;	}	public void setBlockName(String blockName) {		this.blockName = blockName;	}	public String getGbName() {		return gbName;	}	public void setGbName(String gbName) {		this.gbName = gbName;	}
	
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
		NuomiGb other = (NuomiGb) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
