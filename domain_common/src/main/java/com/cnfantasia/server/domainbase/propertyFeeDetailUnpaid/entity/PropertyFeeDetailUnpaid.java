package com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(账单欠费信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class PropertyFeeDetailUnpaid implements Serializable{
/* */

/* 
public class PropertyFeeDetailUnpaid extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区id */	private BigInteger tGbId;	/** 房间id */	private BigInteger tRealRoomId;	/** 账单id(上月欠费账单) */	private BigInteger tUnpaidPayBillId;	/** 最新账单id（没欠费的） */	private BigInteger tPayBillId;
	public PropertyFeeDetailUnpaid(){
	}
	public PropertyFeeDetailUnpaid(PropertyFeeDetailUnpaid arg){
		this.id = arg.getId();		this.tGbId = arg.gettGbId();		this.tRealRoomId = arg.gettRealRoomId();		this.tUnpaidPayBillId = arg.gettUnpaidPayBillId();		this.tPayBillId = arg.gettPayBillId();
	}
	/**	 * 	 * @param id 	 * @param tGbId 小区id	 * @param tRealRoomId 房间id	 * @param tUnpaidPayBillId 账单id(上月欠费账单)	 * @param tPayBillId 最新账单id（没欠费的）	 */
	public PropertyFeeDetailUnpaid(BigInteger id,BigInteger tGbId,BigInteger tRealRoomId,BigInteger tUnpaidPayBillId,BigInteger tPayBillId){
		this.id = id;		this.tGbId = tGbId;		this.tRealRoomId = tRealRoomId;		this.tUnpaidPayBillId = tUnpaidPayBillId;		this.tPayBillId = tPayBillId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGbId=").append(tGbId).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("tUnpaidPayBillId=").append(tUnpaidPayBillId).append(";");		sbf.append("tPayBillId=").append(tPayBillId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGbId() {		return tGbId;	}	public void settGbId(BigInteger tGbId) {		this.tGbId = tGbId;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public BigInteger gettUnpaidPayBillId() {		return tUnpaidPayBillId;	}	public void settUnpaidPayBillId(BigInteger tUnpaidPayBillId) {		this.tUnpaidPayBillId = tUnpaidPayBillId;	}	public BigInteger gettPayBillId() {		return tPayBillId;	}	public void settPayBillId(BigInteger tPayBillId) {		this.tPayBillId = tPayBillId;	}
	
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
		PropertyFeeDetailUnpaid other = (PropertyFeeDetailUnpaid) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
