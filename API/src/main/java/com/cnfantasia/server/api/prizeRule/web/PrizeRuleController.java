/**   
* Filename:    PrizeRuleController.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午7:01:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prizeRule.entity.PrizePoolGroupInfoEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizePoolInfoEntitySimple;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleLoaderService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    PrizeRuleController.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午7:01:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/prizeRule")
public class PrizeRuleController extends BaseController{
	
	private IPrizeRuleLoaderService prizeRuleLoaderService;
	public void setPrizeRuleLoaderService(IPrizeRuleLoaderService prizeRuleLoaderService) {
		this.prizeRuleLoaderService = prizeRuleLoaderService;
	}
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}

	/**
	 * 重新加载奖池配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/reloadPrizeRule.json")
	@ResponseBody
	public JsonResponse reloadPrizeRule(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		if(checkIdentify(request)){
			boolean resBool = prizeRuleLoaderService.reloadPrizeRule();
			if(resBool){
				jsonResponse.putData("result", "ok");
			}
		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询奖池剩余状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPrizePoolLeft.json")
	@ResponseBody
	public JsonResponse qryPrizePoolLeft(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		PrizePoolGroupInfoEntity prizePoolInfoEntity = null;
		if(checkIdentify(request)){
			prizePoolInfoEntity = prizeRuleLoaderService.getPrizePoolInfo();
			
		}
		//3.结果处理
		if(prizePoolInfoEntity!=null){
			Integer poolState = prizePoolInfoEntity.getPoolState();
			jsonResponse.putData("poolState",poolState );
			if(poolState!=null){
				String poolStateDesc = "";
				if(PrizeDict.PrizePool_State.NOT_START.compareTo(poolState)==0){
					poolStateDesc = "未开始";
				}else if(PrizeDict.PrizePool_State.DOING.compareTo(poolState)==0){
					poolStateDesc = "抽奖中";
				}else if(PrizeDict.PrizePool_State.STOPPED.compareTo(poolState)==0){
					poolStateDesc = "已暂停";
				}else if(PrizeDict.PrizePool_State.FINISHED.compareTo(poolState)==0){
					poolStateDesc = "已结束";
				}
				jsonResponse.putData("poolStateDesc", poolStateDesc);
			}
			List<PrizePoolInfoEntitySimple> simpleList = prizePoolInfoEntity.getSimpleList();
			List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
			if(simpleList!=null){
				for(PrizePoolInfoEntitySimple tmpSimple:simpleList){
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("currUsercountConfig", tmpSimple.getPrizeRuleGenerateConfigEntity());
					tmpMap.put("prizeArea", tmpSimple.getMultiPrizeArea().showData());
					resList.add(tmpMap);
				}
			}
			jsonResponse.putData("poolList", resList);
		}
		return jsonResponse;
	}
	
	private boolean checkIdentify(HttpServletRequest request){
		boolean res = commonLoginService.verifyAccount(request);
		return res;
	}
	
}
