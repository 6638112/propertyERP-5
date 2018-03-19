/**   
* Filename:    EbuyProductSpecEntity.java   
* @version:    1.0  
* Create at:   2015年1月9日 上午8:36:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * Filename:    EbuyProductSpecEntity.java
 * @version:    1.0.0
 * Create at:   2015年1月9日 上午8:36:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月9日       shiyl             1.0             1.0 Version
 */
public class EbuyProductSpecEntity extends EbuyProductSpec{
	private static final long serialVersionUID = 1L;
	
	/**商品信息*/
	private EbuyProduct ebuyProduct;
	public EbuyProduct getEbuyProduct() {
		return ebuyProduct;
	}
	public void setEbuyProduct(EbuyProduct ebuyProduct) {
		this.ebuyProduct = ebuyProduct;
	}
	
}
