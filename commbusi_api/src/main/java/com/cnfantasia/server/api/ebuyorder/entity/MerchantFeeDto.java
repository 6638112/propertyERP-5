package com.cnfantasia.server.api.ebuyorder.entity;

/**
 * 供应商运费
 * 
 * @author liyulin
 * @version 1.0 2016年8月10日 下午5:35:06
 */
public class MerchantFeeDto {

	/** 描述 */
	private String desc;
	/** 运费 */
	private long fee;

	public MerchantFeeDto() {
		super();
	}

	public MerchantFeeDto(String desc, long fee) {
		super();
		this.desc = desc;
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "MerchantFeeDto [desc=" + desc + ", fee=" + fee + "]";
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

}
