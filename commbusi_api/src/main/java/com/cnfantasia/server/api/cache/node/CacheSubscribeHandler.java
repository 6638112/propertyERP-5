package com.cnfantasia.server.api.cache.node;

import java.io.Serializable;
import java.util.Map;

import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.api.access.service.BaseCarService;
import com.cnfantasia.server.api.access.service.ICarService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.api.cache.ICacheReload;
import com.cnfantasia.server.api.cache.dao.ICache2Dao;
import com.cnfantasia.server.api.cache.dto.CacheMsg;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.loan.constants.LoanDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.communitySupply.ICommunitySupplyManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;

/**
 * 多节点刷缓存，消息处理
 * 
 * @author liyulin
 * @version 1.0 2017年1月9日 上午10:12:36
 */
public class CacheSubscribeHandler implements ICacheSubscribeHandler {
	private ISysParamManager sysParamManager;
	private BaseCarService baseCarService;
	private ThirdCarFactory thirdCarFactory;
	/**因依赖问题，cache2Dao、hTCarService、operationServiceAreaReload、communitySupplyTypeDataReload、communitySupplyManager不用set方式注入*/
	private ICache2Dao cache2Dao;
	private ICacheReload operationServiceAreaReload;
	private ICacheReload communitySupplyTypeDataReload;
	private ICommunitySupplyManager communitySupplyManager;

	/**
	 * 消息处理
	 */
	@Override
	public void handleMessage(Serializable message) {
		if (message instanceof CacheMsg) {
			CacheMsg msg = (CacheMsg) message;
			switch (msg.getMsgType()) {
			case CacheConstants.MsgType.Refresh_All:
				refreshAll();
				break;
			case CacheConstants.MsgType.Refresh_SysParam:
				refreshSysParam();
				break;
			case CacheConstants.MsgType.Refresh_Mybatis_Cache:
				refreshMyBatisCache();
				break;
			case CacheConstants.MsgType.Refresh_All_HtCarGb_Cache:
				refreshAllHtCarGb();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 刷新所有缓存
	 */
	public void refreshAll() {
		refreshMyBatisCache();
		getOperationServiceAreaReload().doReLoadAllData(true);
		getCommunitySupplyTypeDataReload().doReLoadAllData(true);
		getCommunitySupplyManager().refreshCache();
		refreshSysParam();
		refreshAllHtCarGb();

		RedisCacheHandler.clearCachePerfixName(LoanDict.CACHE_KEY_PERFIX);
		RedisCacheHandler.clearCachePerfixName(AccessDict.CachePrefix.CARNUM_KEY_PREFIX);
		RedisCacheHandler.clearCachePerfixName(AccessDict.CachePrefix.TMP_CAR_PLOT_PREFIX);
		
		RedisCacheHandler.reLoadAllData();
	}

	/**
	 * 刷新t_comm_sys_para表缓存
	 */
	public void refreshSysParam() {
		sysParamManager.updSysParaValue();
	}

	/**
	 * 刷新mybatis缓存
	 */
	public void refreshMyBatisCache() {
		getICache2Dao().clearCacheForCommunitySupply();
		getICache2Dao().clearCacheForEbuyAdvertise();
		getICache2Dao().clearCacheForEbuyNew();
		getICache2Dao().clearCacheForEncrypt();
		getICache2Dao().clearCacheForOperation();
		getICache2Dao().clearCacheForVersion();
		getICache2Dao().clearAppFunctionBar();
	}
	
	/**
	 * 刷新车禁小区缓存
	 */
	public void refreshAllHtCarGb() {
		baseCarService.refreshAllHTCarGb();
		Map<Code, ICarService> carServiceMap = thirdCarFactory.getCarServiceMap();
		for (Map.Entry<Code, ICarService> entry : carServiceMap.entrySet()) {
			entry.getValue().initHTCarGb();
		}
	}
	
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	public void setBaseCarService(BaseCarService baseCarService) {
		this.baseCarService = baseCarService;
	}

	public void setThirdCarFactory(ThirdCarFactory thirdCarFactory) {
		this.thirdCarFactory = thirdCarFactory;
	}

	private ICache2Dao getICache2Dao() {
		if(cache2Dao == null) {
			cache2Dao = (ICache2Dao)CnfantasiaCommbusiUtil.getBeanManager("cache2Dao");
		}
		return cache2Dao;
	}	
	
	private ICacheReload getOperationServiceAreaReload() {
		if(operationServiceAreaReload == null) {
			operationServiceAreaReload = (ICacheReload)CnfantasiaCommbusiUtil.getBeanManager("operationServiceAreaReload");
		}
		return operationServiceAreaReload;
	}	 
	
	private ICacheReload getCommunitySupplyTypeDataReload() {
		if(communitySupplyTypeDataReload == null) {
			communitySupplyTypeDataReload = (ICacheReload)CnfantasiaCommbusiUtil.getBeanManager("communitySupplyTypeDataReload");
		}
		return communitySupplyTypeDataReload;
	}	 
	
	private ICommunitySupplyManager getCommunitySupplyManager() {
		if(communitySupplyManager == null) {
			communitySupplyManager = (ICommunitySupplyManager)CnfantasiaCommbusiUtil.getBeanManager("communitySupplyManager");
		}
		return communitySupplyManager;
	}	 
	
	
}
