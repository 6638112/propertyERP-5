package com.cnfantasia.server.api.room.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.building.entity.Building;


@Controller
@RequestMapping("/building")
public class BuildingController extends BaseController{
//	private IBuildingBaseService buildingBaseService;
//	public void setBuildingBaseService(IBuildingBaseService buildingBaseService) {
//		this.buildingBaseService = buildingBaseService;
//	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}

	private IRoomService roomService;
	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	@RequestMapping("/getBuildingListById.json")
	@ResponseBody
	public JsonResponse getBuildingListById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger groupBuildingId = ParamUtils.getBigInteger(request, "groupBuildingId", null);
		//2.交互
		List<Building> list = roomService.getBuildingListByGbId(groupBuildingId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(Building tmp:list){
			Map<String,Object> tmpMap = commEntityConvertService.building2Map(tmp);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
//	@RequestMapping("/getBuildingListById.json")
//	@ResponseBody
//	public JsonResponse getBuildingListById(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		String groupBuildingId = request.getParameter("groupBuildingId");
//		Building qry = new Building();
//		qry.settGroupBuildingFId(new BigInteger(groupBuildingId));
//		//2.交互
//		List<Building> list = buildingBaseService.getBuildingByCondition(MapConverter.toMap(qry));
//		//3.结果处理
//		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//		for(Building tmp:list){
//			Map<String,Object> tmpMap = commEntityConvertService.building2Map(tmp);
//			resList.add(tmpMap);
//		}
//		return ControllerUtils.addPageInfo(jsonResponse, resList);
//	}
	
}
