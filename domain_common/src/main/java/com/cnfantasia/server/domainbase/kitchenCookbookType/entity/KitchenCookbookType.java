package com.cnfantasia.server.domainbase.kitchenCookbookType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(厨房菜谱分类) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenCookbookType implements Serializable{
*/


public class KitchenCookbookType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 类别名称 */	private String name;	/** 类别图片地址 */	private String picUrl;	/** 适合的城市Id */	private BigInteger suitCityId;	/** 适合的时间 */	private String suitTime;	/** 适合的季节 */	private Integer suitSeason;	/**  */	private BigInteger tKitchenCookbookTopTypeFId;
	public KitchenCookbookType(){
	}
	public KitchenCookbookType(KitchenCookbookType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.picUrl = arg.getPicUrl();		this.suitCityId = arg.getSuitCityId();		this.suitTime = arg.getSuitTime();		this.suitSeason = arg.getSuitSeason();		this.tKitchenCookbookTopTypeFId = arg.gettKitchenCookbookTopTypeFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 类别名称	 * @param picUrl 类别图片地址	 * @param suitCityId 适合的城市Id	 * @param suitTime 适合的时间	 * @param suitSeason 适合的季节	 * @param tKitchenCookbookTopTypeFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public KitchenCookbookType(BigInteger id,String name,String picUrl,BigInteger suitCityId,String suitTime,Integer suitSeason,BigInteger tKitchenCookbookTopTypeFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.picUrl = picUrl;		this.suitCityId = suitCityId;		this.suitTime = suitTime;		this.suitSeason = suitSeason;		this.tKitchenCookbookTopTypeFId = tKitchenCookbookTopTypeFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("suitCityId=").append(suitCityId).append(";");		sbf.append("suitTime=").append(suitTime).append(";");		sbf.append("suitSeason=").append(suitSeason).append(";");		sbf.append("tKitchenCookbookTopTypeFId=").append(tKitchenCookbookTopTypeFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public BigInteger getSuitCityId() {		return suitCityId;	}	public void setSuitCityId(BigInteger suitCityId) {		this.suitCityId = suitCityId;	}	public String getSuitTime() {		return suitTime;	}	public void setSuitTime(String suitTime) {		this.suitTime = suitTime;	}	public Integer getSuitSeason() {		return suitSeason;	}	public void setSuitSeason(Integer suitSeason) {		this.suitSeason = suitSeason;	}	public BigInteger gettKitchenCookbookTopTypeFId() {		return tKitchenCookbookTopTypeFId;	}	public void settKitchenCookbookTopTypeFId(BigInteger tKitchenCookbookTopTypeFId) {		this.tKitchenCookbookTopTypeFId = tKitchenCookbookTopTypeFId;	}
	
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
		KitchenCookbookType other = (KitchenCookbookType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
