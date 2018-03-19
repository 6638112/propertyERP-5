package com.cnfantasia.server.domainbase.payRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Double;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业缴费记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayRecord implements Serializable{
*/


public class PayRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 缴费者Id */	private BigInteger userId;	/** 所属物业账单Id */	private BigInteger tPayBillFId;	/** 缴费时间 */	private String payDate;	/** 缴费金额 */	private Long amount;	/** 使用的折扣 */	private Double discount;	/** 实际缴费金额 */	private Long realAmount;	/** 支付方式 */	private Long payType;	/** 支付结果 */	private Long payResult;
	public PayRecord(){
	}
	public PayRecord(PayRecord arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.tPayBillFId = arg.gettPayBillFId();		this.payDate = arg.getPayDate();		this.amount = arg.getAmount();		this.discount = arg.getDiscount();		this.realAmount = arg.getRealAmount();		this.payType = arg.getPayType();		this.payResult = arg.getPayResult();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 缴费者Id	 * @param tPayBillFId 所属物业账单Id	 * @param payDate 缴费时间	 * @param amount 缴费金额	 * @param discount 使用的折扣	 * @param realAmount 实际缴费金额	 * @param payType 支付方式	 * @param payResult 支付结果	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PayRecord(BigInteger id,BigInteger userId,BigInteger tPayBillFId,String payDate,Long amount,Double discount,Long realAmount,Long payType,Long payResult,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.tPayBillFId = tPayBillFId;		this.payDate = payDate;		this.amount = amount;		this.discount = discount;		this.realAmount = realAmount;		this.payType = payType;		this.payResult = payResult;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");		sbf.append("payDate=").append(payDate).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("discount=").append(discount).append(";");		sbf.append("realAmount=").append(realAmount).append(";");		sbf.append("payType=").append(payType).append(";");		sbf.append("payResult=").append(payResult).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public BigInteger gettPayBillFId() {		return tPayBillFId;	}	public void settPayBillFId(BigInteger tPayBillFId) {		this.tPayBillFId = tPayBillFId;	}	public String getPayDate() {		return payDate;	}	public void setPayDate(String payDate) {		this.payDate = payDate;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public Double getDiscount() {		return discount;	}	public void setDiscount(Double discount) {		this.discount = discount;	}	public Long getRealAmount() {		return realAmount;	}	public void setRealAmount(Long realAmount) {		this.realAmount = realAmount;	}	public Long getPayType() {		return payType;	}	public void setPayType(Long payType) {		this.payType = payType;	}	public Long getPayResult() {		return payResult;	}	public void setPayResult(Long payResult) {		this.payResult = payResult;	}
	
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
		PayRecord other = (PayRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
