package com.cnfantasia.server.ms.limitBuyActivity.entity;

/**
 * 限时购查询参数
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 上午10:24:36
 */
public class QryParam {
	private String productId;
	/** 商品名 */
	private String productName;
	/** 开始起始时间 */
	private String startTimeStart;
	/** 开始截止时间 */
	private String startTimeEnd;
	/** 结束起始时间 */
	private String endTimeStart;
	/** 结束截止时间 */
	private String endTimeEnd;
	/** 状态 */
	private String status;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStartTimeStart() {
		return startTimeStart;
	}

	public void setStartTimeStart(String startTimeStart) {
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	public String getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(String endTimeStart) {
		this.endTimeStart = endTimeStart;
	}

	public String getEndTimeEnd() {
		return endTimeEnd;
	}

	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
