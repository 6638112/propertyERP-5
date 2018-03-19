package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 确认支付界面（优惠）信息请求参数
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午10:06:09
 */
public class ConfirmBillInfoReq {

	/** 数据来源类型0解放区平台，1为第三方极致物业 */
	private String dataFromType;
	/** 车牌 */
	private String carNum;
	/** 月卡、选择缴费周期缴费月数 */
	private String payMonth;
	private BigInteger payBillId;
	/** 账单配置id（支付时需要） */
	private BigInteger gbbccId;
	/**充值金额（单位：分）*/
	private Long rechargeAmt;

	public String getDataFromType() {
		return dataFromType;
	}

	public void setDataFromType(String dataFromType) {
		this.dataFromType = dataFromType;
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

	public BigInteger getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(BigInteger payBillId) {
		this.payBillId = payBillId;
	}

	public BigInteger getGbbccId() {
		return gbbccId;
	}

	public void setGbbccId(BigInteger gbbccId) {
		this.gbbccId = gbbccId;
	}

	public Long getRechargeAmt() {
		return rechargeAmt;
	}

	public void setRechargeAmt(Long rechargeAmt) {
		this.rechargeAmt = rechargeAmt;
	}
	
}
