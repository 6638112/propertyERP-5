package com.cnfantasia.server.domainbase.propertyRechargePrefer.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业充值随机立减记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyRechargePrefer implements Serializable{
*/


public class PropertyRechargePrefer extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tGbbccId;	/**  */	private BigInteger tUserId;	/**  */	private BigInteger tRealRoomId;	/** 充值金额（单位 */	private Long amount;	/** 支付回调时写入 */	private BigInteger tPaybillId;	/** 随机立减金额（单位 */	private Long perferAmount;	/** 支付状态 */	private Integer payState;
	public PropertyRechargePrefer(){
	}
	public PropertyRechargePrefer(PropertyRechargePrefer arg){
		this.id = arg.getId();		this.tGbbccId = arg.gettGbbccId();		this.tUserId = arg.gettUserId();		this.tRealRoomId = arg.gettRealRoomId();		this.amount = arg.getAmount();		this.tPaybillId = arg.gettPaybillId();		this.perferAmount = arg.getPerferAmount();		this.payState = arg.getPayState();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGbbccId 	 * @param tUserId 	 * @param tRealRoomId 	 * @param amount 充值金额（单位	 * @param tPaybillId 支付回调时写入	 * @param perferAmount 随机立减金额（单位	 * @param payState 支付状态	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public PropertyRechargePrefer(BigInteger id,BigInteger tGbbccId,BigInteger tUserId,BigInteger tRealRoomId,Long amount,BigInteger tPaybillId,Long perferAmount,Integer payState,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGbbccId = tGbbccId;		this.tUserId = tUserId;		this.tRealRoomId = tRealRoomId;		this.amount = amount;		this.tPaybillId = tPaybillId;		this.perferAmount = perferAmount;		this.payState = payState;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGbbccId=").append(tGbbccId).append(";");		sbf.append("tUserId=").append(tUserId).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("tPaybillId=").append(tPaybillId).append(";");		sbf.append("perferAmount=").append(perferAmount).append(";");		sbf.append("payState=").append(payState).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGbbccId() {		return tGbbccId;	}	public void settGbbccId(BigInteger tGbbccId) {		this.tGbbccId = tGbbccId;	}	public BigInteger gettUserId() {		return tUserId;	}	public void settUserId(BigInteger tUserId) {		this.tUserId = tUserId;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public BigInteger gettPaybillId() {		return tPaybillId;	}	public void settPaybillId(BigInteger tPaybillId) {		this.tPaybillId = tPaybillId;	}	public Long getPerferAmount() {		return perferAmount;	}	public void setPerferAmount(Long perferAmount) {		this.perferAmount = perferAmount;	}	public Integer getPayState() {		return payState;	}	public void setPayState(Integer payState) {		this.payState = payState;	}
	
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
		PropertyRechargePrefer other = (PropertyRechargePrefer) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
