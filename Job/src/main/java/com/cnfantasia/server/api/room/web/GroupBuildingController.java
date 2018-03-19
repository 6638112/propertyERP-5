package com.cnfantasia.server.api.room.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntityWithDistance;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.api.room.tmpData.GbInfoConvert;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;


@Controller
@RequestMapping("/groupBuilding")
public class GroupBuildingController extends BaseController{
	private IGroupBuildingService groupBuildingService;
	public void setGroupBuildingService(IGroupBuildingService groupBuildingService) {
		this.groupBuildingService = groupBuildingService;
	}
	
	private IGroupBuildingBaseService groupBuildingBaseService;
	public void setGroupBuildingBaseService(IGroupBuildingBaseService groupBuildingBaseService) {
		this.groupBuildingBaseService = groupBuildingBaseService;
	}

	private IAddressCityBaseService addressCityBaseService;
	public void setAddressCityBaseService(IAddressCityBaseService addressCityBaseService) {
		this.addressCityBaseService = addressCityBaseService;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	@RequestMapping("/getGroupBuildingListById.json")
	@ResponseBody
	public JsonResponse getGroupBuildingListById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String blockId = request.getParameter("blockId");
		GroupBuilding qry = new GroupBuilding();
		qry.settBlockFId(new BigInteger(blockId));
		//2.交互
		List<GroupBuilding> list = groupBuildingBaseService.getGroupBuildingByCondition(MapConverter.toMap(qry));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(list!=null&&list.size()>0){
			for(GroupBuilding tmp:list){
				Map<String,Object> tmpMap = commEntityConvertService.groupBuilding2Map(tmp, null,null);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, list);
	}
	
	private GbQueryAddressParam initAddressParam(HttpServletRequest request){
		BaiduLocation baiduLocation = null;//百度地址
		{
			String longitudeStr = request.getParameter("longitude");
			String latitudeStr = request.getParameter("latitude");
			if(!StringUtils.isEmpty(longitudeStr)&&!StringUtils.isEmpty(latitudeStr)){
				baiduLocation = new BaiduLocation(Double.valueOf(longitudeStr), Double.valueOf(latitudeStr));
			}
		}
		BigInteger cityId = ParamUtils.getBigInteger(request, "cityId", null);
		BigInteger blockId  = ParamUtils.getBigInteger(request, "blockId", null);
		String cityName = request.getParameter("cityName");
		return new GbQueryAddressParam(cityId, cityName, blockId, baiduLocation);
	}
	
	@RequestMapping("/getGroupBuildingListByLocation.json")
	@ResponseBody
	public JsonResponse getGroupBuildingListByLocation(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
//		BaiduLocation baiduLocation = null;//百度地址
//		{
//			String longitudeStr = request.getParameter("longitude");
//			String latitudeStr = request.getParameter("latitude");
//			if(!StringUtils.isEmpty(longitudeStr)&&!StringUtils.isEmpty(latitudeStr)){
//				baiduLocation = new BaiduLocation(Double.valueOf(longitudeStr), Double.valueOf(latitudeStr));
//			}
//		}
		GbQueryAddressParam addressParam = initAddressParam(request);
		//搜索关键字
		String searchKey = request.getParameter("searchKey");
		BigInteger userId = UserContext.getOperIdBigInteger();
		Double maxDistance = RoomConstants.DEFAULT_MAX_LOCAT_DISTANCE_NEAR;
		//2.交互
		List<GroupBuildingEntityWithDistance> groupBuildingEntityList = commonRoomService.getNearbyGroupBuildingByBaiduLocation(addressParam, maxDistance,searchKey,userId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(groupBuildingEntityList!=null&&groupBuildingEntityList.size()>0){
			for(GroupBuildingEntity tmp:groupBuildingEntityList){
				Map<String,Object> tmpMap = commEntityConvertService.groupBuilding2Map(tmp, tmp.getAddressBlockEntity(),null);
				resList.add(tmpMap);
			}
		}
		if(groupBuildingEntityList!=null&&groupBuildingEntityList.size()>0){
			jsonResponse.putData("cityId", groupBuildingEntityList.get(0).getAddressBlockEntity().gettCityFId());
		}else{
			jsonResponse.putData("cityId", null);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/qryGroupBuildingDim.json")
	@ResponseBody
	public JsonResponse qryGroupBuildingDim(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String searchKey = request.getParameter("searchKey");
		searchKey = searchKey==null?"":searchKey;
//		BigInteger cityId = ParamUtils.getBigInteger(request, "cityId", null);
//		BigInteger blockId  = ParamUtils.getBigInteger(request, "blockId ", null);
//		String cityName = request.getParameter("cityName");
		GbQueryAddressParam addressParam = initAddressParam(request);
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<GroupBuildingEntity> list = groupBuildingService.getGroupBuildingDimBySearchKey(searchKey,addressParam,pageModel,userId);
		//根据cityName查询cityId
		AddressCity addressCityQry = new AddressCity();
		addressCityQry.setName(addressParam.getCityName());
		List<AddressCity> addressCityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.toMap(addressCityQry));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(GroupBuildingEntity tmp:list){
			Map<String,Object> tmpMap = commEntityConvertService.groupBuilding2Map(tmp, tmp.getAddressBlockEntity(),null);
			resList.add(tmpMap);
		}
		if(addressCityList!=null&&addressCityList.size()>0){
			jsonResponse.putData("cityId", addressCityList.get(0).getId());
		}else{
			jsonResponse.putData("cityId", null);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/getAllBuildingAndRoom.json")
	@ResponseBody
	public JsonResponse getAllBuildingAndRoom(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger groupBuildingId = new BigInteger(request.getParameter("groupBuildingId"));
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
//		JsonResponse tmpJsonResponse = BuildingCacheUtil.getBuildingCacheObj(groupBuildingId);
//		if(tmpJsonResponse!=null){return tmpJsonResponse;}
		List<BuildingAndRealRoomListEntity> list = groupBuildingService.getBuildingAndRoomList(groupBuildingId);
		RoomEntity defaultRoomEntity = userId==null?null:commonRoomService.getDefaultRoomInfo(userId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(BuildingAndRealRoomListEntity tmpEntity:list){
			Map<String,Object> tmpMap = buildingAndRealRoomListEntity2Map(tmpEntity, tmpEntity.getRealRroomList(),defaultRoomEntity);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	private Map<String,Object> buildingAndRealRoomListEntity2Map(Building building,List<RealRoom> realRroomList,RoomEntity defaultRoomEntity){
		BigInteger buildingId = null;
		String buildingName = null;
		BigInteger realRoomId = null;
		String realRoomNum = null;
		try {
			realRoomId = defaultRoomEntity.getRealRoomEntity().getId();
			realRoomNum = defaultRoomEntity.getRealRoomEntity().getNum();
			buildingId = defaultRoomEntity.getRealRoomEntity().getBuildingEntity().getId();
			buildingName = defaultRoomEntity.getRealRoomEntity().getBuildingEntity().getName();
		} catch (Exception e) {
		}
		
		Map<String,Object> resMap = null;
		{
//			resMap = commEntityConvertService.building2Map(building,buildingId,buildingName);
			Map<String,Object> tmpMap = new SimpleResultMap<String, Object>();
			tmpMap.put("id", building.getId());
			String name = RoomEntityConvertUtil.getBuildingShowName(building);
			tmpMap.put("name", name);
			if(buildingId!=null&&building.getId().compareTo(buildingId)==0){
				tmpMap.put("select", true);
			}
			if(buildingName!=null&&buildingName.trim().equals(building.getName())){
				tmpMap.put("select", true);
			}
			resMap = tmpMap;
		}
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//		Long start = new Date().getTime();
		for(RealRoom realRoom:realRroomList){
//			commEntityConvertService.achievement2Map(null);
//			commEntityConvertService.realRoom2Map(realRoom, realRoomId, realRoomNum);
			Map<String,Object> tmpMap = GbInfoConvert.getSignalInstance().stack01(realRoom, realRoomId, realRoomNum);
			resList.add(tmpMap);
		}
//		logger.debug("time:"+((new Date().getTime())-start));
		resMap.put("realRroomList", resList);
		return resMap;
	}
	
//	private Log logger = LogFactory.getLog(getClass());
	
	
}
