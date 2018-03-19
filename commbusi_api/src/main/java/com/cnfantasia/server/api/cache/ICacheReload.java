/**   
* Filename:    ICacheReload.java   
* @version:    1.0  
* Create at:   2015年7月8日 上午9:10:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache;

import java.math.BigInteger;

/**
 * Filename:    ICacheReload.java
 * @version:    1.0.0
 * Create at:   2015年7月8日 上午9:10:17
 * Description:重新加载缓存
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月8日       shiyl             1.0             1.0 Version
 */
public interface ICacheReload {
	/**是否已加载数据:0未加载，1加载中，2加载完成*/
	public static int NotStart = 0;
//	public static int LoadIng = 1;
	public static int StartOk = 2;
	
	/**
	 * 
	 * @param forceUpdate 是否强制刷新
	 */
	public void doReLoadAllData(boolean forceUpdate);
	public String getModelKey();
	public String getModelKey(BigInteger id);
	
	/**检查是否需要重新加载数据*/
	public boolean checkMeedReload();
	/**标记为已加载*/
	public void markAsReloaded();
}
