package com.cnfantasia.server.ms.provinceCityBlock.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.addressProvince.dao.IAddressProvinceBaseDao;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;

public interface IProvinceCityBlockDao extends IAddressProvinceBaseDao {

	List<ProvinceWithCityBlock> getProvinceWithCityBlockList();
}
