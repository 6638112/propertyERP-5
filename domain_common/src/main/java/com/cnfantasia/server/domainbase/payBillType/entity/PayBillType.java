package com.cnfantasia.server.domainbase.payBillType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(账单类型基础信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayBillType implements Serializable{
*/


public class PayBillType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 类别名称 */	private String name;	/** 类别图标 */	private String icon;	/** 是否为物业费 */	private Integer isPropFee;	/** 最近更新时间 */	private String lastUpdTime;	/** 小区Id */	private BigInteger gbId;	/** 缴费时间方式 */	private Integer paytimeType;	/** 有效状态 */	private Integer activeStatus;	/** 随机立减开启状态 */	private Integer preferStatus;
	public PayBillType(){
	}
	public PayBillType(PayBillType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.icon = arg.getIcon();		this.isPropFee = arg.getIsPropFee();		this.lastUpdTime = arg.getLastUpdTime();		this.gbId = arg.getGbId();		this.paytimeType = arg.getPaytimeType();		this.activeStatus = arg.getActiveStatus();		this.preferStatus = arg.getPreferStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 类别名称	 * @param icon 类别图标	 * @param isPropFee 是否为物业费	 * @param lastUpdTime 最近更新时间	 * @param gbId 小区Id	 * @param paytimeType 缴费时间方式	 * @param activeStatus 有效状态	 * @param preferStatus 随机立减开启状态	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PayBillType(BigInteger id,String name,String icon,Integer isPropFee,String lastUpdTime,BigInteger gbId,Integer paytimeType,Integer activeStatus,Integer preferStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.icon = icon;		this.isPropFee = isPropFee;		this.lastUpdTime = lastUpdTime;		this.gbId = gbId;		this.paytimeType = paytimeType;		this.activeStatus = activeStatus;		this.preferStatus = preferStatus;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("icon=").append(icon).append(";");		sbf.append("isPropFee=").append(isPropFee).append(";");		sbf.append("lastUpdTime=").append(lastUpdTime).append(";");		sbf.append("gbId=").append(gbId).append(";");		sbf.append("paytimeType=").append(paytimeType).append(";");		sbf.append("activeStatus=").append(activeStatus).append(";");		sbf.append("preferStatus=").append(preferStatus).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getIcon() {		return icon;	}	public void setIcon(String icon) {		this.icon = icon;	}	public Integer getIsPropFee() {		return isPropFee;	}	public void setIsPropFee(Integer isPropFee) {		this.isPropFee = isPropFee;	}	public String getLastUpdTime() {		return lastUpdTime;	}	public void setLastUpdTime(String lastUpdTime) {		this.lastUpdTime = lastUpdTime;	}	public BigInteger getGbId() {		return gbId;	}	public void setGbId(BigInteger gbId) {		this.gbId = gbId;	}	public Integer getPaytimeType() {		return paytimeType;	}	public void setPaytimeType(Integer paytimeType) {		this.paytimeType = paytimeType;	}	public Integer getActiveStatus() {		return activeStatus;	}	public void setActiveStatus(Integer activeStatus) {		this.activeStatus = activeStatus;	}	public Integer getPreferStatus() {		return preferStatus;	}	public void setPreferStatus(Integer preferStatus) {		this.preferStatus = preferStatus;	}
	
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
		PayBillType other = (PayBillType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
