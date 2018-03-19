package com.cnfantasia.server.api.access.entity;

import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

public class CarNumPayLogDetail extends CarNumPayLog {
    /**
     * 
     */
    private static final long serialVersionUID = 8045593388267549607L;
    private String plate;
    private String pushUrl;
    private String tradeCode;
    private String validDate;
    /** 是否购买停车宝 */
	private boolean isBuyFinance;

    public String getPlate() {
        return plate;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

	public boolean isBuyFinance() {
		return isBuyFinance;
	}

	public void setBuyFinance(boolean isBuyFinance) {
		this.isBuyFinance = isBuyFinance;
	}
    
}
