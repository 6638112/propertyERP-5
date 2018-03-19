/**   
* Filename:    EbuyProductBuyInfoUtil.java   
* @version:    1.0  
* Create at:   2015年1月9日 上午10:07:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;

/**
 * Filename:    EbuyProductBuyInfoUtil.java
 * @version:    1.0.0
 * Create at:   2015年1月9日 上午10:07:53
 * Description:商品购买信息工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月9日       shiyl             1.0             1.0 Version
 */
public class EbuyProductBuyInfoUtil {
	/**
	 * 通过产品信息重组购买相关信息
	 * @param ebuyProductList
	 * @return
	 */
	public static Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> fetchProductDetailMap(List<ProductIdQtyEntity> productIdQtyList,List<EbuyProductWithCurrProductSpec> ebuyProductWithSpecList){
		Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap = new HashMap<ProductIdQtyEntity, EbuyProductWithCurrProductSpec>();
		for(EbuyProductWithCurrProductSpec ebuyProductWithCurrProductSpec:ebuyProductWithSpecList){
			boolean isContained = false;
			for(ProductIdQtyEntity productIdQtyEntity:productIdQtyList){
				if(ebuyProductWithCurrProductSpec.isEqualExt(productIdQtyEntity.getProductId(), productIdQtyEntity.getProductSpecId())){
					detailMap.put(productIdQtyEntity, ebuyProductWithCurrProductSpec);
					isContained = true;
					break;
				}
			}
			if(!isContained){throw new RuntimeException("The product list info cause some lose.ebuyProduct id is :"+ebuyProductWithCurrProductSpec.getProductId()+",productSpecId is "+ebuyProductWithCurrProductSpec.getProductSpecId());}
		}
		return detailMap;
	}
	
}
