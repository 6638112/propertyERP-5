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
public class FinanceSum implements Serializable {
	
	private static final long serialVersionUID = -7652672048339317122L;

	private BigDecimal retWyMoney; //返还的抵扣物业费总计
	
	private BigDecimal retProfitMoney; //返还佣金金额总计
	
	private BigDecimal profitAll; //物业抵扣收益总计
	
	private BigDecimal wyProfit; //物业回款金额总计
	
	private BigDecimal agentProfit; //代理回款金额总计
	
	private BigDecimal wyMoney; //界面4（物业抵扣明细）抵扣物业费金额总计
	
	private BigDecimal parkingFees;// 停车费

	public BigDecimal getParkingFees() {
		return parkingFees;
	}

	public void setParkingFees(BigDecimal parkingFees) {
		this.parkingFees = parkingFees;
	}

	public BigDecimal getRetWyMoney() {
		return retWyMoney;
	}

	public void setRetWyMoney(BigDecimal retWyMoney) {
		this.retWyMoney = retWyMoney;
	}

	public BigDecimal getRetProfitMoney() {
		return retProfitMoney;
	}

	public void setRetProfitMoney(BigDecimal retProfitMoney) {
		this.retProfitMoney = retProfitMoney;
	}

	public BigDecimal getProfitAll() {
		return profitAll;
	}

	public void setProfitAll(BigDecimal profitAll) {
		this.profitAll = profitAll;
	}

	public BigDecimal getWyProfit() {
		return wyProfit;
	}

	public void setWyProfit(BigDecimal wyProfit) {
		this.wyProfit = wyProfit;
	}

	public BigDecimal getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(BigDecimal agentProfit) {
		this.agentProfit = agentProfit;
	}

	public BigDecimal getWyMoney() {
		return wyMoney;
	}

	public void setWyMoney(BigDecimal wyMoney) {
		this.wyMoney = wyMoney;
	}
	
}
