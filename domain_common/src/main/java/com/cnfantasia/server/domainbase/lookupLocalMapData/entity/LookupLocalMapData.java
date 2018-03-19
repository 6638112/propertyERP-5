package com.cnfantasia.server.domainbase.lookupLocalMapData.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(本地地址信息数据) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class LookupLocalMapData implements Serializable{
/* */

/* 
public class LookupLocalMapData extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;
	public LookupLocalMapData(){
	}
	public LookupLocalMapData(LookupLocalMapData arg){
		this.id = arg.getId();
	}
	/**	 * 	 * @param id 	 */
	public LookupLocalMapData(BigInteger id){
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}
	
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
		LookupLocalMapData other = (LookupLocalMapData) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
