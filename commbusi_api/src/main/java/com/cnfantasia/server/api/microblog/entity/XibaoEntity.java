package com.cnfantasia.server.api.microblog.entity;

import java.util.Date;

/**  
* 类说明   
*  
* @author yewj  
* @time   2016年7月27日 下午4:14:37
*/
public class XibaoEntity {
	
	private String buildName;
	
	private Long buyerId;
	
	private Long hbAmount;
	
	private Boolean isFree;
	
	private Date paybillDate;
	
	private String paybillDateStr;

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getHbAmount() {
		return hbAmount;
	}

	public void setHbAmount(Long hbAmount) {
		this.hbAmount = hbAmount;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Date getPaybillDate() {
		return paybillDate;
	}

	public void setPaybillDate(Date paybillDate) {
		this.paybillDate = paybillDate;
	}

	public String getPaybillDateStr() {
		return paybillDateStr;
	}

	public void setPaybillDateStr(String paybillDateStr) {
		this.paybillDateStr = paybillDateStr;
	}
	
}
