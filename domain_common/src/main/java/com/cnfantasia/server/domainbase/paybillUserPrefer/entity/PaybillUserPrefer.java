package com.cnfantasia.server.domainbase.paybillUserPrefer.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(账单优惠用户关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class PaybillUserPrefer implements Serializable{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账单id */	private BigInteger paybillId;	/** 用户id */	private BigInteger userId;	/** 优惠金额 */	private Long prefer;	/** 优惠名称（冗余） */	private String preferName;	/** 是否获取优惠金额（1获取，0未获取） */	private Integer isGetPrefer;
	private String sys0AddTime;
	public PaybillUserPrefer(){
	}
	public PaybillUserPrefer(PaybillUserPrefer arg){
		this.id = arg.getId();		this.paybillId = arg.getPaybillId();		this.userId = arg.getUserId();		this.prefer = arg.getPrefer();		this.preferName = arg.getPreferName();		this.isGetPrefer = arg.getIsGetPrefer();		this.sys0AddTime = arg.getSys0AddTime();
	}
	/**	 * 	 * @param id 	 * @param paybillId 账单id	 * @param userId 用户id	 * @param prefer 优惠金额	 * @param preferName 优惠名称（冗余）	 * @param isGetPrefer 是否获取优惠金额（1获取，0未获取）	 * @param sys0AddTime 新增时间	 */
	public PaybillUserPrefer(BigInteger id,BigInteger paybillId,BigInteger userId,Long prefer,String preferName,Integer isGetPrefer,String sys0AddTime){
		this.id = id;		this.paybillId = paybillId;		this.userId = userId;		this.prefer = prefer;		this.preferName = preferName;		this.isGetPrefer = isGetPrefer;		this.sys0AddTime = sys0AddTime;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("paybillId=").append(paybillId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("prefer=").append(prefer).append(";");		sbf.append("preferName=").append(preferName).append(";");		sbf.append("isGetPrefer=").append(isGetPrefer).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPaybillId() {		return paybillId;	}	public void setPaybillId(BigInteger paybillId) {		this.paybillId = paybillId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public Long getPrefer() {		return prefer;	}	public void setPrefer(Long prefer) {		this.prefer = prefer;	}	public String getPreferName() {		return preferName;	}	public void setPreferName(String preferName) {		this.preferName = preferName;	}	public Integer getIsGetPrefer() {		return isGetPrefer;	}	public void setIsGetPrefer(Integer isGetPrefer) {		this.isGetPrefer = isGetPrefer;	}
	
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
		PaybillUserPrefer other = (PaybillUserPrefer) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	public String getSys0AddTime() {
		return sys0AddTime;
	}
	public void setSys0AddTime(String sys0AddTime) {
		this.sys0AddTime = sys0AddTime;
	}
	
}
