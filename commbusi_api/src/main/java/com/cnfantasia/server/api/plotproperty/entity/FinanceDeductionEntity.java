package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceDeductionEntity implements Serializable {
	
	private static final long serialVersionUID = -6817216171043265007L;

	private Long id; //主键ID
	
	private Long payBillId;

	private Long propertyFeeDetailId; //物业费ID
	
	private String orderNo;
	
	private BigDecimal deductionPrice; //抵扣金额

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(Long payBillId) {
		this.payBillId = payBillId;
	}

	public Long getPropertyFeeDetailId() {
		return propertyFeeDetailId;
	}

	public void setPropertyFeeDetailId(Long propertyFeeDetailId) {
		this.propertyFeeDetailId = propertyFeeDetailId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getDeductionPrice() {
		return deductionPrice.multiply(new BigDecimal(100));
//		return deductionPrice;
	}

	public void setDeductionPrice(BigDecimal deductionPrice) {
		this.deductionPrice = deductionPrice;
	}
	
}
