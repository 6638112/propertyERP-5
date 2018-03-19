package com.cnfantasia.server.api.address.dao;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

/**
 * @className: AddressDaoImpl.
 * @date: 2017-11-08 17:13
 * @author: kangduo
 * @description: ()
 */
public class AddressDaoImpl extends AbstractBaseDao implements AddressDao {

    @Override
    public List<AddressProvince> getAddressAllLevelList() {
        return sqlSession.selectList("address.getAddressAllLevelList");
    }
}
