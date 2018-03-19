/**   
* Filename:    OperationServiceAreaReload.java   
* @version:    1.0  
* Create at:   2015年7月8日 上午9:12:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.cache.AbstractCacheReload;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.operation.dao.IAddressOperationDao;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * Filename:    OperationServiceAreaReload.java
 * @version:    1.0.0
 * Create at:   2015年7月8日 上午9:12:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月8日       shiyl             1.0             1.0 Version
 */
public class OperationServiceAreaReload extends AbstractCacheReload{
	private Log logger = LogFactory.getLog(getClass());
	private IAddressOperationDao addressOperationDao;
	public void setAddressOperationDao(IAddressOperationDao addressOperationDao) {
		this.addressOperationDao = addressOperationDao;
	}
	
	@Override
	public synchronized void doReLoadAllData(boolean forceUpdate) {
		if(!forceUpdate&&!checkMeedReload()){return;}else{markAsReloaded();};
		try {
			logger.debug("OperationServiceAreaReload reLoadAllData start..");
			//初始化各个模块的数据
			List<OperationServiceArea> saListAll = addressOperationDao.selectOperationServiceAreaAll();
			Map<String,String> codeIdMap = new HashMap<String, String>();
			if(saListAll!=null&&saListAll.size()>0){
				for(OperationServiceArea tmpSa:saListAll){
					codeIdMap.put(tmpSa.getAddressUnique(), tmpSa.getId().toString());
				}
			}
			//TODO Try使用Redis事务
			RedisCacheHandler.clearCache(getModelKey());
			RedisCacheHandler.hmset(getModelKey(), codeIdMap);
			logger.debug("OperationServiceAreaReload reLoadAllData success..");
		} catch (Exception e) {
			logger.error("OperationServiceAreaReload reLoadAllData failed", e);
		}finally{
			logger.debug("OperationServiceAreaReload reLoadAllData finished..");
		}
	}
	
	@Override
	public String getModelKey() {
		return CacheConstant.ModelCode.hset_sa_codeid;
	}
	
	
}
