package com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(与供应商结算邮费规则) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class SupplyMerchantDeliveryFeeSettlement implements Serializable{
*/


public class SupplyMerchantDeliveryFeeSettlement extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuySupplyMerchantFId;	/** 运单金额(包含) */	private Long amountStart;	/** 运单金额(不包含) */	private Long amountEnd;	/** 结算邮费 */	private Long settlementFee;
	public SupplyMerchantDeliveryFeeSettlement(){
	}
	public SupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement arg){
		this.id = arg.getId();		this.tEbuySupplyMerchantFId = arg.gettEbuySupplyMerchantFId();		this.amountStart = arg.getAmountStart();		this.amountEnd = arg.getAmountEnd();		this.settlementFee = arg.getSettlementFee();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuySupplyMerchantFId 	 * @param amountStart 运单金额(包含)	 * @param amountEnd 运单金额(不包含)	 * @param settlementFee 结算邮费	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public SupplyMerchantDeliveryFeeSettlement(BigInteger id,BigInteger tEbuySupplyMerchantFId,Long amountStart,Long amountEnd,Long settlementFee,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;		this.amountStart = amountStart;		this.amountEnd = amountEnd;		this.settlementFee = settlementFee;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuySupplyMerchantFId=").append(tEbuySupplyMerchantFId).append(";");		sbf.append("amountStart=").append(amountStart).append(";");		sbf.append("amountEnd=").append(amountEnd).append(";");		sbf.append("settlementFee=").append(settlementFee).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuySupplyMerchantFId() {		return tEbuySupplyMerchantFId;	}	public void settEbuySupplyMerchantFId(BigInteger tEbuySupplyMerchantFId) {		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;	}	public Long getAmountStart() {		return amountStart;	}	public void setAmountStart(Long amountStart) {		this.amountStart = amountStart;	}	public Long getAmountEnd() {		return amountEnd;	}	public void setAmountEnd(Long amountEnd) {		this.amountEnd = amountEnd;	}	public Long getSettlementFee() {		return settlementFee;	}	public void setSettlementFee(Long settlementFee) {		this.settlementFee = settlementFee;	}
	
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
		SupplyMerchantDeliveryFeeSettlement other = (SupplyMerchantDeliveryFeeSettlement) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
