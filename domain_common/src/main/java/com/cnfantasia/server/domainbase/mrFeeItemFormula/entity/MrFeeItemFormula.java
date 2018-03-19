package com.cnfantasia.server.domainbase.mrFeeItemFormula.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(抄表费收费项阶梯计算配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MrFeeItemFormula implements Serializable{
*/


public class MrFeeItemFormula extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tMrFeeItemFId;	/** 计算规则配置id */	private BigInteger tMrCalculateRuleCfgId;	/** 计算方式（1 */	private Integer calculateType;	/** 阶段开始值 */	private Double startValue;	/** 阶段结束值 */	private Double endValue;	/** 单价 */	private Double unitValue;
	public MrFeeItemFormula(){
	}
	public MrFeeItemFormula(MrFeeItemFormula arg){
		this.id = arg.getId();		this.tMrFeeItemFId = arg.gettMrFeeItemFId();		this.tMrCalculateRuleCfgId = arg.gettMrCalculateRuleCfgId();		this.calculateType = arg.getCalculateType();		this.startValue = arg.getStartValue();		this.endValue = arg.getEndValue();		this.unitValue = arg.getUnitValue();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tMrFeeItemFId 	 * @param tMrCalculateRuleCfgId 计算规则配置id	 * @param calculateType 计算方式（1	 * @param startValue 阶段开始值	 * @param endValue 阶段结束值	 * @param unitValue 单价	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MrFeeItemFormula(BigInteger id,BigInteger tMrFeeItemFId,BigInteger tMrCalculateRuleCfgId,Integer calculateType,Double startValue,Double endValue,Double unitValue,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tMrFeeItemFId = tMrFeeItemFId;		this.tMrCalculateRuleCfgId = tMrCalculateRuleCfgId;		this.calculateType = calculateType;		this.startValue = startValue;		this.endValue = endValue;		this.unitValue = unitValue;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tMrFeeItemFId=").append(tMrFeeItemFId).append(";");		sbf.append("tMrCalculateRuleCfgId=").append(tMrCalculateRuleCfgId).append(";");		sbf.append("calculateType=").append(calculateType).append(";");		sbf.append("startValue=").append(startValue).append(";");		sbf.append("endValue=").append(endValue).append(";");		sbf.append("unitValue=").append(unitValue).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettMrFeeItemFId() {		return tMrFeeItemFId;	}	public void settMrFeeItemFId(BigInteger tMrFeeItemFId) {		this.tMrFeeItemFId = tMrFeeItemFId;	}	public BigInteger gettMrCalculateRuleCfgId() {		return tMrCalculateRuleCfgId;	}	public void settMrCalculateRuleCfgId(BigInteger tMrCalculateRuleCfgId) {		this.tMrCalculateRuleCfgId = tMrCalculateRuleCfgId;	}	public Integer getCalculateType() {		return calculateType;	}	public void setCalculateType(Integer calculateType) {		this.calculateType = calculateType;	}	public Double getStartValue() {		return startValue;	}	public void setStartValue(Double startValue) {		this.startValue = startValue;	}	public Double getEndValue() {		return endValue;	}	public void setEndValue(Double endValue) {		this.endValue = endValue;	}	public Double getUnitValue() {		return unitValue;	}	public void setUnitValue(Double unitValue) {		this.unitValue = unitValue;	}
	
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
		MrFeeItemFormula other = (MrFeeItemFormula) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
