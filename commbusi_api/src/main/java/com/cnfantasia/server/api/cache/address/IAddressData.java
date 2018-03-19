/**   
* Filename:    IAddressData.java   
* @version:    1.0  
* Create at:   2015年7月31日 上午11:13:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.address;

import java.util.List;

/**
 * Filename:    IAddressData.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 上午11:13:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public interface IAddressData {
	
	/**获取初始化加载的数据*/
	public List<AddressDataInfo> getInitData();
	
}
