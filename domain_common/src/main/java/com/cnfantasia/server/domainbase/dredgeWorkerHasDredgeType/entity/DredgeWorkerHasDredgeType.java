package com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(疏通师傅支持的疏通类型) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class DredgeWorkerHasDredgeType implements Serializable{
/* */

/* 
public class DredgeWorkerHasDredgeType extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tDredgeTypeFId;	/**  */	private BigInteger tUserFId;
	public DredgeWorkerHasDredgeType(){
	}
	/**	 * 	 * @param id 	 * @param tDredgeTypeFId 	 * @param tUserFId 	 */
	public DredgeWorkerHasDredgeType(BigInteger id,BigInteger tDredgeTypeFId,BigInteger tUserFId){
		this.id = id;		this.tDredgeTypeFId = tDredgeTypeFId;		this.tUserFId = tUserFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeTypeFId=").append(tDredgeTypeFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeTypeFId() {		return tDredgeTypeFId;	}	public void settDredgeTypeFId(BigInteger tDredgeTypeFId) {		this.tDredgeTypeFId = tDredgeTypeFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}
	
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
		DredgeWorkerHasDredgeType other = (DredgeWorkerHasDredgeType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
