package com.cnfantasia.server.ms.provinceCityBlock.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.addressProvince.dao.AddressProvinceBaseDao;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;

public class ProvinceCityBlockDao extends AddressProvinceBaseDao implements IProvinceCityBlockDao {

	@Override
	public List<ProvinceWithCityBlock> getProvinceWithCityBlockList() {
		return sqlSession.selectList("provinceWithCityBlock.select_provinceWithCityBlock_list");
	}
}
