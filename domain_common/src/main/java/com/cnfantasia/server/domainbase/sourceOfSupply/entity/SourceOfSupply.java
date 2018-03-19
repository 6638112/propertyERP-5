package com.cnfantasia.server.domainbase.sourceOfSupply.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(供应商的货源地) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class SourceOfSupply implements Serializable{
*/


public class SourceOfSupply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 货源名称 */	private String name;	/** 货源地描述 */	private String desc;	/** 货源介绍URL地址 */	private String introduceUrl;	/**  */	private BigInteger tEbuySupplyMerchantFId;
	public SourceOfSupply(){
	}
	public SourceOfSupply(SourceOfSupply arg){
		this.id = arg.getId();		this.name = arg.getName();		this.desc = arg.getDesc();		this.introduceUrl = arg.getIntroduceUrl();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tEbuySupplyMerchantFId = arg.gettEbuySupplyMerchantFId();
	}
	/**	 * 	 * @param id 	 * @param name 货源名称	 * @param desc 货源地描述	 * @param introduceUrl 货源介绍URL地址	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tEbuySupplyMerchantFId 	 */
	public SourceOfSupply(BigInteger id,String name,String desc,String introduceUrl,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tEbuySupplyMerchantFId){
		this.id = id;		this.name = name;		this.desc = desc;		this.introduceUrl = introduceUrl;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("introduceUrl=").append(introduceUrl).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tEbuySupplyMerchantFId=").append(tEbuySupplyMerchantFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getIntroduceUrl() {		return introduceUrl;	}	public void setIntroduceUrl(String introduceUrl) {		this.introduceUrl = introduceUrl;	}	public BigInteger gettEbuySupplyMerchantFId() {		return tEbuySupplyMerchantFId;	}	public void settEbuySupplyMerchantFId(BigInteger tEbuySupplyMerchantFId) {		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;	}
	
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
		SourceOfSupply other = (SourceOfSupply) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
