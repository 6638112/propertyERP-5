/**   
* Filename:    CollectionsController.java   
* @version:    1.0  
* Create at:   2014年7月18日 上午6:57:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.collections.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.collections.service.ICollectionsService;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.user.entity.CollectionsEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * Filename:    CollectionsController.java
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
@RequestMapping("/collections")
public class CollectionsController extends BaseController{
	private ICollectionsService collectionsService;
	public void setCollectionsService(ICollectionsService collectionsService) {
		this.collectionsService = collectionsService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}

	@RequestMapping("/doCollections.json")
	@ResponseBody
	public JsonResponse doCollections(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		
		Boolean isCollections = ParamUtils.getBooleanNotNull(request, "isCollections", true);
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		int totalCount = collectionsService.doCollections(userId, goalType, goalId, isCollections);
		//3.结果处理
		jsonResponse.putData("totalCount", totalCount);
		return jsonResponse;
	}
	
	@RequestMapping("/qryIsCollections.json")
	@ResponseBody
	public JsonResponse qryIsCollections(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean resBool = collectionsService.qryIsCollections(userId, goalType, goalId);
		//3.结果处理
		jsonResponse.putData("isCollections", resBool);
		return jsonResponse;
	}
	
	@RequestMapping("/qryCollectionsCount.json")
	@ResponseBody
	public JsonResponse qryCollectionsCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		//2.交互
		int resCount = collectionsService.qryCollectionsCount(goalType, goalId);
		//3.结果处理
		jsonResponse.putData("count", resCount);
		return jsonResponse;
	}
	
	/**
	 * 获取收藏列表 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCollecList.json")
	@ResponseBody
	public JsonResponse qryCollecList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);//分页信息
		Integer type =Integer.parseInt(request.getParameter("type"));//收藏对象的类型
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("40033");
		//2.交互
		List<CollectionsEntity> collecList = null;
		if(CommonModuleDict.CommonModule_TargetType.T_COMMUNITY_SUPPLY.compareTo(type)==0){
			collecList = collectionsService.getCollectionsCommunitySupplyList(userId,pageModel);
		}
		//3.结果处理
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CollectionsEntity collectionsEntity:collecList){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", collectionsEntity.getId());
			tmpMap.put("time", RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(collectionsEntity.getTime()),nowTimeLong));
			tmpMap.put("targetType", collectionsEntity.getTargetType());
			tmpMap.put("targetId", collectionsEntity.getTargetId());
			Object signalDetail = collectionsEntity.getSingalDetail();
			if(signalDetail instanceof CommunitySupplyEntity){
				CommunitySupplyEntity communitySupplyEntity = (CommunitySupplyEntity)signalDetail;
				CommunitySupplyType topCommunitySupplyType = null;
				if(communitySupplyEntity.getCommunitySupplyTypeEntity()!=null
						&&communitySupplyEntity.getCommunitySupplyTypeEntity().getTopCommunitySupplyType()!=null){
					topCommunitySupplyType = communitySupplyEntity.getCommunitySupplyTypeEntity().getTopCommunitySupplyType();
				}
				tmpMap.put("singalDetail", commEntityConvertService.communitySupply2Map(
						communitySupplyEntity,communitySupplyEntity.getCommunitySupplyContectList(),topCommunitySupplyType
				));
			}
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		
	}
	
}
