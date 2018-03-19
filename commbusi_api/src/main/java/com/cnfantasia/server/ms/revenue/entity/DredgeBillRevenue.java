package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.user.entity.User;

public class DredgeBillRevenue implements Serializable {
	
	private static final long serialVersionUID = -6369689948326741641L;
	
//	private RevenueConfig revenueConfig;
	
//	private DredgeBill dredgeBill;
	
	private Long id;
	
	private DredgeBillWithDetail dredgeBillWithDetail;
	
	private Long discountMoney; //优惠金额
	/**
	 * 用户实付金额
	 */
	private Long amountUsrReal; //现金缴费金额
	
	private Date payTm;
	
	private String pcName; //物业公司名称
	
	private BigInteger pcId; //物业ID
	
	private String agentName; //代理商名称
	
	private BigInteger agentId; //代理ID
	
	private BigInteger recommenderId;
	
	private String recommenderName; //手机号码即可为推荐人名称
	
	private String huaId; //下单者花号
	
	private String userName; //下单者用户名
	
	private String dredgeUserName; //师傅名称
	
	private String dredgeServiceName; //维修类型
	
	private String gbName; //小区名称
	
	private BigInteger gbId; //小区ID
	
	private BigInteger propertyManagementId; //管理处ID
	
	private String flowNo; //流水号
	
	private Integer payType; //支付渠道
	
	private List<User> userList;

//	public DredgeBill getDredgeBill() {
//		return dredgeBill;
//	}
//
//	public void setDredgeBill(DredgeBill dredgeBill) {
//		this.dredgeBill = dredgeBill;
//	}

	public Long getDiscountMoney() {
		return discountMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DredgeBillWithDetail getDredgeBillWithDetail() {
		return dredgeBillWithDetail;
	}

	public void setDredgeBillWithDetail(DredgeBillWithDetail dredgeBillWithDetail) {
		this.dredgeBillWithDetail = dredgeBillWithDetail;
	}

	public void setDiscountMoney(Long discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Long getAmountUsrReal() {
		return amountUsrReal;
	}

	public void setAmountUsrReal(Long amountUsrReal) {
		this.amountUsrReal = amountUsrReal;
	}

	public Date getPayTm() {
		return payTm;
	}

	public void setPayTm(Date payTm) {
		this.payTm = payTm;
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

	public String getHuaId() {
		return huaId;
	}

	public void setHuaId(String huaId) {
		this.huaId = huaId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getDredgeUserName() {
		return dredgeUserName;
	}

	public void setDredgeUserName(String dredgeUserName) {
		this.dredgeUserName = dredgeUserName;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getDredgeServiceName() {
		return dredgeServiceName;
	}

	public void setDredgeServiceName(String dredgeServiceName) {
		this.dredgeServiceName = dredgeServiceName;
	}

	public BigInteger getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(BigInteger recommenderId) {
		this.recommenderId = recommenderId;
	}

	public String getRecommenderName() {
		return recommenderName;
	}

	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public String getRevenueTm() {
		if(dredgeBillWithDetail.getRevenueTmAdd() == null || dredgeBillWithDetail.getRevenueTmAdd() == 0) {
			return DateUtils.getCurrentDate();
		} else {
			Date date = DateUtils.addDays(new Date(), getDredgeBillWithDetail().getRevenueTmAdd());
			date = DateUtils.convertStartTimeOfDate(date);
			return DateUtils.formatTime(date);
		}
	}

	public BigInteger getPropertyManagementId() {
		return propertyManagementId;
	}

	public void setPropertyManagementId(BigInteger propertyManagementId) {
		this.propertyManagementId = propertyManagementId;
	}

//	public RevenueConfig getRevenueConfig() {
//		return revenueConfig;
//	}
//
//	public void setRevenueConfig(RevenueConfig revenueConfig) {
//		this.revenueConfig = revenueConfig;
//	}

}
