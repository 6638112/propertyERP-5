package com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业代扣卡交易流水) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCardTransactionFlowing implements Serializable{
*/


public class PropertyCardTransactionFlowing extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 交易流水号 */	private String transNo;	/** 流水金额 */	private Long transAmt;	/** 交易类型 */	private Integer transType;	/** 流水描述 */	private String description;	/** 交易流水时间 */	private String transTime;	/**  */	private BigInteger tUserFId;	/** 订单id */	private BigInteger tEbuyOrderFId;	/**  */	private BigInteger tPayBillFId;
	public PropertyCardTransactionFlowing(){
	}
	public PropertyCardTransactionFlowing(PropertyCardTransactionFlowing arg){
		this.id = arg.getId();		this.transNo = arg.getTransNo();		this.transAmt = arg.getTransAmt();		this.transType = arg.getTransType();		this.description = arg.getDescription();		this.transTime = arg.getTransTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tUserFId = arg.gettUserFId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.tPayBillFId = arg.gettPayBillFId();
	}
	/**	 * 	 * @param id 	 * @param transNo 交易流水号	 * @param transAmt 流水金额	 * @param transType 交易类型	 * @param description 流水描述	 * @param transTime 交易流水时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tUserFId 	 * @param tEbuyOrderFId 订单id	 * @param tPayBillFId 	 */
	public PropertyCardTransactionFlowing(BigInteger id,String transNo,Long transAmt,Integer transType,String description,String transTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tUserFId,BigInteger tEbuyOrderFId,BigInteger tPayBillFId){
		this.id = id;		this.transNo = transNo;		this.transAmt = transAmt;		this.transType = transType;		this.description = description;		this.transTime = transTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tUserFId = tUserFId;		this.tEbuyOrderFId = tEbuyOrderFId;		this.tPayBillFId = tPayBillFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("transNo=").append(transNo).append(";");		sbf.append("transAmt=").append(transAmt).append(";");		sbf.append("transType=").append(transType).append(";");		sbf.append("description=").append(description).append(";");		sbf.append("transTime=").append(transTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getTransNo() {		return transNo;	}	public void setTransNo(String transNo) {		this.transNo = transNo;	}	public Long getTransAmt() {		return transAmt;	}	public void setTransAmt(Long transAmt) {		this.transAmt = transAmt;	}	public Integer getTransType() {		return transType;	}	public void setTransType(Integer transType) {		this.transType = transType;	}	public String getDescription() {		return description;	}	public void setDescription(String description) {		this.description = description;	}	public String getTransTime() {		return transTime;	}	public void setTransTime(String transTime) {		this.transTime = transTime;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public BigInteger gettPayBillFId() {		return tPayBillFId;	}	public void settPayBillFId(BigInteger tPayBillFId) {		this.tPayBillFId = tPayBillFId;	}
	
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
		PropertyCardTransactionFlowing other = (PropertyCardTransactionFlowing) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
