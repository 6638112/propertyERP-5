package com.cnfantasia.server.api.ebuyorder.entity;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 供应商列表
 * 
 * @author liyulin
 * @version 1.0 2016年8月10日 下午7:02:23
 */
public class MerchantListDto extends EbuySupplyMerchant {
	
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public MerchantListDto() {
		super();
	}

	public MerchantListDto(String addMan, String updateMan) {
		super();
		this.addMan = addMan;
		this.updateMan = updateMan;
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
