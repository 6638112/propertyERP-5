package com.cnfantasia.server.domainbase.revenueSignalAmount.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(单个提款项的收益参数信息信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RevenueSignalAmount implements Serializable{
*/


public class RevenueSignalAmount extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属最小粒度角色类型 */	private Integer miniRoleType;	/** 所属最小粒度角色Id */	private BigInteger miniRoleId;	/** 提款对象类型 */	private Integer goalType;	/** 提款对象明细类型 */	private Integer goalDetailType;	/** 提款对象Id */	private BigInteger goalId;	/** 对象产生可产生收益的时间(冗余) */	private String goalRevTime;	/** 此字段仅用于由于物业宝idx_t_revenue_signal_amount_comm的冲突问题 */	private String goalRevTimeStr;	/** 原始数据金额,作为参与收益计算的参数 */	private Double srcMoney;	/** 该项可提款金额(初始为空) */	private Double amount;	/** 可提款金额生成时间 */	private String moneyTime;	/** 对应使用的规则(id,及相关数据冗余) */	private BigInteger revConfigId;	/**  */	private String revConfigJson;	/** 提款状态 */	private Integer tkStatus;	/** 结算通过时间 */	private String tkSuccTime;	/** 提款申请Id */	private BigInteger revApplyId;	/** 角色名称 */	private String roleName;	/** 平台补贴额 */	private Double amountPtbt;	/** 用户实际缴费额 */	private Double amountUsrReal;	/** 支付流水号 */	private String payFlowNo;	/** 支付方式 */	private Integer payMethod;	/** 订单号，暂时只有物业宝抵扣用到 */	private String orderNo;	/** 小区id */	private BigInteger tGroupBuildingId;	/** 物业管理处ID */	private BigInteger tPropertyManagementFId;
	public RevenueSignalAmount(){
	}
	public RevenueSignalAmount(RevenueSignalAmount arg){
		this.id = arg.getId();		this.miniRoleType = arg.getMiniRoleType();		this.miniRoleId = arg.getMiniRoleId();		this.goalType = arg.getGoalType();		this.goalDetailType = arg.getGoalDetailType();		this.goalId = arg.getGoalId();		this.goalRevTime = arg.getGoalRevTime();		this.goalRevTimeStr = arg.getGoalRevTimeStr();		this.srcMoney = arg.getSrcMoney();		this.amount = arg.getAmount();		this.moneyTime = arg.getMoneyTime();		this.revConfigId = arg.getRevConfigId();		this.revConfigJson = arg.getRevConfigJson();		this.tkStatus = arg.getTkStatus();		this.tkSuccTime = arg.getTkSuccTime();		this.revApplyId = arg.getRevApplyId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.roleName = arg.getRoleName();		this.amountPtbt = arg.getAmountPtbt();		this.amountUsrReal = arg.getAmountUsrReal();		this.payFlowNo = arg.getPayFlowNo();		this.payMethod = arg.getPayMethod();		this.orderNo = arg.getOrderNo();		this.tGroupBuildingId = arg.gettGroupBuildingId();		this.tPropertyManagementFId = arg.gettPropertyManagementFId();
	}
	/**	 * 	 * @param id 	 * @param miniRoleType 所属最小粒度角色类型	 * @param miniRoleId 所属最小粒度角色Id	 * @param goalType 提款对象类型	 * @param goalDetailType 提款对象明细类型	 * @param goalId 提款对象Id	 * @param goalRevTime 对象产生可产生收益的时间(冗余)	 * @param goalRevTimeStr 此字段仅用于由于物业宝idx_t_revenue_signal_amount_comm的冲突问题	 * @param srcMoney 原始数据金额,作为参与收益计算的参数	 * @param amount 该项可提款金额(初始为空)	 * @param moneyTime 可提款金额生成时间	 * @param revConfigId 对应使用的规则(id,及相关数据冗余)	 * @param revConfigJson 	 * @param tkStatus 提款状态	 * @param tkSuccTime 结算通过时间	 * @param revApplyId 提款申请Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param roleName 角色名称	 * @param amountPtbt 平台补贴额	 * @param amountUsrReal 用户实际缴费额	 * @param payFlowNo 支付流水号	 * @param payMethod 支付方式	 * @param orderNo 订单号，暂时只有物业宝抵扣用到	 * @param tGroupBuildingId 小区id	 * @param tPropertyManagementFId 物业管理处ID	 */
	public RevenueSignalAmount(BigInteger id,Integer miniRoleType,BigInteger miniRoleId,Integer goalType,Integer goalDetailType,BigInteger goalId,String goalRevTime,String goalRevTimeStr,Double srcMoney,Double amount,String moneyTime,BigInteger revConfigId,String revConfigJson,Integer tkStatus,String tkSuccTime,BigInteger revApplyId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String roleName,Double amountPtbt,Double amountUsrReal,String payFlowNo,Integer payMethod,String orderNo,BigInteger tGroupBuildingId,BigInteger tPropertyManagementFId){
		this.id = id;		this.miniRoleType = miniRoleType;		this.miniRoleId = miniRoleId;		this.goalType = goalType;		this.goalDetailType = goalDetailType;		this.goalId = goalId;		this.goalRevTime = goalRevTime;		this.goalRevTimeStr = goalRevTimeStr;		this.srcMoney = srcMoney;		this.amount = amount;		this.moneyTime = moneyTime;		this.revConfigId = revConfigId;		this.revConfigJson = revConfigJson;		this.tkStatus = tkStatus;		this.tkSuccTime = tkSuccTime;		this.revApplyId = revApplyId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.roleName = roleName;		this.amountPtbt = amountPtbt;		this.amountUsrReal = amountUsrReal;		this.payFlowNo = payFlowNo;		this.payMethod = payMethod;		this.orderNo = orderNo;		this.tGroupBuildingId = tGroupBuildingId;		this.tPropertyManagementFId = tPropertyManagementFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("miniRoleType=").append(miniRoleType).append(";");		sbf.append("miniRoleId=").append(miniRoleId).append(";");		sbf.append("goalType=").append(goalType).append(";");		sbf.append("goalDetailType=").append(goalDetailType).append(";");		sbf.append("goalId=").append(goalId).append(";");		sbf.append("goalRevTime=").append(goalRevTime).append(";");		sbf.append("goalRevTimeStr=").append(goalRevTimeStr).append(";");		sbf.append("srcMoney=").append(srcMoney).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("moneyTime=").append(moneyTime).append(";");		sbf.append("revConfigId=").append(revConfigId).append(";");		sbf.append("revConfigJson=").append(revConfigJson).append(";");		sbf.append("tkStatus=").append(tkStatus).append(";");		sbf.append("tkSuccTime=").append(tkSuccTime).append(";");		sbf.append("revApplyId=").append(revApplyId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("roleName=").append(roleName).append(";");		sbf.append("amountPtbt=").append(amountPtbt).append(";");		sbf.append("amountUsrReal=").append(amountUsrReal).append(";");		sbf.append("payFlowNo=").append(payFlowNo).append(";");		sbf.append("payMethod=").append(payMethod).append(";");		sbf.append("orderNo=").append(orderNo).append(";");		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getMiniRoleType() {		return miniRoleType;	}	public void setMiniRoleType(Integer miniRoleType) {		this.miniRoleType = miniRoleType;	}	public BigInteger getMiniRoleId() {		return miniRoleId;	}	public void setMiniRoleId(BigInteger miniRoleId) {		this.miniRoleId = miniRoleId;	}	public Integer getGoalType() {		return goalType;	}	public void setGoalType(Integer goalType) {		this.goalType = goalType;	}	public Integer getGoalDetailType() {		return goalDetailType;	}	public void setGoalDetailType(Integer goalDetailType) {		this.goalDetailType = goalDetailType;	}	public BigInteger getGoalId() {		return goalId;	}	public void setGoalId(BigInteger goalId) {		this.goalId = goalId;	}	public String getGoalRevTime() {		return goalRevTime;	}	public void setGoalRevTime(String goalRevTime) {		this.goalRevTime = goalRevTime;	}	public String getGoalRevTimeStr() {		return goalRevTimeStr;	}	public void setGoalRevTimeStr(String goalRevTimeStr) {		this.goalRevTimeStr = goalRevTimeStr;	}	public Double getSrcMoney() {		return srcMoney;	}	public void setSrcMoney(Double srcMoney) {		this.srcMoney = srcMoney;	}	public Double getAmount() {		return amount;	}	public void setAmount(Double amount) {		this.amount = amount;	}	public String getMoneyTime() {		return moneyTime;	}	public void setMoneyTime(String moneyTime) {		this.moneyTime = moneyTime;	}	public BigInteger getRevConfigId() {		return revConfigId;	}	public void setRevConfigId(BigInteger revConfigId) {		this.revConfigId = revConfigId;	}	public String getRevConfigJson() {		return revConfigJson;	}	public void setRevConfigJson(String revConfigJson) {		this.revConfigJson = revConfigJson;	}	public Integer getTkStatus() {		return tkStatus;	}	public void setTkStatus(Integer tkStatus) {		this.tkStatus = tkStatus;	}	public String getTkSuccTime() {		return tkSuccTime;	}	public void setTkSuccTime(String tkSuccTime) {		this.tkSuccTime = tkSuccTime;	}	public BigInteger getRevApplyId() {		return revApplyId;	}	public void setRevApplyId(BigInteger revApplyId) {		this.revApplyId = revApplyId;	}	public String getRoleName() {		return roleName;	}	public void setRoleName(String roleName) {		this.roleName = roleName;	}	public Double getAmountPtbt() {		return amountPtbt;	}	public void setAmountPtbt(Double amountPtbt) {		this.amountPtbt = amountPtbt;	}	public Double getAmountUsrReal() {		return amountUsrReal;	}	public void setAmountUsrReal(Double amountUsrReal) {		this.amountUsrReal = amountUsrReal;	}	public String getPayFlowNo() {		return payFlowNo;	}	public void setPayFlowNo(String payFlowNo) {		this.payFlowNo = payFlowNo;	}	public Integer getPayMethod() {		return payMethod;	}	public void setPayMethod(Integer payMethod) {		this.payMethod = payMethod;	}	public String getOrderNo() {		return orderNo;	}	public void setOrderNo(String orderNo) {		this.orderNo = orderNo;	}	public BigInteger gettGroupBuildingId() {		return tGroupBuildingId;	}	public void settGroupBuildingId(BigInteger tGroupBuildingId) {		this.tGroupBuildingId = tGroupBuildingId;	}	public BigInteger gettPropertyManagementFId() {		return tPropertyManagementFId;	}	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {		this.tPropertyManagementFId = tPropertyManagementFId;	}
	
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
		RevenueSignalAmount other = (RevenueSignalAmount) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
