package com.cnfantasia.server.ms.bankCollection.entity;

import com.cnfantasia.server.api.bankCollection.entity.RebackRecordDto;

public class RebackRecordReq extends RebackRecordDto {

	private String rebackStartTime;
	private String rebackEndTime;
	private String bgbcIds;
	/** 银行托收结果 */
	private String bankResult;

	public String getRebackStartTime() {
		return rebackStartTime;
	}

	public void setRebackStartTime(String rebackStartTime) {
		this.rebackStartTime = rebackStartTime;
	}

	public String getRebackEndTime() {
		return rebackEndTime;
	}

	public void setRebackEndTime(String rebackEndTime) {
		this.rebackEndTime = rebackEndTime;
	}

	public String getBgbcIds() {
		return bgbcIds;
	}

	public void setBgbcIds(String bgbcIds) {
		this.bgbcIds = bgbcIds;
	}

	public String getBankResult() {
		return bankResult;
	}

	public void setBankResult(String bankResult) {
		this.bankResult = bankResult;
	}

}
