package com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业代扣卡优惠方案) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCardDiscountTerm implements Serializable{
*/


public class PropertyCardDiscountTerm extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 目标金额 */	private Long goalAmt;	/** 达到目标金额送出的金额 */	private Long sendAmt;	/** 优惠方案描述 */	private String termDescription;
	public PropertyCardDiscountTerm(){
	}
	public PropertyCardDiscountTerm(PropertyCardDiscountTerm arg){
		this.id = arg.getId();		this.goalAmt = arg.getGoalAmt();		this.sendAmt = arg.getSendAmt();		this.termDescription = arg.getTermDescription();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param goalAmt 目标金额	 * @param sendAmt 达到目标金额送出的金额	 * @param termDescription 优惠方案描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyCardDiscountTerm(BigInteger id,Long goalAmt,Long sendAmt,String termDescription,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.goalAmt = goalAmt;		this.sendAmt = sendAmt;		this.termDescription = termDescription;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("goalAmt=").append(goalAmt).append(";");		sbf.append("sendAmt=").append(sendAmt).append(";");		sbf.append("termDescription=").append(termDescription).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getGoalAmt() {		return goalAmt;	}	public void setGoalAmt(Long goalAmt) {		this.goalAmt = goalAmt;	}	public Long getSendAmt() {		return sendAmt;	}	public void setSendAmt(Long sendAmt) {		this.sendAmt = sendAmt;	}	public String getTermDescription() {		return termDescription;	}	public void setTermDescription(String termDescription) {		this.termDescription = termDescription;	}
	
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
		PropertyCardDiscountTerm other = (PropertyCardDiscountTerm) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
