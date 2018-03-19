/**   
* Filename:    EbuyOrderHasTEbuyProductEntity_Product.java   
* @version:    1.0  
* Create at:   2014年6月9日 上午11:02:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * Filename:    EbuyOrderHasTEbuyProductEntity_Product.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 上午11:02:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuyOrderHasTEbuyProductEntity_Product extends EbuyOrderHasTEbuyProduct{
	private static final long serialVersionUID = 1L;
	
	private List<EbuyFilmTicket> filmTicketList;
	
	private EbuyFlowRecharge flowRecharge;
	
	/**商品信息*/
	private EbuyProductEntity ebuyProductEntity;

	/** 是否退款了*/
	private boolean hasRefund;

	public boolean isHasRefund() {
		return hasRefund;
	}

	public void setHasRefund(boolean hasRefund) {
		this.hasRefund = hasRefund;
	}

	public EbuyProductEntity getEbuyProductEntity() {
		return ebuyProductEntity;
	}
	public void setEbuyProductEntity(EbuyProductEntity ebuyProductEntity) {
		this.ebuyProductEntity = ebuyProductEntity;
	}
	@Override
	public BigInteger gettEbuyProductFId() {
		if(ebuyProductEntity==null){return null;}
		return ebuyProductEntity.getId();
	}
	@Override
	public void settEbuyProductFId(BigInteger tEbuyProductFId) {
		if(ebuyProductEntity==null){
			ebuyProductEntity = new EbuyProductEntity();
		}
		ebuyProductEntity.setId(tEbuyProductFId);
	}
	
	/**产品规格*/
	private EbuyProductSpec ebuyProductSpec;
	public EbuyProductSpec getEbuyProductSpec() {
		return ebuyProductSpec;
	}
	public void setEbuyProductSpec(EbuyProductSpec ebuyProductSpec) {
		this.ebuyProductSpec = ebuyProductSpec;
	}
	
	/**兑换码*/
	private List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList;
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeList() {
		return ebuyOrderProductHasCodeList;
	}
	public void setEbuyOrderProductHasCodeList(List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList) {
		this.ebuyOrderProductHasCodeList = ebuyOrderProductHasCodeList;
	}
	
	public List<EbuyFilmTicket> getFilmTicketList() {
		return filmTicketList;
	}
	
	public void setFilmTicketList(List<EbuyFilmTicket> filmTicketList) {
		this.filmTicketList = filmTicketList;
	}
	
	public EbuyFlowRecharge getFlowRecharge() {
		return flowRecharge;
	}
	
	public void setFlowRecharge(EbuyFlowRecharge flowRecharge) {
		this.flowRecharge = flowRecharge;
	}
	
//	/**配送方式*/
//	private EbuyDeliveryMethod ebuyDeliveryMethod;
//	public EbuyDeliveryMethod getEbuyDeliveryMethod() {
//		return ebuyDeliveryMethod;
//	}
//	public void setEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod) {
//		this.ebuyDeliveryMethod = ebuyDeliveryMethod;
//	}
//	@Override
//	public BigInteger getDeliveryId() {
//		if(ebuyDeliveryMethod==null){return null;}
//		return ebuyDeliveryMethod.getId();
//	}
//	@Override
//	public void setDeliveryId(BigInteger deliveryId) {
//		if(ebuyDeliveryMethod==null){
//			ebuyDeliveryMethod = new EbuyDeliveryMethod();
//		}
//		ebuyDeliveryMethod.setId(deliveryId);
//	}
	
//	/**供应商信息*/
//	private EbuySupplyMerchant ebuySupplyMerchant;
//	public EbuySupplyMerchant getEbuySupplyMerchant() {
//		return ebuySupplyMerchant;
//	}
//	public void setEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant) {
//		this.ebuySupplyMerchant = ebuySupplyMerchant;
//	}
//	@Override
//	public BigInteger getSupplyMerchantId() {
//		if(ebuySupplyMerchant==null){
//			return null;
//		}
//		return ebuySupplyMerchant.getId();
//	}
//	@Override
//	public void setSupplyMerchantId(BigInteger supplyMerchantId) {
//		if(ebuySupplyMerchant==null){
//			ebuySupplyMerchant = new EbuySupplyMerchant();
//		}
//		ebuySupplyMerchant.setId(supplyMerchantId);
//	}
	
	
}
