package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @version:    1.0.0
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 */
public class FinanceBuyEntity implements Serializable {
	
	private static final long serialVersionUID = 7224573343202173631L;

	private String orderNo; //订单号
	
	private String hudId; //解放区花号
	
	private Long roomId; //房间ID
	
	private String buyPerson; //真实姓名
	
	private String buyMobile; //手机号码
	
	private String buyIdNo; //身份证号
	
	private Date deductionBeginTime; //抵扣开始日间
	
	private Date deductionEndTime; //抵扣结束日间
	
	private BigDecimal returnMoney; //返还金额
	
	private BigDecimal buyMoney; //购买产品金额
	
	private Date buyTime; //购买时间
	
	private Integer deductionCount;
	
	private Long finaceReqId;
	
	private Date deductionBeginDate;
	
	private Date deductionEndDate;
	
	private BigDecimal wyMoney;
	
	private BigDecimal wyRate;
	
	private Long propertyCompanyId;
	
	private Long channelPartnerId;
	
	private BigDecimal channelRate;
	
	private Date addTm;
	
	private FinanceReqEntity financeReqEntity;
	
	private String financeType;// 类别==>{1:物业宝；2：停车宝}
	
	private	String licensePlate;// 车牌号 
	
	private BigDecimal parkingFee;// 停车费
	
	public BigDecimal getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(BigDecimal parkingFee) {
		this.parkingFee = parkingFee;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getHudId() {
		return hudId;
	}

	public void setHudId(String hudId) {
		this.hudId = hudId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getBuyPerson() {
		return buyPerson;
	}

	public void setBuyPerson(String buyPerson) {
		this.buyPerson = buyPerson;
	}

	public String getBuyMobile() {
		return buyMobile;
	}

	public void setBuyMobile(String buyMobile) {
		this.buyMobile = buyMobile;
	}

	public String getBuyIdNo() {
		return buyIdNo;
	}

	public void setBuyIdNo(String buyIdNo) {
		this.buyIdNo = buyIdNo;
	}

	public Date getDeductionBeginTime() {
		return deductionBeginTime;
	}

	public void setDeductionBeginTime(Date deductionBeginTime) {
		this.deductionBeginTime = deductionBeginTime;
	}

	public Date getDeductionEndTime() {
		return deductionEndTime;
	}

	public void setDeductionEndTime(Date deductionEndTime) {
		this.deductionEndTime = deductionEndTime;
	}

	public BigDecimal getReturnMoney() {
		return returnMoney;
	}
	
//	public BigDecimal getReturnMoneyMonth() {
//		if(returnMoney != null && deductionCount != null) {
//			return returnMoney.divide(new BigDecimal(deductionCount), 5, BigDecimal.ROUND_DOWN);
//		}
//		return null;
//	}

	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Integer getDeductionCount() {
		return deductionCount;
	}

	public void setDeductionCount(Integer deductionCount) {
		this.deductionCount = deductionCount;
	}

	public Long getFinaceReqId() {
		return finaceReqId;
	}

	public void setFinaceReqId(Long finaceReqId) {
		this.finaceReqId = finaceReqId;
	}

	public Date getDeductionBeginDate() {
		return deductionBeginDate;
	}

	public void setDeductionBeginDate(Date deductionBeginDate) {
		this.deductionBeginDate = deductionBeginDate;
	}

	public Date getDeductionEndDate() {
		return deductionEndDate;
	}

	public void setDeductionEndDate(Date deductionEndDate) {
		this.deductionEndDate = deductionEndDate;
	}

	public BigDecimal getWyMoney() {
		return wyMoney;
	}

	public void setWyMoney(BigDecimal wyMoney) {
		this.wyMoney = wyMoney;
	}

	public BigDecimal getWyRate() {
		return wyRate;
	}

	public void setWyRate(BigDecimal wyRate) {
		this.wyRate = wyRate;
	}

	public BigDecimal getChannelRate() {
		return channelRate;
	}

	public void setChannelRate(BigDecimal channelRate) {
		this.channelRate = channelRate;
	}

	public Date getAddTm() {
		return addTm;
	}

	public void setAddTm(Date addTm) {
		this.addTm = addTm;
	}

	public FinanceReqEntity getFinanceReqEntity() {
		return financeReqEntity;
	}

	public void setFinanceReqEntity(FinanceReqEntity financeReqEntity) {
		this.financeReqEntity = financeReqEntity;
	}

	public Long getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(Long propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}

	public Long getChannelPartnerId() {
		return channelPartnerId;
	}

	public void setChannelPartnerId(Long channelPartnerId) {
		this.channelPartnerId = channelPartnerId;
	}

	public String getFinanceType() {
		return financeType;
	}

	public void setFinanceType(String financeType) {
		this.financeType = financeType;
	}
	
}
