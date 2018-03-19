package com.cnfantasia.server.domainbase.ebuyRefundAudit.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商品审核不通过原因) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyRefundAudit implements Serializable{
*/


public class EbuyRefundAudit extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键,同t_ebuy_product.f_id */	private BigInteger id;	/** 退款原因 */	private String reason;	/** 退款比例 */	private Long refundRatio;
	public EbuyRefundAudit(){
	}
	public EbuyRefundAudit(EbuyRefundAudit arg){
		this.id = arg.getId();		this.reason = arg.getReason();		this.sys0DelState = arg.getSys0DelState();		this.refundRatio = arg.getRefundRatio();
	}
	/**	 * 	 * @param id 主键,同t_ebuy_product.f_id	 * @param reason 退款原因	 * @param sys0DelState 记录状态	 * @param refundRatio 退款比例	 */
	public EbuyRefundAudit(BigInteger id,String reason,Integer sys0DelState,Long refundRatio){
		this.id = id;		this.reason = reason;		this.sys0DelState = sys0DelState;		this.refundRatio = refundRatio;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("reason=").append(reason).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("refundRatio=").append(refundRatio).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getReason() {		return reason;	}	public void setReason(String reason) {		this.reason = reason;	}	public Long getRefundRatio() {		return refundRatio;	}	public void setRefundRatio(Long refundRatio) {		this.refundRatio = refundRatio;	}
	
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
		EbuyRefundAudit other = (EbuyRefundAudit) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
