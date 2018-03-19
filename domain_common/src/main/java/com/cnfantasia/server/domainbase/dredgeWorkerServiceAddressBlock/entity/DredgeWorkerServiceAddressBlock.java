package com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(疏通师傅服务行政区) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class DredgeWorkerServiceAddressBlock implements Serializable{
/* */

/* 
public class DredgeWorkerServiceAddressBlock extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tAddressBlockFId;	/**  */	private BigInteger tUserFId;
	public DredgeWorkerServiceAddressBlock(){
	}
	/**	 * 	 * @param id 	 * @param tAddressBlockFId 	 * @param tUserFId 	 */
	public DredgeWorkerServiceAddressBlock(BigInteger id,BigInteger tAddressBlockFId,BigInteger tUserFId){
		this.id = id;		this.tAddressBlockFId = tAddressBlockFId;		this.tUserFId = tUserFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tAddressBlockFId=").append(tAddressBlockFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettAddressBlockFId() {		return tAddressBlockFId;	}	public void settAddressBlockFId(BigInteger tAddressBlockFId) {		this.tAddressBlockFId = tAddressBlockFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}
	
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
		DredgeWorkerServiceAddressBlock other = (DredgeWorkerServiceAddressBlock) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
