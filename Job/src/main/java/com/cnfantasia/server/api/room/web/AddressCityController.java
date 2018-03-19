package com.cnfantasia.server.api.room.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.room.entity.AddressCityHotEntity;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;


@Controller
@RequestMapping("/addressCity")
public class AddressCityController extends BaseController{
	private IAddressCityBaseService addressCityBaseService;
	public void setAddressCityBaseService(IAddressCityBaseService addressCityBaseService) {
		this.addressCityBaseService = addressCityBaseService;
	}
	
	private IRoomService roomService;
	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}
	@RequestMapping("/getAddressCityListById.json")
	@ResponseBody
	public JsonResponse getAddressCityListById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String provId = request.getParameter("provId");
		AddressCity qry = new AddressCity();
		qry.settProvinceFId(new BigInteger(provId));
		//2.交互
		List<AddressCity> list = addressCityBaseService.getAddressCityByCondition(MapConverter.toMap(qry));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(AddressCity addressCity:list){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", addressCity.getId());
			tmpMap.put("name", addressCity.getName());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	@RequestMapping("/getAddressCityGroupAll.json")
	@ResponseBody
	public JsonResponse getAddressCityGroupAll(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
//		List<AddressCity> addressCityList = addressCityBaseService.getAddressCityByCondition(null);
		List<AddressCity> addressCityList = roomService.getAddressCityListAll();
		List<AddressCityHotEntity> addressCityHotList = roomService.getAddressCityHotList();//热门城市列表
		//3.结果处理
		Map<String,List<Map<String,Object>>> resMap = new HashMap<String, List<Map<String,Object>>>();
		//组装数据
		for(AddressCity tmpAddressCity:addressCityList){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", tmpAddressCity.getId());
			tmpMap.put("name", tmpAddressCity.getName());
			String tmpAlpha = tmpAddressCity.getAlpha();
			if(resMap.containsKey(tmpAlpha)){
				resMap.get(tmpAlpha).add(tmpMap);
			}else{
				List<Map<String,Object>> subList = new ArrayList<Map<String,Object>>();
				subList.add(tmpMap);
				resMap.put(tmpAlpha, subList);
			}
		}
		//组装热门城市数据
		List<Map<String,Object>> hotAddressCity = new ArrayList<Map<String,Object>>();
		if(addressCityHotList!=null&&addressCityHotList.size()>0){
			for(AddressCityHotEntity tmpHot:addressCityHotList){
				AddressCity tmpAddressCity = tmpHot.getAddressCity();
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("id", tmpAddressCity.getId());
				tmpMap.put("name", tmpAddressCity.getName());
				hotAddressCity.add(tmpMap);
			}
		}
		//排序
		List<String> sortKeyList = new ArrayList<String>(resMap.keySet());
		Collections.sort(sortKeyList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1==null||o2==null){
					return 0;
				}
				return o1.compareTo(o2);
			}
		});
		//设置到jsonResponse
		jsonResponse.putData("热门", hotAddressCity);//热门放第一个
		for(String alphaKey:sortKeyList){
			jsonResponse.putData(alphaKey, resMap.get(alphaKey));
		}
		return jsonResponse;
	}
	
	@RequestMapping("/getAddressCityIdByName.json")
	@ResponseBody
	public JsonResponse getAddressCityIdByName(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String cityName = request.getParameter("cityName");
		//2.交互
		//根据cityName查询cityId
		AddressCity addressCityQry = new AddressCity();
		addressCityQry.setName(cityName);
		List<AddressCity> addressCityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.toMap(addressCityQry));
		//3.结果处理
		if(addressCityList!=null&&addressCityList.size()>0){
			jsonResponse.putData("cityId", addressCityList.get(0).getId());
		}else{
			jsonResponse.putData("cityId", null);
		}
		return jsonResponse;
	}
	
}
