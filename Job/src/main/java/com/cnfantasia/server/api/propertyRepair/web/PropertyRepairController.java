package com.cnfantasia.server.api.propertyRepair.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepair4List;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.api.propertyRepair.service.IPropertyRepairService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

@RequestMapping("/propertyRepair")
@Controller
public class PropertyRepairController extends BaseController {
	@Resource
	IPropertyRepairService propertyRepairService;
	
	@Resource
	ICommonRoomService commonRoomService;
	/**
	 * 查询是否开通报修
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryIsRepairEnable.json")
	@ResponseBody
	public JsonResponse qryIsRepairEnable(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("userId", userId);
		//paramMap.put("userId", 50059);

		//2.交互
		Map<String, Object> resMap = propertyRepairService.qryIsRepairEnable(paramMap);
		resMap.put("isEnable", (Long) resMap.get("isEnable") == 1L);
		resMap.put("isSupport", (Long) resMap.get("isSupport") == 1L);

		//3.结果处理
		jsonResponse.setDataValue(resMap);

		return jsonResponse;
	}

	/**
	 * 查询报修类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRepairTypes.json")
	@ResponseBody
	public JsonResponse qryRepairTypes(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数

		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		//2.交互
		List<PropertyRepairType> propertyRepairTypeList = propertyRepairService.getPropertyRepairTypeList_ByGbId(paramMap);
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < propertyRepairTypeList.size(); i++) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", propertyRepairTypeList.get(i).getId());
			tmpMap.put("typeName", propertyRepairTypeList.get(i).getName());
			resList.add(tmpMap);
		}

		//3.结果处理(不需要分页)
		jsonResponse.putData(PageModel.PageKey.LIST, resList);

		return jsonResponse;
	}

	@RequestMapping("/qryRepairList.json")
	@ResponseBody
	public JsonResponse qryRepairList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		int type = Integer.parseInt(request.getParameter("type"));
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<PropertyRepair4List> propertyRepairList = propertyRepairService.getRepairList(userId, type, pageModel);
		//3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		resList = propertyRepairService.propertyRepair2MapList(userId, propertyRepairList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}

	@RequestMapping("/qryRepairDetail.json")
	@ResponseBody
	public JsonResponse qryRepairDetail(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		String repairId = request.getParameter("id");
		PropertyRepairEntity propertyRepairEntity = propertyRepairService.qryRepairDetail(repairId);

		jsonResponse.setDataValue(propertyRepairService.propertyRepair2Map(propertyRepairEntity));
		return jsonResponse;
	}
}
