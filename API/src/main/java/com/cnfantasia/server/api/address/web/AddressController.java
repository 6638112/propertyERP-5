package com.cnfantasia.server.api.address.web;

import com.cnfantasia.server.api.address.service.AddressService;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: AddressController.
 * @date: 2017-11-08 17:04
 * @author: kangduo
 * @description: ()
 */
@Controller
@RequestMapping("/addresses")
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public List<AddressProvince> getAddresses() {
        return addressService.getAddressAllLevelList();
    }
}
