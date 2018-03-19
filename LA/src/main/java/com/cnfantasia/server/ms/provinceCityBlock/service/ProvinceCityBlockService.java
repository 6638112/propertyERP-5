package com.cnfantasia.server.ms.provinceCityBlock.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressProvince.service.AddressProvinceBaseService;
import com.cnfantasia.server.ms.provinceCityBlock.dao.IProvinceCityBlockDao;
import com.cnfantasia.server.ms.provinceCityBlock.entity.CityWithBlock;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;

public class ProvinceCityBlockService extends AddressProvinceBaseService implements IProvinceCityBlockService {
	IProvinceCityBlockDao provinceCityBlockDao;

	public void setProvinceCityBlockDao(IProvinceCityBlockDao provinceCityBlockDao) {
		this.provinceCityBlockDao = provinceCityBlockDao;
	}

	/**
	 * 省市区工具类，只从数据库中取一次，然后缓存到内存中
	 */
	List<ProvinceWithCityBlock> provinceWithCityBlockList = null;

	@Override
	public List<ProvinceWithCityBlock> getProvinceWithCityBlockList() {
		if (provinceWithCityBlockList == null) {
			provinceWithCityBlockList = provinceCityBlockDao.getProvinceWithCityBlockList();
		}
		return provinceWithCityBlockList;
	}

	@Override
	public List<CityWithBlock> getCityWiBlockList(String apId) {
		if (provinceWithCityBlockList == null) {
			provinceWithCityBlockList = provinceCityBlockDao.getProvinceWithCityBlockList();
		}

		for (int i = 0; i < provinceWithCityBlockList.size(); i++) {
			if (provinceWithCityBlockList.get(i).getId().toString().equals(apId)) {
				return provinceWithCityBlockList.get(i).getCityList();
			}
		}

		return null;
	}

	@Override
	public List<AddressBlock> getAddressBlockList(String acId) {
		if (provinceWithCityBlockList == null) {
			provinceWithCityBlockList = provinceCityBlockDao.getProvinceWithCityBlockList();
		}

		for (int i = 0; i < provinceWithCityBlockList.size(); i++) {
			for (int j = 0; j < provinceWithCityBlockList.get(i).getCityList().size(); j++) {
				BigInteger cityId = provinceWithCityBlockList.get(i).getCityList().get(j).getId();
				if (acId.equals(cityId.toString())) {
					return provinceWithCityBlockList.get(i).getCityList().get(j).getBlockList();
				}
			}
		}

		return null;
	}
}
