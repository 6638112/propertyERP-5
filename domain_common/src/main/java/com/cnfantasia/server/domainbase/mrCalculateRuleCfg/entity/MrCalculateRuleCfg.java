package com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(抄表计算规则配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MrCalculateRuleCfg implements Serializable{
*/


public class MrCalculateRuleCfg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 计费规则名称 */	private String name;	/** 定价方式（1 */	private Integer calculatePriceType;	/** 计算规则（1 */	private Integer calculateRule;	/** 计算配置对应的抄表项id（为使两种方式统一，所以非空） */	private BigInteger tMrFeeItemId;	/** 一户多表时的计算方式（1 */	private Integer moreMrCalculateType;	/** 一户多表计算百分比（单表为默认的1.00） */	private Double moreMrCalculatePercent;	/** 根据哪个费用项进行计算（多表） */	private BigInteger basisItemId;
	public MrCalculateRuleCfg(){
	}
	public MrCalculateRuleCfg(MrCalculateRuleCfg arg){
		this.id = arg.getId();		this.name = arg.getName();		this.calculatePriceType = arg.getCalculatePriceType();		this.calculateRule = arg.getCalculateRule();		this.tMrFeeItemId = arg.gettMrFeeItemId();		this.moreMrCalculateType = arg.getMoreMrCalculateType();		this.moreMrCalculatePercent = arg.getMoreMrCalculatePercent();		this.basisItemId = arg.getBasisItemId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 计费规则名称	 * @param calculatePriceType 定价方式（1	 * @param calculateRule 计算规则（1	 * @param tMrFeeItemId 计算配置对应的抄表项id（为使两种方式统一，所以非空）	 * @param moreMrCalculateType 一户多表时的计算方式（1	 * @param moreMrCalculatePercent 一户多表计算百分比（单表为默认的1.00）	 * @param basisItemId 根据哪个费用项进行计算（多表）	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public MrCalculateRuleCfg(BigInteger id,String name,Integer calculatePriceType,Integer calculateRule,BigInteger tMrFeeItemId,Integer moreMrCalculateType,Double moreMrCalculatePercent,BigInteger basisItemId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.calculatePriceType = calculatePriceType;		this.calculateRule = calculateRule;		this.tMrFeeItemId = tMrFeeItemId;		this.moreMrCalculateType = moreMrCalculateType;		this.moreMrCalculatePercent = moreMrCalculatePercent;		this.basisItemId = basisItemId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("calculatePriceType=").append(calculatePriceType).append(";");		sbf.append("calculateRule=").append(calculateRule).append(";");		sbf.append("tMrFeeItemId=").append(tMrFeeItemId).append(";");		sbf.append("moreMrCalculateType=").append(moreMrCalculateType).append(";");		sbf.append("moreMrCalculatePercent=").append(moreMrCalculatePercent).append(";");		sbf.append("basisItemId=").append(basisItemId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Integer getCalculatePriceType() {		return calculatePriceType;	}	public void setCalculatePriceType(Integer calculatePriceType) {		this.calculatePriceType = calculatePriceType;	}	public Integer getCalculateRule() {		return calculateRule;	}	public void setCalculateRule(Integer calculateRule) {		this.calculateRule = calculateRule;	}	public BigInteger gettMrFeeItemId() {		return tMrFeeItemId;	}	public void settMrFeeItemId(BigInteger tMrFeeItemId) {		this.tMrFeeItemId = tMrFeeItemId;	}	public Integer getMoreMrCalculateType() {		return moreMrCalculateType;	}	public void setMoreMrCalculateType(Integer moreMrCalculateType) {		this.moreMrCalculateType = moreMrCalculateType;	}	public Double getMoreMrCalculatePercent() {		return moreMrCalculatePercent;	}	public void setMoreMrCalculatePercent(Double moreMrCalculatePercent) {		this.moreMrCalculatePercent = moreMrCalculatePercent;	}	public BigInteger getBasisItemId() {		return basisItemId;	}	public void setBasisItemId(BigInteger basisItemId) {		this.basisItemId = basisItemId;	}
	
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
		MrCalculateRuleCfg other = (MrCalculateRuleCfg) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
