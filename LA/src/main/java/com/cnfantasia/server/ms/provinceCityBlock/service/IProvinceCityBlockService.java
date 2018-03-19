package com.cnfantasia.server.ms.provinceCityBlock.service;

import java.util.List;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.CityWithBlock;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;

public interface IProvinceCityBlockService extends IAddressProvinceBaseService {

	/**
	 * 获得所有省信息，省里包含市区信息
	 * 
	 * @return
	 */
	public List<ProvinceWithCityBlock> getProvinceWithCityBlockList();

	/**
	 * 根据省ID获取其所有市信息
	 * 
	 * @param apId
	 *            省ID
	 * @return
	 */
	public List<CityWithBlock> getCityWiBlockList(String apId);

	/**
	 * 根据市ID获取其下所有区信息
	 * 
	 * @param acId
	 *            市ID
	 * @return
	 */
	public List<AddressBlock> getAddressBlockList(String acId);

}
