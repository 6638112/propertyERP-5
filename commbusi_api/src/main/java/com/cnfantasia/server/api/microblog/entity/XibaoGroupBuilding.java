package com.cnfantasia.server.api.microblog.entity;  
/**  
* 类说明   
*  
* @author yewj  
* @time   2016年7月27日 下午5:05:01
*/
public class XibaoGroupBuilding {
	
	private String gbName;
	
	private String pbDateStr; //2016-07
	
	private Integer userCount; //优惠用户数
	
	private Long maxHbAmount; //最大减免
	
	private Long totalHbAmount; //总共减免

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getPbDateStr() {
		return pbDateStr;
	}

	public void setPbDateStr(String pbDateStr) {
		this.pbDateStr = pbDateStr;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Long getMaxHbAmount() {
		return maxHbAmount;
	}

	public void setMaxHbAmount(Long maxHbAmount) {
		this.maxHbAmount = maxHbAmount;
	}

	public Long getTotalHbAmount() {
		return totalHbAmount;
	}

	public void setTotalHbAmount(Long totalHbAmount) {
		this.totalHbAmount = totalHbAmount;
	}
	
	public String getMonth() {
		if(pbDateStr != null) {
			return pbDateStr.substring(pbDateStr.indexOf("-") + 1);
		}
		return "";
	}

}
