package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceDeductionDetail extends FinanceDeductionEntity {
	
	private static final long serialVersionUID = 8794104235309238761L;
	
	private String wyName;
	
	private Date deductionTm;
	
	private FinanceReqEntity financeReqEntity;
	
	private BigDecimal deductionPrice2;

	public String getWyName() {
		return wyName;
	}

	public void setWyName(String wyName) {
		this.wyName = wyName;
	}

	public Date getDeductionTm() {
		return deductionTm;
	}

	public void setDeductionTm(Date deductionTm) {
		this.deductionTm = deductionTm;
	}

	public FinanceReqEntity getFinanceReqEntity() {
		return financeReqEntity;
	}

	public void setFinanceReqEntity(FinanceReqEntity financeReqEntity) {
		this.financeReqEntity = financeReqEntity;
	}

	public BigDecimal getDeductionPrice2() {
		return deductionPrice2;
	}

	public void setDeductionPrice2(BigDecimal deductionPrice2) {
		this.deductionPrice2 = deductionPrice2;
	}
	
}
