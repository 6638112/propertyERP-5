package com.cnfantasia.server.ms.addressCity.web;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/addressCity")
public class AddressCityController extends BaseController{
	@Resource
	private IAddressCityBaseService addressCityBaseService;
	public void setAddressCityBaseService(IAddressCityBaseService addressCityBaseService) {
		this.addressCityBaseService = addressCityBaseService;
	}

	@RequestMapping(value = "/getAddressCityIdByName.json", method = RequestMethod.GET)
	@ResponseBody
	public List getAddressCityIdByName(String name){
		//根据cityName查询cityId
		PageModel pageModel = new PageModel(0, 20);

		AddressCity addressCity = new AddressCity();
		if (!name.trim().equals("")) {
			addressCity.setName(name);
		}
		return addressCityBaseService.getAddressCityByConditionDim(MapConverter.toMap(addressCity), pageModel);
	}
	
}
