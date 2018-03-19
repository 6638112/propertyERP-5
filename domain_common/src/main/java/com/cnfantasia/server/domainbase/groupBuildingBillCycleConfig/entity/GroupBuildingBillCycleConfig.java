package com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.lang.Double;
import java.lang.Long;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GroupBuildingBillCycleConfig implements Serializable{
*/


public class GroupBuildingBillCycleConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 小区id */
	private BigInteger tGbId;
	/** 账单名称 */
	private String billName;
	/** 收费模式（1定期，2选择周期，3预存收费） */
	private Integer chargingMode;
	/** 账单开始时间 */
	private String billMonthStart;
	/** 账单结束时间 */
	private String billMonthEnd;
	/** 缴费开始时间 */
	private String billPayStart;
	/** 缴费结束时间 */
	private String billPayEnd;
	/** 包含费用项（1,2,3）逗号分隔存储，1 */
	private String feeType;
	/** 是否收取欠费（1暂时不做处理；2收取） */
	private Integer arrearsTransfer;
	/** 选择周期月份数（使用逗号分隔 */
	private String periodMonths;
	/** 最小收费金额（单位 */
	private Double minRecharge;
	/** 最大收费金额（单位 */
	private Double maxRecharge;
	/** 收费期限开始天数 */
	private Integer startDay;
	/** 收费期限截止天数 */
	private Integer endDay;
	/** 充值月数（0 */
	private Long rechargeMonthMode;
	/** 是否银行托收（1托收，2不托收） */
	private Integer bankCollectionStatus;

	public GroupBuildingBillCycleConfig(){
	}
	public GroupBuildingBillCycleConfig(GroupBuildingBillCycleConfig arg){
		this.id = arg.getId();
		this.tGbId = arg.gettGbId();
		this.billName = arg.getBillName();
		this.chargingMode = arg.getChargingMode();
		this.billMonthStart = arg.getBillMonthStart();
		this.billMonthEnd = arg.getBillMonthEnd();
		this.billPayStart = arg.getBillPayStart();
		this.billPayEnd = arg.getBillPayEnd();
		this.feeType = arg.getFeeType();
		this.arrearsTransfer = arg.getArrearsTransfer();
		this.periodMonths = arg.getPeriodMonths();
		this.minRecharge = arg.getMinRecharge();
		this.maxRecharge = arg.getMaxRecharge();
		this.startDay = arg.getStartDay();
		this.endDay = arg.getEndDay();
		this.rechargeMonthMode = arg.getRechargeMonthMode();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.bankCollectionStatus = arg.getBankCollectionStatus();

	}
	/**
	 * 
	 * @param id 
	 * @param tGbId 小区id
	 * @param billName 账单名称
	 * @param chargingMode 收费模式（1定期，2选择周期，3预存收费）
	 * @param billMonthStart 账单开始时间
	 * @param billMonthEnd 账单结束时间
	 * @param billPayStart 缴费开始时间
	 * @param billPayEnd 缴费结束时间
	 * @param feeType 包含费用项（1,2,3）逗号分隔存储，1
	 * @param arrearsTransfer 是否收取欠费（1暂时不做处理；2收取）
	 * @param periodMonths 选择周期月份数（使用逗号分隔
	 * @param minRecharge 最小收费金额（单位
	 * @param maxRecharge 最大收费金额（单位
	 * @param startDay 收费期限开始天数
	 * @param endDay 收费期限截止天数
	 * @param rechargeMonthMode 充值月数（0
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param bankCollectionStatus 是否银行托收（1托收，2不托收）
	 */

	public GroupBuildingBillCycleConfig(BigInteger id,BigInteger tGbId,String billName,Integer chargingMode,String billMonthStart,String billMonthEnd,String billPayStart,String billPayEnd,String feeType,Integer arrearsTransfer,String periodMonths,Double minRecharge,Double maxRecharge,Integer startDay,Integer endDay,Long rechargeMonthMode,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer bankCollectionStatus){
		this.id = id;
		this.tGbId = tGbId;
		this.billName = billName;
		this.chargingMode = chargingMode;
		this.billMonthStart = billMonthStart;
		this.billMonthEnd = billMonthEnd;
		this.billPayStart = billPayStart;
		this.billPayEnd = billPayEnd;
		this.feeType = feeType;
		this.arrearsTransfer = arrearsTransfer;
		this.periodMonths = periodMonths;
		this.minRecharge = minRecharge;
		this.maxRecharge = maxRecharge;
		this.startDay = startDay;
		this.endDay = endDay;
		this.rechargeMonthMode = rechargeMonthMode;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.bankCollectionStatus = bankCollectionStatus;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tGbId=").append(tGbId).append(";");
		sbf.append("billName=").append(billName).append(";");
		sbf.append("chargingMode=").append(chargingMode).append(";");
		sbf.append("billMonthStart=").append(billMonthStart).append(";");
		sbf.append("billMonthEnd=").append(billMonthEnd).append(";");
		sbf.append("billPayStart=").append(billPayStart).append(";");
		sbf.append("billPayEnd=").append(billPayEnd).append(";");
		sbf.append("feeType=").append(feeType).append(";");
		sbf.append("arrearsTransfer=").append(arrearsTransfer).append(";");
		sbf.append("periodMonths=").append(periodMonths).append(";");
		sbf.append("minRecharge=").append(minRecharge).append(";");
		sbf.append("maxRecharge=").append(maxRecharge).append(";");
		sbf.append("startDay=").append(startDay).append(";");
		sbf.append("endDay=").append(endDay).append(";");
		sbf.append("rechargeMonthMode=").append(rechargeMonthMode).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("bankCollectionStatus=").append(bankCollectionStatus).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettGbId() {
		return tGbId;
	}
	public void settGbId(BigInteger tGbId) {
		this.tGbId = tGbId;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public Integer getChargingMode() {
		return chargingMode;
	}
	public void setChargingMode(Integer chargingMode) {
		this.chargingMode = chargingMode;
	}
	public String getBillMonthStart() {
		return billMonthStart;
	}
	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}
	public String getBillMonthEnd() {
		return billMonthEnd;
	}
	public void setBillMonthEnd(String billMonthEnd) {
		this.billMonthEnd = billMonthEnd;
	}
	public String getBillPayStart() {
		return billPayStart;
	}
	public void setBillPayStart(String billPayStart) {
		this.billPayStart = billPayStart;
	}
	public String getBillPayEnd() {
		return billPayEnd;
	}
	public void setBillPayEnd(String billPayEnd) {
		this.billPayEnd = billPayEnd;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getArrearsTransfer() {
		return arrearsTransfer;
	}
	public void setArrearsTransfer(Integer arrearsTransfer) {
		this.arrearsTransfer = arrearsTransfer;
	}
	public String getPeriodMonths() {
		return periodMonths;
	}
	public void setPeriodMonths(String periodMonths) {
		this.periodMonths = periodMonths;
	}
	public Double getMinRecharge() {
		return minRecharge;
	}
	public void setMinRecharge(Double minRecharge) {
		this.minRecharge = minRecharge;
	}
	public Double getMaxRecharge() {
		return maxRecharge;
	}
	public void setMaxRecharge(Double maxRecharge) {
		this.maxRecharge = maxRecharge;
	}
	public Integer getStartDay() {
		return startDay;
	}
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}
	public Integer getEndDay() {
		return endDay;
	}
	public void setEndDay(Integer endDay) {
		this.endDay = endDay;
	}
	public Long getRechargeMonthMode() {
		return rechargeMonthMode;
	}
	public void setRechargeMonthMode(Long rechargeMonthMode) {
		this.rechargeMonthMode = rechargeMonthMode;
	}
	public Integer getBankCollectionStatus() {
		return bankCollectionStatus;
	}
	public void setBankCollectionStatus(Integer bankCollectionStatus) {
		this.bankCollectionStatus = bankCollectionStatus;
	}

	
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
		GroupBuildingBillCycleConfig other = (GroupBuildingBillCycleConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
