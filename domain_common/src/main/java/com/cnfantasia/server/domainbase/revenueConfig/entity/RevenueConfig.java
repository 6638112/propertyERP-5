package com.cnfantasia.server.domainbase.revenueConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(收益规则配置表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RevenueConfig implements Serializable{
*/


public class RevenueConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 物业公司Id */	private BigInteger companyId;	/** 收益项目类别 */	private Integer projectType;	/** 规则开始时间 */	private String startDate;	/** 规则结束时间 */	private String endDate;	/** 规则类型 */	private Integer ruleType;	/** 收益规则计算总值=物业公司+代理+平台 */	private Double totalValue;	/** 维修师傅收益占比 */	private Double masterValue;	/** 物业公司收益占比 */	private Double companyValue;	/** 代理收益占比 */	private Double agentValue;	/** 解放区平台收益占比 */	private Double platformValue;	/**  */	private Double repairValue;	/** 推荐人收益 */	private Double recommenderValue;	/** 有效状态 */	private Integer activeStatus;	/** 被使用对应记录的最新时间 */	private String usedRecLasttime;
	public RevenueConfig(){
	}
	public RevenueConfig(RevenueConfig arg){
		this.id = arg.getId();		this.companyId = arg.getCompanyId();		this.projectType = arg.getProjectType();		this.startDate = arg.getStartDate();		this.endDate = arg.getEndDate();		this.ruleType = arg.getRuleType();		this.totalValue = arg.getTotalValue();		this.masterValue = arg.getMasterValue();		this.companyValue = arg.getCompanyValue();		this.agentValue = arg.getAgentValue();		this.platformValue = arg.getPlatformValue();		this.repairValue = arg.getRepairValue();		this.recommenderValue = arg.getRecommenderValue();		this.activeStatus = arg.getActiveStatus();		this.usedRecLasttime = arg.getUsedRecLasttime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param companyId 物业公司Id	 * @param projectType 收益项目类别	 * @param startDate 规则开始时间	 * @param endDate 规则结束时间	 * @param ruleType 规则类型	 * @param totalValue 收益规则计算总值=物业公司+代理+平台	 * @param masterValue 维修师傅收益占比	 * @param companyValue 物业公司收益占比	 * @param agentValue 代理收益占比	 * @param platformValue 解放区平台收益占比	 * @param repairValue 	 * @param recommenderValue 推荐人收益	 * @param activeStatus 有效状态	 * @param usedRecLasttime 被使用对应记录的最新时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RevenueConfig(BigInteger id,BigInteger companyId,Integer projectType,String startDate,String endDate,Integer ruleType,Double totalValue,Double masterValue,Double companyValue,Double agentValue,Double platformValue,Double repairValue,Double recommenderValue,Integer activeStatus,String usedRecLasttime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.companyId = companyId;		this.projectType = projectType;		this.startDate = startDate;		this.endDate = endDate;		this.ruleType = ruleType;		this.totalValue = totalValue;		this.masterValue = masterValue;		this.companyValue = companyValue;		this.agentValue = agentValue;		this.platformValue = platformValue;		this.repairValue = repairValue;		this.recommenderValue = recommenderValue;		this.activeStatus = activeStatus;		this.usedRecLasttime = usedRecLasttime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("companyId=").append(companyId).append(";");		sbf.append("projectType=").append(projectType).append(";");		sbf.append("startDate=").append(startDate).append(";");		sbf.append("endDate=").append(endDate).append(";");		sbf.append("ruleType=").append(ruleType).append(";");		sbf.append("totalValue=").append(totalValue).append(";");		sbf.append("masterValue=").append(masterValue).append(";");		sbf.append("companyValue=").append(companyValue).append(";");		sbf.append("agentValue=").append(agentValue).append(";");		sbf.append("platformValue=").append(platformValue).append(";");		sbf.append("repairValue=").append(repairValue).append(";");		sbf.append("recommenderValue=").append(recommenderValue).append(";");		sbf.append("activeStatus=").append(activeStatus).append(";");		sbf.append("usedRecLasttime=").append(usedRecLasttime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getCompanyId() {		return companyId;	}	public void setCompanyId(BigInteger companyId) {		this.companyId = companyId;	}	public Integer getProjectType() {		return projectType;	}	public void setProjectType(Integer projectType) {		this.projectType = projectType;	}	public String getStartDate() {		return startDate;	}	public void setStartDate(String startDate) {		this.startDate = startDate;	}	public String getEndDate() {		return endDate;	}	public void setEndDate(String endDate) {		this.endDate = endDate;	}	public Integer getRuleType() {		return ruleType;	}	public void setRuleType(Integer ruleType) {		this.ruleType = ruleType;	}	public Double getTotalValue() {		return totalValue;	}	public void setTotalValue(Double totalValue) {		this.totalValue = totalValue;	}	public Double getMasterValue() {		return masterValue;	}	public void setMasterValue(Double masterValue) {		this.masterValue = masterValue;	}	public Double getCompanyValue() {		return companyValue;	}	public void setCompanyValue(Double companyValue) {		this.companyValue = companyValue;	}	public Double getAgentValue() {		return agentValue;	}	public void setAgentValue(Double agentValue) {		this.agentValue = agentValue;	}	public Double getPlatformValue() {		return platformValue;	}	public void setPlatformValue(Double platformValue) {		this.platformValue = platformValue;	}	public Double getRepairValue() {		return repairValue;	}	public void setRepairValue(Double repairValue) {		this.repairValue = repairValue;	}	public Double getRecommenderValue() {		return recommenderValue;	}	public void setRecommenderValue(Double recommenderValue) {		this.recommenderValue = recommenderValue;	}	public Integer getActiveStatus() {		return activeStatus;	}	public void setActiveStatus(Integer activeStatus) {		this.activeStatus = activeStatus;	}	public String getUsedRecLasttime() {		return usedRecLasttime;	}	public void setUsedRecLasttime(String usedRecLasttime) {		this.usedRecLasttime = usedRecLasttime;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueConfig other = (RevenueConfig) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
