package com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(订单使用消费券表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderHasCoupon implements Serializable{
*/


public class EbuyOrderHasCoupon extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 订单ID */	private BigInteger tEbuyOrderFId;	/** 消费券ID */	private BigInteger tUserCouponFId;	/** 优惠金额(分) */	private Long amount;
	public EbuyOrderHasCoupon(){
	}
	public EbuyOrderHasCoupon(EbuyOrderHasCoupon arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tUserCouponFId = arg.gettUserCouponFId();		this.amount = arg.getAmount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 订单ID	 * @param tUserCouponFId 消费券ID	 * @param amount 优惠金额(分)	 * @param sys0AddTime 新增时间	 * @param sys0AddUser 新增者	 * @param sys0UpdTime 	 * @param sys0UpdUser 	 * @param sys0DelTime 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public EbuyOrderHasCoupon(BigInteger id,BigInteger tEbuyOrderFId,BigInteger tUserCouponFId,Long amount,String sys0AddTime,BigInteger sys0AddUser,String sys0UpdTime,BigInteger sys0UpdUser,String sys0DelTime,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tUserCouponFId = tUserCouponFId;		this.amount = amount;		this.sys0AddTime = sys0AddTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdTime = sys0UpdTime;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelTime = sys0DelTime;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tUserCouponFId=").append(tUserCouponFId).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettUserCouponFId() {		return tUserCouponFId;	}	public void settUserCouponFId(BigInteger tUserCouponFId) {		this.tUserCouponFId = tUserCouponFId;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}
	
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
		EbuyOrderHasCoupon other = (EbuyOrderHasCoupon) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
