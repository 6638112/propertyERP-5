package com.cnfantasia.server.ms.revenue.entity;

import java.util.Date;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class FinanceRevenueSignalAmount extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 1408255921349413654L;
	
	private Double amountAgent;
	
	private Integer tkStatusAgent;
	
	private Double amountWy;
	
	private Integer tkStatusWy;
	
	private Double amountPlatform;
	
	private Integer tkStatusPlatform;
	
	private FinanceRevenue financeBuyEntity;
	
	public String getTkStatusStr() {
		return getStatusStrByStatus(getTkStatus()); 
	}
	
	public String getTkStatusAgentStr() {
		return getStatusStrByStatus(tkStatusAgent); 
	}
	
	public String getTkStatusWyStr() {
		return getStatusStrByStatus(tkStatusWy); 
	}

	private String getStatusStrByStatus(Integer tkStatus) {
		if(RevenueDict.TkStatus.Doing.equals(tkStatus)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatus)) {
			return "已结算";
		}  else if(RevenueDict.TkStatus.Undo.equals(tkStatus)) {
			return "未结算";
		} else {
			return "";
		}
	}
	
	public String getTkStatusPlatformStr() {
		return getStatusStrByStatus(tkStatusPlatform); 
	}

	public FinanceRevenue getFinanceBuyEntity() {
		return financeBuyEntity;
	}

	public void setFinanceBuyEntity(FinanceRevenue financeBuyEntity) {
		this.financeBuyEntity = financeBuyEntity;
	}
	
	public Date getGoalRevTimeDate() {
		String goalRevTime = super.getGoalRevTime();
		return DateUtils.strToDateTime(goalRevTime);
	}

	public Double getAmountAgent() {
		return amountAgent;
	}

	public void setAmountAgent(Double amountAgent) {
		this.amountAgent = amountAgent;
	}

	public Integer getTkStatusAgent() {
		return tkStatusAgent;
	}

	public void setTkStatusAgent(Integer tkStatusAgent) {
		this.tkStatusAgent = tkStatusAgent;
	}

	public Double getAmountWy() {
		return amountWy;
	}

	public void setAmountWy(Double amountWy) {
		this.amountWy = amountWy;
	}

	public Integer getTkStatusWy() {
		return tkStatusWy;
	}

	public void setTkStatusWy(Integer tkStatusWy) {
		this.tkStatusWy = tkStatusWy;
	}

	public Double getAmountPlatform() {
		return amountPlatform;
	}

	public void setAmountPlatform(Double amountPlatform) {
		this.amountPlatform = amountPlatform;
	}

	public Integer getTkStatusPlatform() {
		return tkStatusPlatform;
	}

	public void setTkStatusPlatform(Integer tkStatusPlatform) {
		this.tkStatusPlatform = tkStatusPlatform;
	}

}
