/**   
* Filename:    AbstractAddressDataReLoad.java   
* @version:    1.0  
* Create at:   2015年7月31日 上午11:36:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.address;

import java.util.List;

import org.apache.commons.logging.Log;

import com.cnfantasia.server.api.cache.AbstractCacheReload;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;

/**
 * Filename:    AbstractAddressDataReLoad.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 上午11:36:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractAddressDataReLoad extends AbstractCacheReload implements IAddressData{
	
	abstract Log getLogger();
	
	@Override
	public synchronized void doReLoadAllData(boolean forceUpdate) {
		if(!forceUpdate&&!checkMeedReload()){return;}else{markAsReloaded();};
		Log logger = getLogger();
		List<AddressDataInfo> initData = getInitData();
		String modelKey = getModelKey();
		try {
			logger.debug("reLoadAllData start..");
			//通过前缀清除缓存
			RedisCacheHandler.clearCachePerfixName(CacheConstant.appendModelCodePerfix(modelKey));//TODO Try实现逐步更新数据
			//逐个初始化数据
			if(initData!=null&&initData.size()>0){
				for(AddressDataInfo tmpData:initData){
					String currKey = getModelKey(tmpData.getId());
					RedisCacheHandler.hmset(currKey, tmpData.toMap());
				}
			}
		} catch (Exception e) {
			logger.error("reLoadAllData failed", e);
		} finally {
			logger.debug("reLoadAllData finished..");
		}
	}
	
	
}
