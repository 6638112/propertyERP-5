package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

/**
 * 给app端的查看详情使用
 * @author wenfq
 *
 */
public class DredgeBillForDetail {
	private long id;
	private String billNo;//工单编号
	private String type;
	private String expectDate;//期望上门时间
	private String estimateDoorTime;//预计上门时间
	private String finishTime;//完成时间
	private String acceptTime;//派单时间
	private String updTime;//更新时间
	private String submitDate;
	private String content;
	private BigInteger userId;
	private String userPhone;
	private String linkName;
	private int billType;
	private int status;
	private String statusDesc;
	private String address;
	private BigDecimal payAmount;

	private List<AmountDetail> amountList = new ArrayList<AmountDetail>();
	private List<DredgeProductSpecEntity> dredgeProductSpecList;
	
	private String picUrl;//多张图片拼接在一起
	private List<String> picInfo;

	private Long orderId; //订单id
	private double orderSaveAmount; //优惠的金额

	private Long masterId;//师傅ID
	private String repairName;
	private String repairUrl;
	private String repairPhone;
	private String repairIdNumber; //身份证号

	private int serviceCount = 0;
	private double level = 5;
	private int isAppraised = 0;//是否已评价
	
	private BigInteger subTypeId;//子类型Id
	private String subTypeName;//子类型名称

	private BigInteger communitySupplyType;//维修类别
	private BigInteger dredgeTypeId;//维修类别子类
	
	private int selfBuyCount; //自选耗材件数
	private BigDecimal selfBuyAmount;//自选耗材金额合计
	
	private int dredgeTypeNum;//维修数量
	
	private String closeDesc;//关闭原因 

	public String getCloseDesc() {
		return closeDesc;
	}

	public void setCloseDesc(String closeDesc) {
		this.closeDesc = closeDesc;
	}

	public BigInteger getDredgeTypeId() {
		return dredgeTypeId;
	}

	public void setDredgeTypeId(BigInteger dredgeTypeId) {
		this.dredgeTypeId = dredgeTypeId;
	}

	public BigInteger getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(BigInteger subTypeId) {
		this.subTypeId = subTypeId;
	}

	public int getSelfBuyCount() {
		return selfBuyCount;
	}

	public void setSelfBuyCount(int selfBuyCount) {
		this.selfBuyCount = selfBuyCount;
	}

	public BigDecimal getSelfBuyAmount() {
		return selfBuyAmount == null ? BigDecimal.valueOf(0) : selfBuyAmount.setScale(2, RoundingMode.HALF_UP);
	}

	public void setSelfBuyAmount(BigDecimal selfBuyAmount) {
		this.selfBuyAmount = selfBuyAmount;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public List<AmountDetail> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<AmountDetail> amountList) {
		this.amountList = amountList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRepairIdNumber() {
		return repairIdNumber;
	}

	public void setRepairIdNumber(String repairIdNumber) {
		this.repairIdNumber = repairIdNumber;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public BigDecimal getPayAmount() {
		return payAmount == null ? null: payAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public List<String> getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(List<String> picInfo) {
		this.picInfo = picInfo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public int getIsAppraised() {
		return isAppraised;
	}

	public void setIsAppraised(int isAppraised) {
		this.isAppraised = isAppraised;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public double getOrderSaveAmount() {
		return orderSaveAmount;
	}

	public void setOrderSaveAmount(double orderSaveAmount) {
		this.orderSaveAmount = orderSaveAmount;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public BigInteger getCommunitySupplyType() {
		return communitySupplyType;
	}

	public void setCommunitySupplyType(BigInteger communitySupplyType) {
		this.communitySupplyType = communitySupplyType;
	}

	public int getDredgeTypeNum() {
		return dredgeTypeNum;
	}

	public void setDredgeTypeNum(int dredgeTypeNum) {
		this.dredgeTypeNum = dredgeTypeNum;
	}

	public String getEstimateDoorTime() {
		return estimateDoorTime;
	}

	public void setEstimateDoorTime(String estimateDoorTime) {
		this.estimateDoorTime = estimateDoorTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public List<DredgeProductSpecEntity> getDredgeProductSpecList() {
		return dredgeProductSpecList;
	}

	public void setDredgeProductSpecList(List<DredgeProductSpecEntity> dredgeProductSpecList) {
		this.dredgeProductSpecList = dredgeProductSpecList;
	}
}
