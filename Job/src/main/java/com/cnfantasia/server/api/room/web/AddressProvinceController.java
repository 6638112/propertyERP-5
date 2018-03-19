package com.cnfantasia.server.api.room.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;


@Controller
@RequestMapping("/addressProvince")
public class AddressProvinceController extends BaseController{
	private IAddressProvinceBaseService addressProvinceBaseService;
	
	public void setAddressProvinceBaseService(IAddressProvinceBaseService addressProvinceBaseService) {
		this.addressProvinceBaseService = addressProvinceBaseService;
	}
	
	@RequestMapping("/getAddressProvinceList.json")
	@ResponseBody
	public JsonResponse getAddressProvinceList(){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		List<AddressProvince> list = addressProvinceBaseService.getAddressProvinceByCondition(null);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(AddressProvince addressProvince:list){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", addressProvince.getId());
			tmpMap.put("name", addressProvince.getName());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
}
