package com.cnfantasia.server.domainbase.payBillSplit.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Double;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(费用账单拆分表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayBillSplit implements Serializable{
*/


public class PayBillSplit extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属上级账单Id */	private BigInteger parentBillId;	/** 拆分的管理费金额 */	private Long manageAmount;	/** 名称描述 */	private String nameDesc;	/** 实际支付金额 */	private Long succPayAmount;	/** 优惠的折扣 */	private Double discount;	/** 折扣记录Id */	private BigInteger prizeRecordId;	/** 费用单拆分月份 */	private String billSubMonth;	/** 可用折扣月份 */	private String discountMonth;
	public PayBillSplit(){
	}
	public PayBillSplit(PayBillSplit arg){
		this.id = arg.getId();		this.parentBillId = arg.getParentBillId();		this.manageAmount = arg.getManageAmount();		this.nameDesc = arg.getNameDesc();		this.succPayAmount = arg.getSuccPayAmount();		this.discount = arg.getDiscount();		this.prizeRecordId = arg.getPrizeRecordId();		this.billSubMonth = arg.getBillSubMonth();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.discountMonth = arg.getDiscountMonth();
	}
	/**	 * 	 * @param id 	 * @param parentBillId 所属上级账单Id	 * @param manageAmount 拆分的管理费金额	 * @param nameDesc 名称描述	 * @param succPayAmount 实际支付金额	 * @param discount 优惠的折扣	 * @param prizeRecordId 折扣记录Id	 * @param billSubMonth 费用单拆分月份	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param discountMonth 可用折扣月份	 */
	public PayBillSplit(BigInteger id,BigInteger parentBillId,Long manageAmount,String nameDesc,Long succPayAmount,Double discount,BigInteger prizeRecordId,String billSubMonth,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String discountMonth){
		this.id = id;		this.parentBillId = parentBillId;		this.manageAmount = manageAmount;		this.nameDesc = nameDesc;		this.succPayAmount = succPayAmount;		this.discount = discount;		this.prizeRecordId = prizeRecordId;		this.billSubMonth = billSubMonth;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.discountMonth = discountMonth;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("parentBillId=").append(parentBillId).append(";");		sbf.append("manageAmount=").append(manageAmount).append(";");		sbf.append("nameDesc=").append(nameDesc).append(";");		sbf.append("succPayAmount=").append(succPayAmount).append(";");		sbf.append("discount=").append(discount).append(";");		sbf.append("prizeRecordId=").append(prizeRecordId).append(";");		sbf.append("billSubMonth=").append(billSubMonth).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("discountMonth=").append(discountMonth).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getParentBillId() {		return parentBillId;	}	public void setParentBillId(BigInteger parentBillId) {		this.parentBillId = parentBillId;	}	public Long getManageAmount() {		return manageAmount;	}	public void setManageAmount(Long manageAmount) {		this.manageAmount = manageAmount;	}	public String getNameDesc() {		return nameDesc;	}	public void setNameDesc(String nameDesc) {		this.nameDesc = nameDesc;	}	public Long getSuccPayAmount() {		return succPayAmount;	}	public void setSuccPayAmount(Long succPayAmount) {		this.succPayAmount = succPayAmount;	}	public Double getDiscount() {		return discount;	}	public void setDiscount(Double discount) {		this.discount = discount;	}	public BigInteger getPrizeRecordId() {		return prizeRecordId;	}	public void setPrizeRecordId(BigInteger prizeRecordId) {		this.prizeRecordId = prizeRecordId;	}	public String getBillSubMonth() {		return billSubMonth;	}	public void setBillSubMonth(String billSubMonth) {		this.billSubMonth = billSubMonth;	}	public String getDiscountMonth() {		return discountMonth;	}	public void setDiscountMonth(String discountMonth) {		this.discountMonth = discountMonth;	}
	
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
		PayBillSplit other = (PayBillSplit) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
