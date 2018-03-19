package com.cnfantasia.server.domainbase.propertyRechargePreferRule.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业预充值随机立减规则表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyRechargePreferRule implements Serializable{
*/


public class PropertyRechargePreferRule extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 单笔优惠最小金额（单位 */	private Long minBillAmount;	/** 优惠最小金额（单位 */	private Long preferMinAmount;	/** 优惠最大金额（单位 */	private Long preferMaxAmount;	/** 每月优惠最大金额（单位 */	private Long totalMaxAmountPerMonth;	/** 每月优惠最大金额百分比（单位 */	private Long maxPercentPerMonth;
	public PropertyRechargePreferRule(){
	}
	public PropertyRechargePreferRule(PropertyRechargePreferRule arg){
		this.id = arg.getId();		this.minBillAmount = arg.getMinBillAmount();		this.preferMinAmount = arg.getPreferMinAmount();		this.preferMaxAmount = arg.getPreferMaxAmount();		this.totalMaxAmountPerMonth = arg.getTotalMaxAmountPerMonth();		this.maxPercentPerMonth = arg.getMaxPercentPerMonth();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param minBillAmount 单笔优惠最小金额（单位	 * @param preferMinAmount 优惠最小金额（单位	 * @param preferMaxAmount 优惠最大金额（单位	 * @param totalMaxAmountPerMonth 每月优惠最大金额（单位	 * @param maxPercentPerMonth 每月优惠最大金额百分比（单位	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public PropertyRechargePreferRule(BigInteger id,Long minBillAmount,Long preferMinAmount,Long preferMaxAmount,Long totalMaxAmountPerMonth,Long maxPercentPerMonth,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.minBillAmount = minBillAmount;		this.preferMinAmount = preferMinAmount;		this.preferMaxAmount = preferMaxAmount;		this.totalMaxAmountPerMonth = totalMaxAmountPerMonth;		this.maxPercentPerMonth = maxPercentPerMonth;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("minBillAmount=").append(minBillAmount).append(";");		sbf.append("preferMinAmount=").append(preferMinAmount).append(";");		sbf.append("preferMaxAmount=").append(preferMaxAmount).append(";");		sbf.append("totalMaxAmountPerMonth=").append(totalMaxAmountPerMonth).append(";");		sbf.append("maxPercentPerMonth=").append(maxPercentPerMonth).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getMinBillAmount() {		return minBillAmount;	}	public void setMinBillAmount(Long minBillAmount) {		this.minBillAmount = minBillAmount;	}	public Long getPreferMinAmount() {		return preferMinAmount;	}	public void setPreferMinAmount(Long preferMinAmount) {		this.preferMinAmount = preferMinAmount;	}	public Long getPreferMaxAmount() {		return preferMaxAmount;	}	public void setPreferMaxAmount(Long preferMaxAmount) {		this.preferMaxAmount = preferMaxAmount;	}	public Long getTotalMaxAmountPerMonth() {		return totalMaxAmountPerMonth;	}	public void setTotalMaxAmountPerMonth(Long totalMaxAmountPerMonth) {		this.totalMaxAmountPerMonth = totalMaxAmountPerMonth;	}	public Long getMaxPercentPerMonth() {		return maxPercentPerMonth;	}	public void setMaxPercentPerMonth(Long maxPercentPerMonth) {		this.maxPercentPerMonth = maxPercentPerMonth;	}
	
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
		PropertyRechargePreferRule other = (PropertyRechargePreferRule) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
