package com.cnfantasia.server.api.property.dto;

import java.math.BigInteger;

/**
 * 获取账单详情信息（未缴）请求参数
 * 
 * @author liyulin
 * @version 1.0 2017年3月17日 下午2:17:42
 */
public class UnPaidBillDetailReq {

	/** 用户id */
	private BigInteger userId;
	/** 车牌 */
	private String carNum;
	/** 缴费月数 */
	private String payMonth;
	/** 账单id */
	private String payBillId;
	/** 数据来源类型（0：解放区平台；1：第三方极致物业） */
	private String dataFromType;
	/**收费类型：1 定期  2选择周期*/
	private Integer cycleType;

	private String sessionId;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}

	public String getDataFromType() {
		return dataFromType;
	}

	public void setDataFromType(String dataFromType) {
		this.dataFromType = dataFromType;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getCycleType() {
		return cycleType;
	}

	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}
}
