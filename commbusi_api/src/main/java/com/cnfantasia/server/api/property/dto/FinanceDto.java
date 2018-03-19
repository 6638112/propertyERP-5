package com.cnfantasia.server.api.property.dto;

/**
 * 物业宝信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:36:18
 */
public class FinanceDto {

	/** 物业宝购买地址 */
	private String buyUrl;
	/** 物业宝显示：免物业费 或 已购买 */
	private String content;
	/** 是否已购买 */
	private boolean isBuy;
	/** 是否开通物业宝服务 */
	private boolean isOpenFinance;

	public String getBuyUrl() {
		return buyUrl;
	}

	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public boolean isOpenFinance() {
		return isOpenFinance;
	}

	public void setOpenFinance(boolean isOpenFinance) {
		this.isOpenFinance = isOpenFinance;
	}

}
