package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

import java.math.BigDecimal;

public class PayBillEntity extends PayBill {

	private static final long serialVersionUID = -4324406873627559241L;

	/**
	 * 小区名称
	 */
	private String groupBuildingName;
	/**
	 * 小区名称
	 */
	private String groupBuildingId;

	/** 缴费周期开始时间 */
	private Integer payPeriodStart;

	/** 缴费周期结束时间 */
	private Integer payPeriodEnd;

	/**
	 * 楼栋号
	 */
	private String buildingName;

	/**
	 * 房间-单元号
	 */
	private String realRoomUnitName;

	/**
	 * 房间-房间号
	 */
	private String realRoomNum;

	/**
	 * 合同号
	 */
	private String contractNum;

	/**
	 * 业主姓名
	 */
	private String propertyProprietorName;

	/**
	 * 业主身份证号
	 */
	private String identifyNo;

	/**
	 * 状态更新人
	 */
	private String updateUserName;

	/**
	 * 状态更新人
	 */
	private String payUserName;


	/**
	 * 商户订单号
	 */
	private String orderNo;

	/**
	 * 交易渠道（"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":
	 * "微信轻应用支付"）
	 */

	private int payMethod;
	
	/**
	 * 物业代扣卡优惠金额
	 */
	private double cardSubsidyAmt;
	
	/**
	 * 物业代扣卡用户实缴金额
	 */
	private double cardRealPayAmt;
	/**
	 * 物业代扣卡用户的支付流水
	 */
	private String cardOrderNo;

	/**
	 * 为单独处理抄表三位小数的问题 多加了一个账单金额
	 */
	private BigDecimal amountBigDecimal;
	
	/**
	 * 缴费解放号
	 */
	private long buyerId;

	/**
	 * 本期应收  在get方法中计算了总金额数据
     */
	private Long totalSuccAmount;
	/**
	 * 往月欠费  并非是抄表的往月欠费
     */
	private Long lastUnpaids;

	/**
	 * 是否开始收缴欠费
     */
	private boolean isUnpaidFee;

	public String getCardOrderNo() {
		return cardOrderNo;
	}

	public void setCardOrderNo(String cardOrderNo) {
		this.cardOrderNo = cardOrderNo;
	}

	public double getCardRealPayAmt() {
		return cardRealPayAmt;
	}

	public void setCardRealPayAmt(double cardRealPayAmt) {
		this.cardRealPayAmt = cardRealPayAmt;
	}

	public double getCardSubsidyAmt() {
		return cardSubsidyAmt;
	}

	public void setCardSubsidyAmt(double cardSubsidyAmt) {
		this.cardSubsidyAmt = cardSubsidyAmt;
	}

	public String getGroupBuildingId() {
		return groupBuildingId;
	}

	public void setGroupBuildingId(String groupBuildingId) {
		this.groupBuildingId = groupBuildingId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRealRoomUnitName() {
		return realRoomUnitName;
	}

	public void setRealRoomUnitName(String realRoomUnitName) {
		this.realRoomUnitName = realRoomUnitName;
	}

	public String getRealRoomNum() {
		return realRoomNum;
	}

	public void setRealRoomNum(String realRoomNum) {
		this.realRoomNum = realRoomNum;
	}

	public String getPropertyProprietorName() {
		return propertyProprietorName;
	}

	public void setPropertyProprietorName(String propertyProprietorName) {
		this.propertyProprietorName = propertyProprietorName;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	public Integer getPayPeriodStart() {
		return payPeriodStart;
	}

	public void setPayPeriodStart(Integer payPeriodStart) {
		this.payPeriodStart = payPeriodStart;
	}

	public Integer getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(Integer payPeriodEnd) {
		this.payPeriodEnd = payPeriodEnd;
	}

	public String getPayUserName() {
		return payUserName;
	}

	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}

	/**
	 * 从f_month中获取年信息
	 * 
	 * getMonth()获得的日期格式是：yyyy年MM月
	 * 
	 * @return
	 */
	public String getYearFromMonthfiled() {
		return getMonth().substring(0, 4);
	}

	/**
	 * 从f_month中获取月信息
	 * <p>
	 * getMonth()获得的日期格式是：yyyy年MM月
	 * 
	 * 注意：如果是单月的话，还不能带08这样，必须转成8，因为mysql的month()函数返回的是整型
	 * 
	 * @return
	 */
	public String getMonthFromMonthfiled() {
		if (getMonth().substring(5, 7).startsWith("0")) {
			return getMonth().substring(6, 7);//小于10月的，要去掉0
		} else {
			return getMonth().substring(5, 7);//大于等于10月的
		}
	}
	
	/**
	 * 获得周期账单的描述串：账单开始日期 + 账单截止日期  + 小区名 + “_” + 楼栋 + "_" + 单元 + "_" + "房间号"
	 * @return
	 */
	public String getPeriodSummaryString(){
		return getBillTypeName() + getBillMonthStart() + getBillMonthEnd() + toString();
	}

	/**
	 * 小区名 + “_” + 楼栋 + "_" + 单元 + "_" + "房间号"
	 */
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(this.getGroupBuildingName()).append("_");
		sbf.append(this.getBuildingName()).append("_");
		sbf.append(this.getRealRoomUnitName()).append("_");
		sbf.append(this.getRealRoomNum());
		//sbf.append(this.getPropertyProprietorName());
		return sbf.toString();
	}
	
	/**物业公司名称*/
	private String pcName;

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	
	
	//syl-add-2015-11-19 22:05:55提款状态
	/**物业公司的提款状态*/
	private Integer tkStatus_wy;
	public Integer getTkStatus_wy() {
		return tkStatus_wy;
	}
	public void setTkStatus_wy(Integer tkStatus_wy) {
		this.tkStatus_wy = tkStatus_wy;
	}
	
	/**支付成功流水号*/
	private String payFlowNo;
	public String getPayFlowNo() {
		return payFlowNo;
	}
	public void setPayFlowNo(String payFlowNo) {
		this.payFlowNo = payFlowNo;
	}

	public BigDecimal getAmountBigDecimal() {
		return amountBigDecimal;
	}

	public void setAmountBigDecimal(BigDecimal amountBigDecimal) {
		this.amountBigDecimal = amountBigDecimal;
	}

	public Long getTotalSuccAmount() {
		totalSuccAmount = (lastUnpaids != null && getIsUnpaidFee() ? lastUnpaids: 0) + (getAmount() == null ? 0 : getAmount());
		return totalSuccAmount;
	}

	public void setTotalSuccAmount(Long totalSuccAmount) {
		this.totalSuccAmount = totalSuccAmount;
	}

	public Long getLastUnpaids() {
		return lastUnpaids;
	}

	public void setLastUnpaids(Long lastUnpaids) {
		this.lastUnpaids = lastUnpaids;
	}

	public boolean getIsUnpaidFee() {
		return isUnpaidFee;
	}

	public void setUnpaidFee(boolean unpaidFee) {
		isUnpaidFee = unpaidFee;
	}
}
