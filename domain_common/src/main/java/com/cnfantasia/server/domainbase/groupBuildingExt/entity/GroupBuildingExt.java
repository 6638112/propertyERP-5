package com.cnfantasia.server.domainbase.groupBuildingExt.entity;

/* */ import java.io.Serializable;/* */
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
/*  import com.cnfantasia.server.domain.pub.BaseEntity; */
/**
 * 描述:(小区信息扩展表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/* */
public class GroupBuildingExt implements Serializable{
/* */

/* 
public class GroupBuildingExt extends BaseEntity{
 */
	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 是否根据手机号自动验证门牌 */	private Integer validByMobile;	/** 是否开启定期收费配置={"0" */	private Integer fixedFeeStatus;	/** 是否开启抄表收费配置={"0" */	private Integer meterFeeStatus;	/** 是否开启临时收费配置={"0" */	private Integer tempFeeStatus;	/** 是否不开启寻求帮助={"0" */	private Integer cannotAskHelp;	/** 是否不开启银行托收={"0" */	private Integer openBankCollection;	/** 寻求帮助文字配置，分号隔开 */	private String askHelpPlacehole;	/** 是否支付到物业公司={"0" */	private Integer payToPc;
	public GroupBuildingExt(){
	}
	public GroupBuildingExt(GroupBuildingExt arg){
		this.id = arg.getId();		this.validByMobile = arg.getValidByMobile();		this.fixedFeeStatus = arg.getFixedFeeStatus();		this.meterFeeStatus = arg.getMeterFeeStatus();		this.tempFeeStatus = arg.getTempFeeStatus();		this.cannotAskHelp = arg.getCannotAskHelp();		this.openBankCollection = arg.getOpenBankCollection();		this.askHelpPlacehole = arg.getAskHelpPlacehole();		this.payToPc = arg.getPayToPc();
	}
	/**	 * 	 * @param id 	 * @param validByMobile 是否根据手机号自动验证门牌	 * @param fixedFeeStatus 是否开启定期收费配置={"0"	 * @param meterFeeStatus 是否开启抄表收费配置={"0"	 * @param tempFeeStatus 是否开启临时收费配置={"0"	 * @param cannotAskHelp 是否不开启寻求帮助={"0"	 * @param openBankCollection 是否不开启银行托收={"0"	 * @param askHelpPlacehole 寻求帮助文字配置，分号隔开	 * @param payToPc 是否支付到物业公司={"0"	 */
	public GroupBuildingExt(BigInteger id,Integer validByMobile,Integer fixedFeeStatus,Integer meterFeeStatus,Integer tempFeeStatus,Integer cannotAskHelp,Integer openBankCollection,String askHelpPlacehole,Integer payToPc){
		this.id = id;		this.validByMobile = validByMobile;		this.fixedFeeStatus = fixedFeeStatus;		this.meterFeeStatus = meterFeeStatus;		this.tempFeeStatus = tempFeeStatus;		this.cannotAskHelp = cannotAskHelp;		this.openBankCollection = openBankCollection;		this.askHelpPlacehole = askHelpPlacehole;		this.payToPc = payToPc;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("validByMobile=").append(validByMobile).append(";");		sbf.append("fixedFeeStatus=").append(fixedFeeStatus).append(";");		sbf.append("meterFeeStatus=").append(meterFeeStatus).append(";");		sbf.append("tempFeeStatus=").append(tempFeeStatus).append(";");		sbf.append("cannotAskHelp=").append(cannotAskHelp).append(";");		sbf.append("openBankCollection=").append(openBankCollection).append(";");		sbf.append("askHelpPlacehole=").append(askHelpPlacehole).append(";");		sbf.append("payToPc=").append(payToPc).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getValidByMobile() {		return validByMobile;	}	public void setValidByMobile(Integer validByMobile) {		this.validByMobile = validByMobile;	}	public Integer getFixedFeeStatus() {		return fixedFeeStatus;	}	public void setFixedFeeStatus(Integer fixedFeeStatus) {		this.fixedFeeStatus = fixedFeeStatus;	}	public Integer getMeterFeeStatus() {		return meterFeeStatus;	}	public void setMeterFeeStatus(Integer meterFeeStatus) {		this.meterFeeStatus = meterFeeStatus;	}	public Integer getTempFeeStatus() {		return tempFeeStatus;	}	public void setTempFeeStatus(Integer tempFeeStatus) {		this.tempFeeStatus = tempFeeStatus;	}	public Integer getCannotAskHelp() {		return cannotAskHelp;	}	public void setCannotAskHelp(Integer cannotAskHelp) {		this.cannotAskHelp = cannotAskHelp;	}	public Integer getOpenBankCollection() {		return openBankCollection;	}	public void setOpenBankCollection(Integer openBankCollection) {		this.openBankCollection = openBankCollection;	}	public String getAskHelpPlacehole() {		return askHelpPlacehole;	}	public void setAskHelpPlacehole(String askHelpPlacehole) {		this.askHelpPlacehole = askHelpPlacehole;	}	public Integer getPayToPc() {		return payToPc;	}	public void setPayToPc(Integer payToPc) {		this.payToPc = payToPc;	}
	
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
		GroupBuildingExt other = (GroupBuildingExt) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
