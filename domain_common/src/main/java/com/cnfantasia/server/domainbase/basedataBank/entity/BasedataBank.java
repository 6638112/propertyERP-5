package com.cnfantasia.server.domainbase.basedataBank.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(银行基础资料) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class BasedataBank implements Serializable{
/* */

/* 
public class BasedataBank extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 银行代号 */	private String number;	/** 银行名称 */	private String name;	/** 描述 */	private String description;
	public BasedataBank(){
	}
	public BasedataBank(BasedataBank arg){
		this.id = arg.getId();		this.number = arg.getNumber();		this.name = arg.getName();		this.description = arg.getDescription();
	}
	/**	 * 	 * @param id 	 * @param number 银行代号	 * @param name 银行名称	 * @param description 描述	 */
	public BasedataBank(BigInteger id,String number,String name,String description){
		this.id = id;		this.number = number;		this.name = name;		this.description = description;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("number=").append(number).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("description=").append(description).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getNumber() {		return number;	}	public void setNumber(String number) {		this.number = number;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}
	
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
		BasedataBank other = (BasedataBank) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
