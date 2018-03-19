package com.cnfantasia.server.domainbase.revenueApply.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.lang.Double;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(提款申请记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RevenueApply implements Serializable{
*/


public class RevenueApply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 提款单号 */
	private String applyNo;
	/** 财务提款申请Id */
	private BigInteger revApplyFinanceId;
	/** 可见类型 */
	private Integer visibleType;
	/** 所属最小粒度角色类型 */
	private Integer miniRoleType;
	/** 所属最小粒度角色Id */
	private BigInteger miniRoleId;
	/** 提款对象类型 */
	private Integer goalType;
	/** 添加记录的渠道，DictConstants.channelSub */
	private Integer channel;
	/** 提款申请人omsUserId */
	private BigInteger applyUserId;
	/** 提款申请时间 */
	private String applyTime;
	/** 提款开始时间 */
	private String goalStartTime;
	/** 提款结束时间 */
	private String goalEndTime;
	/** 提款总额 */
	private Double totalAmount;
	/** 提款状态 */
	private Integer tkStatus;
	/** 操作类型 */
	private Integer optType;
	/** 提款成功时间 */
	private String tkSuccTime;
	/** 对象名称 */
	private String roleName;
	/** 手机号 */
	private String bPhone;
	/** 联系人姓名 */
	private String bConnectName;
	/** 银行卡号 */
	private String bBankNo;
	/** 开户支行 */
	private String bBankName;
	/** 开卡支行 */
	private String accountName;
	/** 开卡支行 */
	private String bankBranch;
	/** 支行所在省 */
	private String bankProvince;
	/** 支行所在市 */
	private String bankCity;
	/** 平台补贴额 */
	private Double amountPtbt;
	/** 用户实际实缴 */
	private Double amountUsrReal;
	/** EAS报销单（付款单）单号 */
	private String easBillNumbers;
	/** 审核时间 */
	private String auditTime;
	/** 审核状态 */
	private Integer auditStatus;
	/** 结算申请备注 */
	private String applyNote;
	/** 审核说明 */
	private String handlerNote;
	/** 供应商ID */
	private BigInteger tEbuySupplyMerchantFk;
	/** 供应商合作模式 */
	private Integer revenueType;
	/** 佣金比率 */
	private Double revenueRate;
	/** EAS已付金额 */
	private Double easPayedAmount;
	/** 小区id */
	private BigInteger tGbId;
	/** 物业管理处t_property_management表id */
	private BigInteger tPropertyManagementFId;

	public RevenueApply(){
	}
	public RevenueApply(RevenueApply arg){
		this.id = arg.getId();
		this.applyNo = arg.getApplyNo();
		this.revApplyFinanceId = arg.getRevApplyFinanceId();
		this.visibleType = arg.getVisibleType();
		this.miniRoleType = arg.getMiniRoleType();
		this.miniRoleId = arg.getMiniRoleId();
		this.goalType = arg.getGoalType();
		this.channel = arg.getChannel();
		this.applyUserId = arg.getApplyUserId();
		this.applyTime = arg.getApplyTime();
		this.goalStartTime = arg.getGoalStartTime();
		this.goalEndTime = arg.getGoalEndTime();
		this.totalAmount = arg.getTotalAmount();
		this.tkStatus = arg.getTkStatus();
		this.optType = arg.getOptType();
		this.tkSuccTime = arg.getTkSuccTime();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.roleName = arg.getRoleName();
		this.bPhone = arg.getbPhone();
		this.bConnectName = arg.getbConnectName();
		this.bBankNo = arg.getbBankNo();
		this.bBankName = arg.getbBankName();
		this.accountName = arg.getAccountName();
		this.bankBranch = arg.getBankBranch();
		this.bankProvince = arg.getBankProvince();
		this.bankCity = arg.getBankCity();
		this.amountPtbt = arg.getAmountPtbt();
		this.amountUsrReal = arg.getAmountUsrReal();
		this.easBillNumbers = arg.getEasBillNumbers();
		this.auditTime = arg.getAuditTime();
		this.auditStatus = arg.getAuditStatus();
		this.applyNote = arg.getApplyNote();
		this.handlerNote = arg.getHandlerNote();
		this.tEbuySupplyMerchantFk = arg.gettEbuySupplyMerchantFk();
		this.revenueType = arg.getRevenueType();
		this.revenueRate = arg.getRevenueRate();
		this.easPayedAmount = arg.getEasPayedAmount();
		this.tGbId = arg.gettGbId();
		this.tPropertyManagementFId = arg.gettPropertyManagementFId();

	}
	/**
	 * 
	 * @param id 
	 * @param applyNo 提款单号
	 * @param revApplyFinanceId 财务提款申请Id
	 * @param visibleType 可见类型
	 * @param miniRoleType 所属最小粒度角色类型
	 * @param miniRoleId 所属最小粒度角色Id
	 * @param goalType 提款对象类型
	 * @param channel 添加记录的渠道，DictConstants.channelSub
	 * @param applyUserId 提款申请人omsUserId
	 * @param applyTime 提款申请时间
	 * @param goalStartTime 提款开始时间
	 * @param goalEndTime 提款结束时间
	 * @param totalAmount 提款总额
	 * @param tkStatus 提款状态
	 * @param optType 操作类型
	 * @param tkSuccTime 提款成功时间
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param roleName 对象名称
	 * @param bPhone 手机号
	 * @param bConnectName 联系人姓名
	 * @param bBankNo 银行卡号
	 * @param bBankName 开户支行
	 * @param accountName 开卡支行
	 * @param bankBranch 开卡支行
	 * @param bankProvince 支行所在省
	 * @param bankCity 支行所在市
	 * @param amountPtbt 平台补贴额
	 * @param amountUsrReal 用户实际实缴
	 * @param easBillNumbers EAS报销单（付款单）单号
	 * @param auditTime 审核时间
	 * @param auditStatus 审核状态
	 * @param applyNote 结算申请备注
	 * @param handlerNote 审核说明
	 * @param tEbuySupplyMerchantFk 供应商ID
	 * @param revenueType 供应商合作模式
	 * @param revenueRate 佣金比率
	 * @param easPayedAmount EAS已付金额
	 * @param tGbId 小区id
	 * @param tPropertyManagementFId 物业管理处t_property_management表id
	 */

	public RevenueApply(BigInteger id,String applyNo,BigInteger revApplyFinanceId,Integer visibleType,Integer miniRoleType,BigInteger miniRoleId,Integer goalType,Integer channel,BigInteger applyUserId,String applyTime,String goalStartTime,String goalEndTime,Double totalAmount,Integer tkStatus,Integer optType,String tkSuccTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String roleName,String bPhone,String bConnectName,String bBankNo,String bBankName,String accountName,String bankBranch,String bankProvince,String bankCity,Double amountPtbt,Double amountUsrReal,String easBillNumbers,String auditTime,Integer auditStatus,String applyNote,String handlerNote,BigInteger tEbuySupplyMerchantFk,Integer revenueType,Double revenueRate,Double easPayedAmount,BigInteger tGbId,BigInteger tPropertyManagementFId){
		this.id = id;
		this.applyNo = applyNo;
		this.revApplyFinanceId = revApplyFinanceId;
		this.visibleType = visibleType;
		this.miniRoleType = miniRoleType;
		this.miniRoleId = miniRoleId;
		this.goalType = goalType;
		this.channel = channel;
		this.applyUserId = applyUserId;
		this.applyTime = applyTime;
		this.goalStartTime = goalStartTime;
		this.goalEndTime = goalEndTime;
		this.totalAmount = totalAmount;
		this.tkStatus = tkStatus;
		this.optType = optType;
		this.tkSuccTime = tkSuccTime;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.roleName = roleName;
		this.bPhone = bPhone;
		this.bConnectName = bConnectName;
		this.bBankNo = bBankNo;
		this.bBankName = bBankName;
		this.accountName = accountName;
		this.bankBranch = bankBranch;
		this.bankProvince = bankProvince;
		this.bankCity = bankCity;
		this.amountPtbt = amountPtbt;
		this.amountUsrReal = amountUsrReal;
		this.easBillNumbers = easBillNumbers;
		this.auditTime = auditTime;
		this.auditStatus = auditStatus;
		this.applyNote = applyNote;
		this.handlerNote = handlerNote;
		this.tEbuySupplyMerchantFk = tEbuySupplyMerchantFk;
		this.revenueType = revenueType;
		this.revenueRate = revenueRate;
		this.easPayedAmount = easPayedAmount;
		this.tGbId = tGbId;
		this.tPropertyManagementFId = tPropertyManagementFId;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("applyNo=").append(applyNo).append(";");
		sbf.append("revApplyFinanceId=").append(revApplyFinanceId).append(";");
		sbf.append("visibleType=").append(visibleType).append(";");
		sbf.append("miniRoleType=").append(miniRoleType).append(";");
		sbf.append("miniRoleId=").append(miniRoleId).append(";");
		sbf.append("goalType=").append(goalType).append(";");
		sbf.append("channel=").append(channel).append(";");
		sbf.append("applyUserId=").append(applyUserId).append(";");
		sbf.append("applyTime=").append(applyTime).append(";");
		sbf.append("goalStartTime=").append(goalStartTime).append(";");
		sbf.append("goalEndTime=").append(goalEndTime).append(";");
		sbf.append("totalAmount=").append(totalAmount).append(";");
		sbf.append("tkStatus=").append(tkStatus).append(";");
		sbf.append("optType=").append(optType).append(";");
		sbf.append("tkSuccTime=").append(tkSuccTime).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("roleName=").append(roleName).append(";");
		sbf.append("bPhone=").append(bPhone).append(";");
		sbf.append("bConnectName=").append(bConnectName).append(";");
		sbf.append("bBankNo=").append(bBankNo).append(";");
		sbf.append("bBankName=").append(bBankName).append(";");
		sbf.append("accountName=").append(accountName).append(";");
		sbf.append("bankBranch=").append(bankBranch).append(";");
		sbf.append("bankProvince=").append(bankProvince).append(";");
		sbf.append("bankCity=").append(bankCity).append(";");
		sbf.append("amountPtbt=").append(amountPtbt).append(";");
		sbf.append("amountUsrReal=").append(amountUsrReal).append(";");
		sbf.append("easBillNumbers=").append(easBillNumbers).append(";");
		sbf.append("auditTime=").append(auditTime).append(";");
		sbf.append("auditStatus=").append(auditStatus).append(";");
		sbf.append("applyNote=").append(applyNote).append(";");
		sbf.append("handlerNote=").append(handlerNote).append(";");
		sbf.append("tEbuySupplyMerchantFk=").append(tEbuySupplyMerchantFk).append(";");
		sbf.append("revenueType=").append(revenueType).append(";");
		sbf.append("revenueRate=").append(revenueRate).append(";");
		sbf.append("easPayedAmount=").append(easPayedAmount).append(";");
		sbf.append("tGbId=").append(tGbId).append(";");
		sbf.append("tPropertyManagementFId=").append(tPropertyManagementFId).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public BigInteger getRevApplyFinanceId() {
		return revApplyFinanceId;
	}
	public void setRevApplyFinanceId(BigInteger revApplyFinanceId) {
		this.revApplyFinanceId = revApplyFinanceId;
	}
	public Integer getVisibleType() {
		return visibleType;
	}
	public void setVisibleType(Integer visibleType) {
		this.visibleType = visibleType;
	}
	public Integer getMiniRoleType() {
		return miniRoleType;
	}
	public void setMiniRoleType(Integer miniRoleType) {
		this.miniRoleType = miniRoleType;
	}
	public BigInteger getMiniRoleId() {
		return miniRoleId;
	}
	public void setMiniRoleId(BigInteger miniRoleId) {
		this.miniRoleId = miniRoleId;
	}
	public Integer getGoalType() {
		return goalType;
	}
	public void setGoalType(Integer goalType) {
		this.goalType = goalType;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public BigInteger getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(BigInteger applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getGoalStartTime() {
		return goalStartTime;
	}
	public void setGoalStartTime(String goalStartTime) {
		this.goalStartTime = goalStartTime;
	}
	public String getGoalEndTime() {
		return goalEndTime;
	}
	public void setGoalEndTime(String goalEndTime) {
		this.goalEndTime = goalEndTime;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getTkStatus() {
		return tkStatus;
	}
	public void setTkStatus(Integer tkStatus) {
		this.tkStatus = tkStatus;
	}
	public Integer getOptType() {
		return optType;
	}
	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	public String getTkSuccTime() {
		return tkSuccTime;
	}
	public void setTkSuccTime(String tkSuccTime) {
		this.tkSuccTime = tkSuccTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getbPhone() {
		return bPhone;
	}
	public void setbPhone(String bPhone) {
		this.bPhone = bPhone;
	}
	public String getbConnectName() {
		return bConnectName;
	}
	public void setbConnectName(String bConnectName) {
		this.bConnectName = bConnectName;
	}
	public String getbBankNo() {
		return bBankNo;
	}
	public void setbBankNo(String bBankNo) {
		this.bBankNo = bBankNo;
	}
	public String getbBankName() {
		return bBankName;
	}
	public void setbBankName(String bBankName) {
		this.bBankName = bBankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public Double getAmountPtbt() {
		return amountPtbt;
	}
	public void setAmountPtbt(Double amountPtbt) {
		this.amountPtbt = amountPtbt;
	}
	public Double getAmountUsrReal() {
		return amountUsrReal;
	}
	public void setAmountUsrReal(Double amountUsrReal) {
		this.amountUsrReal = amountUsrReal;
	}
	public String getEasBillNumbers() {
		return easBillNumbers;
	}
	public void setEasBillNumbers(String easBillNumbers) {
		this.easBillNumbers = easBillNumbers;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getApplyNote() {
		return applyNote;
	}
	public void setApplyNote(String applyNote) {
		this.applyNote = applyNote;
	}
	public String getHandlerNote() {
		return handlerNote;
	}
	public void setHandlerNote(String handlerNote) {
		this.handlerNote = handlerNote;
	}
	public BigInteger gettEbuySupplyMerchantFk() {
		return tEbuySupplyMerchantFk;
	}
	public void settEbuySupplyMerchantFk(BigInteger tEbuySupplyMerchantFk) {
		this.tEbuySupplyMerchantFk = tEbuySupplyMerchantFk;
	}
	public Integer getRevenueType() {
		return revenueType;
	}
	public void setRevenueType(Integer revenueType) {
		this.revenueType = revenueType;
	}
	public Double getRevenueRate() {
		return revenueRate;
	}
	public void setRevenueRate(Double revenueRate) {
		this.revenueRate = revenueRate;
	}
	public Double getEasPayedAmount() {
		return easPayedAmount;
	}
	public void setEasPayedAmount(Double easPayedAmount) {
		this.easPayedAmount = easPayedAmount;
	}
	public BigInteger gettGbId() {
		return tGbId;
	}
	public void settGbId(BigInteger tGbId) {
		this.tGbId = tGbId;
	}
	public BigInteger gettPropertyManagementFId() {
		return tPropertyManagementFId;
	}
	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {
		this.tPropertyManagementFId = tPropertyManagementFId;
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
		RevenueApply other = (RevenueApply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
