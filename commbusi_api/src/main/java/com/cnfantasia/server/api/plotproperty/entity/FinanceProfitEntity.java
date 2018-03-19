package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
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
public class FinanceProfitEntity implements Serializable {
	
	private static final long serialVersionUID = 504231777458381250L;

	private Long id;
	
	private String orderNo; //订单号
	
	private BigDecimal profitWy; //物业回款金额
	
	private BigDecimal profitAgent; //代理回款金额
	
	private BigDecimal profitParking; // 停车宝收益
	
	private Date profitTm; //回款日期
	
	private String wyName; //物业公司名称
	
	private String agentName; //代理公司名称
	
	private FinanceReqEntity financeReqEntity;
	
	private FinanceBuyEntity financeBuyEntity;

	public Long getId() {
		return id;
	}

	public BigDecimal getProfitParking() {
		return profitParking;
	}

	public void setProfitParking(BigDecimal profitParking) {
		this.profitParking = profitParking;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getProfitWy() {
		return profitWy;
	}

	public void setProfitWy(BigDecimal profitWy) {
		this.profitWy = profitWy;
	}

	public BigDecimal getProfitAgent() {
		return profitAgent;
	}

	public void setProfitAgent(BigDecimal profitAgent) {
		this.profitAgent = profitAgent;
	}
	
	public BigDecimal getProfit() {
		if(profitWy.doubleValue() > 0) {
			return profitWy;
		} else {
			return profitAgent;
		}
	}

	public String getWyName() {
		return wyName;
	}

	public void setWyName(String wyName) {
		this.wyName = wyName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getProfitTm() {
		return profitTm;
	}

	public void setProfitTm(Date profitTm) {
		this.profitTm = profitTm;
	}

	public FinanceReqEntity getFinanceReqEntity() {
		return financeReqEntity;
	}

	public void setFinanceReqEntity(FinanceReqEntity financeReqEntity) {
		this.financeReqEntity = financeReqEntity;
	}

	public FinanceBuyEntity getFinanceBuyEntity() {
		return financeBuyEntity;
	}

	public void setFinanceBuyEntity(FinanceBuyEntity financeBuyEntity) {
		this.financeBuyEntity = financeBuyEntity;
	}
	
}
