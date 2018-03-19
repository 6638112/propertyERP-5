package com.cnfantasia.server.ms.door.entity;

import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;

/**
 * 门牌验证缴费情况查询Dto
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午11:05:57
 */
public class DoorVerifyAndPaymentDto extends RoomVerifyPayment {

	/** 认证状态 */
	private Integer verifyState;
	/** 认证时间 */
	private String verifyTime;
	/** 物业公司 */
	private String pcName;
	/** 代理商 */
	private String cpCompanyName;

	public DoorVerifyAndPaymentDto() {
		super();
	}

	public DoorVerifyAndPaymentDto(Integer verifyState, String verifyTime, String pcName, String cpCompanyName) {
		super();
		this.verifyState = verifyState;
		this.verifyTime = verifyTime;
		this.pcName = pcName;
		this.cpCompanyName = cpCompanyName;
	}

	public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getCpCompanyName() {
		return cpCompanyName;
	}

	public void setCpCompanyName(String cpCompanyName) {
		this.cpCompanyName = cpCompanyName;
	}

}
