/**   
* Filename:    EbuyBuyCarHasTEbuyProductEntity_Product.java   
* @version:    1.0  
* Create at:   2014年6月27日 上午7:07:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * Filename:    EbuyBuyCarHasTEbuyProductEntity_Product.java
 * @version:    1.0.0
 * Create at:   2014年6月27日 上午7:07:31
 * Description:购物车-商品关系信息，包含商品详情
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月27日       shiyl             1.0             1.0 Version
 */
public class EbuyBuyCarHasTEbuyProductEntity_Product extends EbuyBuyCarHasTEbuyProduct implements Cloneable{
	private static final long serialVersionUID = 1L;
	
	private EbuyProductEntity ebuyProductEntity;
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
	
	/**购物车中商品的规格信息*/
	private EbuyProductSpec buyCarEbuyProductSpec;
	public EbuyProductSpec getBuyCarEbuyProductSpec() {
		return buyCarEbuyProductSpec;
	}
	public void setBuyCarEbuyProductSpec(EbuyProductSpec buyCarEbuyProductSpec) {
		this.buyCarEbuyProductSpec = buyCarEbuyProductSpec;
	}
	
	
}
