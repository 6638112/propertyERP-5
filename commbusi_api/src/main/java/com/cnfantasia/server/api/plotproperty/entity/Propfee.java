package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class Propfee implements Serializable {
	
	private static final long serialVersionUID = 7053857390435103791L;

	private Integer propfeeCanpay; //小区是否可以缴费
	
	private Integer verifyStatus; //门牌是否校验
	
//	private Integer impBillCount;
	
	private Integer totalPrice; //物业费
	
	private Integer deductionCount;
	
	private Integer hasDeductionCount;
	
	private Date deductionBeginDate;
	
	private Date deductionEndDate;
	
	private Integer payPeriodEnd;

	public Integer getPropfeeCanpay() {
		return propfeeCanpay;
	}

	public void setPropfeeCanpay(Integer propfeeCanpay) {
		this.propfeeCanpay = propfeeCanpay;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

//	public Integer getImpBillCount() {
//		return impBillCount;
//	}
//
//	public void setImpBillCount(Integer impBillCount) {
//		this.impBillCount = impBillCount;
//	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getDeductionCount() {
		return deductionCount;
	}

	public void setDeductionCount(Integer deductionCount) {
		this.deductionCount = deductionCount;
	}

	public Integer getHasDeductionCount() {
		return hasDeductionCount;
	}

	public void setHasDeductionCount(Integer hasDeductionCount) {
		this.hasDeductionCount = hasDeductionCount;
	}

	public Date getDeductionBeginDate() {
		return deductionBeginDate;
	}

	public void setDeductionBeginDate(Date deductionBeginDate) {
		this.deductionBeginDate = deductionBeginDate;
	}

	public Date getDeductionEndDate() {
		return deductionEndDate;
	}

	public void setDeductionEndDate(Date deductionEndDate) {
		this.deductionEndDate = deductionEndDate;
	}

	public Integer getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(Integer payPeriodEnd) {
		this.payPeriodEnd = payPeriodEnd;
	}

}
