/**   
* Filename:    EbuyOrderEntity.java   
* @version:    1.0  
* Create at:   2014年6月7日 上午8:58:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * Filename:    EbuyOrderEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月7日 上午8:58:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月7日       shiyl             1.0             1.0 Version
 */
public class EbuyOrderEntity extends EbuyOrder{
	private static final long serialVersionUID = 1L;
//	/**收货地址*/
//	private EbuyDeliveryAddressEntity ebuyDeliveryAddressEntity;
//	public EbuyDeliveryAddressEntity getEbuyDeliveryAddressEntity() {
//		return ebuyDeliveryAddressEntity;
//	}
//	public void setEbuyDeliveryAddressEntity(EbuyDeliveryAddressEntity ebuyDeliveryAddressEntity) {
//		this.ebuyDeliveryAddressEntity = ebuyDeliveryAddressEntity;
//	}
//	@Override
//	public BigInteger gettEbuyDeliveryAddressFId() {
//		if(ebuyDeliveryAddressEntity==null){return null;}
//		return ebuyDeliveryAddressEntity.getId();
//	}
//	@Override
//	public void settEbuyDeliveryAddressFId(BigInteger tEbuyDeliveryAddressFId) {
//		if(ebuyDeliveryAddressEntity==null){
//			ebuyDeliveryAddressEntity = new EbuyDeliveryAddressEntity();
//		}
//		ebuyDeliveryAddressEntity.setId(tEbuyDeliveryAddressFId);
//	}
	/**
	 * 订单包含的商品信息
	 */
	private List<EbuyOrderHasTEbuyProductEntity_Product> ebuyOrderHasTEbuyProductEntity_ProductList;
	public List<EbuyOrderHasTEbuyProductEntity_Product> getEbuyOrderHasTEbuyProductEntity_ProductList() {
		return ebuyOrderHasTEbuyProductEntity_ProductList;
	}
	public void setEbuyOrderHasTEbuyProductEntity_ProductList(
			List<EbuyOrderHasTEbuyProductEntity_Product> ebuyOrderHasTEbuyProductEntity_ProductList) {
		this.ebuyOrderHasTEbuyProductEntity_ProductList = ebuyOrderHasTEbuyProductEntity_ProductList;
	}
//	/**商品详情*/
//	private EbuyProductEntity ebuyProductEntity;
//	public EbuyProductEntity getEbuyProductEntity() {
//		return ebuyProductEntity;
//	}
//	public void setEbuyProductEntity(EbuyProductEntity ebuyProductEntity) {
//		this.ebuyProductEntity = ebuyProductEntity;
//	}
	
	
//	/**配送方式*/
//	private EbuyDeliveryMethod ebuyDeliveryMethod;
//	public EbuyDeliveryMethod getEbuyDeliveryMethod() {
//		return ebuyDeliveryMethod;
//	}
//	public void setEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod) {
//		this.ebuyDeliveryMethod = ebuyDeliveryMethod;
//	}
//	@Override
//	public BigInteger getDeliveryMethodId() {
//		if(ebuyDeliveryMethod==null){return null;}
//		return ebuyDeliveryMethod.getId();
//	}
//	@Override
//	public void setDeliveryMethodId(BigInteger deliveryMethodId) {
//		if(ebuyDeliveryMethod==null){
//			ebuyDeliveryMethod = new EbuyDeliveryMethod();
//		}
//		ebuyDeliveryMethod.setId(deliveryMethodId);
//	}
	/**
	 * 配送单列表
	 */
	private List<EbuyDeliveryOrderEntity> ebuyDeliveryOrderEntityList;
	public List<EbuyDeliveryOrderEntity> getEbuyDeliveryOrderEntityList() {
		return ebuyDeliveryOrderEntityList;
	}
	public void setEbuyDeliveryOrderEntityList(List<EbuyDeliveryOrderEntity> ebuyDeliveryOrderEntityList) {
		this.ebuyDeliveryOrderEntityList = ebuyDeliveryOrderEntityList;
	}
	
//	public EbuyDeliveryOrderEntity fetchEbuyDeliveryOrderEntityByMerId(BigInteger merId){
//		if(ebuyDeliveryOrderEntityList==null||ebuyDeliveryOrderEntityList.size()==0){return null;}
//		for(EbuyDeliveryOrderEntity tmp:ebuyDeliveryOrderEntityList){
//			if(tmp.gettSupplyMerchantFId()!=null&&merId!=null&&tmp.gettSupplyMerchantFId().compareTo(merId)==0){
//				return tmp;
//			}
//		}
//		return null;
//	}
	
//	/**
//	 * 优惠信息列表
//	 */
//	private List<PayCoupon> payCouponList;
//	public List<PayCoupon> getPayCouponList() {
//		return payCouponList;
//	}
//	public void setPayCouponList(List<PayCoupon> payCouponList) {
//		this.payCouponList = payCouponList;
//	}
//	public Long getPayCouponTotal(){
//		long total = 0;
//		if(payCouponList!=null&&payCouponList.size()>0){
//			for(PayCoupon payCoupon:payCouponList){
//				total+=payCoupon.getAmount();
//			}
//		}
//		return total;
//	}
//	public BigDecimal getPayCouponTotalDiv100(){
//		long total = getPayCouponTotal();
//		return PriceUtil.div100(total);
//	}
	
