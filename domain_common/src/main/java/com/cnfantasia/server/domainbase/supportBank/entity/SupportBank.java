package com.cnfantasia.server.domainbase.supportBank.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(支持的转账银行) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class SupportBank implements Serializable{
/* */

/* 
public class SupportBank extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 银行全称 */	private String bankname;	/** 银行简称 */	private String simpleName;
	public SupportBank(){
	}
	public SupportBank(SupportBank arg){
		this.id = arg.getId();		this.bankname = arg.getBankname();		this.simpleName = arg.getSimpleName();
	}
	/**	 * 	 * @param id 	 * @param bankname 银行全称	 * @param simpleName 银行简称	 */
	public SupportBank(BigInteger id,String bankname,String simpleName){
		this.id = id;		this.bankname = bankname;		this.simpleName = simpleName;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("bankname=").append(bankname).append(";");		sbf.append("simpleName=").append(simpleName).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getBankname() {		return bankname;	}	public void setBankname(String bankname) {		this.bankname = bankname;	}	public String getSimpleName() {		return simpleName;	}	public void setSimpleName(String simpleName) {		this.simpleName = simpleName;	}
	
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
		SupportBank other = (SupportBank) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
