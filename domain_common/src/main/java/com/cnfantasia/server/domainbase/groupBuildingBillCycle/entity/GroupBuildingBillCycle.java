package com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(账期管理) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GroupBuildingBillCycle implements Serializable{
*/


public class GroupBuildingBillCycle extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区id */	private BigInteger tGroupBuildingId;	/** 费用类型id */	private BigInteger tPayBillTypeId;	/** 周期缴费时间配置 */	private BigInteger tPayBillTimeCfgId;	/** 账单月份 */	private String billMonth;	/** 缴费开始时间 */	private String billPayStart;	/** 缴费结束时间 */	private String billPayEnd;	/** 账单开始时间（周期） */	private String billMonthStart;	/** 账单结束时间（周期） */	private String billMonthEnd;	/** 缴费时间方式 */	private Integer paytimeType;	/** 账期操作状态（0可以操作，1不可以操作） */	private Integer operateStatus;	/** 包含费用项（1,2,3）逗号分隔存储，1 */	private String feeType;	/** 自动生成的账期 */	private BigInteger gbbcCfgId;	/** 是否收缴欠费（2收缴 1或者空不收缴） */	private Integer isCollectArrears;	/** 是否已经生成（抄表、固定、临时）账单 */	private Integer isGenerateBill;	/** 选择周期月份数（使用逗号分隔 */	private String periodMonths;	/** 收费模式（1定期，2选择周期） */	private Integer chargingMode;	/** 是否银行托收（1托收，2不托收） */	private Integer bankCollectionStatus;
	public GroupBuildingBillCycle(){
	}
	public GroupBuildingBillCycle(GroupBuildingBillCycle arg){
		this.id = arg.getId();		this.tGroupBuildingId = arg.gettGroupBuildingId();		this.tPayBillTypeId = arg.gettPayBillTypeId();		this.tPayBillTimeCfgId = arg.gettPayBillTimeCfgId();		this.billMonth = arg.getBillMonth();		this.billPayStart = arg.getBillPayStart();		this.billPayEnd = arg.getBillPayEnd();		this.billMonthStart = arg.getBillMonthStart();		this.billMonthEnd = arg.getBillMonthEnd();		this.paytimeType = arg.getPaytimeType();		this.operateStatus = arg.getOperateStatus();		this.feeType = arg.getFeeType();		this.gbbcCfgId = arg.getGbbcCfgId();		this.isCollectArrears = arg.getIsCollectArrears();		this.isGenerateBill = arg.getIsGenerateBill();		this.periodMonths = arg.getPeriodMonths();		this.chargingMode = arg.getChargingMode();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.bankCollectionStatus = arg.getBankCollectionStatus();
	}
	/**	 * 	 * @param id 	 * @param tGroupBuildingId 小区id	 * @param tPayBillTypeId 费用类型id	 * @param tPayBillTimeCfgId 周期缴费时间配置	 * @param billMonth 账单月份	 * @param billPayStart 缴费开始时间	 * @param billPayEnd 缴费结束时间	 * @param billMonthStart 账单开始时间（周期）	 * @param billMonthEnd 账单结束时间（周期）	 * @param paytimeType 缴费时间方式	 * @param operateStatus 账期操作状态（0可以操作，1不可以操作）	 * @param feeType 包含费用项（1,2,3）逗号分隔存储，1	 * @param gbbcCfgId 自动生成的账期	 * @param isCollectArrears 是否收缴欠费（2收缴 1或者空不收缴）	 * @param isGenerateBill 是否已经生成（抄表、固定、临时）账单	 * @param periodMonths 选择周期月份数（使用逗号分隔	 * @param chargingMode 收费模式（1定期，2选择周期）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param bankCollectionStatus 是否银行托收（1托收，2不托收）	 */
	public GroupBuildingBillCycle(BigInteger id,BigInteger tGroupBuildingId,BigInteger tPayBillTypeId,BigInteger tPayBillTimeCfgId,String billMonth,String billPayStart,String billPayEnd,String billMonthStart,String billMonthEnd,Integer paytimeType,Integer operateStatus,String feeType,BigInteger gbbcCfgId,Integer isCollectArrears,Integer isGenerateBill,String periodMonths,Integer chargingMode,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer bankCollectionStatus){
		this.id = id;		this.tGroupBuildingId = tGroupBuildingId;		this.tPayBillTypeId = tPayBillTypeId;		this.tPayBillTimeCfgId = tPayBillTimeCfgId;		this.billMonth = billMonth;		this.billPayStart = billPayStart;		this.billPayEnd = billPayEnd;		this.billMonthStart = billMonthStart;		this.billMonthEnd = billMonthEnd;		this.paytimeType = paytimeType;		this.operateStatus = operateStatus;		this.feeType = feeType;		this.gbbcCfgId = gbbcCfgId;		this.isCollectArrears = isCollectArrears;		this.isGenerateBill = isGenerateBill;		this.periodMonths = periodMonths;		this.chargingMode = chargingMode;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.bankCollectionStatus = bankCollectionStatus;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");		sbf.append("tPayBillTypeId=").append(tPayBillTypeId).append(";");		sbf.append("tPayBillTimeCfgId=").append(tPayBillTimeCfgId).append(";");		sbf.append("billMonth=").append(billMonth).append(";");		sbf.append("billPayStart=").append(billPayStart).append(";");		sbf.append("billPayEnd=").append(billPayEnd).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("billMonthEnd=").append(billMonthEnd).append(";");		sbf.append("paytimeType=").append(paytimeType).append(";");		sbf.append("operateStatus=").append(operateStatus).append(";");		sbf.append("feeType=").append(feeType).append(";");		sbf.append("gbbcCfgId=").append(gbbcCfgId).append(";");		sbf.append("isCollectArrears=").append(isCollectArrears).append(";");		sbf.append("isGenerateBill=").append(isGenerateBill).append(";");		sbf.append("periodMonths=").append(periodMonths).append(";");		sbf.append("chargingMode=").append(chargingMode).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("bankCollectionStatus=").append(bankCollectionStatus).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGroupBuildingId() {		return tGroupBuildingId;	}	public void settGroupBuildingId(BigInteger tGroupBuildingId) {		this.tGroupBuildingId = tGroupBuildingId;	}	public BigInteger gettPayBillTypeId() {		return tPayBillTypeId;	}	public void settPayBillTypeId(BigInteger tPayBillTypeId) {		this.tPayBillTypeId = tPayBillTypeId;	}	public BigInteger gettPayBillTimeCfgId() {		return tPayBillTimeCfgId;	}	public void settPayBillTimeCfgId(BigInteger tPayBillTimeCfgId) {		this.tPayBillTimeCfgId = tPayBillTimeCfgId;	}	public String getBillMonth() {		return billMonth;	}	public void setBillMonth(String billMonth) {		this.billMonth = billMonth;	}	public String getBillPayStart() {		return billPayStart;	}	public void setBillPayStart(String billPayStart) {		this.billPayStart = billPayStart;	}	public String getBillPayEnd() {		return billPayEnd;	}	public void setBillPayEnd(String billPayEnd) {		this.billPayEnd = billPayEnd;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getBillMonthEnd() {		return billMonthEnd;	}	public void setBillMonthEnd(String billMonthEnd) {		this.billMonthEnd = billMonthEnd;	}	public Integer getPaytimeType() {		return paytimeType;	}	public void setPaytimeType(Integer paytimeType) {		this.paytimeType = paytimeType;	}	public Integer getOperateStatus() {		return operateStatus;	}	public void setOperateStatus(Integer operateStatus) {		this.operateStatus = operateStatus;	}	public String getFeeType() {		return feeType;	}	public void setFeeType(String feeType) {		this.feeType = feeType;	}	public BigInteger getGbbcCfgId() {		return gbbcCfgId;	}	public void setGbbcCfgId(BigInteger gbbcCfgId) {		this.gbbcCfgId = gbbcCfgId;	}	public Integer getIsCollectArrears() {		return isCollectArrears;	}	public void setIsCollectArrears(Integer isCollectArrears) {		this.isCollectArrears = isCollectArrears;	}	public Integer getIsGenerateBill() {		return isGenerateBill;	}	public void setIsGenerateBill(Integer isGenerateBill) {		this.isGenerateBill = isGenerateBill;	}	public String getPeriodMonths() {		return periodMonths;	}	public void setPeriodMonths(String periodMonths) {		this.periodMonths = periodMonths;	}	public Integer getChargingMode() {		return chargingMode;	}	public void setChargingMode(Integer chargingMode) {		this.chargingMode = chargingMode;	}	public Integer getBankCollectionStatus() {		return bankCollectionStatus;	}	public void setBankCollectionStatus(Integer bankCollectionStatus) {		this.bankCollectionStatus = bankCollectionStatus;	}
	
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
		GroupBuildingBillCycle other = (GroupBuildingBillCycle) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
