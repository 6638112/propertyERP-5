package com.cnfantasia.server.domainbase.cashSqpayBt.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(双乾支付优惠补贴明细表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class CashSqpayBt implements Serializable{
/* */

/* 
public class CashSqpayBt extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuyOrderFId;	/** 业务对象名称（注 */	private String goalName;	/** 订单类型 */	private Integer orderType;	/** 补贴金额（单位 */	private Long amountBt;
	public CashSqpayBt(){
	}
	public CashSqpayBt(CashSqpayBt arg){
		this.id = arg.getId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.goalName = arg.getGoalName();		this.orderType = arg.getOrderType();		this.amountBt = arg.getAmountBt();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderFId 	 * @param goalName 业务对象名称（注	 * @param orderType 订单类型	 * @param amountBt 补贴金额（单位	 */
	public CashSqpayBt(BigInteger id,BigInteger tEbuyOrderFId,String goalName,Integer orderType,Long amountBt){
		this.id = id;		this.tEbuyOrderFId = tEbuyOrderFId;		this.goalName = goalName;		this.orderType = orderType;		this.amountBt = amountBt;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("goalName=").append(goalName).append(";");		sbf.append("orderType=").append(orderType).append(";");		sbf.append("amountBt=").append(amountBt).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public String getGoalName() {		return goalName;	}	public void setGoalName(String goalName) {		this.goalName = goalName;	}	public Integer getOrderType() {		return orderType;	}	public void setOrderType(Integer orderType) {		this.orderType = orderType;	}	public Long getAmountBt() {		return amountBt;	}	public void setAmountBt(Long amountBt) {		this.amountBt = amountBt;	}
	
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
		CashSqpayBt other = (CashSqpayBt) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
