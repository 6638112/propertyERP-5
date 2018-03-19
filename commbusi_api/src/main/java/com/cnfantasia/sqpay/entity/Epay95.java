package com.cnfantasia.sqpay.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "Epay95", propOrder = { "accountInfo", "transferInfo" })
public class Epay95 {
	
	@XmlElement(name="AccountInfo")
	AccountInfo accountInfo;
	
	@XmlElement(name="TransferInfo")
	TransferInfo transferInfo;

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public TransferInfo getTransferInfo() {
		return transferInfo;
	}

	public void setTransferInfo(TransferInfo transferInfo) {
		this.transferInfo = transferInfo;
	}

	@Override
	public String toString() {
		return "Epay95 [accountInfo=" + accountInfo + ", transferInfo=" + transferInfo + "]";
	}
	
	
}
