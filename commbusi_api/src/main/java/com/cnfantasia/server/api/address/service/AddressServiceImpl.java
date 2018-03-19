package com.cnfantasia.server.api.address.service;

import com.cnfantasia.server.api.address.dao.AddressDao;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @className: AddressServiceImpl.
 * @date: 2017-11-08 17:14
 * @author: kangduo
 * @description: ()
 */
public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao;

    @Override
    public List<AddressProvince> getAddressAllLevelList() {
        return addressDao.getAddressAllLevelList();
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
