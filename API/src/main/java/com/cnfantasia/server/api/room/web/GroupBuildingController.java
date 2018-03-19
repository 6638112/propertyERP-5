package com.cnfantasia.server.api.room.web;

import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.entity.BaiduLocation;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.communitySupply.util.LocationConverter;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntityWithDistance;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.room.entity.SimpleGbInfo;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.api.room.tmpData.GbInfoConvert;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.newUserLocation.entity.NewUserLocation;
import com.cnfantasia.server.domainbase.newUserLocation.service.INewUserLocationBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;
import com.cnfantasia.server.domainbase.userSwitchGb.service.IUserSwitchGbBaseService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

	@Resource
	private IUserSwitchGbBaseService userSwitchGbBaseService;

	/**
	 * 查询附近的小区.
	 * @param lat 纬度
	 * @param lnt 经度
	 * @since v513
	 * @return 小区列表
	 */
	@RequestMapping("/nearbyGbs.json")
	@ResponseBody
	public JsonResponse getNearbyGbs(String lng, String lat) {
		JsonResponse json = new JsonResponse();
		if (!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lng)) {
			Location location = new Location(lng, lat);
			location = LocationConverter.gcj02ToBd09(location);
			List<SimpleGbInfo> nearbyGbs = groupBuildingService.getNearbyGbs(new BigDecimal(location.getLng()), new BigDecimal(location.getLat()));
			json.putData("gbList", nearbyGbs);
		} else {
			json.putData("gbList", null);
		}
		return json;
	}


	/**
	 * 切换小区.
	 * @since v513
	 * @param gbId 小区ID
	 * @return
	 */
	@RequestMapping("/switchGb.json")
	@ResponseBody
	public JsonResponse switchGb(@RequestParam("gbId") BigInteger gbId) {
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		UserSwitchGb switchGb = new UserSwitchGb();
		switchGb.setGbId(gbId);
		switchGb.setUserId(userId);
		switchGb.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_switch_gb));
		switchGb.setSys0AddTime(DateUtil.getCurrSysTimeStr());
		userSwitchGbBaseService.insertUserSwitchGb(switchGb);
		return new JsonResponse();
	}

	/**
	 * 查询最近登录的小区，包括手动切换，改门牌，加门牌，切门牌.
	 * @since v513
	 * @return
	 */
	@RequestMapping("/recentGbs.json")
	@ResponseBody
	public JsonResponse recentGbs() {
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		List<SimpleGbInfo> recentGbs = groupBuildingService.getRecentGbs(userId);
		JsonResponse json = new JsonResponse();
		json.putData("gbList", recentGbs);
		return json;
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
	
	@Resource
	IUuidManager uuidManager;
	
	@Resource
	INewUserLocationBaseService newUserLocationBaseService;
	
	@RequestMapping("/qryGroupBuildingByLocation.json")
	@ResponseBody
	public JsonResponse qryGroupBuildingByLocation(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Map<String, Object> locationParamMap = getLocationParam(request);
		
		BigInteger gbId = groupBuildingService.getGroupBuildingByLocationCondition(locationParamMap);
	
		Map<String, Object> resultMap = groupBuildingService.queryGroupBuildingInfoById(gbId);
		if(resultMap.get("gbId").toString().equals("-1")){
			Map<String, Object> city_Province_Name_Map = groupBuildingService.queryAddressInfo_By_City_Province_Name(locationParamMap);
			resultMap.putAll(city_Province_Name_Map);
		}
		
		int hasFound = resultMap.get("gbName").toString().contains((locationParamMap.get("gbName").toString())) ? 1 : 0;

		resultMap.put("hasFound", hasFound);//是否根据定位找到了小区
		
		RedisCacheHandler.set(HeaderManager.getDeviceId() + "_gbId", gbId.toString(), 60 * 60 * 24 * 30);//缓存30天
		RedisCacheHandler.set(HeaderManager.getDeviceId() + "_hasFound", hasFound+"", 60 * 60 * 24 * 30);
		
		saveLocationResult(gbId, resultMap, hasFound);
		
		jsonResponse.setDataValue(resultMap);
		
		return jsonResponse;
	}

	/**
	 * 保存用户定位信息
	 * @param gbId
	 * @param resultMap
	 * @param hasFound
	 */
	private void saveLocationResult(BigInteger gbId, Map<String, Object> resultMap, int hasFound) {
		NewUserLocation nulQry = new NewUserLocation();
		nulQry.setDeviceid(HeaderManager.getDeviceId());
		List<NewUserLocation> nulList = newUserLocationBaseService.getNewUserLocationByCondition(MapConverter.convertBean(nulQry));
		
		NewUserLocation nul = null;
		
		if(nulList.size() == 0){
			nul = new NewUserLocation();
			nul.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_new_user_location));
		}else{
			nul = nulList.get(0);
		}
		
		nul.setDeviceid(HeaderManager.getDeviceId());
		nul.setHasfound(hasFound);
		nul.setGbid(gbId);
		nul.setSignStatus((Integer) resultMap.get("signStatus"));
		
		if(nulList.size() == 0){
			newUserLocationBaseService.insertNewUserLocation(nul);
		}else{
			newUserLocationBaseService.updateNewUserLocation(nul);
		}
	}
	
	/**
	 * 获取前端定位参数 
	 * @author wenfq 
	 * @param request
	 * @return
	 */
	private Map<String, Object> getLocationParam(HttpServletRequest request) {
		Map<String, Object> locationParamMap = new HashMap<String, Object>();
		
		if(request.getParameter("gbName") !=null)
			locationParamMap.put("gbName", request.getParameter("gbName"));
		if(request.getParameter("blockName") !=null)
			locationParamMap.put("blockName", request.getParameter("blockName"));
		if(request.getParameter("cityName") !=null)
			locationParamMap.put("cityName", request.getParameter("cityName"));
		if(request.getParameter("provinceName") !=null)
			locationParamMap.put("provinceName", request.getParameter("provinceName"));
		return locationParamMap;
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
	
	/**
	 * 根据市、区、小区名称查询小区是否存在，并返回其id（轻应用收货地址）
	 * 
	 * @param gbName
	 * @param city
	 * @param block
	 * @return
	 */
	@RequestMapping("/qryGroupBuildingByAddress.json")
	@ResponseBody
	public JsonResponse qryGroupBuildingByAddress(String gbName, BigInteger city, BigInteger block){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", gbName);
		paramMap.put("city", city);
		paramMap.put("block", block);
		
		List<Map<String, Object>> list = groupBuildingService.selectGroupBuildingByAddress(paramMap);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(list);
		return jsonResponse;
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
