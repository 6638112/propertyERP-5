/**   
* Filename:    EbuyBuyCarEntity.java   
* @version:    1.0  
* Create at:   2014年6月27日 上午7:05:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;

/**
 * Filename:    EbuyBuyCarEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月27日 上午7:05:19
 * Description:购物车信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月27日       shiyl             1.0             1.0 Version
 */
public class EbuyBuyCarEntity extends EbuyBuyCar{
	private static final long serialVersionUID = 1L;
	
	/**已注册的购买者*/
	private UserSimpleEntity regUser;
	public UserSimpleEntity getRegUser() {
		return regUser;
	}
	public void setRegUser(UserSimpleEntity regUser) {
		this.regUser = regUser;
	}
	
	/**是否为已注册的用户*/
	public boolean checkIsRegUser(){
		if(this.getUserType()!=null&&this.getUserType().compareTo(LoginDict.UserRegistOrTmp.REGIST_USER)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**购物车包含的商品信息*/
	private List<EbuyBuyCarHasTEbuyProductEntity_Product> ebuyBuyCarHasTEbuyProductEntity_ProductList;
	public List<EbuyBuyCarHasTEbuyProductEntity_Product> getEbuyBuyCarHasTEbuyProductEntity_ProductList() {
		return ebuyBuyCarHasTEbuyProductEntity_ProductList;
	}
	public void setEbuyBuyCarHasTEbuyProductEntity_ProductList(
			List<EbuyBuyCarHasTEbuyProductEntity_Product> ebuyBuyCarHasTEbuyProductEntity_ProductList) {
		this.ebuyBuyCarHasTEbuyProductEntity_ProductList = ebuyBuyCarHasTEbuyProductEntity_ProductList;
	}
	
	/**
	 * 查询商品总数量
	 * @return
	 */
	public long getProductTotalCount(){
		long count = 0;
		if(ebuyBuyCarHasTEbuyProductEntity_ProductList!=null&&ebuyBuyCarHasTEbuyProductEntity_ProductList.size()>0){
			for(EbuyBuyCarHasTEbuyProductEntity_Product tmpEntity:ebuyBuyCarHasTEbuyProductEntity_ProductList){
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
		if(ebuyBuyCarHasTEbuyProductEntity_ProductList!=null&&ebuyBuyCarHasTEbuyProductEntity_ProductList.size()>0){
			for(EbuyBuyCarHasTEbuyProductEntity_Product tmpEntity:ebuyBuyCarHasTEbuyProductEntity_ProductList){
				EbuyProductWithCurrProductSpec ebuyProductWithSpec = new EbuyProductWithCurrProductSpec(tmpEntity.getEbuyProductEntity(),tmpEntity.getBuyCarEbuyProductSpec());
//				priceTotal+=tmpEntity.getEbuyProductEntity().getPriceDiscount()*tmpEntity.getProductQty();//乘以商品数量
				priceTotal+=ebuyProductWithSpec.getPriceDiscount()*tmpEntity.getProductQty();//乘以商品数量
			}
		}
		return priceTotal;
	}
	public BigDecimal getProductTotalPriceDiv100(){
		long priceTotal = getProductTotalPrice();
		return PriceUtil.div100(priceTotal);
	}
	
	/**
	 * 按供应商分组
	 * @return
	 */
	public Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> groupByMerchant(){
		Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> resMap = new HashMap<EbuySupplyMerchantEntity, List<EbuyConfirm_ProductAndCount>>();
		for(EbuyBuyCarHasTEbuyProductEntity_Product tmpEntity:ebuyBuyCarHasTEbuyProductEntity_ProductList){
			EbuySupplyMerchantEntity merchant = tmpEntity.getEbuyProductEntity().getEbuySupplyMerchantEntity();
			List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList = new ArrayList<EbuyBuyCarHasTEbuyProduct_WithExtData>();
			ebuyBuyCarHasTEbuyProduct_WithExtDataList.add(new EbuyBuyCarHasTEbuyProduct_WithExtData(regUser, tmpEntity));
			EbuyProductWithCurrProductSpec ebuyProductWithSpec = new EbuyProductWithCurrProductSpec(tmpEntity.getEbuyProductEntity(),tmpEntity.getBuyCarEbuyProductSpec());
			EbuyConfirm_ProductAndCount tmp2StoreData = new EbuyConfirm_ProductAndCount(ebuyProductWithSpec, tmpEntity.getProductQty(),ebuyBuyCarHasTEbuyProduct_WithExtDataList);
			if(resMap.get(merchant)!=null){
				resMap.get(merchant).add(tmp2StoreData);
			}else{
				List<EbuyConfirm_ProductAndCount> tmpList = new ArrayList<EbuyConfirm_ProductAndCount>();
				tmpList.add(tmp2StoreData);
				resMap.put(merchant, tmpList);
			}
		}
		return resMap;
	}
	
}
