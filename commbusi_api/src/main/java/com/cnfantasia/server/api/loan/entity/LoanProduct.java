package com.cnfantasia.server.api.loan.entity;

import java.math.BigInteger;

/**
 * 借贷产品信息
 * 
 * @author liyulin
 * @version 1.0 2017年6月5日 下午3:28:05
 */
public class LoanProduct {
	private BigInteger lpId;
	private String picUrl;
	private String lpName;
	private String lpDesc;
	private String maxLoanDesc;
	private String rateDesc;
	private String expire;
	private String code;

	public BigInteger getLpId() {
		return lpId;
	}

	public void setLpId(BigInteger lpId) {
		this.lpId = lpId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLpName() {
		return lpName;
	}

	public void setLpName(String lpName) {
		this.lpName = lpName;
	}

	public String getLpDesc() {
		return lpDesc;
	}

	public void setLpDesc(String lpDesc) {
		this.lpDesc = lpDesc;
	}

	public String getMaxLoanDesc() {
		return maxLoanDesc;
	}

	public void setMaxLoanDesc(String maxLoanDesc) {
		this.maxLoanDesc = maxLoanDesc;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	public void setRateDesc(String rateDesc) {
		this.rateDesc = rateDesc;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
