package com.cnfantasia.server.ms.ebuyProduct.entity;

import com.cnfantasia.server.domainbase.ebuyHomeType.entity.EbuyHomeType;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

public class EbuyProductEntity extends EbuyProduct {
	
	private static final long serialVersionUID = -2549349526243211428L;

	private EbuySupplyMerchant supplyMerchant;
	
	private EbuyProductType productType;
	
	private EbuyHomeType ebuyHomeType;
	
	private EbuyProductShelf ebuyProductShelf;
	
	private Integer countEqOnApp;// APP平台上t_ebuy_product表id与t_ebuy_product_shelf表id相同的数量
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;
	
	public EbuySupplyMerchant getSupplyMerchant() {
		return supplyMerchant;
	}

	public void setSupplyMerchant(EbuySupplyMerchant supplyMerchant) {
		this.supplyMerchant = supplyMerchant;
	}

	public EbuyProductType getProductType() {
		return productType;
	}

	public void setProductType(EbuyProductType productType) {
		this.productType = productType;
	}

	public EbuyHomeType getEbuyHomeType() {
		return ebuyHomeType;
	}

	public void setEbuyHomeType(EbuyHomeType ebuyHomeType) {
		this.ebuyHomeType = ebuyHomeType;
	}

	public EbuyProductShelf getEbuyProductShelf() {
		return ebuyProductShelf;
	}

	public void setEbuyProductShelf(EbuyProductShelf ebuyProductShelf) {
		this.ebuyProductShelf = ebuyProductShelf;
	}

	public Integer getCountEqOnApp() {
		return countEqOnApp;
	}

	public void setCountEqOnApp(Integer countEqOnApp) {
		this.countEqOnApp = countEqOnApp;
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
	
//	public String getStatusStr() {
//		if(getStatusAudit() != EbuyProductConstant.ProductAuditStatus.AUDIT_STATUS_PASSED) {
//			return EbuyProductConstant.STATUS_AUDIT_MAP.get(getStatusAudit());
//		} else {
//			return EbuyProductConstant.STATUS_MAP.get(getStatus());
//		}
//	}
	
}
