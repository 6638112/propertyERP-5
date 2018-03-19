package com.cnfantasia.server.api.ebuyorder.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 给审核用的供应商Bean
 * @author wenfq 2015-10-22
 *
 */
public class Merchant4AuditBean {
	long shopId;
	
	/**
	 * 店铺名
	 */
	String shopName;
	/**
	 * 店铺区域
	 */
	String shopArea;
	/**
	 * 店铺地址
	 */
	String shopAddress;
	/**
	 * 营业执照
	 */
	List<String> businessPhotoesList = new ArrayList<String>();
	/**
	 * 起配金额
	 */
	double leastDeliveryAmt = 0;
	
	/**
	 * 店铺图片
	 */
	String shopPhotoes;
	
	List<String> shopPhotoeList = new ArrayList<String>();
	/**
	 * 店铺描述
	 */
	String shopIntroduce;

	/**
	 * 服务小区
	 */
	List<GroupBuilding> serviceGbList = new ArrayList<GroupBuilding>();

	/**
	 * 店主信息
	 */
	OwnerInfo ownerInfo;

	/**
	 * 行政区id
     */
	private BigInteger blockId;

	public List<String> getShopPhotoeList() {
		return shopPhotoeList;
	}

	public void setShopPhotoeList(List<String> shopPhotoeList) {
		this.shopPhotoeList = shopPhotoeList;
	}

	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	public String getShopPhotoes() {
		return shopPhotoes;
	}

	public void setShopPhotoes(String shopPhotoes) {
		this.shopPhotoes = shopPhotoes;
		if(shopPhotoeList == null ){
			shopPhotoeList = new ArrayList<String>();
		}
		
		if(StringUtils.isNotEmpty(shopPhotoes)){
			for(String s: shopPhotoes.split(";")){
				shopPhotoeList.add(s);
			}
		}
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public List<String> getBusinessPhotoesList() {
		return businessPhotoesList;
	}

	public void setBusinessPhotoesList(List<String> businessPhotoesList) {
		this.businessPhotoesList = businessPhotoesList;
	}

	public double getLeastDeliveryAmt() {
		return leastDeliveryAmt;
	}

	public void setLeastDeliveryAmt(double leastDeliveryAmt) {
		this.leastDeliveryAmt = leastDeliveryAmt;
	}

	public String getShopIntroduce() {
		return shopIntroduce;
	}

	public void setShopIntroduce(String shopIntroduce) {
		this.shopIntroduce = shopIntroduce;
	}

	public List<GroupBuilding> getServiceGbList() {
		return serviceGbList;
	}

	public void setServiceGbList(List<GroupBuilding> serviceGbList) {
		this.serviceGbList = serviceGbList;
	}

	public OwnerInfo getOwnerInfo() {
		return ownerInfo;
	}

	public void setOwnerInfo(OwnerInfo ownerInfo) {
		this.ownerInfo = ownerInfo;
	}

	public BigInteger getBlockId() {
		return blockId;
	}

	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}
}

