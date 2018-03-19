package com.cnfantasia.server.domainbase.commonLock.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(公用的锁表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class CommonLock implements Serializable{
/* */

/* 
public class CommonLock extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 需要锁的表名 */	private String tableName;
	public CommonLock(){
	}
	public CommonLock(CommonLock arg){
		this.id = arg.getId();		this.tableName = arg.getTableName();
	}
	/**	 * 	 * @param id 	 * @param tableName 需要锁的表名	 */
	public CommonLock(BigInteger id,String tableName){
		this.id = id;		this.tableName = tableName;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tableName=").append(tableName).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTableName() {		return tableName;	}	public void setTableName(String tableName) {		this.tableName = tableName;	}
	
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
		CommonLock other = (CommonLock) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
