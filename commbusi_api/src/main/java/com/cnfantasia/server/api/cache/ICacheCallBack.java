/**   
* Filename:    ICacheCallBack.java   
* @version:    1.0  
* Create at:   2015年7月8日 上午6:33:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache;

/**
 * Filename:    ICacheCallBack.java
 * @version:    1.0.0
 * Create at:   2015年7月8日 上午6:33:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月8日       shiyl             1.0             1.0 Version
 */
public interface ICacheCallBack {
	
	/**
	 * 回调函数，缓存对象销毁之前
	 * @param object		缓存对象
	 */
	public void onCacheObjectDestory(Object object);
	
	/**
	 * 回调函数，缓存对象销毁之后
	 */
	public void onCacheObjectDestoryed();
	
}
