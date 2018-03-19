package com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(运单备注) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class EbuyDeliveryOrderComment implements Serializable{
/* */

/* 
public class EbuyDeliveryOrderComment extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 运单Id */	private BigInteger tEbuyOrderFId;	/** 备注 */	private String comment;	/** 新增时间 */	private String addTime;
	public EbuyDeliveryOrderComment(){
	}
	public EbuyDeliveryOrderComment(EbuyDeliveryOrderComment arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.comment = arg.getComment();		this.addTime = arg.getAddTime();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 运单Id	 * @param comment 备注	 * @param addTime 新增时间	 */
	public EbuyDeliveryOrderComment(BigInteger id,BigInteger tEbuyOrderFId,String comment,String addTime){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.comment = comment;		this.addTime = addTime;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("comment=").append(comment).append(";");		sbf.append("addTime=").append(addTime).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public String getComment() {		return comment;	}	public void setComment(String comment) {		this.comment = comment;	}	public String getAddTime() {		return addTime;	}	public void setAddTime(String addTime) {		this.addTime = addTime;	}
	
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
		EbuyDeliveryOrderComment other = (EbuyDeliveryOrderComment) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
