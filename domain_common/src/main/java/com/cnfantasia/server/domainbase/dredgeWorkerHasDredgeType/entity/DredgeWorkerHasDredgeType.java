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
	/**  */
	public DredgeWorkerHasDredgeType(){
	}
	/**
	public DredgeWorkerHasDredgeType(BigInteger id,BigInteger tDredgeTypeFId,BigInteger tUserFId){
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
	}
	
	public BigInteger getId() {
	
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
		if (id == null) {
		return true;
	}
	
}