package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;

/**
 * 托盘记录
 * 
 * @author liyulin
 * @version 1.0 2017年4月17日 上午10:28:46
 */
public class BCHistoryDto extends BcBankInfoDto {
	/** 最新的记录id */
	private BigInteger bgbcId;
	/** 账单名称 */
	private String billName;
	/** 账单周期开始 */
	private String billMonthStart;
	/** 账单周期截止 */
	private String billMonthEnd;
	/** 是否已导入回盘文件 */
	private Boolean importFileStats;
	/** 回盘状态 */
	private Integer rebackStatus;
	/** 账期对应的所有的id */
	private String bgbcIds;
	private String className;
	/** 文件格式 */
	private String fileFormat;
	/** 明细出盘文件URL */
	private String detailFileUrl;
	/** sum出盘文件URL */
	private String sumFileUrl;

	public BigInteger getBgbcId() {
		return bgbcId;
	}

	public void setBgbcId(BigInteger bgbcId) {
		this.bgbcId = bgbcId;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillMonthStart() {
		return billMonthStart;
	}

	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}

	public String getBillMonthEnd() {
		return billMonthEnd;
	}

	public void setBillMonthEnd(String billMonthEnd) {
		this.billMonthEnd = billMonthEnd;
	}

	public Boolean getImportFileStats() {
		return importFileStats;
	}

	public void setImportFileStats(Boolean importFileStats) {
		this.importFileStats = importFileStats;
	}

	public Integer getRebackStatus() {
		return rebackStatus;
	}

	public void setRebackStatus(Integer rebackStatus) {
		this.rebackStatus = rebackStatus;
	}

	public String getBgbcIds() {
		return bgbcIds;
	}

	public void setBgbcIds(String bgbcIds) {
		this.bgbcIds = bgbcIds;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getDetailFileUrl() {
		return detailFileUrl;
	}

	public void setDetailFileUrl(String detailFileUrl) {
		this.detailFileUrl = detailFileUrl;
	}

	public String getSumFileUrl() {
		return sumFileUrl;
	}

	public void setSumFileUrl(String sumFileUrl) {
		this.sumFileUrl = sumFileUrl;
	}

}
