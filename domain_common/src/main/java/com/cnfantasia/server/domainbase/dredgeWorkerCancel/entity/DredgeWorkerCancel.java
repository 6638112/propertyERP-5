package com.cnfantasia.server.domainbase.dredgeWorkerCancel.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(疏通师傅取消订单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class DredgeWorkerCancel implements Serializable{
/* */

/* 
public class DredgeWorkerCancel extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 取消原因 */	private String tCancelReason;	/**  */	private BigInteger tUserFId;	/**  */	private BigInteger tDredgeBillFId;
	public DredgeWorkerCancel(){
	}
	/**	 * 	 * @param id 	 * @param tCancelReason 取消原因	 * @param tUserFId 	 * @param tDredgeBillFId 	 */
	public DredgeWorkerCancel(BigInteger id,String tCancelReason,BigInteger tUserFId,BigInteger tDredgeBillFId){
		this.id = id;		this.tCancelReason = tCancelReason;		this.tUserFId = tUserFId;		this.tDredgeBillFId = tDredgeBillFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tCancelReason=").append(tCancelReason).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String gettCancelReason() {		return tCancelReason;	}	public void settCancelReason(String tCancelReason) {		this.tCancelReason = tCancelReason;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}
	
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
		DredgeWorkerCancel other = (DredgeWorkerCancel) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
