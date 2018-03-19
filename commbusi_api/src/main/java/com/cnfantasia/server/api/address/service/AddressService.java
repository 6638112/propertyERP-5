package com.cnfantasia.server.api.address.service;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

import java.util.List;

/**
 * @className: AddressService.
 * @date: 2017-11-08 17:14
 * @author: kangduo
 * @description: ()
 */
public interface AddressService {

    /**
     * 获取带有省市区的地址列表
     * @return 地址列表
     */
    List<AddressProvince> getAddressAllLevelList();
}
