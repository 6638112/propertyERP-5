package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.math.BigInteger;

/**
 * @ClassName: GroupBuildingCfgEntity
 * @Date: 2017-01-18 13:33
 * @Auther: yanghua
 * @Description:(小区缴费权限配置)
 */
public class GroupBuildingCfgEntity {
	private BigInteger id;
	/** 是否可缴纳物业费 */
	private Integer propfeeCanpay;
	/** 是否开启缴费校验 */
	private Integer verificationStatus;
	/** 是否有缴费优惠（1有，0没有） */
	private Integer isPrefer;
	/** 周期缴费方式（1固定周期，2选择周期） */
	private Integer propertyPeriodType;
	/** 是否开启定期收费配置={"0" */
	private Integer fixedFeeStatus;
	/** 是否开启抄表收费配置={"0" */
	private Integer meterFeeStatus;
	/** 是否开启临时收费配置={"0" */
	private Integer tempFeeStatus;
	/** 是否不开启银行托收={"0":"否","1":"是"} */
	private Integer openBankCollection;
	/** 是否直接付款到物业公司 */
	private int isPayToPc;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getPropfeeCanpay() {
		if (propfeeCanpay == null) {
			propfeeCanpay = 2;
		}
		return propfeeCanpay;
	}

	public void setPropfeeCanpay(Integer propfeeCanpay) {
		this.propfeeCanpay = propfeeCanpay;
	}

	public Integer getVerificationStatus() {
		if (verificationStatus == null) {
			verificationStatus = 0;
		}
		return verificationStatus;
	}

	public void setVerificationStatus(Integer verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public Integer getIsPrefer() {
		if (isPrefer == null) {
			isPrefer = 0;
		}
		return isPrefer;
	}

	public void setIsPrefer(Integer isPrefer) {
		this.isPrefer = isPrefer;
	}

	public Integer getPropertyPeriodType() {
		if (propertyPeriodType == null) {
			propertyPeriodType = 1;
		}
		return propertyPeriodType;
	}

	public void setPropertyPeriodType(Integer propertyPeriodType) {
		this.propertyPeriodType = propertyPeriodType;
	}

	public Integer getFixedFeeStatus() {
		if (fixedFeeStatus == null) {
			fixedFeeStatus = 0;
		}
		return fixedFeeStatus;
	}

	public void setFixedFeeStatus(Integer fixedFeeStatus) {
		this.fixedFeeStatus = fixedFeeStatus;
	}

	public Integer getMeterFeeStatus() {
		if (meterFeeStatus == null) {
			meterFeeStatus = 0;
		}
		return meterFeeStatus;
	}

	public void setMeterFeeStatus(Integer meterFeeStatus) {
		this.meterFeeStatus = meterFeeStatus;
	}

	public Integer getTempFeeStatus() {
		if (tempFeeStatus == null) {
			tempFeeStatus = 0;
		}
		return tempFeeStatus;
	}

	public int getIsPayToPc() {
		return isPayToPc;
	}

	public void setIsPayToPc(int isPayToPc) {
		this.isPayToPc = isPayToPc;
	}

	public void setTempFeeStatus(Integer tempFeeStatus) {
		this.tempFeeStatus = tempFeeStatus;
	}

	public Integer getOpenBankCollection() {
		return openBankCollection;
	}

	public void setOpenBankCollection(Integer openBankCollection) {
		this.openBankCollection = openBankCollection;
	}

}
