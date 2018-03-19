package com.cnfantasia.server.ms.groupBuildingBillCycle.dto;

/**
 * 【物业管理-小区缴费管理-收费账单配置-缴费周期】列表查询参数
 * 
 * @author liyulin
 * @version 1.0 2017年2月7日 上午10:09:20
 */
public class BillCycleParam {

	/** 小区名称 */
	private String groupBuildingName;
	/** 账单名称 */
	private String billName;
	/** 账单周期开始时间 */
	private String billCycleStart;
	/** 账单周期截止时间 */
	private String billCycleEnd;
	/** 缴费开始时间 */
	private String payTimeStart;
	/** 缴费截止时间 */
	private String payTimeEnd;

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillCycleStart() {
		return billCycleStart;
	}

	public void setBillCycleStart(String billCycleStart) {
		this.billCycleStart = billCycleStart;
	}

	public String getBillCycleEnd() {
		return billCycleEnd;
	}

	public void setBillCycleEnd(String billCycleEnd) {
		this.billCycleEnd = billCycleEnd;
	}

	public String getPayTimeStart() {
		return payTimeStart;
	}

	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}

	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

}
