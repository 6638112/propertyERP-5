package com.cnfantasia.server.api.ebuyorder.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/** 店铺信息
 * 
 * @author wenfq 2015-10-15 */
public class ShopInfo {
	/** 店铺名称 */
	String shopName;
	/** 联系人 */
	String linkName;
	/** 联系电话 */
	String linkPhone;
	/** 行政区id */
	BigInteger blockId;
	/** 店铺地址 */
	String shopAddress;

	/** 服务小区 */
	String[] serviceGbIds;

	/** 经营范围 */
	String[] serviceTypeIds;

	/** 店铺照片 */
	List<String> shopPicList = new ArrayList<String>();

	/** 店铺营业执照 */
	List<String> shopLicenseList = new ArrayList<String>();

	String introduce;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public BigInteger getBlockId() {
		return blockId;
	}

	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String[] getServiceGbIds() {
		return serviceGbIds;
	}

	public void setServiceGbIds(String[] serviceGbIds) {
		this.serviceGbIds = serviceGbIds;
	}

	public String[] getServiceTypeIds() {
		return serviceTypeIds;
	}

	public void setServiceTypeIds(String[] serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public List<String> getShopPicList() {
		return shopPicList;
	}

	public void setShopPicList(List<String> shopPicList) {
		this.shopPicList = shopPicList;
	}

	public List<String> getShopLicenseList() {
		return shopLicenseList;
	}

	public void setShopLicenseList(List<String> shopLicenseList) {
		this.shopLicenseList = shopLicenseList;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


}
