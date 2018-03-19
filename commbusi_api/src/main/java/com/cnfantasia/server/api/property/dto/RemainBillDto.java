package com.cnfantasia.server.api.property.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.access.entity.CarFeeType;

/**
 * 待缴费账单账单信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:34:17
 */
public class RemainBillDto {

	/** 是否出账单（1已经出账单，0未出账单） */
	private int isSucBill;
	/***/
	private BigInteger payBillId;
	/** 账单标题 */
	private String billTitle;
	/** 账单类型(1物业费；2非物业费；3车禁) */
	private String billType;
	/** 账单类型名称 */
	private String billTypeName;
	/** 账单类型图标 */
	private String billTypeImg;
	/** 账单类型灰色图标 */
	private String billTypeGrayImg;
	/** 账单金额(优惠前) */
	private String billAmt;
	/** 账单实付金额（优惠后） */
	private String billRelAmt;
	/** 单个月的金额（单位：元） */
	private String price;
	/** billAmt对应的月数 */
	private Integer firstMonth;
	/** 物业费billTypeName对应的开始时间 */
	private long monthStart;
	/** 是否有优惠(1有，0没有) */
	private int isPrefer;
	/** 优惠名称 */
	private String preferName;
	/** 优惠金额 */
	private String preferAmt;
	/** 缴费期间描述(12月4日-1月31日可在线缴费) */
	private String payDateDesc;
	/** 是否已经交费（1已支付，2未支付，3支付确认中,4银行托收中） */
	private int isPay;
	/** 是否抽取优惠(1有，0没有) */
	private int isGetPrefer;
	/** 物业宝是否抵扣0未抵扣，1已抵扣 */
	private int isFinanceStatus;
	/** 周期物业费类型（1固定周期，2选择周期） */
	private int propertyPeriodType;
	/** 选择周期缴费的缴费服务状态（1可以缴费，2欠费过多不能进行缴费） */
	private int periodPayServiceStatus;
	/** 数据来源类型0解放区平台，1为第三方极致物业 */
	private int dataFromType;
	/** 车牌 */
	private String carNum;
	/** 选择性周期的滞纳金（单位：元） */
	private String lateFeeAmt;
	// 是否收欠费，2收，其它情况不收，null也不收
	private Integer collectionArrearsType;
	private String billMonthStart;

	private Map<String, Object> extInfo;
	/** 月卡费用类型 */
	private List<CarFeeType> CarFeeTypeList;
	/** 物业费充值需要 */
	private BigInteger gbbccId;
	/** （充值）是否可缴费 */
	private boolean canPay;
	/** 最小收费金额（单位：元） */
	private BigDecimal minRecharge;
	/** 最大收费金额（单位：元），空为无限制 */
	private BigDecimal maxRecharge;

	public int getIsSucBill() {
		return isSucBill;
	}

	public void setIsSucBill(int isSucBill) {
		this.isSucBill = isSucBill;
	}

	public BigInteger getPayBillId() {
		return payBillId;
	}

	public void setPayBillId(BigInteger payBillId) {
		this.payBillId = payBillId;
	}

	public String getBillTitle() {
		if (billTitle != null) {
			billTitle = billTitle.replace("(", "（");
			billTitle = billTitle.replace(")", "）");
		}
		return billTitle;
	}

	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public String getBillTypeImg() {
		return billTypeImg;
	}

	public void setBillTypeImg(String billTypeImg) {
		this.billTypeImg = billTypeImg;
	}

	public String getBillTypeGrayImg() {
		return billTypeGrayImg;
	}

	public void setBillTypeGrayImg(String billTypeGrayImg) {
		this.billTypeGrayImg = billTypeGrayImg;
	}

	public String getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}

	public String getBillRelAmt() {
		return billRelAmt;
	}

	public void setBillRelAmt(String billRelAmt) {
		this.billRelAmt = billRelAmt;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getFirstMonth() {
		return firstMonth;
	}

	public void setFirstMonth(Integer firstMonth) {
		this.firstMonth = firstMonth;
	}

	public long getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(long monthStart) {
		this.monthStart = monthStart;
	}

	public int getIsPrefer() {
		return isPrefer;
	}

	public void setIsPrefer(int isPrefer) {
		this.isPrefer = isPrefer;
	}

	public String getPreferName() {
		return preferName;
	}

	public void setPreferName(String preferName) {
		this.preferName = preferName;
	}

	public String getPreferAmt() {
		return preferAmt;
	}

	public void setPreferAmt(String preferAmt) {
		this.preferAmt = preferAmt;
	}

	public String getPayDateDesc() {
		return payDateDesc;
	}

	public void setPayDateDesc(String payDateDesc) {
		this.payDateDesc = payDateDesc;
	}

	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public int getIsGetPrefer() {
		return isGetPrefer;
	}

	public void setIsGetPrefer(int isGetPrefer) {
		this.isGetPrefer = isGetPrefer;
	}

	public int getIsFinanceStatus() {
		return isFinanceStatus;
	}

	public void setIsFinanceStatus(int isFinanceStatus) {
		this.isFinanceStatus = isFinanceStatus;
	}

	public int getPropertyPeriodType() {
		return propertyPeriodType;
	}

	public void setPropertyPeriodType(int propertyPeriodType) {
		this.propertyPeriodType = propertyPeriodType;
	}

	public int getPeriodPayServiceStatus() {
		return periodPayServiceStatus;
	}

	public void setPeriodPayServiceStatus(int periodPayServiceStatus) {
		this.periodPayServiceStatus = periodPayServiceStatus;
	}

	public int getDataFromType() {
		return dataFromType;
	}

	public void setDataFromType(int dataFromType) {
		this.dataFromType = dataFromType;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getLateFeeAmt() {
		return lateFeeAmt;
	}

	public void setLateFeeAmt(String lateFeeAmt) {
		this.lateFeeAmt = lateFeeAmt;
	}

	public Integer getCollectionArrearsType() {
		return collectionArrearsType;
	}

	public void setCollectionArrearsType(Integer collectionArrearsType) {
		this.collectionArrearsType = collectionArrearsType;
	}

	public String getBillMonthStart() {
		return billMonthStart;
	}

	public void setBillMonthStart(String billMonthStart) {
		this.billMonthStart = billMonthStart;
	}

	public Map<String, Object> getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(Map<String, Object> extInfo) {
		this.extInfo = extInfo;
	}

	public List<CarFeeType> getCarFeeTypeList() {
		return CarFeeTypeList;
	}

	public void setCarFeeTypeList(List<CarFeeType> carFeeTypeList) {
		CarFeeTypeList = carFeeTypeList;
	}

	public BigInteger getGbbccId() {
		return gbbccId;
	}

	public void setGbbccId(BigInteger gbbccId) {
		this.gbbccId = gbbccId;
	}

	public boolean isCanPay() {
		return canPay;
	}

	public void setCanPay(boolean canPay) {
		this.canPay = canPay;
	}

	public BigDecimal getMinRecharge() {
		return minRecharge;
	}

	public void setMinRecharge(BigDecimal minRecharge) {
		this.minRecharge = minRecharge;
	}

	public BigDecimal getMaxRecharge() {
		return maxRecharge;
	}

	public void setMaxRecharge(BigDecimal maxRecharge) {
		this.maxRecharge = maxRecharge;
	}

}
