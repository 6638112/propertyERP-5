package com.cnfantasia.server.ms.carReport.dto;

/**
 * 停车费汇总报显示信息
 * 
 * @author liyulin
 * @version 1.0 2017年11月7日 上午9:40:34
 */
public class CarReportDTO {
	/** 管理处 */
	private String pmName;
	/** 停车场 */
	private String plotName;
	/** 月卡费用 */
	private PayChannelDTO monthPayChannel;
	/** 临停车费用 */
	private PayChannelDTO tempPayChannel;
	/** 总的合计 */
	private PayChannelDTO totalPayChannel;

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPlotName() {
		return plotName;
	}

	public void setPlotName(String plotName) {
		this.plotName = plotName;
	}

	public PayChannelDTO getMonthPayChannel() {
		return monthPayChannel;
	}

	public void setMonthPayChannel(PayChannelDTO monthPayChannel) {
		this.monthPayChannel = monthPayChannel;
	}

	public PayChannelDTO getTempPayChannel() {
		return tempPayChannel;
	}

	public void setTempPayChannel(PayChannelDTO tempPayChannel) {
		this.tempPayChannel = tempPayChannel;
	}

	public PayChannelDTO getTotalPayChannel() {
		return totalPayChannel;
	}

	public void setTotalPayChannel(PayChannelDTO totalPayChannel) {
		this.totalPayChannel = totalPayChannel;
	}

}
