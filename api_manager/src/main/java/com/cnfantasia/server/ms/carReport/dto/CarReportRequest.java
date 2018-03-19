package com.cnfantasia.server.ms.carReport.dto;

/**
 * 停车费汇总报查询参数
 * 
 * @author liyulin
 * @version 1.0 2017年11月7日 上午9:44:22
 */
public class CarReportRequest {

	/** 查询月份 */
	private String month;
	/** 管理处 */
	private String pmId;
	/** 停车场 */
	private String plotId;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getPlotId() {
		return plotId;
	}

	public void setPlotId(String plotId) {
		this.plotId = plotId;
	}

}
