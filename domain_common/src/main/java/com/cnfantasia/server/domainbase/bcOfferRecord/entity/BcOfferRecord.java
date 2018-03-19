package com.cnfantasia.server.domainbase.bcOfferRecord.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(发盘记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class BcOfferRecord implements Serializable{
/* */

/* 
public class BcOfferRecord extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账单id */	private BigInteger paybillId;	/** 账单的唯一短编码 */	private String shortCode;	/** 发盘信息 */	private String offerContent;	/** 发盘时间 */	private String offerTime;	/**  */	private BigInteger tBcGroupBuildingCycleFId;
	public BcOfferRecord(){
	}
	public BcOfferRecord(BcOfferRecord arg){
		this.id = arg.getId();		this.paybillId = arg.getPaybillId();		this.shortCode = arg.getShortCode();		this.offerContent = arg.getOfferContent();		this.offerTime = arg.getOfferTime();		this.tBcGroupBuildingCycleFId = arg.gettBcGroupBuildingCycleFId();
	}
	/**	 * 	 * @param id 	 * @param paybillId 账单id	 * @param shortCode 账单的唯一短编码	 * @param offerContent 发盘信息	 * @param offerTime 发盘时间	 * @param tBcGroupBuildingCycleFId 	 */
	public BcOfferRecord(BigInteger id,BigInteger paybillId,String shortCode,String offerContent,String offerTime,BigInteger tBcGroupBuildingCycleFId){
		this.id = id;		this.paybillId = paybillId;		this.shortCode = shortCode;		this.offerContent = offerContent;		this.offerTime = offerTime;		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("paybillId=").append(paybillId).append(";");		sbf.append("shortCode=").append(shortCode).append(";");		sbf.append("offerContent=").append(offerContent).append(";");		sbf.append("offerTime=").append(offerTime).append(";");		sbf.append("tBcGroupBuildingCycleFId=").append(tBcGroupBuildingCycleFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPaybillId() {		return paybillId;	}	public void setPaybillId(BigInteger paybillId) {		this.paybillId = paybillId;	}	public String getShortCode() {		return shortCode;	}	public void setShortCode(String shortCode) {		this.shortCode = shortCode;	}	public String getOfferContent() {		return offerContent;	}	public void setOfferContent(String offerContent) {		this.offerContent = offerContent;	}	public String getOfferTime() {		return offerTime;	}	public void setOfferTime(String offerTime) {		this.offerTime = offerTime;	}	public BigInteger gettBcGroupBuildingCycleFId() {		return tBcGroupBuildingCycleFId;	}	public void settBcGroupBuildingCycleFId(BigInteger tBcGroupBuildingCycleFId) {		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;	}
	
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
		BcOfferRecord other = (BcOfferRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
