package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 最早最晚时间
 * @author shiyl
 *
 */
public class MinMaxDate implements Serializable{
	private static final long serialVersionUID = 1L;
	/**物业公司Id*/
	private BigInteger companyId;
	/**收益项目类别*/
	private Integer projectType;
	/**最早开始时间*/
	private String minStartDate;
	/**最晚结束时间*/
	private String maxEndDate;
	
	public static enum Location{
		LEFT,MIDDLE,RIGHT//(~,a),[a,b],(b,~)
	}
	
	public static boolean isFirst(MinMaxDate minMaxDate,RevenueConfig detail){
		if(detail==null){throw new BusinessRuntimeException("MinMaxDate.isFirst.RevenueConfig.null");}
		if(minMaxDate==null){
			return true;
		}else if(DateUtil.distance(detail.getStartDate(),DateUtil.formatSecond.get(), minMaxDate.getMinStartDate(),DateUtil.formatDay.get())==0){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isLast(MinMaxDate minMaxDate,RevenueConfig detail){
		if(detail==null){throw new BusinessRuntimeException("MinMaxDate.isLast.RevenueConfig.null");}
		if(minMaxDate==null){
			return true;
		}else if(DateUtil.distance(detail.getEndDate(),DateUtil.formatSecond.get(), minMaxDate.getMaxEndDate(),DateUtil.formatDay.get())==0){
			return true;
		}else{
			return false;
		}
	}
	
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
	public String getMinStartDate() {
		return minStartDate;
	}
	public void setMinStartDate(String minStartDate) {
		this.minStartDate = minStartDate;
	}
	public String getMaxEndDate() {
		return maxEndDate;
	}
	public void setMaxEndDate(String maxEndDate) {
		this.maxEndDate = maxEndDate;
	}
}
