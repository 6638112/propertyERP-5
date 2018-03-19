package com.cnfantasia.server.api.plotproperty.domain;

import java.io.Serializable;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class WybBuyInfo implements Serializable {
	
	private static final long serialVersionUID = 2189616009891699790L;

	private Integer canBuyWyb; //能不能购买
	
	private Integer buyInfo; //1不能缴费 2能缴费，且无购买过物业宝 3能缴费且购买物业宝
	
	private Integer hasBuyed;
	
	private String url;
	
	private String freeCopy; //免缴方案

	public Integer getCanBuyWyb() {
		return canBuyWyb;
	}

	public void setCanBuyWyb(Integer canBuyWyb) {
		this.canBuyWyb = canBuyWyb;
	}

	public Integer getBuyInfo() {
		return buyInfo;
	}

	public void setBuyInfo(Integer buyInfo) {
		this.buyInfo = buyInfo;
	}

	public Integer getHasBuyed() {
		return hasBuyed;
	}

	public void setHasBuyed(Integer hasBuyed) {
		this.hasBuyed = hasBuyed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFreeCopy() {
		return freeCopy;
	}

	public void setFreeCopy(String freeCopy) {
		this.freeCopy = freeCopy;
	}

}
