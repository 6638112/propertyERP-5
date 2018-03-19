/**   
* Filename:    SupportController.java   
* @version:    1.0  
* Create at:   2014年7月18日 上午6:57:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonKitchenService;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.support.service.ISupportService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    SupportController.java
 * @version:    1.0.0
 * Create at:   2014年7月18日 上午6:57:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月18日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/support")
public class SupportController extends BaseController{
	private ISupportService supportService;
	public void setSupportService(ISupportService supportService) {
		this.supportService = supportService;
	}
	
	private ICommonKitchenService commonKitchenService;
	public void setCommonKitchenService(ICommonKitchenService commonKitchenService) {
		this.commonKitchenService = commonKitchenService;
	}

	@RequestMapping("/doSupport.json")
	@ResponseBody
	public JsonResponse doSupport(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = null;
		String goalIdStr = request.getParameter("goalId");
		if(!StringUtils.isEmpty(goalIdStr)){
			goalId = new BigInteger(goalIdStr);
		}
		Boolean isSupport = ParamUtils.getBooleanNotNull(request, "isSupport", true);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		int totalCount = supportService.doSupport(userId, goalType, goalId, isSupport);
		//3.结果处理
		jsonResponse.putData("totalCount", totalCount);
		return jsonResponse;
	}
	
	@RequestMapping("/qryIsSupport.json")
	@ResponseBody
	public JsonResponse qryIsSupport(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean resBool = supportService.qryIsSupport(userId, goalType, goalId);
		//3.结果处理
		jsonResponse.putData("isSupport", resBool);
		return jsonResponse;
	}
	
	@RequestMapping("/qrySupportCount.json")
	@ResponseBody
	public JsonResponse qrySupportCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		//2.交互
		int resCount = supportService.qrySupportCount(goalType, goalId);
		//3.结果处理
		jsonResponse.putData("count", resCount);
		return jsonResponse;
	}
	
	@RequestMapping("/qryKitchenCookbookSupportList.json")
	@ResponseBody
	public JsonResponse qryKitchenCookbookSupportList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<KitchenEntity>  kitchenEntityList = supportService.getKitchenCookbookSupportList(userId,pageModel);
		//3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (KitchenEntity tmpKitchenEntity : kitchenEntityList) {
			Map<String, Object> tmpMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), null,
					tmpKitchenEntity.getIsLikeEat(), null,tmpKitchenEntity.getCollectFlag());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
}
