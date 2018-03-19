package com.cnfantasia.server.api.payment.entity;

public class SqPayBtVerifyResponse extends SqPayBtResponse {

	/** 是否校验通过 */
	private boolean isVerifyPassed;

	public boolean isVerifyPassed() {
		return isVerifyPassed;
	}

	public void setVerifyPassed(boolean isVerifyPassed) {
		this.isVerifyPassed = isVerifyPassed;
	}

	@Override
	public String toString() {
		return "SqPayBtVerifyResponse [isVerifyPassed=" + isVerifyPassed
				+ ", toString()=" + super.toString() + "]";
	}

}