	/**
	 * 查询商品总数量
	 * @return
	 */
	public long getProductTotalCount(){
		long count = 0;
		if(ebuyOrderHasTEbuyProductEntity_ProductList!=null&&ebuyOrderHasTEbuyProductEntity_ProductList.size()>0){
			for(EbuyOrderHasTEbuyProductEntity_Product tmpEntity:ebuyOrderHasTEbuyProductEntity_ProductList){
				count+=tmpEntity.getProductQty();
			}
		}
		return count;
	}
	
	/**
	 * 查询商品总价
	 */
	public Long getProductTotalPrice(){
		long priceTotal = 0;
		if(ebuyOrderHasTEbuyProductEntity_ProductList!=null&&ebuyOrderHasTEbuyProductEntity_ProductList.size()>0){
			for(EbuyOrderHasTEbuyProductEntity_Product tmpEntity:ebuyOrderHasTEbuyProductEntity_ProductList){
//				EbuyProductWithCurrProductSpec currProduct= new EbuyProductWithCurrProductSpec(tmpEntity.getEbuyProductEntity(), tmpEntity.getEbuyProductSpec());
//				priceTotal+=tmpEntity.getEbuyProductEntity().getPriceDiscount()*tmpEntity.getProductQty();//乘以商品数量
//				priceTotal+=currProduct.getPriceDiscount()*tmpEntity.getProductQty();//乘以商品数量
				priceTotal+=tmpEntity.getProductPrice()*tmpEntity.getProductQty();//乘以商品数量
			}
		}
		return priceTotal;
	}
	public BigDecimal getProductTotalPriceDiv100(){
		long priceTotal = getProductTotalPrice();
		return PriceUtil.div100(priceTotal);
	}

	public BigDecimal getTotalRefundFee() {
		BigDecimal res = BigDecimal.valueOf(0);
		if (!DataUtil.isEmpty(ebuyDeliveryOrderEntityList)) {
			for (EbuyDeliveryOrderEntity ebuyDeliveryOrderEntity : ebuyDeliveryOrderEntityList) {
				res = res.add(ebuyDeliveryOrderEntity.getRefundFee() == null ? BigDecimal.valueOf(0) : ebuyDeliveryOrderEntity.getRefundFee());
			}
		}
		return res.setScale(2, RoundingMode.HALF_UP);
	}
	
//	/**
//	 * 按供应商分组
//	 * @return
//	 */
//	public Map<EbuySupplyMerchantEntity,List<EbuyOrderHasTEbuyProductEntity_Product>> groupByMerchant(){
//		Map<EbuySupplyMerchantEntity,List<EbuyOrderHasTEbuyProductEntity_Product>> resMap = new HashMap<EbuySupplyMerchantEntity, List<EbuyOrderHasTEbuyProductEntity_Product>>();
//		for(EbuyOrderHasTEbuyProductEntity_Product tmpEntity:ebuyOrderHasTEbuyProductEntity_ProductList){
//			EbuySupplyMerchantEntity merchant = tmpEntity.getEbuyProductEntity().getEbuySupplyMerchantEntity();
//			if(resMap.get(merchant)!=null){
//				resMap.get(merchant).add(tmpEntity);
//			}else{
//				List<EbuyOrderHasTEbuyProductEntity_Product> tmpList = new ArrayList<EbuyOrderHasTEbuyProductEntity_Product>();
//				tmpList.add(tmpEntity);
//				resMap.put(merchant, tmpList);
//			}
//		}
//		return resMap;
//	}
	
	/**根据配送单获取对应的商品购买信息*/
	public Set<EbuyOrderHasTEbuyProductEntity_Product> fetchEbuyOrderHasTEbuyProductEntityList(EbuyDeliveryOrderEntity ebuyDeliveryOrderEntity){
		Set<EbuyOrderHasTEbuyProductEntity_Product> resList = new HashSet<EbuyOrderHasTEbuyProductEntity_Product>();
		List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList = ebuyDeliveryOrderEntity.getEbuyDeliveryOrderProductList();
		if(ebuyDeliveryOrderProductList!=null&&ebuyDeliveryOrderProductList.size()>0){
			for(EbuyOrderHasTEbuyProductEntity_Product tmpEntity:ebuyOrderHasTEbuyProductEntity_ProductList){
				
				for(EbuyDeliveryOrderProduct tmpEbuyDeliveryOrderProduct:ebuyDeliveryOrderProductList){
					if(tmpEntity.getId().compareTo(tmpEbuyDeliveryOrderProduct.gettEbuyOrderHasTEbuyProductFId())==0){
						resList.add(tmpEntity);
						break;
					}
				}
				
			}
			
		}
		return resList;
	}
	
	
}
