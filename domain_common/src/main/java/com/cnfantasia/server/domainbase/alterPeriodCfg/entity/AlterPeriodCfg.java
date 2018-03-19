package com.cnfantasia.server.domainbase.alterPeriodCfg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Double;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(选择周期配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AlterPeriodCfg implements Serializable{
*/


public class AlterPeriodCfg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区id */	private BigInteger tGbId;	/** 滞纳金利率 */	private Double latefeeRate;	/** 是否收取滞纳金（1收取，2不收取） */	private Integer latefeeStatus;	/** 滞纳金计算表达式 */	private String calculateExpression;
	public AlterPeriodCfg(){
	}
	public AlterPeriodCfg(AlterPeriodCfg arg){
		this.id = arg.getId();		this.tGbId = arg.gettGbId();		this.latefeeRate = arg.getLatefeeRate();		this.latefeeStatus = arg.getLatefeeStatus();		this.calculateExpression = arg.getCalculateExpression();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tGbId 小区id	 * @param latefeeRate 滞纳金利率	 * @param latefeeStatus 是否收取滞纳金（1收取，2不收取）	 * @param calculateExpression 滞纳金计算表达式	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public AlterPeriodCfg(BigInteger id,BigInteger tGbId,Double latefeeRate,Integer latefeeStatus,String calculateExpression,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tGbId = tGbId;		this.latefeeRate = latefeeRate;		this.latefeeStatus = latefeeStatus;		this.calculateExpression = calculateExpression;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGbId=").append(tGbId).append(";");		sbf.append("latefeeRate=").append(latefeeRate).append(";");		sbf.append("latefeeStatus=").append(latefeeStatus).append(";");		sbf.append("calculateExpression=").append(calculateExpression).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGbId() {		return tGbId;	}	public void settGbId(BigInteger tGbId) {		this.tGbId = tGbId;	}	public Double getLatefeeRate() {		return latefeeRate;	}	public void setLatefeeRate(Double latefeeRate) {		this.latefeeRate = latefeeRate;	}	public Integer getLatefeeStatus() {		return latefeeStatus;	}	public void setLatefeeStatus(Integer latefeeStatus) {		this.latefeeStatus = latefeeStatus;	}	public String getCalculateExpression() {		return calculateExpression;	}	public void setCalculateExpression(String calculateExpression) {		this.calculateExpression = calculateExpression;	}
	
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
		AlterPeriodCfg other = (AlterPeriodCfg) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
