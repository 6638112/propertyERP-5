/**   
* Filename:    SimpleResultMap.java   
* @version:    1.0  
* Create at:   2015年7月31日 下午5:53:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.util.HashMap;

/**
 * Filename:    SimpleResultMap.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 下午5:53:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public class SimpleResultMap<K, V> extends HashMap<K, V>{
	private static final long serialVersionUID = -4570297773730374878L;

	@Override
	public V put(K paramK, V paramV) {
		if(paramV!=null){//为空则不保存key,目的:节约流量
			return super.put(paramK, paramV);
		}
		return super.get(paramV);
	}
	
	
}
