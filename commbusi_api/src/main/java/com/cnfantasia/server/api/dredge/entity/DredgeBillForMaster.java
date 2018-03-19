package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DateUtils;

/**
 * 师傅接单中心——疏通单
 * @author wenfq
 *
 */
public class DredgeBillForMaster {
	private long id;
	private String billNo;

	private int isPropertyBill = 0; //是否是物业维修单，默认为0，不是
	private String propertyTypeName; //物业维修的类型

	private String typeName;//类型名称
	private String subTypeName;//子类型名称
	private String typeImgUrl;//类型对应的图标url

	private String expectDate;//期望上门时间
	private String estimateDoorTime;//预计上门时间
	private String finishTime;//完成时间
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

	private String submitDate;
	private int status;
	private String statusDesc;
	private String address;
	private String content;//疏通内容
	private BigDecimal payAmount;
	private List<AmountDetail> amountList = new ArrayList<AmountDetail>();
	private List<String> picInfo;
	private String picUrls;

	private String userUdpTime;
	private String userName;
	private String userHeadImgUrl;
	private String userPhone;
	
	private long masterId;//维修师傅的id

	private double distance;//距离

	private int billType;
	
	private int selfBuyCount; //自选耗材件数
	private BigDecimal selfBuyAmount;//自选耗材金额合计
	
	private int dredgeTypeNum;//维修数量

	private List<DredgeProductSpecEntity> dredgeProductSpecList;//维修商品规格

	public int getDredgeTypeNum() {
		return dredgeTypeNum;
	}

	public void setDredgeTypeNum(int dredgeTypeNum) {
		this.dredgeTypeNum = dredgeTypeNum;
	}

	public int getSelfBuyCount() {
		return selfBuyCount;
	}

	public void setSelfBuyCount(int selfBuyCount) {
		this.selfBuyCount = selfBuyCount;
	}

	public BigDecimal getSelfBuyAmount() {
		return selfBuyAmount;
	}

	public void setSelfBuyAmount(BigDecimal selfBuyAmount) {
		this.selfBuyAmount = selfBuyAmount;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	public long getMasterId() {
		return masterId;
	}

	public void setMasterId(long masterId) {
		this.masterId = masterId;
	}

	public String getUserUdpTime() {
		return userUdpTime;
	}

	public void setUserUdpTime(String userUdpTime) {
		this.userUdpTime = userUdpTime;
	}

	public List<AmountDetail> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<AmountDetail> amountList) {
		this.amountList = amountList;
	}

	public int getIsPropertyBill() {
		return isPropertyBill;
	}

	public void setIsPropertyBill(int isPropertyBill) {
		this.isPropertyBill = isPropertyBill;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeImgUrl() {
		if (typeImgUrl != null) {
			Date updTime = DateUtils.convertStrToDate(submitDate, "yyyy-MM-dd HH:mm:ss");
//			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, typeImgUrl, updTime);
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, typeImgUrl, updTime);
		}
		return typeImgUrl;
	}

	public void setTypeImgUrl(String typeImgUrl) {
		this.typeImgUrl = typeImgUrl;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPayAmount() {
		return payAmount == null ? null: payAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public List<String> getPicInfo() {
		if (isPropertyBill == 0) {//上门维修
			DredgeService dredgeService = (DredgeService) CnfantasiaCommbusiUtil.getBeanManager("dredgeService");
			if (!StringUtils.isEmpty(picUrls)) {
				return dredgeService.getPicInfoList(this.billType == 2, picUrls, submitDate);
			}
		} else {//物业维修
			DredgeService dredgeService = (DredgeService) CnfantasiaCommbusiUtil.getBeanManager("dredgeService");
			if (!StringUtils.isEmpty(picUrls)) {
				return dredgeService.getPropertyPicInfoList(picUrls, submitDate);
			}
		}
		return picInfo;
	}

	public void setPicInfo(List<String> picInfo) {
		this.picInfo = picInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserHeadImgUrl() {
		if (!StringUtils.isEmpty(userHeadImgUrl)) {
			Date updTime = DateUtils.convertStrToDate(submitDate, "yyyy-MM-dd HH:mm:ss");
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.USER_IMAGE_PROFILE_CONFIG, userHeadImgUrl, updTime);
		}
		return userHeadImgUrl;
	}

	public void setUserHeadImgUrl(String userHeadImgUrl) {
		this.userHeadImgUrl = userHeadImgUrl;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public List<DredgeProductSpecEntity> getDredgeProductSpecList() {
		return dredgeProductSpecList;
	}

	public void setDredgeProductSpecList(List<DredgeProductSpecEntity> dredgeProductSpecList) {
		this.dredgeProductSpecList = dredgeProductSpecList;
	}
}
