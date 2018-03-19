/**   
* Filename:    AbstractCacheReload.java   
* @version:    1.0  
* Create at:   2015年7月31日 上午11:43:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache;

import java.math.BigInteger;

import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;

/**
 * Filename:    AbstractCacheReload.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 上午11:43:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractCacheReload implements ICacheReload{
	protected int haveReloadStatus = NotStart;
	@Override
	public String getModelKey(BigInteger id) {
		return CacheConstant.appendModelCodePerfix(getModelKey())+id.toString();
	}
	
	@Override
	public synchronized boolean checkMeedReload() {
		if(haveReloadStatus==NotStart){
			return true;
		}
		if(!RedisCacheHandler.exsits(getModelKey())){//没数据 需要加载
			return true;
		}
		return false;
	}
	
	
	@Override
	public synchronized void markAsReloaded() {
		haveReloadStatus = StartOk;
	}
	
	
}
