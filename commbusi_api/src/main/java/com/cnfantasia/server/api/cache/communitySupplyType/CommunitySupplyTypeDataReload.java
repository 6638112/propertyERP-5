/**   
* Filename:    CommunitySupplyTypeDataReload.java   
* @version:    1.0  
* Create at:   2015年8月19日 下午2:45:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.communitySupplyType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.AbstractCacheReload;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.communitySupplyType.dao.ICommunitySupplyTypeBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    CommunitySupplyTypeDataReload.java
 * @version:    1.0.0
 * Create at:   2015年8月19日 下午2:45:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月19日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyTypeDataReload extends AbstractCacheReload{
	private Log logger = LogFactory.getLog(getClass());
	private Log getLogger() {
		return logger;
	}
	
	private ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao;
	public void setCommunitySupplyTypeBaseDao(ICommunitySupplyTypeBaseDao communitySupplyTypeBaseDao) {
		this.communitySupplyTypeBaseDao = communitySupplyTypeBaseDao;
	}
	
	private List<CommunitySupplyType> getInitData(){
		List<CommunitySupplyType> totalList = communitySupplyTypeBaseDao.selectCommunitySupplyTypeByCondition(null, true);
		return totalList;
	}
	
	@Override
	public synchronized void doReLoadAllData(boolean forceUpdate) {
		if(!forceUpdate&&!checkMeedReload()){return;}else{markAsReloaded();};
		Log logger = getLogger();
		List<CommunitySupplyType> initData = getInitData();
		try {
			logger.debug("CommunitySupplyType reLoadAllData start..");
			Map<String,String> id_JsonDetailMap = new HashMap<String, String>();
			if(initData!=null&&initData.size()>0){
				for(CommunitySupplyType tmpData:initData){//逐个初始化数据
					id_JsonDetailMap.put(tmpData.getId()+"", JSON.toJSONString(tmpData));
				}
			}
			RedisCacheHandler.clearCache(getModelKey());//通过前缀清除缓存
			RedisCacheHandler.hmset(getModelKey(), id_JsonDetailMap);
		} catch (Exception e) {
			logger.error("CommunitySupplyType reLoadAllData failed", e);
		} finally {
			logger.debug("CommunitySupplyType reLoadAllData finished..");
		}
	}
	
	@Override
	public String getModelKey() {
		return CacheConstant.ModelCode.hset_commSupplyType_info;
	}
	
	public static  void reloadIfNotExist(){
		CommunitySupplyTypeDataReload entity = (CommunitySupplyTypeDataReload)CnfantasiaCommbusiUtil.getBeanManager("communitySupplyTypeDataReload");
		if(entity!=null){
			synchronized (entity) {
				if(entity.checkMeedReload()){
					entity.doReLoadAllData(false);
				}
			}
		}
	}
	
}
