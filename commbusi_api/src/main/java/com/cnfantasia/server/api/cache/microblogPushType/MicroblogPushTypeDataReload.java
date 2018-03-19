package com.cnfantasia.server.api.cache.microblogPushType;

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
import com.cnfantasia.server.domainbase.microblogPushType.dao.IMicroblogPushTypeBaseDao;
import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;

public class MicroblogPushTypeDataReload extends AbstractCacheReload{
	private Log logger = LogFactory.getLog(getClass());
	private Log getLogger() {
		return logger;
	}
	
	private IMicroblogPushTypeBaseDao microblogPushTypeBaseDao;
	public void setMicroblogPushTypeBaseDao(
			IMicroblogPushTypeBaseDao microblogPushTypeBaseDao) {
		this.microblogPushTypeBaseDao = microblogPushTypeBaseDao;
	}

	private List<MicroblogPushType> getInitData(){
		List<MicroblogPushType> totalList = microblogPushTypeBaseDao.selectMicroblogPushTypeByCondition(null, true);
		return totalList;
	}
	
	@Override
	public void doReLoadAllData(boolean forceUpdate) {
		if(!forceUpdate&&!checkMeedReload()){return;}else{markAsReloaded();};
		Log logger = getLogger();
		List<MicroblogPushType> initData = getInitData();
		try {
			logger.debug("MicroblogPushType reLoadAllData start..");
			Map<String,String> id_JsonDetailMap = new HashMap<String, String>();
			if(initData!=null&&initData.size()>0){
				for(MicroblogPushType tmpData:initData){//逐个初始化数据
					id_JsonDetailMap.put(tmpData.getId()+"", JSON.toJSONString(tmpData));
				}
			}
			RedisCacheHandler.clearCache(getModelKey());//清除缓存
			RedisCacheHandler.hmset(getModelKey(), id_JsonDetailMap);
		} catch (Exception e) {
			logger.error("MicroblogPushType reLoadAllData failed", e);
		} finally {
			logger.debug("MicroblogPushType reLoadAllData finished..");
		}
		
	}

	@Override
	public String getModelKey() {
		return CacheConstant.ModelCode.hset_microblogPushType_info;
	}
	
	public static  void reloadIfNotExist(){
		MicroblogPushTypeDataReload entity = (MicroblogPushTypeDataReload)CnfantasiaCommbusiUtil.getBeanManager("microblogPushTypeDataReload");
		if(entity!=null){
			synchronized (entity) {
				if(entity.checkMeedReload()){
					entity.doReLoadAllData(false);
				}
			}
		}
	}
	
}


