package com.cnfantasia.server.api.dredge.entity;

import java.util.List;


public class DredgeMsgEntity {

	/**供应商姓名*/
	private String merchantName;
	/**供应商电话*/
	private String merchantTel;
	
	private List<DredgeBillHasEbuyProductShelfEnity> dredgeBillHasEbuyProductShelfList;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantTel() {
		return merchantTel;
	}

	public void setMerchantTel(String merchantTel) {
		this.merchantTel = merchantTel;
	}

	public List<DredgeBillHasEbuyProductShelfEnity> getDredgeBillHasEbuyProductShelfList() {
		return dredgeBillHasEbuyProductShelfList;
	}

	public void setDredgeBillHasEbuyProductShelfList(
			List<DredgeBillHasEbuyProductShelfEnity> dredgeBillHasEbuyProductShelfList) {
		this.dredgeBillHasEbuyProductShelfList = dredgeBillHasEbuyProductShelfList;
	}
}
