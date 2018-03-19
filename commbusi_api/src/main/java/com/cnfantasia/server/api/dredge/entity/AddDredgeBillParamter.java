package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

public class AddDredgeBillParamter {
	private PropertyRepair propertyRepair;
	private BigInteger userId;
	private String dredgeAddress;
	private BigInteger dredgeTypeId;
	private String dredgeContent;
	private Long expectDate;
    private String tel;
    private String referrerMobile;
    private BigInteger roomId;
    private BigInteger blockId;
    private String pics; //寻求帮助转单过来的图片
	private List<RequestFileEntity> picList;
    private BigInteger subTypeId;

	private List<ProductIdQtyEntity> productIdQtyList;//自选耗材id及数量
	private List<DredgeProductSpecEntity> productSpecList;
	private Integer dredgeTypeNum = 1;//维修数量

	private BigInteger dredgeProductId;
	private String linkName;

	//内转外时，传的dbId
	private BigInteger oldBillId;

	private Integer submitChannel = 1;

	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	
	public Integer getDredgeTypeNum() {
		return dredgeTypeNum;
	}
	public void setDredgeTypeNum(Integer dredgeTypeNum) {
		this.dredgeTypeNum = dredgeTypeNum;
	}
	public List<ProductIdQtyEntity> getProductIdQtyList() {
		return productIdQtyList;
	}
	public void setProductIdQtyList(List<ProductIdQtyEntity> productIdQtyList) {
		this.productIdQtyList = productIdQtyList;
	}
	public PropertyRepair getPropertyRepair() {
		return propertyRepair;
	}
	public void setPropertyRepair(PropertyRepair propertyRepair) {
		this.propertyRepair = propertyRepair;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getDredgeAddress() {
		return dredgeAddress;
	}
	public void setDredgeAddress(String dredgeAddress) {
		this.dredgeAddress = dredgeAddress;
	}
	public BigInteger getDredgeTypeId() {
		return dredgeTypeId;
	}
	public void setDredgeTypeId(BigInteger dredgeTypeId) {
		this.dredgeTypeId = dredgeTypeId;
	}
	public String getDredgeContent() {
		return dredgeContent;
	}
	public void setDredgeContent(String dredgeContent) {
		this.dredgeContent = dredgeContent;
	}
	
	public Long getExpectDate() {
		return expectDate;
	}
	public void setExpectDate(Long expectDate) {
		this.expectDate = expectDate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getReferrerMobile() {
		return referrerMobile;
	}
	public void setReferrerMobile(String referrerMobile) {
		this.referrerMobile = referrerMobile;
	}
	public BigInteger getRoomId() {
		return roomId;
	}
	public void setRoomId(BigInteger roomId) {
		this.roomId = roomId;
	}
	public BigInteger getBlockId() {
		return blockId;
	}
	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}
	public List<RequestFileEntity> getPicList() {
		return picList;
	}
	public void setPicList(List<RequestFileEntity> picList) {
		this.picList = picList;
	}
	public BigInteger getSubTypeId() {
		return subTypeId;
	}
	public void setSubTypeId(BigInteger subTypeId) {
		this.subTypeId = subTypeId;
	}

	public List<DredgeProductSpecEntity> getProductSpecList() {
		return productSpecList;
	}

	public void setProductSpecList(List<DredgeProductSpecEntity> productSpecList) {
		this.productSpecList = productSpecList;
	}

	public BigInteger getDredgeProductId() {
		return dredgeProductId;
	}

	public void setDredgeProductId(BigInteger dredgeProductId) {
		this.dredgeProductId = dredgeProductId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public BigInteger getOldBillId() {
		return oldBillId;
	}

	public void setOldBillId(BigInteger oldBillId) {
		this.oldBillId = oldBillId;
	}

	public Integer getSubmitChannel() {
		return submitChannel;
	}

	public void setSubmitChannel(Integer submitChannel) {
		this.submitChannel = submitChannel;
	}

	public AddDredgeBillParamter(PropertyRepair propertyRepair, BigInteger userId, String dredgeAddress,
								 BigInteger dredgeTypeId, String dredgeContent, Long expectDate, String tel, String referrerMobile,
								 BigInteger roomId, BigInteger blockId, List<RequestFileEntity> picList, BigInteger subTypeId) {
		super();
		this.propertyRepair = propertyRepair;
		this.userId = userId;
		this.dredgeAddress = dredgeAddress;
		this.dredgeTypeId = dredgeTypeId;
		this.dredgeContent = dredgeContent;
		this.expectDate = expectDate;
		this.tel = tel;
		this.referrerMobile = referrerMobile;
		this.roomId = roomId;
		this.blockId = blockId;
		this.picList = picList;
		this.subTypeId = subTypeId;
	}
	
	public AddDredgeBillParamter() {
	}
	
}
