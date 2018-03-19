package com.cnfantasia.server.ms.provinceCityBlock.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * 省份，有城市属性
 * 
 * @author wenfq
 * 
 */
public class ProvinceWithCityBlock extends AddressProvince {
	List<CityWithBlock> cityList;

	public List<CityWithBlock> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityWithBlock> cityList) {
		this.cityList = cityList;
	}
}
