package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.springframework.util.StringUtils;

import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

/**
 * 业主托收信息，带账单信息
 * 
 * @author wenfq
 *
 */
public class PPBankCollectionWithPayBill extends PropertyProprietorBankCollectionInfo {
	BigInteger pbId;//账单ID
	String shortCode;//账单短编码
	BigDecimal pbAmount;//账单金额
	String bankNumber;//银行代号
	/** 业主姓名 */
	private String ppName;
	/** 业主联系电话 */
	private String ppPhone;
	
	public String getPpName() {
		return ppName;
	}
	public void setPpName(String ppName) {
		this.ppName = ppName;
	}
	public String getPpPhone() {
		return ppPhone;
	}
	public void setPpPhone(String ppPhone) {
		this.ppPhone = ppPhone;
	}
	
	/**
	 * 如果是空串，返回"00"
	 * @return
	 */
	public String getBankNumber() {
		return StringUtils.isEmpty(bankNumber) ? "00" : bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public BigInteger getPbId() {
		return pbId;
	}
	public void setPbId(BigInteger pbId) {
		this.pbId = pbId;
	}
	public BigDecimal getPbAmount() {
		return pbAmount;
	}
	
	/**
	 * 获取账单金额
	 * @return
	 */
	public String getPbAmountStr() {
		return pbAmount.divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_UP).toString();
	}
	
	public void setPbAmount(BigDecimal pbAmount) {
		this.pbAmount = pbAmount;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
}
