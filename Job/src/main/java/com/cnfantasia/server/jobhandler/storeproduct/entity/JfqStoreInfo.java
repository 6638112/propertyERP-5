package com.cnfantasia.server.jobhandler.storeproduct.entity;

import java.io.Serializable;

public class JfqStoreInfo implements Serializable {

	private String sellerid;
	private String username;
	private String shopname;
	private String paymentname;

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPaymentname() {
		return paymentname;
	}

	public void setPaymentname(String paymentname) {
		this.paymentname = paymentname;
	}

	@Override
	public String toString() {
		return "JfqStoreInfo [sellerid=" + sellerid + ", username=" + username + ", shopname=" + shopname
				+ ", paymentname=" + paymentname + "]";
	}

}
