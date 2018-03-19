package com.cnfantasia.server.api.room.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.AddressCityBaseService;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;


@Controller
@RequestMapping("/addressBlock")
public class AddressBlockController extends BaseController{
	private IAddressBlockBaseService addressBlockBaseService;
	
	public void setAddressBlockBaseService(IAddressBlockBaseService addressBlockBaseService) {
		this.addressBlockBaseService = addressBlockBaseService;
	}


	@RequestMapping("/getAddressBlockListById.json")
	@ResponseBody
	public JsonResponse getAddressBlockListById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger cityId = null;
		{
			String cityIdStr = request.getParameter("cityId");
			try {
				cityId = new BigInteger(cityIdStr);
			} catch (Exception e) {}
		}
		if(cityId==null){
			throw new BusinessRuntimeException("addressBlock.getAddressBlockListById.cityId.null");
		}
		AddressBlock qry = new AddressBlock();
		qry.settCityFId(cityId);
		//2.交互
		List<AddressBlock> list = addressBlockBaseService.getAddressBlockByCondition(MapConverter.toMap(qry));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(AddressBlock tmp:list){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", tmp.getId());
			tmpMap.put("name", tmp.getName());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 根据城市名取得其行政区列表
	 * @param request
	 * @author wenfq 2016-03-21
	 * @return
	 */
	@RequestMapping("/getAddressBlockListByCityName.json")
	@ResponseBody
	public JsonResponse getAddressBlockListByCityName(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String cityName = ParamUtils.getString(request, "cityName");
		if(StringUtils.isEmpty(cityName)){
			throw new BusinessRuntimeException("addressBlock.getAddressBlockListByCityName.cityName.null");
		}
		
		AddressCity city = new AddressCity();
		city.setName(cityName);
		
		AddressCityBaseService addressCityBaseService = (AddressCityBaseService)CnfantasiaCommbusiUtil.getBeanManager("addressCityBaseService");
		List<AddressCity> cityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.convertBean(city));
		if (cityList.size() == 0) {
			throw new BusinessRuntimeException("addressBlock.getAddressBlockListByCityName.cityName.notFound");
		} else if (cityList.size() > 1) {
			throw new ValidateRuntimeException("find more than one city by name " + cityName);
		}
		
		AddressBlock qry = new AddressBlock();
		qry.settCityFId(cityList.get(0).getId());
		
		//2.交互
		List<AddressBlock> list = addressBlockBaseService.getAddressBlockByCondition(MapConverter.toMap(qry));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(AddressBlock tmp:list){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", tmp.getId());
			tmpMap.put("name", tmp.getName());
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
}
