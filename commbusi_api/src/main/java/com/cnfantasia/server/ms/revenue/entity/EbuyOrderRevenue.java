package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class EbuyOrderRevenue implements Serializable {
	
	private static final long serialVersionUID = 2807348805785762854L;

	private BigInteger delivOrderId; //提款对象ID
	
	private BigInteger supplyId; //最小粒度角色ID
	
	private String supplyName; //店名
	
	private Integer smRevenueType; //供应商购代销模式：1:购销;2:代销;
	
	private Double smRevenueRate; //收益比率
	
	private Date recTm; //确认收货时间即产生收益的时间
	
	private Long delivFee; //配送费用
	
	private List<EbuyProdRevenue> ebuyProdRevenueList;
	
	private Date payTime; //支付时间
	
	private Long discountMoney; //优惠金额
	
	private Long refundMoney; //退款金额
	
	private BigInteger userId; //买家用户
	
	private String huaId; //花号
	
	private BigInteger groupBuildingId;
	
	private String groupBuildingName;
	
	private String flowNo; //流水号
	
	private Integer payType; //支付渠道
	
	private Long amountOrderReal; //订单实付金额
	
	private BigInteger pcId; //物业ID
	
	private BigInteger agentId; //代理ID
	
	private String pcName; //物业公司名称
	
	private String agentName; //代理商名称
	
	@Deprecated
	private BigInteger gbId;//小区id
	
	private BigInteger propertyManagementId; //管理处ID
	
//	private Long mount; //可提款金额

	public BigInteger getDelivOrderId() {
		return delivOrderId;
	}

	public void setDelivOrderId(BigInteger delivOrderId) {
		this.delivOrderId = delivOrderId;
	}
	
	/**
	 * 运单总额
	 * @return
	 */
	public Long getAmountTotal() {
		long mountTemp = 0L;
		for(EbuyProdRevenue ebuyProdRevenue : ebuyProdRevenueList) {
			mountTemp += ebuyProdRevenue.getPrice();
		}
		mountTemp += delivFee;
		return mountTemp;
	}

	/**
	 * 可提款金额
	 * @return
	 */
	public Long getAllMount() {
		long allMount = 0L;
		for(EbuyProdRevenue ebuyProdRevenue : ebuyProdRevenueList) {
			allMount += ebuyProdRevenue.getPrice();
		}
		allMount += delivFee;
		allMount -= refundMoney;
		return allMount;
	}
	
	public BigInteger getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(BigInteger supplyId) {
		this.supplyId = supplyId;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public Long getDelivFee() {
		return delivFee;
	}

	public void setDelivFee(Long delivFee) {
		this.delivFee = delivFee;
	}

	public Date getRecTm() {
		return recTm;
	}

	public void setRecTm(Date recTm) {
		this.recTm = recTm;
	}

	public List<EbuyProdRevenue> getEbuyProdRevenueList() {
		return ebuyProdRevenueList;
	}

	public void setEbuyProdRevenueList(List<EbuyProdRevenue> ebuyProdRevenueList) {
		this.ebuyProdRevenueList = ebuyProdRevenueList;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getDiscountMoney() {
		if(discountMoney == null) {
			discountMoney = 0L;
		}
		return discountMoney;
	}

	public void setDiscountMoney(Long discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Long getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Long refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getHuaId() {
		return huaId;
	}

	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	public String getGroupBuildingName() {
		return groupBuildingName;
	}

	public void setGroupBuildingName(String groupBuildingName) {
		this.groupBuildingName = groupBuildingName;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Long getAmountOrderReal() {
		return amountOrderReal;
	}

	public void setAmountOrderReal(Long amountOrderReal) {
		this.amountOrderReal = amountOrderReal;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getGroupBuildingId() {
		return groupBuildingId;
	}

	public void setGroupBuildingId(BigInteger groupBuildingId) {
		this.groupBuildingId = groupBuildingId;
	}

	public BigInteger getPcId() {
		return pcId;
	}

	public void setPcId(BigInteger pcId) {
		this.pcId = pcId;
	}

	public BigInteger getAgentId() {
		return agentId;
	}

	public void setAgentId(BigInteger agentId) {
		this.agentId = agentId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getSmRevenueType() {
		return smRevenueType;
	}

	public void setSmRevenueType(Integer smRevenueType) {
		this.smRevenueType = smRevenueType;
	}

	public Double getSmRevenueRate() {
		return smRevenueRate;
	}

	public void setSmRevenueRate(Double smRevenueRate) {
		this.smRevenueRate = smRevenueRate;
	}

	@Deprecated
	public BigInteger getGbId() {
		return gbId;
	}

	@Deprecated
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public BigInteger getPropertyManagementId() {
		return propertyManagementId;
	}

	public void setPropertyManagementId(BigInteger propertyManagementId) {
		this.propertyManagementId = propertyManagementId;
	}
	
}
