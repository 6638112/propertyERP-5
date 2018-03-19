package com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(菜谱地址关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenCookbookAddressRelation implements Serializable{
*/


public class KitchenCookbookAddressRelation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属国家Id */	private BigInteger countryId;	/** 省Id */	private BigInteger provinceId;	/** 市Id */	private BigInteger cityId;	/** 行政区Id */	private BigInteger blockId;	/** 小区Id */	private BigInteger groupbuildId;	/**  */	private BigInteger tKitchenCookbookFId;
	public KitchenCookbookAddressRelation(){
	}
	public KitchenCookbookAddressRelation(KitchenCookbookAddressRelation arg){
		this.id = arg.getId();		this.countryId = arg.getCountryId();		this.provinceId = arg.getProvinceId();		this.cityId = arg.getCityId();		this.blockId = arg.getBlockId();		this.groupbuildId = arg.getGroupbuildId();		this.tKitchenCookbookFId = arg.gettKitchenCookbookFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param countryId 所属国家Id	 * @param provinceId 省Id	 * @param cityId 市Id	 * @param blockId 行政区Id	 * @param groupbuildId 小区Id	 * @param tKitchenCookbookFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public KitchenCookbookAddressRelation(BigInteger id,BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger groupbuildId,BigInteger tKitchenCookbookFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.countryId = countryId;		this.provinceId = provinceId;		this.cityId = cityId;		this.blockId = blockId;		this.groupbuildId = groupbuildId;		this.tKitchenCookbookFId = tKitchenCookbookFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("countryId=").append(countryId).append(";");		sbf.append("provinceId=").append(provinceId).append(";");		sbf.append("cityId=").append(cityId).append(";");		sbf.append("blockId=").append(blockId).append(";");		sbf.append("groupbuildId=").append(groupbuildId).append(";");		sbf.append("tKitchenCookbookFId=").append(tKitchenCookbookFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getCountryId() {		return countryId;	}	public void setCountryId(BigInteger countryId) {		this.countryId = countryId;	}	public BigInteger getProvinceId() {		return provinceId;	}	public void setProvinceId(BigInteger provinceId) {		this.provinceId = provinceId;	}	public BigInteger getCityId() {		return cityId;	}	public void setCityId(BigInteger cityId) {		this.cityId = cityId;	}	public BigInteger getBlockId() {		return blockId;	}	public void setBlockId(BigInteger blockId) {		this.blockId = blockId;	}	public BigInteger getGroupbuildId() {		return groupbuildId;	}	public void setGroupbuildId(BigInteger groupbuildId) {		this.groupbuildId = groupbuildId;	}	public BigInteger gettKitchenCookbookFId() {		return tKitchenCookbookFId;	}	public void settKitchenCookbookFId(BigInteger tKitchenCookbookFId) {		this.tKitchenCookbookFId = tKitchenCookbookFId;	}
	
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
		KitchenCookbookAddressRelation other = (KitchenCookbookAddressRelation) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
