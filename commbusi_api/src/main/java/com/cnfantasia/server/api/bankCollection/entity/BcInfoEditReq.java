package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;
import java.util.Set;

public class BcInfoEditReq {

	private String[] gdIds;
	private String[] ppbciIds;
	private Integer collectionRange;
	private String no;
	private String contractNo;
	private String bankOrg;
	private String bankOrgName;
	private String bankAccount;
	private String pcbciId;
	private BigInteger pcId;
	private Set<String> gbIdSet;

	public String[] getGdIds() {
		return gdIds;
	}

	public void setGdIds(String[] gdIds) {
		this.gdIds = gdIds;
	}

	public String[] getPpbciIds() {
		return ppbciIds;
	}

	public void setPpbciIds(String[] ppbciIds) {
		this.ppbciIds = ppbciIds;
	}

	public Integer getCollectionRange() {
		return collectionRange;
	}

	public void setCollectionRange(Integer collectionRange) {
		this.collectionRange = collectionRange;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBankOrg() {
		return bankOrg;
	}

	public void setBankOrg(String bankOrg) {
		this.bankOrg = bankOrg;
	}

	public String getBankOrgName() {
		return bankOrgName;
	}

	public void setBankOrgName(String bankOrgName) {
		this.bankOrgName = bankOrgName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getPcbciId() {
		return pcbciId;
	}

	public void setPcbciId(String pcbciId) {
		this.pcbciId = pcbciId;
	}

	public BigInteger getPcId() {
		return pcId;
	}

	public void setPcId(BigInteger pcId) {
		this.pcId = pcId;
	}

	public Set<String> getGbIdSet() {
		return gbIdSet;
	}

	public void setGbIdSet(Set<String> gbIdSet) {
		this.gbIdSet = gbIdSet;
	}

}
