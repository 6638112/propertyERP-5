package com.cnfantasia.server.api.room.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;


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
	
}
