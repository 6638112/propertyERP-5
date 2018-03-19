package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业代扣卡划扣详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyCardDeductionDetail implements Serializable{
*/


public class PropertyCardDeductionDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tUserHasPropertyCardFId;	/**  */	private BigInteger tPayBillFId;	/**  */	private BigInteger tEbuyOrderFId;	/** 账单金额 */	private Long payBillAmt;	/** 平台补贴金额 */	private Long ptbtAmt;	/** 用户实付金额 */	private Long realPayAmt;	/** 订单流水号 */	private String orderNo;
	public PropertyCardDeductionDetail(){
	}
	public PropertyCardDeductionDetail(PropertyCardDeductionDetail arg){
		this.id = arg.getId();		this.tUserHasPropertyCardFId = arg.gettUserHasPropertyCardFId();		this.tPayBillFId = arg.gettPayBillFId();		this.tEbuyOrderFId = arg.gettEbuyOrderFId();		this.payBillAmt = arg.getPayBillAmt();		this.ptbtAmt = arg.getPtbtAmt();		this.realPayAmt = arg.getRealPayAmt();		this.orderNo = arg.getOrderNo();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tUserHasPropertyCardFId 	 * @param tPayBillFId 	 * @param tEbuyOrderFId 	 * @param payBillAmt 账单金额	 * @param ptbtAmt 平台补贴金额	 * @param realPayAmt 用户实付金额	 * @param orderNo 订单流水号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyCardDeductionDetail(BigInteger id,BigInteger tUserHasPropertyCardFId,BigInteger tPayBillFId,BigInteger tEbuyOrderFId,Long payBillAmt,Long ptbtAmt,Long realPayAmt,String orderNo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tUserHasPropertyCardFId = tUserHasPropertyCardFId;		this.tPayBillFId = tPayBillFId;		this.tEbuyOrderFId = tEbuyOrderFId;		this.payBillAmt = payBillAmt;		this.ptbtAmt = ptbtAmt;		this.realPayAmt = realPayAmt;		this.orderNo = orderNo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tUserHasPropertyCardFId=").append(tUserHasPropertyCardFId).append(";");		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");		sbf.append("tEbuyOrderFId=").append(tEbuyOrderFId).append(";");		sbf.append("payBillAmt=").append(payBillAmt).append(";");		sbf.append("ptbtAmt=").append(ptbtAmt).append(";");		sbf.append("realPayAmt=").append(realPayAmt).append(";");		sbf.append("orderNo=").append(orderNo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettUserHasPropertyCardFId() {		return tUserHasPropertyCardFId;	}	public void settUserHasPropertyCardFId(BigInteger tUserHasPropertyCardFId) {		this.tUserHasPropertyCardFId = tUserHasPropertyCardFId;	}	public BigInteger gettPayBillFId() {		return tPayBillFId;	}	public void settPayBillFId(BigInteger tPayBillFId) {		this.tPayBillFId = tPayBillFId;	}	public BigInteger gettEbuyOrderFId() {		return tEbuyOrderFId;	}	public void settEbuyOrderFId(BigInteger tEbuyOrderFId) {		this.tEbuyOrderFId = tEbuyOrderFId;	}	public Long getPayBillAmt() {		return payBillAmt;	}	public void setPayBillAmt(Long payBillAmt) {		this.payBillAmt = payBillAmt;	}	public Long getPtbtAmt() {		return ptbtAmt;	}	public void setPtbtAmt(Long ptbtAmt) {		this.ptbtAmt = ptbtAmt;	}	public Long getRealPayAmt() {		return realPayAmt;	}	public void setRealPayAmt(Long realPayAmt) {		this.realPayAmt = realPayAmt;	}	public String getOrderNo() {		return orderNo;	}	public void setOrderNo(String orderNo) {		this.orderNo = orderNo;	}
	
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
		PropertyCardDeductionDetail other = (PropertyCardDeductionDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
