package com.cnfantasia.server.api.address.dao;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

import java.util.List;

/**
 * @className: AddressDao.
 * @date: 2017-11-08 17:13
 * @author: kangduo
 * @description: ()
 */
public interface AddressDao {

    /**
     * 获取带有省市区的地址列表
     * @return 地址列表
     */
    List<AddressProvince> getAddressAllLevelList();
}
