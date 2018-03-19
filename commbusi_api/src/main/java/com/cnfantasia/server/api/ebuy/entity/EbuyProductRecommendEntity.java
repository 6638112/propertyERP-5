/**   
* Filename:    EbuyProductRecommendEntity.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午5:53:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductRecommend.entity.EbuyProductRecommend;

/**
 * Filename:    EbuyProductRecommendEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午5:53:52
 * Description:推荐的商品
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public class EbuyProductRecommendEntity extends EbuyProductRecommend{
	private static final long serialVersionUID = 1L;
	/**商品详情*/
	private EbuyProductEntity ebuyProductInfo;
	public EbuyProductEntity getEbuyProductInfo() {
		return ebuyProductInfo;
	}
	public void setEbuyProductInfo(EbuyProductEntity ebuyProductInfo) {
		this.ebuyProductInfo = ebuyProductInfo;
	}
	@Override
	public BigInteger gettEbuyProductFId() {
		if(ebuyProductInfo==null ){return null;}
		return ebuyProductInfo.getId();
	}
	@Override
	public void settEbuyProductFId(BigInteger tEbuyProductFId) {
		if(ebuyProductInfo==null){ebuyProductInfo = new EbuyProductEntity();}
		ebuyProductInfo.setId(tEbuyProductFId);
	}

}
