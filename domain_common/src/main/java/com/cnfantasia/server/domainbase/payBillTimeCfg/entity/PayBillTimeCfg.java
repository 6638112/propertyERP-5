package com.cnfantasia.server.domainbase.payBillTimeCfg.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(账单类型对应缴费时间配置) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayBillTimeCfg implements Serializable{
*/


public class PayBillTimeCfg extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账单类型Id */	private BigInteger billTypeId;	/** 小区Id */	private BigInteger gbId;	/** 账单月份长度 */	private Long billMonthSize;	/** 费用单开始时间,同一小区时间段不可重叠 */	private String billMonthStart;	/** 费用单截止时间,同一小区时间段不可重叠 */	private String billMonthEnd;	/** 缴费开始日期 */	private String payDayStart;	/** 缴费截止日期 */	private String payDayEnd;
	public PayBillTimeCfg(){
	}
	public PayBillTimeCfg(PayBillTimeCfg arg){
		this.id = arg.getId();		this.billTypeId = arg.getBillTypeId();		this.gbId = arg.getGbId();		this.billMonthSize = arg.getBillMonthSize();		this.billMonthStart = arg.getBillMonthStart();		this.billMonthEnd = arg.getBillMonthEnd();		this.payDayStart = arg.getPayDayStart();		this.payDayEnd = arg.getPayDayEnd();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param billTypeId 账单类型Id	 * @param gbId 小区Id	 * @param billMonthSize 账单月份长度	 * @param billMonthStart 费用单开始时间,同一小区时间段不可重叠	 * @param billMonthEnd 费用单截止时间,同一小区时间段不可重叠	 * @param payDayStart 缴费开始日期	 * @param payDayEnd 缴费截止日期	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PayBillTimeCfg(BigInteger id,BigInteger billTypeId,BigInteger gbId,Long billMonthSize,String billMonthStart,String billMonthEnd,String payDayStart,String payDayEnd,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.billTypeId = billTypeId;		this.gbId = gbId;		this.billMonthSize = billMonthSize;		this.billMonthStart = billMonthStart;		this.billMonthEnd = billMonthEnd;		this.payDayStart = payDayStart;		this.payDayEnd = payDayEnd;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("billTypeId=").append(billTypeId).append(";");		sbf.append("gbId=").append(gbId).append(";");		sbf.append("billMonthSize=").append(billMonthSize).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("billMonthEnd=").append(billMonthEnd).append(";");		sbf.append("payDayStart=").append(payDayStart).append(";");		sbf.append("payDayEnd=").append(payDayEnd).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getBillTypeId() {		return billTypeId;	}	public void setBillTypeId(BigInteger billTypeId) {		this.billTypeId = billTypeId;	}	public BigInteger getGbId() {		return gbId;	}	public void setGbId(BigInteger gbId) {		this.gbId = gbId;	}	public Long getBillMonthSize() {		return billMonthSize;	}	public void setBillMonthSize(Long billMonthSize) {		this.billMonthSize = billMonthSize;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getBillMonthEnd() {		return billMonthEnd;	}	public void setBillMonthEnd(String billMonthEnd) {		this.billMonthEnd = billMonthEnd;	}	public String getPayDayStart() {		return payDayStart;	}	public void setPayDayStart(String payDayStart) {		this.payDayStart = payDayStart;	}	public String getPayDayEnd() {		return payDayEnd;	}	public void setPayDayEnd(String payDayEnd) {		this.payDayEnd = payDayEnd;	}
	
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
		PayBillTimeCfg other = (PayBillTimeCfg) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
