/**   
* Filename:    EbuyOrderBuyInfo.java   
* @version:    1.0  
* Create at:   2014年6月28日 上午8:11:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    EbuyOrderBuyInfo.java
 * @version:    1.0.0
 * Create at:   2014年6月28日 上午8:11:09
 * Description:订单购买的商品相关信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月28日       shiyl             1.0             1.0 Version
 */
public class EbuyOrderBuyInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 购物的商品列表
	 */
	private List<ProductIdQtyEntity> productIdQtyList;
	
//	public EbuyOrderBuyInfo(){}
	public EbuyOrderBuyInfo(ProductIdQtyEntity productIdQtyEntity){
		this.productIdQtyList = new ArrayList<ProductIdQtyEntity>();
		this.productIdQtyList.add(productIdQtyEntity);
	}
	public EbuyOrderBuyInfo(List<ProductIdQtyEntity> productIdQtyList){
		this.productIdQtyList = productIdQtyList;
	}
	
//	/**
//	 * 获取所有的商品Id的集合
//	 * @return
//	 */
//	public List<BigInteger> fetchProductIdList(){
//		List<BigInteger> list = new ArrayList<BigInteger>();
//		for(ProductIdQtyEntity productIdQtyEntity:productIdQtyList){
//			list.add(productIdQtyEntity.getProductId());
//		}
//		return list;
//	}
	
//	public Long fetchTotalCount(){
//		Long count = 0L;
//		for(ProductIdQtyEntity productIdQtyEntity:productIdQtyList){
//			count+=productIdQtyEntity.getProductQty().longValue();
//		}
//		return count;
//	}
	
//	public Long fetchTotalMoney(List<EbuyProduct> ebuyProductList){
//		Map<ProductIdQtyEntity,EbuyProduct> detailMap = fetchProductDetailMap(ebuyProductList);
//		Long money = 0L;
//		for(Map.Entry<ProductIdQtyEntity,EbuyProduct> tmoEntry:detailMap.entrySet()){
//			EbuyProduct ebuyProduct = tmoEntry.getValue();
//			ProductIdQtyEntity productIdQtyEntity = tmoEntry.getKey();
//			money+=ebuyProduct.getPriceDiscount()*productIdQtyEntity.getProductQty();
//		}
//		return money;
//	}
	
	
//	/**
//	 * 通过产品信息重组购买相关信息
//	 * @param ebuyProductList
//	 * @return
//	 */
//	public Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> fetchProductDetailMap(List<EbuyProductWithCurrProductSpec> ebuyProductWithSpecList){
//		Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap = new HashMap<ProductIdQtyEntity, EbuyProductWithCurrProductSpec>();
//		for(EbuyProductWithCurrProductSpec ebuyProductWithCurrProductSpec:ebuyProductWithSpecList){
//			boolean isContained = false;
//			EbuyProduct ebuyProduct = ebuyProductWithCurrProductSpec.getEbuyProduct();
//			for(ProductIdQtyEntity productIdQtyEntity:productIdQtyList){
//				if(ebuyProduct.getId().compareTo(productIdQtyEntity.getProductId())==0){
//					detailMap.put(productIdQtyEntity, ebuyProductWithCurrProductSpec);
//					isContained = true;
//					break;
//				}
//			}
//			if(!isContained){throw new RuntimeException("The product list info cause some lose.ebuyProduct info is :"+ebuyProduct);}
//		}
//		return detailMap;
//	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public List<ProductIdQtyEntity> getProductIdQtyList() {
		return productIdQtyList;
	}

	public void setProductIdQtyList(List<ProductIdQtyEntity> productIdQtyList) {
		this.productIdQtyList = productIdQtyList;
	}
	
}
