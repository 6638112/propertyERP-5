/**   
* Filename:    EbuyBuyCarEntityFamilyGroup.java   
* @version:    1.0  
* Create at:   2015年1月26日 上午8:35:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    EbuyBuyCarEntityFamilyGroup.java
 * @version:    1.0.0
 * Create at:   2015年1月26日 上午8:35:45
 * Description:家庭购物车组
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月26日       shiyl             1.0             1.0 Version
 */
public class EbuyBuyCarEntityFamilyGroup {
	
	public List<UserSimpleEntity> getHasProductBuyerList(){
		List<UserSimpleEntity> resList = new ArrayList<UserSimpleEntity>();
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
			for(EbuyBuyCarEntity tmp:ebuyBuyCarEntityList){
				UserSimpleEntity regUser = tmp.getRegUser();
				//已注册的用户并且有商品
				if(regUser!=null&&tmp.getEbuyBuyCarHasTEbuyProductEntity_ProductList()!=null
						&&tmp.getEbuyBuyCarHasTEbuyProductEntity_ProductList().size()>0){
					resList.add(regUser);
				}
			}
		}
		return resList;
	}
	
	/**家庭购物车列表*/
	private List<EbuyBuyCarEntity> ebuyBuyCarEntityList;
	public List<EbuyBuyCarEntity> getEbuyBuyCarEntityList() {
		return ebuyBuyCarEntityList;
	}
	public void setEbuyBuyCarEntityList(List<EbuyBuyCarEntity> ebuyBuyCarEntityList) {
		this.ebuyBuyCarEntityList = ebuyBuyCarEntityList;
	}
	
	public EbuyBuyCarEntityFamilyGroup(List<EbuyBuyCarEntity> ebuyBuyCarEntityList){
		this.ebuyBuyCarEntityList = ebuyBuyCarEntityList;
	}
	
	/**
	 * 查询商品总数量
	 * @return
	 */
	public long getProductTotalCount(){
		long count = 0;
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
			for(EbuyBuyCarEntity tmp:ebuyBuyCarEntityList){
				count+=tmp.getProductTotalCount();
			}
		}
		return count;
	}
	
	/**
	 * 查询商品类目总数
	 * @return
	 */
	public int getProductTotalCount_productType(){
		Set<BigInteger> productIdsSet = new HashSet<BigInteger>();
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
			for(EbuyBuyCarEntity tmp:ebuyBuyCarEntityList){
				for(EbuyBuyCarHasTEbuyProductEntity_Product tmpAA:tmp.getEbuyBuyCarHasTEbuyProductEntity_ProductList()){
					productIdsSet.add(tmpAA.gettEbuyProductFId());
				}
			}
		}
		return productIdsSet.size();
	}
	
	/**
	 * 按供应商分组
	 * @return
	 */
	public Map<EbuySupplyMerchantEntity, List<EbuyConfirm_ProductAndCount>> groupByMerchant() {
		Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroup = new HashMap<EbuySupplyMerchantEntity, List<EbuyConfirm_ProductAndCount>>();
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
			for(EbuyBuyCarEntity tmp:ebuyBuyCarEntityList){
				Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> tmpGroup = tmp.groupByMerchant();
				for(EbuySupplyMerchantEntity aaEbuySupplyMerchantEntity:tmpGroup.keySet()){
					List<EbuyConfirm_ProductAndCount> combiList = new ArrayList<EbuyConfirm_ProductAndCount>();
					if(merchantGroup.get(aaEbuySupplyMerchantEntity)==null){
						combiList = tmpGroup.get(aaEbuySupplyMerchantEntity);
					}else{//合并相同商品
						List<EbuyConfirm_ProductAndCount> existList = merchantGroup.get(aaEbuySupplyMerchantEntity);
						List<EbuyConfirm_ProductAndCount> currList = tmpGroup.get(aaEbuySupplyMerchantEntity);
						for(EbuyConfirm_ProductAndCount tmpExist:existList){//先录入和更新existList
							Long productQty = tmpExist.getProductQty();
							List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList = new ArrayList<EbuyBuyCarHasTEbuyProduct_WithExtData>();
							ebuyBuyCarHasTEbuyProduct_WithExtDataList.addAll(tmpExist.getEbuyBuyCarHasTEbuyProduct_WithExtDataList());
							for(EbuyConfirm_ProductAndCount tmpCurr:currList){
								if(tmpCurr.isEqualExt(tmpExist)){//商品相同
									productQty+=tmpCurr.getProductQty();
									ebuyBuyCarHasTEbuyProduct_WithExtDataList.addAll(tmpCurr.getEbuyBuyCarHasTEbuyProduct_WithExtDataList());
								}
							}
							combiList.add(new EbuyConfirm_ProductAndCount(tmpExist.getEbuyProductWithSpec(),productQty,ebuyBuyCarHasTEbuyProduct_WithExtDataList));
						}
						//再录入currList不在existList中的
						for(EbuyConfirm_ProductAndCount tmpCurr:currList){
							boolean isInExist = false;
							for(EbuyConfirm_ProductAndCount tmpExist:existList){
								if(tmpCurr.isEqualExt(tmpExist)){
									isInExist = true;
								}
							}
							if(!isInExist){
								combiList.add(tmpCurr);
							}
						}
					}
					merchantGroup.put(aaEbuySupplyMerchantEntity, combiList);
				}
			}
		}
		return merchantGroup;
	}
	
	public long getProductTotalPrice(){
		long priceTotal = 0;
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
			for(EbuyBuyCarEntity tmp:ebuyBuyCarEntityList){
				priceTotal += tmp.getProductTotalPrice();
			}
		}
		return priceTotal;
	}
	
	public BigDecimal getProductTotalPriceDiv100(){
		long priceTotal = getProductTotalPrice();
		return PriceUtil.div100(priceTotal);
	}
	
	//TODO 关于运费的计算
}
