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
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

@RequestMapping("/propertyRepair")
@Controller
public class PropertyRepairController extends BaseController {
	@Resource
	IPropertyRepairService propertyRepairService;
	
	@Resource
	ICommonRoomService commonRoomService;

	@Resource
	private IUuidManager uuidManager;

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

	@RequestMapping("/addRepair.json")
	@ResponseBody
	@RepeatReqValidate
	public JsonResponse addRepair(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String repairAddress = request.getParameter("repairAddress");
		BigInteger repairTypeId = new BigInteger(request.getParameter("repairTypeId"));
		String repairContent = request.getParameter("repairContent");
		String expectDate = request.getParameter("expectDate");
		String expectTimeBegin = request.getParameter("expectTimeBegin");
		String expectTimeEnd = request.getParameter("expectTimeEnd");
		String tel = request.getParameter("tel");

		if (repairContent != null && repairContent.length() > 1000) {
			throw new ValidateRuntimeException("propertyRepair.addRepair.repaireContent.length>1000");
		}

		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		//用户Id，还有可能从OOS帮帮看转单过来，见UserQuestionController.convertToDredgeBill()
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		if(userId == null)
			userId = UserContext.getOperIdMustExistBigInteger();
		
		BigInteger propertyRepairAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_repair);
		PropertyRepair propertyRepair = new PropertyRepair();
		propertyRepair.setId(propertyRepairAddId);
		propertyRepair.setAddress(repairAddress);
		propertyRepair.settPropertyRepairTypeFId(repairTypeId);
		propertyRepair.setRepairContent(repairContent);
		propertyRepair.setExpectDate(expectDate);
		propertyRepair.setExpectTimeBegin(expectTimeBegin);
		propertyRepair.setExpectTimeEnd(expectTimeEnd);
		propertyRepair.setRepaireState(0);//待分配
		propertyRepair.setTel(tel);
		propertyRepair.setPicUrl(ParamUtils.getString(request, "pics", null));//OOS寻求帮忙转过来的图片

		BigInteger roomId = ParamUtils.getBigInteger(request, "roomId", null);
		if(roomId == null) {
			roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		}
		propertyRepair.settRoomFId(roomId);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		if(gbId == null) {
			gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		}
		propertyRepair.settGroupBuildingFId(gbId);
		propertyRepair.settUserFId(userId);
		propertyRepair.setSys0AddUser(userId);
		propertyRepair.setIsTransed(0);

		//2.交互
		propertyRepairService.addRepair(propertyRepair, picList);
		jsonResponse.putData("repairBillId", propertyRepairAddId);

		//3.结果处理
		return jsonResponse;
	}

	@RequestMapping("/qryRepairList.json")
	@ResponseBody
	@Deprecated//维修合一，早就不用了
	public JsonResponse qryRepairList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		int type = Integer.parseInt(request.getParameter("type"));
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<PropertyRepair4List> propertyRepairList = propertyRepairService.getRepairList(userId, type, pageModel);
		//3.结果处理
		List<Map<String, Object>> resList = propertyRepairService.propertyRepair2MapList(userId, propertyRepairList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}

	@RequestMapping("/qryRepairDetail.json")
	@ResponseBody
	@Deprecated//维修合一，早就不用了
	public JsonResponse qryRepairDetail(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		String repairId = request.getParameter("id");
		PropertyRepairEntity propertyRepairEntity = propertyRepairService.qryRepairDetail(repairId);

		jsonResponse.setDataValue(propertyRepairService.propertyRepair2Map(propertyRepairEntity));
		return jsonResponse;
	}
}
