package com.cnfantasia.server.api.ebuyProductFightGroups.entity;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by kangduo on 2016/5/27.
 */
public class EbuyProductFightGroupsDto implements Serializable {
	private static final long serialVersionUID = 1590734506141665670L;

	private BigInteger productFightGroupsId;
	private EbuyProductFightGroups ebuyProductFightGroups;
	private EbuySupplyMerchant ebuySupplyMerchant;
	private EbuyProduct ebuyProduct;

	private String ziTiDianName;

	private BigInteger productId;
	private String expireDate;
	private String fightStatus;
	private String productName;
	private String supplyMerchantName;

	private String sortType;
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public EbuyProductFightGroups getEbuyProductFightGroups() {
		return ebuyProductFightGroups;
	}

	public void setEbuyProductFightGroups(
			EbuyProductFightGroups ebuyProductFightGroups) {
		this.ebuyProductFightGroups = ebuyProductFightGroups;
	}

	public EbuySupplyMerchant getEbuySupplyMerchant() {
		return ebuySupplyMerchant;
	}

	public void setEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant) {
		this.ebuySupplyMerchant = ebuySupplyMerchant;
	}

	public EbuyProduct getEbuyProduct() {
		return ebuyProduct;
	}

	public void setEbuyProduct(EbuyProduct ebuyProduct) {
		this.ebuyProduct = ebuyProduct;
	}

	public BigInteger getProductFightGroupsId() {
		return productFightGroupsId;
	}

	public void setProductFightGroupsId(BigInteger productFightGroupsId) {
		this.productFightGroupsId = productFightGroupsId;
	}

	public BigInteger getProductId() {
		return productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getFightStatus() {
		return fightStatus;
	}

	public void setFightStatus(String fightStatus) {
		this.fightStatus = fightStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplyMerchantName() {
		return supplyMerchantName;
	}

	public void setSupplyMerchantName(String supplyMerchantName) {
		this.supplyMerchantName = supplyMerchantName;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getZiTiDianName() {
		return ziTiDianName;
	}

	public void setZiTiDianName(String ziTiDianName) {
		this.ziTiDianName = ziTiDianName;
	}

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

}
