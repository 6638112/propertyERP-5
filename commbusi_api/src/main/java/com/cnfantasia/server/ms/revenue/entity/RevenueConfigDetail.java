package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 单个配置项信息
 * @author shiyl
 *
 */
public class RevenueConfigDetail extends RevenueConfig{
	private static final long serialVersionUID = 1L;
	/**物业公司名称*/
	private String companyName;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	private MinMaxDate minMaxDate;
	public MinMaxDate getMinMaxDate() {
		return minMaxDate;
	}
	public void setMinMaxDate(MinMaxDate minMaxDate) {
		this.minMaxDate = minMaxDate;
	}
	
}
