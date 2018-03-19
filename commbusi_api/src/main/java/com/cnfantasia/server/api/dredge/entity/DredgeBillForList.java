package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.util.DredgeCombineStatusUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 给app的列表使用
 * 
 * @author wenfq
 *
 */
public class DredgeBillForList {
	private long id;
	private String parentTypeName;
	private String type;
	private String expectDate;//期望上门时间
	private String estimateDoorTime;//预计上门时间
	private String submitDate;
	private int status;
	private int isTransed;
	private int billType;
	private boolean canTrans;
	private String statusDesc;
	private BigDecimal payAmount;
	private BigDecimal selfBuyAmount;
	private String address;
	
	private double level;
	private String repairName;
	private String repairUrl;
	private String repairPhone;
	private Date updateTime;
	private Date addTime;
	
	private String dredgeTypePicURL;//维修类型图片地址
	private BigDecimal discountPrice; //活动价
	private String priceRange;//参考价格范围
	private List<AmountDetail> amountList = new ArrayList<AmountDetail>();

	// 1 待付款 2 待分配 3 待服务 4 已服务 5 已完成 6 已取消 7 退款中 8 已退款
	private Integer combineStatus;
	private String billNo;
	private String dredgeProductName;

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getDredgeTypePicURL() {
		if (dredgeTypePicURL != null) {
//			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, dredgeTypePicURL, null);
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, dredgeTypePicURL, null);
		}
		return dredgeTypePicURL;
	}

	public void setDredgeTypePicURL(String dredgeTypePicURL) {
		this.dredgeTypePicURL = dredgeTypePicURL;
	}

	public BigDecimal getPayAmount() {
		return payAmount == null ? null: payAmount.add(selfBuyAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(String expectDate) {
		this.expectDate = expectDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRepairName() {
		return repairName;
	}

	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}

	public String getRepairUrl() {
		if ((billType == 1 || billType == 2) && !StringUtils.isEmpty(repairUrl)) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.USER_IMAGE_PROFILE_CONFIG, repairUrl, null);
		} else if (billType == 1 || billType == 2){
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, DredgeConstant.DredgeMasterDefaultHeadImg, null);
		} else if (billType == 3 && !StringUtils.isEmpty(repairUrl)) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.Repair_Pic_BasePath, repairUrl, null);
		} else if (billType == 3){
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, DredgeConstant.DredgeMasterDefaultHeadImg, null);
		} else {
			return null;
		}
	}

	public void setRepairUrl(String repairUrl) {
		this.repairUrl = repairUrl;
	}

	public String getRepairPhone() {
		return repairPhone;
	}

	public void setRepairPhone(String repairPhone) {
		this.repairPhone = repairPhone;
	}

	public int getIsTransed() {
		return isTransed;
	}

	public void setIsTransed(int isTransed) {
		this.isTransed = isTransed;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public boolean isCanTrans() {
		return canTrans;
	}

	public void setCanTrans(boolean canTrans) {
		this.canTrans = canTrans;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getSelfBuyAmount() {
		return selfBuyAmount;
	}

	public void setSelfBuyAmount(BigDecimal selfBuyAmount) {
		this.selfBuyAmount = selfBuyAmount;
	}

	public String getEstimateDoorTime() {
		return estimateDoorTime;
	}

	public void setEstimateDoorTime(String estimateDoorTime) {
		this.estimateDoorTime = estimateDoorTime;
	}

	public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public List<AmountDetail> getAmountList() {
		boolean hasMFee = false;
		for (AmountDetail amountDetail : this.amountList) {
			if ("耗材费".equals(amountDetail.getFeeName())) {
				hasMFee = true;
				break;
			}
		}
		if (!hasMFee && this.selfBuyAmount != null && this.selfBuyAmount.compareTo(BigDecimal.ZERO) > 0) {
			AmountDetail amountDetail = new AmountDetail();
			amountDetail.setFeeAmount(this.selfBuyAmount);
			amountDetail.setFeeName("耗材费");
			amountDetail.setFeeType(DredgeConstant.DredgeBillAmountDetailType.SelfBuy_fee);
			amountList.add(amountDetail);
		}
		return amountList;
	}

	public void setAmountList(List<AmountDetail> amountList) {
		this.amountList = amountList;
	}

	public Integer getCombineStatus() {
		return DredgeCombineStatusUtil.getCombineStatus(billType, status);
	}

	public void setCombineStatus(Integer combineStatus) {
		this.combineStatus = combineStatus;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDredgeProductName() {
		return dredgeProductName;
	}

	public void setDredgeProductName(String dredgeProductName) {
		this.dredgeProductName = dredgeProductName;
	}
}
