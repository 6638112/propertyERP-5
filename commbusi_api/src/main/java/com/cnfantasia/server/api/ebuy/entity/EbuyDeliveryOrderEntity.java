/**   
* Filename:    EbuyDeliveryOrderEntity.java   
* @version:    1.0  
* Create at:   2014年7月7日 上午12:58:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * Filename:    EbuyDeliveryOrderEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月7日 上午12:58:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月7日       shiyl             1.0             1.0 Version
 */
public class EbuyDeliveryOrderEntity extends EbuyDeliveryOrder{
	private static final long serialVersionUID = 1L;
	/**
	 * 快递公司
	 */
	private EbuyExpressCompany ebuyExpressCompany;
	public EbuyExpressCompany getEbuyExpressCompany() {
		return ebuyExpressCompany;
	}
	public void setEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany) {
		this.ebuyExpressCompany = ebuyExpressCompany;
	}
	@Override
	public BigInteger gettEbuyExpressCompanyFId() {
		if(ebuyExpressCompany==null){return null;}
		return ebuyExpressCompany.getId();
	}
	@Override
	public void settEbuyExpressCompanyFId(BigInteger tEbuyExpressCompanyFId) {
		if(ebuyExpressCompany==null){ebuyExpressCompany= new EbuyExpressCompany();}
		ebuyExpressCompany.setId(tEbuyExpressCompanyFId);
	}
	/**
	 * 配送方式
	 */
	private EbuyDeliveryMethod ebuyDeliveryMethod;
	public EbuyDeliveryMethod getEbuyDeliveryMethod() {
		return ebuyDeliveryMethod;
	}
	public void setEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod) {
		this.ebuyDeliveryMethod = ebuyDeliveryMethod;
	}
	@Override
	public BigInteger getDeliveryId() {
		if(ebuyDeliveryMethod==null){return null;}
		return ebuyDeliveryMethod.getId();
	}
	@Override
	public void setDeliveryId(BigInteger deliveryId) {
		if(ebuyDeliveryMethod==null){ebuyDeliveryMethod = new EbuyDeliveryMethod();}
		ebuyDeliveryMethod.setId(deliveryId);
	}
	
	/**供应商信息*/
	private EbuySupplyMerchant ebuySupplyMerchant;
	public EbuySupplyMerchant getEbuySupplyMerchant() {
		return ebuySupplyMerchant;
	}
	public void setEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant) {
		this.ebuySupplyMerchant = ebuySupplyMerchant;
	}
	@Override
	public BigInteger gettSupplyMerchantFId() {
		if(ebuySupplyMerchant==null){
			return null;
		}
		return ebuySupplyMerchant.getId();
	}
	@Override
	public void settSupplyMerchantFId(BigInteger tSupplyMerchantFId) {
		if(ebuySupplyMerchant==null){
			ebuySupplyMerchant = new EbuySupplyMerchant();
		}
		ebuySupplyMerchant.setId(tSupplyMerchantFId);
	}
	
	/**
	 * 包含的商品信息
	 */
	private List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList;
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductList() {
		return ebuyDeliveryOrderProductList;
	}
	public void setEbuyDeliveryOrderProductList(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList) {
		this.ebuyDeliveryOrderProductList = ebuyDeliveryOrderProductList;
	}

	/** 是否进行了全额退款*/
	private boolean hasFullyRefund;
	public boolean isHasFullyRefund() {
		return hasFullyRefund;
	}
	public void setHasFullyRefund(boolean hasFullyRefund) {
		this.hasFullyRefund = hasFullyRefund;
	}

	private BigDecimal refundFee;

	public BigDecimal getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	private int deliveryType = 0;//是否用户自提：0否，1是

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}
}
