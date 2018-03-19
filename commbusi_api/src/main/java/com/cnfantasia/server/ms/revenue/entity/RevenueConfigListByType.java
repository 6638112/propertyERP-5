package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;


/**
 * 某个类别的列表
 * @author shiyl
 *
 */
public class RevenueConfigListByType implements Serializable{
	private static final long serialVersionUID = 1L;
	/**物业公司Id*/
	private BigInteger companyId;
	/**收益项目类别*/
	private Integer projectType;
	/**配置项列表*/
	private List<RevenueConfig> revenueConfigList;
//	/**是最早起始日期*/
//	private Boolean dateFirst;
//	/**是最晚结束日期*/
//	private Boolean dateLast;
	
	public Integer getSubSize(){
		return revenueConfigList==null?0:revenueConfigList.size();
	}
	
//	public void addRevenueConfig(RevenueConfig revenueConfig) {
//		if(revenueConfigList==null){
//			revenueConfigList = new ArrayList<RevenueConfig>();
//		}
//		revenueConfigList.add(revenueConfig);
//	}
	
	public BigInteger getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}
	public Integer getProjectType() {
		return projectType;
	}
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	public List<RevenueConfig> getRevenueConfigList() {
		return revenueConfigList;
	}
	public void setRevenueConfigList(List<RevenueConfig> revenueConfigList) {
		this.revenueConfigList = revenueConfigList;
	}
	
}
