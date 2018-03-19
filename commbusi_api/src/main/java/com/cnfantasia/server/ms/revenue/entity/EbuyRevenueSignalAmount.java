package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class EbuyRevenueSignalAmount extends RevenueSignalAmount {
	
	private static final long serialVersionUID = 1408255921349413654L;
	
	private RevenueSignalAmountEbuy revenueSignalAmountEbuy;
	
	/** 该项可提款金额(初始为空) */
	private Double amountAgent;
	
	/** 提款状态 */
	private Integer tkStatusAgent;
	
	/** 该项可提款金额(初始为空) */
	private Double amountWy;
	
	/** 提款状态 */
	private Integer tkStatusWy;
	
	/** 该项可提款金额(初始为空) */
	private Double amountRepair;
	
	/** （物业 ）提款状态 */
	private Integer tkStatusRepair;
	
	/** 平台收益 */
	private Double amountPlatform;
	
	private Integer tkStatusPlatform;
	
	/** 平台收益 */
	private Double amountSupply;
	
	private Integer tkStatusSupply;
	
	private Double amountRecommender;
	
	private Integer tkStatusRecommender;
	
	private String recommenderName;
	
	private String orderNo; //订单号
	
	private String delivePeople;
	
	private String delivePhone;
	
	private String deliveAddress;
	
	private String supplyName; //店铺名称
	
	private List<EbuyRevenueProd> ebuyRevenueProdList;
	
	private Integer goalDetailType;//提款对象明细类型
	
	private String goalDetailTypeStr;//提款对象明细类型
	
	private Double payAmount;//订单金额

	public Double getDeliveTotalFee() {
		double total = 0.0;
		for(EbuyRevenueProd prod : ebuyRevenueProdList) {
			total = BigDecimalUtil.add(prod.getSettlePriceAll(), total);
		}
		
//		total = BigDecimalUtil.add(ebuyRevenueProdList.get(0).getDeliveFee(), total);
		return total;
	}
	

	public RevenueSignalAmountEbuy getRevenueSignalAmountEbuy() {
		return revenueSignalAmountEbuy;
	}

	public void setRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy) {
		this.revenueSignalAmountEbuy = revenueSignalAmountEbuy;
	}
	
	public Date getPayTm() {
		return DateUtils.strToDateTime(revenueSignalAmountEbuy.getPayTm());
	}
	
	public String getTkStatusStr() {
		if(RevenueDict.TkStatus.Doing.equals(getTkStatus())) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(getTkStatus())) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(getTkStatus())) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusWyStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusWy)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusWy)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusWy)) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusAgentStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusAgent)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusAgent)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusAgent)) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusRecommenderStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusRecommender)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusRecommender)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusRecommender)) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusRepairStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusRepair)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusRepair)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusRepair)) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusPlatformStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusPlatform)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusPlatform)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusPlatform)) {
			return "未结算";
		} else {
			return "";
		} 
	}
	
	public String getTkStatusSupplyStr() {
		if(RevenueDict.TkStatus.Doing.equals(tkStatusSupply)) {
			return "申请中";
		} else if(RevenueDict.TkStatus.Finished.equals(tkStatusSupply)) {
			return "已结算";
		} else if(RevenueDict.TkStatus.Undo.equals(tkStatusSupply)) {
			return "未结算";
		} else {
			return "";
		} 
	}

	@Override
	public void setId(BigInteger id) {
		super.setId(id);
//		revenueSignalAmountEbuy.settRevenueSignalAmountId(id);
	}

	public Double getAmountAgent() {
		return amountAgent;
	}

	public void setAmountAgent(Double amountAgent) {
		this.amountAgent = amountAgent;
	}

	public Integer getTkStatusAgent() {
		return tkStatusAgent;
	}

	public void setTkStatusAgent(Integer tkStatusAgent) {
		this.tkStatusAgent = tkStatusAgent;
	}

	public Double getAmountRepair() {
		return amountRepair;
	}

	public void setAmountRepair(Double amountRepair) {
		this.amountRepair = amountRepair;
	}

	public Integer getTkStatusRepair() {
		return tkStatusRepair;
	}

	public void setTkStatusRepair(Integer tkStatusRepair) {
		this.tkStatusRepair = tkStatusRepair;
	}

	public Double getAmountWy() {
		return amountWy;
	}

	public void setAmountWy(Double amountWy) {
		this.amountWy = amountWy;
	}

	public Integer getTkStatusWy() {
		return tkStatusWy;
	}

	public void setTkStatusWy(Integer tkStatusWy) {
		this.tkStatusWy = tkStatusWy;
	}

	public Double getAmountPlatform() {
		return amountPlatform;
	}

	public void setAmountPlatform(Double amountPlatform) {
		this.amountPlatform = amountPlatform;
	}

	public Integer getTkStatusPlatform() {
		return tkStatusPlatform;
	}

	public void setTkStatusPlatform(Integer tkStatusPlatform) {
		this.tkStatusPlatform = tkStatusPlatform;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<EbuyRevenueProd> getEbuyRevenueProdList() {
		return ebuyRevenueProdList;
	}

	public void setEbuyRevenueProdList(List<EbuyRevenueProd> ebuyRevenueProdList) {
		this.ebuyRevenueProdList = ebuyRevenueProdList;
	}


	public String getDelivePeople() {
		return delivePeople;
	}


	public void setDelivePeople(String delivePeople) {
		this.delivePeople = delivePeople;
	}


	public String getDelivePhone() {
		return delivePhone;
	}


	public void setDelivePhone(String delivePhone) {
		this.delivePhone = delivePhone;
	}


	public String getDeliveAddress() {
		return deliveAddress;
	}


	public void setDeliveAddress(String deliveAddress) {
		this.deliveAddress = deliveAddress;
	}


	public Double getAmountRecommender() {
		return amountRecommender;
	}


	public void setAmountRecommender(Double amountRecommender) {
		this.amountRecommender = amountRecommender;
	}


	public Integer getTkStatusRecommender() {
		return tkStatusRecommender;
	}


	public void setTkStatusRecommender(Integer tkStatusRecommender) {
		this.tkStatusRecommender = tkStatusRecommender;
	}


	public String getRecommenderName() {
		return recommenderName;
	}


	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}


	public Double getAmountSupply() {
		return amountSupply;
	}


	public void setAmountSupply(Double amountSupply) {
		this.amountSupply = amountSupply;
	}


	public Integer getTkStatusSupply() {
		return tkStatusSupply;
	}

	public void setTkStatusSupply(Integer tkStatusSupply) {
		this.tkStatusSupply = tkStatusSupply;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}


	public Integer getGoalDetailType() {
		return goalDetailType;
	}


	public void setGoalDetailType(Integer goalDetailType) {
		this.goalDetailType = goalDetailType;
	}


	public String getGoalDetailTypeStr() {
		if(goalDetailType != null) {
			if(goalDetailType == 0) {
				goalDetailTypeStr = "人工费";
			} else if(goalDetailType == 1) {
				goalDetailTypeStr = "其它费";
			}
		}
		return goalDetailTypeStr;
	}


	public void setGoalDetailTypeStr(String goalDetailTypeStr) {
		this.goalDetailTypeStr = goalDetailTypeStr;
	}


	public Double getPayAmount() {
		return payAmount;
	}


	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	
}
