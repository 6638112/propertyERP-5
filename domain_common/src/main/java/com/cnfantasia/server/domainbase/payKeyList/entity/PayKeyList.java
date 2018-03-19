package com.cnfantasia.server.domainbase.payKeyList.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(云钥匙付款列表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayKeyList implements Serializable{
*/


public class PayKeyList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键,同t_ebuy_product.f_id */	private BigInteger id;	/** 付费详情描述 */	private String desc;	/** 支付价格 */	private Long payPrice;	/** 有效时间 */	private Long payTime;
	public PayKeyList(){
	}
	public PayKeyList(PayKeyList arg){
		this.id = arg.getId();		this.desc = arg.getDesc();		this.sys0DelState = arg.getSys0DelState();		this.payPrice = arg.getPayPrice();		this.payTime = arg.getPayTime();
	}
	/**	 * 	 * @param id 主键,同t_ebuy_product.f_id	 * @param desc 付费详情描述	 * @param sys0DelState 记录状态	 * @param payPrice 支付价格	 * @param payTime 有效时间	 */
	public PayKeyList(BigInteger id,String desc,Integer sys0DelState,Long payPrice,Long payTime){
		this.id = id;		this.desc = desc;		this.sys0DelState = sys0DelState;		this.payPrice = payPrice;		this.payTime = payTime;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("payPrice=").append(payPrice).append(";");		sbf.append("payTime=").append(payTime).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Long getPayPrice() {		return payPrice;	}	public void setPayPrice(Long payPrice) {		this.payPrice = payPrice;	}	public Long getPayTime() {		return payTime;	}	public void setPayTime(Long payTime) {		this.payTime = payTime;	}
	
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
		PayKeyList other = (PayKeyList) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
