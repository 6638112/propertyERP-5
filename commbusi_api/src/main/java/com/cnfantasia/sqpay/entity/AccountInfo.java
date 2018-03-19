package com.cnfantasia.sqpay.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "account", "accountKind" })
public class AccountInfo {
	//就是你在乾多多注册的登录账号；
	@XmlAttribute 
	String account;
	//就是你的账号的类型，分为email和phone
	@XmlAttribute
	String accountKind;
	
	public AccountInfo() {
		// TODO Auto-generated constructor stub
	}

	public AccountInfo(String account, String accountKind) {
		super();
		this.account = account;
		this.accountKind = accountKind;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountKind() {
		return accountKind;
	}

	public void setAccountKind(String accountKind) {
		this.accountKind = accountKind;
	}

	@Override
	public String toString() {
		return "AccountInfo [account=" + account + ", accountKind=" + accountKind + "]";
	}
}
