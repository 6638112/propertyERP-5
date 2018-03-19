package com.cnfantasia.server.domainbase.alterPeriodPrefer.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(选择周期缴费优惠表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AlterPeriodPrefer implements Serializable{
*/


public class AlterPeriodPrefer extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 解放号 */	private BigInteger tUserFId;	/** 账单id（支付成功回写） */	private BigInteger tPayBillId;	/** 真实房间id */	private BigInteger tRealRoomId;	/** 缴费月数 */	private Long payMonth;	/** 总金额（单位 */	private Long amount;	/** 优惠金额（单位 */	private Long couponAmount;	/**  */	private String operationDate;
	public AlterPeriodPrefer(){
	}
	public AlterPeriodPrefer(AlterPeriodPrefer arg){
		this.id = arg.getId();		this.tUserFId = arg.gettUserFId();		this.tPayBillId = arg.gettPayBillId();		this.tRealRoomId = arg.gettRealRoomId();		this.payMonth = arg.getPayMonth();		this.amount = arg.getAmount();		this.couponAmount = arg.getCouponAmount();		this.operationDate = arg.getOperationDate();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tUserFId 解放号	 * @param tPayBillId 账单id（支付成功回写）	 * @param tRealRoomId 真实房间id	 * @param payMonth 缴费月数	 * @param amount 总金额（单位	 * @param couponAmount 优惠金额（单位	 * @param operationDate 	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public AlterPeriodPrefer(BigInteger id,BigInteger tUserFId,BigInteger tPayBillId,BigInteger tRealRoomId,Long payMonth,Long amount,Long couponAmount,String operationDate,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tUserFId = tUserFId;		this.tPayBillId = tPayBillId;		this.tRealRoomId = tRealRoomId;		this.payMonth = payMonth;		this.amount = amount;		this.couponAmount = couponAmount;		this.operationDate = operationDate;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tPayBillId=").append(tPayBillId).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("payMonth=").append(payMonth).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("couponAmount=").append(couponAmount).append(";");		sbf.append("operationDate=").append(operationDate).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettPayBillId() {		return tPayBillId;	}	public void settPayBillId(BigInteger tPayBillId) {		this.tPayBillId = tPayBillId;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public Long getPayMonth() {		return payMonth;	}	public void setPayMonth(Long payMonth) {		this.payMonth = payMonth;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public Long getCouponAmount() {		return couponAmount;	}	public void setCouponAmount(Long couponAmount) {		this.couponAmount = couponAmount;	}	public String getOperationDate() {		return operationDate;	}	public void setOperationDate(String operationDate) {		this.operationDate = operationDate;	}
	
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
		AlterPeriodPrefer other = (AlterPeriodPrefer) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
