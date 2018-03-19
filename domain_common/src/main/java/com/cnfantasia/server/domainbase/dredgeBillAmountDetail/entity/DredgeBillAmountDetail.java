package com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(上门维修费用明细) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBillAmountDetail implements Serializable{
*/


public class DredgeBillAmountDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 费用类型 */	private Integer feeType;	/** 费用名称（冗余） */	private String feeName;	/** 金额 */	private Long payAmount;	/** 是否包含平台费 */	private Integer isIncludePlatformFee;	/**  */	private BigInteger tDredgeBillFId;
	public DredgeBillAmountDetail(){
	}
	public DredgeBillAmountDetail(DredgeBillAmountDetail arg){
		this.id = arg.getId();		this.feeType = arg.getFeeType();		this.feeName = arg.getFeeName();		this.payAmount = arg.getPayAmount();		this.isIncludePlatformFee = arg.getIsIncludePlatformFee();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tDredgeBillFId = arg.gettDredgeBillFId();
	}
	/**	 * 	 * @param id 	 * @param feeType 费用类型	 * @param feeName 费用名称（冗余）	 * @param payAmount 金额	 * @param isIncludePlatformFee 是否包含平台费	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tDredgeBillFId 	 */
	public DredgeBillAmountDetail(BigInteger id,Integer feeType,String feeName,Long payAmount,Integer isIncludePlatformFee,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tDredgeBillFId){
		this.id = id;		this.feeType = feeType;		this.feeName = feeName;		this.payAmount = payAmount;		this.isIncludePlatformFee = isIncludePlatformFee;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tDredgeBillFId = tDredgeBillFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("feeType=").append(feeType).append(";");		sbf.append("feeName=").append(feeName).append(";");		sbf.append("payAmount=").append(payAmount).append(";");		sbf.append("isIncludePlatformFee=").append(isIncludePlatformFee).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getFeeType() {		return feeType;	}	public void setFeeType(Integer feeType) {		this.feeType = feeType;	}	public String getFeeName() {		return feeName;	}	public void setFeeName(String feeName) {		this.feeName = feeName;	}	public Long getPayAmount() {		return payAmount;	}	public void setPayAmount(Long payAmount) {		this.payAmount = payAmount;	}	public Integer getIsIncludePlatformFee() {		return isIncludePlatformFee;	}	public void setIsIncludePlatformFee(Integer isIncludePlatformFee) {		this.isIncludePlatformFee = isIncludePlatformFee;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}
	
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
		DredgeBillAmountDetail other = (DredgeBillAmountDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
