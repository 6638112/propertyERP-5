package com.cnfantasia.server.domainbase.ebuyOrderExt.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(订单扩展表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderExt implements Serializable{
*/


public class EbuyOrderExt extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderFId;	/** 物业公司优惠金额（单位 */	private Long wyCouponAmount;	/** 付款到哪里（0为自己平台，1物业公司，2管理处） */	private Integer paymentTo;
	public EbuyOrderExt(){
	}
	public EbuyOrderExt(EbuyOrderExt arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.wyCouponAmount = arg.getWyCouponAmount();		this.paymentTo = arg.getPaymentTo();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 	 * @param wyCouponAmount 物业公司优惠金额（单位	 * @param paymentTo 付款到哪里（0为自己平台，1物业公司，2管理处）	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public EbuyOrderExt(BigInteger id,BigInteger tEbuyOrderFId,Long wyCouponAmount,Integer paymentTo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.wyCouponAmount = wyCouponAmount;		this.paymentTo = paymentTo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("wyCouponAmount=").append(wyCouponAmount).append(";");		sbf.append("paymentTo=").append(paymentTo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public Long getWyCouponAmount() {		return wyCouponAmount;	}	public void setWyCouponAmount(Long wyCouponAmount) {		this.wyCouponAmount = wyCouponAmount;	}	public Integer getPaymentTo() {		return paymentTo;	}	public void setPaymentTo(Integer paymentTo) {		this.paymentTo = paymentTo;	}
	
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
		EbuyOrderExt other = (EbuyOrderExt) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
