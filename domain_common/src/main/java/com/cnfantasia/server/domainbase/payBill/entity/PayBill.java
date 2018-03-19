package com.cnfantasia.server.domainbase.payBill.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;
import java.lang.Double;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PayBill implements Serializable{
*/


public class PayBill extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 账单的唯一短编码 */
	private String shortCode;
	/** 待缴总金额 */
	private Long amount;
	/** 账单对应可用的折扣月份 */
	private String month;
	/** 账单月份,为空则取折扣月份 */
	private String billMonth;
	/** 缴费状态 */
	private Integer isPay;
	/** 付款时间 */
	private String payTime;
	/** 是否物业宝抵扣过 */
	private Integer financeStatus;
	/** 物业宝抵扣金额 */
	private Long deduPrice;
	/** 缴费方式 */
	private Integer paymentWay;
	/** 所属真实门牌的Id */
	private BigInteger tRealRoomFId;
	/** 当前真实门牌的业主Id */
	private String propertyProprietorId;
	/** 用户实际支付的账单金额 */
	private Long succPayAmount;
	/** 使用的折扣 */
	private Double discount;
	/** 中奖记录Id */
	private String prizeRecordId;
	/** 所属类别缴费时间配置Id */
	private BigInteger billTimeCfgId;
	/** 基础类别信息Id */
	private BigInteger billTypeId;
	/** 费用类别名称(冗余) */
	private String billTypeName;
	/** 是否为物业费(冗余) */
	private Integer isPropFee;
	/** 缴费时间方式 */
	private Integer paytimeType;
	/** 周期缴费方式 */
	private Integer cycleType;
	/** 账单月份跨度(冗余) */
	private Long billMonthSize;
	/** 账单开始月份(冗余) */
	private String billMonthStart;
	/** 账单截止月份(冗余) */
	private String billMonthEnd;
	/** 缴费开始日期(冗余) */
	private String payDayStart;
	/** 缴费截止日期(冗余) */
	private String payDayEnd;
	/** 红包补贴 */
	private Long hbAmount;
	/** 优惠类型 */
	private Integer preferType;
	/** 账期管理id */
	private BigInteger tBillCycleId;
	/** 物业缴费充值（需要） */
	private BigInteger tGroupBuildingBillCycleConfigFId;
	/** 现金流生成状态 */
	private Integer cashStatus;
	/** 使用物业对接数据生成的账单，费用对应的ID */
	private BigInteger tRealroomSoftwareFeeFId;
	/** 往月欠费（单位 */
	private Long lastUnpaid;
	/** 账单银行托收状态={0或null */
	private Integer bankCollectionStatus;

	public PayBill(){
	}
	public PayBill(PayBill arg){
		this.id = arg.getId();
		this.shortCode = arg.getShortCode();
		this.amount = arg.getAmount();
		this.month = arg.getMonth();
		this.billMonth = arg.getBillMonth();
		this.isPay = arg.getIsPay();
		this.payTime = arg.getPayTime();
		this.financeStatus = arg.getFinanceStatus();
		this.deduPrice = arg.getDeduPrice();
		this.paymentWay = arg.getPaymentWay();
		this.tRealRoomFId = arg.gettRealRoomFId();
		this.propertyProprietorId = arg.getPropertyProprietorId();
		this.succPayAmount = arg.getSuccPayAmount();
		this.discount = arg.getDiscount();
		this.prizeRecordId = arg.getPrizeRecordId();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.billTimeCfgId = arg.getBillTimeCfgId();
		this.billTypeId = arg.getBillTypeId();
		this.billTypeName = arg.getBillTypeName();
		this.isPropFee = arg.getIsPropFee();
		this.paytimeType = arg.getPaytimeType();
		this.cycleType = arg.getCycleType();
		this.billMonthSize = arg.getBillMonthSize();
		this.billMonthStart = arg.getBillMonthStart();
		this.billMonthEnd = arg.getBillMonthEnd();
		this.payDayStart = arg.getPayDayStart();
		this.payDayEnd = arg.getPayDayEnd();
		this.hbAmount = arg.getHbAmount();
		this.preferType = arg.getPreferType();
		this.tBillCycleId = arg.gettBillCycleId();
		this.tGroupBuildingBillCycleConfigFId = arg.gettGroupBuildingBillCycleConfigFId();
		this.cashStatus = arg.getCashStatus();
		this.tRealroomSoftwareFeeFId = arg.gettRealroomSoftwareFeeFId();
		this.lastUnpaid = arg.getLastUnpaid();
		this.bankCollectionStatus = arg.getBankCollectionStatus();

	}
	/**
	 * 
	 * @param id 
	 * @param shortCode 账单的唯一短编码
	 * @param amount 待缴总金额
	 * @param month 账单对应可用的折扣月份
	 * @param billMonth 账单月份,为空则取折扣月份
	 * @param isPay 缴费状态
	 * @param payTime 付款时间
	 * @param financeStatus 是否物业宝抵扣过
	 * @param deduPrice 物业宝抵扣金额
	 * @param paymentWay 缴费方式
	 * @param tRealRoomFId 所属真实门牌的Id
	 * @param propertyProprietorId 当前真实门牌的业主Id
	 * @param succPayAmount 用户实际支付的账单金额
	 * @param discount 使用的折扣
	 * @param prizeRecordId 中奖记录Id
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param billTimeCfgId 所属类别缴费时间配置Id
	 * @param billTypeId 基础类别信息Id
	 * @param billTypeName 费用类别名称(冗余)
	 * @param isPropFee 是否为物业费(冗余)
	 * @param paytimeType 缴费时间方式
	 * @param cycleType 周期缴费方式
	 * @param billMonthSize 账单月份跨度(冗余)
	 * @param billMonthStart 账单开始月份(冗余)
	 * @param billMonthEnd 账单截止月份(冗余)
	 * @param payDayStart 缴费开始日期(冗余)
	 * @param payDayEnd 缴费截止日期(冗余)
	 * @param hbAmount 红包补贴
	 * @param preferType 优惠类型
	 * @param tBillCycleId 账期管理id
	 * @param tGroupBuildingBillCycleConfigFId 物业缴费充值（需要）
	 * @param cashStatus 现金流生成状态
	 * @param tRealroomSoftwareFeeFId 使用物业对接数据生成的账单，费用对应的ID
	 * @param lastUnpaid 往月欠费（单位
	 * @param bankCollectionStatus 账单银行托收状态={0或null
	 */

	public PayBill(BigInteger id,String shortCode,Long amount,String month,String billMonth,Integer isPay,String payTime,Integer financeStatus,Long deduPrice,Integer paymentWay,BigInteger tRealRoomFId,String propertyProprietorId,Long succPayAmount,Double discount,String prizeRecordId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger billTimeCfgId,BigInteger billTypeId,String billTypeName,Integer isPropFee,Integer paytimeType,Integer cycleType,Long billMonthSize,String billMonthStart,String billMonthEnd,String payDayStart,String payDayEnd,Long hbAmount,Integer preferType,BigInteger tBillCycleId,BigInteger tGroupBuildingBillCycleConfigFId,Integer cashStatus,BigInteger tRealroomSoftwareFeeFId,Long lastUnpaid,Integer bankCollectionStatus){
		this.id = id;
		this.shortCode = shortCode;
		this.amount = amount;
		this.month = month;
		this.billMonth = billMonth;
		this.isPay = isPay;
		this.payTime = payTime;
		this.financeStatus = financeStatus;
		this.deduPrice = deduPrice;
		this.paymentWay = paymentWay;
		this.tRealRoomFId = tRealRoomFId;
		this.propertyProprietorId = propertyProprietorId;
		this.succPayAmount = succPayAmount;
		this.discount = discount;
		this.prizeRecordId = prizeRecordId;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.billTimeCfgId = billTimeCfgId;
		this.billTypeId = billTypeId;
		this.billTypeName = billTypeName;
		this.isPropFee = isPropFee;
		this.paytimeType = paytimeType;
		this.cycleType = cycleType;
		this.billMonthSize = billMonthSize;
		this.billMonthStart = billMonthStart;
		this.billMonthEnd = billMonthEnd;
		this.payDayStart = payDayStart;
		this.payDayEnd = payDayEnd;
		this.hbAmount = hbAmount;
		this.preferType = preferType;
		this.tBillCycleId = tBillCycleId;
		this.tGroupBuildingBillCycleConfigFId = tGroupBuildingBillCycleConfigFId;
		this.cashStatus = cashStatus;
		this.tRealroomSoftwareFeeFId = tRealroomSoftwareFeeFId;
		this.lastUnpaid = lastUnpaid;
		this.bankCollectionStatus = bankCollectionStatus;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("shortCode=").append(shortCode).append(";");
		sbf.append("amount=").append(amount).append(";");
		sbf.append("month=").append(month).append(";");
		sbf.append("billMonth=").append(billMonth).append(";");
		sbf.append("isPay=").append(isPay).append(";");
		sbf.append("payTime=").append(payTime).append(";");
		sbf.append("financeStatus=").append(financeStatus).append(";");
		sbf.append("deduPrice=").append(deduPrice).append(";");
		sbf.append("paymentWay=").append(paymentWay).append(";");
		sbf.append("tRealRoomFId=").append(tRealRoomFId).append(";");
		sbf.append("propertyProprietorId=").append(propertyProprietorId).append(";");
		sbf.append("succPayAmount=").append(succPayAmount).append(";");
		sbf.append("discount=").append(discount).append(";");
		sbf.append("prizeRecordId=").append(prizeRecordId).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("billTimeCfgId=").append(billTimeCfgId).append(";");
		sbf.append("billTypeId=").append(billTypeId).append(";");
		sbf.append("billTypeName=").append(billTypeName).append(";");
		sbf.append("isPropFee=").append(isPropFee).append(";");
		sbf.append("paytimeType=").append(paytimeType).append(";");
		sbf.append("cycleType=").append(cycleType).append(";");
		sbf.append("billMonthSize=").append(billMonthSize).append(";");
		sbf.append("billMonthStart=").append(billMonthStart).append(";");
		sbf.append("billMonthEnd=").append(billMonthEnd).append(";");
		sbf.append("payDayStart=").append(payDayStart).append(";");
		sbf.append("payDayEnd=").append(payDayEnd).append(";");
		sbf.append("hbAmount=").append(hbAmount).append(";");
		sbf.append("preferType=").append(preferType).append(";");
		sbf.append("tBillCycleId=").append(tBillCycleId).append(";");
		sbf.append("tGroupBuildingBillCycleConfigFId=").append(tGroupBuildingBillCycleConfigFId).append(";");
		sbf.append("cashStatus=").append(cashStatus).append(";");
		sbf.append("tRealroomSoftwareFeeFId=").append(tRealroomSoftwareFeeFId).append(";");
		sbf.append("lastUnpaid=").append(lastUnpaid).append(";");
		sbf.append("bankCollectionStatus=").append(bankCollectionStatus).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Integer getFinanceStatus() {
		return financeStatus;
	}
	public void setFinanceStatus(Integer financeStatus) {
		this.financeStatus = financeStatus;
	}
	public Long getDeduPrice() {
		return deduPrice;
	}
	public void setDeduPrice(Long deduPrice) {
		this.deduPrice = deduPrice;
	}
	public Integer getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}
	public BigInteger gettRealRoomFId() {
		return tRealRoomFId;
	}
	public void settRealRoomFId(BigInteger tRealRoomFId) {
		this.tRealRoomFId = tRealRoomFId;
	}
	public String getPropertyProprietorId() {
		return propertyProprietorId;
	}
	public void setPropertyProprietorId(String propertyProprietorId) {
		this.propertyProprietorId = propertyProprietorId;
	}
	public Long getSuccPayAmount() {
		return succPayAmount;
	}
	public void setSuccPayAmount(Long succPayAmount) {
		this.succPayAmount = succPayAmount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getPrizeRecordId() {
		return prizeRecordId;
	}
	public void setPrizeRecordId(String prizeRecordId) {
		this.prizeRecordId = prizeRecordId;
	}
	public BigInteger getBillTimeCfgId() {
		return billTimeCfgId;
	}
	public void setBillTimeCfgId(BigInteger billTimeCfgId) {
		this.billTimeCfgId = billTimeCfgId;
	}
	public BigInteger getBillTypeId() {
		return billTypeId;
	}
	public void setBillTypeId(BigInteger billTypeId) {
		this.billTypeId = billTypeId;
	}
	public String getBillTypeName() {
		return billTypeName;
	}
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}
	public Integer getIsPropFee() {
		return isPropFee;
	}
	public void setIsPropFee(Integer isPropFee) {
		this.isPropFee = isPropFee;
	}
	public Integer getPaytimeType() {
		return paytimeType;
	}
	public void setPaytimeType(Integer paytimeType) {
		this.paytimeType = paytimeType;
	}
	public Integer getCycleType() {
		return cycleType;
	}
	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}
	public Long getBillMonthSize() {
		return billMonthSize;
	}
	public void setBillMonthSize(Long billMonthSize) {
		this.billMonthSize = billMonthSize;
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
	public String getPayDayStart() {
		return payDayStart;
	}
	public void setPayDayStart(String payDayStart) {
		this.payDayStart = payDayStart;
	}
	public String getPayDayEnd() {
		return payDayEnd;
	}
	public void setPayDayEnd(String payDayEnd) {
		this.payDayEnd = payDayEnd;
	}
	public Long getHbAmount() {
		return hbAmount;
	}
	public void setHbAmount(Long hbAmount) {
		this.hbAmount = hbAmount;
	}
	public Integer getPreferType() {
		return preferType;
	}
	public void setPreferType(Integer preferType) {
		this.preferType = preferType;
	}
	public BigInteger gettBillCycleId() {
		return tBillCycleId;
	}
	public void settBillCycleId(BigInteger tBillCycleId) {
		this.tBillCycleId = tBillCycleId;
	}
	public BigInteger gettGroupBuildingBillCycleConfigFId() {
		return tGroupBuildingBillCycleConfigFId;
	}
	public void settGroupBuildingBillCycleConfigFId(BigInteger tGroupBuildingBillCycleConfigFId) {
		this.tGroupBuildingBillCycleConfigFId = tGroupBuildingBillCycleConfigFId;
	}
	public Integer getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}
	public BigInteger gettRealroomSoftwareFeeFId() {
		return tRealroomSoftwareFeeFId;
	}
	public void settRealroomSoftwareFeeFId(BigInteger tRealroomSoftwareFeeFId) {
		this.tRealroomSoftwareFeeFId = tRealroomSoftwareFeeFId;
	}
	public Long getLastUnpaid() {
		return lastUnpaid;
	}
	public void setLastUnpaid(Long lastUnpaid) {
		this.lastUnpaid = lastUnpaid;
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
		PayBill other = (PayBill) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
