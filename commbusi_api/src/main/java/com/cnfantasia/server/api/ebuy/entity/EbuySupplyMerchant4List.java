package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 运营后台 供应商列表 使用
 * @author wenfq 2016-03-31
 *
 */
public class EbuySupplyMerchant4List extends EbuySupplyMerchant {
	/**
	 * 注册人手机号
	 */
	String registMobile;
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public String getRegistMobile() {
		return registMobile;
	}

	public void setRegistMobile(String registMobile) {
		this.registMobile = registMobile;
	}

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	
}
