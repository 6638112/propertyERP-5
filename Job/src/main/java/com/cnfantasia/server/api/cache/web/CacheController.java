/**   
* Filename:    CacheController.java   
* @version:    1.0  
* Create at:   2015年8月19日 下午3:30:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.cache.ICacheReload;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.api.operation.dao.IAddressOperationDao;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    CacheController.java
 * @version:    1.0.0
 * Create at:   2015年8月19日 下午3:30:04
 * Description:缓存刷新处理工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月19日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/cache")
public class CacheController extends BaseController{
	
	private IAddressOperationDao addressOperationDao;
	public void setAddressOperationDao(IAddressOperationDao addressOperationDao) {
		this.addressOperationDao = addressOperationDao;
	}
	/**服务范围数据*/
	private ICacheReload operationServiceAreaReload;
	public void setOperationServiceAreaReload(ICacheReload operationServiceAreaReload) {
		this.operationServiceAreaReload = operationServiceAreaReload;
	}
	
	
	/**商家类别数据*/
	private ICommunitySupplyDao communitySupplyDao;
	public void setCommunitySupplyDao(ICommunitySupplyDao communitySupplyDao) {
		this.communitySupplyDao = communitySupplyDao;
	}
	private ICacheReload communitySupplyTypeDataReload;
	public void setCommunitySupplyTypeDataReload(ICacheReload communitySupplyTypeDataReload) {
		this.communitySupplyTypeDataReload = communitySupplyTypeDataReload;
	}
	

	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	@RequestMapping("/refreshCacheAll.json")
	@ResponseBody
	public JsonResponse refreshCacheAll(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		boolean res = commonLoginService.verifyAccount(request);
		if(res){
			addressOperationDao.cleanCache();
//			operationServiceAreaReload.doReLoadAllData();
			
			communitySupplyDao.cleanCache();
//			communitySupplyTypeDataReload.doReLoadAllData();
			
			RedisCacheHandler.reLoadAllData();
			jsonResponse.putData("result", "ok");
		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 刷新服务范围数据的缓存
	 * 商家类别数据的缓存
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshSaCache.json")
	@ResponseBody
	public JsonResponse refreshSaCache(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		boolean res = commonLoginService.verifyAccount(request);
		if(res){
			addressOperationDao.cleanCache();
			operationServiceAreaReload.doReLoadAllData(true);
			jsonResponse.putData("result", "ok");
		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 刷新服务范围数据的缓存
	 * 商家类别数据的缓存
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshSupplyTypeCache.json")
	@ResponseBody
	public JsonResponse refreshSupplyTypeCache(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		boolean res = commonLoginService.verifyAccount(request);
		if(res){
			communitySupplyDao.cleanCache();
			communitySupplyTypeDataReload.doReLoadAllData(true);
			jsonResponse.putData("result", "ok");
		}
		//3.结果处理
		return jsonResponse;
	}
	
}
